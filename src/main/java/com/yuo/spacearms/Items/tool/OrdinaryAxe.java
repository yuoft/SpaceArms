package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.SATabs;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class OrdinaryAxe extends AxeItem {
	public OrdinaryAxe(IItemTier iItemTier) {
		super(iItemTier, 6f, - 3.0f, new Properties().group(SATabs.spaceArms0));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}
}
