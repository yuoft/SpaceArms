package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Items.tool.Helper;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceBlock extends OreBlock{

	public SpaceBlock() {
		super(Block.Properties.create((new Material.Builder(MaterialColor.BLACK)).build()).harvestLevel(4).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(40, 100).setRequiresTool());
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.blockInfo.space_block"));
	}

	//实体行走
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity){
			Helper.TP((LivingEntity) entityIn, worldIn);
		}
	}
}
