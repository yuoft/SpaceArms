package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenSkeleton;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class GreenSkeletonEyesLayer extends EyesLayer<GreenSkeleton, SkeletonModel<GreenSkeleton>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_skeleton_eyes.png"));

    public GreenSkeletonEyesLayer(RenderLayerParent<GreenSkeleton, SkeletonModel<GreenSkeleton>> renderer) {
        super(renderer);
    }

    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
