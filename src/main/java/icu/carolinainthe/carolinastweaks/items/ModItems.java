package icu.carolinainthe.carolinastweaks.items;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModItems {


    // Registers mod items.
//    public static final Item SACK_OF_COCOA = registerItem("sack_of_cocoa", new Item(new FabricItemSettings()));

    // Register individual item function.
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    // Main function to register all items, called in the main class.
    public static void registerModItems() {
        CarolinasTweaks.LOGGER.info("Registering mod items for " + MOD_ID + ", created by Carolina Mitchell (carolina_slays)");

    }
}