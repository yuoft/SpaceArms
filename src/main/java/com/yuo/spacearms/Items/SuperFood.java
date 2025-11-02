package com.yuo.spacearms.Items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class SuperFood extends Item {
    public SuperFood(FoodProperties food) {
        super(new Properties().food(food).stacksTo(32).rarity(Rarity.RARE));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}
