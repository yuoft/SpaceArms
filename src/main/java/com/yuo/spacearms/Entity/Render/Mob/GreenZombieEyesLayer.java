package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenZombie;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;

public class GreenZombieEyesLayer extends AbstractEyesLayer<GreenZombie, ZombieModel<GreenZombie>> {
    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(SpaceArms.MOD_ID,
            "textures/entity/mob/green_zombie_eyes.png"));

    public GreenZombieEyesLayer(IEntityRenderer<GreenZombie, ZombieModel<GreenZombie>> renderer) {
        super(renderer);
    }

    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}
