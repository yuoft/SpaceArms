package com.yuo.spacearms.Entity.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.SpaceArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SpaceArrowRender extends ArrowRenderer<SpaceArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/space_arrow.png");

    public SpaceArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(SpaceArrow entity) {
        return TEXTURE;
    }
}
