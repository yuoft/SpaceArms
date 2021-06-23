package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class SpacePickaxe extends PickaxeItem {

	private ItemHander hander;

	public SpacePickaxe() {
		super(MyItemTier.SPACE, 3, -2.4f, new Properties().group(ModGroup.myGroup));
		this.hander = new ItemHander(this);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.aoeBlock"));
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.space_pickaxe"));
	}
	//切换工具模式 开启或关闭范围挖掘
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemHander.changeMode(worldIn, playerIn, handIn);
		return ActionResult.resultPass(playerIn.getHeldItem(handIn));
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		return ItemHander.toolBreakBlock(itemstack, player, pos, hander, 1);
	}
}
