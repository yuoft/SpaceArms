package com.yuo.spacearms.Event;

import com.yuo.spacearms.Items.tool.ShieldType;
import com.yuo.spacearms.Spacearms;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Spacearms.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    public static final ResourceLocation IRON_SHIELD_TEXTURE = new ResourceLocation(Spacearms.MOD_ID, "entity/iron_shield");
    public static final ResourceLocation IRON_SHIELD_TEXTURE_NOPATTERN = new ResourceLocation(Spacearms.MOD_ID, "entity/iron_shield_nopattern");
    public static final ResourceLocation GOLD_SHIELD_TEXTURE = new ResourceLocation(Spacearms.MOD_ID, "entity/gold_shield");
    public static final ResourceLocation GOLD_SHIELD_TEXTURE_NOPATTERN = new ResourceLocation(Spacearms.MOD_ID, "entity/gold_shield_nopattern");
    public static final ResourceLocation DIAMOND_SHIELD_TEXTURE = new ResourceLocation(Spacearms.MOD_ID, "entity/diamond_shield");
    public static final ResourceLocation DIAMOND_SHIELD_TEXTURE_NOPATTERN = new ResourceLocation(Spacearms.MOD_ID, "entity/diamond_shield_nopattern");
    public static final ResourceLocation NETHERITE_SHIELD_TEXTURE = new ResourceLocation(Spacearms.MOD_ID, "entity/netherite_shield");
    public static final ResourceLocation NETHERITE_SHIELD_TEXTURE_NOPATTERN = new ResourceLocation(Spacearms.MOD_ID, "entity/netherite_shield_nopattern");
    public static final ResourceLocation OBSIDIAN_SHIELD_TEXTURE = new ResourceLocation(Spacearms.MOD_ID, "entity/obsidian_shield");
    public static final ResourceLocation OBSIDIAN_SHIELD_TEXTURE_NOPATTERN = new ResourceLocation(Spacearms.MOD_ID, "entity/obsidian_shield_nopattern");
    public static final ResourceLocation NORMAL_SHIELD_TEXTURE = new ResourceLocation("entity/shield_base");
    public static final ResourceLocation NORMAL_SHIELD_TEXTURE_NOPATTERN = new ResourceLocation("entity/shield_base_nopattern");

    public static RenderMaterial getShieldTexture(ShieldType type, boolean flag) {
        switch (type) {
            case IRON:
                return getRenderMaterial(flag ? IRON_SHIELD_TEXTURE : IRON_SHIELD_TEXTURE_NOPATTERN);
            case GOLD:
                return getRenderMaterial(flag ? GOLD_SHIELD_TEXTURE : GOLD_SHIELD_TEXTURE_NOPATTERN);
            case DIAMOND:
                return getRenderMaterial(flag ? DIAMOND_SHIELD_TEXTURE : DIAMOND_SHIELD_TEXTURE_NOPATTERN);
            case NETHERITE:
                return getRenderMaterial(flag ? NETHERITE_SHIELD_TEXTURE : NETHERITE_SHIELD_TEXTURE_NOPATTERN);
            case OBSIDIAN:
                return getRenderMaterial(flag ? OBSIDIAN_SHIELD_TEXTURE : OBSIDIAN_SHIELD_TEXTURE_NOPATTERN);
            default:
                return getRenderMaterial(flag ? NORMAL_SHIELD_TEXTURE : NORMAL_SHIELD_TEXTURE_NOPATTERN);
        }
    }

    private static RenderMaterial getRenderMaterial(ResourceLocation resourceLocation){
      return new RenderMaterial(Atlases.SHIELD_ATLAS, resourceLocation);
    }

    //盾牌贴图
    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().getTextureLocation().equals(Atlases.SHIELD_ATLAS)) {
            event.addSprite(IRON_SHIELD_TEXTURE);
            event.addSprite(IRON_SHIELD_TEXTURE_NOPATTERN);
            event.addSprite(GOLD_SHIELD_TEXTURE);
            event.addSprite(GOLD_SHIELD_TEXTURE_NOPATTERN);
            event.addSprite(DIAMOND_SHIELD_TEXTURE);
            event.addSprite(DIAMOND_SHIELD_TEXTURE_NOPATTERN);
            event.addSprite(NETHERITE_SHIELD_TEXTURE);
            event.addSprite(NETHERITE_SHIELD_TEXTURE_NOPATTERN);
            event.addSprite(OBSIDIAN_SHIELD_TEXTURE);
            event.addSprite(OBSIDIAN_SHIELD_TEXTURE_NOPATTERN);
        }
    }
}
