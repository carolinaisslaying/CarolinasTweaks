package icu.carolinainthe.carolinastweaks.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SeedPacketItem extends Item {
    private final Item seedType;

    public SeedPacketItem(Item seedType, Settings settings) {
        super(settings);
        this.seedType = seedType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack heldStack = player.getStackInHand(hand);

        if (!world.isClient) {
            ItemStack seedsToGive = new ItemStack(this.seedType, 9);

            if (!player.getInventory().insertStack(seedsToGive)) {
                player.dropItem(seedsToGive, false);
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));

            if (!player.getAbilities().creativeMode) {
                heldStack.decrement(1);
            }
        }

        return TypedActionResult.success(heldStack, world.isClient());
    }
}
