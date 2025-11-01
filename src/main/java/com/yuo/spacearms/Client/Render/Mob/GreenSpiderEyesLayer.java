package com.yuo.spacearms.Client.Render.Mob;

import com.yuo.spacearms.Entity.Mob.GreenSpider;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class GreenSpiderEyesLayer extends EyesLayer<GreenSpider, SpiderModel<GreenSpider>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_spider_eyes.png"));

    public GreenSpiderEyesLayer(RenderLayerParent<GreenSpider, SpiderModel<GreenSpider>> renderer) {
        super(renderer);
    }

    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
