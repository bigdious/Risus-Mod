// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class drumstick_held_Converted<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "drumstick_held_converted"), "main");
	private final ModelPart bone;

	public drumstick_held_Converted(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 1).addBox(-9.0F, -27.0F, 7.0F, 2.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 28).addBox(-7.0F, -1.0F, 7.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(9, 29).addBox(-9.0F, -1.0F, 6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 28).mirror().addBox(-10.0F, -1.0F, 7.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(14, 29).mirror().addBox(-9.0F, -1.0F, 9.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(20, 14).addBox(-10.0F, -10.0F, 6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 12).addBox(-11.0F, -13.0F, 5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(12, 10).addBox(-12.0F, -18.0F, 4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(8, 8).addBox(-13.0F, -26.0F, 3.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}