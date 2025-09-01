package com.yuo.spacearms.Entity.Render.Mob;

import com.yuo.spacearms.Entity.Mob.RedSpider;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.util.ResourceLocation;

public class RedSpiderEyesLayer extends AbstractEyesLayer<RedSpider, SpiderModel<RedSpider>> {
    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(SpaceArms.MOD_ID,
            "textures/entity/mob/red_spider_eyes.png"));

    public RedSpiderEyesLayer(IEntityRenderer<RedSpider, SpiderModel<RedSpider>> renderer) {
        super(renderer);
    }

    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}
