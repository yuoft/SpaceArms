package com.yuo.spacearms.Entity.Render.Mob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuo.spacearms.Entity.Mob.GreenEnderMan;
import com.yuo.spacearms.RlUtils;
import com.yuo.spacearms.SpaceArms;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class GreenEndermanRender extends MobRenderer<GreenEnderMan, EndermanModel<GreenEnderMan>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/green_enderman.png");
    private final RandomSource random = RandomSource.create();

    public GreenEndermanRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new EndermanModel<>(renderManagerIn.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
        this.addLayer(new GreenEndermanEyesLayer(this));
        this.addLayer(new GreenCarriedBlockLayer(this, renderManagerIn.getBlockRenderDispatcher()));
    }

    @Override
    public void render(GreenEnderMan enderMan, float v, float v1, PoseStack poseStack, MultiBufferSource bufferSource, int i) {
        BlockState $$6 = enderMan.getCarriedBlock();
        EndermanModel<GreenEnderMan> $$7 = this.getModel();
        $$7.carrying = $$6 != null;
        $$7.creepy = enderMan.isCreepy();
        super.render(enderMan, v, v1, poseStack, bufferSource, i);
    }

    @Override
    public @NotNull Vec3 getRenderOffset(GreenEnderMan p_114336_, float p_114337_) {
        if (p_114336_.isCreepy()) {
            double v = 0.02;
            return new Vec3(this.random.nextGaussian() * v, 0.0, this.random.nextGaussian() * v);
        } else {
            return super.getRenderOffset(p_114336_, p_114337_);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(GreenEnderMan entity) {
        return TEXTURE;
    }
}