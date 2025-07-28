package com.bigdious.risus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SinnerRobeHelmetModel {
	public static MeshDefinition addPieces(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
			.texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		head.addOrReplaceChild("hoodie_part_3", CubeListBuilder.create().texOffs(20, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -1.6043F, 9.3159F, -1.1345F, 0.0F, 0.0F));

		head.addOrReplaceChild("hoodie_part_2", CubeListBuilder.create().texOffs(16, 20).addBox(-2.0F, -2.0F, -1.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -3.6118F, 6.4489F, -0.6109F, 0.0F, 0.0F));

		head.addOrReplaceChild("hoodie_part_1", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.2618F, 0.0F, 0.0F));

		return meshdefinition;
	}
}
