package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class OrdinaryAxe extends AxeItem {
	public OrdinaryAxe(IItemTier iItemTier, int damage, float speed) {
		super(iItemTier, damage, speed, new Properties().group(ModGroup.myGroup));
	}

}
