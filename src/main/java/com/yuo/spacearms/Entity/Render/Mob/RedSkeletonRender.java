package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedSkeleton;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;

public class RedSkeletonRender extends BipedRenderer<RedSkeleton, SkeletonModel<RedSkeleton>> {
    private final ResourceLocation TEXTURE = new ResourceLocation(SpaceArms.MOD_ID, "textures/entity/mob/red_skeleton.png");

    public RedSkeletonRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn,new SkeletonModel<>(), 0.5F);
        this.addLayer(new RedSkeletonEyesLayer(this));
        this.addLayer(new BipedArmorLayer<>(this, new SkeletonModel<>(0.5F, true), new SkeletonModel<>(1.0F, true)));
    }

    @Override
    public ResourceLocation getEntityTexture(RedSkeleton entity) {
        return TEXTURE;
    }
}