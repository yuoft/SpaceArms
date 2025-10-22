package com.yuo.spacearms.Entity.Arrow;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class IronArrow extends BaseArrow {
    private static final float DAMAGE = 2.5f;
    public IronArrow(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn, DAMAGE);
    }

    public IronArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn, DAMAGE);
    }

    public IronArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn, DAMAGE);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.ironArrow.get());
    }
}
