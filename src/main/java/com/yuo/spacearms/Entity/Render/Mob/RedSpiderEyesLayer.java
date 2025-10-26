package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedSpider;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class RedSpiderEyesLayer extends EyesLayer<RedSpider, SpiderModel<RedSpider>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_spider_eyes.png"));

    public RedSpiderEyesLayer(RenderLayerParent<RedSpider, SpiderModel<RedSpider>> renderer) {
        super(renderer);
    }

    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
