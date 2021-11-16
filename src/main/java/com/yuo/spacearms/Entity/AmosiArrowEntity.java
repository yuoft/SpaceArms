package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.ItemRegistry;
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

import java.util.ArrayList;
import java.util.List;

//箭实体
public class AmosiArrowEntity extends AbstractArrowEntity {
    public AmosiArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDamage(10);
    }

    public AmosiArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setDamage(10);
    }

    public AmosiArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.setDamage(10);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemRegistry.amosiArrow.get());
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
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        BlockPos blockPos = result.getPos();
        BlockPos pos = blockPos.up(3);
        ganyuArrow(world, pos);
        this.remove();
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        BlockPos blockPos = living.getPosition();
        BlockPos pos = blockPos.up(3);
        ganyuArrow(world, pos);
        this.remove();
    }

    //生成向四周散射的箭
    private static void ganyuArrow(World world, BlockPos pos){
        List<AmosiBowArrowEntity> arrows = new ArrayList<>();
        for (int i = 0; i < 16; i++){
            arrows.add(i, new AmosiBowArrowEntity(EntityRegistry.AMOSI_BOW_ARROW.get(), pos.getX(), pos.getY(), pos.getZ(), world));
        }
        arrows.get(0).shoot(2, -1.10, 2, 3.0f, 1.0f);
        arrows.get(1).shoot(-2, -1.10, 2, 3.0f, 1.0f);
        arrows.get(2).shoot(2, -1.10, -2, 3.0f, 1.0f);
        arrows.get(3).shoot(-2, -1.10, -2, 3.0f, 1.0f);
        arrows.get(4).shoot(-2, -1.10, 0, 3.0f, 1.0f);
        arrows.get(5).shoot(0, -1.10, 2, 3.0f, 1.0f);
        arrows.get(6).shoot(0, -1.10, -2, 3.0f, 1.0f);
        arrows.get(7).shoot(2, -1.10, 0, 3.0f, 1.0f);

        arrows.get(8).shoot(1, -1.10, 2, 3.0f, 1.0f);
        arrows.get(9).shoot(-1, -1.10, 2, 3.0f, 1.0f);
        arrows.get(10).shoot(1, -1.10, -2, 3.0f, 1.0f);
        arrows.get(11).shoot(-1, -1.10, -2, 3.0f, 1.0f);
        arrows.get(12).shoot(2, -1.10, -1, 3.0f, 1.0f);
        arrows.get(13).shoot(-2, -1.10, 1, 3.0f, 1.0f);
        arrows.get(14).shoot(2, -1.10, 1, 3.0f, 1.0f);
        arrows.get(15).shoot(-2, -1.10, -1, 3.0f, 1.0f);
        for (AmosiBowArrowEntity arrow : arrows){
            world.addEntity(arrow);
        }
    }
}
