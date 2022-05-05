package com.yuo.spacearms.Arms;

import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.SlimeBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SlimeArms extends ArmorItem{

	private static Properties properties = new Properties().maxStackSize(1).group(ModGroup.myGroup);

	public SlimeArms(EquipmentSlotType slot) {
		super(MyArmorMaterial.SLIME, slot, properties);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Item item = stack.getItem();
		if (item.equals(ItemRegistry.slimeFeet.get())){
			tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.slime_feet"));
		}
	}
}
