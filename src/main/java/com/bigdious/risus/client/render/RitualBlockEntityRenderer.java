package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.entity.RitualBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public class RitualBlockEntityRenderer implements BlockEntityRenderer<RitualBlockEntity> {

	private final ItemRenderer itemRenderer;
	private float oRot;
	private float rot;

	public RitualBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
		this.itemRenderer = context.getItemRenderer();
	}

	@Override
	public void render(RitualBlockEntity entity, float partialTick, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		this.updateEntityRotation(entity.getBlockPos(), Minecraft.getInstance().gameRenderer.getMainCamera());
		ItemStack itemstack = entity.getTheItem();
		if (itemstack != ItemStack.EMPTY) {
			stack.pushPose();
			stack.translate(0.5D, 2.5D, 0.5D);
			stack.mulPose(Axis.YP.rotation(-this.rotateTowardsPlayer(partialTick) - Mth.HALF_PI));
			this.handleRotationTimer(entity.getTimer(), stack);
			stack.scale(0.6F, 0.6F, 0.6F);
			this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, stack, buffers, entity.getLevel(), 0);
			stack.popPose();
		}
	}

	private void handleRotationTimer(int timer, PoseStack stack) {
		stack.mulPose(Axis.ZP.rotationDegrees(180));
		if (timer > 20 && timer < 60) {
			stack.translate(0.0D, ((timer - 20) / 40.0F), 0.0D);
			stack.mulPose(Axis.XP.rotationDegrees(90 * ((timer - 20) / 40.0F)));
		} else if (timer >= 60 && timer < 100) {
			stack.translate(0.0D, 1.0D, 0.0D);
			stack.mulPose(Axis.XP.rotationDegrees(90));
		}
	}

	@Override
	public AABB getRenderBoundingBox(RitualBlockEntity entity) {
		return new AABB(entity.getBlockPos()).inflate(1.0D).expandTowards(0.0D, 1.0D, 0.0D);
	}

	private void updateEntityRotation(BlockPos pos, Camera camera) {
		this.oRot = this.rot;
		double d0 = camera.getPosition().x - pos.getX() - 0.5D;
		double d1 = camera.getPosition().z - pos.getZ() - 0.5D;
		float tRot = (float) Mth.atan2(d1, d0);

		while (this.rot >= Mth.PI) {
			this.rot -= Mth.TWO_PI;
		}

		while (this.rot < -Mth.PI) {
			this.rot += Mth.TWO_PI;
		}

		while (tRot >= Mth.PI) {
			tRot -= Mth.TWO_PI;
		}

		while (tRot < -Mth.PI) {
			tRot += Mth.TWO_PI;
		}

		float f2 = tRot - this.rot;

		while (f2 >= Mth.PI) {
			f2 -= Mth.TWO_PI;
		}

		while (f2 < -Mth.PI) {
			f2 += Mth.TWO_PI;
		}

		this.rot += f2 * 0.4F;
	}

	private float rotateTowardsPlayer(float partialTick) {
		float f1 = this.rot - this.oRot;

		while (f1 >= Mth.PI) {
			f1 -= Mth.TWO_PI;
		}

		while (f1 < -Mth.PI) {
			f1 += Mth.TWO_PI;
		}

		return this.oRot + f1 * partialTick;
	}
}

