package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenEnderMan;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.util.ResourceLocation;

public class GreenEndermanEyesLayer extends AbstractEyesLayer<GreenEnderMan, EndermanModel<GreenEnderMan>> {
    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(SpaceArms.MOD_ID,
            "textures/entity/mob/green_enderman_eyes.png"));

    public GreenEndermanEyesLayer(IEntityRenderer<GreenEnderMan, EndermanModel<GreenEnderMan>> renderer) {
        super(renderer);
    }

    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}
