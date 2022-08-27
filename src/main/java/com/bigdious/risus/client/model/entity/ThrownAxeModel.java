package com.bigdious.risus.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class ThrownAxeModel extends Model {
	private final ModelPart root;

	public ThrownAxeModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.root = root;
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition axe = partdefinition.addOrReplaceChild("axe", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-1.5F, -11.5F, -1.5F, 3.0F, 3.0F, 3.0F).mirror(false)
				.texOffs(0, 22).mirror().addBox(-1.0F, -12.5F, -2.5F, 2.0F, 5.0F, 5.0F).mirror(false)
				.texOffs(24, 17).mirror().addBox(-1.01F, -13.5F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offset(0.0F, 16.5F, 3.5F));

		PartDefinition cube_r1 = axe.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, 1.3241F, 2.8206F, 2.0F, 8.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -3.7049F, -2.8713F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r2 = axe.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, -14).addBox(0.0F, -4.5423F, -0.5184F, 0.0F, 14.0F, 14.0F), PartPose.offsetAndRotation(0.0F, -3.7049F, -2.8713F, 2.3562F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.root.render(stack, consumer, light, overlay, red, green, blue, alpha);
	}
}