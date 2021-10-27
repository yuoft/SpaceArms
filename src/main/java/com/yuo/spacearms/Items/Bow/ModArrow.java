package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.*;
import com.yuo.spacearms.Items.ItemRegistry;
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
        super(new Item.Properties().group(ModGroup.myGroup));
    }

    //创建箭实体
    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        Item item = stack.getItem();
        if (item.equals(ItemRegistry.ironArrow.get())){
            IronArrowEntity arrowentity = new IronArrowEntity(EntityRegistry.IRON_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.goldArrow.get())){
            GoldArrowEntity arrowentity = new GoldArrowEntity(EntityRegistry.GOLD_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.diamondArrow.get())){
            DiamondArrowEntity arrowentity = new DiamondArrowEntity(EntityRegistry.DIAMOND_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.netheriteArrow.get())){
            NetheriteArrowEntity arrowentity = new NetheriteArrowEntity(EntityRegistry.NETHERITE_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.dragonArrow.get())){
            DragonArrowEntity arrowentity = new DragonArrowEntity(EntityRegistry.DRAGON_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.spaceArrow.get())){
            SpaceArrowEntity arrowentity = new SpaceArrowEntity(EntityRegistry.SPACE_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.enderArrow.get())){
            EnderArrowEntity arrowentity = new EnderArrowEntity(EntityRegistry.ENDER_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.fireArrow.get())){
            FireArrowEntity arrowentity = new FireArrowEntity(EntityRegistry.FIRE_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.iceArrow.get())){
            IceArrowEntity arrowentity = new IceArrowEntity(EntityRegistry.ICE_ARROW.get(), shooter, worldIn);
            return arrowentity;
        } else if (item.equals(ItemRegistry.amosiArrow.get())){
            AmosiArrowEntity arrowentity = new AmosiArrowEntity(EntityRegistry.AMOSI_ARROW.get(), shooter, worldIn);
            return arrowentity;
        }else return null;
    }

    //是否无限
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
        return enchant <= 0 ? false : this.getClass() == ModArrow.class;
    }
}
