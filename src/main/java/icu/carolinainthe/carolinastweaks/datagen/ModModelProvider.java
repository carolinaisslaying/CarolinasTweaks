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
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerAxisRotated(ModBlocks.BUNDLE_OF_STICKS, TexturedModel.END_FOR_TOP_CUBE_COLUMN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CONDENSED_COCOA);
        blockStateModelGenerator.registerDoor(ModBlocks.GOLD_PAINTED_DOOR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_GUNPOWDER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_OF_LEATHER);

        BlockStateModelGenerator.BlockTexturePool condensedQuartzPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CONDENSED_QUARTZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CONDENSED_QUARTZ_BRICKS);
        blockStateModelGenerator.registerAxisRotated(ModBlocks.CONDENSED_QUARTZ_PILLAR,
                TexturedModel.END_FOR_TOP_CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK,
                TexturedModel.END_FOR_TOP_CUBE_COLUMN);

        condensedQuartzPool.stairs(ModBlocks.CONDENSED_QUARTZ_STAIRS);
        condensedQuartzPool.slab(ModBlocks.CONDENSED_QUARTZ_SLAB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BOTTLE_OF_BERRY_JUICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHEAT_SEED_PACKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_SEED_PACKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_SEED_PACKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEETROOT_SEED_PACKET, Models.GENERATED);
    }
}
