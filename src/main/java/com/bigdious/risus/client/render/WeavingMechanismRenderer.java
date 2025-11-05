package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
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
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class WeavingMechanismRenderer implements BlockEntityRenderer<WeavingMechanismBlockEntity> {
	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/block/weaving_mechanism.png");
	private final ItemRenderer itemRenderer;
	private final ModelPart base;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;

	public WeavingMechanismRenderer(BlockEntityRendererProvider.Context context) {
		ModelPart modelpart = context.bakeLayer(RisusModelLayers.WEAVING_MECHANISM);
		this.itemRenderer = context.getItemRenderer();
		this.base = modelpart.getChild("base");
		this.leg1 = modelpart.getChild("leg1");
		this.leg2 = modelpart.getChild("leg2");
		this.leg3 = modelpart.getChild("leg3");
		this.leg4 = modelpart.getChild("leg4");
		this.leg5 = modelpart.getChild("leg5");
		this.leg6 = modelpart.getChild("leg6");
	}

	public static LayerDefinition createBaseLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 12.5F, 3.5F, -0.2689F, -1.2432F, 1.5787F));

		leg1.addOrReplaceChild("half1", CubeListBuilder.create().texOffs(0, 2).addBox(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 2.0222F, -1.4884F, -2.2289F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 15.5F, 3.5F, 0.4508F, -0.9476F, 0.1017F));

		leg2.addOrReplaceChild("half2", CubeListBuilder.create().texOffs(0, 2).addBox(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, -0.2182F, -1.3963F, 0.0F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 18.5F, 3.5F, -0.1345F, -0.7418F, -0.213F));

		leg3.addOrReplaceChild("half3", CubeListBuilder.create().texOffs(0, 2).addBox(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 1.9633F, -1.2393F, -1.9835F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 18.5F, 3.5F, 0.2689F, 0.7001F, 0.4041F));

		leg4.addOrReplaceChild("half4", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.5236F));

		PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 15.5F, 3.5F, 0.2366F, 0.6329F, 0.2817F));

		leg5.addOrReplaceChild("half5", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.0F, 0.0F, 2.0811F, 1.3759F, 2.0299F));

		PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 12.5F, 3.5F, 0.6566F, 1.2971F, 0.2585F));

		leg6.addOrReplaceChild("half6", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.0F, 0.0F, 0.375F, 0.8497F, 0.5394F));

		partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(42, 0).addBox(-2.5F, -2.98F, -6.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(16, 16).addBox(-3.5F, -6.0F, 0.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(42, 6).addBox(2.0F, -6.96F, -7.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-7.0F, -2.02F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(3.5F, -3.0F, -7.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-4.5F, -3.0F, -7.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(19, 19).addBox(-3.5F, -8.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(19, 19).addBox(-3.5F, -8.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(19, 16).addBox(2.5F, -8.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(19, 16).addBox(2.5F, -8.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 23).addBox(-1.5F, -10.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-2.5F, -11.0F, 0.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(46, 22).addBox(-2.5F, -11.5F, 2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(46, 6).addBox(-1.5F, -5.5F, 2.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(42, 6).addBox(2.0F, -6.96F, -2.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(42, 6).addBox(-3.0F, -6.96F, -2.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(42, 6).addBox(-3.0F, -6.96F, -7.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public void render(WeavingMechanismBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
		ItemStack itemstack = entity.getTheItem();
		poseStack.pushPose();
		Direction direction = entity.getDirection();
		poseStack.translate(0.5, 0, 0.5);
		poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
		poseStack.translate(0, -1.5, 0);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F + direction.toYRot()));
		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
		this.base.render(poseStack, vertexconsumer, light, overlay);
		scutterLeg1(entity, this.leg1, poseStack, vertexconsumer, light, overlay);
		scutterLeg2(entity, this.leg2, poseStack, vertexconsumer, light, overlay);
		scutterLeg3(entity, this.leg3, poseStack, vertexconsumer, light, overlay);
		scutterLeg4(entity, this.leg4, poseStack, vertexconsumer, light, overlay);
		scutterLeg5(entity, this.leg5, poseStack, vertexconsumer, light, overlay);
		scutterLeg6(entity, this.leg6, poseStack, vertexconsumer, light, overlay);

		if (!itemstack.isEmpty() && itemstack != null && itemstack == entity.getTheItem()) {
			this.renderItem(entity, itemstack, poseStack, buffer, light);
		}
		poseStack.popPose();
	}

	protected void renderItem(WeavingMechanismBlockEntity entity, ItemStack itemstack, PoseStack poseStack, MultiBufferSource buffer, int light) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
		poseStack.scale(0.5F, 0.5F, 0.5F);
		poseStack.translate(0, -2.35, -0.5);

		this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.getLevel(), (int) entity.getBlockPos().asLong());
		poseStack.popPose();
	}

	protected void scutterLeg1 (WeavingMechanismBlockEntity entity, ModelPart part, PoseStack poseStack, VertexConsumer vertexconsumer, int light, int overlay) {
		poseStack.pushPose();
			if (entity.isWeaving && !entity.getLevel().hasNeighborSignal(entity.getBlockPos())) {
				float f = AnimationRenderHelper.rotation;
				poseStack.rotateAround(Axis.YP.rotationDegrees(Mth.cos(f*0.3F) * 3F), 0.1F, -0.6F, 0.3F);
				poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.cos(f*0.5F) * 1.5F), 0.0F, -0.2F, 0.0F);
				poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.cos(f*0.3F) * 2.2F), 0.1F, -0.2F, 0.1F);
			}
			part.render(poseStack, vertexconsumer, light, overlay);
		poseStack.popPose();
	}

	protected void scutterLeg2 (WeavingMechanismBlockEntity entity, ModelPart part, PoseStack poseStack, VertexConsumer vertexconsumer, int light, int overlay) {
		poseStack.pushPose();
		if (entity.isWeaving && !entity.getLevel().hasNeighborSignal(entity.getBlockPos())) {
			float f = AnimationRenderHelper.rotation;
			poseStack.rotateAround(Axis.YP.rotationDegrees(Mth.sin(f*1.3F) * 3F), 0.1F, -0.6F, 0.3F);
			poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.cos(f) * 0.9F), 0.0F, -0.2F, 0.0F);
			poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.sin(f) * -0.5F), 0.1F, -0.2F, 0.1F);
		}
		part.render(poseStack, vertexconsumer, light, overlay);
		poseStack.popPose();
	}

	protected void scutterLeg3 (WeavingMechanismBlockEntity entity, ModelPart part, PoseStack poseStack, VertexConsumer vertexconsumer, int light, int overlay) {
		poseStack.pushPose();
		if (entity.isWeaving && !entity.getLevel().hasNeighborSignal(entity.getBlockPos())) {
			float f = AnimationRenderHelper.rotation;
			poseStack.rotateAround(Axis.YP.rotationDegrees(Mth.cos(f*0.5F) * 2F), 0.1F, -0.6F, 0.3F);
			poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.sin(f*0.4F) * 1.5F), 0.0F, -0.2F, 0.0F);
			poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.cos(f*0.2F) * 3F), 0.1F, -0.2F, 0.1F);
		}
		part.render(poseStack, vertexconsumer, light, overlay);
		poseStack.popPose();
	}

	protected void scutterLeg4 (WeavingMechanismBlockEntity entity, ModelPart part, PoseStack poseStack, VertexConsumer vertexconsumer, int light, int overlay) {
		poseStack.pushPose();
		if (entity.isWeaving && !entity.getLevel().hasNeighborSignal(entity.getBlockPos())) {
			float f = AnimationRenderHelper.rotation;
			poseStack.rotateAround(Axis.YP.rotationDegrees(Mth.sin(f*0.1F) * 3.2F), 0.1F, -0.6F, 0.3F);
			poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.cos(f*0.25F) * 2.5F), 0.0F, -0.2F, 0.0F);
			poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.cos(f) * 0.5F), 0.1F, -0.2F, 0.1F);
		}
		part.render(poseStack, vertexconsumer, light, overlay);
		poseStack.popPose();
	}

	protected void scutterLeg5 (WeavingMechanismBlockEntity entity, ModelPart part, PoseStack poseStack, VertexConsumer vertexconsumer, int light, int overlay) {
		poseStack.pushPose();
		if (entity.isWeaving && !entity.getLevel().hasNeighborSignal(entity.getBlockPos())) {
			float f = AnimationRenderHelper.rotation;
			poseStack.rotateAround(Axis.YP.rotationDegrees(Mth.cos(f*0.5F) * 1.2F), 0.1F, -0.6F, 0.3F);
			poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.cos(f*0.7F) * 2.7F), 0.0F, -0.2F, 0.0F);
			poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.cos(f*0.4F) * 2.3F), 0.1F, -0.2F, 0.1F);
		}
		part.render(poseStack, vertexconsumer, light, overlay);
		poseStack.popPose();
	}

	protected void scutterLeg6 (WeavingMechanismBlockEntity entity, ModelPart part, PoseStack poseStack, VertexConsumer vertexconsumer, int light, int overlay) {
		poseStack.pushPose();
		if (entity.isWeaving && !entity.getLevel().hasNeighborSignal(entity.getBlockPos())) {
			float f = AnimationRenderHelper.rotation;
			poseStack.rotateAround(Axis.YP.rotationDegrees(Mth.cos(f*0.8F) * 1.8F), 0.1F, -0.6F, 0.3F);
			poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.cos(f*0.4F) * 0.5F), 0.0F, -0.2F, 0.0F);
			poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.cos(f*1.1F) * 1.7F), 0.1F, -0.2F, 0.1F);
		}
		part.render(poseStack, vertexconsumer, light, overlay);
		poseStack.popPose();
	}
}
