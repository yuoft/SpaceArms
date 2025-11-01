package com.yuo.spacearms.Client.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenEnderMan;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class GreenEndermanEyesLayer extends EyesLayer<GreenEnderMan, EndermanModel<GreenEnderMan>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_enderman_eyes.png"));

    public GreenEndermanEyesLayer(RenderLayerParent<GreenEnderMan, EndermanModel<GreenEnderMan>> renderer) {
        super(renderer);
    }

    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
