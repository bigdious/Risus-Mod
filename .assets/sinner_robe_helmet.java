// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class sinner_robe_helmet<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "sinner_robe_helmet"), "main");
	private final ModelPart bb_main;

	public sinner_robe_helmet(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.65F))
		.texOffs(40, 27).addBox(-2.0F, -3.88F, -5.7F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
		.texOffs(26, 17).addBox(-3.2F, -1.75F, -5.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 16).addBox(-2.0F, -2.88F, -5.75F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
		.texOffs(26, 17).addBox(2.2F, -1.75F, -5.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 26).addBox(-1.5F, -2.88F, -7.8F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition spyglass_4_r1 = bb_main.addOrReplaceChild("spyglass_4_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-2.0F, -3.5F, -12.2F, -1.5708F, 0.0F, 3.1416F));

		PartDefinition spyglass_2_r1 = bb_main.addOrReplaceChild("spyglass_2_r1", CubeListBuilder.create().texOffs(56, 19).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.5F, -6.5F, -1.5708F, 0.0F, -3.1416F));

		PartDefinition spyglass_2_r2 = bb_main.addOrReplaceChild("spyglass_2_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(2.0F, -3.5F, -12.2F, -1.5708F, 0.0F, 3.1416F));

		PartDefinition spyglass_1_r1 = bb_main.addOrReplaceChild("spyglass_1_r1", CubeListBuilder.create().texOffs(56, 19).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -3.5F, -6.5F, -1.5708F, 0.0F, -3.1416F));

		PartDefinition mask_r1 = bb_main.addOrReplaceChild("mask_r1", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -4.0F, 0.3F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -4.0F, -4.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition hoodie_part_3_r1 = bb_main.addOrReplaceChild("hoodie_part_3_r1", CubeListBuilder.create().texOffs(30, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -1.6042F, 9.3159F, -1.1345F, 0.0F, 0.0F));

		PartDefinition hoodie_part_2_r1 = bb_main.addOrReplaceChild("hoodie_part_2_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -2.0F, -1.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -3.6118F, 6.4489F, -0.6109F, 0.0F, 0.0F));

		PartDefinition hoodie_part_1_r1 = bb_main.addOrReplaceChild("hoodie_part_1_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.2618F, 0.0F, 0.0F));

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