package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.SATabs;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.units.qual.C;

import javax.annotation.Nullable;
import java.util.List;

public class OrdinarySword extends SwordItem {
	public OrdinarySword(Tier iItemTier) {
		super(iItemTier, 4, - 2.4f, new Properties());
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		Item item = stack.getItem();
		if (item.equals(SAItems.totemSword.get())){
			components.add(Component.translatable("spacearms.text.itemInfo.totem_sword"));
		}
		if (item.equals(SAItems.glowstoneSword.get())){
			components.add(Component.translatable("spacearms.text.itemInfo.glowstone_sword"));
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		Item item = stack.getItem();
		if (item.equals(SAItems.totemSword.get())){
			if (target.getMobType() == MobType.UNDEAD)
				target.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 10, 0));
			else target.addEffect(new MobEffectInstance(MobEffects.HARM, 10, 0));
			return true;
		}
		if (item.equals(SAItems.glowstoneSword.get())){
			target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0));
			return true;
		}
		return super.hurtEnemy(stack, target, attacker);
	}
}
