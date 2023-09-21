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
import net.minecraft.world.entity.AnimationState;

public class WeaverModel<T extends Weaver> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart top;
	private final ModelPart bottom;
	private final ModelPart memoryShell;

	private final ModelPart leftLeg1;
	private final ModelPart leftLeg2;
	private final ModelPart leftLeg3;
	private final ModelPart rightLeg1;
	private final ModelPart rightLeg2;
	private final ModelPart rightLeg3;

	public WeaverModel(ModelPart root) {
		this.root = root;
		ModelPart rightLegs = root.getChild("rightLegs");
		ModelPart leftLegs = root.getChild("leftLegs");
		this.leftLeg1 = leftLegs.getChild("leftLeg1");
		this.leftLeg2 = leftLegs.getChild("leftLeg2");
		this.leftLeg3 = leftLegs.getChild("leftLeg3");
		this.rightLeg1 = rightLegs.getChild("rightLeg1");
		this.rightLeg2 = rightLegs.getChild("rightLeg2");
		this.rightLeg3 = rightLegs.getChild("rightLeg3");
		ModelPart head = root.getChild("head");
		this.top = head.getChild("top");
		this.bottom = head.getChild("bottom");
		ModelPart memoryCore = root.getChild("memoryCore");
		this.memoryShell = memoryCore.getChild("memoryShell");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rightLegs = partdefinition.addOrReplaceChild("rightLegs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition rightLeg1 = rightLegs.addOrReplaceChild("rightLeg1", CubeListBuilder.create(), PartPose.offset(1.0F, -6.0F, 2.0F));

		rightLeg1.addOrReplaceChild("segment3_r1", CubeListBuilder.create().texOffs(4, 4).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 11.0F), PartPose.offsetAndRotation(3.0F, -3.0F, 4.0F, -1.1781F, 1.1345F, 0.0F));

		rightLeg1.addOrReplaceChild("segment2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.7418F, 0.6545F, 0.0F));

		PartDefinition rightLeg2 = rightLegs.addOrReplaceChild("rightLeg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -5.0F, 2.0F, 0.0F, -0.4363F, 0.0F));

		rightLeg2.addOrReplaceChild("segment3_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -0.0761F, 1.0F, 1.0F, 10.0F), PartPose.offsetAndRotation(3.4226F, -4.0F, 3.9063F, -1.1781F, 1.1345F, 0.0F));

		rightLeg2.addOrReplaceChild("segment2_r2", CubeListBuilder.create().texOffs(6, 2).addBox(-1.0F, -0.2627F, -1.6756F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.4226F, -1.0F, 0.9063F, 0.7418F, 0.6545F, 0.0F));

		PartDefinition rightLeg3 = rightLegs.addOrReplaceChild("rightLeg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -4.0F, 2.0F, 0.0F, -0.9163F, 0.0F));

		rightLeg3.addOrReplaceChild("segment3_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -0.0761F, 1.0F, 1.0F, 9.0F), PartPose.offsetAndRotation(3.1846F, -4.0F, 4.4021F, -1.1781F, 0.2618F, -0.0436F));

		rightLeg3.addOrReplaceChild("segment2_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.2627F, -1.6756F, 1.0F, 1.0F, 6.0F), PartPose.offsetAndRotation(1.1846F, -1.0F, 1.4021F, 0.7418F, 0.6545F, 0.0F));

		PartDefinition leftLegs = partdefinition.addOrReplaceChild("leftLegs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition leftLeg1 = leftLegs.addOrReplaceChild("leftLeg1", CubeListBuilder.create(), PartPose.offset(1.0F, -6.0F, -2.0F));

		leftLeg1.addOrReplaceChild("segment2_r4", CubeListBuilder.create().texOffs(4, 4).addBox(-1.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F), PartPose.offsetAndRotation(3.0F, -3.0F, -4.0F, 1.1781F, -1.1345F, 0.0F));

		leftLeg1.addOrReplaceChild("segment1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, -0.7418F, -0.6545F, 0.0F));

		PartDefinition leftLeg2 = leftLegs.addOrReplaceChild("leftLeg2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -5.0F, -2.0F, 0.0F, 0.4363F, 0.0F));

		leftLeg2.addOrReplaceChild("segment2_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -9.9239F, 1.0F, 1.0F, 10.0F), PartPose.offsetAndRotation(3.4226F, -4.0F, -3.9063F, 1.1781F, -1.1345F, 0.0F));

		leftLeg2.addOrReplaceChild("segment1_r2", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -0.2627F, -5.3244F, 1.0F, 1.0F, 7.0F), PartPose.offsetAndRotation(1.4226F, -1.0F, -0.9063F, -0.7418F, -0.6545F, 0.0F));

		PartDefinition leftLeg3 = leftLegs.addOrReplaceChild("leftLeg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -4.0F, -2.0F, 0.0F, 0.9163F, 0.0F));

		leftLeg3.addOrReplaceChild("segment2_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.6173F, -8.9239F, 1.0F, 1.0F, 9.0F), PartPose.offsetAndRotation(3.1846F, -4.0F, -4.4021F, 1.1781F, -0.2618F, -0.0436F));

		leftLeg3.addOrReplaceChild("segment1_r3", CubeListBuilder.create().texOffs(16, 3).addBox(-1.0F, -0.2627F, -4.3244F, 1.0F, 1.0F, 6.0F), PartPose.offsetAndRotation(1.1846F, -1.0F, -1.4021F, -0.7418F, -0.6545F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(6, 12).addBox(-2.0F, -1.0F, -2.0F, 2.0F, 2.0F, 3.0F), PartPose.offsetAndRotation(-0.5F, 19.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

		head.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).addBox(-2.7648F, -0.9389F, 1.5F, 3.0F, 1.0F, 1.0F)
		.texOffs(0, 0).addBox(-2.7648F, -0.9389F, -2.5F, 3.0F, 1.0F, 1.0F)
		.texOffs(0, 0).addBox(-3.7648F, -0.9389F, -1.5F, 1.0F, 1.0F, 3.0F)
		.texOffs(31, 6).addBox(-3.7648F, -0.9389F, 1.5F, 1.0F, 2.0F, 1.0F)
		.texOffs(31, 6).addBox(-3.7648F, -0.9389F, -2.5F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(-2.0F, -1.0F, -0.5F, 0.0F, 0.0F, 0.4363F));

		head.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-2.7696F, -0.8415F, 1.5F, 3.0F, 1.0F, 1.0F)
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
		this.memoryShell.visible = false;
		this.root().render(stack, builder, light, overlay, red, green, blue, alpha);
	}

	public void renderCore(PoseStack stack, VertexConsumer builder, int light, int overlay, float red, float green, float blue, float alpha) {
		this.root.getChild("memoryCore").render(stack, builder, light, overlay, red, green, blue, alpha);
	}
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.animate(entity.walk, WeaverAnimations.WEAVER_WALKING, ageInTicks);
		this.animate(entity.core, WeaverAnimations.WEAVER_CORE_ROTATION, ageInTicks);

		if (entity.getActiveAttackTarget(entity) != null) {
			this.animate(entity.idle, WeaverAnimations.WEAVER_MOUTH_IDLE, ageInTicks);
		} else {
			this.animate(entity.leapAnim, WeaverAnimations.WEAVER_ATTACK, ageInTicks);
		}
	}
}