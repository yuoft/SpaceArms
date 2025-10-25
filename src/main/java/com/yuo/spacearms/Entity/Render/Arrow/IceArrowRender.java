package com.yuo.spacearms.Entity.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.IceArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class IceArrowRender extends ArrowRenderer<IceArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/ice_arrow.png");

    public IceArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(IceArrow entity) {
        return TEXTURE;
    }
}
