package com.yuo.spacearms.Blocks;

import com.yuo.spacearms.Items.ToolHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof LivingEntity living){
			ToolHelper.TP(living, level);
		}
	}

	@Override
	public boolean canEntityDestroy(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
		return super.canEntityDestroy(state, level, pos, entity);
	}
}
