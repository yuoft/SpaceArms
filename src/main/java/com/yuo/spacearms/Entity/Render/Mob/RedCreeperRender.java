package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenCreeper;
import com.yuo.spacearms.Entity.Mob.RedCreeper;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;

public class RedCreeperRender extends MobRenderer<RedCreeper, CreeperModel<RedCreeper>> {
    private final ResourceLocation TEXTURE = new ResourceLocation(SpaceArms.MOD_ID, "textures/entity/mob/red_creeper.png");

    public RedCreeperRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CreeperModel<>(), 0.5F);
        this.addLayer(new RedCreeperEyesLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(RedCreeper entity) {
        return TEXTURE;
    }
}