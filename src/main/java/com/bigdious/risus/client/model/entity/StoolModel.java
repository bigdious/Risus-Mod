package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Holder;
import com.bigdious.risus.entity.Stool;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

public class StoolModel<T extends Stool> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;


	public StoolModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 33).addBox(-6.0F, -21.0F, 3.0F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-8.0F, -24.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(8, 18).addBox(-7.0F, -22.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-5.5F, -16.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("support_4", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		body.addOrReplaceChild("support_3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		body.addOrReplaceChild("support_2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		body.addOrReplaceChild("leg_4", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, -10.5F, -1.5F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -10.5F, -4.5F, 0.0F, -1.5708F, 0.0F));

		body.addOrReplaceChild("leg_3", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, -10.5F, -1.5F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -10.5F, -4.5F, 0.0F, 3.1416F, 0.0F));

		body.addOrReplaceChild("leg_2", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, -10.5F, -1.5F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -10.5F, 4.5F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

}
