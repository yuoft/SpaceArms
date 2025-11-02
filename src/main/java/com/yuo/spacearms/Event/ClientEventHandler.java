package com.yuo.spacearms.Event;

import com.yuo.spacearms.Client.Render.Arrow.*;
import com.yuo.spacearms.Client.Render.Mob.*;
import com.yuo.spacearms.Entity.SAEntitys;
import com.yuo.spacearms.Items.ModSpawnEgg;
import com.yuo.spacearms.Items.SAItems;
import com.yuo.spacearms.Items.ShieldType;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import moze_intel.projecte.rendering.EntitySpriteRenderer;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SpaceArms.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    public static final ResourceLocation IRON_SHIELD_TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/iron_shield");
    public static final ResourceLocation IRON_SHIELD_TEXTURE_NOPATTERN = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/iron_shield_nopattern");
    public static final ResourceLocation GOLD_SHIELD_TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/gold_shield");
    public static final ResourceLocation GOLD_SHIELD_TEXTURE_NOPATTERN = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/gold_shield_nopattern");
    public static final ResourceLocation DIAMOND_SHIELD_TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/diamond_shield");
    public static final ResourceLocation DIAMOND_SHIELD_TEXTURE_NOPATTERN = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/diamond_shield_nopattern");
    public static final ResourceLocation NETHERITE_SHIELD_TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/netherite_shield");
    public static final ResourceLocation NETHERITE_SHIELD_TEXTURE_NOPATTERN = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/netherite_shield_nopattern");
    public static final ResourceLocation OBSIDIAN_SHIELD_TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/obsidian_shield");
    public static final ResourceLocation OBSIDIAN_SHIELD_TEXTURE_NOPATTERN = RlUtils.fa(SpaceArms.MOD_ID, "entity/shield/obsidian_shield_nopattern");

    public static Material getShieldTexture(ShieldType type, boolean flag) {
        return switch (type) {
            case IRON -> getRenderMaterial(flag ? IRON_SHIELD_TEXTURE : IRON_SHIELD_TEXTURE_NOPATTERN);
            case GOLD -> getRenderMaterial(flag ? GOLD_SHIELD_TEXTURE : GOLD_SHIELD_TEXTURE_NOPATTERN);
            case DIAMOND -> getRenderMaterial(flag ? DIAMOND_SHIELD_TEXTURE : DIAMOND_SHIELD_TEXTURE_NOPATTERN);
            case NETHERITE -> getRenderMaterial(flag ? NETHERITE_SHIELD_TEXTURE : NETHERITE_SHIELD_TEXTURE_NOPATTERN);
            case OBSIDIAN -> getRenderMaterial(flag ? OBSIDIAN_SHIELD_TEXTURE : OBSIDIAN_SHIELD_TEXTURE_NOPATTERN);
        };
    }

    private static Material getRenderMaterial(ResourceLocation resourceLocation){
      return new Material(Sheets.SHIELD_SHEET, resourceLocation);
    }

    //盾牌贴图
    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(new ModelLayerLocation(IRON_SHIELD_TEXTURE, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(IRON_SHIELD_TEXTURE_NOPATTERN, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(GOLD_SHIELD_TEXTURE, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(GOLD_SHIELD_TEXTURE_NOPATTERN, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(DIAMOND_SHIELD_TEXTURE, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(DIAMOND_SHIELD_TEXTURE_NOPATTERN, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(NETHERITE_SHIELD_TEXTURE, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(NETHERITE_SHIELD_TEXTURE_NOPATTERN, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(OBSIDIAN_SHIELD_TEXTURE, "main"), ShieldModel::createLayer);
        event.registerLayerDefinition(new ModelLayerLocation(OBSIDIAN_SHIELD_TEXTURE_NOPATTERN, "main"), ShieldModel::createLayer);
    }

    //实体渲染注册
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //投掷物渲染
        event.registerEntityRenderer(SAEntitys.DRAGON_CRYSTAL.get(), (renderManager) -> new EntitySpriteRenderer<>(renderManager, RlUtils.fa(SpaceArms.MOD_ID, "entity/dragon_crystal")));
        event.registerEntityRenderer(SAEntitys.IRON_ARROW.get(), IronArrowRender::new);//实体箭渲染
        event.registerEntityRenderer(SAEntitys.GOLD_ARROW.get(), GoldArrowRender::new);
        event.registerEntityRenderer(SAEntitys.DIAMOND_ARROW.get(), DiamondArrowRender::new);
        event.registerEntityRenderer(SAEntitys.NETHERITE_ARROW.get(), NetheriteArrowRender::new);
        event.registerEntityRenderer(SAEntitys.DRAGON_ARROW.get(), DragonArrowRender::new);
        event.registerEntityRenderer(SAEntitys.SPACE_ARROW.get(), SpaceArrowRender::new);
        event.registerEntityRenderer(SAEntitys.ENDER_ARROW.get(), EnderArrowRender::new);
        event.registerEntityRenderer(SAEntitys.FIRE_ARROW.get(), FireArrowRender::new);
        event.registerEntityRenderer(SAEntitys.ICE_ARROW.get(), IceArrowRender::new);
        event.registerEntityRenderer(SAEntitys.AMOSI_ARROW.get(), AmosiArrowRender::new);
        event.registerEntityRenderer(SAEntitys.AMOSI_BOW_ARROW.get(), AmosiArrowRender::new);

        event.registerEntityRenderer(SAEntitys.GREEN_ZOMBIE.get(), GreenZombieRender::new); //渲染实体
        event.registerEntityRenderer(SAEntitys.RED_ZOMBIE.get(), RedZombieRender::new);
        event.registerEntityRenderer(SAEntitys.GREEN_SKELETON.get(), GreenSkeletonRender::new);
        event.registerEntityRenderer(SAEntitys.RED_SKELETON.get(), RedSkeletonRender::new);
        event.registerEntityRenderer(SAEntitys.GREEN_SPIDER.get(), GreenSpiderRender::new);
        event.registerEntityRenderer(SAEntitys.RED_SPIDER.get(), RedSpiderRender::new);
        event.registerEntityRenderer(SAEntitys.GREEN_CREEPER.get(), GreenCreeperRender::new);
        event.registerEntityRenderer(SAEntitys.RED_CREEPER.get(), RedCreeperRender::new);
        event.registerEntityRenderer(SAEntitys.GREEN_ENDERMAN.get(), GreenEndermanRender::new);
        event.registerEntityRenderer(SAEntitys.RED_ENDERMAN.get(), RedEndermanRender::new);
    }

    //染色
    @SubscribeEvent
    public static void itemColors(RegisterColorHandlersEvent.Item event) {
        for (RegistryObject<Item> entry : SAItems.ITEMS.getEntries()) {
            Item item = entry.get();
            if (item instanceof ModSpawnEgg)
                event.getItemColors().register(ModSpawnEgg::getColor, item);
        }
    }
}
