package com.yuo.spacearms.Entity.AI;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AISkeletonAttackSword extends Goal {
    private final Skeleton entity;

    public AISkeletonAttackSword(Skeleton entity) {
        this.entity = entity;
    }

    //是否执行ai任务
    @Override
    public boolean canUse() {
        LivingEntity entityLivingBase = entity.level().getNearestPlayer(entity, 3.0D);
        if (entityLivingBase == null) {
            return false;
        } else if (!entityLivingBase.isAlive()) {
            return false;
        } else {
            ItemStack stack = entity.getItemBySlot(EquipmentSlot.MAINHAND);
            return !stack.getItem().equals(Items.IRON_SWORD);
        }
    }

    //ai任务
    @Override
    public void tick() {//当玩家靠近骷髅3格时，切换武器为剑
        LivingEntity entityLivingBase = this.entity.level().getNearestPlayer(entity, 3.0D);
        if (entityLivingBase instanceof Player) {
            this.entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        }
    }
}

