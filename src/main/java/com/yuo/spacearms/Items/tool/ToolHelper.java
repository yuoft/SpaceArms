package com.yuo.spacearms.Items.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class ToolHelper {
    //传送效果 原版紫颂果
	public static void TP(LivingEntity entityLiving, World worldIn)
	{
        if (!worldIn.isRemote) {
            double d0 = entityLiving.getPosX();
            double d1 = entityLiving.getPosY();
            double d2 = entityLiving.getPosZ();

            for(int i = 0; i < 16; ++i) {
                double d3 = entityLiving.getPosX() + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;
                double d4 = MathHelper.clamp(entityLiving.getPosY() + (double)(entityLiving.getRNG().nextInt(16) - 8), 0.0D, (double)(worldIn.func_234938_ad_() - 1));
                double d5 = entityLiving.getPosZ() + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;
                if (entityLiving.isPassenger()) {
                    entityLiving.stopRiding();
                }

                if (entityLiving.attemptTeleport(d3, d4, d5, true)) {
                    worldIn.playSound(null, d0, d1, d2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entityLiving.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
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
    public static void spawnExp(PlayerEntity player, World world, ItemStack stack, BlockPos pos){
        stack.damageItem(1, player, e -> player.sendBreakAnimation(Hand.MAIN_HAND));
        if (RANDOM.nextInt(100) > 50){//50%额外概率掉落经验
            ExperienceOrbEntity exp = new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), RANDOM.nextInt(5) + 1);
            world.addEntity(exp);
            stack.damageItem(1, player, e -> player.sendBreakAnimation(Hand.MAIN_HAND));
        }
    }
}
