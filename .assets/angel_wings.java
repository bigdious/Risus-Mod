// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class angel_wings<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "angel_wings"), "main");
	private final ModelPart leftWing0;
	private final ModelPart leftWing1;
	private final ModelPart leftWing2;
	private final ModelPart leftWing3;
	private final ModelPart rightWing0;
	private final ModelPart rightWing1;
	private final ModelPart rightWing2;
	private final ModelPart rightWing3;

	public angel_wings(ModelPart root) {
		this.leftWing0 = root.getChild("leftWing0");
		this.leftWing1 = this.leftWing0.getChild("leftWing1");
		this.leftWing2 = this.leftWing1.getChild("leftWing2");
		this.leftWing3 = this.leftWing2.getChild("leftWing3");
		this.rightWing0 = root.getChild("rightWing0");
		this.rightWing1 = this.rightWing0.getChild("rightWing1");
		this.rightWing2 = this.rightWing1.getChild("rightWing2");
		this.rightWing3 = this.rightWing2.getChild("rightWing3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
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
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leftWing0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightWing0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}