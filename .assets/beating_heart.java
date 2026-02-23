// Made with Blockbench 5.0.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class beating_heart<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "beating_heart"), "main");
	private final ModelPart heartMeat;
	private final ModelPart pinkTube;
	private final ModelPart redTube;
	private final ModelPart blueTube;

	public beating_heart(ModelPart root) {
		this.heartMeat = root.getChild("heartMeat");
		this.pinkTube = root.getChild("pinkTube");
		this.redTube = root.getChild("redTube");
		this.blueTube = root.getChild("blueTube");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition heartMeat = partdefinition.addOrReplaceChild("heartMeat", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -3.0F, -6.0F, 5.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 50).addBox(-5.0F, 0.0F, -6.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 39).addBox(-4.0F, 4.0F, -5.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 31).addBox(-3.0F, 7.0F, -4.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-5.0F, -3.0F, -4.0F, 5.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 1.0F));

		PartDefinition pinkTube = partdefinition.addOrReplaceChild("pinkTube", CubeListBuilder.create().texOffs(30, 2).addBox(-3.0F, -2.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(-2.0F, -4.0F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 2).addBox(0.0F, -5.0F, -2.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -1.0F));

		PartDefinition redTube = partdefinition.addOrReplaceChild("redTube", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -4.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(2.0F, -4.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 25).addBox(-2.0F, -4.0F, -2.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-2.5F, -5.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 8).addBox(-0.5F, -5.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 18).addBox(1.5F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 2.0F));

		PartDefinition blueTube = partdefinition.addOrReplaceChild("blueTube", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -3.0F, -4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(26, 11).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 11.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		heartMeat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		pinkTube.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		redTube.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		blueTube.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}