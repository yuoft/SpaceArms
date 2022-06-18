package com.yuo.spacearms.Entity.Render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import com.yuo.spacearms.Event.ClientEventHandler;
import com.yuo.spacearms.Items.tool.ModShield;
import com.yuo.spacearms.Items.tool.ShieldType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.BannerTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.*;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ShieldTileEntityRenderer extends ItemStackTileEntityRenderer {
	private final ShieldModel modelShield = new ShieldModel();

	@Override
	public void func_239207_a_(ItemStack stack, TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		Item shield = stack.getItem();
		if (shield instanceof ModShield){
			ModShield modShield = (ModShield) shield;
			boolean flag = stack.getChildTag("BlockEntityTag") != null;
			ShieldType shieldType = modShield.getShieldType();
			matrixStack.push();
			matrixStack.scale(1, -1, -1);
			RenderMaterial rendermaterial = ClientEventHandler.getShieldTexture(shieldType, flag);

			IVertexBuilder ivertexbuilder = rendermaterial.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(
					buffer, this.modelShield.getRenderType(rendermaterial.getAtlasLocation()), true, stack.hasEffect()));
			this.modelShield.func_228294_b_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F,
					1.0F, 1.0F, 1.0F);
			if (flag) {
				List<Pair<BannerPattern, DyeColor>> list = BannerTileEntity.getPatternColorData(ShieldItem.getColor(stack),
						BannerTileEntity.getPatternData(stack));
				BannerTileEntityRenderer.func_241717_a_(matrixStack, buffer, combinedLight, combinedOverlay,
						this.modelShield.func_228293_a_(), rendermaterial, false, list, stack.hasEffect());
			} else {
				this.modelShield.func_228293_a_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F,
						1.0F, 1.0F, 1.0F);
			}
			matrixStack.pop();
		}

	}

}
