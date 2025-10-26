package com.yuo.spacearms.Entity.Render.Mob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yuo.spacearms.Entity.Mob.GreenEnderMan;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;

public class GreenCarriedBlockLayer extends RenderLayer<GreenEnderMan, EndermanModel<GreenEnderMan>> {
    private final BlockRenderDispatcher blockRenderer;

    public GreenCarriedBlockLayer(RenderLayerParent<GreenEnderMan, EndermanModel<GreenEnderMan>> layerParent, BlockRenderDispatcher dispatcher) {
        super(layerParent);
        this.blockRenderer = dispatcher;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, GreenEnderMan greenEnderMan, float v, float v1, float v2, float v3, float v4, float v5) {
        BlockState carriedBlock = greenEnderMan.getCarriedBlock();
        if (carriedBlock != null) {
            poseStack.pushPose();
            poseStack.translate(0.0F, 0.6875F, -0.75F);
            poseStack.mulPose(Axis.XP.rotationDegrees(20.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
            poseStack.translate(0.25F, 0.1875F, 0.25F);
            float v6 = 0.5F;
            poseStack.scale(-v6, -v6, v6);
            poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            this.blockRenderer.renderSingleBlock(carriedBlock, poseStack, bufferSource, i, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
