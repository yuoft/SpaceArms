package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.*;
import com.yuo.spacearms.Entity.Arrow.*;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SATabs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

//箭物品
public class ModArrow extends ArrowItem {
    public ModArrow() {
        super(new Item.Properties().group(SATabs.spaceArms0));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getItem() == SAItems.amosiArrow.get();
    }

    //创建箭实体
    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        Item item = stack.getItem();
        if (item.equals(SAItems.ironArrow.get())){
            return new IronArrow(SAEntitys.IRON_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.goldArrow.get())){
            return new GoldArrow(SAEntitys.GOLD_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.diamondArrow.get())){
            return new DiamondArrow(SAEntitys.DIAMOND_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.netheriteArrow.get())){
            return new NetheriteArrow(SAEntitys.NETHERITE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.dragonArrow.get())){
            return new DragonArrow(SAEntitys.DRAGON_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.spaceArrow.get())){
            return new SpaceArrow(SAEntitys.SPACE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.enderArrow.get())){
            return new EnderArrow(SAEntitys.ENDER_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.fireArrow.get())){
            return new FireArrow(SAEntitys.FIRE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.iceArrow.get())){
            return new IceArrow(SAEntitys.ICE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.amosiArrow.get())){
            return new AmosiArrow(SAEntitys.AMOSI_ARROW.get(), shooter, worldIn);
        }else return null;
    }

    //是否无限
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == ModArrow.class;
    }
}
