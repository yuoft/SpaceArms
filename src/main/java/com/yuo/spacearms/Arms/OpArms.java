package com.yuo.spacearms.Arms;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class OpArms extends ArmorItem{

	private static Properties properties = new Properties().maxStackSize(1).group(ModGroup.myGroup);

	public OpArms(EquipmentSlotType slot) {
		super(MyArmorMaterial.OP, slot, properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
