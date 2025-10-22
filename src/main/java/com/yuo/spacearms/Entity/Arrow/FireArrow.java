package com.yuo.spacearms.Entity.Arrow;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.Tags;

public class FireArrow extends BaseArrow {
    private static final float DAMAGE = 4f;
    public FireArrow(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn, DAMAGE);
    }

    public FireArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn, DAMAGE);
    }

    public FireArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn, DAMAGE);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.fireArrow.get());
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        Direction face = result.getDirection();
        BlockPos pos = result.getBlockPos().relative(face);
        if (!world.getBlockState(pos).isAir()) return;
        if (face == Direction.UP){
            world.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState()); //生成火
            this.discard();
        }
        BlockState state = world.getBlockState(pos);
        if (state.is(BlockTags.LOGS)){
            world.setBlockAndUpdate(pos, Blocks.FIRE_CORAL_WALL_FAN.defaultBlockState());
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity living)
            living.setSecondsOnFire(3);
    }
}
