/*
    Carolina's Tweaks - A Minecraft Fabric Mod with a collection of minor adjustments and tweaks to improve your game.
    Copyright (C) 2025 Carolina Mitchell
    
    This licence notice only applies to non-asset components relating to this software. For the assets licence,
    see the ASSETS_LICENCE.md file.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package icu.carolinainthe.carolinastweaks.blocks;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModBlocks {
    public static final Map<Block, Block> MIGRATION_MAP = new HashMap<>();

    // Registers all mod blocks.
    public static final Block BUNDLE_OF_STICKS = registerBlock("bundle_of_sticks",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO_BLOCK)
                    .strength(0.5f, 0.2f)
            ));

    public static final Block CONDENSED_COCOA = registerBlock("condensed_cocoa",
            new Block(FabricBlockSettings.copyOf(Blocks.COCOA)
                .strength(0.8f, 3.0f)
            ));

    public static final Block GOLD_PAINTED_DOOR = registerBlock("gold_painted_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), BlockSetType.OAK));

    public static final Block BLOCK_OF_GUNPOWDER = registerBlock("block_of_gunpowder",
            new FallingBlock(FabricBlockSettings.copyOf(Blocks.SAND)));

    public static final Block CONDENSED_QUARTZ_BLOCK = registerBlock("condensed_quartz_block",
            new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)
                    .strength(1.2f)
            ));

    public static final Block CONDENSED_QUARTZ_PILLAR = registerBlock("condensed_quartz_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR)
                    .strength(1.2f)
            ));

    public static final Block CONDENSED_QUARTZ_BRICKS = registerBlock("condensed_quartz_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)
                    .strength(1.2f)
            ));

    public static final Block CHISELED_CONDENSED_QUARTZ_BLOCK = registerBlock("chiseled_condensed_quartz_block",
            new Block(FabricBlockSettings.copyOf(Blocks.CHISELED_QUARTZ_BLOCK)
                    .strength(1.2f)
            ));

    public static final Block CONDENSED_QUARTZ_STAIRS = registerBlock("condensed_quartz_stairs",
            new StairsBlock(ModBlocks.CONDENSED_QUARTZ_BLOCK.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.QUARTZ_STAIRS)
                        .strength(1.2f)
            ));

    public static final Block CONDENSED_QUARTZ_SLAB = registerBlock("condensed_quartz_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_SLAB)
                    .strength(1.2f)
            ));

    public static final Block BLOCK_OF_LEATHER = registerBlock("block_of_leather",
            new Block(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL)
                    .strength(1.5f, 1.0f)
            ));

    // Register block function.
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, name), block);
    }

    // Register individual block item function.
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    // Function to register multiple flammable blocks.
    private static void registerFlammableBlocks(Block... blocks) {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        for (Block block : blocks) {
            registry.add(block, 5, 5);
        }
    }

    // Migration helper
    // In ModBlocks.java
    private static void createGhost(String oldPath, Block dummyBlock, Block realBlock) {
        Identifier oldId = new Identifier("carolinas-tweaks", oldPath);
        Registry.register(Registries.BLOCK, oldId, dummyBlock);

        // Create the Dummy Item
        BlockItem dummyItem = new BlockItem(dummyBlock, new Item.Settings());
        Registry.register(Registries.ITEM, oldId, dummyItem);

        // Link Block Migration
        MIGRATION_MAP.put(dummyBlock, realBlock);

        // --- ADD THIS LINE ---
        // Link Item Migration (using the map from ModItems)
        // This ensures the Item form of the block migrates in the inventory
        icu.carolinainthe.carolinastweaks.items.ModItems.ITEM_MIGRATION_MAP.put(dummyItem, realBlock.asItem());
    }

    // Main function to register all items, called in the main class.
    public static void registerModBlocks() {
        CarolinasTweaks.LOGGER.info("Registering mod blocks for " + MOD_ID + ", created by Carolina Mitchell (carolina_slays)");
        registerFlammableBlocks(BUNDLE_OF_STICKS, BLOCK_OF_GUNPOWDER);

        // ====================================================================
        // MIGRATION REGISTRATION
        // We now register specific classes (Pillar, Door, Stairs, etc)
        // so they can accept properties like 'axis' or 'facing' from the save.
        // ====================================================================

        // 1. PILLARS (Must support Axis)
        createGhost("bundle_of_sticks", new PillarBlock(FabricBlockSettings.copy(BUNDLE_OF_STICKS)), BUNDLE_OF_STICKS);
        createGhost("condensed_quartz_pillar", new PillarBlock(FabricBlockSettings.copy(CONDENSED_QUARTZ_PILLAR)), CONDENSED_QUARTZ_PILLAR);

        // 2. DOORS (Must support Open/Hinge/Facing/Half)
        createGhost("gold_painted_door", new DoorBlock(FabricBlockSettings.copy(GOLD_PAINTED_DOOR), BlockSetType.OAK), GOLD_PAINTED_DOOR);

        // 3. FALLING BLOCKS
        createGhost("block_of_gunpowder", new FallingBlock(FabricBlockSettings.copy(BLOCK_OF_GUNPOWDER)), BLOCK_OF_GUNPOWDER);

        // 4. STAIRS (Must support Facing/Half/Shape)
        createGhost("condensed_quartz_stairs", new StairsBlock(CONDENSED_QUARTZ_BLOCK.getDefaultState(), FabricBlockSettings.copy(CONDENSED_QUARTZ_STAIRS)), CONDENSED_QUARTZ_STAIRS);

        // 5. SLABS (Must support Type/Waterlogged)
        createGhost("condensed_quartz_slab", new SlabBlock(FabricBlockSettings.copy(CONDENSED_QUARTZ_SLAB)), CONDENSED_QUARTZ_SLAB);

        // 6. STANDARD BLOCKS (Generic Block is fine)
        createGhost("condensed_cocoa", new Block(FabricBlockSettings.copy(CONDENSED_COCOA)), CONDENSED_COCOA);
        createGhost("condensed_quartz_block", new Block(FabricBlockSettings.copy(CONDENSED_QUARTZ_BLOCK)), CONDENSED_QUARTZ_BLOCK);
        createGhost("condensed_quartz_bricks", new Block(FabricBlockSettings.copy(CONDENSED_QUARTZ_BRICKS)), CONDENSED_QUARTZ_BRICKS);
        createGhost("chiseled_condensed_quartz_block", new Block(FabricBlockSettings.copy(CHISELED_CONDENSED_QUARTZ_BLOCK)), CHISELED_CONDENSED_QUARTZ_BLOCK);
        createGhost("block_of_leather", new Block(FabricBlockSettings.copy(BLOCK_OF_LEATHER)), BLOCK_OF_LEATHER);
    }
}