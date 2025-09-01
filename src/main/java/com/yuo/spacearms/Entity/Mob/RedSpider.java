package com.yuo.spacearms.Entity.Mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.world.World;

public class RedSpider extends GreenSpider {
    public RedSpider(EntityType<? extends SpiderEntity> type, World world) {
        super(type, world);
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
}
