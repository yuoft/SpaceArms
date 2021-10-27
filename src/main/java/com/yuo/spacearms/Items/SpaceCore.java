package com.yuo.spacearms.Items;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.Item;

//第一个物品
public class SpaceCore extends Item{

	public SpaceCore() {
		super(new Properties().group(ModGroup.myGroup)); //设置物品所在 创造模式物品栏
	}

}
