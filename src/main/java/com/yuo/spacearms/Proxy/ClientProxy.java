package com.yuo.spacearms.Proxy;

import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.Entity.EntityRegistry;
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
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DRAGON_CRYSTAL.get(),
                (renderManager) -> new SpriteRenderer<>(renderManager, renderer)); //投掷物渲染
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.GOLD_TNT.get(), //实体箭渲染
                (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.IRON_ARROW.get(),
                IronArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.GOLD_ARROW.get(),
                GoldArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DIAMOND_ARROW.get(),
                DiamondArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.NETHERITE_ARROW.get(),
                NetheriteArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DRAGON_ARROW.get(),
                DragonArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SPACE_ARROW.get(),
                SpaceArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.ENDER_ARROW.get(),
                EnderArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FIRE_ARROW.get(),
                FireArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.ICE_ARROW.get(),
                IceArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.AMOSI_ARROW.get(),
                AmosiArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.AMOSI_BOW_ARROW.get(),
                AmosiArrowRender::new);

    }

    @Override
    public void registerHandlers() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::clientSetup);
    }

}
