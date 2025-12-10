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
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CarolinasTweaks.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CAROLINAS_TWEAKS_TAB =
            CREATIVE_MODE_TABS.register("carolinas_tweaks_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.BOTTLE_OF_BERRY_JUICE.get()))
                            .title(Component.translatable("itemGroup.items"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModBlocks.BUNDLE_OF_STICKS.get());
                                pOutput.accept(ModBlocks.CONDENSED_COCOA.get());
                                pOutput.accept(ModBlocks.GOLD_PAINTED_DOOR.get());
                                pOutput.accept(ModBlocks.BLOCK_OF_GUNPOWDER.get());
                                pOutput.accept(ModBlocks.CONDENSED_QUARTZ_BLOCK.get());
                                pOutput.accept(ModBlocks.CONDENSED_QUARTZ_PILLAR.get());
                                pOutput.accept(ModBlocks.CONDENSED_QUARTZ_BRICKS.get());
                                pOutput.accept(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get());
                                pOutput.accept(ModBlocks.CONDENSED_QUARTZ_STAIRS.get());
                                pOutput.accept(ModBlocks.CONDENSED_QUARTZ_SLAB.get());
                                pOutput.accept(ModBlocks.BLOCK_OF_LEATHER.get());

                                pOutput.accept(ModItems.BOTTLE_OF_BERRY_JUICE.get());
                                pOutput.accept(ModItems.WHEAT_SEED_PACKET.get());
                                pOutput.accept(ModItems.MELON_SEED_PACKET.get());
                                pOutput.accept(ModItems.PUMPKIN_SEED_PACKET.get());
                                pOutput.accept(ModItems.BEETROOT_SEED_PACKET.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
