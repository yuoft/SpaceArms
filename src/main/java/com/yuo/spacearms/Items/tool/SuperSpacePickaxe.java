package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.SATabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SuperSpacePickaxe extends PickaxeItem {

	public SuperSpacePickaxe() {
		super(SAItemTiers.SUPER_SPACE, 2, -2.4f, new Properties().group(SATabs.spaceArms0).isImmuneToFire());
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.super_space_pickaxe"));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
