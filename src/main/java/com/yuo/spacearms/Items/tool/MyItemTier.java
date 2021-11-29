package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum MyItemTier implements IItemTier {
    EMERALD(1432, 7.0f, 3.0f, 3, 12, () -> {
             return Ingredient.fromItems(ItemRegistry.emeraldIngot.get());
    }),
    RUBY(1214, 7.0f, 2.5f, 2, 10, () -> {
        return Ingredient.fromItems(ItemRegistry.rubyIngot.get());
    }),
    SPACE(3124, 16.0f, 8.0f, 6, 20, () -> {
        return Ingredient.fromItems(ItemRegistry.spaceIngot.get());
    }),
    SUPERSPACE(3124, 18.0f, 6.0f, 9, 20, () -> {
        return Ingredient.fromItems(ItemRegistry.spaceCore.get());
    }),
    WOLF(1000, 20.0f, 600.0f, 3, 50, () -> {
        return Ingredient.fromItems(Items.NETHER_STAR);
    }),
    SLIME(150, 5.0f, 0, 0, 0, () -> {
        return Ingredient.fromItems(ItemRegistry.slimeCrystal.get());
    }),
    DRAGON(2365, 14.0f, 6.0f, 5, 18, () -> {
        return Ingredient.fromItems(ItemRegistry.dragonCrystal.get());
    }), //数值无穷表示：Double或Float的POSITIVE_INFINITY（正）或NEGATIVE_INFINITY（负）
    OP(9999, 99.0f, 10.0f, 99, 0, () -> {
        return Ingredient.EMPTY;
    });

    private final int maxUses;//耐久
    private final float efficiency;//使用效率
    private final float attackDamage;//工具伤害
    private final int harvestLevel;//工具等级
    private final int enchantability;//附魔等级
    private final LazyValue<Ingredient> repairMaterial;//修复材料

    MyItemTier(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<Ingredient>(repairMaterial);
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
    }
