package com.yuo.spacearms.Items.tool;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class OrdinaryHoe extends HoeItem {

	public OrdinaryHoe(Tier itemTier) {
		super(itemTier, (int) (-itemTier.getAttackDamageBonus() + 1), 0, new Properties());
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}
}
