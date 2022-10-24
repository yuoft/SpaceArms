package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//箭实体
public class DragonArrowEntity extends AbstractArrowEntity {
    public DragonArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDamage(4.5);
    }

    public DragonArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setDamage(4.5);
    }

    public DragonArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.setDamage(4.5);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(SAItems.dragonArrow.get());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putDouble("damage", 4.5);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setDamage(compound.getDouble("damage"));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        if (living instanceof PlayerEntity){
            living.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 100, 0));
        }
        LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(world);  //召唤闪电
        if (!world.isRemote && lightningBoltEntity != null){
            lightningBoltEntity.moveForced(Vector3d.copyCenteredHorizontally(living.getPosition()));
            lightningBoltEntity.setCaster(living instanceof ServerPlayerEntity ? (ServerPlayerEntity)living : null);
            world.addEntity(lightningBoltEntity);
        }
        this.remove();
    }
}
