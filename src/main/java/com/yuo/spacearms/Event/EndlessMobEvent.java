package com.yuo.spacearms.Event;

import com.yuo.spacearms.Entity.Mob.*;
import com.yuo.spacearms.Entity.SAEntitys;
import com.yuo.spacearms.Items.ModSpawnEgg;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpaceArms.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EndlessMobEvent {

    //注册刷怪蛋
    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        
        ModSpawnEgg.initSpawnEggs();
    }

    //实体属性
    @SubscribeEvent
    public static void onRegisterEntitiesAttr(EntityAttributeCreationEvent event) {
        event.put(SAEntitys.GREEN_ZOMBIE.get(), GreenZombie.setCustomAttributes().create());
        event.put(SAEntitys.RED_ZOMBIE.get(), RedZombie.setCustomAttributes().create());
        event.put(SAEntitys.GREEN_SKELETON.get(), GreenSkeleton.setCustomAttributes().create());
        event.put(SAEntitys.RED_SKELETON.get(), RedSkeleton.setCustomAttributes().create());
        event.put(SAEntitys.GREEN_SPIDER.get(), GreenSpider.setCustomAttributes().create());
        event.put(SAEntitys.RED_SPIDER.get(), RedSpider.setCustomAttributes().create());
        event.put(SAEntitys.GREEN_CREEPER.get(), GreenCreeper.setCustomAttributes().create());
        event.put(SAEntitys.RED_CREEPER.get(), RedCreeper.setCustomAttributes().create());
        event.put(SAEntitys.GREEN_ENDERMAN.get(), GreenEnderman.setCustomAttributes().create());
        event.put(SAEntitys.RED_ENDERMAN.get(), RedEnderman.setCustomAttributes().create());
    }
}
