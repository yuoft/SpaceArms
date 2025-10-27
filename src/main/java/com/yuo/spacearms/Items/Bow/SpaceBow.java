package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import com.yuo.spacearms.SATabs;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

/**
 * 空间弓
 */
public class SpaceBow extends ModBow {
    public static final Predicate<ItemStack> SPACE_ARROWS = (stack) -> stack.is(SATags.SPACE_ARROWS);

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    public SpaceBow() {
        super(new Properties().durability(849), SAItems.spaceArrow.get());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return SPACE_ARROWS;
    }
}
