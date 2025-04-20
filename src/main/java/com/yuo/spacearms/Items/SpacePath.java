package com.yuo.spacearms.Items;

import com.yuo.spacearms.Items.tool.ToolHelper;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

//第一个物品
public class SpacePath extends Item{

	public SpacePath() {
		super(new Properties().group(ModGroup.spaceArms)); //设置物品所在 创造模式物品栏
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.space_path"));
	}

	//使用时间
	@Override
	public int getUseDuration(ItemStack stack) {
		return 16;
	}

	//播放动画
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	//玩家使用完物品时调用
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if (entityLiving instanceof PlayerEntity){
			PlayerEntity player = (PlayerEntity) entityLiving;
			ToolHelper.TP(entityLiving, worldIn);
			player.getCooldownTracker().setCooldown(this, 30);
			stack.setCount(stack.getCount() - 1);
		}
		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}
