package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
//狼的末路
public class WolfSword extends SwordItem{
	public WolfSword() {
		super(SAItemTiers.WOLF, 7, -2.4F, new Item.Properties().group(ModGroup.spaceArms).isImmuneToFire());
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
