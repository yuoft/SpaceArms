package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceHoe extends HoeItem {

	private final ItemHander handler;

	public SpaceHoe() {
		super(SAItemTiers.SPACE, -3, 0, new Properties().group(ModGroup.spaceArms).isImmuneToFire());
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

	//切换工具模式 开启或关闭范围挖掘
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		return ItemHander.changeMode(worldIn, playerIn, handIn);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		return ItemHander.toolBreakBlock(itemstack, player, pos, handler, 1, ToolType.HOE);
	}

	@Override
	public boolean canHarvestBlock(BlockState blockIn) {
		int i = this.getTier().getHarvestLevel();
		if (blockIn.getHarvestTool() == ToolType.HOE) {
			return i >= blockIn.getHarvestLevel();
		}
		return false;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
		if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
		if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
			BlockState blockstate = world.getBlockState(blockpos).getToolModifiedState(world, blockpos, context.getPlayer(), context.getItem(), net.minecraftforge.common.ToolType.HOE);
			if (blockstate != null) {
				PlayerEntity playerentity = context.getPlayer();
				world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					if (context.getItem().getOrCreateTag().getBoolean("mode")){
						world.setBlockState(blockpos, blockstate, 11);
					}else {
						Iterable<BlockPos> allInBoxMutable = BlockPos.getAllInBoxMutable(blockpos.add(-1, 0, -1), blockpos.add(1, 0, 1));
						for (BlockPos pos : allInBoxMutable) {
							BlockState blockState = world.getBlockState(pos).getToolModifiedState(world, pos, context.getPlayer(), context.getItem(), net.minecraftforge.common.ToolType.HOE);
							if (blockState != null) {
								world.setBlockState(pos, blockState, 11);
							}
						}
					}
					if (playerentity != null) {
						context.getItem().damageItem(1, playerentity, (player) -> {
							player.sendBreakAnimation(context.getHand());
						});
					}
				}

				return ActionResultType.func_233537_a_(world.isRemote);
			}
		}
		return ActionResultType.PASS;
	}
}
