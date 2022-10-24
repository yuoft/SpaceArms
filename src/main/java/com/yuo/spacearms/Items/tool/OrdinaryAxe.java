package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class OrdinaryAxe extends AxeItem {
	public OrdinaryAxe(IItemTier iItemTier) {
		super(iItemTier, 6f, - 3.0f, new Properties().group(ModGroup.spaceArms));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getTier() == MyItemTier.SUPER_XRAY || getTier() == MyItemTier.ULTRA) || stack.isEnchanted();
	}
}
