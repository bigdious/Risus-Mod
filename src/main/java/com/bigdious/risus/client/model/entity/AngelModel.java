package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.creatures.Angel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class AngelModel<T extends Angel> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart innerRing;
	private final ModelPart middleRing;
	private final ModelPart outerRing;
	private final ModelPart head;
	private final ModelPart eyeCluster;
	private final ModelPart topRightWing;
	private final ModelPart topRightWingOutcurve;
	private final ModelPart topRightWingIncurve;
	private final ModelPart topRightWingIncurveTwo;
	private final ModelPart middleRightWing;
	private final ModelPart middleRightWingOutcurve;
	private final ModelPart middleRightWingIncurve;
	private final ModelPart middleRightWingIncurveTwo;
	private final ModelPart bottomRightWing;
	private final ModelPart bottomRightWingOutcurve;
	private final ModelPart bottomRightWingIncurve;
	private final ModelPart bottomRightWingIncurveTwo;
	private final ModelPart topLeftWing;
	private final ModelPart topLeftWingOutcurve;
	private final ModelPart topLeftWingIncurve;
	private final ModelPart topLeftWingIncurveTwo;
	private final ModelPart middleLeftWing;
	private final ModelPart middleLeftWingOutcurve;
	private final ModelPart middleLeftWingIncurve;
	private final ModelPart middleLeftWingIncurveTwo;
	private final ModelPart bottomLeftWing;
	private final ModelPart bottomLeftWingOutcurve;
	private final ModelPart bottomLeftWingIncurve;
	private final ModelPart bottomLeftWingIncurveTwo;


	public AngelModel(ModelPart root) {
		this.root = root;
		this.innerRing = root.getChild("innerRing");
		this.middleRing = root.getChild("middleRing");
		this.outerRing = root.getChild("outerRing");
		this.head = root.getChild("head");
		this.eyeCluster = head.getChild("eyeCluster");
		this.topRightWing = root.getChild("topRightWing");
		this.topRightWingOutcurve = topRightWing.getChild("topRightWingOutcurve");
		this.topRightWingIncurve = topRightWingOutcurve.getChild("topRightWingIncurve");
		this.topRightWingIncurveTwo = topRightWingIncurve.getChild("topRightWingIncurveTwo");
		this.middleRightWing = root.getChild("middleRightWing");
		this.middleRightWingOutcurve = middleRightWing.getChild("middleRightWingOutcurve");
		this.middleRightWingIncurve = middleRightWingOutcurve.getChild("middleRightWingIncurve");
		this.middleRightWingIncurveTwo = middleRightWingIncurve.getChild("middleRightWingIncurveTwo");
		this.bottomRightWing = root.getChild("bottomRightWing");
		this.bottomRightWingOutcurve = bottomRightWing.getChild("bottomRightWingOutcurve");
		this.bottomRightWingIncurve = bottomRightWingOutcurve.getChild("bottomRightWingIncurve");
		this.bottomRightWingIncurveTwo = bottomRightWingIncurve.getChild("bottomRightWingIncurveTwo");
		this.topLeftWing = root.getChild("topLeftWing");
		this.topLeftWingOutcurve = topLeftWing.getChild("topLeftWingOutcurve");
		this.topLeftWingIncurve = topLeftWingOutcurve.getChild("topLeftWingIncurve");
		this.topLeftWingIncurveTwo = topLeftWingIncurve.getChild("topLeftWingIncurveTwo");
		this.middleLeftWing = root.getChild("middleLeftWing");
		this.middleLeftWingOutcurve = middleLeftWing.getChild("middleLeftWingOutcurve");
		this.middleLeftWingIncurve = middleLeftWingOutcurve.getChild("middleLeftWingIncurve");
		this.middleLeftWingIncurveTwo = middleLeftWingIncurve.getChild("middleLeftWingIncurveTwo");
		this.bottomLeftWing = root.getChild("bottomLeftWing");
		this.bottomLeftWingOutcurve = bottomLeftWing.getChild("bottomLeftWingOutcurve");
		this.bottomLeftWingIncurve = bottomLeftWingOutcurve.getChild("bottomLeftWingIncurve");
		this.bottomLeftWingIncurveTwo = bottomLeftWingIncurve.getChild("bottomLeftWingIncurveTwo");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.5F, 0.5F));

		PartDefinition eyeCluster = head.addOrReplaceChild("eyeCluster", CubeListBuilder.create().texOffs(21, 0).addBox(6.0F, -5.5F, 6.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(3.0F, 4.5F, 4.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(-2.0F, 5.5F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(8.0F, -1.5F, -10.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(2.0F, -11.5F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(-10.0F, 2.5F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(-12.0F, -4.5F, 1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(7.0F, 2.5F, -4.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(0.0F, -7.5F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(-8.0F, -1.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(-6.0F, 2.5F, 4.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(1.0F, 8.5F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(-5.0F, -9.5F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(21, 0).addBox(7.0F, -8.5F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.3963F));

		PartDefinition innerRing = partdefinition.addOrReplaceChild("innerRing", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 0.0F, -2.618F, 1.0036F, 3.1416F));

		PartDefinition innerRing8_r1 = innerRing.addOrReplaceChild("innerRing8_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition innerRing7_r1 = innerRing.addOrReplaceChild("innerRing7_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition innerRing6_r1 = innerRing.addOrReplaceChild("innerRing6_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition innerRing5_r1 = innerRing.addOrReplaceChild("innerRing5_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition innerRing4_r1 = innerRing.addOrReplaceChild("innerRing4_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition innerRing3_r1 = innerRing.addOrReplaceChild("innerRing3_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition innerRing2_r1 = innerRing.addOrReplaceChild("innerRing2_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, 13.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition middleRing = partdefinition.addOrReplaceChild("middleRing", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 0.0F, 1.8646F, 0.0436F, -0.5219F));

		PartDefinition middleRing8_r1 = middleRing.addOrReplaceChild("middleRing8_r1", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition middleRing7_r1 = middleRing.addOrReplaceChild("middleRing7_r1", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition middleRing6_r1 = middleRing.addOrReplaceChild("middleRing6_r1", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition middleRing5_r1 = middleRing.addOrReplaceChild("middleRing5_r1", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition middleRing4_r1 = middleRing.addOrReplaceChild("middleRing4_r1", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition middleRing3_r1 = middleRing.addOrReplaceChild("middleRing3_r1", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition middleRing2_r1 = middleRing.addOrReplaceChild("middleRing2_r1", CubeListBuilder.create().texOffs(30, 5).addBox(-7.5F, 17.0F, -2.0F, 15.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition outerRing = partdefinition.addOrReplaceChild("outerRing", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 0.0F, 0.8471F, 0.7844F, 0.4363F));

		PartDefinition outerRing8_r1 = outerRing.addOrReplaceChild("outerRing8_r1", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition outerRing7_r1 = outerRing.addOrReplaceChild("outerRing7_r1", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition outerRing6_r1 = outerRing.addOrReplaceChild("outerRing6_r1", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition outerRing5_r1 = outerRing.addOrReplaceChild("outerRing5_r1", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition outerRing4_r1 = outerRing.addOrReplaceChild("outerRing4_r1", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition outerRing3_r1 = outerRing.addOrReplaceChild("outerRing3_r1", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition outerRing2_r1 = outerRing.addOrReplaceChild("outerRing2_r1", CubeListBuilder.create().texOffs(27, 10).addBox(-9.0F, 20.5F, -2.0F, 18.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition topRightWing = partdefinition.addOrReplaceChild("topRightWing", CubeListBuilder.create().texOffs(65, 0).addBox(0.0F, -2.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -10.0F, 0.0F, 0.0F, 0.0151F, -1.3513F));

		PartDefinition rightFeatherBack1_r1 = topRightWing.addOrReplaceChild("rightFeatherBack1_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightFeatherFront1_r1 = topRightWing.addOrReplaceChild("rightFeatherFront1_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition topRightWingOutcurve = topRightWing.addOrReplaceChild("topRightWingOutcurve", CubeListBuilder.create().texOffs(67, 8).addBox(-0.5F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.48F));

		PartDefinition topRightWingIncurve = topRightWingOutcurve.addOrReplaceChild("topRightWingIncurve", CubeListBuilder.create().texOffs(91, 0).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 1.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition rightFeatherFront2_r1 = topRightWingIncurve.addOrReplaceChild("rightFeatherFront2_r1", CubeListBuilder.create().texOffs(0, 41).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, -0.1745F, 0.0F, 1.5708F));

		PartDefinition rightFeatherBack2_r1 = topRightWingIncurve.addOrReplaceChild("rightFeatherBack2_r1", CubeListBuilder.create().texOffs(0, 41).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 0.1745F, 0.0F, 1.5708F));

		PartDefinition rightFeatherMiddle1_r1 = topRightWingIncurve.addOrReplaceChild("rightFeatherMiddle1_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.5F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition topRightWingIncurveTwo = topRightWingIncurve.addOrReplaceChild("topRightWingIncurveTwo", CubeListBuilder.create().texOffs(91, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 15.5F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition rightFeatherMiddle2_r1 = topRightWingIncurveTwo.addOrReplaceChild("rightFeatherMiddle2_r1", CubeListBuilder.create().texOffs(69, 35).addBox(-15.0F, 0.0F, 0.0F, 24.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 14.0F, 0.0F, 0.0436F, 0.0F, 1.5708F));

		PartDefinition middleRightWing = partdefinition.addOrReplaceChild("middleRightWing", CubeListBuilder.create().texOffs(65, 0).addBox(0.0F, -2.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.0F, 6.0F, 0.0F, 0.124F, 0.0F, -0.4876F));

		PartDefinition rightFeatherBack1_r2 = middleRightWing.addOrReplaceChild("rightFeatherBack1_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightFeatherFront1_r2 = middleRightWing.addOrReplaceChild("rightFeatherFront1_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition middleRightWingOutcurve = middleRightWing.addOrReplaceChild("middleRightWingOutcurve", CubeListBuilder.create().texOffs(67, 8).addBox(-0.5F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition middleRightWingIncurve = middleRightWingOutcurve.addOrReplaceChild("middleRightWingIncurve", CubeListBuilder.create().texOffs(91, 0).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 1.5F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition rightFeatherFront2_r2 = middleRightWingIncurve.addOrReplaceChild("rightFeatherFront2_r2", CubeListBuilder.create().texOffs(0, 41).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, -0.1745F, 0.0F, 1.5708F));

		PartDefinition rightFeatherBack2_r2 = middleRightWingIncurve.addOrReplaceChild("rightFeatherBack2_r2", CubeListBuilder.create().texOffs(0, 41).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 0.1745F, 0.0F, 1.5708F));

		PartDefinition rightFeatherMiddle1_r2 = middleRightWingIncurve.addOrReplaceChild("rightFeatherMiddle1_r2", CubeListBuilder.create().texOffs(0, 32).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.5F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition middleRightWingIncurveTwo = middleRightWingIncurve.addOrReplaceChild("middleRightWingIncurveTwo", CubeListBuilder.create().texOffs(91, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 15.5F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition rightFeatherMiddle2_r2 = middleRightWingIncurveTwo.addOrReplaceChild("rightFeatherMiddle2_r2", CubeListBuilder.create().texOffs(69, 35).addBox(-15.0F, 0.0F, 0.0F, 24.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 14.0F, 0.0F, 0.0436F, 0.0F, 1.5708F));

		PartDefinition bottomRightWing = partdefinition.addOrReplaceChild("bottomRightWing", CubeListBuilder.create().texOffs(65, 0).addBox(0.0F, -2.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, 23.0F, 0.0F, -0.0456F, 0.0F, 0.5711F));

		PartDefinition rightFeatherBack1_r3 = bottomRightWing.addOrReplaceChild("rightFeatherBack1_r3", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightFeatherFront1_r3 = bottomRightWing.addOrReplaceChild("rightFeatherFront1_r3", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition bottomRightWingOutcurve = bottomRightWing.addOrReplaceChild("bottomRightWingOutcurve", CubeListBuilder.create().texOffs(67, 8).addBox(-0.5F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

		PartDefinition bottomRightWingIncurve = bottomRightWingOutcurve.addOrReplaceChild("bottomRightWingIncurve", CubeListBuilder.create().texOffs(91, 0).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition rightFeatherFront2_r3 = bottomRightWingIncurve.addOrReplaceChild("rightFeatherFront2_r3", CubeListBuilder.create().texOffs(0, 41).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, -0.1745F, 0.0F, 1.5708F));

		PartDefinition rightFeatherBack2_r3 = bottomRightWingIncurve.addOrReplaceChild("rightFeatherBack2_r3", CubeListBuilder.create().texOffs(0, 41).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 0.1745F, 0.0F, 1.5708F));

		PartDefinition rightFeatherMiddle1_r3 = bottomRightWingIncurve.addOrReplaceChild("rightFeatherMiddle1_r3", CubeListBuilder.create().texOffs(0, 32).addBox(-8.5F, 0.0F, 0.0F, 17.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.5F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition bottomRightWingIncurveTwo = bottomRightWingIncurve.addOrReplaceChild("bottomRightWingIncurveTwo", CubeListBuilder.create().texOffs(91, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 15.5F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition rightFeatherMiddle2_r3 = bottomRightWingIncurveTwo.addOrReplaceChild("rightFeatherMiddle2_r3", CubeListBuilder.create().texOffs(69, 35).addBox(-15.0F, 0.0F, 0.0F, 24.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 14.0F, 0.0F, 0.0436F, 0.0F, 1.5708F));

		PartDefinition topLeftWing = partdefinition.addOrReplaceChild("topLeftWing", CubeListBuilder.create().texOffs(65, 0).mirror().addBox(-9.0F, -2.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-17.0F, -10.0F, 0.0F, 0.0F, -0.0302F, 1.35F));

		PartDefinition leftFeatherBack2_r1 = topLeftWing.addOrReplaceChild("leftFeatherBack2_r1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-8.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leftFeatherFront1_r1 = topLeftWing.addOrReplaceChild("leftFeatherFront1_r1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-8.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition topLeftWingOutcurve = topLeftWing.addOrReplaceChild("topLeftWingOutcurve", CubeListBuilder.create().texOffs(67, 8).mirror().addBox(-8.5F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition topLeftWingIncurve = topLeftWingOutcurve.addOrReplaceChild("topLeftWingIncurve", CubeListBuilder.create().texOffs(91, 0).mirror().addBox(-1.5F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition leftFeatherFront2_r1 = topLeftWingIncurve.addOrReplaceChild("leftFeatherFront2_r1", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, -0.1745F, 0.0F, -1.5708F));

		PartDefinition leftFeatherBack2_r2 = topLeftWingIncurve.addOrReplaceChild("leftFeatherBack2_r2", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 0.1745F, 0.0F, -1.5708F));

		PartDefinition leftFeatherMiddle1_r1 = topLeftWingIncurve.addOrReplaceChild("leftFeatherMiddle1_r1", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition topLeftWingIncurveTwo = topLeftWingIncurve.addOrReplaceChild("topLeftWingIncurveTwo", CubeListBuilder.create().texOffs(91, 0).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 15.5F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition leftFeatherMiddle2_r1 = topLeftWingIncurveTwo.addOrReplaceChild("leftFeatherMiddle2_r1", CubeListBuilder.create().texOffs(69, 35).mirror().addBox(-9.0F, 0.0F, 0.0F, 24.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 14.0F, 0.0F, 0.0436F, 0.0F, -1.5708F));

		PartDefinition middleLeftWing = partdefinition.addOrReplaceChild("middleLeftWing", CubeListBuilder.create().texOffs(65, 0).mirror().addBox(-9.0F, -2.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-24.0F, 6.0F, 0.0F, 0.124F, 0.0F, 0.4876F));

		PartDefinition leftFeatherBack1_r1 = middleLeftWing.addOrReplaceChild("leftFeatherBack1_r1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-8.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leftFeatherFront1_r2 = middleLeftWing.addOrReplaceChild("leftFeatherFront1_r2", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-8.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition middleLeftWingOutcurve = middleLeftWing.addOrReplaceChild("middleLeftWingOutcurve", CubeListBuilder.create().texOffs(67, 8).mirror().addBox(-8.5F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition middleLeftWingIncurve = middleLeftWingOutcurve.addOrReplaceChild("middleLeftWingIncurve", CubeListBuilder.create().texOffs(91, 0).mirror().addBox(-1.5F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition leftFeatherFront2_r2 = middleLeftWingIncurve.addOrReplaceChild("leftFeatherFront2_r2", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, -0.1745F, 0.0F, -1.5708F));

		PartDefinition leftFeatherBack2_r3 = middleLeftWingIncurve.addOrReplaceChild("leftFeatherBack2_r3", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 0.1745F, 0.0F, -1.5708F));

		PartDefinition leftFeatherMiddle1_r2 = middleLeftWingIncurve.addOrReplaceChild("leftFeatherMiddle1_r2", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition middleLeftWingIncurveTwo = middleLeftWingIncurve.addOrReplaceChild("middleLeftWingIncurveTwo", CubeListBuilder.create().texOffs(91, 0).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 15.5F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition leftFeatherMiddle2_r2 = middleLeftWingIncurveTwo.addOrReplaceChild("leftFeatherMiddle2_r2", CubeListBuilder.create().texOffs(69, 35).mirror().addBox(-9.0F, 0.0F, 0.0F, 24.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 14.0F, 0.0F, 0.0436F, 0.0F, -1.5708F));

		PartDefinition bottomLeftWing = partdefinition.addOrReplaceChild("bottomLeftWing", CubeListBuilder.create().texOffs(65, 0).mirror().addBox(-9.0F, -2.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-17.0F, 23.0F, 0.0F, -0.0456F, 0.0F, -0.5711F));

		PartDefinition leftFeatherBack1_r2 = bottomLeftWing.addOrReplaceChild("leftFeatherBack1_r2", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-8.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leftFeatherFront1_r3 = bottomLeftWing.addOrReplaceChild("leftFeatherFront1_r3", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-8.0F, -3.0F, 0.0F, 12.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition bottomLeftWingOutcurve = bottomLeftWing.addOrReplaceChild("bottomLeftWingOutcurve", CubeListBuilder.create().texOffs(67, 8).mirror().addBox(-8.5F, -1.5F, -1.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition bottomLeftWingIncurve = bottomLeftWingOutcurve.addOrReplaceChild("bottomLeftWingIncurve", CubeListBuilder.create().texOffs(91, 0).mirror().addBox(-1.5F, 0.0F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 1.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition leftFeatherFront2_r3 = bottomLeftWingIncurve.addOrReplaceChild("leftFeatherFront2_r3", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, -0.1745F, 0.0F, -1.5708F));

		PartDefinition leftFeatherBack2_r4 = bottomLeftWingIncurve.addOrReplaceChild("leftFeatherBack2_r4", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.5F, 0.0F, 0.1745F, 0.0F, -1.5708F));

		PartDefinition leftFeatherMiddle1_r3 = bottomLeftWingIncurve.addOrReplaceChild("leftFeatherMiddle1_r3", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-8.5F, 0.0F, 0.0F, 17.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition bottomLeftWingIncurveTwo = bottomLeftWingIncurve.addOrReplaceChild("bottomLeftWingIncurveTwo", CubeListBuilder.create().texOffs(91, 0).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 15.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition leftFeatherMiddle2_r3 = bottomLeftWingIncurveTwo.addOrReplaceChild("leftFeatherMiddle2_r3", CubeListBuilder.create().texOffs(69, 35).mirror().addBox(-9.0F, 0.0F, 0.0F, 24.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 14.0F, 0.0F, 0.0436F, 0.0F, -1.5708F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.eyeCluster.zRot = ageInTicks * 0.02F;
		float circle = ageInTicks * 0.05F;
		float middleWinging = Mth.sin(ageInTicks * 0.08F) * -0.35F;
		float winging = Mth.cos(ageInTicks * 0.08F) * 0.15F;
		float weakerWinging = Mth.cos(ageInTicks * 0.05F) * 0.2F;
		float topWinging = Mth.cos(ageInTicks * 0.08F) * -0.3F;
		this.innerRing.xRot = circle * 0.5F;
		this.innerRing.yRot = -circle;
		this.middleRing.yRot = circle;
		this.middleRing.zRot = circle * 0.8F;
		this.outerRing.zRot = circle * 1.5F;
		this.outerRing.xRot = circle * 0.5F;
		this.topRightWing.xRot = - topWinging - (25.5F * Mth.DEG_TO_RAD+50.02F);
		this.topRightWing.yRot = topWinging + (24F * Mth.DEG_TO_RAD+50.02F);
		this.topRightWing.zRot = - topWinging - (2F * Mth.DEG_TO_RAD-23.9F);
		this.topRightWingOutcurve.xRot = - topWinging - (-10F * Mth.DEG_TO_RAD-25.02F);
		this.topRightWingIncurve.xRot = - topWinging - (4.5F * Mth.DEG_TO_RAD-25.02F);
		this.topRightWingIncurveTwo.xRot = - topWinging - (12.5F * Mth.DEG_TO_RAD-25.02F);
		this.middleRightWing.yRot = middleWinging + (12.5F * Mth.DEG_TO_RAD);
		this.middleRightWing.zRot = middleWinging - (38F * Mth.DEG_TO_RAD);
		this.middleRightWingOutcurve.xRot = -middleWinging - (2F * Mth.DEG_TO_RAD+25.55F);
		this.middleRightWingIncurve.xRot = -middleWinging - (2F * Mth.DEG_TO_RAD+25.3F);
		this.middleRightWingIncurveTwo.xRot = -middleWinging - (2F * Mth.DEG_TO_RAD+25.55F);
		this.bottomRightWing.zRot = weakerWinging + (12.5F * Mth.DEG_TO_RAD);
		this.bottomRightWing.yRot = -weakerWinging + (12.5F * Mth.DEG_TO_RAD);
		this.bottomRightWing.xRot = weakerWinging - (12.5F * Mth.DEG_TO_RAD);
		this.bottomRightWingIncurve.zRot = weakerWinging + (12.5F * Mth.DEG_TO_RAD);
		this.bottomRightWingIncurveTwo.zRot = weakerWinging + (20F * Mth.DEG_TO_RAD);
//		this.topLeftWing.xRot = middleWinging - (12.5F * Mth.DEG_TO_RAD+50.02F);
//		this.topLeftWingOutcurve.xRot = middleWinging + (12.5F * Mth.DEG_TO_RAD+50.02F);
//		this.topLeftWingIncurve.xRot = middleWinging + (12.5F * Mth.DEG_TO_RAD+50.02F);
//		this.topLeftWingIncurveTwo.xRot = middleWinging + (12.5F * Mth.DEG_TO_RAD+50.02F);
//		this.middleLeftWing.yRot = -flapping - (12.5F * Mth.DEG_TO_RAD);
//		this.middleLeftWingOutcurve.xRot = -flapping - (2F * Mth.DEG_TO_RAD+25.55F);
//		this.middleLeftWingIncurve.xRot = -flapping - (2F * Mth.DEG_TO_RAD+25.3F);
//		this.middleLeftWingIncurveTwo.xRot = -flapping - (2F * Mth.DEG_TO_RAD+25.55F);
//		this.bottomLeftWing.zRot = -weakerWinging - (12.5F * Mth.DEG_TO_RAD);
//		this.bottomLeftWing.yRot = weakerWinging - (12.5F * Mth.DEG_TO_RAD);
//		this.bottomLeftWingIncurve.zRot = -winging - (12.5F * Mth.DEG_TO_RAD);
//		this.bottomLeftWingIncurveTwo.zRot = -winging - (20F * Mth.DEG_TO_RAD);

		this.topLeftWing.xRot = this.topRightWing.xRot;
		this.topLeftWing.yRot = -this.topRightWing.yRot;
		this.topLeftWing.zRot = -this.topRightWing.zRot;
		this.topLeftWingOutcurve.xRot = this.topRightWingOutcurve.xRot;
		this.topLeftWingIncurve.xRot = this.topRightWingIncurve.xRot;
		this.topLeftWingIncurveTwo.xRot = this.topRightWingIncurveTwo.xRot;
		this.middleLeftWing.yRot = -this.middleRightWing.yRot;
		this.middleLeftWing.zRot = -this.middleRightWing.zRot;
		this.middleLeftWingOutcurve.xRot = this.middleRightWingOutcurve.xRot;
		this.middleLeftWingIncurve.xRot = this.middleRightWingIncurve.xRot;
		this.middleLeftWingIncurveTwo.xRot = this.middleRightWingIncurveTwo.xRot;
		this.bottomLeftWing.zRot = -this.bottomRightWing.zRot;
		this.bottomLeftWing.yRot = -this.bottomRightWing.yRot;
		this.bottomLeftWing.xRot = this.bottomRightWing.xRot;
		this.bottomLeftWingIncurve.zRot = -this.bottomRightWingIncurve.zRot;
		this.bottomLeftWingIncurveTwo.zRot = -this.bottomRightWingIncurveTwo.zRot;
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
