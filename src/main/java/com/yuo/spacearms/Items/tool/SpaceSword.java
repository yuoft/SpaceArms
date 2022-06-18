package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.tab.ModGroup;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class SpaceSword extends SwordItem{
	public SpaceSword() {
		super(MyItemTier.SPACE, 4, -2.4F, new Item.Properties().group(ModGroup.spaceArms).isImmuneToFire());
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("spacearms.text.itemInfo.space_sword"));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	//使用时间
	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}
	//播放动画
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}
	//玩家使用完物品时调用
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if (entityLiving instanceof PlayerEntity){
			PlayerEntity player = (PlayerEntity) entityLiving;
			if (!player.isCreative()){
				int level = player.experienceLevel;
				if (level < 5){
					if (!worldIn.isRemote)
						player.sendMessage(new TranslationTextComponent("spacearms.text.info.space_sword"), UUID.randomUUID());
					return stack;
				}
				player.experienceLevel = level - 5;
			}
			if (player instanceof ServerPlayerEntity) {
				ServerPlayerEntity playerEntity = (ServerPlayerEntity) player;
				CriteriaTriggers.CONSUME_ITEM.trigger(playerEntity, stack);
				player.addStat(Stats.ITEM_USED.get(this));
				BlockPos pos = playerEntity.func_241140_K_();
				if (pos != null && !worldIn.isRemote){
					//传送前生成粒子
					for (int i = 0; i < 10; i++)
						worldIn.addParticle(ParticleTypes.PORTAL, player.getPosX() + 0.5 , player.getPosY(), player.getPosZ() + 0.5, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
					//如果玩家不在主世界，则先将玩家传回主世界
					if (!playerEntity.func_241141_L_().equals(worldIn.getDimensionKey()))
						player.changeDimension((ServerWorld) worldIn);
					player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ()); //传送玩家回出生点
					worldIn.playSound(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
					player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F); //播放音效
					stack.damageItem(30, player, e -> player.sendBreakAnimation(Hand.MAIN_HAND));
					//传送后生成粒子
					for (int i = 0; i < 10; i++)
						worldIn.addParticle(ParticleTypes.PORTAL, player.getPosX() + 0.5, player.getPosY(), player.getPosZ() + 0.5, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
					player.getCooldownTracker().setCooldown(this, 1200); //进入一分钟的冷却时间
				}
			}
		}
		return stack;
	}
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}
