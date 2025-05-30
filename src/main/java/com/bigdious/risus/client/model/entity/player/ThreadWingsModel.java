package com.bigdious.risus.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ThreadWingsModel extends HumanoidModel<LivingEntity> {

	private final ModelPart main;
	private final ModelPart topRightWing;
	private final ModelPart middleRightWing;
	private final ModelPart bottomRightWing;
	private final ModelPart topLeftWing;
	private final ModelPart middleLeftWing;
	private final ModelPart bottomLeftWing;
	private final ModelPart innerRing;

	public ThreadWingsModel(ModelPart root) {
		super(root);
		this.main = root.getChild("main");
		this.innerRing = this.main.getChild("innerRing");
		this.bottomLeftWing = this.main.getChild("bottomLeftWing");
		this.middleLeftWing = this.main.getChild("middleLeftWing");
		this.topLeftWing = this.main.getChild("topLeftWing");
		this.bottomRightWing = this.main.getChild("bottomRightWing");
		this.middleRightWing = this.main.getChild("middleRightWing");
		this.topRightWing = this.main.getChild("topRightWing");
	}
	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 2.0F));

		PartDefinition innerRing = main.addOrReplaceChild("innerRing", CubeListBuilder.create().texOffs(36, 19).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(39, 19).addBox(-5.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(36, 21).addBox(4.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(40, 24).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, 1.5708F, 0.0F, 0.7854F));

		PartDefinition bottomLeftWing = main.addOrReplaceChild("bottomLeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.9605F, 8.2347F, 7.0F, 0.0F, 0.0F, -1.2654F));

		PartDefinition bottomLeftWing_r1 = bottomLeftWing.addOrReplaceChild("bottomLeftWing_r1", CubeListBuilder.create().texOffs(-8, 13).addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.1742F, -6.1262F, 0.0F, 1.5708F, 0.0F, 0.6981F));

		PartDefinition middleLeftWing = main.addOrReplaceChild("middleLeftWing", CubeListBuilder.create().texOffs(-13, 0).addBox(-29.5F, 0.0F, -6.5F, 29.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -1.0F, 7.5F, 1.5708F, 0.0F, 0.0F));

		PartDefinition topLeftWing = main.addOrReplaceChild("topLeftWing", CubeListBuilder.create(), PartPose.offset(-5.0F, -7.0F, 7.0F));

		PartDefinition bottomLeftWing_r2 = topLeftWing.addOrReplaceChild("bottomLeftWing_r2", CubeListBuilder.create().texOffs(-8, 13).addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -7.0F, 0.0F, 1.5708F, 0.0F, 0.6981F));

		PartDefinition bottomRightWing = main.addOrReplaceChild("bottomRightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(4.9605F, 8.2347F, 7.0F, 0.0F, 0.0F, 1.2654F));

		PartDefinition bottomRightWing_r1 = bottomRightWing.addOrReplaceChild("bottomRightWing_r1", CubeListBuilder.create().texOffs(-8, 13).mirror().addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.1742F, -6.1262F, 0.0F, 1.5708F, 0.0F, -0.6981F));

		PartDefinition middleRightWing = main.addOrReplaceChild("middleRightWing", CubeListBuilder.create().texOffs(-13, 0).mirror().addBox(0.5F, 0.0F, -6.5F, 29.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.5F, -1.0F, 7.5F, 1.5708F, 0.0F, 0.0F));

		PartDefinition topRightWing = main.addOrReplaceChild("topRightWing", CubeListBuilder.create(), PartPose.offset(5.5F, -8.0F, 7.5F));

		PartDefinition bottomRightWing_r2 = topRightWing.addOrReplaceChild("bottomRightWing_r2", CubeListBuilder.create().texOffs(-8, 13).mirror().addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.5F, -6.0F, -0.5F, 1.5708F, 0.0F, -0.6981F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float flapping = Mth.sin(ageInTicks * 0.1F) * 0.1F;
		float circle = ageInTicks * 0.05F;
		this.topRightWing.yRot = flapping + (18F * Mth.DEG_TO_RAD);
		this.topLeftWing.yRot = -flapping - (18F * Mth.DEG_TO_RAD);
		this.middleRightWing.yRot = flapping + (18F * Mth.DEG_TO_RAD);
		this.middleLeftWing.yRot = -flapping - (18F * Mth.DEG_TO_RAD);
		this.bottomRightWing.yRot = flapping + (18F * Mth.DEG_TO_RAD);
		this.bottomLeftWing.yRot = -flapping - (18F * Mth.DEG_TO_RAD);
		this.innerRing.zRot = circle * 0.5F;
		this.innerRing.xRot = -circle * 1.5F;
		this.innerRing.yRot = circle;
	}



	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color) {
		this.main.render(stack, consumer, light, overlay, color);

	}
}
