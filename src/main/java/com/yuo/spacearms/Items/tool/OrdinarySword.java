package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class OrdinarySword extends SwordItem{
	public OrdinarySword(IItemTier iItemTier, int damage, float speed) {
		super(iItemTier, damage, speed, new Properties().group(ModGroup.myGroup));
	}

}
