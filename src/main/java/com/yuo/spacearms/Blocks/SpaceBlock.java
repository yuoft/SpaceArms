package com.yuo.spacearms.Blocks;

import java.util.ArrayList;
import java.util.List;

import com.yuo.spacearms.Util.Helper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class SpaceBlock extends OreBlock{

	public SpaceBlock() {
		super(Block.Properties.create(Material.ROCK).harvestLevel(4).harvestTool(ToolType.PICKAXE)
				.hardnessAndResistance(40, 100));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.blockInfo.space_block"));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder) {
		List<ItemStack> loots = new ArrayList<ItemStack>();
		loots.add(new ItemStack(this, 1));
		return loots;
	}
	//实体行走
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity){
			Helper.TP((LivingEntity) entityIn, worldIn);
		}
	}
}
