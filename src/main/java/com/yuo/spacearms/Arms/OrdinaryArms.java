package com.yuo.spacearms.Arms;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 通用普通盔甲注册
 */
public class OrdinaryArms extends ArmorItem{

	public OrdinaryArms(MyArmorMaterial material, EquipmentSlotType slot) {
		super(material, slot, new Properties().maxStackSize(1).group(ModGroup.spaceArms).defaultMaxDamage(material.getDurability(slot)));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getArmorMaterial() == MyArmorMaterial.SUPER_XRAY || getArmorMaterial() == MyArmorMaterial.ULTRA) || stack.isEnchanted();
	}

}
