package com.yuo.spacearms.Blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class XrayBlock extends Block{

	public XrayBlock(int harvestLevel, ToolType toolType, float hardness, float resistancelln) {
		super(Properties.create(Material.GLASS).sound(SoundType.GLASS).harvestLevel(harvestLevel).harvestTool(toolType)
				.hardnessAndResistance(hardness, resistancelln).setRequiresTool().setBlocksVision((bs, br, bp) -> false).variableOpacity());
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
}
