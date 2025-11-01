package com.yuo.spacearms.Client.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.EnderArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EnderArrowRender extends ArrowRenderer<EnderArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/ender_arrow.png");

    public EnderArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(EnderArrow entity) {
        return TEXTURE;
    }
}
