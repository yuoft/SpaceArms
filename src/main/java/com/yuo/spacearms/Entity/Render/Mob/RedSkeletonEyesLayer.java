package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedSkeleton;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class RedSkeletonEyesLayer extends EyesLayer<RedSkeleton, SkeletonModel<RedSkeleton>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_skeleton_eyes.png"));

    public RedSkeletonEyesLayer(RenderLayerParent<RedSkeleton, SkeletonModel<RedSkeleton>> renderer) {
        super(renderer);
    }

    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
