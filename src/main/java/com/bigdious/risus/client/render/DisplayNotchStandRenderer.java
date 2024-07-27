package com.bigdious.risus.client.render;

import com.bigdious.risus.blocks.DisplayNotchStandBlock;
import com.bigdious.risus.blocks.entity.DisplayNotchStandBlockEntity;
import com.bigdious.risus.blocks.entity.DisplayNotchStandBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DisplayNotchStandRenderer implements BlockEntityRenderer<DisplayNotchStandBlockEntity> {
	private final ItemRenderer itemRenderer;
	public DisplayNotchStandRenderer(BlockEntityRendererProvider.Context pContext) {
		this.itemRenderer = pContext.getItemRenderer();
	}

	@Override
	public void render(DisplayNotchStandBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light, int overlay) {
		ItemStack itemstack = entity.getInputItem();
		if (entity.getInputItem() != null && !entity.getInputItem().isEmpty()) {
				stack.pushPose();
				switch (entity.getBlockState().getValue(BlockStateProperties.ORIENTATION)){
					case UP_NORTH -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.25, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(180));
						stack.scale(1f, 1f, 1f);
					}
					case UP_SOUTH -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.25, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.scale(1f, 1f, 1f);
					}
					case UP_WEST -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.25, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(270));
						stack.scale(1f, 1f, 1f);
					}
					case UP_EAST -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.25, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(90));
						stack.scale(1f, 1f, 1f);
					}
					case DOWN_NORTH -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.75, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(180));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(180));
						stack.scale(1f, 1f, 1f);
					}
					case DOWN_SOUTH -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.75, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(180));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.scale(1f, 1f, 1f);
					}
					case DOWN_WEST -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.75, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(180));
						stack.mulPose(Axis.YP.rotationDegrees(270));
						stack.scale(1f, 1f, 1f);
					}
					case DOWN_EAST -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.75, 0.5D);
						} else {
							stack.translate(0.5D, 0.5, 0.5D);
						}
						stack.mulPose(Axis.XP.rotationDegrees(0));
						stack.mulPose(Axis.ZP.rotationDegrees(180));
						stack.mulPose(Axis.YP.rotationDegrees(90));
						stack.scale(1f, 1f, 1f);
					}
					case NORTH_UP -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.5, 0.75);
						} else {
							stack.translate(0.5D, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(270));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(0));
						stack.scale(1f, 1f, 1f);
					}
					case SOUTH_UP -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.5D, 0.5, 0.25);
						} else {
							stack.translate(0.5D, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(90));
						stack.mulPose(Axis.ZP.rotationDegrees(0));
						stack.mulPose(Axis.YP.rotationDegrees(0));
						stack.scale(1f, 1f, 1f);
					}
					case EAST_UP -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.25D, 0.5, 0.5);
						} else {
							stack.translate(0.5D, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(90));
						stack.mulPose(Axis.ZP.rotationDegrees(270));
						stack.mulPose(Axis.YP.rotationDegrees(0));
						stack.scale(1f, 1f, 1f);
					}
					case WEST_UP -> {
						if (entity.getBlockState().getValue(DisplayNotchStandBlock.ELEVATE) == true) {
							stack.translate(0.75D, 0.5, 0.5);
						} else {
							stack.translate(0.5, 0.5, 0.5);
						}
						stack.mulPose(Axis.XP.rotationDegrees(270));
						stack.mulPose(Axis.ZP.rotationDegrees(90));
						stack.mulPose(Axis.YP.rotationDegrees(0));
						stack.scale(1f, 1f, 1f);
					}

				}
			assert itemstack != null;
			this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, light, overlay, stack, buffers, entity.getLevel(), (int) entity.getBlockPos().asLong());
				stack.popPose();
			}
		}
	}
