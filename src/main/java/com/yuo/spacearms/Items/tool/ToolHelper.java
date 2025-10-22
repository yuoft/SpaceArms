package com.yuo.spacearms.Items.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ToolHelper {
    //传送效果 原版紫颂果
	public static void TP(LivingEntity entityLiving, Level worldIn)
	{
        if (!worldIn.isClientSide) {
            double d0 = entityLiving.getX();
            double d1 = entityLiving.getY();
            double d2 = entityLiving.getZ();

            for(int i = 0; i < 16; ++i) {
                double d3 = entityLiving.getX() + (entityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
                double d4 = Mth.clamp(entityLiving.getY() + (double)(entityLiving.getRandom().nextInt(16) - 8), 0.0D, (double)(worldIn.getMinBuildHeight() - 1));
                double d5 = entityLiving.getZ() + (entityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (entityLiving.isPassenger()) {
                    entityLiving.stopRiding();
                }

                Vec3 vec3 = entityLiving.position();
                worldIn.gameEvent(GameEvent.TELEPORT, vec3, Context.of(entityLiving));
                if (entityLiving.randomTeleport(d3, d4, d5, true)) {
                    worldIn.playSound(null, d0, d1, d2, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    entityLiving.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
                }
            }
        }
	}

    private static final Random RANDOM = new Random();

    /**
     * 龙晶工具挖掘时额外掉落经验
     * @param player 玩家
     * @param world 世界
     * @param stack 工具
     * @param pos 坐标
     */
    public static void spawnExp(Player player, Level world, ItemStack stack, BlockPos pos){
        stack.setDamageValue(1);
        if (RANDOM.nextInt(100) > 50){//50%额外概率掉落经验
            ExperienceOrb exp = new ExperienceOrb(world, pos.getX(), pos.getY(), pos.getZ(), RANDOM.nextInt(5) + 1);
            world.addFreshEntity(exp);
            stack.setDamageValue(1);
        }
    }
}
