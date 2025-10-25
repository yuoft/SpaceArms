package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedEnderMan;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.util.ResourceLocation;

public class RedEndermanEyesLayer extends AbstractEyesLayer<RedEnderMan, EndermanModel<RedEnderMan>> {
    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(SpaceArms.MOD_ID,
            "textures/entity/mob/red_enderman_eyes.png"));

    public RedEndermanEyesLayer(IEntityRenderer<RedEnderMan, EndermanModel<RedEnderMan>> renderer) {
        super(renderer);
    }

    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}
