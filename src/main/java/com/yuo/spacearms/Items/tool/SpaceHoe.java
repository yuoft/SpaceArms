package com.yuo.spacearms.Items.tool;

import com.mojang.datafixers.util.Pair;
import com.yuo.spacearms.Items.ItemHander;
import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SpaceHoe extends HoeItem {

	private final ItemHander handler;

	public SpaceHoe() {
		super(SAItemTiers.SPACE, -3, 0, new Properties().fireResistant());
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
		BlockState toolModifiedState = level.getBlockState(blockpos).getToolModifiedState(context, ToolActions.HOE_TILL, false);
		Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null : Pair.of((ctx) -> true, changeIntoState(toolModifiedState));
		if (pair == null) {
			return InteractionResult.PASS;
		} else {
			Predicate<UseOnContext> predicate = pair.getFirst();
			Consumer<UseOnContext> consumer = pair.getSecond();
			if (predicate.test(context)) {
				Player player = context.getPlayer();
				level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (!level.isClientSide) {
					if (context.getItemInHand().getOrCreateTag().getBoolean("mode")){
						level.setBlock(blockpos, toolModifiedState, 11);
					}else {
						Iterable<BlockPos> allInBoxMutable = BlockPos.betweenClosed(blockpos.offset(-1, 0, -1), blockpos.offset(1, 0, 1));
						for (BlockPos pos : allInBoxMutable) {
							BlockState blockState = level.getBlockState(pos).getToolModifiedState(context, ToolActions.HOE_TILL, false);
							if (blockState != null) {
								level.setBlock(pos, blockState, 11);
							}
						}
					}
					consumer.accept(context);
					if (player != null) {
						context.getItemInHand().hurtAndBreak(1, player, (p_150845_) -> {
							p_150845_.broadcastBreakEvent(context.getHand());
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
