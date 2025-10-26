package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenSpider;
import com.yuo.spacearms.Entity.Mob.RedSpider;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RedSpiderRender extends MobRenderer<RedSpider, SpiderModel<RedSpider>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_spider.png");

    public RedSpiderRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SpiderModel<>(renderManagerIn.bakeLayer(ModelLayers.SPIDER)), 0.8F);
        this.addLayer(new RedSpiderEyesLayer(this));
    }

    @Override
    protected float getFlipDegrees(RedSpider spider) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(RedSpider entity) {
        return TEXTURE;
    }
}