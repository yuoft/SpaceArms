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

/**
 * 箭矢基础实体
 */
public class BaseArrow extends AbstractArrow {
    private final float damage;
    protected LivingEntity shooter;
    protected final Level world;
    public BaseArrow(EntityType<? extends AbstractArrow> type, Level worldIn, float damage) {
        super(type, worldIn);
        this.damage = damage;
        this.world = worldIn;
        this.setBaseDamage(damage);
    }

    public BaseArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn, float damage) {
        super(type, x, y, z, worldIn);
        this.damage = damage;
        this.world = worldIn;
        this.setBaseDamage(damage);
    }

    public BaseArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn, float damage) {
        super(type, shooter, worldIn);
        this.damage = damage;
        this.shooter = shooter;
        this.world = worldIn;
        this.setBaseDamage(damage);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.ironArrow.get());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putDouble("damage", this.damage);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setBaseDamage(compound.getDouble("damage"));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
