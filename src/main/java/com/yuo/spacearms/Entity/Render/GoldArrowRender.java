package com.yuo.spacearms.Entity.Render;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

//箭实体渲染
public class GoldArrowRender extends ArrowRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("spacearms:textures/models/misc/gold_arrow.png");

    public GoldArrowRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}
