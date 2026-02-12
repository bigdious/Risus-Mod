package com.bigdious.risus.client.render.block;

import com.bigdious.risus.blocks.entity.AlterationCatalystBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;


public class AlterationCatalystRenderer implements BlockEntityRenderer<AlterationCatalystBlockEntity> {
	private final ItemRenderer itemRenderer;
	public AlterationCatalystRenderer(BlockEntityRendererProvider.Context pContext) {
		this.itemRenderer = pContext.getItemRenderer();
	}

	@Override
	public void render(AlterationCatalystBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		ItemStack itemstack = entity.getTheItem();
		int i = (int)entity.getBlockPos().asLong();
		if (itemstack != ItemStack.EMPTY) {
			stack.pushPose();
			double yOffset = entity.getTheItem().getItem() instanceof BlockItem ? 1.0D : 0.95D;
			stack.translate(0.5D, yOffset, 0.5D);
			stack.mulPose(Axis.XP.rotationDegrees(90.0F));
			stack.mulPose(Axis.ZP.rotationDegrees(entity.rotationDegrees));
			stack.scale(0.25f, 0.25f, 0.25f);
			this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, stack, buffers, entity.getLevel(), i);
			stack.popPose();
		}

		VertexConsumer consumer = buffers.getBuffer(RenderType.endPortal());
		Matrix4f matrix4f = stack.last().pose();
		//south -> east
		this.renderVoidFace(matrix4f, consumer, 0.41F, 0.59F, 0.9425F, 0.9425F);
		this.renderVoidFace(matrix4f, consumer, 0.59F, 0.75F, 0.9425F, 0.875F);
		this.renderVoidFace(matrix4f, consumer, 0.75F, 0.875F, 0.875F, 0.75F);
		this.renderVoidFace(matrix4f, consumer, 0.875F, 0.94F, 0.75F, 0.595F);
		//east -> north
		this.renderVoidFace(matrix4f, consumer, 0.94F, 0.94F, 0.595F, 0.41F);
		this.renderVoidFace(matrix4f, consumer, 0.94F, 0.875F, 0.41F, 0.25F);
		this.renderVoidFace(matrix4f, consumer, 0.875F, 0.75F, 0.25F, 0.125F);
		this.renderVoidFace(matrix4f, consumer, 0.75F, 0.59F, 0.125F, 0.058F);
		//north -> west
		this.renderVoidFace(matrix4f, consumer, 0.59F, 0.41F, 0.0575F, 0.0575F);
		this.renderVoidFace(matrix4f, consumer, 0.41F, 0.25F, 0.0575F, 0.125F);
		this.renderVoidFace(matrix4f, consumer, 0.25F, 0.125F, 0.125F, 0.25F);
		this.renderVoidFace(matrix4f, consumer, 0.125F, 0.0575F, 0.25F, 0.41F);
		//west -> south
		this.renderVoidFace(matrix4f, consumer, 0.0575F, 0.0575F, 0.41F, 0.59F);
		this.renderVoidFace(matrix4f, consumer, 0.0575F, 0.125F, 0.59F, 0.75F);
		this.renderVoidFace(matrix4f, consumer, 0.125F, 0.25F, 0.75F, 0.875F);
		this.renderVoidFace(matrix4f, consumer, 0.25F, 0.41F, 0.875F, 0.9425F);

	}

	private void renderVoidFace(Matrix4f pose, VertexConsumer consumer, float x0, float x1, float z0, float z1) {
		consumer.addVertex(pose, x0, 0.325F, z0);
		consumer.addVertex(pose, x1, 0.325F, z1);
		consumer.addVertex(pose, x1, 0.55F, z1);
		consumer.addVertex(pose, x0, 0.55F, z0);
	}
}
