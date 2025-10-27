package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class NetheriteBow extends ModBow {
    public static final Predicate<ItemStack> NETHERITE_ARROWS = (stack) -> {
        return stack.is(SATags.NETHERITE_ARROWS);
    };

    public NetheriteBow() {
        super(new Properties().durability(643), SAItems.netheriteArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return NETHERITE_ARROWS;
    }
}
