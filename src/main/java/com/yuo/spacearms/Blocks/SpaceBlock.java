package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Items.tool.ToolHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.List;

public class SpaceBlock extends Block {

	public SpaceBlock() {
		super(Block.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(100, 1000).requiresCorrectToolForDrops());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable BlockGetter getter, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.blockInfo.space_block"));
	}

	//实体行走
	@Override
	public void entityInside(BlockState pos, Level worldIn, BlockPos blockPos, Entity entityIn) {
		if (entityIn instanceof LivingEntity){
			ToolHelper.TP((LivingEntity) entityIn, worldIn);
		}
	}
}
