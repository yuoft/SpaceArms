package com.yuo.spacearms.Blocks;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.material.MapColor;

/**
 * 通用普通方块制作
 */
public class OrdinaryOre extends DropExperienceBlock {
	public OrdinaryOre(MapColor mapColor, int harvestLevel, float hardness, float resistancelln, IntProvider exp) {
		super(Properties.of().mapColor(mapColor).strength(hardness, resistancelln).requiresCorrectToolForDrops(), exp);
	}
}
