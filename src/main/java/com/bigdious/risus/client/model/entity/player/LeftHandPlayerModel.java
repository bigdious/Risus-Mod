package com.bigdious.risus.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class LeftHandPlayerModel extends HumanoidModel<LivingEntity> {

	private final ModelPart arm;
	private final ModelPart slimArm;

	public LeftHandPlayerModel(ModelPart root) {
		super(root, RenderType::entityTranslucent);
		this.arm = root.getChild("arm");
		this.slimArm = root.getChild("slim_arm");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.0F), 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(0, 0).addBox( -6.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-5.0F, -2.0F, 0.0F));
		arm.addOrReplaceChild("outer_arm", CubeListBuilder.create().texOffs(16, 0).addBox(-6.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.52F)), PartPose.ZERO);

		var slimArm = partdefinition.addOrReplaceChild("slim_arm", CubeListBuilder.create().texOffs(1, 0).addBox(-5.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-5.0F, -2.0F, 0.0F));
		slimArm.addOrReplaceChild("outer_slim_arm", CubeListBuilder.create().texOffs(17, 0).addBox(-5.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.52F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	public void setupArmSize(boolean slim) {
		this.arm.visible = !slim;
		this.slimArm.visible = slim;
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.arm.copyFrom(this.leftArm);
		this.slimArm.copyFrom(this.leftArm);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color) {
		this.arm.render(stack, consumer, light, overlay, color);
		this.slimArm.render(stack, consumer, light, overlay, color);
	}
}
