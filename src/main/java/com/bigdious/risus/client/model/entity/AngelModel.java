package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Angel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class AngelModel<T extends Angel> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart innerRing;
	private final ModelPart middleRing;
	private final ModelPart outerRing;
	private final ModelPart headCluster1;
	private final ModelPart headCluster2;
	private final ModelPart topRightWing;
	private final ModelPart topLeftWing;
	private final ModelPart bottomRightWing;
	private final ModelPart bottomLeftWing;

	public AngelModel(ModelPart root) {
		this.root = root;
		this.innerRing = root.getChild("innerRing");
		this.middleRing = root.getChild("middleRing");
		this.outerRing = root.getChild("outerRing");
		this.headCluster1 = root.getChild("headCluster1");
		this.headCluster2 = root.getChild("headCluster2");
		this.topRightWing = root.getChild("topRightWing");
		this.topLeftWing = root.getChild("topLeftWing");
		this.bottomRightWing = root.getChild("bottomRightWing");
		this.bottomLeftWing = root.getChild("bottomLeftWing");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("innerRing", CubeListBuilder.create().texOffs(36, 19).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 2.0F, 1.0F)
				.texOffs(39, 19).addBox(-5.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F)
				.texOffs(36, 21).addBox(4.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F)
				.texOffs(40, 24).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.4712F, 0.0F, 0.5585F));

		partdefinition.addOrReplaceChild("middleRing", CubeListBuilder.create().texOffs(37, 22).addBox(-5.0F, 5.0F, -1.0F, 10.0F, 1.0F, 2.0F)
				.texOffs(39, 24).addBox(-5.0F, -6.0F, -1.0F, 10.0F, 1.0F, 2.0F)
				.texOffs(45, 20).addBox(-6.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F)
				.texOffs(58, 20).addBox(5.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 3.0543F, 1.3439F));

		partdefinition.addOrReplaceChild("outerRing", CubeListBuilder.create().texOffs(36, 19).addBox(-1.0F, 6.0F, -6.0F, 2.0F, 1.0F, 12.0F)
				.texOffs(36, 19).addBox(-1.0F, -7.0F, -6.0F, 2.0F, 1.0F, 12.0F)
				.texOffs(58, 19).addBox(-1.0F, -6.0F, 6.0F, 2.0F, 12.0F, 1.0F)
				.texOffs(36, 19).addBox(-1.0F, -6.0F, -7.0F, 2.0F, 12.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -1.0821F, -2.3911F, 0.0F));

		partdefinition.addOrReplaceChild("headCluster1", CubeListBuilder.create().texOffs(0, 22).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 3.0F, 0.0F));

		partdefinition.addOrReplaceChild("headCluster2", CubeListBuilder.create().texOffs(20, 26).addBox(-4.0F, 3.5F, -3.0F, 3.0F, 3.0F, 3.0F)
				.texOffs(37, 44).addBox(2.0F, -2.0F, 2.0F, 3.0F, 3.0F, 3.0F)
				.texOffs(20, 26).addBox(1.0F, -4.0F, -3.0F, 3.0F, 3.0F, 3.0F)
				.texOffs(20, 26).addBox(-4.0F, -1.0F, -1.0F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 3.0F, 0.0F));

		partdefinition.addOrReplaceChild("topRightWing", CubeListBuilder.create().texOffs(-13, 0).mirror().addBox(5.0F, 2.0F, -11.0F, 29.0F, 0.0F, 13.0F).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.4835F, -0.2182F, -0.5236F));

		partdefinition.addOrReplaceChild("topLeftWing", CubeListBuilder.create().texOffs(-13, 0).addBox(-34.0F, 2.0F, -11.0F, 29.0F, 0.0F, 13.0F), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 1.4835F, 0.2182F, 0.5236F));

		partdefinition.addOrReplaceChild("bottomRightWing", CubeListBuilder.create().texOffs(-8, 13).mirror().addBox(6.0F, 2.0F, -5.0F, 18.0F, 0.0F, 8.0F).mirror(false), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 1.7017F, -0.1873F, 0.3927F));

		partdefinition.addOrReplaceChild("bottomLeftWing", CubeListBuilder.create().texOffs(-8, 13).addBox(-24.0F, 2.0F, -5.0F, 18.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 1.7017F, 0.1873F, -0.3927F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.headCluster1.xRot = headPitch * ((float) Math.PI / 180F);
		this.headCluster1.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.headCluster2.xRot = headPitch * ((float) Math.PI / 270F);
		this.headCluster2.yRot = netHeadYaw * ((float) Math.PI / 270F);
		this.headCluster2.zRot = ageInTicks * 0.01F;
		float circle = ageInTicks * 0.05F;
		float flapping = Mth.sin(ageInTicks * 0.1F) * 0.1F;
		this.innerRing.xRot = circle * 0.5F;
		this.middleRing.yRot = circle;
		this.outerRing.zRot = circle * 1.5F;
		this.topRightWing.yRot = flapping - (12.5F * Mth.DEG_TO_RAD);
		this.topLeftWing.yRot = -flapping + (12.5F * Mth.DEG_TO_RAD);
		this.bottomRightWing.yRot = flapping - (12.5F * Mth.DEG_TO_RAD);
		this.bottomLeftWing.yRot = -flapping + (12.5F * Mth.DEG_TO_RAD);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
