package com.yuo.spacearms.Items.Arms;

import com.yuo.spacearms.Items.SAArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * 通用普通盔甲注册
 */
public class OrdinaryArms extends ArmorItem {

    public OrdinaryArms(SAArmorMaterials material, Type slot) {
        super(material, slot, new Properties().stacksTo(1).defaultDurability(material.getDurabilityForType(slot)));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
		return (getMaterial() == SAArmorMaterials.SUPER_XRAY || getMaterial() == SAArmorMaterials.ULTRA) || stack.isEnchanted();
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
    }
}
