package com.yuo.spacearms.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class XrayBlock extends Block {
	private final int harvestLevel;

	public XrayBlock(int harvestLevel, float hardness, float resistancelln) {
		super(Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.GLASS).strength(hardness, resistancelln)
				.requiresCorrectToolForDrops().forceSolidOn().isViewBlocking((v1, v2, v3) -> false).dynamicShape());
		this.harvestLevel = harvestLevel;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
		if (player.getMainHandItem().getItem() instanceof DiggerItem digger){
			int levelDig = digger.getTier().getLevel();
			if (digger instanceof PickaxeItem){
				return levelDig >= harvestLevel;
			}
		}
		return super.canHarvestBlock(state, level, pos, player);
	}
}
