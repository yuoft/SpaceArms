package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//箭实体
public class FireArrowEntity extends AbstractArrowEntity {
    public FireArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDamage(4);
    }

    public FireArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setDamage(4);
    }

    public FireArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.setDamage(4);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemRegistry.fireArrow.get());
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
    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        Direction face = result.getFace();
        BlockPos pos = result.getPos().offset(face);
        if (!world.isAirBlock(pos)) return;
        if (face == Direction.UP){
            world.setBlockState(pos, Blocks.FIRE.getDefaultState()); //生成火
            this.remove();
        }
        BlockState state = world.getBlockState(pos);
        if (state.getMaterial() == Material.WOOD){
            world.setBlockState(pos, Blocks.FIRE_CORAL_WALL_FAN.getDefaultState());
            this.remove();
        }
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        living.setFire(3); //点燃
    }
}
