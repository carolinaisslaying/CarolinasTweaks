package icu.carolinainthe.carolinastweaks;

import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MigrationHelper {

    // A thread-safe queue to store chunks that need processing
    private static final Queue<ChunkData> CHUNK_QUEUE = new ConcurrentLinkedQueue<>();

    private record ChunkData(ServerWorld world, WorldChunk chunk) {}

    public static void init() {
        System.out.println("!!! MIGRATION HELPER ACTIVE - v4 (Queued Processing) !!!");

        // 1. Queue chunks when they load (Don't touch them yet!)
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            if (world instanceof ServerWorld && chunk instanceof WorldChunk) {
                CHUNK_QUEUE.add(new ChunkData((ServerWorld) world, (WorldChunk) chunk));
            }
        });

        // 2. Process the queue at the end of the server tick (Safe time)
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Process up to 5 chunks per tick to prevent lag spikes/hanging
            int processed = 0;
            while (!CHUNK_QUEUE.isEmpty() && processed < 5) {
                ChunkData data = CHUNK_QUEUE.poll();
                if (data != null) {
                    processChunk(data.world, data.chunk);
                    processed++;
                }
            }

            // Process players every tick
            for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (migrateInventory(player.getInventory())) {
                    System.out.println("Migrated inventory items for: " + player.getName().getString());
                }
            }
        });
    }

    private static void processChunk(ServerWorld world, WorldChunk chunk) {
        boolean chunkDirty = false;

        // --- 1. BLOCK MIGRATION ---
        // Use a safe iterator over block positions
        // Note: For large chunks, iterating every block is heavy.
        // We only scan if we suspect there might be old blocks.
        // A full scan is safer for migration though.
        Iterable<BlockPos> positions = BlockPos.iterate(
                chunk.getPos().getStartX(), world.getBottomY(), chunk.getPos().getStartZ(),
                chunk.getPos().getEndX(), world.getTopY(), chunk.getPos().getEndZ()
        );

        for (BlockPos pos : positions) {
            // Fix Blocks
            if (migrateBlock(chunk, pos)) {
                chunkDirty = true;
            }

            // Fix Chests/Containers
            BlockEntity be = chunk.getBlockEntity(pos);
            if (be instanceof Inventory inventory) {
                if (migrateInventory(inventory)) {
                    be.markDirty();
                    chunkDirty = true;
                }
            }
        }

        // --- 2. DROPPED ITEM MIGRATION ---
        Box chunkBox = new Box(
                chunk.getPos().getStartX(), world.getBottomY(), chunk.getPos().getStartZ(),
                chunk.getPos().getEndX(), world.getTopY(), chunk.getPos().getEndZ()
        );

        List<ItemEntity> droppedItems = world.getEntitiesByClass(ItemEntity.class, chunkBox, entity -> true);

        for (ItemEntity entity : droppedItems) {
            ItemStack stack = entity.getStack();
            if (ModItems.ITEM_MIGRATION_MAP.containsKey(stack.getItem())) {
                Item newItem = ModItems.ITEM_MIGRATION_MAP.get(stack.getItem());
                ItemStack newStack = new ItemStack(newItem, stack.getCount());
                if (stack.hasNbt()) newStack.setNbt(stack.getNbt().copy());
                entity.setStack(newStack);
                chunkDirty = true;
                System.out.println("Migrated Dropped Item at " + entity.getPos());
            }
        }

        if (chunkDirty) {
            chunk.setNeedsSaving(true);
            System.out.println("Migrated chunk at " + chunk.getPos());
        }
    }

    private static boolean migrateInventory(Inventory inventory) {
        boolean changed = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && ModItems.ITEM_MIGRATION_MAP.containsKey(stack.getItem())) {
                Item newItem = ModItems.ITEM_MIGRATION_MAP.get(stack.getItem());
                ItemStack newStack = new ItemStack(newItem, stack.getCount());
                if (stack.hasNbt()) newStack.setNbt(stack.getNbt().copy());
                inventory.setStack(i, newStack);
                changed = true;
            }
        }
        return changed;
    }

    private static boolean migrateBlock(WorldChunk chunk, BlockPos pos) {
        BlockState oldState = chunk.getBlockState(pos);
        Block oldBlock = oldState.getBlock();

        if (ModBlocks.MIGRATION_MAP.containsKey(oldBlock)) {
            Block newBlock = ModBlocks.MIGRATION_MAP.get(oldBlock);
            BlockState newState = newBlock.getDefaultState();

            for (Property<?> prop : oldState.getProperties()) {
                if (newState.contains(prop)) {
                    newState = copyProperty(oldState, newState, prop);
                }
            }

            // Force update without notifying neighbors to prevent cascades
            chunk.setBlockState(pos, newState, false);
            return true;
        }
        return false;
    }

    private static <T extends Comparable<T>> BlockState copyProperty(BlockState from, BlockState to, Property<T> property) {
        return to.with(property, from.get(property));
    }
}