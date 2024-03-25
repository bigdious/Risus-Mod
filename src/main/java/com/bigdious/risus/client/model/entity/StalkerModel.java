package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Stalker;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class StalkerModel<T extends Stalker> extends HierarchicalModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart root;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;

	public StalkerModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("Body");
		this.head = root.getChild("Head");
		this.leftHindLeg = body.getChild("leg1");
		this.rightHindLeg = body.getChild("leg0");
		this.leftFrontLeg = body.getChild("leg3");
		this.rightFrontLeg = body.getChild("leg2");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 43).addBox(-4.0F, -9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(0, 26).addBox(-7.0F, -27.0F, 0.0F, 17.0F, 17.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition FalseHead_r1 = Body.addOrReplaceChild("FalseHead_r1", CubeListBuilder.create().texOffs(32, 48).addBox(-3.0F, -7.5147F, -4.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -15.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition Body2_r1 = Body.addOrReplaceChild("Body2_r1", CubeListBuilder.create().texOffs(16, 50).addBox(-2.0F, -8.0F, -2.01F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -9.0F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition Body1_r1 = Body.addOrReplaceChild("Body1_r1", CubeListBuilder.create().texOffs(0, 50).addBox(-3.0F, -9.0F, -2.02F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leg0 = Body.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, 4.0F));

		PartDefinition leg1 = Body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 4.0F));

		PartDefinition leg2 = Body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, -4.0F));

		PartDefinition leg3 = Body.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, -4.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 4.0F, 0.0F));

		PartDefinition Eye3_r1 = Head.addOrReplaceChild("Eye3_r1", CubeListBuilder.create().texOffs(20, 8).addBox(0.8284F, -4.8284F, -2.999F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.005F, 0.0F, 0.0F, 2.3562F));

		PartDefinition Eye2_r1 = Head.addOrReplaceChild("Eye2_r1", CubeListBuilder.create().texOffs(20, 0).addBox(0.8284F, -4.8284F, -2.999F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.005F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 340F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 350F);
		this.rightHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
		this.leftHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.rightFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.leftFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart root() {
		return this.root;
	}
}
