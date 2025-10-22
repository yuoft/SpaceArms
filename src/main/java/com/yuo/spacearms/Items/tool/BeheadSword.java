package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.SATabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BeheadSword extends SwordItem{
	public BeheadSword() {
		super(SAItemTiers.RUBY, 4, -3.0F, new Properties().group(SATabs.spaceArms0));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.behead_sword"));
	}

}
