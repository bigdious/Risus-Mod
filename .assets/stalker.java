// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class stalker<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "stalker"), "main");
	private final ModelPart Body;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart Head;

	public stalker(ModelPart root) {
		this.Body = root.getChild("Body");
		this.leg0 = this.Body.getChild("leg0");
		this.leg1 = this.Body.getChild("leg1");
		this.leg2 = this.Body.getChild("leg2");
		this.leg3 = this.Body.getChild("leg3");
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 43).addBox(-4.0F, -9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 26).addBox(-7.0F, -27.0F, 0.0F, 17.0F, 17.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition FalseHead_r1 = Body.addOrReplaceChild("FalseHead_r1", CubeListBuilder.create().texOffs(32, 48).addBox(-3.0F, -7.5147F, -4.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -15.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition Body2_r1 = Body.addOrReplaceChild("Body2_r1", CubeListBuilder.create().texOffs(16, 50).addBox(-2.0F, -8.0F, -2.01F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -9.0F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition Body1_r1 = Body.addOrReplaceChild("Body1_r1", CubeListBuilder.create().texOffs(0, 50).addBox(-3.0F, -9.0F, -2.02F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leg0 = Body.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, 4.0F));

		PartDefinition leg1 = Body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 4.0F));

		PartDefinition leg2 = Body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, -4.0F));

		PartDefinition leg3 = Body.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, -4.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 4.0F, 0.0F));

		PartDefinition Eye3_r1 = Head.addOrReplaceChild("Eye3_r1", CubeListBuilder.create().texOffs(20, 8).addBox(0.8284F, -4.8284F, -2.975F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.005F, 0.0F, 0.0F, 2.3562F));

		PartDefinition Eye2_r1 = Head.addOrReplaceChild("Eye2_r1", CubeListBuilder.create().texOffs(20, 0).addBox(0.8284F, -4.8284F, -2.975F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.005F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}