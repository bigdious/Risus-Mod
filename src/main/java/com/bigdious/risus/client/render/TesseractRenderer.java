package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.entity.TesseractBlockEntity;
import com.bigdious.risus.init.RisusItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class TesseractRenderer implements BlockEntityRenderer<TesseractBlockEntity> {

	private final ItemRenderer itemRenderer;

	public TesseractRenderer(BlockEntityRendererProvider.Context context) {
		this.itemRenderer = context.getItemRenderer();
	}


	@Override
	public void render(TesseractBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		ItemStack itemstack = RisusItems.BLOOD_FEATHER.get().getDefaultInstance();
		stack.pushPose();

		stack.translate(0.5D, 0.5D, 0.5D);
		stack.scale(0.70F, 0.70F, 0.70F);
		stack.mulPose(Axis.YP.rotationDegrees(AnimationRenderHelper.rotation*2));

		this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, stack, buffers, entity.getLevel(), (int) entity.getBlockPos().asLong());

		stack.popPose();
	}
}