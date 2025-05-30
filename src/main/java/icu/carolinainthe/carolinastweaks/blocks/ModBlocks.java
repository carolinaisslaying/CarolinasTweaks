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

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModBlocks {
    // Registers all mod blocks.
    public static final Block BUNDLE_OF_STICKS = registerBlock("bundle_of_sticks",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO_BLOCK)
                    .strength(0.5f, 0.2f)
            ));

    public static final Block CONDENSED_COCOA = registerBlock("condensed_cocoa",
            new Block(FabricBlockSettings.copyOf(Blocks.COCOA)
                .strength(0.8f, 3.0f)
            ));

    public static final Block CONDENSED_QUARTZ_BLOCK = registerBlock("condensed_quartz_block",
            new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)
                    .strength(1.2f)
            ));

    public static final Block GOLD_PAINTED_DOOR = registerBlock("gold_painted_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), BlockSetType.OAK));

    public static final Block BLOCK_OF_GUNPOWDER = registerBlock("block_of_gunpowder",
            new FallingBlock(FabricBlockSettings.copyOf(Blocks.SAND)));

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

    // Main function to register all items, called in the main class.
    public static void registerModBlocks() {
        CarolinasTweaks.LOGGER.info("Registering mod blocks for " + MOD_ID + ", created by Carolina Mitchell (carolina_slays)");
        registerFlammableBlocks(BUNDLE_OF_STICKS, BLOCK_OF_GUNPOWDER);
    }
}