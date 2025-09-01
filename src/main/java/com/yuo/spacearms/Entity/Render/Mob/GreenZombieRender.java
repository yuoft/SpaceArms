package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenZombie;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;

public class GreenZombieRender extends AbstractZombieRenderer<GreenZombie, ZombieModel<GreenZombie>> {
    private final ResourceLocation TEXTURE = new ResourceLocation(SpaceArms.MOD_ID, "textures/entity/mob/green_zombie.png");

    public GreenZombieRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ZombieModel<>(0.0F, false),
                new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true));
        this.addLayer(new GreenZombieEyesLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GreenZombie entity) {
        return TEXTURE;
    }
}