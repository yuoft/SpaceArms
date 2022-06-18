package com.yuo.spacearms.Items;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 通用不会被烧毁的物品
 */
public class NetheriteItem extends Item{

	public NetheriteItem() {
		super(new Properties().group(ModGroup.spaceArms)); //设置物品所在 创造模式物品栏
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (stack.getItem().equals(ItemRegistry.bedrockPowder.get())){
			tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.bedrock_powder"));
		}
	}
}
