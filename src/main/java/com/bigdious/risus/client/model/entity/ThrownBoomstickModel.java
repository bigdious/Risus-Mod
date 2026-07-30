package com.bigdious.risus.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class ThrownBoomstickModel extends Model {

	private final ModelPart root;

	public ThrownBoomstickModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.root = root;
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-9.0F, -30.0F, 7.0F, 2.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(8, 5).addBox(-10.0F, -22.0F, 6.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(18, -3).addBox(-8.0F, -32.0F, 7.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 24, 48);
	}

	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color) {
		this.root.render(stack, consumer, light, overlay, color);
	}
}
