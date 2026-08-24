// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class sinner_robe_chestplate<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "sinner_robe_chestplate"), "main");
	private final ModelPart bb_main;

	public sinner_robe_chestplate(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
		.texOffs(52, 0).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.6F))
		.texOffs(0, 17).addBox(-3.0F, 0.0F, -2.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(5, 16).addBox(-1.0F, -0.5F, 2.25F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 16).addBox(-1.0F, 5.5F, 2.25F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, 2.0F, -2.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, 4.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(1.0F, 4.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(2.0F, 6.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-4.0F, 6.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, 0.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, 2.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, 4.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, 6.0F, 0.75F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 0).addBox(-3.0F, 0.0F, 2.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.6F))
		.texOffs(52, 0).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.6F))
		.texOffs(16, 0).addBox(-3.5F, 1.0F, -5.65F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(19, 7).mirror().addBox(-3.5F, 2.0F, -5.65F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}