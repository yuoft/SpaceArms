package com.yuo.spacearms.Proxy;

import com.yuo.spacearms.Blocks.SABlocks;
import com.yuo.spacearms.Items.Bow.ModBow;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.tool.ModShield;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

/**
 * 客户端属性注册
 */
public class ClientProxy implements IProxy {

    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(SABlocks.xrayBlock.get(), RenderType.cutout()); //方块透明
        ItemBlockRenderTypes.setRenderLayer(SABlocks.superXrayBlock.get(), RenderType.cutout());
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
        ItemProperties.register(item, RlUtils.fa(SpaceArms.MOD_ID, "blocking"), (itemStack, clientWorld, livingEntity, i) -> livingEntity != null && livingEntity.isUsingItem()
                && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    //史莱姆弹弓和基岩锭
    private void setSmileShotProperty(){
        ItemProperties.register(SAItems.bedrockIngot.get(), RlUtils.fa(SpaceArms.MOD_ID, "count"), (itemStack, clientWorld, livingEntity, i) -> itemStack.getCount());
    }

    //设置弓物品的动态属性
    private void setBowProperty(Item item){
        ItemProperties.register(item, RlUtils.fa(SpaceArms.MOD_ID, "pull"), (itemStack, clientWorld, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getTicksUsingItem()) / 20.0F;
            }
        });
        ItemProperties.register(item, RlUtils.fa(SpaceArms.MOD_ID, "pulling"), (itemStack, clientWorld, livingEntity, i)
                -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    @Override
    public void registerHandlers() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::clientSetup);
    }

}
