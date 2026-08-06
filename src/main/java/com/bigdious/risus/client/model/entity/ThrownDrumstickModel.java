package com.bigdious.risus.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class ThrownDrumstickModel extends Model {

	private final ModelPart root;

	public ThrownDrumstickModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.root = root;
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 1).addBox(-9.0F, -27.0F, 7.0F, 2.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(8, 28).addBox(-7.0F, -1.0F, 7.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(9, 29).addBox(-9.0F, -1.0F, 6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(8, 28).mirror().addBox(-10.0F, -1.0F, 7.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(14, 29).mirror().addBox(-9.0F, -1.0F, 9.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(20, 14).addBox(-10.0F, -10.0F, 6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(16, 12).addBox(-11.0F, -13.0F, 5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(12, 10).addBox(-12.0F, -18.0F, 4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(8, 8).addBox(-13.0F, -26.0F, 3.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color) {
		this.root.render(stack, consumer, light, overlay, color);
	}
}
