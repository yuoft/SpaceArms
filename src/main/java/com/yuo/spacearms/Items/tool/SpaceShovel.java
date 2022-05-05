package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceShovel extends ShovelItem {

	private ItemHander hander;

	public SpaceShovel() {
		super(MyItemTier.SPACE, -6, -3.0f, new Properties().group(ModGroup.myGroup));
		this.hander = new ItemHander();
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
		if (blockIn.getHarvestTool() == ToolType.SHOVEL) {
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
		return ItemHander.toolBreakBlock(itemstack, player, pos, hander, 1, ToolType.SHOVEL);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = world.getBlockState(blockpos);
		if (context.getFace() == Direction.DOWN) {
			return ActionResultType.PASS;
		} else {
			PlayerEntity playerentity = context.getPlayer();
			BlockState blockstate1 = blockstate.getToolModifiedState(world, blockpos, playerentity, context.getItem(), net.minecraftforge.common.ToolType.SHOVEL);
			BlockState blockstate2 = null;
			if (blockstate1 != null && world.isAirBlock(blockpos.up())) {
				world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
				blockstate2 = blockstate1;
			} else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT)) {
				if (!world.isRemote()) {
					world.playEvent(null, 1009, blockpos, 0);
				}

				CampfireBlock.extinguish(world, blockpos, blockstate);
				blockstate2 = blockstate.with(CampfireBlock.LIT, Boolean.FALSE);
			}

			if (blockstate2 != null) {
				if (!world.isRemote) {
					if (context.getItem().getOrCreateTag().getBoolean("mode")){
						world.setBlockState(blockpos, blockstate2, 11);
					}else { //正常形态使用才能范围更改
						Iterable<BlockPos> allInBoxMutable = BlockPos.getAllInBoxMutable(blockpos.add(-1, 0, -1), blockpos.add(1, 0, 1));
						for (BlockPos pos : allInBoxMutable) {
							BlockState state = world.getBlockState(pos);
							if (state.getBlock() instanceof GrassBlock && world.isAirBlock(pos.up())){ //当前方块是草方块，并且上方是空气
								world.setBlockState(pos, blockstate2, 11);
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
			} else {
				return ActionResultType.PASS;
			}
		}
	}
}
