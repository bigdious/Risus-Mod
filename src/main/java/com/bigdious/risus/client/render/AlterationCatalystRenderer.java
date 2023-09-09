package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.entity.AlterationCatalystBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class AlterationCatalystRenderer implements BlockEntityRenderer<AlterationCatalystBlockEntity> {

	@Override
	public void render(AlterationCatalystBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		if (entity.getInputItem() != null && !entity.getInputItem().isEmpty()) {
			stack.pushPose();
			double yOffset = entity.getInputItem().getItem() instanceof BlockItem ? 1.0D : 0.95D;
			stack.translate(0.5D, yOffset, 0.5D);
			stack.mulPose(Axis.XP.rotationDegrees(90.0F));
			stack.mulPose(Axis.ZP.rotationDegrees(entity.rotationDegrees));
			stack.scale(0.25f, 0.25f, 0.25f);
			ItemStack itemstack = entity.getInputItem();
			Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, stack, buffers, entity.getLevel(), (int) entity.getBlockPos().asLong());
			stack.popPose();
		}
	}
}
