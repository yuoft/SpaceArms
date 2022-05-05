package com.yuo.spacearms.Arms;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 通用普通盔甲注册
 */
public class OrdinaryArms extends ArmorItem{

	private static Properties properties = new Properties().maxStackSize(1).group(ModGroup.myGroup);

	public OrdinaryArms(IArmorMaterial material, EquipmentSlotType slot) {
		super(material, slot, properties);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return getArmorMaterial() == MyArmorMaterial.SUPER_XRAY || getArmorMaterial() == MyArmorMaterial.ULTRA;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	}
}
