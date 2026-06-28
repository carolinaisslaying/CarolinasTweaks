package icu.carolinainthe.carolinastweaks.datagen;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CarolinasTweaks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BUNDLE_OF_STICKS.get(),
                        ModBlocks.CONDENSED_COCOA.get(),
                        ModBlocks.GOLD_PAINTED_DOOR.get(),
                        ModBlocks.BLOCK_OF_LEATHER.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.BLOCK_OF_GUNPOWDER.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CONDENSED_QUARTZ_BLOCK.get(),
                        ModBlocks.CONDENSED_QUARTZ_BRICKS.get(),
                        ModBlocks.CONDENSED_QUARTZ_PILLAR.get(),
                        ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get(),
                        ModBlocks.CONDENSED_QUARTZ_STAIRS.get(),
                        ModBlocks.CONDENSED_QUARTZ_SLAB.get());
    }
}
