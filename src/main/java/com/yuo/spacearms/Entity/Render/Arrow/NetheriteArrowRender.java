package com.yuo.spacearms.Entity.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.NetheriteArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class NetheriteArrowRender extends ArrowRenderer<NetheriteArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/netherite_arrow.png");

    public NetheriteArrowRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getTextureLocation(NetheriteArrow entity) {
        return TEXTURE;
    }
}
