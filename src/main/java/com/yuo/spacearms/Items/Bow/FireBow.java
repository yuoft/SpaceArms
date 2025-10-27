package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class FireBow extends ModBow {
    public static final Predicate<ItemStack> FIRE_ARROWS = (stack) -> stack.is(SATags.FIRE_ARROWS);

    public FireBow() {
        super(new Properties().durability(426), SAItems.fireArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return FIRE_ARROWS;
    }
}
