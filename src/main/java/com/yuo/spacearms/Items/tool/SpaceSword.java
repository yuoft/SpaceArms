package com.yuo.spacearms.Items.tool;

import com.yuo.spacearms.Items.SAItemTiers;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpaceSword extends SwordItem {
	public SpaceSword() {
		super(SAItemTiers.SPACE, 4, -2.4F, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag flag) {
		components.add(Component.translatable("spacearms.text.itemInfo.space_sword"));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	//使用时间
	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
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
			if (!player.isCreative()){
				int experienceLevel = player.experienceLevel;
				if (experienceLevel < 5){
					if (!level.isClientSide)
						player.sendSystemMessage(Component.translatable("spacearms.text.info.space_sword"));
					return stack;
				}
				player.experienceLevel = experienceLevel - 5;
			}
			if (player instanceof ServerPlayer playerEntity) {
				CriteriaTriggers.CONSUME_ITEM.trigger(playerEntity, stack);
				player.awardStat(Stats.ITEM_USED.get(this));
				BlockPos pos = playerEntity.getRespawnPosition();
				if (!level.isClientSide && pos != null){
					//传送前生成粒子
					for (int i = 0; i < 10; i++)
						level.addParticle(ParticleTypes.PORTAL, player.getX() + 0.5 , player.getY(), player.getZ() + 0.5, level.random.nextGaussian() * 0.005D, level.random.nextGaussian() * 0.005D, level.random.nextGaussian() * 0.005D);
					//如果玩家不在主世界，则先将玩家传回主世界
//					if (!player.)
//						player.changeDimension((ServerLevel) level);
					player.teleportTo(pos.getX(), pos.getY(), pos.getZ()); //传送玩家回出生点
					level.playSound(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
					player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F); //播放音效
					stack.hurtAndBreak(30, player, e -> player.broadcastBreakEvent(InteractionHand.MAIN_HAND));
					//传送后生成粒子
					for (int i = 0; i < 10; i++)
						level.addParticle(ParticleTypes.PORTAL, player.getX() + 0.5, player.getY(), player.getZ() + 0.5, level.random.nextGaussian() * 0.005D, level.random.nextGaussian() * 0.005D, level.random.nextGaussian() * 0.005D);
					player.getCooldowns().addCooldown(this, 1200); //进入一分钟的冷却时间
				}
			}
		}
		return stack;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		player.startUsingItem(hand);
		return super.use(level, player, hand);
	}
}
