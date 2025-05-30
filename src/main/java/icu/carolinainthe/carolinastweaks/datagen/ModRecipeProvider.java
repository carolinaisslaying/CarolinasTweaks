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
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

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
