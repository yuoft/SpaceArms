package com.yuo.spacearms.Entity;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//箭实体
public class IceArrowEntity extends AbstractArrowEntity {
    public IceArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDamage(4);
    }

    public IceArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setDamage(4);
    }

    public IceArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
        this.setDamage(4);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(SAItems.iceArrow.get());
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
            world.setBlockState(pos, Blocks.SNOW.getDefaultState()); //生成雪
            this.remove();
        }
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        living.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 60, 1)); //缓慢2
        living.setMotion(0, 0, 0);
    }

}
