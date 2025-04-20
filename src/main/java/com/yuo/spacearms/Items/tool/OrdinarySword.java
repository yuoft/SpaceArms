package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class OrdinarySword extends SwordItem{
	public OrdinarySword(IItemTier iItemTier) {
		super(iItemTier, 4, - 2.4f, new Properties().group(ModGroup.spaceArms));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (getTier() == SAItemTiers.SUPER_XRAY || getTier() == SAItemTiers.ULTRA) || stack.isEnchanted();
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Item item = stack.getItem();
		if (item.equals(SAItems.totemSword.get())){
			tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.totem_sword"));
		}
		if (item.equals(SAItems.glowstoneSword.get())){
			tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.glowstone_sword"));
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		Item item = stack.getItem();
		if (item.equals(SAItems.totemSword.get())){
			if (target.isEntityUndead())
				target.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 10, 0));
			else target.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 10, 0));
			return true;
		}
		if (item.equals(SAItems.glowstoneSword.get())){
			target.addPotionEffect(new EffectInstance(Effects.GLOWING, 40, 0));
			return true;
		}
		return true;
	}
}
