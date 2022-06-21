package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;

public enum ShieldType {
    IRON("iron", 2, 396, 0.1f, Tags.Items.INGOTS_IRON, 5, false),
    GOLD("gold", 3.5f, 256, 0.2f, Tags.Items.INGOTS_GOLD, 12, false),
    DIAMOND("diamond", 4, 443, 0.3f, Tags.Items.GEMS_DIAMOND, 10, false),
    NETHERITE("netherite", 6, 526, 0.5f, Tags.Items.INGOTS_NETHERITE, 15, true),
    OBSIDIAN("obsidian", 5.5f, 509, 0.45f, Tags.Items.OBSIDIAN, 13, true);

    private final String name; //id
    private final float protectionValue; //防护数值(盔甲值)
    private final int maxDamage; //耐久值
    private final float probability; //免伤概率
    private final Tags.IOptionalNamedTag<Item> repairable; //修复材料
    private final int enchantAbility; //附魔能力
    private final Item.Properties properties; //物品属性

    ShieldType(@Nonnull String id, float protectionValueIn, int maxDamageIn, float probabilityIn, Tags.IOptionalNamedTag<Item> repairableIn,
               int enchantAbilityIn, boolean isImmuneToFire){
        this.name = id;
        this.protectionValue = protectionValueIn;
        this.maxDamage = maxDamageIn;
        this.probability = probabilityIn;
        this.repairable = repairableIn;
        this.enchantAbility = enchantAbilityIn;
        if (isImmuneToFire)
            this.properties = new Item.Properties().group(ModGroup.spaceArms).maxDamage(maxDamage).isImmuneToFire();
        else this.properties = new Item.Properties().group(ModGroup.spaceArms).maxDamage(maxDamage);
    }

    public String getName() {
        return name;
    }

    public float getProtectionValue() {
        return protectionValue;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    /**
     * 最大概率不能超过90%
     * @return 概率
     */
    public float getProbability() {
        return Math.min(0.9f, probability);
    }

    public int getEnchantAbility() {
        return enchantAbility;
    }

    public Tags.IOptionalNamedTag<Item> getRepairable() {
        return repairable;
    }

    public Item.Properties getProperties() {
        return properties;
    }
}
