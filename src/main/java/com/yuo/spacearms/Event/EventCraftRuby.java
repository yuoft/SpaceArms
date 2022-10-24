package com.yuo.spacearms.Event;

import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Spacearms;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 部分源码参考更多实用设备2
 */
@Mod.EventBusSubscriber(modid = Spacearms.MOD_ID)
public class EventCraftRuby {
    static List<ItemEntity> rubyServer = new ArrayList<>();
    static List<ItemEntity> rubyClient = new ArrayList<>();

    @SubscribeEvent
    public static void onJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ItemEntity) {
            ItemStack item = ((ItemEntity) entity).getItem();
            if (!item.isEmpty() && item.getItem() == SAItems.rubyOre.get()) {
                if (event.getWorld().isRemote)
                    rubyClient.add((ItemEntity) entity);
                else
                    rubyServer.add((ItemEntity) entity);
            }
        }
    }

    @SubscribeEvent
    public static void run(TickEvent.ClientTickEvent event) {
        handleIngots(rubyServer);
    }

    @SubscribeEvent
    public static void run(TickEvent.ServerTickEvent event) {
        handleIngots(rubyClient);
    }

    private static void handleIngots(List<ItemEntity> ruby) {
        for (Iterator<ItemEntity> iterator = ruby.iterator(); iterator.hasNext(); ) {
            ItemEntity item = iterator.next();
            if (!item.isAlive()) {
                iterator.remove();
                continue;
            }
            ItemStack rawStack = item.getItem();
            if (rawStack.isEmpty()) {
                continue;
            }
            if (rawStack.getItem() != SAItems.rubyOre.get()) {
                iterator.remove();
                continue;
            }
            //检测结构
            World world = item.world;
            BlockPos pos = item.getPosition();
            boolean found = false;

            //中间方块为岩浆
            if (world.getBlockState(pos).getMaterial() == Material.LAVA) {
                for (Direction dir : Direction.values()) {
                    if (dir == Direction.UP) continue;
                    //四周是地狱砖
                    if (!world.getBlockState(pos.offset(dir)).getBlock().equals(Blocks.NETHER_BRICKS)) {
                        found = true;
                        break;
                    }
                }
            }
            //结构正确
            if (found) {
                continue;
            }
            //生物转化物品
            if (!world.isRemote && world instanceof ServerWorld) {
				ServerWorld worldServer = (ServerWorld) world;
                worldServer.spawnParticle(ParticleTypes.LAVA, pos.getX(), pos.getY(), pos.getZ(), 100, 0.0, 0D, 0D,0.0);
				item.remove();
                ItemEntity demonicIngotItem = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(SAItems.rubyIngot.get(), rawStack.getCount()));
               	demonicIngotItem.addVelocity(world.rand.nextDouble() / 2.0, 0.1, world.rand.nextDouble() / 2.0);
                world.addEntity(demonicIngotItem);
                iterator.remove();
            }

        }
    }
}
