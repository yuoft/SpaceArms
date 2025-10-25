package com.yuo.spacearms.Entity.Mob;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class RedEnderMan extends GreenEnderMan {
    public RedEnderMan(EntityType<? extends EnderMan> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, .01D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ARMOR, 2.0d);
    }

    @Override
    public void tick() {
        super.tick();
        Player player = this.lastHurtByPlayer;
        if (player != null && random.nextInt(100) > 50 && this.tickCount % 40 == 0) {
            BlockPos pos = player.blockPosition();
            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ());
            BlockState state = level().getBlockState(pos1);
            if (state.getBlock().defaultDestroyTime() < 5.0f) {
                this.setCarriedBlock(state);
                level().setBlockAndUpdate(pos1, Blocks.AIR.defaultBlockState());
            }
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        super.populateDefaultEquipmentSlots(randomSource, difficultyInstance);
        MobHelper.setEquipmentBasedOnDifficulty(this, difficultyInstance, false);
    }

    @Override
    public int getExperienceReward() {
        this.xpReward *= 3;
        return super.getExperienceReward();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        MobHelper.getMobDrops(this, source, looting, false);
    }
}
