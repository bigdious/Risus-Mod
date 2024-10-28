package com.bigdious.risus.client.model.entity;

// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.bigdious.risus.entity.BabySpider;
import com.bigdious.risus.entity.Licker;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BabySpiderModel<T extends BabySpider> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart leg8;
	private final ModelPart leg7;
	private final ModelPart leg6;
	private final ModelPart leg1;
	private final ModelPart leg4;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg5;

	public BabySpiderModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.leg8 = body.getChild("leg8");
		this.leg7 = body.getChild("leg7");
		this.leg6 = body.getChild("leg6");
		this.leg1 = body.getChild("leg1");
		this.leg4 = body.getChild("leg4");
		this.leg2 = body.getChild("leg2");
		this.leg3 = body.getChild("leg3");
		this.leg5 = body.getChild("leg5");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 2).addBox(-1.0F, -1.75F, -0.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 5).addBox(-0.5F, -1.5F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		body.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -1.5F, -1.25F, 0.5042F, 0.272F, 1.1177F));

		body.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -1.5F, 0.5F, -0.0756F, -0.0436F, 1.0488F));

		body.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -1.5F, 1.25F, -0.5042F, -0.272F, 1.1177F));

		body.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.5F, 1.25F, -0.5042F, 0.272F, -1.1177F));

		body.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.5F, 0.5F, -0.0756F, 0.0436F, -1.0488F));

		body.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -1.5F, -0.5F, 0.0756F, 0.0436F, 1.0488F));

		body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.5F, -0.5F, 0.0756F, -0.0436F, -1.0488F));

		body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.5F, -1.25F, 0.5042F, -0.272F, -1.1177F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float f3 = -(Mth.cos(limbSwing * 0.6662F * 10F + 0.0F) * 0.4F) * limbSwingAmount;
		float f4 = -(Mth.cos(limbSwing * 0.6662F * 10F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f6 = -(Mth.cos(limbSwing * 0.6662F * 10F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F * 3F + 0.0F) * 0.4F) * limbSwingAmount;
		float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F * 3F + (float) Math.PI) * 0.4F) * limbSwingAmount;
		float f10 = Math.abs(Mth.sin(limbSwing * 0.6662F * 3F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		this.leg8.yRot += f7;
		this.leg7.yRot += f7;
		this.leg5.yRot += f7;
		this.leg4.yRot -= f7;
		this.leg3.yRot += f7;
		this.leg2.yRot -= f7;
		this.leg1.yRot += f7;
		this.leg6.yRot -= f7;
		this.leg8.zRot += f7;
		this.leg7.zRot -= f8;
		this.leg5.zRot += f8;
		this.leg4.zRot -= f7;
		this.leg3.zRot += f7;
		this.leg2.zRot -= f10;
		this.leg1.zRot += f10;
		this.leg6.zRot -= f7;
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
