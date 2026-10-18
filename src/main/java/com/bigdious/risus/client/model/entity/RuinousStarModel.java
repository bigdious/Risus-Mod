package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.RaidCoreEntity;
import com.bigdious.risus.entity.Stool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class RuinousStarModel<T extends RaidCoreEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart star;
	private final ModelPart face;
	public RuinousStarModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.star = body.getChild("star");
		this.face = body.getChild("face");
	}
	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition star = body.addOrReplaceChild("star", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -38.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition SideBranchDownRightSideTwo = star.addOrReplaceChild("SideBranchDownRightSideTwo", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, 21.0F, 0.0F, 0.0F, 0.0F, -1.0036F));

		PartDefinition SideBranchDownRightSideOne = star.addOrReplaceChild("SideBranchDownRightSideOne", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(21.0F, 21.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition SideBranchDownLeftSideTwo = star.addOrReplaceChild("SideBranchDownLeftSideTwo", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 21.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition SideBranchDownLeftSideOne = star.addOrReplaceChild("SideBranchDownLeftSideOne", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-21.0F, 21.0F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition SideBranchUpLeftSideTwo = star.addOrReplaceChild("SideBranchUpLeftSideTwo", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-21.0F, -21.0F, 0.0F, 0.0F, 0.0F, -1.0036F));

		PartDefinition SideBranchUpLeftSideOne = star.addOrReplaceChild("SideBranchUpLeftSideOne", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -21.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition SideBranchUpRightSideTwo = star.addOrReplaceChild("SideBranchUpRightSideTwo", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, -21.0F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition SideBranchUpRightSideOne = star.addOrReplaceChild("SideBranchUpRightSideOne", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(21.0F, -21.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition SideBranchUpRight = star.addOrReplaceChild("SideBranchUpRight", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -25.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition SideBranchDownRight = star.addOrReplaceChild("SideBranchDownRight", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -9.5F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.9602F, 14.9602F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition SideBranchDownLeft = star.addOrReplaceChild("SideBranchDownLeft", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-1.0F, -9.5F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-14.9602F, 14.9602F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition SideBranchUpLeft = star.addOrReplaceChild("SideBranchUpLeft", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-1.0F, -25.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition MainBranchDownSideTwo = star.addOrReplaceChild("MainBranchDownSideTwo", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3078F, 26.5091F, 0.0F, 0.0F, 0.0F, -2.8362F));

		PartDefinition MainBranchDownSideOne = star.addOrReplaceChild("MainBranchDownSideOne", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.3078F, 26.5091F, 0.0F, 0.0F, 0.0F, 2.8362F));

		PartDefinition MainBranchUpSideTwo = star.addOrReplaceChild("MainBranchUpSideTwo", CubeListBuilder.create().texOffs(20, 0).addBox(-13.4268F, -36.2412F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition MainBranchUpSideOne = star.addOrReplaceChild("MainBranchUpSideOne", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.3078F, -26.5091F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition MainBranchRightSideTwo = star.addOrReplaceChild("MainBranchRightSideTwo", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(26.5091F, 3.3078F, 0.0F, 0.0F, 0.0F, 1.2654F));

		PartDefinition MainBranchRightSideOne = star.addOrReplaceChild("MainBranchRightSideOne", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(37.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8762F));

		PartDefinition MainBranchLeftSideTwo = star.addOrReplaceChild("MainBranchLeftSideTwo", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.5091F, 3.3078F, 0.0F, 0.0F, 0.0F, -1.2654F));

		PartDefinition MainBranchLeftSideOne = star.addOrReplaceChild("MainBranchLeftSideOne", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, 0.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-37.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8762F));

		PartDefinition MainBranchLeft = star.addOrReplaceChild("MainBranchLeft", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -38.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition MainBranchRight = star.addOrReplaceChild("MainBranchRight", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -38.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition MainBranchDown = star.addOrReplaceChild("MainBranchDown", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -16.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition face = body.addOrReplaceChild("face", CubeListBuilder.create().texOffs(16, 44).addBox(-4.0F, -5.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 64);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Entity camera = Minecraft.getInstance().getCameraEntity();
		if (camera != null) {
			Vec3 vec3 = camera.getEyePosition(0.0F);
			Vec3 vec31 = entity.getEyePosition(0.0F);
			this.face.xRot = (float) vec31.subtract(vec3).normalize().y();

			this.body.yRot = (float) Mth.atan2(vec3.z() - vec31.z(), vec3.x() - vec31.x()) - (90 * Mth.DEG_TO_RAD);
		}
	}
}
