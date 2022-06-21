package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Items.TagsRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class GoldBow extends ModBow {
    public static final Predicate<ItemStack> GOLD_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.GOLD_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public GoldBow() {
        super(new Properties().maxDamage(384).group(ModGroup.spaceArms), ItemRegistry.goldArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return GOLD_ARROWS;
    }
}
