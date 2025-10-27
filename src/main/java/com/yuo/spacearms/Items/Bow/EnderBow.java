package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class EnderBow extends ModBow {
    public static final Predicate<ItemStack> ENDER_ARROWS = (stack) -> stack.is(SATags.ENDER_ARROWS);

    public EnderBow() {
        super(new Properties().durability(432), SAItems.enderArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ENDER_ARROWS;
    }


}
