package com.yuo.spacearms.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

//第一个物品
public class SpacePath extends Item {

	public SpacePath() {
		super(new Properties().fireResistant()); //设置物品所在 创造模式物品栏
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.space_path"));
	}

	//使用时间
	@Override
	public int getUseDuration(ItemStack stack) {
		return 16;
	}

	//播放动画
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	//玩家使用完物品时调用
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		if (living instanceof Player player){
			ToolHelper.TP(player, level);
			player.getCooldowns().addCooldown(this, 30);
			stack.setCount(stack.getCount() - 1);
		}
		return stack;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		player.swing(hand);
		return super.use(level, player, hand);
	}
}
