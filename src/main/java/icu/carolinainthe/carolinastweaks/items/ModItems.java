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
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModItems {

    // Registers mod items.
    public static final Item BOTTLE_OF_BERRY_JUICE = registerItem("bottle_of_berry_juice",
            new BerryJuiceItem(new FabricItemSettings().maxCount(16).food(ModFoodComponents.BOTTLE_OF_BERRY_JUICE)));

    // Register individual item function.
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    // Main function to register all items, called in the main class.
    public static void registerModItems() {
        CarolinasTweaks.LOGGER.info("Registering mod items for " + MOD_ID + ", created by Carolina Mitchell (carolina_slays)");
    }
}