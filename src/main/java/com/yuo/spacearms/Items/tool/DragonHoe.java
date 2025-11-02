package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import com.yuo.spacearms.Items.ToolHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class DragonHoe extends HoeItem {

	public DragonHoe() {
		super(SAItemTiers.DRAGON, -3, 0, new Properties());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.dragon_tool"));
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		mineBlockExp(stack, worldIn, state, pos, entityLiving);
		return super.mineBlock(stack, worldIn, state, pos, entityLiving);
	}

	public static void mineBlockExp(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (!worldIn.isClientSide){
			if (entityLiving instanceof Player player && stack.isCorrectToolForDrops(state)){
				ToolHelper.spawnExp(player, worldIn, stack, pos);
			}
		}
	}
}
