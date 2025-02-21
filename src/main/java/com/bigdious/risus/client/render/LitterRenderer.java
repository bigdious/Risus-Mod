package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.LitterModel;
import com.bigdious.risus.entity.Litter;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.client.model.data.ModelData;

public class LitterRenderer extends MobRenderer<Litter, LitterModel<Litter>> {

	private static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/litter.png");
	private final BlockRenderDispatcher blockRenderer;

	public LitterRenderer(EntityRendererProvider.Context context) {
		super(context, new LitterModel<>(context.bakeLayer(RisusModelLayers.LITTER)), 0.0F);
		this.blockRenderer = context.getBlockRenderDispatcher();
	}

	@Override
	public void render(Litter entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
		stack.pushPose();
		stack.scale(0.5F, 0.5F, 0.5F);
		float f = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
		stack.mulPose(Axis.YN.rotationDegrees(f));
		stack.translate(-0.5F, 0.5F, -0.5F);
		this.blockRenderer.renderSingleBlock(entity.getLightBlockState(), stack, buffer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
		stack.popPose();
	}

	@Override
	protected float getFlipDegrees(Litter litter) {
		return 0.0F;
	}


	@Override
	public ResourceLocation getTextureLocation(Litter litter) {
		return TEXTURE;
	}
}