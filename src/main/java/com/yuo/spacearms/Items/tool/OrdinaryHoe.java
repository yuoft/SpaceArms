package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.SATabs;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class OrdinaryHoe extends HoeItem {

	public OrdinaryHoe(IItemTier itemTier) {
		super(itemTier, (int) (-itemTier.getAttackDamage() + 1), 0, new Properties().group(SATabs.spaceArms0));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}
}
