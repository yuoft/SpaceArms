package com.yuo.spacearms.Items.Bow;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.SATags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

/**
 * 阿莫斯之弓
 */
public class AmosiBow extends ModBow {
    public static final Predicate<ItemStack> AMOSI_ARROWS = (stack) -> stack.is(SATags.AMOSI_ARROWS);

    public AmosiBow() {
        super(new Properties().durability(987), SAItems.amosiArrow.get());
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return AMOSI_ARROWS;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }
}
