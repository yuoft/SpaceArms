package com.yuo.spacearms.Items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SABlockItem extends BlockItem {
    public SABlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        Level world = entity.level();
        Item item = stack.getItem();
        if (!world.isClientSide && item == SAItems.rubyOre.get()){
            BlockPos pos = entity.blockPosition();

            //中间方块为岩浆
            if (world.getBlockState(pos).getBlock() == Blocks.LAVA) {
                for (Direction dir : Direction.values()) {
                    if (dir == Direction.UP) continue;
                    //四周是地狱砖
                    if (world.getBlockState(pos.relative(dir)).getBlock().equals(Blocks.NETHER_BRICKS)) {
                        //生物转化物品
                        if (world instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(ParticleTypes.LAVA, pos.getX(), pos.getY(), pos.getZ(), 50, 0.0, 0D, 0D,0.0);
                            ItemEntity demonicIngotItem = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(SAItems.ruby.get(), stack.getCount()));
                            demonicIngotItem.setDeltaMovement(world.random.nextDouble() / 2.0, 0.1, world.random.nextDouble() / 2.0);
                            world.addFreshEntity(demonicIngotItem);
                            stack.shrink(stack.getCount());
                        }
                    }
                }
            }
        }
        return false;
    }
}
