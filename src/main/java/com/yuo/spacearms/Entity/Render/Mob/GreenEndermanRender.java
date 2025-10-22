package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenEnderman;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.util.ResourceLocation;

public class GreenEndermanRender extends MobRenderer<GreenEnderman, EndermanModel<GreenEnderman>> {
    private final ResourceLocation TEXTURE = new ResourceLocation(SpaceArms.MOD_ID, "textures/entity/mob/green_enderman.png");

    public GreenEndermanRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EndermanModel<>(0.0F), 0.5F);
        this.addLayer(new GreenEndermanEyesLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GreenEnderman entity) {
        return TEXTURE;
    }
}