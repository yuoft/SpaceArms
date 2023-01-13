package com.yuo.spacearms.Items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SABlockItem extends BlockItem {
    public SABlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        World world = entity.world;
        Item item = stack.getItem();
        if (!world.isRemote && item == SAItems.rubyOre.get()){
            BlockPos pos = entity.getPosition();

            //中间方块为岩浆
            if (world.getBlockState(pos).getMaterial() == Material.LAVA) {
                for (Direction dir : Direction.values()) {
                    if (dir == Direction.UP) continue;
                    //四周是地狱砖
                    if (world.getBlockState(pos.offset(dir)).getBlock().equals(Blocks.NETHER_BRICKS)) {
                        //生物转化物品
                        if (world instanceof ServerWorld) {
                            ServerWorld worldServer = (ServerWorld) world;
                            worldServer.spawnParticle(ParticleTypes.LAVA, pos.getX(), pos.getY(), pos.getZ(), 50, 0.0, 0D, 0D,0.0);
                            ItemEntity demonicIngotItem = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(SAItems.rubyIngot.get(), stack.getCount()));
                            demonicIngotItem.addVelocity(world.rand.nextDouble() / 2.0, 0.1, world.rand.nextDouble() / 2.0);
                            world.addEntity(demonicIngotItem);
                            stack.shrink(stack.getCount());
                        }
                    }
                }
            }
        }
        return false;
    }
}
