package com.yuo.spacearms.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class DragonOre extends OreBlock {
    public DragonOre() {
        super(Block.Properties.create(Material.ROCK).harvestLevel(4).harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(30, 100).setRequiresTool());
    }

    @Override
    protected int getExperience(Random rand) {
        return MathHelper.nextInt(rand, 4, 8);
    }

}
