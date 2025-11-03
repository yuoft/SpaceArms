package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Items.ToolHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.List;

public class SpaceBlock extends Block {

	public SpaceBlock() {
		super(Block.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(100, 1000).requiresCorrectToolForDrops());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable BlockGetter getter, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.blockInfo.space_block"));
	}

	//实体行走
	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof LivingEntity living){
			ToolHelper.TP(living, level);
		}
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
		if (player.getMainHandItem().getItem() instanceof DiggerItem digger){
			int levelDig = digger.getTier().getLevel();
			if (digger instanceof PickaxeItem){
				return levelDig >= 7;
			}
		}
		return super.canHarvestBlock(state, level, pos, player);
	}
}
