package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

//投掷物实体
public class DragonCrystal extends ThrowableItemProjectile {

    public DragonCrystal(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    public DragonCrystal(EntityType type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn);
    }

    public DragonCrystal(EntityType type, LivingEntity livingEntityIn, Level worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return SAItems.dragonCrystal.get().asItem();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Level level = this.level();
        if (!level.isClientSide){
            Entity entity = result.getEntity();
            level.explode(this, this.getX(), this.getY(), this.getZ(), 5.0f, true, ExplosionInteraction.TNT);
            LightningBolt lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(level);
            if (lightningBoltEntity != null){
                lightningBoltEntity.moveTo(Vec3.atBottomCenterOf(entity.blockPosition()));
                lightningBoltEntity.setCause(entity instanceof ServerPlayer ? (ServerPlayer)entity : null);
                level.addFreshEntity(lightningBoltEntity); //服务端召唤闪电
            }
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        Level world = this.level();
        if (!world.isClientSide){
            //爆炸
            world.explode(this, this.getX(), this.getY(), this.getZ(), 5.0f, true, ExplosionInteraction.TNT);
            LightningBolt lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(world);
            lightningBoltEntity.moveTo(Vec3.atBottomCenterOf(result.getBlockPos()));
            world.addFreshEntity(lightningBoltEntity); //服务端召唤闪电
            this.discard();
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
