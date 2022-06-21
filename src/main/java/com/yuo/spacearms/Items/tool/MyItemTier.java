package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum MyItemTier implements IItemTier {
    EMERALD(1332, 9.0f, 3.0f, 3, 10, () -> {
             return Ingredient.fromItems(ItemRegistry.emeraldIngot.get());
    }),
    RUBY(1214, 8.5f, 2.5f, 3, 10, () -> {
        return Ingredient.fromItems(ItemRegistry.rubyIngot.get());
    }),
    SPACE(3124, 30.0f, 20.0f, 7, 30, () -> {
        return Ingredient.fromItems(ItemRegistry.spaceIngot.get());
    }),
    SUPERSPACE(3124, 15.0f, 10.0f, 9, 15, () -> {
        return Ingredient.fromItems(ItemRegistry.spaceCore.get());
    }),
    WOLF(233, 20.0f, 600f, 3, 0, () -> {
        return Ingredient.fromItems(Items.NETHER_STAR);
    }),
    SLIME(150, 5.0f, 0, 0, 0, () -> {
        return Ingredient.fromItems(ItemRegistry.slimeCrystal.get());
    }),
    XRAY(1679, 11.0f, 5, 4, 12, () -> {
        return Ingredient.fromItems(ItemRegistry.xrayIngot.get());
    }),
    BH3(886, 11.0f, 15, 3, 10, () -> {
        return Ingredient.fromItems(ItemRegistry.jiejing.get());
    }),
    SUPER_XRAY(2647, 17.0f, 9, 5, 16, () -> {
        return Ingredient.fromItems(ItemRegistry.superXrayIngot.get());
    }),
    SUPER(2105, 13.0f, 6, 4, 14, () -> {
        return Ingredient.fromItems(ItemRegistry.superIngot.get());
    }),
    ULTRA(2967, 19.0f, 10, 6, 20, () -> {
        return Ingredient.fromItems(ItemRegistry.ultraIngot.get());
    }),
    DRAGON(2365, 15f, 8, 5, 15, () -> {
        return Ingredient.fromItems(ItemRegistry.dragonCrystal.get());
    }), //数值无穷表示：Double或Float的POSITIVE_INFINITY（正）或NEGATIVE_INFINITY（负）
    OP(9999, 99.0f, 10.0f, 99, 0, () -> Ingredient.EMPTY);

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
