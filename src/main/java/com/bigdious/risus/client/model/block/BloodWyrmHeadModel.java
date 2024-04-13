package com.bigdious.risus.client.model.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class BloodWyrmHeadModel extends SkullModel {
	private final ModelPart head;
	private final ModelPart lowerJaw;
	private final ModelPart upperJaw;

	public BloodWyrmHeadModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.upperJaw = this.head.getChild("upper_jaw");
		this.lowerJaw = this.head.getChild("lower_jaw");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -14.0F, -8.0F, 16.0F, 14.0F, 16.0F)
				.texOffs(0, 0).addBox(-5.0F, -17.0F, -2.0F, 2.0F, 3.0F, 6.0F)
				.texOffs(0, 0).addBox(3.0F, -17.0F, -2.0F, 2.0F, 3.0F, 6.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

		head.addOrReplaceChild("flamethrower", CubeListBuilder.create().texOffs(83, 52).addBox(-11.0F, -6.0F, 5.0F, 3.0F, 3.0F, 3.0F)
				.texOffs(10, 38).addBox(-10.5F, -5.5F, -10.0F, 2.0F, 2.0F, 15.0F)
				.texOffs(21, 47).addBox(-8.5F, -5.5F, -10.0F, 8.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(84, 47).addBox(-4.0F, -4.0F, -14.0F, 2.0F, 2.0F, 3.0F)
				.texOffs(84, 50).addBox(2.0F, -4.0F, -14.0F, 2.0F, 2.0F, 3.0F)
				.texOffs(56, 21).addBox(-6.0F, -2.0F, -16.0F, 12.0F, 4.0F, 16.0F)
				.texOffs(0, 0).addBox(4.0F, 0.0F, -8.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(-5.0F, 0.0F, -8.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(-5.0F, 1.0F, -11.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(4.0F, 1.0F, -11.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(4.0F, 1.0F, -14.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(-5.0F, 1.0F, -14.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(-4.0F, 1.0F, -15.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(-5.0F, 2.0F, -15.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(4.0F, 2.0F, -15.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(3.0F, 1.0F, -15.0F, 1.0F, 3.0F, 1.0F), PartPose.offset(0.0F, -6.0F, -7.0F));

		head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -11.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(-5.0F, -1.0F, -8.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(-5.0F, 0.0F, -4.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(4.0F, 0.0F, -4.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(4.0F, -1.0F, -8.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(4.0F, -1.0F, -11.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(2.0F, -1.0F, -14.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(-3.0F, -1.0F, -14.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(-2.0F, -1.0F, -15.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(1.0F, -1.0F, -15.0F, 1.0F, 2.0F, 1.0F)
				.texOffs(0, 0).addBox(2.0F, -2.0F, -15.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(0, 0).addBox(-3.0F, -2.0F, -15.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(56, 42).addBox(-6.0F, 1.0F, -16.0F, 12.0F, 4.0F, 16.0F), PartPose.offset(0.0F, -5.0F, -7.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(float ticks, float yRot, float xRot) {
		this.upperJaw.xRot = -(float) (Math.sin(ticks * (float) Math.PI * 0.2F) + 1.0D) * 0.2F;
		this.lowerJaw.xRot = (float) (Math.sin(ticks * (float) Math.PI * 0.2F) + 1.0D) * 0.2F;
		this.head.yRot = yRot * ((float) Math.PI / 180F);
		this.head.xRot = xRot * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
		stack.pushPose();
		stack.translate(0.0D, -1.15F, 0.0D);
		stack.scale(0.75F, 0.75F, 0.75F);
		this.head.render(stack, consumer, light, overlay, red, green, blue, alpha);
		stack.popPose();
	}
}
