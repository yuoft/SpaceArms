package com.yuo.spacearms.Client.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedCreeper;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class RedCreeperEyesLayer extends EyesLayer<RedCreeper, CreeperModel<RedCreeper>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_creeper_eyes.png"));

    public RedCreeperEyesLayer(RenderLayerParent<RedCreeper, CreeperModel<RedCreeper>> renderer) {
        super(renderer);
    }

    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
