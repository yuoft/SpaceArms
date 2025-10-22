package com.yuo.spacearms.Entity.AI;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class AISetBlock extends Goal {
    private final CreatureEntity entity;
    private int tick = 0;

    public AISetBlock(CreatureEntity entity) {
        this.entity = entity;
    }

    //是否执行ai任务
    @Override
    public boolean shouldExecute() {
        LivingEntity entityLivingBase = entity.world.getClosestPlayer(entity, 16.0D);//发现可攻击的非创造玩家
        if (entityLivingBase == null) {
            return false;
        } else if (!entityLivingBase.isAlive()) {
            return false;
        } else {
            BlockPos entityPos = entity.getPosition();
            BlockPos playerPos = entityLivingBase.getPosition();
            int i = (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(entityPos.getX() - playerPos.getX()), 2) + Math.pow(Math.abs(entityPos.getZ() - playerPos.getZ()), 2))));
            return playerPos.getY() > entityPos.getY() + 3 && i < 10;//this.entity.world.getGameRules().getBoolean("mobGriefing");//实体可放置方块
        }
    }

    //ai任务
    @Override
    public void tick() {
        this.tick++;
        if (tick < 20) {
            return;
        }
        PlayerEntity player = this.entity.world.getClosestPlayer(entity, 16.0D);
        BlockPos entityPos = entity.getPosition();
        if (player != null) {
            BlockPos playerPos = player.getPosition();
            BlockPos entityPosUP = new BlockPos(entity.getPosition().getX(), entity.getPosition().getY() + 2, entity.getPosition().getZ());
            if (entityPos.getY() < playerPos.getY() && entity.world.isAirBlock(entityPosUP))//垂直搭方块，直到与玩家高度一致
            {
                BlockState block = Blocks.COBBLESTONE.getDefaultState();
                entity.setLocationAndAngles(entityPos.getX(), entityPos.getY() + 1, entityPos.getZ(), entity.rotationYaw, entity.rotationPitch);
                entity.world.setBlockState(entityPos, block);
            } else if (entityPos.getY() == playerPos.getY())//与玩家高度一致时，水平搭方块
            {
                BlockState block = Blocks.COBBLESTONE.getDefaultState();
                int mobX = entityPos.getX();
                int mobZ = entityPos.getZ();
                int playerX = playerPos.getX();
                int playerZ = playerPos.getZ();
                if (mobX < playerX && mobZ < playerZ)//玩家在mob左下 de
                {
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ + 1);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                } else if (mobX < playerX && mobZ > playerZ)//左上 ab
                {
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ - 1);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                } else if (mobX > playerX && mobZ < playerZ)//右下 df
                {
                    BlockPos pos = new BlockPos(mobX + 1, entityPos.getY() - 1, mobZ);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                } else if (mobX > playerX && mobZ > playerZ)//右上 ac
                {
                    BlockPos pos = new BlockPos(mobX - 1, entityPos.getY() - 1, mobZ);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                } else if (mobX == playerX && mobZ > playerZ)//上
                {
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ - 1);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                } else if (mobX == playerX && mobZ < playerZ)//下
                {
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ + 1);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                } else if (mobX > playerX)//右
                {
                    BlockPos pos = new BlockPos(mobX - 1, entityPos.getY() - 1, mobZ);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                } else if (mobX < playerX)//左
                {
                    BlockPos pos = new BlockPos(mobX + 1, entityPos.getY() - 1, mobZ);
                    if (entity.world.isAirBlock(pos))
                        entity.world.setBlockState(pos, block);
                }
                tick = 0;
            }
        }
    }
}

