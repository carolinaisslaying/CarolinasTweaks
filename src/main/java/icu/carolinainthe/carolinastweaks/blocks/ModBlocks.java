package icu.carolinainthe.carolinastweaks.blocks;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModBlocks {
    // Registers all mod blocks.
    public static final Block BUNDLE_OF_STICKS = registerBlock("bundle_of_sticks",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO_BLOCK)
                    .strength(0.5f, 0.2f)
            ));

    public static final Block CONDENSED_COCOA = registerBlock("condensed_cocoa",
            new Block(FabricBlockSettings.copyOf(Blocks.COCOA)
                .strength(0.8f, 3.0f)
            ));

    public static final Block GOLD_PAINTED_DOOR = registerBlock("gold_painted_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), BlockSetType.OAK));

    // Register block function.
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, name), block);
    }

    // Register individual block item function.
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    // Main function to register all items, called in the main class.
    public static void registerModBlocks() {
        CarolinasTweaks.LOGGER.info("Registering mod blocks for " + MOD_ID + ", created by Carolina Mitchell (carolina_slays)");
    }
}