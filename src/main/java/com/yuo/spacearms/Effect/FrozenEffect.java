package com.yuo.spacearms.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class FrozenEffect extends MobEffect {

    protected FrozenEffect(int liquidColorIn) {
        super(MobEffectCategory.HARMFUL, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (this == EffectRegistry.frozen.get()){
//            living.setDeltaMovement(Vec3.ZERO);
        }
    }
}
