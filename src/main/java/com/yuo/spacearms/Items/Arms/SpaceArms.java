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

public class SpaceArms extends ArmorItem {

	public SpaceArms(Type slot) {
		super(SAArmorMaterials.SPACE, slot, new Properties().stacksTo(1).defaultDurability(SAArmorMaterials.SPACE.getDurabilityForType(slot)));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.space_arms"));
	}
	@Override
	public void onArmorTick(ItemStack stack, Level world, Player player) {
		NonNullList<ItemStack> stacks = player.getInventory().armor;
		boolean flag = stacks.size() >= 4;
		for (ItemStack itemStack : stacks) {
			if (itemStack.isEmpty() || !(itemStack.getItem() instanceof SpaceArms))
				flag = false;
		}
		//抗性提升
		if (flag) {
			player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 0, 1));
		}
	}
}
