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

package icu.carolinainthe.carolinastweaks.items;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.items.custom.BerryJuiceItem;
import icu.carolinainthe.carolinastweaks.items.custom.SeedPacketItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static void register(IEventBus eventBus) {
        CarolinasTweaks.LOGGER.info("Registering mod items for "
                + MOD_ID + ", created by Carolina Mitchell (carolina_slaying)");

        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> BOTTLE_OF_BERRY_JUICE = ITEMS.register("bottle_of_berry_juice",
            () -> new BerryJuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.6f)
                            .build())
                    .stacksTo(16)));

    public static final RegistryObject<Item> WHEAT_SEED_PACKET = ITEMS.register("wheat_seed_packet",
            () -> new SeedPacketItem(Items.WHEAT_SEEDS, new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> MELON_SEED_PACKET = ITEMS.register("melon_seed_packet",
            () -> new SeedPacketItem(Items.MELON_SEEDS, new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PUMPKIN_SEED_PACKET = ITEMS.register("pumpkin_seed_packet",
            () -> new SeedPacketItem(Items.PUMPKIN_SEEDS, new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BEETROOT_SEED_PACKET = ITEMS.register("beetroot_seed_packet",
            () -> new SeedPacketItem(Items.BEETROOT_SEEDS, new Item.Properties().stacksTo(16)));
}
