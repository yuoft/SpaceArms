package com.yuo.spacearms.Entity.Mob;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Collection;
import java.util.Iterator;

public class GreenCreeper extends Creeper {
    private int lastActiveTime;
    private int timeSinceIgnited;
    protected int fuseTime;
    protected int explosionRadius;

    public GreenCreeper(EntityType<? extends Creeper> type, Level world) {
        super(type, world);
        this.fuseTime = 15;
        this.explosionRadius = 6;
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.24D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 2.0d);
    }

    public void tick() {
        if (this.isAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int i = this.getSwellDir();
            if (i > 0 && this.timeSinceIgnited == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.explodeCreeper();
            }
        }

        super.tick();
    }

    @Override
    public float getSwelling(float v) {
        return Mth.lerp(v, (float)this.lastActiveTime, (float)this.timeSinceIgnited) / (float)(this.fuseTime - 2);
    }

    protected void explodeCreeper() {
        if (!this.level().isClientSide) {
            ExplosionInteraction interaction = ForgeEventFactory.getMobGriefingEvent(this.level(), this) ? ExplosionInteraction.MOB : ExplosionInteraction.NONE;
            int i = this.isPowered() ? this.explosionRadius + 2 : this.explosionRadius;
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), i, interaction);
            this.discard();
            this.spawnLingeringCloud();
        }

    }

    //效果云
    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> activeEffects = this.getActiveEffects();
        if (!activeEffects.isEmpty()) {
            AreaEffectCloud areaEffectCloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaEffectCloud.setRadius(2.5F);
            areaEffectCloud.setRadiusOnUse(-0.5F);
            areaEffectCloud.setWaitTime(10);
            areaEffectCloud.setDuration(areaEffectCloud.getDuration() / 2);
            areaEffectCloud.setRadiusPerTick(-areaEffectCloud.getRadius() / (float)areaEffectCloud.getDuration());

            for (MobEffectInstance $$2 : activeEffects) {
                areaEffectCloud.addEffect(new MobEffectInstance($$2));
            }

            this.level().addFreshEntity(areaEffectCloud);
        }

    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        super.populateDefaultEquipmentSlots(randomSource, difficultyInstance);
        MobHelper.setEquipmentBasedOnDifficulty(this, difficultyInstance, false);
    }

    @Override
    public int getExperienceReward() {
        this.xpReward *= 2;
        return super.getExperienceReward();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        MobHelper.getMobDrops(this, source, looting, false);
    }
}
