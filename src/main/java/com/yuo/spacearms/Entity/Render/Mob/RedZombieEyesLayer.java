package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenZombie;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class RedZombieEyesLayer extends EyesLayer<GreenZombie, ZombieModel<GreenZombie>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_zombie_eyes.png"));

    public RedZombieEyesLayer(RenderLayerParent<GreenZombie, ZombieModel<GreenZombie>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
