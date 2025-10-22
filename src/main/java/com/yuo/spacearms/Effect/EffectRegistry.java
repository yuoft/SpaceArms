package com.yuo.spacearms.Effect;

import com.yuo.spacearms.SpaceArms;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SpaceArms.MOD_ID);

    //冻结
    public static final RegistryObject<MobEffect> frozen = EFFECTS.register("frozen", () ->
            new FrozenEffect(0xffffff).addAttributeModifier(Attributes.MOVEMENT_SPEED, "9b361cb7-708f-40d2-804c-241266c0a611", -10000, AttributeModifier.Operation.ADDITION));
}
