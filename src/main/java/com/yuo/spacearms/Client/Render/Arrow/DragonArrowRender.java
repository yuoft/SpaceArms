package com.yuo.spacearms.Client.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.DragonArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DragonArrowRender extends ArrowRenderer<DragonArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/dragon_arrow.png");

    public DragonArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(DragonArrow entity) {
        return TEXTURE;
    }
}
