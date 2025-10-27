package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class GoldBow extends ModBow {
    public static final Predicate<ItemStack> GOLD_ARROWS = (stack) -> stack.is(SATags.GOLD_ARROWS);

    public GoldBow() {
        super(new Properties().durability(384), SAItems.goldArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return GOLD_ARROWS;
    }
}
