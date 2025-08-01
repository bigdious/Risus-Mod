// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class crown_of_bones<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "crown_of_bones"), "main");
	private final ModelPart bb_main;

	public crown_of_bones(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -11.5F, -4.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition crown_3_r1 = bb_main.addOrReplaceChild("crown_3_r1", CubeListBuilder.create().texOffs(9, 17).mirror().addBox(-1.0F, 2.5F, -4.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition crown_2_r1 = bb_main.addOrReplaceChild("crown_2_r1", CubeListBuilder.create().texOffs(9, 17).addBox(0.0F, 2.5F, -4.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_secondary_horn_1_r1 = bb_main.addOrReplaceChild("left_secondary_horn_1_r1", CubeListBuilder.create().texOffs(18, 17).mirror().addBox(-1.5F, -3.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -6.0F, 0.5F, -0.3491F, 0.0F, -2.0508F));

		PartDefinition left_secondary_horn_2_r1 = bb_main.addOrReplaceChild("left_secondary_horn_2_r1", CubeListBuilder.create().texOffs(32, 20).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.2274F, -3.7091F, 0.8728F, 2.846F, -0.0779F, 0.4479F));

		PartDefinition left_secondary_horn_3_r1 = bb_main.addOrReplaceChild("left_secondary_horn_3_r1", CubeListBuilder.create().texOffs(12, 27).mirror().addBox(-0.0122F, -1.997F, 0.1723F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.9878F, -0.503F, -0.4223F, 2.3243F, -0.3131F, -0.1834F));

		PartDefinition right_secondary_horn_3_r1 = bb_main.addOrReplaceChild("right_secondary_horn_3_r1", CubeListBuilder.create().texOffs(12, 27).addBox(-0.9878F, -1.997F, 0.1723F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.9878F, -0.503F, -0.4223F, 2.3243F, 0.3131F, 0.1834F));

		PartDefinition right_secondary_horn_2_r1 = bb_main.addOrReplaceChild("right_secondary_horn_2_r1", CubeListBuilder.create().texOffs(32, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.2274F, -3.7091F, 0.8728F, 0.2956F, -0.0779F, 2.6937F));

		PartDefinition right_secondary_horn_1_r1 = bb_main.addOrReplaceChild("right_secondary_horn_1_r1", CubeListBuilder.create().texOffs(18, 17).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -6.0F, 0.5F, -0.3491F, 0.0F, 2.0508F));

		PartDefinition left_main_horn_1_r1 = bb_main.addOrReplaceChild("left_main_horn_1_r1", CubeListBuilder.create().texOffs(18, 17).mirror().addBox(-1.5F, -1.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -9.0F, -4.5F, 0.583F, 0.1796F, -0.583F));

		PartDefinition left_main_horn_2_r1 = bb_main.addOrReplaceChild("left_main_horn_2_r1", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(-1.1011F, -3.7261F, -1.1011F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.8036F, -9.47F, -4.8036F, 0.3657F, 0.1255F, -0.2778F));

		PartDefinition left_main_horn_3_r1 = bb_main.addOrReplaceChild("left_main_horn_3_r1", CubeListBuilder.create().texOffs(4, 27).mirror().addBox(-0.4279F, -2.8858F, -0.124F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.1304F, -12.8007F, -6.5155F, 0.1011F, -0.0852F, 0.0246F));

		PartDefinition right_main_horn_3_r1 = bb_main.addOrReplaceChild("right_main_horn_3_r1", CubeListBuilder.create().texOffs(4, 27).addBox(-0.5721F, -2.8858F, -0.124F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1304F, -12.8007F, -6.5155F, 0.1011F, 0.0852F, -0.0246F));

		PartDefinition right_main_horn_2_r1 = bb_main.addOrReplaceChild("right_main_horn_2_r1", CubeListBuilder.create().texOffs(20, 24).addBox(-0.8989F, -3.7261F, -1.1011F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8036F, -9.47F, -4.8036F, 0.3657F, -0.1255F, 0.2778F));

		PartDefinition right_main_horn_1_r1 = bb_main.addOrReplaceChild("right_main_horn_1_r1", CubeListBuilder.create().texOffs(18, 17).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -9.0F, -4.5F, 0.583F, -0.1796F, 0.583F));

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