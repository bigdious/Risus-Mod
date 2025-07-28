package com.bigdious.risus.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CrownOfBonesModel {
	public static MeshDefinition addPieces(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -11.5F, -4.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		head.addOrReplaceChild("crown_3", CubeListBuilder.create().texOffs(9, 17).mirror().addBox(-1.0F, 2.5F, -4.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		head.addOrReplaceChild("crown_2", CubeListBuilder.create().texOffs(9, 17).addBox(0.0F, 2.5F, -4.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 2.0F, 0.0F, 1.5708F, 0.0F));

		head.addOrReplaceChild("left_secondary_horn_1", CubeListBuilder.create().texOffs(18, 17).mirror().addBox(-1.5F, -3.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -6.0F, 0.5F, -0.3491F, 0.0F, -2.0508F));

		head.addOrReplaceChild("left_secondary_horn_2", CubeListBuilder.create().texOffs(32, 20).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.2274F, -3.7091F, 0.8728F, 2.846F, -0.0779F, 0.4479F));

		head.addOrReplaceChild("left_secondary_horn_3", CubeListBuilder.create().texOffs(12, 27).mirror().addBox(-0.0122F, -1.997F, 0.1723F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.9878F, -0.503F, -0.4223F, 2.3243F, -0.3131F, -0.1834F));

		head.addOrReplaceChild("right_secondary_horn_3", CubeListBuilder.create().texOffs(12, 27).addBox(-0.9878F, -1.997F, 0.1723F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.9878F, -0.503F, -0.4223F, 2.3243F, 0.3131F, 0.1834F));

		head.addOrReplaceChild("right_secondary_horn_2", CubeListBuilder.create().texOffs(32, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.2274F, -3.7091F, 0.8728F, 0.2956F, -0.0779F, 2.6937F));

		head.addOrReplaceChild("right_secondary_horn_1", CubeListBuilder.create().texOffs(18, 17).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -6.0F, 0.5F, -0.3491F, 0.0F, 2.0508F));

		head.addOrReplaceChild("left_main_horn_1", CubeListBuilder.create().texOffs(18, 17).mirror().addBox(-1.5F, -1.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -9.0F, -4.5F, 0.583F, 0.1796F, -0.583F));

		head.addOrReplaceChild("left_main_horn_2", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(-1.1011F, -3.7261F, -1.1011F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.8036F, -9.47F, -4.8036F, 0.3657F, 0.1255F, -0.2778F));

		head.addOrReplaceChild("left_main_horn_3", CubeListBuilder.create().texOffs(4, 27).mirror().addBox(-0.4279F, -2.8858F, -0.124F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.1304F, -12.8007F, -6.5155F, 0.1011F, -0.0852F, 0.0246F));

		head.addOrReplaceChild("right_main_horn_3", CubeListBuilder.create().texOffs(4, 27).addBox(-0.5721F, -2.8858F, -0.124F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1304F, -12.8007F, -6.5155F, 0.1011F, 0.0852F, -0.0246F));

		head.addOrReplaceChild("right_main_horn_2", CubeListBuilder.create().texOffs(20, 24).addBox(-0.8989F, -3.7261F, -1.1011F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8036F, -9.47F, -4.8036F, 0.3657F, -0.1255F, 0.2778F));

		head.addOrReplaceChild("right_main_horn_1", CubeListBuilder.create().texOffs(18, 17).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -9.0F, -4.5F, 0.583F, -0.1796F, 0.583F));

		return meshdefinition;
	}
}
