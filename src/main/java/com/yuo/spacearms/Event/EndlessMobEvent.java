package com.yuo.spacearms.Event;

import com.yuo.spacearms.Entity.Mob.*;
import com.yuo.spacearms.Entity.SAEntitys;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = SpaceArms.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EndlessMobEvent {

    //注册刷怪蛋
    @SubscribeEvent
    public static void onRegisterEntities(RegisterEvent event) {
//        ModSpawnEgg.initSpawnEggs();
    }

    //实体属性
    @SubscribeEvent
    public static void onRegisterEntitiesAttr(EntityAttributeCreationEvent event) {
        event.put(SAEntitys.GREEN_ZOMBIE.get(), GreenZombie.setCustomAttributes().build());
        event.put(SAEntitys.RED_ZOMBIE.get(), RedZombie.setCustomAttributes().build());
        event.put(SAEntitys.GREEN_SKELETON.get(), GreenSkeleton.setCustomAttributes().build());
        event.put(SAEntitys.RED_SKELETON.get(), RedSkeleton.setCustomAttributes().build());
        event.put(SAEntitys.GREEN_SPIDER.get(), GreenSpider.setCustomAttributes().build());
        event.put(SAEntitys.RED_SPIDER.get(), RedSpider.setCustomAttributes().build());
        event.put(SAEntitys.GREEN_CREEPER.get(), GreenCreeper.setCustomAttributes().build());
        event.put(SAEntitys.RED_CREEPER.get(), RedCreeper.setCustomAttributes().build());
        event.put(SAEntitys.GREEN_ENDERMAN.get(), GreenEnderMan.setCustomAttributes().build());
        event.put(SAEntitys.RED_ENDERMAN.get(), RedEnderMan.setCustomAttributes().build());
    }
}
