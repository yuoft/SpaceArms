package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenCreeper;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;

public class GreenCreeperRender extends MobRenderer<GreenCreeper, CreeperModel<GreenCreeper>> {
    private final ResourceLocation TEXTURE = new ResourceLocation(SpaceArms.MOD_ID, "textures/entity/mob/green_creeper.png");

    public GreenCreeperRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CreeperModel<>(), 0.5F);
        this.addLayer(new GreenCreeperEyesLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GreenCreeper entity) {
        return TEXTURE;
    }
}