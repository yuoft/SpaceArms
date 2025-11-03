package com.yuo.spacearms.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ToolActions;

/**
 * 通用普通方块制作
 */
public class OrdinaryBlock extends Block {
	private final int harvestLevel;

	public OrdinaryBlock(MapColor material, int harvestLevel, float hardness, float resistancelln) {
		super(Properties.of().mapColor(material).strength(hardness, resistancelln).requiresCorrectToolForDrops());
		this.harvestLevel = harvestLevel;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
		if (player.getMainHandItem().getItem() instanceof DiggerItem digger){
			int levelDig = digger.getTier().getLevel();
			if (digger instanceof PickaxeItem){
				return levelDig >= harvestLevel;
			}
		}
		return super.canHarvestBlock(state, level, pos, player);
	}
}
