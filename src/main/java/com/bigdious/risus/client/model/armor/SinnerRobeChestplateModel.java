package com.bigdious.risus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SinnerRobeChestplateModel {
	public static MeshDefinition addPieces(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
			.texOffs(52, 0).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.6F))
			.texOffs(0, 17).addBox(-3.0F, 0.0F, -2.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(5, 16).addBox(-1.0F, -0.5F, 2.25F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(5, 16).addBox(-1.0F, 5.5F, 2.25F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-3.0F, 2.0F, -2.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-3.0F, 4.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(1.0F, 4.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(2.0F, 6.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-4.0F, 6.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-3.0F, 0.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-3.0F, 2.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-3.0F, 4.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-3.0F, 6.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(52, 0).addBox(-3.0F, 0.0F, 2.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.6F))
			.texOffs(52, 0).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.6F))
			.texOffs(16, 0).addBox(-3.5F, 1.0F, -5.65F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(19, 7).mirror().addBox(-3.5F, 2.0F, -5.65F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		var left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.1F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false)
			.texOffs(36, 0).mirror().addBox(-1.1F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		var right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 0).addBox(-2.9F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
			.texOffs(40, 16).addBox(-2.9F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.9F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return meshdefinition;
    }
}
