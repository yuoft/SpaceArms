package com.yuo.spacearms.Items;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum SAItemTiers implements Tier {
    WOLF(233, 20.0f, 52.8f, 3, 0, () -> Ingredient.of(Items.NETHER_STAR)),
    BH3(233, 11.0f, 15, 3, 10, () -> Ingredient.of(Items.NETHER_STAR)),
    RUBY(1456, 11f, 4.0f, 3, 10, () -> Ingredient.of(SAItems.ruby.get())),
    JADE(1589, 15.0f, 5.0f, 3, 10, () -> Ingredient.of(SAItems.jade.get())),
    XRAY(2179, 19.0f, 12, 4, 12, () -> Ingredient.of(SAItems.xrayIngot.get())),
    SUPER(2605, 22.0f, 15, 4, 14, () -> Ingredient.of(SAItems.superIngot.get())),
    DRAGON(3465, 25f, 18, 5, 15, () -> Ingredient.of(SAItems.dragonCrystal.get())),
    SUPER_XRAY(3947, 30.0f, 23, 5, 16, () -> Ingredient.of(SAItems.superXrayIngot.get())),
    ULTRA(4467, 35.0f, 29, 6, 20, () -> Ingredient.of(SAItems.ultraIngot.get())),
    SPACE(5924, 50.0f, 50.0f, 7, 30, () -> Ingredient.of(SAItems.spaceIngot.get())),
    SUPER_SPACE(1124, 50.0f, 12.0f, 9, 15, () -> Ingredient.of(SAItems.spaceCore.get())),
    //数值无穷表示：Double或Float的POSITIVE_INFINITY（正）或NEGATIVE_INFINITY（负）
    OP(9999, 99.0f, 10.0f, 99, 0, () -> Ingredient.EMPTY);

    private final int maxUses;//耐久
    private final float efficiency;//使用效率
    private final float attackDamage;//工具伤害
    private final int harvestLevel;//工具等级
    private final int enchantability;//附魔等级
    private final LazyLoadedValue<Ingredient> repairMaterial;//修复材料

    SAItemTiers(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
}
