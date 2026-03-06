package com.bigdious.risus.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class AngelWingsModel extends HumanoidModel<LivingEntity> {
	private final ModelPart leftWing0;
	private final ModelPart leftWing1;
	private final ModelPart leftWing2;
	private final ModelPart leftWing3;
	private final ModelPart rightWing0;
	private final ModelPart rightWing1;
	private final ModelPart rightWing2;
	private final ModelPart rightWing3;


	public AngelWingsModel(ModelPart root) {
		super(root);
		this.leftWing0 = root.getChild("leftWing0");
		this.leftWing1 = this.leftWing0.getChild("leftWing1");
		this.leftWing2 = this.leftWing1.getChild("leftWing2");
		this.leftWing3 = this.leftWing2.getChild("leftWing3");
		this.rightWing0 = root.getChild("rightWing0");
		this.rightWing1 = this.rightWing0.getChild("rightWing1");
		this.rightWing2 = this.rightWing1.getChild("rightWing2");
		this.rightWing3 = this.rightWing2.getChild("rightWing3");
	}
	public static LayerDefinition create() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftWing0 = partdefinition.addOrReplaceChild("leftWing0", CubeListBuilder.create().texOffs(0, 2).addBox(-8.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(42, 14).addBox(-8.0F, 1.0F, 0.0F, 9.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.0F, 3.0F, 0.2748F, 0.3169F, 0.5969F));

		PartDefinition leftWing1 = leftWing0.addOrReplaceChild("leftWing1", CubeListBuilder.create().texOffs(0, 6).addBox(-8.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(22, 12).addBox(-8.0F, 1.0F, 0.0F, 10.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, -0.1033F, -0.8195F, 0.0774F));

		PartDefinition leftWing2 = leftWing1.addOrReplaceChild("leftWing2", CubeListBuilder.create().texOffs(22, 6).addBox(-11.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 10).addBox(-11.0F, 1.0F, 0.0F, 11.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, -0.0662F, -0.2606F, -1.312F));

		PartDefinition leftWing3 = leftWing2.addOrReplaceChild("leftWing3", CubeListBuilder.create().texOffs(22, 10).addBox(-11.0F, -0.5F, -0.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 21).addBox(-23.0F, -0.5F, 0.0F, 24.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.0F, 0.0F, 0.2424F, -0.3878F, -0.793F));

		PartDefinition rightWing0 = partdefinition.addOrReplaceChild("rightWing0", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-1.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(42, 14).mirror().addBox(-1.0F, 1.0F, 0.0F, 9.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 1.0F, 3.0F, 0.2748F, -0.3169F, -0.5969F));

		PartDefinition rightWing1 = rightWing0.addOrReplaceChild("rightWing1", CubeListBuilder.create().texOffs(0, 6).mirror().addBox(-1.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(22, 12).mirror().addBox(-2.0F, 1.0F, 0.0F, 10.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, -0.1033F, 0.8195F, -0.0774F));

		PartDefinition rightWing2 = rightWing1.addOrReplaceChild("rightWing2", CubeListBuilder.create().texOffs(22, 6).mirror().addBox(-1.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 10).mirror().addBox(0.0F, 1.0F, 0.0F, 11.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, -0.0662F, 0.2606F, 1.312F));

		PartDefinition rightWing3 = rightWing2.addOrReplaceChild("rightWing3", CubeListBuilder.create().texOffs(22, 10).mirror().addBox(-1.0F, -0.5F, -0.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 21).mirror().addBox(-1.0F, -0.5F, 0.0F, 24.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.0F, 0.0F, 0.0F, 0.2424F, 0.3878F, 0.793F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 0.000F;
		float f1 = 0.0F;
		float f2 = 0.0F;
//		float f3 = 0.655878F;
//		float f5 = -1.48F;
//		float f6 = -0.78F;
		if (entity.isSprinting()) {

		}
		if (entity.isCrouching()) {
			f = ((float) Math.PI / 4F);
			f1 = ((float) Math.PI / 8F);
			f2 = 3.0F;
		}
		if (entity.isFallFlying()) {

//			float f4 = 1.0F;
//			Vec3 vec3 = entity.getDeltaMovement();
//			if (vec3.y < (double)0.0F) {
//				Vec3 vec31 = vec3.normalize();
//				f4 = 1.0F - (float)Math.pow(-vec31.y, 1.5F);
//			}
//
//
//			f = f4 * ((float)Math.PI / 2F) + (1F - f4) * f1;
//			f5 = f4 * ((float)Math.PI / 2F) + (-1.15F - f4);
//			f6 = f4 * ((float)Math.PI / 2F) + (-1.25F - f4);

		} else {
			this.leftWing0.xRot =  (float)Math.toRadians(15.7442D) - f1;
			this.leftWing0.yRot = (float)Math.toRadians(18.1577) - f;
			this.leftWing0.zRot = (float)Math.toRadians(34.2017);

			this.leftWing1.xRot = (float)Math.toRadians(-5.9178D);
			this.leftWing1.yRot = (float)Math.toRadians(-46.9517D);
			this.leftWing1.zRot = (float)Math.toRadians(-4.4369D);

			this.leftWing2.xRot = (float)Math.toRadians(-3.7913D);
			this.leftWing2.yRot = (float)Math.toRadians(-14.9331D);
			this.leftWing2.zRot = (float)Math.toRadians(-75.1746D) + f;

			this.leftWing3.xRot = (float)Math.toRadians(13.8884D) - f1;
			this.leftWing3.yRot = (float)Math.toRadians(-22.2166D);
			this.leftWing3.zRot = (float)Math.toRadians(-45.4346D) + f;

		}
		this.leftWing0.y = f2;
//		this.leftWing1.xRot = f5;
//		this.leftWing2.xRot = f6;
//		if (entity instanceof AbstractClientPlayer abstractclientplayer) {
////			abstractclientplayer.elytraRotX += (f + abstractclientplayer.elytraRotX) * 0.1F;
////			abstractclientplayer.elytraRotY += (f - abstractclientplayer.elytraRotY) * 0.1F;
////			abstractclientplayer.elytraRotZ += (f - abstractclientplayer.elytraRotZ) * 0.1F;
////			this.leftWing1.xRot = abstractclientplayer.elytraRotX;
////			this.leftWing1.yRot = abstractclientplayer.elytraRotY;
////			this.leftWing1.zRot = abstractclientplayer.elytraRotZ;
//		} else {

//		}
//
//
		this.rightWing0.y = this.leftWing0.y;
		this.rightWing0.xRot = this.leftWing0.xRot;
		this.rightWing0.yRot = -this.leftWing0.yRot;
		this.rightWing0.zRot = -this.leftWing0.zRot;
		this.rightWing1.zRot = -this.leftWing1.zRot;
		this.rightWing1.yRot = -this.leftWing1.yRot;
		this.rightWing1.xRot = this.leftWing1.xRot;
		this.rightWing2.zRot = -this.leftWing2.zRot;
		this.rightWing2.yRot = -this.leftWing2.yRot;
		this.rightWing2.xRot = this.leftWing2.xRot;
		this.rightWing3.zRot = -this.leftWing3.zRot;
		this.rightWing3.yRot = -this.leftWing3.yRot;
		this.rightWing3.xRot = this.leftWing3.xRot;
//
//		this.rightWing2.xRot = this.leftWing2.xRot;
//		this.rightWing3.xRot = this.leftWing3.xRot;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color) {
		this.rightWing0.render(stack, consumer, light, overlay, color);
		this.leftWing0.render(stack, consumer, light, overlay, color);
	}

}
