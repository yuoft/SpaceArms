package com.yuo.spacearms.Blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class FraglieBedrock extends OreBlock{

	public FraglieBedrock() {
		super(Properties.create(Material.ROCK).harvestLevel(9).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(150, 1000).setLightLevel(e ->1).setRequiresTool());
	}

}
