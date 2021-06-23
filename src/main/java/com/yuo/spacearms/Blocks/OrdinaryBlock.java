package com.yuo.spacearms.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用普通方块制作
 */
public class OrdinaryBlock extends Block{

	public OrdinaryBlock(Material material, int harvestLevel, ToolType toolType,float hardness, float resistancelln) {
		super(Properties.create(material).harvestLevel(harvestLevel).harvestTool(toolType)
				.hardnessAndResistance(hardness, resistancelln));
	}
	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder) {
		List<ItemStack> loots = new ArrayList<ItemStack>();
		loots.add(new ItemStack(this, 1));
		return loots;
	}
}
