package com.yuo.spacearms.Items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class SuperFoodEffect {
    //食物属性构建  补充饥饿值，饱腹度  药水效果 获取药水效果概率（1=100%） 总是可以食用 肉
    public static final Food SUPER_ONE = (new Food.Builder()).hunger(5).saturation(1).effect(
            () -> new EffectInstance(Effects.SPEED,  20 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.HASTE,  30 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.STRENGTH,  20 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.ABSORPTION,  5 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.JUMP_BOOST, 20 * 20, 1), 1).setAlwaysEdible().meat().build();
    public static final Food SUPER_ONE_LONG = (new Food.Builder()).hunger(12).saturation(6).effect(
            () -> new EffectInstance(Effects.SPEED,  30 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.HASTE,  40 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.STRENGTH,  30 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.ABSORPTION,  10 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.JUMP_BOOST, 30 * 20, 1), 1).setAlwaysEdible().meat().build();
    public static final Food SUPER_TWO = (new Food.Builder()).hunger(6).saturation(2).effect(
            () -> new EffectInstance(Effects.REGENERATION,  20 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.FIRE_RESISTANCE,  15 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.NIGHT_VISION,  20 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.HEALTH_BOOST, 10 * 20, 1), 1).setAlwaysEdible().build();
    public static final Food SUPER_TWO_LONG = (new Food.Builder()).hunger(14).saturation(8).effect(
            () -> new EffectInstance(Effects.REGENERATION,  30 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.FIRE_RESISTANCE,  20 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.NIGHT_VISION,  30 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.HEALTH_BOOST, 15 * 20, 1), 1).setAlwaysEdible().build();
    public static final Food SUPER_THREE = (new Food.Builder()).hunger(20).saturation(10).effect(
            () -> new EffectInstance(Effects.SPEED,  15 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.HASTE,  20 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.STRENGTH,  15 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.ABSORPTION,  3 * 20, 1), 1).effect(
            () -> new EffectInstance(Effects.REGENERATION,  15 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.FIRE_RESISTANCE,  10 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.NIGHT_VISION,  15 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.JUMP_BOOST,  15 * 20, 0), 1).effect(
            () -> new EffectInstance(Effects.HEALTH_BOOST, 10 * 20, 1), 1).setAlwaysEdible().build();
}
