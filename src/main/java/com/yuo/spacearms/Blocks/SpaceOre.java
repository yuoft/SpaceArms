package com.yuo.spacearms.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yuo.spacearms.Items.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraftforge.common.ToolType;

public class SpaceOre extends OreBlock{

	public SpaceOre() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(5).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(35, 100).lightValue(2));
	}

	@Override
	protected int getExperience(Random rand) {
		return MathHelper.nextInt(rand, 5, 10);
	}

}
