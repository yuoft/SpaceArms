package com.yuo.spacearms.Entity.AI;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;

public class AISkeletonAttackBow extends Goal {
    private final CreatureEntity entity;

    public AISkeletonAttackBow(CreatureEntity entity) {
        this.entity = entity;
    }

    //是否执行ai任务
    @Override
    public boolean shouldExecute() {
        LivingEntity entityLivingBase = entity.world.getClosestPlayer(entity, 16.0D);
        if (entityLivingBase == null) {
            return false;
        } else if (!entityLivingBase.isAlive()) {
            return false;
        } else {
            ItemStack stack = entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            return stack != null && stack.getItem().equals(Items.IRON_SWORD);
        }
    }

    //ai任务
    @Override
    public void tick() {
        LivingEntity entityLivingBase = this.entity.world.getClosestPlayer(entity, 16.0D);
        if (entityLivingBase instanceof PlayerEntity) {
            BlockPos entityPos = entity.getPosition();
            BlockPos playerPos = entityLivingBase.getPosition();
            int i = (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(entityPos.getX() - playerPos.getX()), 2) + Math.pow(Math.abs(entityPos.getZ() - playerPos.getZ()), 2))));
            if (i > 5) {
                this.entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
            }
        }
    }
}

