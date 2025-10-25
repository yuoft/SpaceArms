package com.yuo.spacearms.Entity.AI;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AISetBlock extends Goal {
    private final Zombie entity;
    private int tick = 0;

    public AISetBlock(Zombie entity) {
        this.entity = entity;
    }

    //是否执行ai任务
    @Override
    public boolean canUse() {
        LivingEntity entityLivingBase = entity.level().getNearestPlayer(entity, 16.0D);//发现可攻击的非创造玩家
        if (entityLivingBase == null) {
            return false;
        } else if (!entityLivingBase.isAlive()) {
            return false;
        } else {
            BlockPos entityPos = entity.blockPosition();
            BlockPos playerPos = entityLivingBase.blockPosition();
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
        Level level = this.entity.level();
        Player player = level.getNearestPlayer(entity, 16.0D);
        BlockPos entityPos = entity.blockPosition();
        if (player != null) {
            BlockPos playerPos = player.blockPosition();
            BlockPos entityPosUP = new BlockPos(entity.blockPosition().getX(), entity.blockPosition().getY() + 2, entity.blockPosition().getZ());
            if (entityPos.getY() < playerPos.getY() && level.getBlockState(entityPosUP).isAir()) { //垂直搭方块，直到与玩家高度一致
                BlockState block = Blocks.COBBLESTONE.defaultBlockState();
                entity.setDeltaMovement(entityPos.getX(), entityPos.getY() + 1, entityPos.getZ());
                level.setBlockAndUpdate(entityPos, block);
            } else if (entityPos.getY() == playerPos.getY()) { //与玩家高度一致时，水平搭方块
                BlockState block = Blocks.COBBLESTONE.defaultBlockState();
                int mobX = entityPos.getX();
                int mobZ = entityPos.getZ();
                int playerX = playerPos.getX();
                int playerZ = playerPos.getZ();
                if (mobX < playerX && mobZ < playerZ) { //玩家在mob左下 de
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ + 1);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                } else if (mobX < playerX && mobZ > playerZ) { //左上 ab
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ - 1);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                } else if (mobX > playerX && mobZ < playerZ) { //右下 df
                    BlockPos pos = new BlockPos(mobX + 1, entityPos.getY() - 1, mobZ);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                } else if (mobX > playerX && mobZ > playerZ) { //右上 ac
                    BlockPos pos = new BlockPos(mobX - 1, entityPos.getY() - 1, mobZ);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                } else if (mobX == playerX && mobZ > playerZ) { //上
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ - 1);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                } else if (mobX == playerX && mobZ < playerZ) { //下
                    BlockPos pos = new BlockPos(mobX, entityPos.getY() - 1, mobZ + 1);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                } else if (mobX > playerX) { //右
                    BlockPos pos = new BlockPos(mobX - 1, entityPos.getY() - 1, mobZ);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                } else if (mobX < playerX) { //左
                    BlockPos pos = new BlockPos(mobX + 1, entityPos.getY() - 1, mobZ);
                    if (level.getBlockState(pos).isAir())
                        level.setBlockAndUpdate(pos, block);
                }
                tick = 0;
            }
        }
    }
}

