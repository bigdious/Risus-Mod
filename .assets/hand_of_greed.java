// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class hand_of_greed<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "hand_of_greed"), "main");
	private final ModelPart OuterHand;
	private final ModelPart InnerHand;

	public hand_of_greed(ModelPart root) {
		this.OuterHand = root.getChild("OuterHand");
		this.InnerHand = root.getChild("InnerHand");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition OuterHand = partdefinition.addOrReplaceChild("OuterHand", CubeListBuilder.create().texOffs(16, 0).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition InnerHand = partdefinition.addOrReplaceChild("InnerHand", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 25.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		OuterHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		InnerHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}