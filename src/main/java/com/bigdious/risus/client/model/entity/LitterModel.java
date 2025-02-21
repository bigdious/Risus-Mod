package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Litter;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class LitterModel<T extends Litter> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart leftLeg1;
	private final ModelPart rightLeg1;
	private final ModelPart leftLeg2;
	private final ModelPart rightLeg2;
	private final ModelPart leftLeg3;
	private final ModelPart rightLeg3;

	public LitterModel(ModelPart root) {
		this.root = root;
		this.leftLeg1 = root.getChild("left_leg_1");
		this.rightLeg1 = root.getChild("right_leg_1");
		this.leftLeg2 = root.getChild("left_leg_2");
		this.rightLeg2 = root.getChild("right_leg_2");
		this.leftLeg3 = root.getChild("left_leg_3");
		this.rightLeg3 = root.getChild("right_leg_3");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -4.75F, -2.5F, 6.0F, 2.0F, 5.0F),
			PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftLeg1 = partdefinition.addOrReplaceChild("left_leg_1", CubeListBuilder.create()
			.texOffs(4, 1).addBox(-0.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F),
			PartPose.offsetAndRotation(3.0F, 20.5F, -2.0F, 0.0F, 0.5236F, 0.0F));

		leftLeg1.addOrReplaceChild("leg", CubeListBuilder.create()
			.texOffs(4, 1).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F)
			.texOffs(5, 0).addBox(2.0F, -1.25F, -1.0F, 1.0F, 3.0F, 2.0F)
			.texOffs(0, 0).addBox(2.0F, 1.75F, -0.5F, 1.0F, 2.0F, 1.0F),
			PartPose.offsetAndRotation(1.5F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition rightLeg1 = partdefinition.addOrReplaceChild("right_leg_1", CubeListBuilder.create()
			.texOffs(4, 1).addBox(-1.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F),
			PartPose.offsetAndRotation(-3.0F, 20.5F, -2.0F, 0.0F, -0.5236F, 0.0F));

		rightLeg1.addOrReplaceChild("leg", CubeListBuilder.create()
				.texOffs(4, 1).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F)
			.texOffs(5, 0).addBox(-3.0F, -1.25F, -1.0F, 1.0F, 3.0F, 2.0F)
			.texOffs(0, 0).addBox(-3.0F, 1.75F, -0.5F, 1.0F, 2.0F, 1.0F),
			PartPose.offsetAndRotation(-1.5F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition leftLeg2 = partdefinition.addOrReplaceChild("left_leg_2", CubeListBuilder.create()
			.texOffs(4, 1).addBox(0.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F),
			PartPose.offset(3.0F, 20.5F, 0.0F));

		leftLeg2.addOrReplaceChild("leg", CubeListBuilder.create()
			.texOffs(4, 1).addBox(0.25F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F)
			.texOffs(0, 0).addBox(2.25F, 1.75F, -0.5F, 1.0F, 2.0F, 1.0F)
			.texOffs(5, 0).addBox(2.25F, -1.25F, -1.0F, 1.0F, 3.0F, 2.0F),
			PartPose.offsetAndRotation(1.75F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition rightLeg2 = partdefinition.addOrReplaceChild("right_leg_2", CubeListBuilder.create().mirror()
			.texOffs(4, 1).addBox(-2.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F),
			PartPose.offset(-3.0F, 20.5F, 0.0F));

		rightLeg2.addOrReplaceChild("leg", CubeListBuilder.create().mirror()
				.texOffs(4, 1).addBox(-2.25F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F)
			.texOffs(0, 0).addBox(-3.25F, 1.75F, -0.5F, 1.0F, 2.0F, 1.0F)
			.texOffs(5, 0).addBox(-3.25F, -1.25F, -1.0F, 1.0F, 3.0F, 2.0F),
			PartPose.offsetAndRotation(-1.75F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition leftLeg3 = partdefinition.addOrReplaceChild("left_leg_3", CubeListBuilder.create()
			.texOffs(4, 1).addBox(-0.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F),
			PartPose.offsetAndRotation(3.0F, 20.5F, 2.0F, 0.0F, -0.5236F, 0.0F));

		leftLeg3.addOrReplaceChild("leg", CubeListBuilder.create().texOffs(4, 1).addBox(0.0F, -1.0F, 0.5F, 2.0F, 1.0F, 2.0F)
			.texOffs(5, 0).addBox(2.0F, -1.25F, 0.5F, 1.0F, 3.0F, 2.0F)
			.texOffs(0, 0).addBox(2.0F, 1.75F, 1.0F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(1.5F, 0.5F, -1.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition rightLeg3 = partdefinition.addOrReplaceChild("right_leg_3", CubeListBuilder.create().mirror()
			.texOffs(4, 1).addBox(-1.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F),
			PartPose.offsetAndRotation(-3.0F, 20.5F, 2.0F, 0.0F, 0.5236F, 0.0F));

		rightLeg3.addOrReplaceChild("leg", CubeListBuilder.create().mirror()
				.texOffs(4, 1).addBox(-2.0F, -1.0F, 0.5F, 2.0F, 1.0F, 2.0F)
			.texOffs(5, 0).addBox(-3.0F, -1.25F, 0.5F, 1.0F, 3.0F, 2.0F)
			.texOffs(0, 0).addBox(-3.0F, 1.75F, 1.0F, 1.0F, 2.0F, 1.0F),
			PartPose.offsetAndRotation(-1.5F, 0.5F, -1.5F, 0.0F, 0.0F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float f8 = Math.abs(Mth.sin(limbSwing * 0.25F * 3F + Mth.PI) * 0.4F) * limbSwingAmount;
		float f10 = Math.abs(Mth.sin(limbSwing * 0.25F * 3F + (Mth.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		this.rightLeg3.yRot -= f10;
		this.rightLeg2.yRot += f10;
		this.rightLeg1.yRot -= f10;
		this.leftLeg3.yRot -= f10;
		this.leftLeg2.yRot += f10;
		this.leftLeg1.yRot -= f10;

		this.rightLeg3.zRot += f8;
		this.rightLeg2.zRot -= f8;
		this.rightLeg1.zRot += f8;
		this.leftLeg3.zRot -= f8;
		this.leftLeg2.zRot += f8;
		this.leftLeg1.zRot -= f8;
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
