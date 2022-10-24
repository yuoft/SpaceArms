package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//箭实体
public class EnderArrowEntity extends AbstractArrowEntity {
    private LivingEntity entity;
    public EnderArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDamage(4);
    }

    public EnderArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setDamage(4);
    }

    public EnderArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.entity = shooter;
        this.setDamage(4);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(SAItems.enderArrow.get());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putDouble("damage", 4);
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
        living.setMotion(0, 1.5, 0); //击飞生物
        this.remove();
    }

    @Override
    protected void func_230299_a_(BlockRayTraceResult result) { //击中方块传送玩家
        BlockPos pos = result.getPos();
        BlockPos offset = pos.offset(result.getFace());
        if (entity != null){
            entity.moveForced(offset.getX(), offset.getY(), offset.getZ()); //强制移动玩家
        }
        super.func_230299_a_(result);
    }
}
