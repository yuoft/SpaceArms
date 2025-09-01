package com.yuo.spacearms;

import com.yuo.spacearms.Items.SAItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

//创造模式物品栏 实例化
public class SATabs extends ItemGroup{
	public static ItemGroup spaceArms = new SATabs();
	public static ItemGroup spaceArms0 = new ItemTab();

	public SATabs() {
		super(ItemGroup.GROUPS.length, "SpaceArms0"); //页码11开始，名称
	}
	//图标
	@Override
	public ItemStack createIcon() {
		return new ItemStack(SAItems.spacePath.get());
	}

	private static class ItemTab extends ItemGroup{
		public ItemTab() {
            super(ItemGroup.GROUPS.length, "SpaceArms1");
        }

		@Override
		public ItemStack createIcon() {
			return new ItemStack(SAItems.opSword.get());
		}
	}
}
