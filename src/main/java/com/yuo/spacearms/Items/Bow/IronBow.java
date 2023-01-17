package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class IronBow extends ModBow {
    public static final Predicate<ItemStack> IRON_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(SATags.IRON_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public IronBow() {
        super(new Properties().maxDamage(414).group(ModGroup.spaceArms), SAItems.ironArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return IRON_ARROWS;
    }
}
