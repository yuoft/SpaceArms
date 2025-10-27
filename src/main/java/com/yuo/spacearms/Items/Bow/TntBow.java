package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.SATabs;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;

import javax.annotation.Nullable;
import java.util.List;

public class TntBow extends BowItem {

    public TntBow() {
        super(new Properties().durability(389));
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("spacearms.text.itemInfo.tnt_bow"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        boolean flag = !findAmmo(itemstack, playerIn).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.isCreative() && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            playerIn.swing(handIn);
//            playerIn.interact(playerIn, handIn);
            return InteractionResultHolder.consume(itemstack);
        }
    }


    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player playerentity) {
            boolean flag = playerentity.getAbilities().instabuild || stack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0;
            ItemStack itemstack = findAmmo(stack, playerentity);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.TNT);
                }

                double f = getPowerForTime(i) + 0.5d;
                if (f < 0.6d) return;
                if (!worldIn.isClientSide) {
                    PrimedTnt tntEntity = new PrimedTnt(worldIn, playerentity.getX(), playerentity.getY() + 0.5, playerentity.getZ(), playerentity);
                    int power = stack.getEnchantmentLevel(Enchantments.POWER_ARROWS);
                    tntEntity.setDeltaMovement(playerentity.getLookAngle().scale(f + 0.5 + power * 0.05));
                    tntEntity.setFuse((int) Math.ceil(30 * f));
                    stack.hurtAndBreak(1, playerentity, e -> e.broadcastBreakEvent(playerentity.getUsedItemHand()));
                    worldIn.addFreshEntity(tntEntity);
                }

                worldIn.playSound(null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS,
                        1.0F, (float) (1.0F / (worldIn.random.nextFloat() * 0.4F + 1.2F) + f * 0.5F));

                if (!flag && !playerentity.isCreative()) {
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        playerentity.getInventory().removeItem(itemstack);
                    }
                }

                playerentity.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    private ItemStack findAmmo(ItemStack shootable, Player player) {
        if (!(shootable.getItem() instanceof ProjectileWeaponItem)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack heldItem = player.getItemInHand(InteractionHand.OFF_HAND);
            if (heldItem.getItem() == Items.TNT) {
                return heldItem;
            } else {
                for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (stack.getItem() == Items.TNT) {
                        return stack;
                    }
                }

                return player.isCreative() ? new ItemStack(Items.TNT) : ItemStack.EMPTY;
            }
        }
    }

    //是否允许附魔
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.FLAMING_ARROWS || enchantment == Enchantments.PUNCH_ARROWS) return false;
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 0;
    }
}
