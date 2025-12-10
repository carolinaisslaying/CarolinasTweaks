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
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CarolinasTweaks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Remove ModTags.Blocks.METAL_DETECTOR_VALUABLES line if you don't have ModTags class
        // Or create it if needed

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BUNDLE_OF_STICKS.get(),
                        ModBlocks.CONDENSED_COCOA.get(),
                        ModBlocks.GOLD_PAINTED_DOOR.get(),
                        ModBlocks.BLOCK_OF_LEATHER.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.BLOCK_OF_GUNPOWDER.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CONDENSED_QUARTZ_BLOCK.get(),
                        ModBlocks.CONDENSED_QUARTZ_BRICKS.get(),
                        ModBlocks.CONDENSED_QUARTZ_PILLAR.get(),
                        ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get(),
                        ModBlocks.CONDENSED_QUARTZ_STAIRS.get(),
                        ModBlocks.CONDENSED_QUARTZ_SLAB.get());
    }
}
