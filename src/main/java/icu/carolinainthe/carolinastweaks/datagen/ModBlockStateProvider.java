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

package icu.carolinainthe.carolinastweaks.datagen;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CarolinasTweaks.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Axis rotated block (pillar-style)
        logBlock((RotatedPillarBlock) ModBlocks.BUNDLE_OF_STICKS.get());
        blockItem(ModBlocks.BUNDLE_OF_STICKS);

        // Simple cube all blocks
        blockWithItem(ModBlocks.CONDENSED_COCOA);
        blockWithItem(ModBlocks.BLOCK_OF_GUNPOWDER);
        blockWithItem(ModBlocks.BLOCK_OF_LEATHER);

        // Door (modLoc required for texture references)
        doorBlockWithRenderType((DoorBlock) ModBlocks.GOLD_PAINTED_DOOR.get(),
                modLoc("block/gold_painted_door_bottom"),
                modLoc("block/gold_painted_door_top"),
                "cutout");

        // Condensed quartz blocks
        blockWithItem(ModBlocks.CONDENSED_QUARTZ_BLOCK);
        blockWithItem(ModBlocks.CONDENSED_QUARTZ_BRICKS);

        // Axis rotated pillar
        logBlock((RotatedPillarBlock) ModBlocks.CONDENSED_QUARTZ_PILLAR.get());
        blockItem(ModBlocks.CONDENSED_QUARTZ_PILLAR);

        // Chiseled block (top/bottom texture different from sides)
        simpleBlock(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get(), models().cubeColumn(
                "chiseled_condensed_quartz_block",
                blockTexture(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get()),
                blockTexture(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get()).withSuffix("_top")));
        blockItem(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK);

        // Stairs and slab
        stairsBlock((StairBlock) ModBlocks.CONDENSED_QUARTZ_STAIRS.get(),
                blockTexture(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()));
        blockItem(ModBlocks.CONDENSED_QUARTZ_STAIRS);

        slabBlock((SlabBlock) ModBlocks.CONDENSED_QUARTZ_SLAB.get(),
                blockTexture(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                blockTexture(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()));
        blockItem(ModBlocks.CONDENSED_QUARTZ_SLAB);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(
                modLoc("block/" + blockRegistryObject.getId().getPath())));
    }
}
