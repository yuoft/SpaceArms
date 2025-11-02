package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class OrdinaryPickaxe extends PickaxeItem {
	public OrdinaryPickaxe(Tier iItemTier) {
		super(iItemTier, 2, -2.8f, new Properties());
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}
}
