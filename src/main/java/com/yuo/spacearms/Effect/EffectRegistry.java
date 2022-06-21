package com.yuo.spacearms.Effect;

import com.yuo.spacearms.Spacearms;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegistry {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Spacearms.MOD_ID);

    //冻结
    public static final RegistryObject<Effect> frozen = EFFECTS.register("frozen", () ->
            new FrozenEffect(0xffffff).addAttributesModifier(Attributes.MOVEMENT_SPEED, "9b361cb7-708f-40d2-804c-241266c0a611", -10000, AttributeModifier.Operation.ADDITION));
}
