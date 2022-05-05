package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Items.TagsRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class DiamondBow extends ModBow {
    public static final Predicate<ItemStack> DIAMOND_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.DIAMOND_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public DiamondBow() {
        super(new Properties().maxDamage(412).group(ModGroup.myGroup), ItemRegistry.diamondArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return DIAMOND_ARROWS;
    }
}
