package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Lover;
import com.bigdious.risus.entity.Singer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SingerModel<T extends Singer> extends HierarchicalModel<T> {
	private final ModelPart Body;
	private final ModelPart root;
	private final ModelPart pearl;
	private final ModelPart pearlPart2;
	private final ModelPart pearlPart3;
	private final ModelPart upperBody;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart Head;
	private final ModelPart topHead;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart leftFace;
	private final ModelPart rightFace;
	private final ModelPart brain;

	public SingerModel(ModelPart root) {
		this.root = root;
		this.Body = root.getChild("Body");
		this.pearl = this.Body.getChild("pearl");
		this.pearlPart2 = this.pearl.getChild("pearlPart2");
		this.pearlPart3 = this.pearl.getChild("pearlPart3");
		this.upperBody = this.Body.getChild("upperBody");
		this.RightArm = this.upperBody.getChild("RightArm");
		this.LeftArm = this.upperBody.getChild("LeftArm");
		this.Head = this.upperBody.getChild("Head");
		this.topHead = this.Head.getChild("topHead");
		this.brain = this.topHead.getChild("brain");
		this.leftFace = this.topHead.getChild("leftFace");
		this.rightFace = this.topHead.getChild("rightFace");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 89).addBox(-8.5F, -16.0F, -7.25F, 17.0F, 8.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 111).addBox(-4.5F, -8.0F, -4.25F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 1.25F));

		PartDefinition pearl = Body.addOrReplaceChild("pearl", CubeListBuilder.create().texOffs(36, 114).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, -0.25F));

		PartDefinition pearlPart3 = pearl.addOrReplaceChild("pearlPart3", CubeListBuilder.create().texOffs(36, 114).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.7854F, 0.0F));

		PartDefinition pearlPart2 = pearl.addOrReplaceChild("pearlPart2", CubeListBuilder.create().texOffs(36, 114).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, -0.7854F, 0.0F));

		PartDefinition upperBody = Body.addOrReplaceChild("upperBody", CubeListBuilder.create().texOffs(0, 67).addBox(-8.499F, -7.0F, -6.0F, 17.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, -1.25F));

		PartDefinition RightArm = upperBody.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-9.0F, -4.0F, 0.0F));

		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(56, 49).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6378F, -0.1582F, 0.5759F));

		PartDefinition LeftArm = upperBody.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(9.0F, -4.0F, 0.0F));

		PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(56, 49).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4765F, 0.0603F, -0.6691F));

		PartDefinition Head = upperBody.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 43).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -7.0F, 1.0F));

		PartDefinition topHead = Head.addOrReplaceChild("topHead", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition brain = topHead.addOrReplaceChild("brain", CubeListBuilder.create().texOffs(0, 5).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leftFace = topHead.addOrReplaceChild("leftFace", CubeListBuilder.create().texOffs(0, 21).addBox(-3.0F, -10.0F, -6.0F, 6.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0002F));

		PartDefinition rightFace = topHead.addOrReplaceChild("rightFace", CubeListBuilder.create().texOffs(28, 11).addBox(-3.0F, -10.0F, -6.0F, 6.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.0002F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-3.0F, 23.0F, 0.0F));

		PartDefinition RightLeg_r1 = RightLeg.addOrReplaceChild("RightLeg_r1", CubeListBuilder.create().texOffs(56, 46).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.618F, 0.3923F, -0.0181F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 61).mirror().addBox(-0.8326F, 0.4369F, -15.3066F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.3636F, 21.869F, -0.8225F, -0.9849F, -0.5221F, 0.4146F));

		PartDefinition LeftLeg_r1 = LeftLeg.addOrReplaceChild("LeftLeg_r1", CubeListBuilder.create().texOffs(56, 49).mirror().addBox(9.126F, -18.8951F, 18.5305F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.9585F, -19.0936F, -18.2017F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.upperBody.xRot = entity.isAggressive() ? -0.3F+Mth.sin(ageInTicks * 0.6F) * 0.06F : -0.05F+0.025F * Mth.sin(ageInTicks * 0.05F+ 0.05F);
		this.RightArm.xRot = +0.05F-0.025F * Mth.sin(ageInTicks * 0.05F+ 0.05F);
		this.LeftArm.xRot = +0.05F-0.025F * Mth.sin(ageInTicks * 0.05F+ 0.05F);
		this.pearlPart2.xRot = ageInTicks * 2 % 360 * Mth.DEG_TO_RAD;
		this.pearlPart2.yRot = ageInTicks * 2 % 360 * Mth.DEG_TO_RAD;
		this.pearlPart2.zRot = ageInTicks * 2 % 360 * Mth.DEG_TO_RAD;
		this.pearlPart3.xRot = ageInTicks * 3 % 360 * Mth.DEG_TO_RAD;
		this.pearlPart3.yRot = ageInTicks * 3 % 360 * Mth.DEG_TO_RAD;
		this.pearlPart3.zRot = ageInTicks * 3 % 360 * Mth.DEG_TO_RAD;
		this.pearl.xRot = ageInTicks % 360 * Mth.DEG_TO_RAD;
		this.pearl.yRot = ageInTicks % 360 * Mth.DEG_TO_RAD;
		this.pearl.zRot = ageInTicks % 360 * Mth.DEG_TO_RAD;
		this.RightArm.zRot = entity.isAggressive() ? 1.7F + Mth.sin(ageInTicks * 0.60F) * 0.60F : 0F;
		this.LeftArm.zRot = entity.isAggressive() ? -1.7F - Mth.sin(ageInTicks * 0.55F) * 0.55F: 0F;
		this.rightFace.zRot = entity.isAggressive() ? 0.35F : 0F;
		this.leftFace.zRot = entity.isAggressive() ? -0.35F : 0F;
		this.topHead.y = entity.isAggressive() ? -9F: 0F;
		this.brain.y = entity.isAggressive() ? -9F: -5.5F;
		this.Head.yRot = entity.isAggressive() ? Mth.sin(ageInTicks * 0.3F) * 0.3F :  netHeadYaw * ((float) Math.PI / 340F);
		this.Head.xRot = entity.isAggressive() ? Mth.sin(ageInTicks * 0.1F) * 0.1F : headPitch * ((float) Math.PI / 350F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,int color) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
