package com.yuo.spacearms.Entity.Render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.yuo.spacearms.Event.ClientEventHandler;
import com.yuo.spacearms.Items.tool.ModShield;
import com.yuo.spacearms.Items.tool.ShieldType;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
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
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ShieldTileEntityRenderer extends BlockEntityWithoutLevelRenderer {
	private final ShieldModel modelShield;

	public ShieldTileEntityRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
		super(dispatcher, modelSet);
		modelShield = new ShieldModel(modelSet.bakeLayer(new ModelLayerLocation(ClientEventHandler.NORMAL_SHIELD_TEXTURE_NOPATTERN, "main")));
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
		Item shield = stack.getItem();
		if (shield instanceof ModShield modShield){
            boolean flag = stack.getTagElement("BlockEntityTag") != null;
			ShieldType shieldType = modShield.getShieldType();
			poseStack.pushPose();
			poseStack.scale(1, -1, -1);
			Material rendermaterial = ClientEventHandler.getShieldTexture(shieldType, flag);

			VertexConsumer ivertexbuilder = rendermaterial.sprite().wrap(ItemRenderer.getArmorFoilBuffer(
					bufferSource, this.modelShield.renderType(rendermaterial.atlasLocation()), true, stack.isEnchanted()));
			this.modelShield.handle().render(poseStack, ivertexbuilder, light, overlay, 1.0F,
					1.0F, 1.0F, 1.0F);
			if (flag) {
				List<Pair<Holder<BannerPattern>, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(stack),
						BannerBlockEntity.getItemPatterns(stack));
				BannerRenderer.renderPatterns(poseStack, bufferSource, light, overlay,
						this.modelShield.handle(), rendermaterial, false, list, stack.isEnchanted());
			} else {
				this.modelShield.handle().render(poseStack, ivertexbuilder, light, overlay, 1.0F,
						1.0F, 1.0F, 1.0F);
			}
			poseStack.popPose();
		}

	}

}
