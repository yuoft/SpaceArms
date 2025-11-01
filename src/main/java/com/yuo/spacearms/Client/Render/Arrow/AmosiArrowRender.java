package com.yuo.spacearms.Client.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.AmosiArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class AmosiArrowRender extends ArrowRenderer {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/amosi_arrow.png");

    public AmosiArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return TEXTURE;
    }
}
