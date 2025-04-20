package com.yuo.spacearms.Arms;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

/**
 * 通用普通盔甲注册
 */
public class OrdinaryArms extends ArmorItem{

	public OrdinaryArms(SAArmorMaterials material, EquipmentSlotType slot) {
		super(material, slot, new Properties().maxStackSize(1).group(ModGroup.spaceArms).defaultMaxDamage(material.getDurability(slot)));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getArmorMaterial() == SAArmorMaterials.SUPER_XRAY || getArmorMaterial() == SAArmorMaterials.ULTRA) || stack.isEnchanted();
	}

}
