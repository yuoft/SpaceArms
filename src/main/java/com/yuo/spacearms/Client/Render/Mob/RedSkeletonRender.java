package com.yuo.spacearms.Client.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedSkeleton;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class RedSkeletonRender extends HumanoidMobRenderer<RedSkeleton, SkeletonModel<RedSkeleton>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_skeleton.png");

    public RedSkeletonRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn,new SkeletonModel<>(renderManagerIn.bakeLayer(ModelLayers.SKELETON)), 0.5F);
        this.addLayer(new RedSkeletonEyesLayer(this));
        this.addLayer(new HumanoidArmorLayer<>(this,
                new SkeletonModel<>(renderManagerIn.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)),
                new SkeletonModel<>(renderManagerIn.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), renderManagerIn.getModelManager()));
    }

    @Override
    protected boolean isShaking(RedSkeleton skeleton) {
        return skeleton.isShaking();
    }

    @Override
    public ResourceLocation getTextureLocation(RedSkeleton entity) {
        return TEXTURE;
    }
}