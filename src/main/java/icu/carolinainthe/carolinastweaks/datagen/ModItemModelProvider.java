package icu.carolinainthe.carolinastweaks.datagen;

import icu.carolinainthe.carolinastweaks.CarolinasTweaks;
import icu.carolinainthe.carolinastweaks.blocks.ModBlocks;
import icu.carolinainthe.carolinastweaks.items.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CarolinasTweaks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BOTTLE_OF_BERRY_JUICE.get());

        basicItem(ModItems.WHEAT_SEED_PACKET.get());
        basicItem(ModItems.MELON_SEED_PACKET.get());
        basicItem(ModItems.PUMPKIN_SEED_PACKET.get());
        basicItem(ModItems.BEETROOT_SEED_PACKET.get());

        basicItem(ModBlocks.GOLD_PAINTED_DOOR.asItem());
    }
}
