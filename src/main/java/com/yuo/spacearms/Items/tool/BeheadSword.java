package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BeheadSword extends SwordItem {
	public BeheadSword() {
		super(SAItemTiers.RUBY, 4, -3.0F, new Properties());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.behead_sword"));
	}

}
