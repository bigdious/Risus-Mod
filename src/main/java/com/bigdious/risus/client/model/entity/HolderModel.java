package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Holder;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

public class HolderModel<T extends Holder> extends HierarchicalModel<T> implements ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;

	public HolderModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-4.0F, -8.0F, -1.0F, 7.0F, 9.0F, 6.0F)
		.texOffs(6, 15).addBox(-6.0F, -5.0F, 2.0F, 4.0F, 6.0F, 4.0F)
		.texOffs(2, 12).addBox(-5.0F, -2.0F, -4.0F, 5.0F, 3.0F, 6.0F)
		.texOffs(5, 14).addBox(0.0F, -11.0F, 0.0F, 4.0F, 5.0F, 4.0F)
		.texOffs(2, 14).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 2.0F, 3.0F)
		.texOffs(1, 18).addBox(-2.0F, 0.0F, -6.0F, 7.0F, 1.0F, 6.0F)
		.texOffs(5, 15).addBox(1.0F, -3.0F, -2.0F, 5.0F, 4.0F, 6.0F)
		.texOffs(4, 21).addBox(-7.0F, 0.0F, -1.0F, 2.0F, 1.0F, 5.0F)
		.texOffs(13, 23).addBox(4.0F, 0.0F, 4.0F, 1.0F, 1.0F, 2.0F), PartPose.offset(0.0F, 23.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(3, 15).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 8.0F), PartPose.offset(3.0F, 19.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(3, 15).mirror().addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 8.0F).mirror(false), PartPose.offset(-4.0F, 19.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	private ModelPart getArm(HumanoidArm arm) {
		return arm == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack stack) {
		this.getArm(arm).translateAndRotate(stack);
	}
}