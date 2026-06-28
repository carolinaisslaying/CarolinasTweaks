package icu.carolinainthe.carolinastweaks.items;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.items.custom.BerryJuiceItem;
import icu.carolinainthe.carolinastweaks.items.custom.SeedPacketItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MOD_ID);

    public static void register(IEventBus eventBus) {
        CarolinasTweaks.LOGGER.info("Registering mod items for "
                + MOD_ID + ", created by Carolina Mitchell (carolina_slaying)");

        ITEMS.register(eventBus);
    }

    public static final DeferredItem<Item> BOTTLE_OF_BERRY_JUICE = ITEMS.register("bottle_of_berry_juice",
            () -> new BerryJuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.6f)
                            .build())
                    .stacksTo(16)));

    public static final DeferredItem<Item> WHEAT_SEED_PACKET = ITEMS.register("wheat_seed_packet",
            () -> new SeedPacketItem(Items.WHEAT_SEEDS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> MELON_SEED_PACKET = ITEMS.register("melon_seed_packet",
            () -> new SeedPacketItem(Items.MELON_SEEDS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> PUMPKIN_SEED_PACKET = ITEMS.register("pumpkin_seed_packet",
            () -> new SeedPacketItem(Items.PUMPKIN_SEEDS, new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> BEETROOT_SEED_PACKET = ITEMS.register("beetroot_seed_packet",
            () -> new SeedPacketItem(Items.BEETROOT_SEEDS, new Item.Properties().stacksTo(16)));

}
