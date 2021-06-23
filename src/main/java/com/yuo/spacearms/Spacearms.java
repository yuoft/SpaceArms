package com.yuo.spacearms;

import com.yuo.spacearms.Blocks.BlockRegistry;
import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.Items.ItemRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.world.gen.feature.structure.VillagePieces;
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
//        MinecraftForge.EVENT_BUS.register(EventHandler.class);
    }
    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        registerEntityRender(event.getMinecraftSupplier()); //注册客户端渲染
    }
    private void registerEntityRender(Supplier<Minecraft> minecraft){
        ItemRenderer renderer = minecraft.get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DRAGON_CRYSTAL.get(),
                (renderManager) -> new SpriteRenderer<>(renderManager, renderer));

    }
}
