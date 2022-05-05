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

public class DragonArms extends ArmorItem{

	private static Properties properties = new Properties().maxStackSize(1).group(ModGroup.myGroup);

	public DragonArms(EquipmentSlotType slot) {
		super(MyArmorMaterial.DRAGON, slot, properties);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.dragon_arms"));
	}

	//盔甲在身上时触发效果
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		NonNullList<ItemStack> stacks = player.inventory.armorInventory;
		boolean flag = stacks.size() >= 4;
		for (ItemStack itemStack : stacks) {
			if (itemStack.isEmpty() || !(itemStack.getItem() instanceof DragonArms))
				flag = false;
		}
		//缓降
		if (flag) {
			player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 0, 0));
		}
	}
}
