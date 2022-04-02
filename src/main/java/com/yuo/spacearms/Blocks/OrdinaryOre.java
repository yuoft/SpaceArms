package com.yuo.spacearms.Blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

/**
 * 通用普通方块制作
 */
public class OrdinaryOre extends OreBlock {

	public OrdinaryOre(Material material, int harvestLevel, ToolType toolType, float hardness, float resistancelln) {
		super(Properties.create(material).harvestLevel(harvestLevel).harvestTool(toolType)
				.hardnessAndResistance(hardness, resistancelln).setRequiresTool());
	}
}
