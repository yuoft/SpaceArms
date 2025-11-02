package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

//狼的末路
public class WolfSword extends SwordItem {
	public WolfSword() {
		super(SAItemTiers.WOLF, 7, -2.4F, new Item.Properties().fireResistant());
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
}
