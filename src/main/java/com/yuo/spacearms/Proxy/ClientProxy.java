package com.yuo.spacearms.Proxy;

import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.Entity.Render.Mob.*;
import com.yuo.spacearms.Entity.SAEntitys;
import com.yuo.spacearms.Entity.Render.*;
import com.yuo.spacearms.Items.Bow.ModBow;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.tool.ModShield;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 客户端属性注册
 */
public class ClientProxy implements IProxy {

    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        registerEntityRender(event.getMinecraftSupplier()); //注册客户端渲染
        RenderTypeLookup.setRenderLayer(SABlocks.xrayBlock.get(), RenderType.getCutout()); //方块透明
        RenderTypeLookup.setRenderLayer(SABlocks.superXrayBlock.get(), RenderType.getCutout());
        //物品动态属性注册
        event.enqueueWork(() -> {
            Collection<RegistryObject<Item>> entries = SAItems.ITEMS.getEntries();
            for (RegistryObject<Item> entry : entries) {
                Item item = entry.get();
                if (item instanceof ModBow){
                    setBowProperty(item);
                }else if (item instanceof ModShield){
                    setShieldProperty(item);
                }
            }
            setSmileShotProperty();
        });
    }

    //盾牌
    private void setShieldProperty(Item item){
        ItemModelsProperties.registerProperty(item, new ResourceLocation(SpaceArms.MOD_ID,
                "blocking"), (itemStack, clientWorld, livingEntity) -> livingEntity != null && livingEntity.isHandActive()
                && livingEntity.getActiveItemStack() == itemStack ? 1.0F : 0.0F);
    }

    //史莱姆弹弓和基岩锭
    private void setSmileShotProperty(){
        ItemModelsProperties.registerProperty(SAItems.bedrockIngot.get(), new ResourceLocation(SpaceArms.MOD_ID,
                "count"), (itemStack, clientWorld, livingEntity) -> itemStack.getCount());
    }

    //设置弓物品的动态属性
    private void setBowProperty(Item item){
        ItemModelsProperties.registerProperty(item, new ResourceLocation(SpaceArms.MOD_ID,
                "pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItemStack() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getItemInUseCount()) / 20.0F;
            }
        });
        ItemModelsProperties.registerProperty(item, new ResourceLocation(SpaceArms.MOD_ID,
                "pulling"), (itemStack, clientWorld, livingEntity)
                -> livingEntity != null && livingEntity.isHandActive() && livingEntity.getActiveItemStack() == itemStack ? 1.0F : 0.0F);
    }

    private void registerEntityRender(Supplier<Minecraft> minecraft){
        ItemRenderer renderer = minecraft.get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.DRAGON_CRYSTAL.get(),
                (renderManager) -> new SpriteRenderer<>(renderManager, renderer)); //投掷物渲染
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.IRON_ARROW.get(),//实体箭渲染
                IronArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.GOLD_ARROW.get(),
                GoldArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.DIAMOND_ARROW.get(),
                DiamondArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.NETHERITE_ARROW.get(),
                NetheriteArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.DRAGON_ARROW.get(),
                DragonArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.SPACE_ARROW.get(),
                SpaceArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.ENDER_ARROW.get(),
                EnderArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.FIRE_ARROW.get(),
                FireArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.ICE_ARROW.get(),
                IceArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.AMOSI_ARROW.get(),
                AmosiArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.AMOSI_BOW_ARROW.get(),
                AmosiArrowRender::new);

        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.GREEN_ZOMBIE.get(), GreenZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.RED_ZOMBIE.get(), RedZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.GREEN_SKELETON.get(), GreenSkeletonRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.RED_SKELETON.get(), RedSkeletonRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.GREEN_SPIDER.get(), GreenSpiderRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.RED_SPIDER.get(), RedSpiderRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.GREEN_CREEPER.get(), GreenCreeperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.RED_CREEPER.get(), RedCreeperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.GREEN_ENDERMAN.get(), GreenEndermanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SAEntitys.RED_ENDERMAN.get(), RedEndermanRender::new);
    }

    @Override
    public void registerHandlers() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::clientSetup);
    }

}
