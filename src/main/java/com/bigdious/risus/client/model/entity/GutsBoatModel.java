package com.bigdious.risus.client.model.entity;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GutsBoatModel extends BoatModel {
	private static final String ORGANS = "organs";
	private static final String RIBCAGE = "ribcage";
	public GutsBoatModel(ModelPart root) {
		super(root);
	}

	@Override
	protected ImmutableList.Builder<ModelPart> createPartsBuilder(ModelPart root) {
		ImmutableList.Builder<ModelPart> builder = super.createPartsBuilder(root);
		builder.add(root.getChild("organs"));
		builder.add(root.getChild("ribcage"));
		return builder;
	}
	public static LayerDefinition createBodyModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		BoatModel.createChildren(partdefinition);
		partdefinition.addOrReplaceChild("ribcage", CubeListBuilder.create().texOffs(16, 59).addBox(-5.0F, 3.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, 3.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, 3.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(-5.0F, 3.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(-5.0F, -9.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(-5.0F, -9.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, -9.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, -9.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, -5.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, -5.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(-5.0F, -5.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, -1.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(-5.0F, -1.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(-5.0F, -5.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(3.0F, -1.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 59).addBox(-5.0F, -1.0F, -10.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(1.0F, -7.0F, -9.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(1.0F, -7.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(8.0F, 4.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(-6.0F, 4.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(-6.0F, -1.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(-6.0F, -5.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(8.0F, -5.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(8.0F, -1.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(-6.0F, -10.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 59).addBox(8.0F, -10.0F, -8.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -3.0F, 2.0F));

		partdefinition.addOrReplaceChild("organs", CubeListBuilder.create().texOffs(26, 97).addBox(-2.0F, -17.0F, -8.0F, 9.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 97).addBox(5.0F, -18.0F, -10.0F, 5.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 83).addBox(-1.0F, -20.0F, -9.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(0, 73).addBox(0.0F, -22.0F, -8.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 9.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

}
