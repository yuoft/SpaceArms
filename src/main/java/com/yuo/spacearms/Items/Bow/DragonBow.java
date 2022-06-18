package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Items.TagsRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class DragonBow extends ModBow {
    public static final Predicate<ItemStack> DRAGON_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.DRAGON_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public DragonBow() {
        super(new Properties().maxDamage(432).group(ModGroup.spaceArms), ItemRegistry.dragonArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return DRAGON_ARROWS;
    }
}
