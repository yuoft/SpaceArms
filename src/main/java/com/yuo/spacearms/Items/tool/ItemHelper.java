package com.yuo.spacearms.Items.tool;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemHelper {

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
