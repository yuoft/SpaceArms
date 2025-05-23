package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class OrdinaryPickaxe extends PickaxeItem {
	public OrdinaryPickaxe(IItemTier iItemTier) {
		super(iItemTier, 2, -2.8f, new Properties().group(ModGroup.spaceArms));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}
}
