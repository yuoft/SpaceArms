package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpPickaxe extends PickaxeItem {
	private ItemHander hander;
	public OpPickaxe() {
		super(MyItemTier.OP, 1, -2.4f, new Properties().group(ModGroup.spaceArms));
		this.hander = new ItemHander();
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)){
			Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
			map.put(Enchantments.FORTUNE, 10);
			ItemStack stack = new ItemStack(this);
			EnchantmentHelper.setEnchantments(map, stack);
			items.add(stack);
		}
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (state.getHarvestTool() == ToolType.PICKAXE){
			return 100.0f;
		}
		return Math.max(super.getDestroySpeed(stack, state), 10.0f);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.aoeBlock"));
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.space_pickaxe"));
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.op_pickaxe"));
		if (stack.hasTag() && stack.getTag().contains("mode")){
			if (stack.getTag().getBoolean("mode"))
				tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.aoe"));
			else tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.unAoe"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemHander.changeMode(worldIn, playerIn, handIn);
		return ActionResult.resultPass(playerIn.getHeldItem(handIn));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		return ItemHander.toolBreakBlock(itemstack, player, pos, hander, 3, ToolType.PICKAXE);
	}
}
