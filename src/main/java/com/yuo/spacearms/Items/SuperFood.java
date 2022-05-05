package com.yuo.spacearms.Items;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public class SuperFood extends Item {
    public SuperFood(Food food) {
        super(new Properties().group(ModGroup.myGroup).food(food).maxStackSize(32).rarity(Rarity.RARE));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
