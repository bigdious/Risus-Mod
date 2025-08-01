// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class angel_wings<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "angel_wings"), "main");
	private final ModelPart RightWing;
	private final ModelPart RightThreeQuarter;
	private final ModelPart RightTwoQuarter;
	private final ModelPart RightOneQuarter;
	private final ModelPart LeftWing;
	private final ModelPart LeftThreeQuarter;
	private final ModelPart LeftTwoQuarter;
	private final ModelPart LeftOneQuarter;

	public angel_wings(ModelPart root) {
		this.RightWing = root.getChild("RightWing");
		this.RightThreeQuarter = this.RightWing.getChild("RightThreeQuarter");
		this.RightTwoQuarter = this.RightThreeQuarter.getChild("RightTwoQuarter");
		this.RightOneQuarter = this.RightTwoQuarter.getChild("RightOneQuarter");
		this.LeftWing = root.getChild("LeftWing");
		this.LeftThreeQuarter = this.LeftWing.getChild("LeftThreeQuarter");
		this.LeftTwoQuarter = this.LeftThreeQuarter.getChild("LeftTwoQuarter");
		this.LeftOneQuarter = this.LeftTwoQuarter.getChild("LeftOneQuarter");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
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
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		RightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}