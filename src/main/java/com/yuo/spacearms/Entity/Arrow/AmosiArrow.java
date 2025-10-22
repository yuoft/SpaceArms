package com.yuo.spacearms.Entity.Arrow;

import com.yuo.spacearms.Entity.SAEntitys;
import com.yuo.spacearms.Items.SAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.ArrayList;
import java.util.List;

public class AmosiArrow extends BaseArrow {
    private static final float DAMAGE = 11f;
    public AmosiArrow(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn, DAMAGE);
    }

    public AmosiArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn, DAMAGE);
    }

    public AmosiArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn, DAMAGE);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.amosiArrow.get());
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos blockPos = result.getBlockPos();
        BlockPos pos = blockPos.above(3);
        ganyuArrow(world, pos);
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity living) {
            BlockPos blockPos = living.blockPosition();
            BlockPos pos = blockPos.above(3);
            ganyuArrow(world, pos);
            this.discard();
        }
    }

    //生成向四周散射的箭
    private static void ganyuArrow(Level world, BlockPos pos){
        List<AmosiBowArrow> arrows = new ArrayList<>();
        for (int i = 0; i < 16; i++){
            arrows.add(i, new AmosiBowArrow(SAEntitys.AMOSI_BOW_ARROW.get(), pos.getX(), pos.getY(), pos.getZ(), world));
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
        for (AmosiBowArrow arrow : arrows){
            world.addFreshEntity(arrow);
        }
    }
}
