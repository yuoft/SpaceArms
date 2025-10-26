package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedCreeper;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RedCreeperRender extends MobRenderer<RedCreeper, CreeperModel<RedCreeper>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_creeper.png");

    public RedCreeperRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CreeperModel<>(renderManagerIn.bakeLayer(ModelLayers.SPIDER)), 0.5F);
        this.addLayer(new RedCreeperEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RedCreeper entity) {
        return TEXTURE;
    }
}