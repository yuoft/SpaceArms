package com.yuo.spacearms.Client.Render.Mob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuo.spacearms.Entity.Mob.RedEnderMan;
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

public class RedEndermanRender extends MobRenderer<RedEnderMan, EndermanModel<RedEnderMan>> {
    private final ResourceLocation TEXTURE = RlUtils.fa(SpaceArms.MOD_ID, "textures/entity/mob/red_enderman.png");
    private final RandomSource random = RandomSource.create();

    public RedEndermanRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new EndermanModel<>(renderManagerIn.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
        this.addLayer(new RedEndermanEyesLayer(this));
        this.addLayer(new RedCarriedBlockLayer(this, renderManagerIn.getBlockRenderDispatcher()));
    }

    @Override
    public void render(RedEnderMan enderMan, float v, float v1, PoseStack poseStack, MultiBufferSource bufferSource, int i) {
        BlockState $$6 = enderMan.getCarriedBlock();
        EndermanModel<RedEnderMan> $$7 = this.getModel();
        $$7.carrying = $$6 != null;
        $$7.creepy = enderMan.isCreepy();
        super.render(enderMan, v, v1, poseStack, bufferSource, i);
    }

    @Override
    public @NotNull Vec3 getRenderOffset(RedEnderMan p_114336_, float p_114337_) {
        if (p_114336_.isCreepy()) {
            double v = 0.02;
            return new Vec3(this.random.nextGaussian() * v, 0.0, this.random.nextGaussian() * v);
        } else {
            return super.getRenderOffset(p_114336_, p_114337_);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(RedEnderMan entity) {
        return TEXTURE;
    }
}