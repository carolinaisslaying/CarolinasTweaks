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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.STICK,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BUNDLE_OF_STICKS);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.COCOA_BEANS,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_COCOA);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.GUNPOWDER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOCK_OF_GUNPOWDER);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.QUARTZ,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BLOCK);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.LEATHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOCK_OF_LEATHER);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.QUARTZ, 4)
                .input(Blocks.QUARTZ_BLOCK)
                .criterion(hasItem(Items.QUARTZ_BLOCK), conditionsFromItem(Items.QUARTZ_BLOCK))
                .offerTo(exporter, new Identifier("carolinas-tweaks", "quartz_from_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_PILLAR, 2)
                .pattern("C")
                .pattern("C")
                .input('C', ModBlocks.CONDENSED_QUARTZ_BLOCK)
                .criterion(hasItem(ModBlocks.CONDENSED_QUARTZ_BLOCK), conditionsFromItem((ModBlocks.CONDENSED_QUARTZ_BLOCK)))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BRICKS, 4)
                .pattern("CC")
                .pattern("CC")
                .input('C', ModBlocks.CONDENSED_QUARTZ_BLOCK)
                .criterion(hasItem(ModBlocks.CONDENSED_QUARTZ_BLOCK), conditionsFromItem((ModBlocks.CONDENSED_QUARTZ_BLOCK)))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.BELL, 1)
            .pattern("SWS")
            .pattern("SGS")
            .pattern("SNS")
            .input('S', Blocks.STONE)
            .input('W', ItemTags.WOODEN_SLABS)
            .input('N', Blocks.NOTE_BLOCK)
            .input('G', Blocks.GOLD_BLOCK)
            .criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK))
            .criterion(hasItem(Blocks.NOTE_BLOCK), conditionsFromItem(Blocks.NOTE_BLOCK))
            .offerTo(exporter);

        createStairsRecipe(ModBlocks.CONDENSED_QUARTZ_STAIRS, Ingredient.ofItems(ModBlocks.CONDENSED_QUARTZ_BLOCK))
                .criterion(hasItem(ModBlocks.CONDENSED_QUARTZ_BLOCK), conditionsFromItem((ModBlocks.CONDENSED_QUARTZ_BLOCK)))
                .offerTo(exporter);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_SLAB,
                ModBlocks.CONDENSED_QUARTZ_BLOCK);

        Map<Block, Integer> stonecuttingRecipes = Map.of(
                ModBlocks.CONDENSED_QUARTZ_PILLAR, 1,
                ModBlocks.CONDENSED_QUARTZ_BRICKS, 1,
                ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK, 1,
                ModBlocks.CONDENSED_QUARTZ_STAIRS, 1,
                ModBlocks.CONDENSED_QUARTZ_SLAB, 2
        );

        for (Map.Entry<Block, Integer> entry : stonecuttingRecipes.entrySet()) {
            Block output = entry.getKey();
            int count = entry.getValue();

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, output, ModBlocks.CONDENSED_QUARTZ_BLOCK, count);
        }

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BOTTLE_OF_BERRY_JUICE, 1)
                .pattern("SSS")
                .pattern("SGS")
                .pattern("SSS")
                .input('S', Items.SWEET_BERRIES)
                .input('G', Items.GLASS_BOTTLE)
                .criterion(hasItem(Items.SWEET_BERRIES), conditionsFromItem((Items.SWEET_BERRIES)))
                .offerTo(exporter, new Identifier(getRecipeName((ModItems.BOTTLE_OF_BERRY_JUICE))));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_PAINTED_DOOR, 1)
                .pattern("GGG")
                .pattern("GDG")
                .pattern("GGG")
                .input('D', ItemTags.DOORS)
                .input('G', Items.GOLD_NUGGET)
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem((Items.GOLD_NUGGET)))
                .offerTo(exporter, new Identifier(getRecipeName((ModBlocks.GOLD_PAINTED_DOOR))));

    }
}
