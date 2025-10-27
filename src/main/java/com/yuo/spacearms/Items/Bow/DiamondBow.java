package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class DiamondBow extends ModBow {
    public static final Predicate<ItemStack> DIAMOND_ARROWS = (stack) -> stack.is(SATags.DIAMOND_ARROWS);

    public DiamondBow() {
        super(new Properties().durability(576), SAItems.diamondArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return DIAMOND_ARROWS;
    }
}
