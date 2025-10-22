package com.yuo.spacearms.Entity.Arrow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.tool.ToolHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

//箭实体
public class SpaceArrow extends BaseArrow {
    private static final float DAMAGE = 5f;
    public SpaceArrow(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn, DAMAGE);
    }

    public SpaceArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn, DAMAGE);
    }

    public SpaceArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn, DAMAGE);
        this.shooter = shooter;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.spaceArrow.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity living){
            if (living instanceof Player){
                ToolHelper.TP(living, world); //传送玩家
            }else { //产生爆炸
                world.explode(this, getX(), getY(), getZ(), 5, true, ExplosionInteraction.TNT);
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (state.isSolid() && shooter != null && shooter instanceof Player){
            BlockPos position = shooter.blockPosition().below();
            BlockState blockState = world.getBlockState(position);
            world.setBlockAndUpdate(pos, blockState);
            world.setBlockAndUpdate(position, state);
            this.discard();
        }
    }
}
