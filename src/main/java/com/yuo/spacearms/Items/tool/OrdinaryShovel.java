package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class OrdinaryShovel extends PickaxeItem {

	public OrdinaryShovel(IItemTier itemTier) {
		super(itemTier, 3, -3f, new Properties().group(ModGroup.spaceArms));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return getTier() == MyItemTier.SUPER_XRAY || getTier() == MyItemTier.ULTRA;
	}
}
