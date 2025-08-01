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

		var head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
			.texOffs(40, 27).addBox(-2.0F, -3.88F, -5.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
			.texOffs(26, 17).addBox(-3.2F, -1.75F, -5.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(16, 16).addBox(-2.0F, -2.88F, -5.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
			.texOffs(26, 17).addBox(2.2F, -1.75F, -5.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(52, 26).addBox(-1.5F, -2.88F, -7.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		head.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -4.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		head.addOrReplaceChild("hoodie_part_3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -1.6042F, 9.3159F, -1.1345F, 0.0F, 0.0F));

		head.addOrReplaceChild("hoodie_part_2", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -2.0F, -1.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -3.6118F, 6.4489F, -0.6109F, 0.0F, 0.0F));

		head.addOrReplaceChild("hoodie_part_1", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.2618F, 0.0F, 0.0F));

		return meshdefinition;
	}
}
