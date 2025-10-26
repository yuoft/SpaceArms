package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenZombie;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;

public class GreenZombieRender extends AbstractZombieRenderer<GreenZombie, ZombieModel<GreenZombie>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_zombie.png");

    public GreenZombieRender(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)),
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
        this.addLayer(new GreenZombieEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GreenZombie entity) {
        return TEXTURE;
    }
}