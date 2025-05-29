package com.bigdious.risus.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ThreadWingsModel extends HumanoidModel<LivingEntity> {

	private final ModelPart topRightWing;
	private final ModelPart middleRightWing;
	private final ModelPart bottomRightWing;
	private final ModelPart topLeftWing;
	private final ModelPart middleLeftWing;
	private final ModelPart bottomLeftWing;
	private final ModelPart innerRing;

	public ThreadWingsModel(ModelPart root) {
		super(root);
		this.topRightWing = root.getChild("topRightWing");
		this.middleRightWing = root.getChild("middleRightWing");
		this.bottomRightWing = root.getChild("bottomRightWing");
		this.topLeftWing = root.getChild("topLeftWing");
		this.middleLeftWing = root.getChild("middleLeftWing");
		this.bottomLeftWing = root.getChild("bottomLeftWing");
		this.innerRing = root.getChild("innerRing");
	}
	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition topRightWing = partdefinition.addOrReplaceChild("topRightWing", CubeListBuilder.create(), PartPose.offset(-21.5F, 3.0F, 9.5F));

		topRightWing.addOrReplaceChild("bottomRightWing_r1", CubeListBuilder.create().texOffs(-8, 13).mirror().addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(33.5F, -13.0F, -0.5F, 1.5708F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("middleRightWing", CubeListBuilder.create().texOffs(-13, 0).mirror().addBox(-14.5F, 0.0F, -6.5F, 29.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(21.5F, 3.0F, 9.5F, 1.5708F, 0.0F, 0.0F));

		PartDefinition bottomRightWing = partdefinition.addOrReplaceChild("bottomRightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 10.0F, 9.0F, 0.0F, 0.0F, 1.2654F));

		bottomRightWing.addOrReplaceChild("bottomRightWing_r2", CubeListBuilder.create().texOffs(-8, 13).mirror().addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(12.0F, -14.0F, 0.0F, 1.5708F, 0.0F, -0.6981F));

		PartDefinition topLeftWing = partdefinition.addOrReplaceChild("topLeftWing", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 18.0F));

		topLeftWing.addOrReplaceChild("bottomLeftWing_r1", CubeListBuilder.create().texOffs(-8, 13).addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, -34.0F, -9.0F, 1.5708F, 0.0F, 0.6981F));

		partdefinition.addOrReplaceChild("middleLeftWing", CubeListBuilder.create().texOffs(-13, 0).addBox(-14.5F, 0.0F, -6.5F, 29.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.5F, 3.0F, 9.5F, 1.5708F, 0.0F, 0.0F));

		PartDefinition bottomLeftWing = partdefinition.addOrReplaceChild("bottomLeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 10.0F, 9.0F, 0.0F, 0.0F, -1.2654F));

		bottomLeftWing.addOrReplaceChild("bottomLeftWing_r2", CubeListBuilder.create().texOffs(-8, 13).addBox(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, -14.0F, 0.0F, 1.5708F, 0.0F, 0.6981F));

		partdefinition.addOrReplaceChild("innerRing", CubeListBuilder.create().texOffs(36, 19).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(39, 19).addBox(-5.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(36, 21).addBox(4.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(40, 24).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 9.0F, 1.5708F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color) {
		this.topRightWing.render(stack, consumer, light, overlay, color);
		this.topLeftWing.render(stack, consumer, light, overlay, color);
		this.middleRightWing.render(stack, consumer, light, overlay, color);
		this.middleLeftWing.render(stack, consumer, light, overlay, color);
		this.bottomRightWing.render(stack, consumer, light, overlay, color);
		this.bottomLeftWing.render(stack, consumer, light, overlay, color);
		this.innerRing.render(stack, consumer, light, overlay, color);
	}
}
