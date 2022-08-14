package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Maw;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class MawModel<T extends Maw> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart bigPincer1;
	private final ModelPart bigPincer2;
	private final ModelPart bigPincer3;
	private final ModelPart bigPincer4;
	private final ModelPart bigPincer5;
	private final ModelPart bigPincer6;
	private final ModelPart bigPincer7;
	private final ModelPart bigPincer8;

	public MawModel(ModelPart root) {
		this.root = root;
		this.bigPincer1 = root.getChild("bigPincer1");
		this.bigPincer2 = root.getChild("bigPincer2");
		this.bigPincer3 = root.getChild("bigPincer3");
		this.bigPincer4 = root.getChild("bigPincer4");
		this.bigPincer5 = root.getChild("bigPincer5");
		this.bigPincer6 = root.getChild("bigPincer6");
		this.bigPincer7 = root.getChild("bigPincer7");
		this.bigPincer8 = root.getChild("bigPincer8");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 0).addBox(-11.5F, 0.0F, -11.5F, 27.0F, 0.0F, 27.0F)
		.texOffs(0, 0).addBox(-3.0F, 0.0F, -11.0F, 10.0F, 2.0F, 4.0F)
		.texOffs(0, 0).addBox(-3.0F, 0.0F, 11.0F, 10.0F, 2.0F, 4.0F), PartPose.offset(-2.0F, 24.0F, -2.0F));

		body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 27).addBox(-7.0F, -1.0F, -3.0F, 18.0F, 2.0F, 18.0F), PartPose.offsetAndRotation(8.0F, 3.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 10.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(12.0F, 1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 10.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(-10.0F, 1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(2.0F, 1.0F, 2.0F, 0.0F, 2.3562F, 0.0F));

		body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(2.0F, 1.0F, 2.0F, 0.0F, -2.3562F, 0.0F));

		body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(2.0F, 1.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

		body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(2.0F, 1.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition bigPincer1 = partdefinition.addOrReplaceChild("bigPincer1", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -7.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offset(-12.0F, 24.0F, 0.0F));

		bigPincer1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(1.0F, -3.0F, -1.0F, 0.0F, 0.0F, 1.3963F));

		bigPincer1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(4.0F, -8.0F, -1.0F, 0.0F, 0.0F, 1.3963F));

		bigPincer1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(7.0F, -13.0F, -1.0F, 0.0F, 0.0F, 1.3963F));

		bigPincer1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition bigPincer2 = partdefinition.addOrReplaceChild("bigPincer2", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -12.0F));

		bigPincer2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(1.0F, -3.0F, 1.0F, -1.5708F, -0.1745F, 1.5708F));

		bigPincer2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(1.0F, -8.0F, 4.0F, -1.5708F, -0.1745F, 1.5708F));

		bigPincer2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(1.0F, -13.0F, 7.0F, -1.5708F, -0.1745F, 1.5708F));

		bigPincer2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 1.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

		bigPincer2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -1.5708F, -0.829F, 1.5708F));

		PartDefinition bigPincer3 = partdefinition.addOrReplaceChild("bigPincer3", CubeListBuilder.create(), PartPose.offset(12.0F, 24.0F, 0.0F));

		bigPincer3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-1.0F, -3.0F, 1.0F, 3.1416F, 0.0F, 1.7453F));

		bigPincer3.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-4.0F, -8.0F, 1.0F, 3.1416F, 0.0F, 1.7453F));

		bigPincer3.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-7.0F, -13.0F, 1.0F, 3.1416F, 0.0F, 1.7453F));

		bigPincer3.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

		bigPincer3.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 3.1416F, 0.0F, 2.3998F));

		PartDefinition bigPincer4 = partdefinition.addOrReplaceChild("bigPincer4", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 12.0F));

		bigPincer4.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-1.0F, -3.0F, -1.0F, 1.5708F, 0.1745F, 1.5708F));

		bigPincer4.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-1.0F, -8.0F, -4.0F, 1.5708F, 0.1745F, 1.5708F));

		bigPincer4.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-1.0F, -13.0F, -7.0F, 1.5708F, 0.1745F, 1.5708F));

		bigPincer4.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		bigPincer4.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 1.5708F, 0.829F, 1.5708F));

		PartDefinition bigPincer5 = partdefinition.addOrReplaceChild("bigPincer5", CubeListBuilder.create(), PartPose.offset(9.0F, 24.0F, 9.0F));

		bigPincer5.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-1.9289F, -3.0F, -0.5147F, 2.3638F, 0.1231F, 1.6948F));

		bigPincer5.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-4.0503F, -8.0F, -2.636F, 2.3638F, 0.1231F, 1.6948F));

		bigPincer5.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-6.1716F, -13.0F, -4.7574F, 2.3638F, 0.1231F, 1.6948F));

		bigPincer5.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offsetAndRotation(0.1924F, 1.0F, 0.1924F, -3.1416F, 0.7854F, 3.1416F));

		bigPincer5.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(-0.5147F, -8.0F, -0.5147F, 2.5474F, 0.5484F, 2.228F));

		PartDefinition bigPincer6 = partdefinition.addOrReplaceChild("bigPincer6", CubeListBuilder.create(), PartPose.offset(-9.0F, 24.0F, 9.0F));

		bigPincer6.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(0.5147F, -3.0F, -1.9289F, 0.7777F, 0.1231F, 1.4468F));

		bigPincer6.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(2.636F, -8.0F, -4.0503F, 0.7777F, 0.1231F, 1.4468F));

		bigPincer6.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(4.7574F, -13.0F, -6.1716F, 0.7777F, 0.1231F, 1.4468F));

		bigPincer6.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offsetAndRotation(-0.1924F, 1.0F, 0.1924F, 0.0F, 0.7854F, 0.0F));

		bigPincer6.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(0.5147F, -8.0F, -0.5147F, 0.5942F, 0.5484F, 0.9136F));

		PartDefinition bigPincer7 = partdefinition.addOrReplaceChild("bigPincer7", CubeListBuilder.create(), PartPose.offset(-9.0F, 24.0F, -9.0F));

		bigPincer7.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(1.9289F, -3.0F, 0.5147F, -0.7777F, -0.1231F, 1.4468F));

		bigPincer7.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(4.0503F, -8.0F, 2.636F, -0.7777F, -0.1231F, 1.4468F));

		bigPincer7.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(6.1716F, -13.0F, 4.7574F, -0.7777F, -0.1231F, 1.4468F));

		bigPincer7.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offsetAndRotation(-0.1924F, 1.0F, -0.1924F, 0.0F, -0.7854F, 0.0F));

		bigPincer7.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(0.5147F, -8.0F, 0.5147F, -0.5942F, -0.5484F, 0.9136F));

		PartDefinition bigPincer8 = partdefinition.addOrReplaceChild("bigPincer8", CubeListBuilder.create(), PartPose.offset(9.0F, 24.0F, -9.0711F));

		bigPincer8.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-0.5147F, -3.0F, 2.0F, -2.3638F, -0.1231F, 1.6948F));

		bigPincer8.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-2.636F, -8.0F, 4.1213F, -2.3638F, -0.1231F, 1.6948F));

		bigPincer8.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(-4.7574F, -13.0F, 6.2426F, -2.3638F, -0.1231F, 1.6948F));

		bigPincer8.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offsetAndRotation(0.1924F, 1.0F, -0.1213F, -3.1416F, -0.7854F, 3.1416F));

		bigPincer8.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F), PartPose.offsetAndRotation(-0.5147F, -8.0F, 0.5858F, -2.5474F, -0.5484F, 2.228F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.bigPincer1.zRot = Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.bigPincer2.xRot = -Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.bigPincer3.zRot = -Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.bigPincer4.xRot = Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.bigPincer5.zRot = -Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.bigPincer6.xRot = Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.bigPincer7.zRot = Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.bigPincer8.xRot = -Mth.sin(ageInTicks * 0.067F) * 0.05F;
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}