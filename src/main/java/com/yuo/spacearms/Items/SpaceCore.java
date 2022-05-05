package com.yuo.spacearms.Items;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

//第一个物品
public class SpaceCore extends Item{

	public SpaceCore() {
		super(new Properties().group(ModGroup.myGroup)); //设置物品所在 创造模式物品栏
	}

}
