package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenSpider;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.util.ResourceLocation;

public class GreenSpiderRender extends MobRenderer<GreenSpider, SpiderModel<GreenSpider>> {
    private final ResourceLocation TEXTURE = new ResourceLocation(SpaceArms.MOD_ID, "textures/entity/mob/green_spider.png");

    public GreenSpiderRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SpiderModel<>(), 0.8F);
        this.addLayer(new GreenSpiderEyesLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GreenSpider entity) {
        return TEXTURE;
    }
}