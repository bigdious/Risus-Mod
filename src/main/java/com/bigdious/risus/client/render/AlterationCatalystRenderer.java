package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.entity.AlterationCatalystBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


public class AlterationCatalystRenderer implements BlockEntityRenderer<AlterationCatalystBlockEntity> {
	private final ItemRenderer itemRenderer;
	public AlterationCatalystRenderer(BlockEntityRendererProvider.Context pContext) {
		this.itemRenderer = pContext.getItemRenderer();
	}

	@Override
	public void render(AlterationCatalystBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		ItemStack itemstack = entity.getInputItem();
		int i = (int)entity.getBlockPos().asLong();
		if (itemstack != ItemStack.EMPTY) {
			stack.pushPose();
			double yOffset = entity.getInputItem().getItem() instanceof BlockItem ? 1.0D : 0.95D;
			stack.translate(0.5D, yOffset, 0.5D);
			stack.mulPose(Axis.XP.rotationDegrees(90.0F));
			stack.mulPose(Axis.ZP.rotationDegrees(entity.rotationDegrees));
			stack.scale(0.25f, 0.25f, 0.25f);
			this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, stack, buffers, entity.getLevel(), i);
			stack.popPose();
		}
	}
}
