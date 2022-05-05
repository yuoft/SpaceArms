package com.yuo.spacearms.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class EmeraldIngotOre extends OreBlock{

	public EmeraldIngotOre() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(7, 7).setLightLevel(e -> 5).setRequiresTool());
	}
	@Override
	protected int getExperience(Random rand) {
		return MathHelper.nextInt(rand, 3, 6);
	}

}
