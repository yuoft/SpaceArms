package com.yuo.spacearms.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class FrozenEffect extends Effect {

    protected FrozenEffect(int liquidColorIn) {
        super(EffectType.HARMFUL, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (this == EffectRegistry.frozen.get()){
            entityLivingBaseIn.setAIMoveSpeed(0);
        }
    }
}
