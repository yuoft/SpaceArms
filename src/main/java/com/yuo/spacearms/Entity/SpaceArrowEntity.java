package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Util.Helper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//箭实体
public class SpaceArrowEntity extends AbstractArrowEntity {
    public SpaceArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDamage(5);
    }

    public SpaceArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setDamage(5);
    }

    public SpaceArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.setDamage(5);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemRegistry.spaceArrow.get());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putDouble("damage", 5);
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
            Helper.TP(living, world); //传送玩家
        }else { //产生爆炸
            world.createExplosion(this, getPosX(), getPosY(), getPosZ(), 5, true, Explosion.Mode.BREAK);
        }
    }
}
