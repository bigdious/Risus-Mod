package com.bigdious.risus.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class AngelWingsModel extends HumanoidModel<LivingEntity> {
	private final ModelPart RightWing;
	private final ModelPart RightThreeQuarter;
	private final ModelPart RightTwoQuarter;
	private final ModelPart RightOneQuarter;
	private final ModelPart LeftWing;
	private final ModelPart LeftThreeQuarter;
	private final ModelPart LeftTwoQuarter;
	private final ModelPart LeftOneQuarter;

	public AngelWingsModel(ModelPart root) {
		super(root);
		this.RightWing = root.getChild("RightWing");
		this.RightThreeQuarter = this.RightWing.getChild("RightThreeQuarter");
		this.RightTwoQuarter = this.RightThreeQuarter.getChild("RightTwoQuarter");
		this.RightOneQuarter = this.RightTwoQuarter.getChild("RightOneQuarter");
		this.LeftWing = root.getChild("LeftWing");
		this.LeftThreeQuarter = this.LeftWing.getChild("LeftThreeQuarter");
		this.LeftTwoQuarter = this.LeftThreeQuarter.getChild("LeftTwoQuarter");
		this.LeftOneQuarter = this.LeftTwoQuarter.getChild("LeftOneQuarter");
	}
	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightWing = partdefinition.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(29, 19).addBox(-1.1F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, 2.0F, 0.232F, -0.8035F, -0.3171F));

		PartDefinition RightThreeQuarter = RightWing.addOrReplaceChild("RightThreeQuarter", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.4316F, -0.656F, 0.2048F));

		PartDefinition RightFeatherOne_r1 = RightThreeQuarter.addOrReplaceChild("RightFeatherOne_r1", CubeListBuilder.create().texOffs(47, 19).addBox(0.0F, -2.0F, -1.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition RightMemberTwo_r1 = RightThreeQuarter.addOrReplaceChild("RightMemberTwo_r1", CubeListBuilder.create().texOffs(26, 10).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.0F, 0.5F, 0.0F, 0.0F, -0.0074F));

		PartDefinition RightTwoQuarter = RightThreeQuarter.addOrReplaceChild("RightTwoQuarter", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, -1.4668F, -0.1365F, -0.1373F));

		PartDefinition RightFeatherTwo_r1 = RightTwoQuarter.addOrReplaceChild("RightFeatherTwo_r1", CubeListBuilder.create().texOffs(44, -10).addBox(0.0F, -3.0F, -5.0F, 0.0F, 9.0F, 10.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition RightMemberThree_r1 = RightTwoQuarter.addOrReplaceChild("RightMemberThree_r1", CubeListBuilder.create().texOffs(0, 10).addBox(-0.4F, 0.0F, -0.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.0F, 0.5F, 0.0F, 0.0F, -0.0074F));

		PartDefinition RightOneQuarter = RightTwoQuarter.addOrReplaceChild("RightOneQuarter", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.7897F, -0.1609F, 0.1716F));

		PartDefinition RightFeatherThree_r1 = RightOneQuarter.addOrReplaceChild("RightFeatherThree_r1", CubeListBuilder.create().texOffs(0, -20).addBox(0.0F, -2.0F, -1.0F, 0.0F, 10.0F, 20.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition RightMemberFour_r1 = RightOneQuarter.addOrReplaceChild("RightMemberFour_r1", CubeListBuilder.create().texOffs(0, 20).addBox(-0.4F, 0.0F, -0.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.0F, 0.5F, 0.0F, 0.0F, -0.0074F));

		PartDefinition LeftWing = partdefinition.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(29, 19).mirror().addBox(-0.9F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 2.0F, 2.0F, 0.232F, 0.8035F, 0.3171F));

		PartDefinition LeftThreeQuarter = LeftWing.addOrReplaceChild("LeftThreeQuarter", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.4316F, 0.656F, -0.2048F));

		PartDefinition LeftFeatherOne_r1 = LeftThreeQuarter.addOrReplaceChild("LeftFeatherOne_r1", CubeListBuilder.create().texOffs(47, 19).mirror().addBox(0.0F, -2.0F, -1.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition LeftMemberTwo_r1 = LeftThreeQuarter.addOrReplaceChild("LeftMemberTwo_r1", CubeListBuilder.create().texOffs(26, 10).mirror().addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.0F, 0.5F, 0.0F, 0.0F, 0.0074F));

		PartDefinition LeftTwoQuarter = LeftThreeQuarter.addOrReplaceChild("LeftTwoQuarter", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, -1.4668F, 0.1365F, 0.1373F));

		PartDefinition LeftFeatherTwo_r1 = LeftTwoQuarter.addOrReplaceChild("LeftFeatherTwo_r1", CubeListBuilder.create().texOffs(44, -10).mirror().addBox(0.0F, -3.0F, -5.0F, 0.0F, 9.0F, 10.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition LeftMemberThree_r1 = LeftTwoQuarter.addOrReplaceChild("LeftMemberThree_r1", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-0.6F, 0.0F, -0.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.0F, 0.5F, 0.0F, 0.0F, 0.0074F));

		PartDefinition LeftOneQuarter = LeftTwoQuarter.addOrReplaceChild("LeftOneQuarter", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, -0.7897F, 0.1609F, -0.1716F));

		PartDefinition LeftFeatherThree_r1 = LeftOneQuarter.addOrReplaceChild("LeftFeatherThree_r1", CubeListBuilder.create().texOffs(0, -20).mirror().addBox(0.0F, -2.0F, -1.0F, 0.0F, 10.0F, 20.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LeftMemberFour_r1 = LeftOneQuarter.addOrReplaceChild("LeftMemberFour_r1", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-0.6F, 0.0F, -0.5F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.0F, 0.5F, 0.0F, 0.0F, 0.0074F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 0.507271F;
		float f1 = -0.117359F;
		float f2 = 2.0F;
		float f3 = 0.655878F;
		float f5 = -1.48F;
		float f6 = -0.78F;
		if (entity.isFallFlying()) {
			float f4 = 1.0F;
			Vec3 vec3 = entity.getDeltaMovement();
			if (vec3.y < (double)0.0F) {
				Vec3 vec31 = vec3.normalize();
				f4 = 1.0F - (float)Math.pow(-vec31.y, 1.5F);
			}


			f = f4 * ((float)Math.PI / 2F) + (1F - f4) * f1;
			f5 = f4 * ((float)Math.PI / 2F) + (-1.15F - f4);
			f6 = f4 * ((float)Math.PI / 2F) + (-1.25F - f4);
		} else if (entity.isCrouching()) {
			f = ((float)Math.PI / 4F);

			f2 = 5.0F;
			f5 = -0.9F;
			f6 = -0.4F;

		}
		this.LeftWing.y = f2;
		this.LeftTwoQuarter.xRot = f5;
		this.LeftOneQuarter.xRot = f6;
		if (entity instanceof AbstractClientPlayer abstractclientplayer) {
			abstractclientplayer.elytraRotX += (f - abstractclientplayer.elytraRotX) * 0.1F;
			abstractclientplayer.elytraRotY += (f3 - abstractclientplayer.elytraRotY) * 0.1F;
			abstractclientplayer.elytraRotZ += (f1 - abstractclientplayer.elytraRotZ) * 0.1F;
			this.LeftThreeQuarter.xRot = abstractclientplayer.elytraRotX;
			this.LeftThreeQuarter.yRot = abstractclientplayer.elytraRotY;
			this.LeftThreeQuarter.zRot = abstractclientplayer.elytraRotZ;
		} else {
			this.LeftThreeQuarter.xRot = f;
			this.LeftThreeQuarter.yRot = f3;
			this.LeftThreeQuarter.zRot = f1;
		}

		this.RightThreeQuarter.yRot = -this.LeftThreeQuarter.yRot;
		this.RightWing.y = this.LeftWing.y;
		this.RightThreeQuarter.xRot = this.LeftThreeQuarter.xRot;
		this.RightThreeQuarter.zRot = -this.LeftThreeQuarter.zRot;

		this.RightTwoQuarter.xRot = this.LeftTwoQuarter.xRot;
		this.RightOneQuarter.xRot = this.LeftOneQuarter.xRot;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color) {
		this.RightWing.render(stack, consumer, light, overlay, color);
		this.LeftWing.render(stack, consumer, light, overlay, color);
	}

}
