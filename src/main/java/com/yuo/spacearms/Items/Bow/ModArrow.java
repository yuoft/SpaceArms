package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Entity.*;
import com.yuo.spacearms.Entity.Arrow.*;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SATabs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

//箭物品
public class ModArrow extends ArrowItem {
    public ModArrow() {
        super(new Item.Properties());
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return stack.getItem() == SAItems.amosiArrow.get();
    }

    //创建箭实体
    @Override
    public @NotNull AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
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
        }else return new Arrow(worldIn, shooter);
    }

    //是否无限
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = bow.getEnchantmentLevel(Enchantments.INFINITY_ARROWS);
        return enchant > 0 && this.getClass() == ModArrow.class;
    }
}
