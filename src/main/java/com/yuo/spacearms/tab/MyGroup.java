package com.yuo.spacearms.tab;

import com.yuo.spacearms.Items.ItemRegistry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
//创造模式物品栏
public class MyGroup extends ItemGroup{

	public MyGroup() {
		super(12, "SpaceArms"); //页码11开始，名称
	}
	//图标
	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemRegistry.rubyIngot.get());
	}

}
