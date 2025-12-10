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

package icu.carolinainthe.carolinastweaks.datagen.loot;

import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Bundle of sticks drops 9 sticks
        this.add(ModBlocks.BUNDLE_OF_STICKS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(9)))
                        )
                )
        );

        this.dropSelf(ModBlocks.CONDENSED_COCOA.get());
        this.add(ModBlocks.GOLD_PAINTED_DOOR.get(), this.createDoorTable(ModBlocks.GOLD_PAINTED_DOOR.get()));
        this.dropSelf(ModBlocks.BLOCK_OF_GUNPOWDER.get());
        this.dropSelf(ModBlocks.CONDENSED_QUARTZ_BLOCK.get());
        this.dropSelf(ModBlocks.CONDENSED_QUARTZ_BRICKS.get());
        this.dropSelf(ModBlocks.CONDENSED_QUARTZ_PILLAR.get());
        this.dropSelf(ModBlocks.CHISELED_CONDENSED_QUARTZ_BLOCK.get());
        this.dropSelf(ModBlocks.CONDENSED_QUARTZ_STAIRS.get());
        this.add(ModBlocks.CONDENSED_QUARTZ_SLAB.get(), this.createSlabItemTable(ModBlocks.CONDENSED_QUARTZ_SLAB.get()));
        this.dropSelf(ModBlocks.BLOCK_OF_LEATHER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
