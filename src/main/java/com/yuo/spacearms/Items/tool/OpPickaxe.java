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

public class OpPickaxe extends PickaxeItem {
	private final ItemHander handler;
	public OpPickaxe() {
		super(SAItemTiers.OP, 1, -2.4f, new Properties());
		this.handler = new ItemHander();
	}

//	@Override
//	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
//		if (this.isInGroup(group)){
//			Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
//			map.put(Enchantments.FORTUNE, 10);
//			ItemStack stack = new ItemStack(this);
//			EnchantmentHelper.setEnchantments(map, stack);
//			items.add(stack);
//		}
//	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (stack.isCorrectToolForDrops(state)){
			return 100.0f;
		}
		return Math.max(super.getDestroySpeed(stack, state), 10.0f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("spacearms.text.itemInfo.aoeBlock"));
		tooltip.add(Component.translatable("spacearms.text.itemInfo.op_pickaxe"));
		if (stack.hasTag() && stack.getOrCreateTag().contains("mode")){
			if (stack.getOrCreateTag().getBoolean("mode"))
				tooltip.add(Component.translatable("spacearms.text.itemInfo.aoe"));
			else tooltip.add(Component.translatable("spacearms.text.itemInfo.unAoe"));
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemHander.changeMode(worldIn, playerIn, handIn);
		return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}


	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		return ItemHander.toolBreakBlock(itemstack, player, pos, handler, 3);
	}
}
