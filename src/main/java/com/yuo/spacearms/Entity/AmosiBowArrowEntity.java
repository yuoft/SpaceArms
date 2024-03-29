package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//箭实体
public class AmosiBowArrowEntity extends AbstractArrowEntity {
    public AmosiBowArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDamage(10);
    }

    public AmosiBowArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setDamage(10);
    }

    public AmosiBowArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.setDamage(10);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(SAItems.diamondArrow.get());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putDouble("damage", 10);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setDamage(compound.getDouble("damage"));
    }

    @Override
    public void setPierceLevel(byte level) {
        super.setPierceLevel((byte) 10); //穿透等级10
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        living.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 2)); //减速
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
