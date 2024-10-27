package com.bigdious.risus.client.model.entity;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.bigdious.risus.entity.Licker;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class LickerModel<T extends Licker> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart deadHead;
	private final ModelPart body0;
	private final ModelPart newHead;
	private final ModelPart jaw;
	private final ModelPart fullTongue;
	private final ModelPart tonguePart1;
	private final ModelPart tonguePart2;
	private final ModelPart leg8;
	private final ModelPart lowLeg8;
	private final ModelPart leg7;
	private final ModelPart lowLeg7;
	private final ModelPart leg6;
	private final ModelPart lowLeg6;
	private final ModelPart leg1;
	private final ModelPart lowLeg1;
	private final ModelPart leg4;
	private final ModelPart lowLeg4;
	private final ModelPart leg2;
	private final ModelPart lowLeg2;
	private final ModelPart leg3;
	private final ModelPart lowLeg3;
	private final ModelPart leg5;
	private final ModelPart lowLeg5;

	public LickerModel(ModelPart root) {
		this.root = root;
		this.deadHead = root.getChild("deadHead");
		this.body0 = root.getChild("body0");
		this.newHead = root.getChild("newHead");
		this.jaw = this.newHead.getChild("jaw");
		this.fullTongue = this.newHead.getChild("fullTongue");
		this.tonguePart1 = this.fullTongue.getChild("tonguePart1");
		this.tonguePart2 = this.tonguePart1.getChild("tonguePart2");
		this.leg8 = root.getChild("leg8");
		this.lowLeg8 = this.leg8.getChild("lowLeg8");
		this.leg7 = root.getChild("leg7");
		this.lowLeg7 = this.leg7.getChild("lowLeg7");
		this.leg6 = root.getChild("leg6");
		this.lowLeg6 = this.leg6.getChild("lowLeg6");
		this.leg1 = root.getChild("leg1");
		this.lowLeg1 = this.leg1.getChild("lowLeg1");
		this.leg4 = root.getChild("leg4");
		this.lowLeg4 = this.leg4.getChild("lowLeg4");
		this.leg2 = root.getChild("leg2");
		this.lowLeg2 = this.leg2.getChild("lowLeg2");
		this.leg3 = root.getChild("leg3");
		this.lowLeg3 = this.leg3.getChild("lowLeg3");
		this.leg5 = root.getChild("leg5");
		this.lowLeg5 = this.leg5.getChild("lowLeg5");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition deadHead = partdefinition.addOrReplaceChild("deadHead", CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 3.0F, 2.8798F, 0.0F, 0.0F));

		PartDefinition body0 = partdefinition.addOrReplaceChild("body0", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition newHead = partdefinition.addOrReplaceChild("newHead", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.9172F, -1.5116F, 3.1416F, 0.0F, 0.0F));

		PartDefinition newHeadUp = newHead.addOrReplaceChild("newHeadUp", CubeListBuilder.create().texOffs(0, 28).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0828F, 2.4884F, -0.48F, 0.0F, 0.0F));

		PartDefinition jaw = newHead.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, -0.0786F, 1.819F));

		PartDefinition lipLeft = jaw.addOrReplaceChild("lipLeft", CubeListBuilder.create().texOffs(0, 35).addBox(0.0F, -4.0F, -5.5F, 0.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
			.texOffs(0, 35).addBox(8.0F, -4.0F, -5.5F, 0.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.8827F, 6.7383F, 0.1745F, 0.0F, 0.0F));

		PartDefinition newHeadDown = jaw.addOrReplaceChild("newHeadDown", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0042F, 0.6695F, 0.3927F, 0.0F, 0.0F));

		PartDefinition fullTongue = newHead.addOrReplaceChild("fullTongue", CubeListBuilder.create().texOffs(37, 41).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0828F, 2.4884F, 0.2182F, 0.0F, 0.0F));

		PartDefinition tonguePart1 = fullTongue.addOrReplaceChild("tonguePart1", CubeListBuilder.create().texOffs(42, 32).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 10.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition tonguePart2 = tonguePart1.addOrReplaceChild("tonguePart2", CubeListBuilder.create().texOffs(47, 25).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition tongue4 = tonguePart2.addOrReplaceChild("tongue4", CubeListBuilder.create().texOffs(52, 20).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition leg8 = partdefinition.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, 2.0F, -3.1416F, -0.7854F, -0.7854F));

		PartDefinition lowLeg8 = leg8.addOrReplaceChild("lowLeg8", CubeListBuilder.create().texOffs(0, 44).addBox(-0.25F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.25F, 0.0F, 0.0F, 0.0F, 0.6545F, -1.6144F));

		PartDefinition leg83 = lowLeg8.addOrReplaceChild("leg83", CubeListBuilder.create().texOffs(19, 1).addBox(0.0F, -0.5F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.75F, 0.0F, 0.0F, 0.0F, -0.0873F, -0.9599F));

		PartDefinition leg7 = partdefinition.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 1.0F, 2.0F, -3.1416F, 0.7854F, 0.7854F));

		PartDefinition lowLeg7 = leg7.addOrReplaceChild("lowLeg7", CubeListBuilder.create().texOffs(0, 44).mirror().addBox(-18.75F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.25F, 0.0F, 0.0F, 0.0F, -0.6545F, 1.6144F));

		PartDefinition leg73 = lowLeg7.addOrReplaceChild("leg73", CubeListBuilder.create().texOffs(19, 1).mirror().addBox(-16.0F, -0.5F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-18.75F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.9599F));

		PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 1.0F, -2.0F, 3.1416F, -0.7854F, 0.7854F));

		PartDefinition lowLeg6 = leg6.addOrReplaceChild("lowLeg6", CubeListBuilder.create().texOffs(0, 44).mirror().addBox(-18.75F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.25F, 0.0F, 0.0F, 0.0F, 0.6545F, 1.6144F));

		PartDefinition leg63 = lowLeg6.addOrReplaceChild("leg63", CubeListBuilder.create().texOffs(19, 1).mirror().addBox(-16.0F, -0.5F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-18.75F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.9599F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, -2.0F, 3.1416F, 0.7854F, -0.7854F));

		PartDefinition lowLeg1 = leg1.addOrReplaceChild("lowLeg1", CubeListBuilder.create().texOffs(0, 44).addBox(-0.25F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.25F, 0.0F, 0.0F, 0.0F, -0.6545F, -1.6144F));

		PartDefinition leg13 = lowLeg1.addOrReplaceChild("leg13", CubeListBuilder.create().texOffs(19, 1).addBox(0.0F, -0.5F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.75F, 0.0F, 0.0F, 0.0F, 0.0873F, -0.9599F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 1.0F, 1.0F, 3.1416F, 0.2618F, 0.6109F));

		PartDefinition lowLeg4 = leg4.addOrReplaceChild("lowLeg4", CubeListBuilder.create(), PartPose.offsetAndRotation(-15.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leg42 = lowLeg4.addOrReplaceChild("leg42", CubeListBuilder.create().texOffs(0, 44).mirror().addBox(-19.0F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.7453F));

		PartDefinition leg43 = lowLeg4.addOrReplaceChild("leg43", CubeListBuilder.create().texOffs(19, 1).addBox(0.0F, 0.0F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -19.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 1.0F, -1.0F, 3.1416F, -0.2618F, 0.6109F));

		PartDefinition lowLeg2 = leg2.addOrReplaceChild("lowLeg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-15.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition leg22 = lowLeg2.addOrReplaceChild("leg22", CubeListBuilder.create().texOffs(0, 44).mirror().addBox(-19.0F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.7453F));

		PartDefinition leg23 = lowLeg2.addOrReplaceChild("leg23", CubeListBuilder.create().texOffs(19, 1).addBox(0.0F, 0.0F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -19.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, -1.0F, 3.1416F, 0.2618F, -0.6109F));

		PartDefinition lowLeg3 = leg3.addOrReplaceChild("lowLeg3", CubeListBuilder.create(), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition leg32 = lowLeg3.addOrReplaceChild("leg32", CubeListBuilder.create().texOffs(0, 44).addBox(0.0F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.7453F));

		PartDefinition leg33 = lowLeg3.addOrReplaceChild("leg33", CubeListBuilder.create().texOffs(19, 1).mirror().addBox(-16.0F, 0.0F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -19.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, 1.0F, 3.1416F, -0.2618F, -0.6109F));

		PartDefinition lowLeg5 = leg5.addOrReplaceChild("lowLeg5", CubeListBuilder.create(), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leg52 = lowLeg5.addOrReplaceChild("leg52", CubeListBuilder.create().texOffs(0, 44).addBox(0.0F, -0.5F, -0.5F, 19.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.7453F));

		PartDefinition leg53 = lowLeg5.addOrReplaceChild("leg53", CubeListBuilder.create().texOffs(19, 1).mirror().addBox(-16.0F, 0.0F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -19.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float f3 = -(Mth.cos(limbSwing * 0.6662F * 1.5F + 0.0F) * 0.4F) * limbSwingAmount;
		float f4 = -(Mth.cos(limbSwing * 0.6662F * 1.5F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f6 = -(Mth.cos(limbSwing * 0.6662F * 1.5F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f10 = Math.abs(Mth.sin(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		this.leg8.yRot += f3;
		this.leg7.yRot += f4;
		this.leg5.yRot += f4;
		this.leg4.yRot -= f3;
		this.leg3.yRot += f6;
		this.leg2.yRot -= f3;
		this.leg1.yRot += f3;
		this.leg6.yRot -= f6;
		this.leg8.zRot += f7;
		this.leg7.zRot -= f8;
		this.leg5.zRot += f8;
		this.leg4.zRot -= f7;
		this.leg3.zRot += f7;
		this.leg2.zRot -= f10;
		this.leg1.zRot += f10;
		this.leg6.zRot -= f7;
		this.newHead.yRot = entity.isAggressive() ? f3 : 0F;
		this.newHead.xRot = entity.isAggressive() ? 3.20F-f10 : 3.20F+0.05F * Mth.sin(ageInTicks * 0.1F+ 0.1F);
		this.fullTongue.zRot = !entity.isAggressive() ? 0F * Mth.sin(ageInTicks * 0.1F+ 0.4F) : f3;
		this.tonguePart1.zRot = !entity.isAggressive() ? 0F * Mth.sin(ageInTicks * 0.1F+ 0.4F) : f3;
		this.tonguePart2.zRot = !entity.isAggressive() ? 0F * Mth.sin(ageInTicks * 0.1F+ 0.4F) : f3;
		this.fullTongue.xRot = 0.2F+0.05F * Mth.sin(ageInTicks * 0.1F+ 0.4F);
		this.tonguePart1.xRot = 0.6F+0.05F * Mth.sin(ageInTicks * 0.1F+ 0.4F);
		this.tonguePart2.xRot = 1F+0.05F * Mth.sin(ageInTicks * 0.1F+ 0.4F);
		this.deadHead.xRot = 3.20F-0.05F * Mth.sin(ageInTicks * 0.1F+ 0.1F);
	}
//	@Override
//	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
//		int attackTimer = entity.getAttackTimer();
//		if (attackTimer > 0) {
//			this.newHead.xRot = 4;
//			this.fullTongue.xRot = 4;
//		} else {
//			this.newHead.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
//			this.fullTongue.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
//		}
//	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
