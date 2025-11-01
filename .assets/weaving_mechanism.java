// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class weaving_mechanism<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "weaving_mechanism"), "main");
	private final ModelPart leg1;
	private final ModelPart half1;
	private final ModelPart leg2;
	private final ModelPart half2;
	private final ModelPart leg3;
	private final ModelPart half3;
	private final ModelPart leg4;
	private final ModelPart half4;
	private final ModelPart leg5;
	private final ModelPart half5;
	private final ModelPart leg6;
	private final ModelPart half6;
	private final ModelPart bb_main;

	public weaving_mechanism(ModelPart root) {
		this.leg1 = root.getChild("leg1");
		this.half1 = this.leg1.getChild("half1");
		this.leg2 = root.getChild("leg2");
		this.half2 = this.leg2.getChild("half2");
		this.leg3 = root.getChild("leg3");
		this.half3 = this.leg3.getChild("half3");
		this.leg4 = root.getChild("leg4");
		this.half4 = this.leg4.getChild("half4");
		this.leg5 = root.getChild("leg5");
		this.half5 = this.leg5.getChild("half5");
		this.leg6 = root.getChild("leg6");
		this.half6 = this.leg6.getChild("half6");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 12.5F, 3.5F, -0.2689F, -1.2432F, 1.5787F));

		PartDefinition half1 = leg1.addOrReplaceChild("half1", CubeListBuilder.create().texOffs(0, 2).addBox(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 2.0222F, -1.4884F, -2.2289F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 15.5F, 3.5F, 0.4508F, -0.9476F, 0.1017F));

		PartDefinition half2 = leg2.addOrReplaceChild("half2", CubeListBuilder.create().texOffs(0, 2).addBox(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, -0.2182F, -1.3963F, 0.0F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 18.5F, 3.5F, -0.1345F, -0.7418F, -0.213F));

		PartDefinition half3 = leg3.addOrReplaceChild("half3", CubeListBuilder.create().texOffs(0, 2).addBox(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 1.9633F, -1.2393F, -1.9835F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 18.5F, 3.5F, 0.2689F, 0.7001F, 0.4041F));

		PartDefinition half4 = leg4.addOrReplaceChild("half4", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.5236F));

		PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 15.5F, 3.5F, 0.2366F, 0.6329F, 0.2817F));

		PartDefinition half5 = leg5.addOrReplaceChild("half5", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.0F, 0.0F, 2.0811F, 1.3759F, 2.0299F));

		PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 12.5F, 3.5F, 0.6566F, 1.2971F, 0.2585F));

		PartDefinition half6 = leg6.addOrReplaceChild("half6", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 0.0F, 0.0F, 0.375F, 0.8497F, 0.5394F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(42, 0).addBox(-2.5F, -2.98F, -6.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(16, 16).addBox(-3.5F, -6.0F, 0.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(42, 6).addBox(2.0F, -6.96F, -7.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -2.02F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(3.5F, -3.0F, -7.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-4.5F, -3.0F, -7.0F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(19, 19).addBox(-3.5F, -8.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 19).addBox(-3.5F, -8.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 16).addBox(2.5F, -8.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 16).addBox(2.5F, -8.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-1.5F, -10.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-2.5F, -11.0F, 0.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(46, 22).addBox(-2.5F, -11.5F, 2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(46, 6).addBox(-1.5F, -5.5F, 2.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(42, 6).addBox(2.0F, -6.96F, -2.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 6).addBox(-3.0F, -6.96F, -2.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 6).addBox(-3.0F, -6.96F, -7.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}