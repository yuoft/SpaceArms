package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Util.Helper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//箭实体
public class SpaceArrowEntity extends AbstractArrowEntity {
    private LivingEntity living;

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
        this.living = shooter;
        this.setDamage(5);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(SAItems.spaceArrow.get());
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

    @Override
    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getMaterial().isSolid() && living != null && living instanceof PlayerEntity){
            BlockPos position = living.getPosition().down();
            BlockState blockState = world.getBlockState(position);
            world.setBlockState(pos, blockState);
            world.setBlockState(position, state);
            this.remove();
        }
    }
}
