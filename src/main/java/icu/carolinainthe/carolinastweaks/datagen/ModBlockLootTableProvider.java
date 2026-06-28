package icu.carolinainthe.carolinastweaks.datagen;

import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // Bundle of sticks drops 9 sticks
        add(ModBlocks.BUNDLE_OF_STICKS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(9)))
                        )
                )
        );

        dropSelf(ModBlocks.CONDENSED_COCOA.get());

        add(ModBlocks.GOLD_PAINTED_DOOR.get(),
                block -> createDoorTable(ModBlocks.GOLD_PAINTED_DOOR.get()));

        dropSelf(ModBlocks.BLOCK_OF_GUNPOWDER.get());
        dropSelf(ModBlocks.CONDENSED_QUARTZ_BLOCK.get());
        dropSelf(ModBlocks.CONDENSED_QUARTZ_BRICKS.get());
        dropSelf(ModBlocks.CONDENSED_QUARTZ_PILLAR.get());
        dropSelf(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get());
        dropSelf(ModBlocks.CONDENSED_QUARTZ_STAIRS.get());

        add(ModBlocks.CONDENSED_QUARTZ_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CONDENSED_QUARTZ_SLAB.get()));

        dropSelf(ModBlocks.BLOCK_OF_LEATHER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
