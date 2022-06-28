package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class OrdinaryHoe extends HoeItem {

	public OrdinaryHoe(IItemTier itemTier) {
		super(itemTier, (int) (-itemTier.getAttackDamage() + 1), 0, new Properties().group(ModGroup.spaceArms));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getTier() == MyItemTier.SUPER_XRAY || getTier() == MyItemTier.ULTRA) || stack.isEnchanted();
	}
}
