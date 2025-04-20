package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceAxe extends AxeItem {

	private final ItemHander handler;

	public SpaceAxe() {
		super(SAItemTiers.SPACE, 6, -3.0f, new Properties().group(ModGroup.spaceArms).isImmuneToFire());
		this.handler = new ItemHander();
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		ItemHander.addInfo(stack, tooltip);
	}

	@Override
	public boolean canHarvestBlock(BlockState blockIn) {
		int i = this.getTier().getHarvestLevel();
		if (blockIn.getHarvestTool() == ToolType.AXE) {
			return i >= blockIn.getHarvestLevel();
		}
		return false;
	}

	//切换工具模式 开启或关闭范围挖掘
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		return ItemHander.changeMode(worldIn, playerIn, handIn);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		return ItemHander.toolBreakBlock(itemstack, player, pos, handler, 1, ToolType.AXE);
	}
}
