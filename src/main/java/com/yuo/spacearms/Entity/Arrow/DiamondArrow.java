package com.yuo.spacearms.Entity.Arrow;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DiamondArrow extends BaseArrow {
    private static final float DAMAGE = 3.5f;
    public DiamondArrow(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn, DAMAGE);
    }

    public DiamondArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn, DAMAGE);
    }

    public DiamondArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn, DAMAGE);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.diamondArrow.get());
    }
}
