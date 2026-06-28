package icu.carolinainthe.carolinastweaks.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.api.data.recipe.DatagenMod;
import com.simibubi.create.api.data.recipe.StandardProcessingRecipeGen;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.HauntingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class CreateRecipeProvider {

    public static void register(DataGenerator generator, PackOutput output,
                                CompletableFuture<HolderLookup.Provider> lookup, boolean server) {
        generator.addProvider(server, new Crushing(output, lookup));
        generator.addProvider(server, new Milling(output, lookup));
        generator.addProvider(server, new Haunting(output, lookup));
        generator.addProvider(server, new Mixing(output, lookup));
        generator.addProvider(server, new Filling(output, lookup));
        generator.addProvider(server, new RecipeDisabler(output));
    }

    private static class RecipeDisabler implements DataProvider {
        private final PackOutput output;

        RecipeDisabler(PackOutput output) { this.output = output; }

        @Override
        public CompletableFuture<?> run(CachedOutput cache) {
            JsonObject json = new JsonObject();
            JsonArray conditions = new JsonArray();
            for (String modid : new String[]{"ad_astra", "tfmg"}) {
                JsonObject cond = new JsonObject();
                cond.addProperty("type", "neoforge:mod_loaded");
                cond.addProperty("modid", modid);
                conditions.add(cond);
            }
            json.add("neoforge:conditions", conditions);

            Path path = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                    .resolve("tfmg/recipe/sequenced_assembly/heavy_plate.json");
            return DataProvider.saveStable(cache, json, path);
        }

        @Override
        public String getName() { return "Carolina's Tweaks Recipe Disabler"; }
    }

    private static final DatagenMod CREATE   = () -> "create";
    private static final DatagenMod AD_ASTRA = () -> "ad_astra";
    private static final DatagenMod TFMG     = () -> "tfmg";

    private static class Crushing extends StandardProcessingRecipeGen<CrushingRecipe> {
        GeneratedRecipe NETHER_BRICKS = create("nether_bricks", b -> b
                .require(Blocks.NETHER_BRICKS)
                .output(Items.NETHER_BRICK, 2)
                .output(0.25f, AllItems.CINDER_FLOUR.get()));

        Crushing(PackOutput o, CompletableFuture<HolderLookup.Provider> r) {
            super(o, r, CarolinasTweaks.MOD_ID);
        }

        @Override protected IRecipeTypeInfo getRecipeType() { return AllRecipeTypes.CRUSHING; }
    }

    private static class Milling extends StandardProcessingRecipeGen<MillingRecipe> {
        GeneratedRecipe SOUL_SOIL = create("soul_soil", b -> b
                .require(Blocks.SOUL_SOIL)
                .duration(150)
                .output(Blocks.SOUL_SAND)
                .output(0.25f, Items.GUNPOWDER));

        Milling(PackOutput o, CompletableFuture<HolderLookup.Provider> r) {
            super(o, r, CarolinasTweaks.MOD_ID);
        }

        @Override protected IRecipeTypeInfo getRecipeType() { return AllRecipeTypes.MILLING; }
    }

    private static class Haunting extends StandardProcessingRecipeGen<HauntingRecipe> {
        GeneratedRecipe SOUL_SAND = create("soul_sand", b -> b
                .require(Blocks.SOUL_SAND)
                .output(0.125f, Items.QUARTZ, 3)
                .output(0.125f, Items.GOLD_NUGGET));

        Haunting(PackOutput o, CompletableFuture<HolderLookup.Provider> r) {
            super(o, r, CarolinasTweaks.MOD_ID);
        }

        @Override protected IRecipeTypeInfo getRecipeType() { return AllRecipeTypes.HAUNTING; }
    }

    private static class Mixing extends StandardProcessingRecipeGen<MixingRecipe> {
        GeneratedRecipe BOTTLE_OF_BERRY_JUICE = create("bottle_of_berry_juice", b -> b
                .require(Items.SWEET_BERRIES).require(Items.SWEET_BERRIES)
                .require(Items.SWEET_BERRIES).require(Items.SWEET_BERRIES)
                .require(Items.GLASS_BOTTLE)
                .output(ModItems.BOTTLE_OF_BERRY_JUICE.get()));

        GeneratedRecipe GOLDEN_CARROT = create("golden_carrot", b -> b
                .require(Items.CARROT)
                .require(Items.GOLD_NUGGET).require(Items.GOLD_NUGGET)
                .require(Items.GOLD_NUGGET).require(Items.GOLD_NUGGET)
                .require(Items.GOLD_NUGGET).require(Items.GOLD_NUGGET)
                .output(Items.GOLDEN_CARROT));

        GeneratedRecipe REDSTONE = create("redstone", b -> b
                .requiresHeat(HeatCondition.SUPERHEATED)
                .require(Items.QUARTZ).require(Items.GLOWSTONE_DUST)
                .require(AllItems.CINDER_FLOUR.get())
                .require((FlowingFluid) Fluids.LAVA, 1000)
                .output(Items.REDSTONE, 2));

        GeneratedRecipe AD_ASTRA_STEEL_INGOT = create("ad_astra_steel_ingot", b -> b
                .requiresHeat(HeatCondition.SUPERHEATED)
                .require(Items.IRON_INGOT).require(ItemTags.COALS)
                .output(AD_ASTRA, "steel_ingot")
                .whenModLoaded("ad_astra"));

        Mixing(PackOutput o, CompletableFuture<HolderLookup.Provider> r) {
            super(o, r, CarolinasTweaks.MOD_ID);
            BuiltInRegistries.FLUID.getOptional(
                    ResourceLocation.fromNamespaceAndPath("tfmg", "liquid_concrete"))
                .filter(f -> f instanceof FlowingFluid)
                .map(f -> (FlowingFluid) f)
                .ifPresent(liquidConcrete -> {
                    create("tfmg_cinder_block", b -> b
                            .requiresHeat(HeatCondition.SUPERHEATED)
                            .require(TFMG, "cinderblock").require(TFMG, "cinderblock")
                            .require(TFMG, "cinderblock").require(TFMG, "cinderblock")
                            .require(TFMG, "cinderblock").require(TFMG, "cinderblock")
                            .require(TFMG, "rebar").require(TFMG, "rebar")
                            .require(liquidConcrete, 1000)
                            .output(1.0f, TFMG, "cinder_block", 8)
                            .whenModLoaded("tfmg"));
                    create("tfmg_concrete", b -> b
                            .requiresHeat(HeatCondition.SUPERHEATED)
                            .require(liquidConcrete, 1000)
                            .output(TFMG, "concrete")
                            .whenModLoaded("tfmg"));
                });
        }

        @Override protected IRecipeTypeInfo getRecipeType() { return AllRecipeTypes.MIXING; }
    }

    private static class Filling extends StandardProcessingRecipeGen<FillingRecipe> {
        // Vanilla copper block variants
        GeneratedRecipe EXPOSED_COPPER          = water(Blocks.COPPER_BLOCK,          Blocks.EXPOSED_COPPER);
        GeneratedRecipe WEATHERED_COPPER        = water(Blocks.EXPOSED_COPPER,        Blocks.WEATHERED_COPPER);
        GeneratedRecipe OXIDIZED_COPPER         = water(Blocks.WEATHERED_COPPER,      Blocks.OXIDIZED_COPPER);

        // Vanilla chiseled copper
        GeneratedRecipe EXPOSED_CHISELED_COPPER   = water(Blocks.CHISELED_COPPER,          Blocks.EXPOSED_CHISELED_COPPER);
        GeneratedRecipe WEATHERED_CHISELED_COPPER = water(Blocks.EXPOSED_CHISELED_COPPER,  Blocks.WEATHERED_CHISELED_COPPER);
        GeneratedRecipe OXIDIZED_CHISELED_COPPER  = water(Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER);

        // Vanilla copper bulbs
        GeneratedRecipe EXPOSED_COPPER_BULB    = water(Blocks.COPPER_BULB,           Blocks.EXPOSED_COPPER_BULB);
        GeneratedRecipe WEATHERED_COPPER_BULB  = water(Blocks.EXPOSED_COPPER_BULB,   Blocks.WEATHERED_COPPER_BULB);
        GeneratedRecipe OXIDIZED_COPPER_BULB   = water(Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB);

        // Vanilla copper doors
        GeneratedRecipe EXPOSED_COPPER_DOOR    = water(Blocks.COPPER_DOOR,           Blocks.EXPOSED_COPPER_DOOR);
        GeneratedRecipe WEATHERED_COPPER_DOOR  = water(Blocks.EXPOSED_COPPER_DOOR,   Blocks.WEATHERED_COPPER_DOOR);
        GeneratedRecipe OXIDIZED_COPPER_DOOR   = water(Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR);

        // Vanilla copper grates
        GeneratedRecipe EXPOSED_COPPER_GRATE   = water(Blocks.COPPER_GRATE,           Blocks.EXPOSED_COPPER_GRATE);
        GeneratedRecipe WEATHERED_COPPER_GRATE = water(Blocks.EXPOSED_COPPER_GRATE,   Blocks.WEATHERED_COPPER_GRATE);
        GeneratedRecipe OXIDIZED_COPPER_GRATE  = water(Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE);

        // Vanilla copper trapdoors
        GeneratedRecipe EXPOSED_COPPER_TRAPDOOR   = water(Blocks.COPPER_TRAPDOOR,           Blocks.EXPOSED_COPPER_TRAPDOOR);
        GeneratedRecipe WEATHERED_COPPER_TRAPDOOR = water(Blocks.EXPOSED_COPPER_TRAPDOOR,   Blocks.WEATHERED_COPPER_TRAPDOOR);
        GeneratedRecipe OXIDIZED_COPPER_TRAPDOOR  = water(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR);

        // Vanilla cut copper
        GeneratedRecipe EXPOSED_CUT_COPPER   = water(Blocks.CUT_COPPER,          Blocks.EXPOSED_CUT_COPPER);
        GeneratedRecipe WEATHERED_CUT_COPPER = water(Blocks.EXPOSED_CUT_COPPER,  Blocks.WEATHERED_CUT_COPPER);
        GeneratedRecipe OXIDIZED_CUT_COPPER  = water(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER);

        // Vanilla cut copper slabs
        GeneratedRecipe EXPOSED_CUT_COPPER_SLAB   = water(Blocks.CUT_COPPER_SLAB,          Blocks.EXPOSED_CUT_COPPER_SLAB);
        GeneratedRecipe WEATHERED_CUT_COPPER_SLAB = water(Blocks.EXPOSED_CUT_COPPER_SLAB,  Blocks.WEATHERED_CUT_COPPER_SLAB);
        GeneratedRecipe OXIDIZED_CUT_COPPER_SLAB  = water(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB);

        // Vanilla cut copper stairs
        GeneratedRecipe EXPOSED_CUT_COPPER_STAIRS   = water(Blocks.CUT_COPPER_STAIRS,          Blocks.EXPOSED_CUT_COPPER_STAIRS);
        GeneratedRecipe WEATHERED_CUT_COPPER_STAIRS = water(Blocks.EXPOSED_CUT_COPPER_STAIRS,  Blocks.WEATHERED_CUT_COPPER_STAIRS);
        GeneratedRecipe OXIDIZED_CUT_COPPER_STAIRS  = water(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS);

        // Create copper shingles
        GeneratedRecipe EXPOSED_COPPER_SHINGLES   = water(CREATE, "copper_shingles",          CREATE, "exposed_copper_shingles");
        GeneratedRecipe WEATHERED_COPPER_SHINGLES = water(CREATE, "exposed_copper_shingles",  CREATE, "weathered_copper_shingles");
        GeneratedRecipe OXIDIZED_COPPER_SHINGLES  = water(CREATE, "weathered_copper_shingles", CREATE, "oxidized_copper_shingles");

        // Create copper shingle slabs
        GeneratedRecipe EXPOSED_COPPER_SHINGLE_SLAB   = water(CREATE, "copper_shingle_slab",          CREATE, "exposed_copper_shingle_slab");
        GeneratedRecipe WEATHERED_COPPER_SHINGLE_SLAB = water(CREATE, "exposed_copper_shingle_slab",  CREATE, "weathered_copper_shingle_slab");
        GeneratedRecipe OXIDIZED_COPPER_SHINGLE_SLAB  = water(CREATE, "weathered_copper_shingle_slab", CREATE, "oxidized_copper_shingle_slab");

        // Create copper shingle stairs
        GeneratedRecipe EXPOSED_COPPER_SHINGLE_STAIRS   = water(CREATE, "copper_shingle_stairs",          CREATE, "exposed_copper_shingle_stairs");
        GeneratedRecipe WEATHERED_COPPER_SHINGLE_STAIRS = water(CREATE, "exposed_copper_shingle_stairs",  CREATE, "weathered_copper_shingle_stairs");
        GeneratedRecipe OXIDIZED_COPPER_SHINGLE_STAIRS  = water(CREATE, "weathered_copper_shingle_stairs", CREATE, "oxidized_copper_shingle_stairs");

        // Create copper tiles
        GeneratedRecipe EXPOSED_COPPER_TILES   = water(CREATE, "copper_tiles",          CREATE, "exposed_copper_tiles");
        GeneratedRecipe WEATHERED_COPPER_TILES = water(CREATE, "exposed_copper_tiles",  CREATE, "weathered_copper_tiles");
        GeneratedRecipe OXIDIZED_COPPER_TILES  = water(CREATE, "weathered_copper_tiles", CREATE, "oxidized_copper_tiles");

        // Create copper tile slabs
        GeneratedRecipe EXPOSED_COPPER_TILE_SLAB   = water(CREATE, "copper_tile_slab",          CREATE, "exposed_copper_tile_slab");
        GeneratedRecipe WEATHERED_COPPER_TILE_SLAB = water(CREATE, "exposed_copper_tile_slab",  CREATE, "weathered_copper_tile_slab");
        GeneratedRecipe OXIDIZED_COPPER_TILE_SLAB  = water(CREATE, "weathered_copper_tile_slab", CREATE, "oxidized_copper_tile_slab");

        // Create copper tile stairs
        GeneratedRecipe EXPOSED_COPPER_TILE_STAIRS   = water(CREATE, "copper_tile_stairs",          CREATE, "exposed_copper_tile_stairs");
        GeneratedRecipe WEATHERED_COPPER_TILE_STAIRS = water(CREATE, "exposed_copper_tile_stairs",  CREATE, "weathered_copper_tile_stairs");
        GeneratedRecipe OXIDIZED_COPPER_TILE_STAIRS  = water(CREATE, "weathered_copper_tile_stairs", CREATE, "oxidized_copper_tile_stairs");

        private GeneratedRecipe water(net.minecraft.world.level.ItemLike in, net.minecraft.world.level.ItemLike out) {
            String id = BuiltInRegistries.ITEM.getKey(out.asItem()).getPath();
            return create(id, b -> b.require(in).require(Fluids.WATER, 500).output(out));
        }

        private GeneratedRecipe water(DatagenMod inMod, String inPath, DatagenMod outMod, String outPath) {
            return create(outPath, b -> b
                    .require(inMod, inPath)
                    .require((FlowingFluid) Fluids.WATER, 500)
                    .output(outMod, outPath));
        }

        Filling(PackOutput o, CompletableFuture<HolderLookup.Provider> r) {
            super(o, r, CarolinasTweaks.MOD_ID);
        }

        @Override protected IRecipeTypeInfo getRecipeType() { return AllRecipeTypes.FILLING; }
    }
}
