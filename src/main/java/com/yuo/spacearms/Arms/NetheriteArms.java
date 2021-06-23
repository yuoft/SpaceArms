package com.yuo.spacearms.Arms;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

/**
 * 通用普通盔甲注册
 */
public class NetheriteArms extends ArmorItem{

	private static Properties properties = new Properties().maxStackSize(1).group(ModGroup.myGroup);

	public NetheriteArms(IArmorMaterial material, EquipmentSlotType slot) {
		super(material, slot, properties);
	}

}
