package com.yuo.spacearms.Client.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenZombie;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RedZombieRender extends AbstractZombieRenderer<GreenZombie, ZombieModel<GreenZombie>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_zombie.png");

    public RedZombieRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ZombieModel<>(renderManagerIn.bakeLayer(ModelLayers.ZOMBIE)),
                new ZombieModel<>(renderManagerIn.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new ZombieModel<>(renderManagerIn.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
        this.addLayer(new RedZombieEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GreenZombie entity) {
        return TEXTURE;
    }
}