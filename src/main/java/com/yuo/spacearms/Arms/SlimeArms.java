package com.yuo.spacearms.Arms;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SlimeArms extends ArmorItem{

	public SlimeArms(EquipmentSlotType slot) {
		super(MyArmorMaterial.SLIME, slot, new Properties().maxStackSize(1).group(ModGroup.spaceArms).defaultMaxDamage(MyArmorMaterial.SLIME.getDurability(slot)));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Item item = stack.getItem();
		if (item.equals(SAItems.slimeFeet.get())){
			tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.slime_feet"));
		}
	}
}
