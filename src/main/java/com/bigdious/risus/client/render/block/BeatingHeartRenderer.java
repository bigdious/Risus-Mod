package com.bigdious.risus.client.render.block;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.BeatingHeartBlock;
import com.bigdious.risus.blocks.entity.BeatingHeartBlockEntity;
import com.bigdious.risus.blocks.entity.DepthVaseBlockEntity;
import com.bigdious.risus.blocks.entity.WeavingMechanismBlockEntity;
import com.bigdious.risus.client.RisusModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BeatingHeartRenderer implements BlockEntityRenderer<BeatingHeartBlockEntity> {
	private final ModelPart heartMeat;
	private final ModelPart pinkTube;
	private final ModelPart redTube;
	private final ModelPart blueTube;

	public BeatingHeartRenderer(BlockEntityRendererProvider.Context context) {
		ModelPart root = context.bakeLayer(RisusModelLayers.BEATING_HEART);
		this.heartMeat = root.getChild("heartMeat");
		this.pinkTube = root.getChild("pinkTube");
		this.redTube = root.getChild("redTube");
		this.blueTube = root.getChild("blueTube");
	}

	public static LayerDefinition createBaseLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition heartMeat = partdefinition.addOrReplaceChild("heartMeat", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -3.0F, -6.0F, 5.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(0, 50).addBox(-5.0F, 0.0F, -6.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(0, 39).addBox(-4.0F, 4.0F, -5.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 31).addBox(-3.0F, 7.0F, -4.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 7).addBox(-5.0F, -3.0F, -4.0F, 5.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 1.0F));

		PartDefinition pinkTube = partdefinition.addOrReplaceChild("pinkTube", CubeListBuilder.create().texOffs(30, 2).addBox(-3.0F, -2.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(0, 2).addBox(-2.0F, -4.0F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(12, 2).addBox(0.0F, -5.0F, -2.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -1.0F));

		PartDefinition redTube = partdefinition.addOrReplaceChild("redTube", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -4.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 10).addBox(2.0F, -4.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(20, 25).addBox(-2.0F, -4.0F, -2.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 8).addBox(-2.5F, -5.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(4, 8).addBox(-0.5F, -5.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(6, 18).addBox(1.5F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 2.0F));

		PartDefinition blueTube = partdefinition.addOrReplaceChild("blueTube", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -3.0F, -4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(26, 11).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 11.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void render(BeatingHeartBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
		poseStack.pushPose();
		Direction direction = entity.getDirection();
		poseStack.translate(0.5, 0, 0.5);
		poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
		poseStack.translate(0, -1.5, 0);
		poseStack.mulPose(Axis.YP.rotationDegrees(direction.getOpposite().toYRot()));
		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutout(BeatingHeartBlockEntity.HEALTH_EFFECTS.get(entity.getBlockState().getValue(BeatingHeartBlock.HealthEffectEnum.HEALTH_EFFECT)).getSecond()));
		if (entity.getLevel() != null) {
			float f = ((float) (entity.getLevel().getGameTime() - entity.beatStartedAtTick) + partialTick) / (float) 7;
			if (f >= 0.6F && f <= 1.0F) {
				float f1 = f + 0.25F;
				float f2 = f * (float) (Math.PI * 2);
				float f3 = -1.5F * (Mth.cos(f2)+1) * Mth.sin(f2 / 2.0F);
				poseStack.scale(f1, 1, f1);
			}
		}
		this.blueTube.render(poseStack, vertexconsumer, light,overlay);
		this.pinkTube.render(poseStack, vertexconsumer, light,overlay);
		this.redTube.render(poseStack, vertexconsumer, light,overlay);

		this.heartMeat.render(poseStack, vertexconsumer, light,overlay);
		poseStack.popPose();
	}
}
