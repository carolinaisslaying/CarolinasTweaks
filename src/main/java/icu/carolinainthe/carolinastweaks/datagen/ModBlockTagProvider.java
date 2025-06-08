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

package icu.carolinainthe.carolinastweaks.datagen;

import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.BUNDLE_OF_STICKS)
                .add(ModBlocks.CONDENSED_COCOA)
                .add(ModBlocks.GOLD_PAINTED_DOOR)
                .add(ModBlocks.BLOCK_OF_LEATHER);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.BLOCK_OF_GUNPOWDER);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.CONDENSED_QUARTZ_BLOCK)
                .add(ModBlocks.CONDENSED_QUARTZ_BRICKS)
                .add(ModBlocks.CONDENSED_QUARTZ_PILLAR)
                .add(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK)
                .add(ModBlocks.CONDENSED_QUARTZ_STAIRS)
                .add(ModBlocks.CONDENSED_QUARTZ_SLAB);
    }
}
