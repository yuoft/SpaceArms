package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class DiamondBow extends ModBow {
    public static final Predicate<ItemStack> DIAMOND_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(SATags.DIAMOND_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public DiamondBow() {
        super(new Properties().maxDamage(412).group(ModGroup.spaceArms), SAItems.diamondArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return DIAMOND_ARROWS;
    }
}
