package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class OrdinaryHoe extends PickaxeItem {

	public OrdinaryHoe(IItemTier itemTier) {
		super(itemTier, (int) (-itemTier.getAttackDamage() + 1), 0, new Properties().group(ModGroup.myGroup));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return getTier() == MyItemTier.SUPER_XRAY || getTier() == MyItemTier.ULTRA;
	}
}
