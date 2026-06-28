/*
    Carolina's Tweaks - A Minecraft Neoforge Mod (originally Fabric)
    with a collection of minor adjustments and tweaks to improve your game.

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
import icu.carolinainthe.carolinastweaks.blocks.custom.FlammableFallingBlock;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import icu.carolinainthe.carolinastweaks.blocks.custom.FlammablePillarBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MOD_ID);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        CarolinasTweaks.LOGGER.info("Registering mod blocks for "
                + MOD_ID + ", created by Carolina Mitchell (carolina_slaying)");

        BLOCKS.register(eventBus);
    }

    public static final DeferredBlock<Block> BUNDLE_OF_STICKS = registerBlock("bundle_of_sticks",
            () -> new FlammablePillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_BLOCK)
                    .strength(0.5f, 0.2f)));

    public static final DeferredBlock<Block> CONDENSED_COCOA = registerBlock("condensed_cocoa",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA)
                    .strength(0.8f, 3.0f)));

    public static final DeferredBlock<Block> GOLD_PAINTED_DOOR = registerBlock("gold_painted_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));

    public static final DeferredBlock<Block> BLOCK_OF_GUNPOWDER = registerBlock("block_of_gunpowder",
            () -> new FlammableFallingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)));

    public static final DeferredBlock<Block> CONDENSED_QUARTZ_BLOCK = registerBlock("condensed_quartz_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
                    .strength(1.2f)));

    public static final DeferredBlock<Block> CONDENSED_QUARTZ_PILLAR = registerBlock("condensed_quartz_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)
                    .strength(1.2f)));

    public static final DeferredBlock<Block> CONDENSED_QUARTZ_BRICKS = registerBlock("condensed_quartz_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS)
                    .strength(1.2f)));

    public static final DeferredBlock<Block> CHISELED_CONDENSED_QUARTZ_BLOCK = registerBlock("chiseled_condensed_quartz_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_QUARTZ_BLOCK)
                    .strength(1.2f)));

    public static final DeferredBlock<Block> CONDENSED_QUARTZ_STAIRS = registerBlock("condensed_quartz_stairs",
            () -> new StairBlock(CONDENSED_QUARTZ_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS)
                            .strength(1.2f)));

    public static final DeferredBlock<Block> CONDENSED_QUARTZ_SLAB = registerBlock("condensed_quartz_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB)
                    .strength(1.2f)));

    public static final DeferredBlock<Block> BLOCK_OF_LEATHER = registerBlock("block_of_leather",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL)
                    .strength(1.5f, 1.0f)));
}