package com.yuo.spacearms.Items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class SuperFoodEffect {
    //食物属性构建  补充饥饿值，饱腹度  药水效果 获取药水效果概率（1=100%） 总是可以食用 肉
    public static final FoodProperties SUPER_ONE = (new FoodProperties.Builder()).nutrition(5).saturationMod(1).effect(
            () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,  20 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.DIG_SPEED,  30 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST,  20 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.ABSORPTION,  5 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.JUMP, 20 * 20, 1), 1).alwaysEat().meat().build();
    public static final FoodProperties SUPER_ONE_LONG = (new FoodProperties.Builder()).nutrition(12).saturationMod(6).effect(
            () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,  30 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.DIG_SPEED,  40 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST,  30 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.ABSORPTION,  10 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.JUMP, 30 * 20, 1), 1).alwaysEat().meat().build();
    public static final FoodProperties SUPER_TWO = (new FoodProperties.Builder()).nutrition(6).saturationMod(2).effect(
            () -> new MobEffectInstance(MobEffects.REGENERATION,  20 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,  15 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.NIGHT_VISION,  20 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 10 * 20, 1), 1).alwaysEat().build();
    public static final FoodProperties SUPER_TWO_LONG = (new FoodProperties.Builder()).nutrition(14).saturationMod(8).effect(
            () -> new MobEffectInstance(MobEffects.REGENERATION,  30 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,  20 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.NIGHT_VISION,  30 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 15 * 20, 1), 1).alwaysEat().build();
    public static final FoodProperties SUPER_THREE = (new FoodProperties.Builder()).nutrition(20).saturationMod(10).effect(
            () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,  15 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.DIG_SPEED,  20 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST,  15 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.ABSORPTION,  3 * 20, 1), 1).effect(
            () -> new MobEffectInstance(MobEffects.REGENERATION,  15 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,  10 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.NIGHT_VISION,  15 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.JUMP,  15 * 20, 0), 1).effect(
            () -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 10 * 20, 1), 1).alwaysEat().build();
}
