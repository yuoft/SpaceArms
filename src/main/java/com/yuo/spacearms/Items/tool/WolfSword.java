package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
//狼的末路
public class WolfSword extends SwordItem{
	public WolfSword() {
		super(MyItemTier.WOLF, 7, -2.4F, new Item.Properties().group(ModGroup.myGroup));
	}

}
