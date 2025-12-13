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
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Reversible compacting recipes (9 items <-> 1 block)

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.STICK,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BUNDLE_OF_STICKS.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.COCOA_BEANS,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_COCOA.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.GUNPOWDER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOCK_OF_GUNPOWDER.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.QUARTZ,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BLOCK.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.LEATHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOCK_OF_LEATHER.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.WHEAT_SEEDS,
                RecipeCategory.MISC, ModItems.WHEAT_SEED_PACKET.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.MELON_SEEDS,
                RecipeCategory.MISC, ModItems.MELON_SEED_PACKET.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.PUMPKIN_SEEDS,
                RecipeCategory.MISC, ModItems.PUMPKIN_SEED_PACKET.get());

        reversibleCompacting(consumer, RecipeCategory.MISC, Items.BEETROOT_SEEDS,
                RecipeCategory.MISC, ModItems.BEETROOT_SEED_PACKET.get());

        // Quartz from block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.QUARTZ, 4)
                .requires(Blocks.QUARTZ_BLOCK)
                .unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID, "quartz_from_block"));

        // Condensed quartz pillar
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_PILLAR.get(), 2)
                .pattern("C")
                .pattern("C")
                .define('C', ModBlocks.CONDENSED_QUARTZ_BLOCK.get())
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer);

        // Condensed quartz bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BRICKS.get(), 4)
                .pattern("CC")
                .pattern("CC")
                .define('C', ModBlocks.CONDENSED_QUARTZ_BLOCK.get())
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer);

        // Bell recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.BELL, 1)
                .pattern("SWS")
                .pattern("SGS")
                .pattern("SNS")
                .define('S', Blocks.STONE)
                .define('W', ItemTags.WOODEN_SLABS)
                .define('N', Blocks.NOTE_BLOCK)
                .define('G', Blocks.GOLD_BLOCK)
                .unlockedBy("has_gold_block", has(Blocks.GOLD_BLOCK))
                .unlockedBy("has_note_block", has(Blocks.NOTE_BLOCK))
                .save(consumer, resLoc(Blocks.BELL));

        // Stairs
        stairBuilder(ModBlocks.CONDENSED_QUARTZ_STAIRS.get(), Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer);

        // Slab
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_SLAB.get(),
                ModBlocks.CONDENSED_QUARTZ_BLOCK.get());

        // Stonecutting recipes
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_PILLAR.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID,
                        "condensed_quartz_pillar_from_condensed_quartz_block_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BRICKS.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID,
                        "condensed_quartz_bricks_from_condensed_quartz_block_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID,
                        "chiseled_condensed_quartz_block_from_condensed_quartz_block_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_STAIRS.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID,
                        "condensed_quartz_stairs_from_condensed_quartz_block_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_SLAB.get(), 2)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID,
                        "condensed_quartz_slab_from_condensed_quartz_block_stonecutting"));

        // Berry juice
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BOTTLE_OF_BERRY_JUICE.get(), 1)
                .pattern("SSS")
                .pattern("SGS")
                .pattern("SSS")
                .define('S', Items.SWEET_BERRIES)
                .define('G', Items.GLASS_BOTTLE)
                .unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES))
                .save(consumer);

        // Gold painted door
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_PAINTED_DOOR.get(), 1)
                .pattern("GGG")
                .pattern("GDG")
                .pattern("GGG")
                .define('D', ItemTags.DOORS)
                .define('G', Items.GOLD_NUGGET)
                .unlockedBy("has_gold_nugget", has(Items.GOLD_NUGGET))
                .save(consumer);
    }

    public static void reversibleCompacting(
            Consumer<FinishedRecipe> consumer,
            RecipeCategory smallCategory,
            ItemLike small,
            RecipeCategory bigCategory,
            ItemLike big
    ) {
        String bigName = ForgeRegistries.ITEMS.getKey(big.asItem()).getPath();
        String smallName = ForgeRegistries.ITEMS.getKey(small.asItem()).getPath();

        ShapedRecipeBuilder.shaped(bigCategory, big)
                .define('#', small)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_small", has(small))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID, bigName));

        ShapelessRecipeBuilder.shapeless(smallCategory, small, 9)
                .requires(big)
                .unlockedBy("has_big", has(big))
                .save(consumer, new ResourceLocation(CarolinasTweaks.MOD_ID, smallName));
    }

    private static ResourceLocation resLoc(ItemLike item) {
        return new ResourceLocation(CarolinasTweaks.MOD_ID,
                ForgeRegistries.ITEMS.getKey(item.asItem()).getPath());
    }
}
