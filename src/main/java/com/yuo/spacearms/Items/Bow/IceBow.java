package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class IceBow extends ModBow {
    public static final Predicate<ItemStack> ICE_ARROWS = (stack) -> stack.is(SATags.ICE_ARROWS);

    public IceBow() {
        super(new Properties().durability(454), SAItems.iceArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ICE_ARROWS;
    }
}
