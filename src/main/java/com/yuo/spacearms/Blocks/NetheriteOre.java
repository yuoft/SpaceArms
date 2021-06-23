package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Items.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NetheriteOre extends OreBlock{

	public NetheriteOre() {
		super(Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(25, 100));
	}
	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder) {
		List<ItemStack> loots = new ArrayList<ItemStack>();
		loots.add(new ItemStack(this, 1));
		return loots;
	}
}
