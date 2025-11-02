package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.ItemHander;
import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class SpacePickaxe extends PickaxeItem {

	private final ItemHander handler;

	public SpacePickaxe() {
		super(SAItemTiers.SPACE, 2, -2.8f, new Properties().fireResistant());
		this.handler = new ItemHander();
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		ItemHander.addInfo(stack, components);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (stack.isCorrectToolForDrops(state)){
			return 50.0f;
		}
		return Math.max(super.getDestroySpeed(stack, state), 6.0f);
	}

	//切换工具模式 开启或关闭范围挖掘
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return ItemHander.changeMode(level, player, hand);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		return ItemHander.toolBreakBlock(itemstack, player, pos, handler, 1);
	}
}
