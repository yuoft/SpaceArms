package com.yuo.spacearms.Entity.Mob;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class RedEnderman extends GreenEnderman {
    public RedEnderman(EntityType<? extends EndermanEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 80.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.26D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, .01D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D)
                .createMutableAttribute(Attributes.ARMOR, 2.0d);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        PlayerEntity player = this.attackingPlayer;
        if (player != null && rand.nextInt(100) > 50 && world.getGameTime() % 40 == 0) {
            BlockPos pos = player.getPosition();
            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ());
            BlockState state = world.getBlockState(pos1);
            if (state.getBlockHardness(world, pos1) < 5.0f) {
                this.setHeldBlockState(state);
                world.setBlockState(pos1, Blocks.AIR.getDefaultState());
            }
        }
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        MobHelper.setEquipmentBasedOnDifficulty(this, difficulty, true);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        this.experienceValue *= 3;
        return super.getExperiencePoints(player);
    }

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        MobHelper.getMobDrops(this, source, looting, true);
    }
}
