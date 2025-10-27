package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class DragonBow extends ModBow {
    public static final Predicate<ItemStack> DRAGON_ARROWS = (stack) -> stack.is(SATags.DRAGON_ARROWS);

    public DragonBow() {
        super(new Properties().durability(715), SAItems.dragonArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return DRAGON_ARROWS;
    }
}
