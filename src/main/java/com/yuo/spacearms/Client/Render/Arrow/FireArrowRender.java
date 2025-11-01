package com.yuo.spacearms.Client.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.FireArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FireArrowRender extends ArrowRenderer<FireArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/fire_arrow.png");

    public FireArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(FireArrow entity) {
        return TEXTURE;
    }
}
