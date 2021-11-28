package com.yuo.spacearms;

import com.yuo.spacearms.Blocks.BlockRegistry;
import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.Entity.Render.*;
import com.yuo.spacearms.Items.ItemRegistry;
import com.yuo.spacearms.world.OreGen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Supplier;

@Mod("spacearms")
@Mod.EventBusSubscriber(modid = Spacearms.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Spacearms {
	public static final String MODID = "spacearms";
	public Spacearms() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::clientSetup);
		//注册物品至mod总线
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        EntityRegistry.ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGen::generateOres); //注册矿物生成
    }
    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        registerEntityRender(event.getMinecraftSupplier()); //注册客户端渲染
        //物品动态属性注册
        event.enqueueWork(() -> {
            setBowProperty(ItemRegistry.ironBow.get());
            setBowProperty(ItemRegistry.goldBow.get());
            setBowProperty(ItemRegistry.diamondBow.get());
            setBowProperty(ItemRegistry.netheriteBow.get());
            setBowProperty(ItemRegistry.dragonBow.get());
            setBowProperty(ItemRegistry.spaceBow.get());
            setBowProperty(ItemRegistry.enderBow.get());
            setBowProperty(ItemRegistry.fireBow.get());
            setBowProperty(ItemRegistry.iceBow.get());
            setBowProperty(ItemRegistry.amosiBow.get());
            setBowProperty(ItemRegistry.tntBow.get());
            ItemModelsProperties.registerProperty(ItemRegistry.slimeSlingshot.get(), new ResourceLocation(Spacearms.MODID,
                    "time"), (itemStack, clientWorld, livingEntity) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return livingEntity.getActiveItemStack() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getItemInUseCount()) / 20.0F;
                }
            });
            ItemModelsProperties.registerProperty(ItemRegistry.bedrockIngot.get(), new ResourceLocation(Spacearms.MODID,
                    "count"), (itemStack, clientWorld, livingEntity) -> {
                return itemStack.getCount();
            });
        });
    }

    //设置弓物品的动态属性
    private void setBowProperty(Item item){
        ItemModelsProperties.registerProperty(item, new ResourceLocation(Spacearms.MODID,
                "pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItemStack() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getItemInUseCount()) / 20.0F;
            }
        });
        ItemModelsProperties.registerProperty(item, new ResourceLocation(Spacearms.MODID,
                "pulling"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && livingEntity.isHandActive() && livingEntity.getActiveItemStack() == itemStack ? 1.0F : 0.0F;
        });
    }

    private void registerEntityRender(Supplier<Minecraft> minecraft){
        ItemRenderer renderer = minecraft.get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DRAGON_CRYSTAL.get(),
                (renderManager) -> new SpriteRenderer<>(renderManager, renderer)); //投掷物渲染
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.GOLD_TNT.get(), //实体箭渲染
                (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.IRON_ARROW.get(),
                (renderManager) -> new IronArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.GOLD_ARROW.get(),
                (renderManager) -> new GoldArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DIAMOND_ARROW.get(),
                (renderManager) -> new DiamondArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.NETHERITE_ARROW.get(),
                (renderManager) -> new NetheriteArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DRAGON_ARROW.get(),
                (renderManager) -> new DragonArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SPACE_ARROW.get(),
                (renderManager) -> new SpaceArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.ENDER_ARROW.get(),
                (renderManager) -> new EnderArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FIRE_ARROW.get(),
                (renderManager) -> new FireArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.ICE_ARROW.get(),
                (renderManager) -> new IceArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.AMOSI_ARROW.get(),
                (renderManager) -> new AmosiArrowRender(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.AMOSI_BOW_ARROW.get(),
                (renderManager) -> new AmosiArrowRender(renderManager));

    }
}
