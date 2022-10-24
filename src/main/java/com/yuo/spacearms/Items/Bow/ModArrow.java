package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.*;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

//箭物品
public class ModArrow extends ArrowItem {
    public ModArrow() {
        super(new Item.Properties().group(ModGroup.spaceArms));
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
            return new IronArrowEntity(EntityRegistry.IRON_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.goldArrow.get())){
            return new GoldArrowEntity(EntityRegistry.GOLD_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.diamondArrow.get())){
            return new DiamondArrowEntity(EntityRegistry.DIAMOND_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.netheriteArrow.get())){
            return new NetheriteArrowEntity(EntityRegistry.NETHERITE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.dragonArrow.get())){
            return new DragonArrowEntity(EntityRegistry.DRAGON_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.spaceArrow.get())){
            return new SpaceArrowEntity(EntityRegistry.SPACE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.enderArrow.get())){
            return new EnderArrowEntity(EntityRegistry.ENDER_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.fireArrow.get())){
            return new FireArrowEntity(EntityRegistry.FIRE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.iceArrow.get())){
            return new IceArrowEntity(EntityRegistry.ICE_ARROW.get(), shooter, worldIn);
        } else if (item.equals(SAItems.amosiArrow.get())){
            return new AmosiArrowEntity(EntityRegistry.AMOSI_ARROW.get(), shooter, worldIn);
        }else return null;
    }

    //是否无限
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == ModArrow.class;
    }
}
