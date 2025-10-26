package com.yuo.spacearms.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * 通用普通物品
 */
public class OrdinaryItem extends Item {

	public OrdinaryItem() {
		super(new Properties()); //设置物品所在 创造模式物品栏
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, level, components, flag);
	}
}
