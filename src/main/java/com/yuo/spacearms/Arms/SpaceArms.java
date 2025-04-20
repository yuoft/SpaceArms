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

public class SpaceArms extends ArmorItem{

	public SpaceArms(EquipmentSlotType slot) {
		super(SAArmorMaterials.SPACE, slot, new Properties().maxStackSize(1).group(ModGroup.spaceArms).defaultMaxDamage(SAArmorMaterials.SPACE.getDurability(slot)));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.space_arms"));
	}
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		NonNullList<ItemStack> stacks = player.inventory.armorInventory;
		boolean flag = stacks.size() >= 4;
		for (ItemStack itemStack : stacks) {
			if (itemStack.isEmpty() || !(itemStack.getItem() instanceof SpaceArms))
				flag = false;
		}
		//抗性提升
		if (flag) {
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 0, 1));
		}
	}
}
