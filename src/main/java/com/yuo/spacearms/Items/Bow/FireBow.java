package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class FireBow extends ModBow {
    public static final Predicate<ItemStack> FIRE_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(SATags.FIRE_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public FireBow() {
        super(new Properties().maxDamage(400).group(ModGroup.spaceArms), SAItems.fireArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return FIRE_ARROWS;
    }
}
