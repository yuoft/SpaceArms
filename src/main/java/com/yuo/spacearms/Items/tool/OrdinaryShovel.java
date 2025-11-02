package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class OrdinaryShovel extends ShovelItem {

	public OrdinaryShovel(Tier itemTier) {
		super(itemTier, 3, -3f, new Properties());
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}
}
