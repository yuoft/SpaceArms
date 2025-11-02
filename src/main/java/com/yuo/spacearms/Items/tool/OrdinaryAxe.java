package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class OrdinaryAxe extends AxeItem {
	public OrdinaryAxe(Tier iItemTier) {
		super(iItemTier, 6f, - 3.0f, new Properties());
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}
}
