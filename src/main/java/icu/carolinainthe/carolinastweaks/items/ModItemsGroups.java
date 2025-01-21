package icu.carolinainthe.carolinastweaks.items;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModItemsGroups {
    // Add items to the mod item group in the creative mode menu.
    public static final ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MOD_ID,"items"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.items"))
                    .icon(() -> new ItemStack(ModBlocks.CONDENSED_COCOA)).entries((displayContext, entries) -> {
                         entries.add(ModBlocks.BUNDLE_OF_STICKS);
                         entries.add(ModBlocks.CONDENSED_COCOA);
                    }).build());

    // Adds items to the ingredients item group in the creative mode menu.
    private static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.BUNDLE_OF_STICKS);
    }

    // Adds items to the natural blocks item group in the creative mode menu.
    private static void addItemsToNaturalBlocksItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.CONDENSED_COCOA);
    }

    // Registers all mod items into their respective groups, called by the main class.
    public static void registerItemGroups() {
        CarolinasTweaks.LOGGER.info("Registering mod item groups for " + MOD_ID + ", created by Carolina Mitchell (carolina_slays)");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItemsGroups::addItemsToIngredientsItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItemsGroups::addItemsToNaturalBlocksItemGroup);
    }


}