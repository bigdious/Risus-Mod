package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.entity.DepthVaseBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity.WobbleStyle;

public class DepthVaseRenderer implements BlockEntityRenderer<DepthVaseBlockEntity> {
	protected static final float WOBBLE_AMPLITUDE = 0.125F;
	public void render(DepthVaseBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		poseStack.pushPose();
		poseStack.translate(0.5, 0.0, 0.5);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F ));
		poseStack.translate(-0.5, 0.0, -0.5);
		WobbleStyle wobbleStyle = blockEntity.lastWobbleStyle;


		if (wobbleStyle != null && blockEntity.getLevel() != null) {
			float f = ((float) (blockEntity.getLevel().getGameTime() - blockEntity.wobbleStartedAtTick) + partialTick) / (float) wobbleStyle.duration;
			if (f >= 0.0F && f <= 1.0F) {
				if (wobbleStyle == WobbleStyle.POSITIVE) {
					float f1 = 0.015625F;
					float f2 = f * (float) (Math.PI * 2);
					float f3 = -1.5F * (Mth.cos(f2) + 0.5F) * Mth.sin(f2 / 2.0F);
					poseStack.rotateAround(Axis.XP.rotation(f3 * f1), 0.5F, 0.0F, 0.5F);
					float f4 = Mth.sin(f2);
					poseStack.rotateAround(Axis.ZP.rotation(f4 * f1), 0.5F, 0.0F, 0.5F);
				} else {
					float f5 = Mth.sin(-f * 3.0F * (float) Math.PI) * WOBBLE_AMPLITUDE;
					float f6 = 1.0F - f;
					poseStack.rotateAround(Axis.YP.rotation(f5 * f6), 0.5F, 0.0F, 0.5F);
				}
			}
		}
		poseStack.popPose();
	}

}
