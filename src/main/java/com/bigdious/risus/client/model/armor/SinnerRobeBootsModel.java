package com.bigdious.risus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SinnerRobeBootsModel {
	public static MeshDefinition addPieces(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 11.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.6F)).mirror(false)
			.texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		var right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 11.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.6F))
			.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return meshdefinition;
    }
}
