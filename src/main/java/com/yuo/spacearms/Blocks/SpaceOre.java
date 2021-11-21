package com.yuo.spacearms.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class SpaceOre extends OreBlock{

	public SpaceOre() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(5).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(35, 100).setLightLevel(e ->2).setRequiresTool().setRequiresTool());
	}

	@Override
	protected int getExperience(Random rand) {
		return MathHelper.nextInt(rand, 5, 10);
	}

}
