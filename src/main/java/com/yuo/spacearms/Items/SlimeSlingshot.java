package com.yuo.spacearms.Items;

import com.yuo.spacearms.Event.TickEvents;
import com.yuo.spacearms.Items.tool.MyItemTier;
import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * 史莱姆弹弓
 */
public class SlimeSlingshot extends ToolItem {

	public SlimeSlingshot() {
		super(0,0, MyItemTier.SLIME,null,new Properties().group(ModGroup.spaceArms));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.slime_slingshot"));
	}
	//使用时间
	@Override
	public int getUseDuration(ItemStack stack) {
		return 64;
	}
	//播放动画
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	//玩家停止使用物品时调用
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
		if (entityLiving instanceof PlayerEntity){
			PlayerEntity player = (PlayerEntity) entityLiving;
//			if (player instanceof ServerPlayerEntity) {
//				CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)player, stack);
//			}
			if (!player.isOnGround()) return;
			player.addStat(Stats.ITEM_USED.get(this));
			int i = this.getUseDuration(stack) - timeLeft;
			float f = i / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			f *= 4f;

			if (f > 6f) {
				f = 6f;
			}
			//检查玩家是否瞄准了一个方块
			RayTraceResult mop = rayTrace(worldIn, player, RayTraceContext.FluidMode.NONE);

			if (mop.getType() == RayTraceResult.Type.BLOCK) {
				// 反转玩家朝向
				Vector3d vec = player.getLookVec().normalize();
				float d0 = 1.0f;
				if (player.isSneaking()) {
					if (!worldIn.isRemote) player.sendMessage(new TranslationTextComponent("spacearms.text.info.slime_slingshot"), UUID.randomUUID());
					d0 = 2.0f;
				}
				player.addVelocity(vec.x * -f, (vec.y * -f / 3f) * 1.5f * d0, vec.z * -f);
				player.playSound(SoundEvents.ITEM_CROSSBOW_SHOOT, 1f, 3.0f);
				stack.damageItem(1, player, e -> e.sendBreakAnimation(Hand.MAIN_HAND));
				TickEvents.addBounceHandler(player);
			}
		}
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}
