// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class great_stool<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "great_stool"), "main");
	private final ModelPart bb_main;

	public great_stool(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 33).addBox(-6.0F, -21.0F, 3.0F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -24.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(8, 18).addBox(-7.0F, -22.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.5F, -16.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition support_4_r1 = bb_main.addOrReplaceChild("support_4_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition support_3_r1 = bb_main.addOrReplaceChild("support_3_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition support_2_r1 = bb_main.addOrReplaceChild("support_2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition leg_4_r1 = bb_main.addOrReplaceChild("leg_4_r1", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, -10.5F, -1.5F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -10.5F, -4.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition leg_3_r1 = bb_main.addOrReplaceChild("leg_3_r1", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, -10.5F, -1.5F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -10.5F, -4.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leg_2_r1 = bb_main.addOrReplaceChild("leg_2_r1", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, -10.5F, -1.5F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -10.5F, 4.5F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}