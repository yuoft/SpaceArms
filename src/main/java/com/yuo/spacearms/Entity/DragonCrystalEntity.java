package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

//投掷物实体
public class DragonCrystalEntity extends ProjectileItemEntity {

    public DragonCrystalEntity(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    public DragonCrystalEntity(EntityType type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public DragonCrystalEntity(EntityType type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.dragonCrystal.get().asItem();
    }
    //落地或碰撞后触发事件
    @Override
    protected void onImpact(RayTraceResult result) {
        world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 5.0f, true, Explosion.Mode.DESTROY);
        LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
        if (world instanceof ServerWorld){
            ((ServerWorld) world).addLightningBolt(lightningBoltEntity); //服务端召唤闪电
        }
        if (!world.isRemote){
            this.remove();
        }
    }
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
