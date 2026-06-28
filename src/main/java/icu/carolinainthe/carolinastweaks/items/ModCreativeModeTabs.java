package icu.carolinainthe.carolinastweaks.items;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static icu.carolinainthe.carolinastweaks.CarolinasTweaks.MOD_ID;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Supplier<CreativeModeTab> CAROLINAS_TWEAKS_TAB =
            CREATIVE_MODE_TABS.register("carolinas_tweaks_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.BOTTLE_OF_BERRY_JUICE.get()))
                            .title(Component.translatable("itemGroup.items"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModBlocks.BUNDLE_OF_STICKS.get());
                                output.accept(ModBlocks.CONDENSED_COCOA.get());
                                output.accept(ModBlocks.GOLD_PAINTED_DOOR.get());
                                output.accept(ModBlocks.BLOCK_OF_GUNPOWDER.get());
                                output.accept(ModBlocks.CONDENSED_QUARTZ_BLOCK.get());
                                output.accept(ModBlocks.CONDENSED_QUARTZ_PILLAR.get());
                                output.accept(ModBlocks.CONDENSED_QUARTZ_BRICKS.get());
                                output.accept(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get());
                                output.accept(ModBlocks.CONDENSED_QUARTZ_STAIRS.get());
                                output.accept(ModBlocks.CONDENSED_QUARTZ_SLAB.get());
                                output.accept(ModBlocks.BLOCK_OF_LEATHER.get());

                                output.accept(ModItems.BOTTLE_OF_BERRY_JUICE.get());
                                output.accept(ModItems.WHEAT_SEED_PACKET.get());
                                output.accept(ModItems.MELON_SEED_PACKET.get());
                                output.accept(ModItems.PUMPKIN_SEED_PACKET.get());
                                output.accept(ModItems.BEETROOT_SEED_PACKET.get());
                            }).build());

    public static void register(IEventBus eventBus) {
        CarolinasTweaks.LOGGER.info("Registering creative tab for "
                + MOD_ID + ", created by Carolina Mitchell (carolina_slaying)");

        CREATIVE_MODE_TABS.register(eventBus);
    }
}
