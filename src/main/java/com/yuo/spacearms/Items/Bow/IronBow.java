package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class IronBow extends ModBow {
    public static final Predicate<ItemStack> IRON_ARROWS = (stack) -> stack.is(SATags.IRON_ARROWS);

    public IronBow() {
        super(new Properties().durability(414), SAItems.ironArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return IRON_ARROWS;
    }
}
