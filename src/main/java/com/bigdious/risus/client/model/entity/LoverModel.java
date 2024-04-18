package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Lover;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class LoverModel<T extends Lover> extends HierarchicalModel<T> {
	private final ModelPart body0;
	private final ModelPart root;
	private final ModelPart bodythreequarter;
	private final ModelPart bodytwoquarter;
	private final ModelPart bodytail;
	private final ModelPart stalk0;
	private final ModelPart stalk1;
	private final ModelPart stalk2;
	private final ModelPart stalk3;
	private final ModelPart stalk4;
	private final ModelPart stalk5;
	private final ModelPart stalk6;
	private final ModelPart stalk7;
	private final ModelPart postule0;
	private final ModelPart postule1;
	private final ModelPart postule2;
	private final ModelPart postule3;
	private final ModelPart postule4;
	private final ModelPart postule5;
	private final ModelPart postule6;
	private final ModelPart postule7;
	private final ModelPart everything;

	public LoverModel(ModelPart root) {
		this.root = root;
		this.everything = root.getChild("everything");
		this.body0 = everything.getChild("body0");
		//I know this may look weird but stick with me, we need it to wiggle
		this.bodythreequarter = body0.getChild("bodythreequarter");
		this.bodytwoquarter = bodythreequarter.getChild("bodytwoquarter");
		this.bodytail = bodytwoquarter.getChild("bodytail");
		this.stalk0 = bodytwoquarter.getChild("stalk0");
		this.stalk1 = bodytail.getChild("stalk1");
		this.stalk2 = body0.getChild("stalk2");
		this.stalk3 = bodythreequarter.getChild("stalk3");
		this.stalk4 = bodythreequarter.getChild("stalk4");
		this.stalk5 = bodythreequarter.getChild("stalk5");
		this.stalk6 = bodytwoquarter.getChild("stalk6");
		this.stalk7 = bodytail.getChild("stalk7");
		this.postule0 = stalk0.getChild("postule0");
		this.postule1 = stalk1.getChild("postule1");
		this.postule2 = stalk2.getChild("postule2");
		this.postule3 = stalk3.getChild("postule3");
		this.postule4 = stalk4.getChild("postule4");
		this.postule5 = stalk5.getChild("postule5");
		this.postule6 = stalk6.getChild("postule6");
		this.postule7 = stalk7.getChild("postule7");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition everything = partdefinition.addOrReplaceChild("everything", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.5421F, -16.9252F, 7.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body0 = everything.addOrReplaceChild("body0", CubeListBuilder.create(), PartPose.offset(-0.5F, -9.0875F, -0.7669F));

		PartDefinition frontright = body0.addOrReplaceChild("frontright", CubeListBuilder.create().texOffs(0, 10).addBox(-2.0F, -4.5F, -5.0F, 4.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(39, 0).addBox(-3.0F, 1.5F, 0.0F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.5875F, -8.2331F, 0.0516F, 0.2112F, 0.3119F));

		PartDefinition fronttopteethright = body0.addOrReplaceChild("fronttopteethright", CubeListBuilder.create().texOffs(31, 0).addBox(-1.9306F, 0.2962F, -4.7151F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.26F, -1.7358F, -7.1915F, -0.0344F, 0.2147F, -0.09F));

		PartDefinition frontleft = body0.addOrReplaceChild("frontleft", CubeListBuilder.create().texOffs(28, 10).addBox(-2.0F, -4.5F, -5.0F, 4.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(41, 0).addBox(2.0F, 1.5F, 0.0F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.5875F, -8.2331F, 0.0516F, -0.2112F, -0.3119F));

		PartDefinition fronttopteethleft = body0.addOrReplaceChild("fronttopteethleft", CubeListBuilder.create().texOffs(27, 0).addBox(-0.0694F, 0.2962F, -4.7151F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.26F, -1.7358F, -7.1915F, -0.0439F, -0.213F, 0.1346F));

		PartDefinition bodythreequarter = body0.addOrReplaceChild("bodythreequarter", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.4858F, -3.4173F, -0.0436F, 0.0F, 0.0F));

		PartDefinition firstmiddlebotteethright = bodythreequarter.addOrReplaceChild("firstmiddlebotteethright", CubeListBuilder.create().texOffs(28, 2).addBox(-1.0F, -3.8637F, -4.0353F, 1.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.8069F, 4.0805F, -0.203F, 0.0886F, 0.3381F));

		PartDefinition firstmiddletopteethright = bodythreequarter.addOrReplaceChild("firstmiddletopteethright", CubeListBuilder.create().texOffs(23, 8).addBox(-1.9397F, 0.3417F, -4.5149F, 2.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3827F, -3.1613F, 4.0498F, -0.2172F, -0.0427F, -0.2591F));

		PartDefinition firstmiddleright = bodythreequarter.addOrReplaceChild("firstmiddleright", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, -7.8637F, -6.0353F, 4.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.8069F, 4.0805F, -0.203F, 0.0886F, 0.3381F));

		PartDefinition firstmiddleboteethleft = bodythreequarter.addOrReplaceChild("firstmiddleboteethleft", CubeListBuilder.create().texOffs(26, 2).addBox(0.0F, -3.8637F, -4.0353F, 1.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(28, 29).addBox(-4.0F, -7.8637F, -6.0353F, 4.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.8069F, 4.0805F, -0.203F, -0.0886F, -0.3381F));

		PartDefinition firstmiddletopteethleft = bodythreequarter.addOrReplaceChild("firstmiddletopteethleft", CubeListBuilder.create().texOffs(20, 8).addBox(-0.0603F, 0.3417F, -4.0149F, 2.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3902F, -3.0518F, 4.5376F, -0.2172F, 0.0427F, 0.2591F));

		PartDefinition bodytwoquarter = bodythreequarter.addOrReplaceChild("bodytwoquarter", CubeListBuilder.create(), PartPose.offset(0.0785F, 2.777F, 7.7679F));

		PartDefinition secondmiddlebottoothright = bodytwoquarter.addOrReplaceChild("secondmiddlebottoothright", CubeListBuilder.create().texOffs(22, 2).addBox(-2.0F, 1.0F, -5.0F, 1.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 47).addBox(-1.0F, -3.0F, -5.0F, 4.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4215F, -2.5475F, 4.5237F, -0.026F, -0.2485F, 0.4134F));

		PartDefinition secondmiddletoptoothright = bodytwoquarter.addOrReplaceChild("secondmiddletoptoothright", CubeListBuilder.create().texOffs(14, 6).addBox(-1.8876F, 0.3957F, -4.7644F, 2.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4376F, -5.1623F, 4.0822F, 0.1331F, -0.212F, -0.2586F));

		PartDefinition secondmiddlebottoothleft = bodytwoquarter.addOrReplaceChild("secondmiddlebottoothleft", CubeListBuilder.create().texOffs(24, 2).addBox(1.0F, 1.0F, -5.0F, 1.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(28, 47).addBox(-3.0F, -3.0F, -5.0F, 4.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5785F, -2.5475F, 4.5237F, -0.026F, 0.2485F, -0.4134F));

		PartDefinition secondmiddletoptoothleft = bodytwoquarter.addOrReplaceChild("secondmiddletoptoothleft", CubeListBuilder.create().texOffs(10, 6).addBox(-0.1124F, 0.3957F, -4.7644F, 2.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5947F, -5.1623F, 4.0822F, 0.1237F, 0.2176F, 0.2143F));

		PartDefinition bodytail = bodytwoquarter.addOrReplaceChild("bodytail", CubeListBuilder.create(), PartPose.offset(-0.0785F, -2.0467F, 9.8198F));

		PartDefinition tail = bodytail.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 64).addBox(-3.0F, -2.4693F, -0.9037F, 6.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6756F, -0.3731F, 0.6109F, 0.0F, 0.0F));

		PartDefinition stalk1 = bodytail.addOrReplaceChild("stalk1", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7359F, -0.9897F, 5.1247F, 0.4126F, 1.1906F, -2.7199F));

		PartDefinition postule1 = stalk1.addOrReplaceChild("postule1", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -1.1114F, -0.5214F, 0.5543F));

		PartDefinition secondelement1 = stalk1.addOrReplaceChild("secondelement1", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		PartDefinition stalk7 = bodytail.addOrReplaceChild("stalk7", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2641F, -4.9897F, 7.1247F, 2.4959F, 0.6499F, -0.2122F));

		PartDefinition postule7 = stalk7.addOrReplaceChild("postule7", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -0.6751F, -0.5214F, 0.5543F));

		PartDefinition secondelement7 = stalk7.addOrReplaceChild("secondelement7", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		PartDefinition stalk6 = bodytwoquarter.addOrReplaceChild("stalk6", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.6574F, -1.0364F, 0.9445F, 0.0283F, 0.3785F, 2.7331F));

		PartDefinition postule6 = stalk6.addOrReplaceChild("postule6", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -0.7624F, -0.5214F, 0.5543F));

		PartDefinition secondelement6 = stalk6.addOrReplaceChild("secondelement6", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		PartDefinition stalk0 = bodytwoquarter.addOrReplaceChild("stalk0", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3426F, -3.0364F, 3.9445F, 0.0604F, 0.7381F, 0.1637F));

		PartDefinition postule0 = stalk0.addOrReplaceChild("postule0", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -0.806F, -0.5214F, 0.5543F));

		PartDefinition secondelement0 = stalk0.addOrReplaceChild("secondelement0", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		PartDefinition stalk5 = bodythreequarter.addOrReplaceChild("stalk5", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2641F, -3.2594F, 4.7124F, -0.2272F, 0.5146F, 1.4132F));

		PartDefinition postule5 = stalk5.addOrReplaceChild("postule5", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -1.1114F, -0.5214F, 0.5543F));

		PartDefinition secondelement5 = stalk5.addOrReplaceChild("secondelement5", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		PartDefinition stalk3 = bodythreequarter.addOrReplaceChild("stalk3", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2641F, 0.7406F, -0.2876F, -0.8779F, 0.1689F, -0.8282F));

		PartDefinition postule3 = stalk3.addOrReplaceChild("postule3", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -0.6751F, -0.5214F, 0.5543F));

		PartDefinition secondelement3 = stalk3.addOrReplaceChild("secondelement3", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		PartDefinition stalk4 = bodythreequarter.addOrReplaceChild("stalk4", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7359F, 3.7406F, -1.2876F, -1.2198F, -0.2578F, -2.631F));

		PartDefinition postule4 = stalk4.addOrReplaceChild("postule4", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -1.0242F, -0.5214F, 0.5543F));

		PartDefinition secondelement4 = stalk4.addOrReplaceChild("secondelement4", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		PartDefinition stalk2 = body0.addOrReplaceChild("stalk2", CubeListBuilder.create().texOffs(17, 0).addBox(-2.2359F, -0.5111F, -0.4208F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7359F, -3.9435F, -9.7375F, 0.1403F, -0.4062F, 1.5024F));

		PartDefinition postule2 = stalk2.addOrReplaceChild("postule2", CubeListBuilder.create().texOffs(20, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0536F, -2.065F, -2.1622F, -1.1114F, -0.5214F, 0.5543F));

		PartDefinition secondelement2 = stalk2.addOrReplaceChild("secondelement2", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7359F, -0.0111F, 0.0792F, -0.8933F, -0.5214F, 0.5543F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float swinging = Mth.sin(ageInTicks * 0.15F) * 0.15F;
		float pulsing1 = Mth.sin(ageInTicks * 0.1F) * 0.1F;
		float pulsing2 = Mth.sin((ageInTicks + 4) * 0.1F) * 0.1F;
		float pulsing3 = Mth.sin((ageInTicks + 8) * 0.1F) * 0.1F;
		float pulsing4 = Mth.sin((ageInTicks + 12) * 0.1F) * 0.1F;
		float pulsing5 = Mth.sin((ageInTicks + 16) * 0.1F) * 0.1F;
		float pulsing6 = Mth.sin((ageInTicks + 20) * 0.1F) * 0.1F;
		this.everything.xRot = Mth.sin(ageInTicks * 0.05F) * 0.05F + 0.05F;
		this.everything.yRot = -Mth.sin(ageInTicks * 0.10F) * 0.10F;
		this.bodythreequarter.yRot = swinging;
		this.bodythreequarter.xRot = -0.15F * Mth.sin(ageInTicks * -0.3F + (float)3) - 0.05F;
		this.bodythreequarter.zRot = swinging;
		this.bodytwoquarter.yRot = swinging;
		this.bodytwoquarter.xRot = 0.2F * Mth.sin(ageInTicks * -0.3F + (float)2) - 0.1F;
		this.bodytwoquarter.zRot = swinging;
		this.bodytail.yRot = -swinging;
		this.bodytail.xRot = 0.2F * Mth.sin(ageInTicks * -0.3F + (float)1) - 0.1F;
		this.bodytail.zRot = swinging;
		this.postule0.zScale = 1 + pulsing1;
		this.postule0.xScale = 1 + pulsing1;
		this.postule0.yScale = 1 + pulsing1;
		this.postule1.zScale = 1 + pulsing5;
		this.postule1.xScale = 1 + pulsing5;
		this.postule1.yScale = 1 + pulsing5;
		this.postule2.zScale = 1 + pulsing3;
		this.postule2.xScale = 1 + pulsing3;
		this.postule2.yScale = 1 + pulsing3;
		this.postule3.zScale = 1 + pulsing6;
		this.postule3.xScale = 1 + pulsing6;
		this.postule3.yScale = 1 + pulsing6;
		this.postule4.zScale = 1 + pulsing1;
		this.postule4.xScale = 1 + pulsing1;
		this.postule4.yScale = 1 + pulsing1;
		this.postule5.zScale = 1 + pulsing5;
		this.postule5.xScale = 1 + pulsing5;
		this.postule5.yScale = 1 + pulsing5;
		this.postule6.zScale = 1 + pulsing2;
		this.postule6.xScale = 1 + pulsing2;
		this.postule6.yScale = 1 + pulsing2;
		this.postule7.zScale = 1 + pulsing4;
		this.postule7.xScale = 1 + pulsing4;
		this.postule7.yScale = 1 + pulsing4;


	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		everything.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
