package com.yuo.spacearms.Client.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.GoldArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class GoldArrowRender extends ArrowRenderer<GoldArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/gold_arrow.png");

    public GoldArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(GoldArrow entity) {
        return TEXTURE;
    }
}
