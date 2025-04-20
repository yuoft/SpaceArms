package com.yuo.spacearms;

import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.Effect.EffectRegistry;
import com.yuo.spacearms.Entity.EntityRegistry;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Proxy.ClientProxy;
import com.yuo.spacearms.Proxy.CommonProxy;
import com.yuo.spacearms.Proxy.IProxy;
import com.yuo.spacearms.world.OreGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("spacearms")
public class SpaceArms {
	public static final String MOD_ID = "spacearms";
    public static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	public SpaceArms() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		//注册物品至mod总线
        SAItems.ITEMS.register(modEventBus);
        SABlocks.BLOCKS.register(modEventBus);
        EntityRegistry.ENTITY_TYPES.register(modEventBus);
        EffectRegistry.EFFECTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGen::generateOres); //注册矿物生成
        proxy.registerHandlers();
    }

}
