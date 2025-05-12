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
                    .icon(() -> new ItemStack(ModItems.BOTTLE_OF_BERRY_JUICE)).entries((displayContext, entries) -> {
                         entries.add(ModBlocks.BUNDLE_OF_STICKS);
                         entries.add(ModBlocks.CONDENSED_COCOA);
                         entries.add(ModBlocks.GOLD_PAINTED_DOOR);
                         entries.add(ModItems.BOTTLE_OF_BERRY_JUICE);
                    }).build());

    // Adds items to the ingredients item group in the creative mode menu.
    private static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.BUNDLE_OF_STICKS);
    }

    // Adds items to the natural blocks item group in the creative mode menu.
    private static void addItemsToNaturalBlocksItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.CONDENSED_COCOA);
    }

    // Add items to the building blocks item group in the creative mode menu.
    private static void addItemsToBuildingBlocksItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.GOLD_PAINTED_DOOR);
    }

    // Add items to the food and drink item group in the creative mode menu.
    private static void addItemsToFoodAndDrinkItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.BOTTLE_OF_BERRY_JUICE);
    }

    // Registers all mod items into their respective groups, called by the main class.
    public static void registerItemGroups() {
        CarolinasTweaks.LOGGER.info("Registering mod item groups for " + MOD_ID +
                ", created by Carolina Mitchell (carolina_slays)");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(ModItemsGroups::addItemsToIngredientsItemGroup);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register(ModItemsGroups::addItemsToNaturalBlocksItemGroup);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(ModItemsGroups::addItemsToBuildingBlocksItemGroup);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(ModItemsGroups::addItemsToFoodAndDrinkItemGroup);
    }
}