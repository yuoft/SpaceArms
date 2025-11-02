package com.yuo.spacearms.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * 通用不会被烧毁的物品
 */
public class FireImmuneItem extends Item {

	public FireImmuneItem() {
		super(new Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		if (stack.getItem().equals(SAItems.bedrockPowder.get())){
			components.add(Component.translatable("spacearms.text.itemInfo.bedrock_powder"));
		}
	}
}
