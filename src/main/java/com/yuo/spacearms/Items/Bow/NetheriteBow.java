package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.Items.TagsRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import java.util.function.Predicate;

public class NetheriteBow extends ModBow {
    public static final Predicate<ItemStack> NETHERITE_ARROWS = (stack) -> {
        ITag<Item> tag = ItemTags.getCollection().get(TagsRegistry.NETHERITE_ARROWS);
        return stack.getItem().isIn(tag);
    };

    public NetheriteBow() {
        super(new Properties().maxDamage(524).group(ModGroup.myGroup), ItemRegistry.netheriteArrow.get());
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return NETHERITE_ARROWS;
    }
}
