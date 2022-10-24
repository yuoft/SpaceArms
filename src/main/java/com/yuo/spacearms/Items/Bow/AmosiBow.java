package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

/**
 * 阿莫斯之弓
 */
public class AmosiBow extends ModBow {
    public static final Predicate<ItemStack> AMOSI_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(SATags.AMOSI_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public AmosiBow() {
        super(new Properties().maxDamage(666).group(ModGroup.spaceArms), SAItems.amosiArrow.get());
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return AMOSI_ARROWS;
    }
}
