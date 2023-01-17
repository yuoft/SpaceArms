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
 * 空间弓
 */
public class SpaceBow extends ModBow {
    public static final Predicate<ItemStack> SPACE_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(SATags.SPACE_ARROWS);
        return stack.getItem().isIn(tag);
    };

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public SpaceBow() {
        super(new Properties().maxDamage(849).group(ModGroup.spaceArms), SAItems.spaceArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return SPACE_ARROWS;
    }
}
