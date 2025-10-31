package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.SATabs;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SuperSpacePickaxe extends PickaxeItem {

	public SuperSpacePickaxe() {
		super(SAItemTiers.SUPER_SPACE, 2, -2.4f, new Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.super_space_pickaxe"));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
}
