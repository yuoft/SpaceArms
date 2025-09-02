package com.yuo.spacearms.Entity.Mob;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;

public class GreenEnderman extends EndermanEntity {
    public GreenEnderman(EntityType<? extends EndermanEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.24D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 40.0D)
                .createMutableAttribute(Attributes.ARMOR, 2.0d);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        PlayerEntity player = this.attackingPlayer;
        if (player != null && rand.nextInt(100) > 50 && world.getGameTime() % 200 == 0) {
            AxisAlignedBB alignedBB = new AxisAlignedBB(this.getPosition().add(-16, -8, -16), this.getPosition().add(16, 8, 8));
            for (MonsterEntity monster : this.getEntityWorld().getEntitiesWithinAABB(MonsterEntity.class, alignedBB)) {
                if (monster instanceof CreeperEntity){
                    this.teleportToEntity(monster);

                    BlockPos blockPos = player.getPosition();
                    BlockPos blockPos1 = new BlockPos(blockPos.getX() + rand.nextFloat() * 0.5f, blockPos.getY() + 0.5, blockPos.getZ() + rand.nextFloat() * 0.5f);
                    for (int i = 0; i < 10; i++){
                        world.addParticle(ParticleTypes.PORTAL, blockPos1.getX(), blockPos1.getY(), blockPos1.getZ(), (this.rand.nextDouble() - 0.5) * 2.0, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5) * 2.0);
                        this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    }
                    monster.setPosition(blockPos1.getX(), blockPos1.getY(), blockPos1.getZ());
                    this.teleportToEntity(player);

                    this.teleportRandomly();

                    if (this.getMaxHealth() < 80) break;
                }
            }
        }
    }

    protected boolean teleportRandomly() {
        if (!this.world.isRemote() && this.isAlive()) {
            double d0 = this.getPosX() + (this.rand.nextDouble() - 0.5) * 64.0;
            double d1 = this.getPosY() + (double)(this.rand.nextInt(64) - 32);
            double d2 = this.getPosZ() + (this.rand.nextDouble() - 0.5) * 64.0;
            return this.teleportTo(d0, d1, d2);
        } else {
            return false;
        }
    }


    private boolean teleportToEntity(Entity p_70816_1_) {
        Vector3d vector3d = new Vector3d(this.getPosX() - p_70816_1_.getPosX(), this.getPosYHeight(0.5) - p_70816_1_.getPosYEye(), this.getPosZ() - p_70816_1_.getPosZ());
        vector3d = vector3d.normalize();
        double d0 = 16.0;
        double d1 = this.getPosX() + (this.rand.nextDouble() - 0.5) * 8.0 - vector3d.x * 16.0;
        double d2 = this.getPosY() + (double)(this.rand.nextInt(16) - 8) - vector3d.y * 16.0;
        double d3 = this.getPosZ() + (this.rand.nextDouble() - 0.5) * 8.0 - vector3d.z * 16.0;
        return this.teleportTo(d1, d2, d3);
    }

    protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(p_70825_1_, p_70825_3_, p_70825_5_);

        while(blockpos$mutable.getY() > 0 && !this.world.getBlockState(blockpos$mutable).getMaterial().blocksMovement()) {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = this.world.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMovement();
        boolean flag1 = blockstate.getFluidState().isTagged(FluidTags.WATER);
        if (flag && !flag1) {
            EntityTeleportEvent.EnderEntity event = ForgeEventFactory.onEnderTeleport(this, p_70825_1_, p_70825_3_, p_70825_5_);
            if (event.isCanceled()) {
                return false;
            } else {
                boolean flag2 = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
                if (flag2 && !this.isSilent()) {
                    this.world.playSound((PlayerEntity)null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                    this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }

                return flag2;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canEquipItem(ItemStack stack) {
        return true;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        MobHelper.setEquipmentBasedOnDifficulty(this, difficulty, false);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        this.experienceValue *= 2;
        return super.getExperiencePoints(player);
    }

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        MobHelper.getMobDrops(this, source, looting, false);
    }
}
