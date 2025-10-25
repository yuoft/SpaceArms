package com.yuo.spacearms.Entity.AI;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AISkeletonAttackBow extends Goal {
    private final Skeleton entity;

    public AISkeletonAttackBow(Skeleton entity) {
        this.entity = entity;
    }

    //是否执行ai任务
    @Override
    public boolean canUse() {
        LivingEntity entityLivingBase = entity.level().getNearestPlayer(entity, 16.0D);
        if (entityLivingBase == null) {
            return false;
        } else if (!entityLivingBase.isAlive()) {
            return false;
        } else {
            ItemStack stack = entity.getItemBySlot(EquipmentSlot.MAINHAND);
            return stack.isEmpty() && stack.getItem().equals(Items.IRON_SWORD);
        }
    }

    //ai任务
    @Override
    public void tick() {
        LivingEntity entityLivingBase = this.entity.level().getNearestPlayer(entity, 16.0D);
        if (entityLivingBase instanceof Player) {
            BlockPos entityPos = entity.blockPosition();
            BlockPos playerPos = entityLivingBase.blockPosition();
            int i = (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(entityPos.getX() - playerPos.getX()), 2) + Math.pow(Math.abs(entityPos.getZ() - playerPos.getZ()), 2))));
            if (i > 5) {
                this.entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
            }
        }
    }
}

