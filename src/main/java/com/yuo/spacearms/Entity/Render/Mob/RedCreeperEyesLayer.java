package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenCreeper;
import com.yuo.spacearms.Entity.Mob.RedCreeper;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;

public class RedCreeperEyesLayer extends AbstractEyesLayer<RedCreeper, CreeperModel<RedCreeper>> {
    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(SpaceArms.MOD_ID,
            "textures/entity/mob/red_creeper_eyes.png"));

    public RedCreeperEyesLayer(IEntityRenderer<RedCreeper, CreeperModel<RedCreeper>> renderer) {
        super(renderer);
    }

    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}
