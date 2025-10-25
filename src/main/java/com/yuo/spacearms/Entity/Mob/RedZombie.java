package com.yuo.spacearms.Entity.Mob;

import com.yuo.spacearms.Entity.AI.AISetBlock;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class RedZombie extends GreenZombie {

    public RedZombie(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(2, new AISetBlock(this));
    }

    //属性
    public static AttributeSupplier.Builder setCustomAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 2.0D)
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, .01D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ARMOR, 2.0d);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(randomSource, difficulty);
        MobHelper.setEquipmentBasedOnDifficulty(this, difficulty, true);
    }

    @Override
    public int getExperienceReward() {
        this.xpReward *= 3;
        return super.getExperienceReward();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean b) {
        super.dropCustomDeathLoot(source, looting, b);
        MobHelper.getMobDrops(this, source, looting, true);
    }
}
