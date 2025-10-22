package com.yuo.spacearms.Entity.Arrow;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class DragonArrow extends BaseArrow {
    private static final float DAMAGE = 4.5f;
    public DragonArrow(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn, DAMAGE);
    }

    public DragonArrow(EntityType<? extends AbstractArrow> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn, DAMAGE);
    }

    public DragonArrow(EntityType<? extends AbstractArrow> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn, DAMAGE);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(SAItems.dragonArrow.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof  LivingEntity living){
            if (living instanceof Player){
                living.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 0));
            }
            LightningBolt lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(world);  //召唤闪电
            if (!world.isClientSide && lightningBoltEntity != null){
                lightningBoltEntity.moveTo(Vec3.atBottomCenterOf(living.blockPosition()));
                lightningBoltEntity.setCause(living instanceof ServerPlayer ? (ServerPlayer) living : null);
                world.addFreshEntity(lightningBoltEntity);
            }
            this.discard();
        }
    }
}
