package com.yuo.spacearms.Client.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenSpider;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GreenSpiderRender extends MobRenderer<GreenSpider, SpiderModel<GreenSpider>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_spider.png");

    public GreenSpiderRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SpiderModel<>(renderManagerIn.bakeLayer(ModelLayers.SPIDER)), 0.8F);
        this.addLayer(new GreenSpiderEyesLayer(this));
    }

    @Override
    protected float getFlipDegrees(GreenSpider spider) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(GreenSpider entity) {
        return TEXTURE;
    }
}