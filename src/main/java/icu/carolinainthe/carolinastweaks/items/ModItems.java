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
import icu.carolinainthe.carolinastweaks.items.custom.BerryJuiceItem;
import icu.carolinainthe.carolinastweaks.items.custom.SeedPacketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModItems {
    public static final Map<Item, Item> ITEM_MIGRATION_MAP = new HashMap<>();

    // Registers mod items.
    public static final Item BOTTLE_OF_BERRY_JUICE = registerItem("bottle_of_berry_juice",
            new BerryJuiceItem(new FabricItemSettings().maxCount(16).food(ModFoodComponents.BOTTLE_OF_BERRY_JUICE)));

    public static final Item WHEAT_SEED_PACKET = registerItem("wheat_seed_packet",
            new SeedPacketItem(Items.WHEAT_SEEDS, new FabricItemSettings().maxCount(16)));

    public static final Item MELON_SEED_PACKET = registerItem("melon_seed_packet",
            new SeedPacketItem(Items.MELON_SEEDS, new FabricItemSettings().maxCount(16)));

    public static final Item PUMPKIN_SEED_PACKET = registerItem("pumpkin_seed_packet",
            new SeedPacketItem(Items.PUMPKIN_SEEDS, new FabricItemSettings().maxCount(16)));

    public static final Item BEETROOT_SEED_PACKET = registerItem("beetroot_seed_packet",
            new SeedPacketItem(Items.BEETROOT_SEEDS, new FabricItemSettings().maxCount(16)));

    // Register individual item function.
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    // Function to add compostable items to the compostable items registry.
    public static void registerCompostableItems() {
        CompostingChanceRegistry.INSTANCE.add(Items.POISONOUS_POTATO, 0.45f);
    }

    // Migration to new MOD_ID helper method
    private static void registerGhostItem(String oldPath, Item realItem) {
        Item dummyItem = new Item(new Item.Settings());
        Registry.register(Registries.ITEM, new Identifier("carolinas-tweaks", oldPath), dummyItem);

        // 2. Save the link so MigrationHelper can fix the inventory
        ITEM_MIGRATION_MAP.put(dummyItem, realItem);
    }

    // Main function to register all items, called in the main class.
    public static void registerModItems() {
        CarolinasTweaks.LOGGER.info("Registering mod items for " + MOD_ID + ", created by Carolina Mitchell (carolina_slays)");
        registerCompostableItems();

        // Register ghost items
        registerGhostItem("bottle_of_berry_juice", BOTTLE_OF_BERRY_JUICE);
        registerGhostItem("wheat_seed_packet", WHEAT_SEED_PACKET);
        registerGhostItem("melon_seed_packet", MELON_SEED_PACKET);
        registerGhostItem("pumpkin_seed_packet", PUMPKIN_SEED_PACKET);
        registerGhostItem("beetroot_seed_packet", BEETROOT_SEED_PACKET);
    }
}