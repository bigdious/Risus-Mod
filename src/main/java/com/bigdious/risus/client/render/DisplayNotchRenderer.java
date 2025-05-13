package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.DisplayNotchBlock;
import com.bigdious.risus.blocks.entity.DisplayNotchBlockEntity;
import com.bigdious.risus.config.RisusConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class DisplayNotchRenderer implements BlockEntityRenderer<DisplayNotchBlockEntity> {

	private final ItemRenderer itemRenderer;

	public DisplayNotchRenderer(BlockEntityRendererProvider.Context context) {
		this.itemRenderer = context.getItemRenderer();
	}


	private int getLightVal(DisplayNotchBlockEntity entity, int regularLightVal) {
		return entity.getBlockState().getValue(DisplayNotchBlock.GLOWING) ? LightTexture.FULL_BRIGHT : regularLightVal;
	}

	@Override
	public void render(DisplayNotchBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		ItemStack itemstack = entity.getTheItem();
		stack.pushPose();

		boolean stand = entity.stand;
		boolean elevate = entity.getBlockState().getValue(DisplayNotchBlock.ELEVATE);
		var dir = entity.getBlockState().getValue(DisplayNotchBlock.FACING);
		var offset = elevate ? stand ? -0.25D : -0.3D : stand ? 0.001D : -0.4675D;
		stack.translate(offset * dir.getStepX() + 0.5D, offset * dir.getStepY() + 0.5D, offset * dir.getStepZ() + 0.5D);
		var rotation = (entity.getBlockState().getValue(DisplayNotchBlock.ROTATION) * 22.5F) * Mth.DEG_TO_RAD;
		stack.mulPose(dir.getRotation()
			.rotateX(stand ? 0.0F : -90.0F * Mth.DEG_TO_RAD)
			.rotateY(stand ? -rotation : 180.0F * Mth.DEG_TO_RAD)
			.rotateZ(stand ? 0.0F : rotation));

		if ((RisusConfig.spinningSource == RisusConfig.SpinningSource.SIGNAL && entity.getLevel().hasNeighborSignal(entity.getBlockPos())) || entity.shouldRotate) {
			if (stand) {
				stack.mulPose(Axis.YP.rotationDegrees(AnimationRenderHelper.rotation*2));
			} else {
				stack.mulPose(Axis.ZP.rotationDegrees(AnimationRenderHelper.rotation*2));
			}
		}

		if (!itemstack.isEmpty() && itemstack != null && itemstack == entity.getTheItem()) {
			int k = this.getLightVal(entity, light);
			this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, k, OverlayTexture.NO_OVERLAY, stack, buffers, entity.getLevel(), (int) entity.getBlockPos().asLong());
		}
		stack.popPose();
	}
}