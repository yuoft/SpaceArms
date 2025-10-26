package com.yuo.spacearms.Items;

import com.yuo.spacearms.SATabs;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 通用不会被烧毁的物品
 */
public class NetheriteItem extends Item {

	public NetheriteItem() {
		super(new Properties()); //设置物品所在 创造模式物品栏
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		if (stack.getItem().equals(SAItems.bedrockPowder.get())){
			components.add(Component.translatable("spacearms.text.itemInfo.bedrock_powder"));
		}
	}
}
