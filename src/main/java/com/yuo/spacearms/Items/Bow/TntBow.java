package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TntBow extends BowItem {

    public TntBow() {
        super(new Properties().maxDamage(384).group(ModGroup.spaceArms));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.tnt_bow"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !findAmmo(itemstack, playerIn).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !flag) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }


    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = findAmmo(stack, playerentity);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.TNT);
                }

                double f = getArrowVelocity(i) + 0.5d;
                if (f < 0.6d) return;
                if (!worldIn.isRemote) {
                    TNTEntity tntEntity = new TNTEntity(worldIn, playerentity.getPosX(), playerentity.getPosY() + 0.5, playerentity.getPosZ(), playerentity);
                    int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                    tntEntity.setMotion(playerentity.getLookVec().scale(f + 0.5 + power * 0.05));
                    tntEntity.setFuse((int) Math.ceil(30 * f));
                    stack.damageItem(1, playerentity, e -> e.sendBreakAnimation(playerentity.getActiveHand()));
                    worldIn.addEntity(tntEntity);
                }

                worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, (float) (1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F));

                if (!flag && !playerentity.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        playerentity.inventory.deleteStack(itemstack);
                    }
                }

                playerentity.addStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    private ItemStack findAmmo(ItemStack shootable, PlayerEntity player) {
        if (!(shootable.getItem() instanceof ShootableItem)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack heldItem = player.getHeldItem(Hand.OFF_HAND);
            if (heldItem.getItem() == Items.TNT) {
                return heldItem;
            } else {
                for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if (stack.getItem() == Items.TNT) {
                        return stack;
                    }
                }

                return player.abilities.isCreativeMode ? new ItemStack(Items.TNT) : ItemStack.EMPTY;
            }
        }
    }

    //是否允许附魔
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.FLAME || enchantment == Enchantments.PUNCH) return false;
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }
}
