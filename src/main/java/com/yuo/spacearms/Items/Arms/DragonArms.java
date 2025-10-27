package com.yuo.spacearms.Items.Arms;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class DragonArms extends ArmorItem {

	public DragonArms(Type slot) {
		super(SAArmorMaterials.DRAGON, slot, new Properties().stacksTo(1).defaultDurability(SAArmorMaterials.DRAGON.getDurabilityForType(slot)));
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.dragon_arms"));
	}

	//盔甲在身上时触发效果
	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {
		NonNullList<ItemStack> stacks = player.getInventory().armor;
		boolean flag = stacks.size() >= 4;
		for (ItemStack itemStack : stacks) {
			if (itemStack.isEmpty() || !(itemStack.getItem() instanceof DragonArms))
				flag = false;
		}
		//缓降
		if (flag) {
			player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 0, 0));
		}
	}
}
