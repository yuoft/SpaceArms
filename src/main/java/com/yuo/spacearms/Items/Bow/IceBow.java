package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class IceBow extends ModBow {
    public static final Predicate<ItemStack> ICE_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(SATags.ICE_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public IceBow() {
        super(new Properties().maxDamage(384).group(ModGroup.spaceArms), SAItems.iceArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return ICE_ARROWS;
    }
}
