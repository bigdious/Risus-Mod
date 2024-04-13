package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Weaver;
import com.bigdious.risus.entity.animations.WeaverAnimations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class WeaverModel<T extends Weaver> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart upperJaw;
	private final ModelPart lowerJaw;
	private final ModelPart memoryCore;
	private final ModelPart left_leg1;
	private final ModelPart left_leg2;
	private final ModelPart left_leg3;
	private final ModelPart right_leg1;
	private final ModelPart right_leg2;
	private final ModelPart right_leg3;


	public WeaverModel(ModelPart root) {
		this.root = root;
		ModelPart rightLegs = root.getChild("rightLegs");
		ModelPart leftLegs = root.getChild("leftLegs");
		this.left_leg1 = leftLegs.getChild("left_leg1");
		this.left_leg2 = leftLegs.getChild("left_leg2");
		this.left_leg3 = leftLegs.getChild("left_leg3");
		this.right_leg1 = rightLegs.getChild("right_leg1");
		this.right_leg2 = rightLegs.getChild("right_leg2");
		this.right_leg3 = rightLegs.getChild("right_leg3");
		ModelPart head = root.getChild("head");
		this.upperJaw = head.getChild("upperJaw");
		this.lowerJaw = head.getChild("lowerJaw");
		ModelPart coreHolder = root.getChild("memoryCore");
		ModelPart body = root.getChild("body");
		this.memoryCore = coreHolder.getChild("memoryShell");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rightLegs = partdefinition.addOrReplaceChild("rightLegs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition right_leg1 = rightLegs.addOrReplaceChild("right_leg1", CubeListBuilder.create(), PartPose.offset(1.0F, -6.0F, 2.0F));

		right_leg1.addOrReplaceChild("segment3_r1", CubeListBuilder.create().texOffs(4, 4).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 11.0F), PartPose.offsetAndRotation(3.0F, -3.0F, 4.0F, -1.1781F, 1.1345F, 0.0F));

		right_leg1.addOrReplaceChild("segment2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.7418F, 0.6545F, 0.0F));

		PartDefinition right_leg2 = rightLegs.addOrReplaceChild("right_leg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -5.0F, 2.0F, 0.0F, -0.4363F, 0.0F));

		right_leg2.addOrReplaceChild("segment3_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -0.0761F, 1.0F, 1.0F, 10.0F), PartPose.offsetAndRotation(3.4226F, -4.0F, 3.9063F, -1.1781F, 1.1345F, 0.0F));

		right_leg2.addOrReplaceChild("segment2_r2", CubeListBuilder.create().texOffs(6, 2).addBox(-1.0F, -0.2627F, -1.6756F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.4226F, -1.0F, 0.9063F, 0.7418F, 0.6545F, 0.0F));

		PartDefinition right_leg3 = rightLegs.addOrReplaceChild("right_leg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -4.0F, 2.0F, 0.0F, -0.9163F, 0.0F));

		right_leg3.addOrReplaceChild("segment3_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -0.0761F, 1.0F, 1.0F, 9.0F), PartPose.offsetAndRotation(3.1846F, -4.0F, 4.4021F, -1.1781F, 0.2618F, -0.0436F));

		right_leg3.addOrReplaceChild("segment2_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.2627F, -1.6756F, 1.0F, 1.0F, 6.0F), PartPose.offsetAndRotation(1.1846F, -1.0F, 1.4021F, 0.7418F, 0.6545F, 0.0F));

		PartDefinition leftLegs = partdefinition.addOrReplaceChild("leftLegs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition left_leg1 = leftLegs.addOrReplaceChild("left_leg1", CubeListBuilder.create(), PartPose.offset(1.0F, -6.0F, -2.0F));

		left_leg1.addOrReplaceChild("segment2_r4", CubeListBuilder.create().texOffs(4, 4).addBox(-1.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F), PartPose.offsetAndRotation(3.0F, -3.0F, -4.0F, 1.1781F, -1.1345F, 0.0F));

		left_leg1.addOrReplaceChild("segment1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, -0.7418F, -0.6545F, 0.0F));

		PartDefinition left_leg2 = leftLegs.addOrReplaceChild("left_leg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -5.0F, -2.0F, 0.0F, 0.4363F, 0.0F));

		left_leg2.addOrReplaceChild("segment2_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -9.9239F, 1.0F, 1.0F, 10.0F), PartPose.offsetAndRotation(3.4226F, -4.0F, -3.9063F, 1.1781F, -1.1345F, 0.0F));

		left_leg2.addOrReplaceChild("segment1_r2", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -0.2627F, -5.3244F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.4226F, -1.0F, -0.9063F, -0.7418F, -0.6545F, 0.0F));

		PartDefinition left_leg3 = leftLegs.addOrReplaceChild("left_leg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -4.0F, -2.0F, 0.0F, 0.9163F, 0.0F));

		left_leg3.addOrReplaceChild("segment2_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -8.9239F, 1.0F, 1.0F, 9.0F), PartPose.offsetAndRotation(3.1846F, -4.0F, -4.4021F, 1.1781F, -0.2618F, -0.0436F));

		left_leg3.addOrReplaceChild("segment1_r3", CubeListBuilder.create().texOffs(16, 3).addBox(-1.0F, -0.2627F, -4.3244F, 1.0F, 1.0F, 6.0F), PartPose.offsetAndRotation(1.1846F, -1.0F, -1.4021F, -0.7418F, -0.6545F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(6, 12).addBox(-2.0F, -1.0F, -2.0F, 2.0F, 2.0F, 3.0F), PartPose.offsetAndRotation(-0.5F, 19.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

		head.addOrReplaceChild("upperJaw", CubeListBuilder.create().texOffs(0, 0).addBox(-2.7648F, -0.9389F, 1.5F, 3.0F, 1.0F, 1.0F)
				.texOffs(0, 0).addBox(-2.7648F, -0.9389F, -2.5F, 3.0F, 1.0F, 1.0F)
				.texOffs(0, 0).addBox(-3.7648F, -0.9389F, -1.5F, 1.0F, 1.0F, 3.0F)
				.texOffs(31, 6).addBox(-3.7648F, -0.9389F, 1.5F, 1.0F, 2.0F, 1.0F)
				.texOffs(31, 6).addBox(-3.7648F, -0.9389F, -2.5F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(-2.0F, -1.0F, -0.5F, 0.0F, 0.0F, 0.4363F));

		head.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(0, 0).addBox(-2.7696F, -0.8415F, 1.5F, 3.0F, 1.0F, 1.0F)
				.texOffs(0, 0).addBox(-2.7696F, -0.8415F, -2.5F, 3.0F, 1.0F, 1.0F)
				.texOffs(0, 0).addBox(-3.7696F, -0.8415F, -1.5F, 1.0F, 1.0F, 3.0F)
				.texOffs(31, 6).addBox(-3.7696F, -0.8415F, 1.5F, 1.0F, 2.0F, 1.0F)
				.texOffs(31, 6).addBox(-3.7696F, -0.8415F, -2.5F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(-2.0F, 1.0F, -0.5F, 3.1416F, 0.0F, -0.3927F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(4, 12).addBox(0.0F, -8.0F, -1.5F, 4.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		body.addOrReplaceChild("middleSegment_r1", CubeListBuilder.create().texOffs(0, 11).addBox(-3.0F, -2.0F, -2.5F, 6.0F, 4.0F, 5.0F), PartPose.offsetAndRotation(-2.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition memoryCore = partdefinition.addOrReplaceChild("memoryCore", CubeListBuilder.create().texOffs(10, 14).addBox(-2.5F, -2.5F, 0.6429F, 5.0F, 5.0F, 1.0F)
				.texOffs(0, 0).addBox(2.0F, 1.5F, 1.6429F, 1.0F, 1.0F, 5.0F)
				.texOffs(0, 0).addBox(2.0F, -2.5F, 1.6429F, 1.0F, 1.0F, 5.0F)
				.texOffs(0, 0).addBox(-3.0F, -2.5F, 1.6429F, 1.0F, 1.0F, 5.0F)
				.texOffs(0, 0).addBox(-3.0F, 1.5F, 1.6429F, 1.0F, 1.0F, 5.0F)
				.texOffs(30, 6).addBox(-0.5F, -0.5F, 4.6429F, 1.0F, 1.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 17.5F, 3.3571F, 0.2182F, 0.0F, 0.0F));

		memoryCore.addOrReplaceChild("memoryShell", CubeListBuilder.create().texOffs(30, 0).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 5.1429F, -0.7854F, 0.7854F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer builder, int light, int overlay, float red, float green, float blue, float alpha) {
		this.memoryCore.visible = false;
		this.root().render(stack, builder, light, overlay, red, green, blue, alpha);
	}

	public void renderCore(PoseStack stack, VertexConsumer builder, int light, int overlay, float red, float green, float blue, float alpha) {
		this.root.getChild("memoryCore").render(stack, builder, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.right_leg1.yRot = 0.0F;
		this.right_leg2.yRot = -25.0F * Mth.DEG_TO_RAD;
		this.right_leg3.yRot = -52.5F * Mth.DEG_TO_RAD;
		this.right_leg1.zRot = 0.0F;
		this.right_leg2.zRot = 0.0F;
		this.right_leg3.zRot = 0.0F;
		this.left_leg1.yRot = 0.0F;
		this.left_leg2.yRot = 25.0F * Mth.DEG_TO_RAD;
		this.left_leg3.yRot = 52.5F * Mth.DEG_TO_RAD;
		this.left_leg1.zRot = 0.0F;
		this.left_leg2.zRot = 0.0F;
		this.left_leg3.zRot = 0.0F;
		float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float f4 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f6 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
		float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f10 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		this.right_leg3.yRot += f3;
		this.left_leg3.yRot += -f3;
		this.right_leg2.yRot += f4;
		this.left_leg2.yRot -= f4;
		this.right_leg1.yRot += f6;
		this.left_leg1.yRot -= f6;
		this.right_leg3.zRot += f7;
		this.left_leg3.zRot -= f7;
		this.right_leg2.zRot += f8;
		this.left_leg2.zRot -= f8;
		this.right_leg1.zRot += f10;
		this.left_leg1.zRot -= f10;
		this.memoryCore.xRot = ageInTicks * 2 % 360 * Mth.DEG_TO_RAD;
		this.memoryCore.yRot = ageInTicks * 2 % 360 * Mth.DEG_TO_RAD;
		this.memoryCore.zRot = ageInTicks * 2 % 360 * Mth.DEG_TO_RAD;

		if (entity.getActiveAttackTarget(entity) == null) {
			this.upperJaw.zRot = Mth.sin(ageInTicks * 0.067F) * 0.1F + (10.0F * Mth.DEG_TO_RAD);
			this.lowerJaw.zRot = -Mth.sin(ageInTicks * 0.067F) * 0.1F + (-10.0F * Mth.DEG_TO_RAD);
		}

		this.animate(entity.leapAnim, WeaverAnimations.WEAVER_ATTACK, ageInTicks);
	}

	//TODO figure out why weaver won't bite
	public void prepareMobModel(T p_102957_, float p_102958_, float p_102959_, float p_102960_) {
		int i = p_102957_.getAttackAnimationTick();
		if (i > 0) {
			this.upperJaw.zRot = -2.0F;
			this.lowerJaw.zRot = -2.0F;
		} else {
			this.upperJaw.zRot = -0.2F;
			this.lowerJaw.zRot = -0.2F;
		}
	}
}
