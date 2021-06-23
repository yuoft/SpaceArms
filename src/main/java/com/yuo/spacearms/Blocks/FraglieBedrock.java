package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Items.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

public class FraglieBedrock extends OreBlock{

	public FraglieBedrock() {
		super(Properties.create(Material.ROCK).harvestLevel(9).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(150, 1000).lightValue(1));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder) {
		List<ItemStack> loots = new ArrayList<ItemStack>();
		loots.add(new ItemStack(ItemRegistry.bedrockNugget.get(),1));
		return loots;
	}
}
