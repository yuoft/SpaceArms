package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.ItemHander;
import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraftforge.common.ToolActions;

import java.util.List;

public class SpaceShovel extends ShovelItem {

	private final ItemHander handler;

	public SpaceShovel() {
		super(SAItemTiers.SPACE, -6, -3.0f, new Properties().fireResistant());
		this.handler = new ItemHander();
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		ItemHander.addInfo(stack, components);
	}

	//切换工具模式 开启或关闭范围挖掘
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return ItemHander.changeMode(level, player, hand);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		return ItemHander.toolBreakBlock(itemstack, player, pos, handler, 1);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		if (context.getClickedFace() == Direction.DOWN) {
			return InteractionResult.PASS;
		} else {
			Player player = context.getPlayer();
			BlockState blockstate1 = blockstate.getToolModifiedState(context, ToolActions.SHOVEL_FLATTEN, false);
			BlockState blockstate2 = null;
			if (blockstate1 != null && level.isEmptyBlock(blockpos.above())) {
				level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
				blockstate2 = blockstate1;
			} else if (blockstate.getBlock() instanceof CampfireBlock && (Boolean)blockstate.getValue(CampfireBlock.LIT)) {
				if (!level.isClientSide()) {
					level.levelEvent((Player)null, 1009, blockpos, 0);
				}

				CampfireBlock.dowse(context.getPlayer(), level, blockpos, blockstate);
				blockstate2 = (BlockState)blockstate.setValue(CampfireBlock.LIT, false);
			}

			if (blockstate2 != null) {
				if (!level.isClientSide) {
					if (context.getItemInHand().getOrCreateTag().getBoolean("mode")){
						level.setBlock(blockpos, blockstate2, 11);
					}else { //正常形态使用才能范围更改
						Iterable<BlockPos> allInBoxMutable = BlockPos.betweenClosed(blockpos.offset(-1, 0, -1), blockpos.offset(1, 0, 1));
						for (BlockPos pos : allInBoxMutable) {
							BlockState state = level.getBlockState(pos);
							if (state.getBlock() instanceof GrassBlock && level.getBlockState(pos.above()).isAir()){ //当前方块是草方块，并且上方是空气
								level.setBlock(pos, blockstate2, 11);
							}
						}
					}
					level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, Context.of(player, blockstate2));
					if (player != null) {
						context.getItemInHand().hurtAndBreak(1, player, (p_43122_) -> {
							p_43122_.broadcastBreakEvent(context.getHand());
						});
					}
				}

				return InteractionResult.sidedSuccess(level.isClientSide);
			} else {
				return InteractionResult.PASS;
			}
		}
	}
}
