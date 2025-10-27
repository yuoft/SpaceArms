package com.yuo.spacearms.Items.Arms;

import com.yuo.spacearms.SATabs;
import net.minecraft.client.renderer.EffectInstance;
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

public class TotemArms extends ArmorItem {

	public TotemArms(Type slot) {
		super(SAArmorMaterials.TOTEM, slot, new Properties().stacksTo(1).defaultDurability(SAArmorMaterials.TOTEM.getDurabilityForType(slot)));
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.totem_arms"));
	}
	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {
		NonNullList<ItemStack> stacks = player.getInventory().armor;
		boolean flag = stacks.size() >= 4;
		for (ItemStack itemStack : stacks) {
			if (itemStack.isEmpty() || !(itemStack.getItem() instanceof TotemArms))
				flag = false;
		}
		//生命恢复
		if (flag) {
			player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 0, 0));
		}
	}
}
