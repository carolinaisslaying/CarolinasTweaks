package icu.carolinainthe.carolinastweaks.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class BerryJuiceItem extends Item {
    public BerryJuiceItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack resultStack = super.finishUsing(stack, world, user);
        if (user instanceof PlayerEntity player) {
            if (!player.getAbilities().creativeMode) {
                ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);
                if (!player.getInventory().insertStack(emptyBottle)) {
                    player.dropItem(emptyBottle, false);
                }
            }
        }
        return resultStack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.canConsume(false)) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }
        return TypedActionResult.fail(stack);
    }
}