package com.yuo.spacearms.Blocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class XrayBlock extends Block {

	public XrayBlock(int harvestLevel, float hardness, float resistancelln) {
		super(Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.GLASS).strength(hardness, resistancelln)
				.requiresCorrectToolForDrops().noOcclusion());
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
}
