package com.yuo.spacearms.Entity.Arrow;

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

//箭实体
public class EnderArrow extends BaseArrow {
    private static final float DAMAGE = 4f;
    public EnderArrow(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn, DAMAGE);
    }

    public EnderArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn, DAMAGE);
    }

    public EnderArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn, DAMAGE);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.enderArrow.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity living){
            living.setDeltaMovement(0, 1.5, 0); //击飞生物
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) { //击中方块传送玩家
        BlockPos pos = result.getBlockPos();
        BlockPos offset = pos.relative(result.getDirection());
        if (shooter != null){
            shooter.moveTo(offset.getX(), offset.getY(), offset.getZ()); //强制移动玩家
        }
        super.onHitBlock(result);
    }
}
