package com.yuo.spacearms.Entity.AI;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class AISkeletonAttackSword extends Goal {
    private final CreatureEntity entity;

    public AISkeletonAttackSword(CreatureEntity entity) {
        this.entity = entity;
    }

    //是否执行ai任务
    @Override
    public boolean shouldExecute() {
        LivingEntity entityLivingBase = entity.world.getClosestPlayer(entity, 3.0D);
        if (entityLivingBase == null) {
            return false;
        } else if (!entityLivingBase.isAlive()) {
            return false;
        } else {
            ItemStack stack = entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            return !stack.getItem().equals(Items.IRON_SWORD);
        }
    }

    //ai任务
    @Override
    public void tick() {//当玩家靠近骷髅3格时，切换武器为剑
        LivingEntity entityLivingBase = this.entity.world.getClosestPlayer(entity, 3.0D);
        if (entityLivingBase instanceof PlayerEntity) {
            this.entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
        }
    }
}

