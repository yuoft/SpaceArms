package com.yuo.spacearms.Arms;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GlowstoneArms extends ArmorItem{

	private static final Properties properties = new Properties().maxStackSize(1).group(ModGroup.spaceArms);

	public GlowstoneArms(EquipmentSlotType slot) {
		super(MyArmorMaterial.GLOWSTONE, slot, properties);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.glowstone_arms"));
	}
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		NonNullList<ItemStack> stacks = player.inventory.armorInventory;
		boolean flag = stacks.size() >= 4;
		//未装备4件
		for (ItemStack itemStack : stacks) {
			if (itemStack.isEmpty() || !(itemStack.getItem() instanceof GlowstoneArms)) //不是当前盔甲
				flag = false;
		}
		//夜视
		if (flag) {
			player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 0));
		}
	}
}
