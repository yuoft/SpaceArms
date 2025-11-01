package com.yuo.spacearms.Client.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenCreeper;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GreenCreeperRender extends MobRenderer<GreenCreeper, CreeperModel<GreenCreeper>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_creeper.png");

    public GreenCreeperRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CreeperModel<>(renderManagerIn.bakeLayer(ModelLayers.CREEPER)), 0.5F);
        this.addLayer(new GreenCreeperEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GreenCreeper entity) {
        return TEXTURE;
    }
}