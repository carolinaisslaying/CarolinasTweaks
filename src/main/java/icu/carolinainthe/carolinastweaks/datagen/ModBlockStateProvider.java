package icu.carolinainthe.carolinastweaks.datagen;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

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

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("carolinas_tweaks:block/" + deferredBlock.getId().getPath()));
    }
}
