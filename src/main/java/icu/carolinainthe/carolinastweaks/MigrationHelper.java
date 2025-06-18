package icu.carolinainthe.carolinastweaks;

import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;

public class MigrationHelper {
    public static void init() {
        System.out.println("!!! MIGRATION HELPER ACTIVE - v2 (State Copy + Inventory) !!!");

        // ---------------------------------------------------------
        // 1. BLOCK MIGRATION (Chunk Load)
        // ---------------------------------------------------------
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            if (chunk instanceof WorldChunk) {
                boolean markedDirty = false;

                Iterable<BlockPos> positions = BlockPos.iterate(
                        chunk.getPos().getStartX(), world.getBottomY(), chunk.getPos().getStartZ(),
                        chunk.getPos().getEndX(), world.getTopY(), chunk.getPos().getEndZ()
                );

                for (BlockPos pos : positions) {
                    BlockState oldState = chunk.getBlockState(pos);
                    Block oldBlock = oldState.getBlock();

                    if (ModBlocks.MIGRATION_MAP.containsKey(oldBlock)) {
                        Block newBlock = ModBlocks.MIGRATION_MAP.get(oldBlock);

                        // --- FIX FOR DOORS/STAIRS ---
                        // Instead of getDefaultState(), we copy properties!
                        BlockState newState = newBlock.getDefaultState();

                        // Loop through all properties in the OLD block (Facing, Open, Half, etc.)
                        for (Property<?> prop : oldState.getProperties()) {
                            // If the NEW block supports the same property, copy the value
                            if (newState.contains(prop)) {
                                newState = copyProperty(oldState, newState, prop);
                            }
                        }

                        chunk.setBlockState(pos, newState, false);
                        markedDirty = true;
                    }
                }

                if (markedDirty) {
                    chunk.setNeedsSaving(true);
                }
            }
        });

        // ---------------------------------------------------------
        // 2. INVENTORY MIGRATION (Player Tick)
        // ---------------------------------------------------------
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
                boolean inventoryChanged = false;

                // Scan main inventory
                for (int i = 0; i < player.getInventory().size(); i++) {
                    ItemStack stack = player.getInventory().getStack(i);
                    if (!stack.isEmpty() && ModItems.ITEM_MIGRATION_MAP.containsKey(stack.getItem())) {

                        // Found a Ghost Item! Swap it.
                        Item newItem = ModItems.ITEM_MIGRATION_MAP.get(stack.getItem());
                        ItemStack newStack = new ItemStack(newItem, stack.getCount());

                        // Copy NBT (Enchantments, names, etc.)
                        if (stack.hasNbt()) {
                            newStack.setNbt(stack.getNbt().copy());
                        }

                        player.getInventory().setStack(i, newStack);
                        inventoryChanged = true;
                    }
                }

                // If we swapped items, force an inventory sync/save
                if (inventoryChanged) {
                    System.out.println("Migrated items for player: " + player.getName().getString());
                }
            }
        });
    }

    // Helper to safely copy a generic property between states
    private static <T extends Comparable<T>> BlockState copyProperty(BlockState from, BlockState to, Property<T> property) {
        return to.with(property, from.get(property));
    }
}