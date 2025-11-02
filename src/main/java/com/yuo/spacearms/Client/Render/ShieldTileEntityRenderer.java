package com.yuo.spacearms.Client.Render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.yuo.spacearms.Event.ClientEventHandler;
import com.yuo.spacearms.Items.tool.ModShield;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Holder;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ShieldTileEntityRenderer extends BlockEntityWithoutLevelRenderer {
	private final ShieldModel modelShield;

	public ShieldTileEntityRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
		super(dispatcher, modelSet);
		modelShield = new ShieldModel(modelSet.bakeLayer(ModelLayers.SHIELD));
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
		Item shield = stack.getItem();
		if (shield instanceof ModShield modShield){

			boolean flag = BlockItem.getBlockEntityData(stack) != null;
			poseStack.pushPose();
			poseStack.scale(1.0F, -1.0F, -1.0F);
			Material material = ClientEventHandler.getShieldTexture(modShield.getShieldType(), flag);
			VertexConsumer consumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(bufferSource, this.modelShield.renderType(material.atlasLocation()), true, stack.hasFoil()));
			this.modelShield.handle().render(poseStack, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
			if (flag) {
				List<Pair<Holder<BannerPattern>, DyeColor>> $$28 = BannerBlockEntity.createPatterns(ShieldItem.getColor(stack), BannerBlockEntity.getItemPatterns(stack));
				BannerRenderer.renderPatterns(poseStack, bufferSource, light, overlay, this.modelShield.plate(), material, false, $$28, stack.hasFoil());
			} else {
				this.modelShield.plate().render(poseStack, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			poseStack.popPose();

		}

	}

}
