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

public class EmeraldIngotOre extends OreBlock{

	public EmeraldIngotOre() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(6, 6).lightValue(5));
	}
	@Override
	protected int getExperience(Random rand) {
		return MathHelper.nextInt(rand, 3, 6);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder) {
		List<ItemStack> loots = new ArrayList<ItemStack>();
		loots.add(new ItemStack(ItemRegistry.emeraldIngot.get(), RANDOM.nextInt(3) + 1));
		return loots;
	}
}
