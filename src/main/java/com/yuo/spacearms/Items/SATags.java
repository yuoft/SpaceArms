package com.yuo.spacearms.Items;

import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

//模组标签定义
public class SATags {
    public static final TagKey<Item> IRON_ARROWS = bind("iron_arrows");
    public static final TagKey<Item> GOLD_ARROWS = bind("gold_arrows");
    public static final TagKey<Item> DIAMOND_ARROWS = bind("diamond_arrows");
    public static final TagKey<Item> NETHERITE_ARROWS = bind("netherite_arrows");
    public static final TagKey<Item> DRAGON_ARROWS = bind("dragon_arrows");
    public static final TagKey<Item> SPACE_ARROWS = bind("space_arrows");
    public static final TagKey<Item> ENDER_ARROWS = bind( "ender_arrows");
    public static final TagKey<Item> FIRE_ARROWS = bind( "fire_arrows");
    public static final TagKey<Item> ICE_ARROWS = bind( "ice_arrows");
    public static final TagKey<Item> AMOSI_ARROWS = bind("amosi_arrows");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, RlUtils.fa(SpaceArms.MOD_ID, name));
    }
}
