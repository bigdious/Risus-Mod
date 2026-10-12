// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class ruinous_star<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "ruinous_star"), "main");
	private final ModelPart Body;
	private final ModelPart Star;
	private final ModelPart Face;

	public ruinous_star(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Star = this.Body.getChild("Star");
		this.Face = this.Body.getChild("Face");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition Star = Body.addOrReplaceChild("Star", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -38.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition SideBranchDownRightSideTwo_r1 = Star.addOrReplaceChild("SideBranchDownRightSideTwo_r1", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, 21.0F, 0.0F, 0.0F, 0.0F, -1.0036F));

		PartDefinition SideBranchDownRightSideOne_r1 = Star.addOrReplaceChild("SideBranchDownRightSideOne_r1", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(21.0F, 21.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition SideBranchDownLeftSideTwo_r1 = Star.addOrReplaceChild("SideBranchDownLeftSideTwo_r1", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 21.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition SideBranchDownLeftSideOne_r1 = Star.addOrReplaceChild("SideBranchDownLeftSideOne_r1", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, -12.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-21.0F, 21.0F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition SideBranchUpLeftSideTwo_r1 = Star.addOrReplaceChild("SideBranchUpLeftSideTwo_r1", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-21.0F, -21.0F, 0.0F, 0.0F, 0.0F, -1.0036F));

		PartDefinition SideBranchUpLeftSideOne_r1 = Star.addOrReplaceChild("SideBranchUpLeftSideOne_r1", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -21.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition SideBranchUpRightSideTwo_r1 = Star.addOrReplaceChild("SideBranchUpRightSideTwo_r1", CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, -21.0F, 0.0F, 0.0F, 0.0F, 1.0036F));

		PartDefinition SideBranchUpRightSideOne_r1 = Star.addOrReplaceChild("SideBranchUpRightSideOne_r1", CubeListBuilder.create().texOffs(28, 52).mirror().addBox(-1.0F, 0.4558F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(21.0F, -21.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition SideBranchUpRight_r1 = Star.addOrReplaceChild("SideBranchUpRight_r1", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -25.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition SideBranchDownRight_r1 = Star.addOrReplaceChild("SideBranchDownRight_r1", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -9.5F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.9602F, 14.9602F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition SideBranchDownLeft_r1 = Star.addOrReplaceChild("SideBranchDownLeft_r1", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-1.0F, -9.5F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-14.9602F, 14.9602F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition SideBranchUpLeft_r1 = Star.addOrReplaceChild("SideBranchUpLeft_r1", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-1.0F, -25.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition MainBranchDownSideTwo_r1 = Star.addOrReplaceChild("MainBranchDownSideTwo_r1", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3078F, 26.5091F, 0.0F, 0.0F, 0.0F, -2.8362F));

		PartDefinition MainBranchDownSideOne_r1 = Star.addOrReplaceChild("MainBranchDownSideOne_r1", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.3078F, 26.5091F, 0.0F, 0.0F, 0.0F, 2.8362F));

		PartDefinition MainBranchUpSideTwo_r1 = Star.addOrReplaceChild("MainBranchUpSideTwo_r1", CubeListBuilder.create().texOffs(20, 0).addBox(-13.4268F, -36.2412F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition MainBranchUpSideOne_r1 = Star.addOrReplaceChild("MainBranchUpSideOne_r1", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.3078F, -26.5091F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition MainBranchRightSideTwo_r1 = Star.addOrReplaceChild("MainBranchRightSideTwo_r1", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(26.5091F, 3.3078F, 0.0F, 0.0F, 0.0F, 1.2654F));

		PartDefinition MainBranchRightSideOne_r1 = Star.addOrReplaceChild("MainBranchRightSideOne_r1", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(37.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8762F));

		PartDefinition MainBrenchLeftSideTwo_r1 = Star.addOrReplaceChild("MainBrenchLeftSideTwo_r1", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -11.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.5091F, 3.3078F, 0.0F, 0.0F, 0.0F, -1.2654F));

		PartDefinition MainBrenchLeftSideOne_r1 = Star.addOrReplaceChild("MainBrenchLeftSideOne_r1", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-2.0F, 0.0F, -1.0F, 4.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-37.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8762F));

		PartDefinition MainBranchLeft_r1 = Star.addOrReplaceChild("MainBranchLeft_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -38.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition MainBranchRight_r1 = Star.addOrReplaceChild("MainBranchRight_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -38.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition MainBranchDown_r1 = Star.addOrReplaceChild("MainBranchDown_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -16.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition Face = Body.addOrReplaceChild("Face", CubeListBuilder.create().texOffs(16, 44).addBox(-4.0F, -5.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}