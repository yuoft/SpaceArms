package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedSpider;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.util.ResourceLocation;

public class RedSpiderRender extends MobRenderer<RedSpider, SpiderModel<RedSpider>> {
    private final ResourceLocation TEXTURE = new ResourceLocation(SpaceArms.MOD_ID, "textures/entity/mob/green_spider.png");

    public RedSpiderRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SpiderModel<>(), 0.8F);
        this.addLayer(new RedSpiderEyesLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(RedSpider entity) {
        return TEXTURE;
    }
}