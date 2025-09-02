package com.yuo.spacearms.Entity.Mob;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Explosion;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Collection;
import java.util.Iterator;

public class RedCreeper extends GreenCreeper {
    public RedCreeper(EntityType<? extends CreeperEntity> type, World world) {
        super(type, world);
        this.fuseTime = 5;
        this.explosionRadius = 8;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.26D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, .01D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D)
                .createMutableAttribute(Attributes.ARMOR, 2.0d);
    }

    @Override
    public float getExplosionResistance(Explosion explosion, IBlockReader blockReader, BlockPos pos, BlockState state, FluidState fluidState, float v) {
        return super.getExplosionResistance(explosion, blockReader, pos, state, fluidState, v);
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
