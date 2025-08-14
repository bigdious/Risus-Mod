package com.bigdious.risus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SinnerRobeLeggingsModel {
	public static MeshDefinition addPieces(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		left_leg.addOrReplaceChild("robe", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.65F, -1.5F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.95F)).mirror(false), PartPose.offsetAndRotation(-0.1F, 2.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		var right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		right_leg.addOrReplaceChild("robe", CubeListBuilder.create().texOffs(60, 9).addBox(-3.35F, -1.5F, -1.0F, 0.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(50, 0).addBox(-4.35F, -0.5F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-2.35F, -1.5F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.95F)), PartPose.offsetAndRotation(0.1F, 2.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		return meshdefinition;
    }
}
