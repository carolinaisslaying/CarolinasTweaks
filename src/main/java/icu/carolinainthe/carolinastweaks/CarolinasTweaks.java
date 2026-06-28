package icu.carolinainthe.carolinastweaks;

import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import icu.carolinainthe.carolinastweaks.items.ModCreativeModeTabs;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CarolinasTweaks.MOD_ID)
public class CarolinasTweaks {
    public static final String MOD_ID = "carolinas_tweaks";

    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CarolinasTweaks(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        CarolinasTweaks.LOGGER.info("Adding items to creative tabs for "
                + MOD_ID + ", created by Carolina Mitchell (carolina_slaying)");

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.WHEAT_SEED_PACKET);
            event.accept(ModItems.MELON_SEED_PACKET);
            event.accept(ModItems.PUMPKIN_SEED_PACKET);
            event.accept(ModItems.BEETROOT_SEED_PACKET);
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.GOLD_PAINTED_DOOR);
            event.accept(ModBlocks.BUNDLE_OF_STICKS);
            event.accept(ModBlocks.BLOCK_OF_GUNPOWDER);
            event.accept(ModBlocks.BLOCK_OF_LEATHER);
            event.accept(ModBlocks.CONDENSED_COCOA);
            event.accept(ModBlocks.CONDENSED_QUARTZ_BLOCK);
            event.accept(ModBlocks.CONDENSED_QUARTZ_BRICKS);
            event.accept(ModBlocks.CONDENSED_QUARTZ_PILLAR);
            event.accept(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK);
            event.accept(ModBlocks.CONDENSED_QUARTZ_STAIRS);
            event.accept(ModBlocks.CONDENSED_QUARTZ_SLAB);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BOTTLE_OF_BERRY_JUICE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Loading " + MOD_ID + ", created by Carolina Mitchell (carolina_slaying)");
    }
}
