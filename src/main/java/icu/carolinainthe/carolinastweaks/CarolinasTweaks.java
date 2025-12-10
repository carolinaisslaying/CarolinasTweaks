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

package icu.carolinainthe.carolinastweaks;

import com.mojang.logging.LogUtils;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import icu.carolinainthe.carolinastweaks.items.ModCreativeModTabs;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CarolinasTweaks.MOD_ID)
public class CarolinasTweaks {
    public static final String MOD_ID = "carolinas_tweaks";

    public CarolinasTweaks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
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
            event.accept(ModBlocks.GOLD_PAINTED_DOOR);
            event.accept(ModBlocks.GOLD_PAINTED_DOOR);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BOTTLE_OF_BERRY_JUICE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) { }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) { }
    }
}