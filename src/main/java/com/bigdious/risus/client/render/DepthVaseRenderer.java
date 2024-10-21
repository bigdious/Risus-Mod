package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.entity.DepthVaseBlockEntity;
import com.bigdious.risus.client.RisusModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity.WobbleStyle;
import net.minecraft.world.phys.AABB;

public class DepthVaseRenderer implements BlockEntityRenderer<DepthVaseBlockEntity> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/depth_vase.png");
	private final ModelPart vaseBody;
	private final ModelPart decoration;
	protected static final float WOBBLE_AMPLITUDE = 0.125F;
	public DepthVaseRenderer(BlockEntityRendererProvider.Context p_272872_) {
		ModelPart modelpart = p_272872_.bakeLayer(RisusModelLayers.DEPTH_VASE);
		this.vaseBody = modelpart.getChild("vaseBody");
		this.decoration = modelpart.getChild("decoration");
	}
	public static LayerDefinition createBaseLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("vaseBody", CubeListBuilder.create().texOffs(0, 27).addBox(-12.0F, -1.0F, 4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(16, 42).addBox(-14.0F, -12.0F, 2.0F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(36, 1).addBox(-11.5F, -16.0F, 4.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(44, 9).mirror().addBox(-10.5F, -15.0F, 5.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(24, 16).mirror().addBox(-13.0F, -13.0F, 3.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(24, 27).mirror().addBox(-13.0F, -2.0F, 3.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, 24.0F, -8.0F));

		partdefinition.addOrReplaceChild("decoration", CubeListBuilder.create().texOffs(26, 31).addBox(3.5F, 3.0F, -6.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(25, 29).addBox(2.5F, 1.0F, -6.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(25, 30).addBox(3.5F, -1.0F, -6.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(25, 30).addBox(5.5F, 2.0F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(25, 28).addBox(5.5F, 0.0F, -4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void render(DepthVaseBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		poseStack.pushPose();
		Direction direction = blockEntity.getDirection();
		poseStack.translate(0.5, 0, 0.5);
		poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
		poseStack.translate(0, -1.5, 0);
		DepthVaseBlockEntity.DepthWobbleStyle wobbleStyle = blockEntity.lastWobbleStyle;
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F + direction.toYRot()));


		if (wobbleStyle != null && blockEntity.getLevel() != null) {
			float f = ((float) (blockEntity.getLevel().getGameTime() - blockEntity.wobbleStartedAtTick) + partialTick) / (float) wobbleStyle.duration;
			if (f >= 0.0F && f <= 1.0F) {
				if (wobbleStyle == DepthVaseBlockEntity.DepthWobbleStyle.POSITIVE) {
					float f1 = 0.015625F;
					float f2 = f * (float) (Math.PI * 2);
					float f3 = -1.5F * (Mth.cos(f2) + 0.5F) * Mth.sin(f2 / 2.0F);
					poseStack.rotateAround(Axis.XP.rotation(f3 * f1), 0.5F, 0.0F, 0.5F);
					float f4 = Mth.sin(f2);
					poseStack.rotateAround(Axis.ZP.rotation(f4 * f1), 0.5F, 0.0F, 0.5F);
				} else {
					float f5 = Mth.sin(-f * 3.0F * (float) Math.PI) * WOBBLE_AMPLITUDE;
					float f6 = 1.0F - f;
					poseStack.rotateAround(Axis.YP.rotation(f5 * f6), 0F, 0.0F, 0F);
				}
			}
		}
		VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(TEXTURE));
		this.vaseBody.render(poseStack, vertexconsumer, pPackedLight,pPackedOverlay);
		this.decoration.render(poseStack, vertexconsumer, pPackedLight,pPackedOverlay);
		poseStack.popPose();
	}
	public AABB getRenderBoundingBox(DecoratedPotBlockEntity blockEntity) {
		BlockPos pos = blockEntity.getBlockPos();
		return new AABB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)pos.getX() + 1.0, (double)pos.getY() + 1.3, (double)pos.getZ() + 1.0);
	}

}
