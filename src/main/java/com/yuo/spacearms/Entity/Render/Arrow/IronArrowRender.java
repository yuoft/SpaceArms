package com.yuo.spacearms.Entity.Render.Arrow;

import com.yuo.spacearms.Entity.Arrow.IronArrow;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class IronArrowRender extends ArrowRenderer<IronArrow> {

    private static final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID,"textures/models/misc/iron_arrow.png");

    public IronArrowRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull IronArrow entity) {
        return TEXTURE;
    }
}
