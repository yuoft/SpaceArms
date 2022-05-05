package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//投掷物实体
public class GoldTntEntity extends ProjectileItemEntity {

    public GoldTntEntity(EntityType type, World worldIn) {
        super(type, worldIn);
    }

    public GoldTntEntity(EntityType type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public GoldTntEntity(EntityType type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.goldTnt.get().asItem();
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        if (!world.isRemote){
            world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 5.0f, true, Explosion.Mode.DESTROY);
            this.remove();
        }
    }

    //落地或碰撞后触发事件
    protected void onImpact(RayTraceResult result) {
        RayTraceResult.Type raytraceresult$type = result.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onEntityHit((EntityRayTraceResult)result);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            this.func_230299_a_((BlockRayTraceResult)result);
        }

    }

    @Override
    protected void func_230299_a_(BlockRayTraceResult result) {
        if (!world.isRemote){
            //爆炸
            world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 5.0f, true, Explosion.Mode.DESTROY);
            this.remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
