package icu.carolinainthe.carolinastweaks.datagen;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Reversible compacting recipes (9 items <-> 1 block)

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.STICK,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BUNDLE_OF_STICKS.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.COCOA_BEANS,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_COCOA.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.GUNPOWDER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOCK_OF_GUNPOWDER.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.QUARTZ,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BLOCK.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.LEATHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOCK_OF_LEATHER.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.WHEAT_SEEDS,
                RecipeCategory.MISC, ModItems.WHEAT_SEED_PACKET.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.MELON_SEEDS,
                RecipeCategory.MISC, ModItems.MELON_SEED_PACKET.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.PUMPKIN_SEEDS,
                RecipeCategory.MISC, ModItems.PUMPKIN_SEED_PACKET.get());

        reversibleCompacting(recipeOutput, RecipeCategory.MISC, Items.BEETROOT_SEEDS,
                RecipeCategory.MISC, ModItems.BEETROOT_SEED_PACKET.get());

        // Quartz from block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.QUARTZ, 4)
                .requires(Blocks.QUARTZ_BLOCK)
                .unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK))
                .save(recipeOutput, "carolinas_tweaks:quartz_from_block");

        // Condensed quartz pillar
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_PILLAR.get(), 2)
                .pattern("C")
                .pattern("C")
                .define('C', ModBlocks.CONDENSED_QUARTZ_BLOCK.get())
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput);

        // Condensed quartz bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BRICKS.get(), 4)
                .pattern("CC")
                .pattern("CC")
                .define('C', ModBlocks.CONDENSED_QUARTZ_BLOCK.get())
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput);

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
                .save(recipeOutput);

        // Stairs
        stairBuilder(ModBlocks.CONDENSED_QUARTZ_STAIRS.get(), Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput);

        // Slab
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_SLAB.get(),
                ModBlocks.CONDENSED_QUARTZ_BLOCK.get());

        // Stonecutting recipes
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_PILLAR.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput, "carolinas_tweaks:condensed_quartz_pillar_from_condensed_quartz_block_stonecutting");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_BRICKS.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput, "carolinas_tweaks:condensed_quartz_bricks_from_condensed_quartz_block_stonecutting");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput, "carolinas_tweaks:chiseled_condensed_quartz_block_from_condensed_quartz_block_stonecutting");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_STAIRS.get(), 1)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput, "carolinas_tweaks:condensed_quartz_stairs_from_condensed_quartz_block_stonecutting");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CONDENSED_QUARTZ_SLAB.get(), 2)
                .unlockedBy("has_condensed_quartz_block", has(ModBlocks.CONDENSED_QUARTZ_BLOCK.get()))
                .save(recipeOutput, "carolinas_tweaks:condensed_quartz_slab_from_condensed_quartz_block_stonecutting");

        // Berry juice
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BOTTLE_OF_BERRY_JUICE.get(), 1)
                .pattern("SSS")
                .pattern("SGS")
                .pattern("SSS")
                .define('S', Items.SWEET_BERRIES)
                .define('G', Items.GLASS_BOTTLE)
                .unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES))
                .save(recipeOutput);

        // Gold painted door
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_PAINTED_DOOR.get(), 1)
                .pattern("GGG")
                .pattern("GDG")
                .pattern("GGG")
                .define('D', ItemTags.DOORS)
                .define('G', Items.GOLD_NUGGET)
                .unlockedBy("has_gold_nugget", has(Items.GOLD_NUGGET))
                .save(recipeOutput);
    }

    public static void reversibleCompacting(
            RecipeOutput recipeOutput,
            RecipeCategory smallCategory,
            ItemLike small,
            RecipeCategory bigCategory,
            ItemLike big
    ) {
        String bigName = BuiltInRegistries.ITEM.getKey(big.asItem()).getPath();
        String smallName = BuiltInRegistries.ITEM.getKey(small.asItem()).getPath();

        ShapedRecipeBuilder.shaped(bigCategory, big)
                .define('#', small)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_small", has(small))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(CarolinasTweaks.MOD_ID, bigName));

        ShapelessRecipeBuilder.shapeless(smallCategory, small, 9)
                .requires(big)
                .unlockedBy("has_big", has(big))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(CarolinasTweaks.MOD_ID, smallName));
    }
}
