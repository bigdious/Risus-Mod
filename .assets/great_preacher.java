// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class great_preacher<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "great_preacher"), "main");
	private final ModelPart body;
	private final ModelPart LeftWing;
	private final ModelPart LeftWingMiddle;
	private final ModelPart LeftWingBottom;
	private final ModelPart RightWing;
	private final ModelPart RightWingMiddle;
	private final ModelPart RightWingBottom;
	private final ModelPart Head;
	private final ModelPart nose;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart RightArm;
	private final ModelPart RightGrowthMiddle;
	private final ModelPart RightGrowthBottom;
	private final ModelPart RightPalm;
	private final ModelPart RightThumb;
	private final ModelPart RightThumbBottom;
	private final ModelPart RightPointer;
	private final ModelPart RightPointerBottom;
	private final ModelPart RightMiddle;
	private final ModelPart RightMiddleBottom2;
	private final ModelPart RightRing;
	private final ModelPart RightRingBottom3;
	private final ModelPart LeftArm;
	private final ModelPart LeftGrowthMiddle;
	private final ModelPart LeftGrowthBottom;
	private final ModelPart LeftPalm;
	private final ModelPart LeftThumb;
	private final ModelPart LeftThumbBottom;
	private final ModelPart LeftPointer;
	private final ModelPart LeftPointerBottom;
	private final ModelPart LeftMiddle;
	private final ModelPart LeftMiddleBottom2;
	private final ModelPart LeftRing;
	private final ModelPart LeftRingBottom3;
	private final ModelPart Circle;
	private final ModelPart Smile;

	public great_preacher(ModelPart root) {
		this.body = root.getChild("body");
		this.LeftWing = this.body.getChild("LeftWing");
		this.LeftWingMiddle = this.LeftWing.getChild("LeftWingMiddle");
		this.LeftWingBottom = this.LeftWingMiddle.getChild("LeftWingBottom");
		this.RightWing = this.body.getChild("RightWing");
		this.RightWingMiddle = this.RightWing.getChild("RightWingMiddle");
		this.RightWingBottom = this.RightWingMiddle.getChild("RightWingBottom");
		this.Head = this.body.getChild("Head");
		this.nose = this.Head.getChild("nose");
		this.leg0 = this.body.getChild("leg0");
		this.leg1 = this.body.getChild("leg1");
		this.RightArm = this.body.getChild("RightArm");
		this.RightGrowthMiddle = this.RightArm.getChild("RightGrowthMiddle");
		this.RightGrowthBottom = this.RightGrowthMiddle.getChild("RightGrowthBottom");
		this.RightPalm = this.RightGrowthBottom.getChild("RightPalm");
		this.RightThumb = this.RightPalm.getChild("RightThumb");
		this.RightThumbBottom = this.RightThumb.getChild("RightThumbBottom");
		this.RightPointer = this.RightPalm.getChild("RightPointer");
		this.RightPointerBottom = this.RightPointer.getChild("RightPointerBottom");
		this.RightMiddle = this.RightPalm.getChild("RightMiddle");
		this.RightMiddleBottom2 = this.RightMiddle.getChild("RightMiddleBottom2");
		this.RightRing = this.RightPalm.getChild("RightRing");
		this.RightRingBottom3 = this.RightRing.getChild("RightRingBottom3");
		this.LeftArm = this.body.getChild("LeftArm");
		this.LeftGrowthMiddle = this.LeftArm.getChild("LeftGrowthMiddle");
		this.LeftGrowthBottom = this.LeftGrowthMiddle.getChild("LeftGrowthBottom");
		this.LeftPalm = this.LeftGrowthBottom.getChild("LeftPalm");
		this.LeftThumb = this.LeftPalm.getChild("LeftThumb");
		this.LeftThumbBottom = this.LeftThumb.getChild("LeftThumbBottom");
		this.LeftPointer = this.LeftPalm.getChild("LeftPointer");
		this.LeftPointerBottom = this.LeftPointer.getChild("LeftPointerBottom");
		this.LeftMiddle = this.LeftPalm.getChild("LeftMiddle");
		this.LeftMiddleBottom2 = this.LeftMiddle.getChild("LeftMiddleBottom2");
		this.LeftRing = this.LeftPalm.getChild("LeftRing");
		this.LeftRingBottom3 = this.LeftRing.getChild("LeftRingBottom3");
		this.Circle = this.body.getChild("Circle");
		this.Smile = this.body.getChild("Smile");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftWing = body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(20, 24).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 40.0F, new CubeDeformation(0.0F))
		.texOffs(138, 52).addBox(0.0F, 3.0F, -1.0F, 0.0F, 39.0F, 40.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 3.0F, 4.0F, 0.7728F, 0.8393F, 0.1028F));

		PartDefinition LeftWingMiddle = LeftWing.addOrReplaceChild("LeftWingMiddle", CubeListBuilder.create().texOffs(24, 70).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 52.0F, new CubeDeformation(0.0F))
		.texOffs(128, 67).addBox(0.0F, -1.0F, -6.0F, 0.0F, 56.0F, 64.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 39.0F, -0.489F, 0.5652F, -0.2432F));

		PartDefinition LeftWingBottom = LeftWingMiddle.addOrReplaceChild("LeftWingBottom", CubeListBuilder.create().texOffs(34, 127).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 60.0F, new CubeDeformation(0.0F))
		.texOffs(0, 72).addBox(0.0F, -1.0F, 0.0F, 0.0F, 56.0F, 128.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 52.0F, -0.7393F, 0.7425F, -0.3133F));

		PartDefinition RightWing = body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 40.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(138, 52).mirror().addBox(0.0F, 3.0F, -1.0F, 0.0F, 39.0F, 40.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 3.0F, 4.0F, 0.7728F, -0.8393F, -0.1028F));

		PartDefinition RightWingMiddle = RightWing.addOrReplaceChild("RightWingMiddle", CubeListBuilder.create().texOffs(24, 70).mirror().addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 52.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(128, 67).mirror().addBox(0.0F, -1.0F, -6.0F, 0.0F, 56.0F, 64.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 39.0F, -0.489F, -0.5652F, 0.2432F));

		PartDefinition RightWingBottom = RightWingMiddle.addOrReplaceChild("RightWingBottom", CubeListBuilder.create().texOffs(34, 127).mirror().addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 60.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 72).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 56.0F, 128.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 52.0F, -0.7393F, -0.7425F, 0.3133F));

		PartDefinition Head = body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition nose = Head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition leg0 = body.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition leg1 = body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition RightArm = body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -0.3491F, 0.0F, 0.5672F));

		PartDefinition RightGrowthMiddle = RightArm.addOrReplaceChild("RightGrowthMiddle", CubeListBuilder.create().texOffs(0, 93).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 30.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.082F, 9.0531F, -0.0531F, 0.48F, 0.0F, 0.0F));

		PartDefinition RightGrowthBottom = RightGrowthMiddle.addOrReplaceChild("RightGrowthBottom", CubeListBuilder.create().texOffs(0, 129).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 36.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 30.0F, 0.0F, -0.7418F, 0.0F, -0.1745F));

		PartDefinition RightPalm = RightGrowthBottom.addOrReplaceChild("RightPalm", CubeListBuilder.create().texOffs(0, 172).mirror().addBox(-2.5F, 0.0F, -6.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 36.0F, 0.0F, 0.109F, -0.3323F, -0.3237F));

		PartDefinition RightThumb = RightPalm.addOrReplaceChild("RightThumb", CubeListBuilder.create().texOffs(24, 70).mirror().addBox(-2.0F, -2.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.0F, -6.0F, 1.3095F, -0.3535F, -0.913F));

		PartDefinition RightThumbBottom = RightThumb.addOrReplaceChild("RightThumbBottom", CubeListBuilder.create().texOffs(40, 86).mirror().addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -12.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition RightPointer = RightPalm.addOrReplaceChild("RightPointer", CubeListBuilder.create().texOffs(24, 86).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, -4.0F, -0.202F, -0.0829F, -0.3843F));

		PartDefinition RightPointerBottom = RightPointer.addOrReplaceChild("RightPointerBottom", CubeListBuilder.create().texOffs(24, 102).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition RightMiddle = RightPalm.addOrReplaceChild("RightMiddle", CubeListBuilder.create().texOffs(24, 86).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RightMiddleBottom2 = RightMiddle.addOrReplaceChild("RightMiddleBottom2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition RightMiddleBottom_r1 = RightMiddleBottom2.addOrReplaceChild("RightMiddleBottom_r1", CubeListBuilder.create().texOffs(24, 102).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition RightRing = RightPalm.addOrReplaceChild("RightRing", CubeListBuilder.create().texOffs(24, 86).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 12.0F, 4.0F, 0.1942F, 0.1001F, -0.4702F));

		PartDefinition RightRingBottom3 = RightRing.addOrReplaceChild("RightRingBottom3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition RightRingBottom3_r1 = RightRingBottom3.addOrReplaceChild("RightRingBottom3_r1", CubeListBuilder.create().texOffs(24, 102).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition LeftArm = body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -0.3491F, 0.0F, -0.5672F));

		PartDefinition LeftGrowthMiddle = LeftArm.addOrReplaceChild("LeftGrowthMiddle", CubeListBuilder.create().texOffs(0, 93).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 30.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.082F, 9.0531F, -0.0531F, 0.48F, 0.0F, 0.0F));

		PartDefinition LeftGrowthBottom = LeftGrowthMiddle.addOrReplaceChild("LeftGrowthBottom", CubeListBuilder.create().texOffs(0, 129).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 36.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 30.0F, 0.0F, -0.7418F, 0.0F, 0.1745F));

		PartDefinition LeftPalm = LeftGrowthBottom.addOrReplaceChild("LeftPalm", CubeListBuilder.create().texOffs(0, 172).addBox(-2.5F, 0.0F, -6.0F, 5.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 36.0F, 0.0F, 0.109F, 0.3323F, 0.3237F));

		PartDefinition LeftThumb = LeftPalm.addOrReplaceChild("LeftThumb", CubeListBuilder.create().texOffs(24, 70).addBox(-2.0F, -2.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -6.0F, 1.3095F, 0.3535F, 0.913F));

		PartDefinition LeftThumbBottom = LeftThumb.addOrReplaceChild("LeftThumbBottom", CubeListBuilder.create().texOffs(40, 86).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -12.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition LeftPointer = LeftPalm.addOrReplaceChild("LeftPointer", CubeListBuilder.create().texOffs(24, 86).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -4.0F, -0.202F, 0.0829F, 0.3843F));

		PartDefinition LeftPointerBottom = LeftPointer.addOrReplaceChild("LeftPointerBottom", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition LeftPointerBottom_r1 = LeftPointerBottom.addOrReplaceChild("LeftPointerBottom_r1", CubeListBuilder.create().texOffs(24, 102).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition LeftMiddle = LeftPalm.addOrReplaceChild("LeftMiddle", CubeListBuilder.create().texOffs(24, 86).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition LeftMiddleBottom2 = LeftMiddle.addOrReplaceChild("LeftMiddleBottom2", CubeListBuilder.create().texOffs(24, 102).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition LeftRing = LeftPalm.addOrReplaceChild("LeftRing", CubeListBuilder.create().texOffs(24, 86).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 4.0F, 0.1942F, -0.1001F, 0.4702F));

		PartDefinition LeftRingBottom3 = LeftRing.addOrReplaceChild("LeftRingBottom3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition LeftRingBottom3_r1 = LeftRingBottom3.addOrReplaceChild("LeftRingBottom3_r1", CubeListBuilder.create().texOffs(24, 102).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition Circle = body.addOrReplaceChild("Circle", CubeListBuilder.create().texOffs(72, 0).addBox(-30.0F, -30.0F, 0.0F, 60.0F, 60.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 5.0F));

		PartDefinition Smile = body.addOrReplaceChild("Smile", CubeListBuilder.create().texOffs(192, 0).addBox(-10.0F, 7.3625F, -5.1066F, 20.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(192, 8).addBox(-10.0F, -4.6375F, -5.1066F, 20.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(192, 32).addBox(-10.5F, -1.1375F, -3.1065F, 21.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -43.3625F, 3.1065F, 0.2182F, 0.0F, 0.0F));

		PartDefinition TeethLeft_r1 = Smile.addOrReplaceChild("TeethLeft_r1", CubeListBuilder.create().texOffs(192, 41).mirror().addBox(-15.0F, -2.5F, 0.0F, 15.0F, 9.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-10.0F, 1.3625F, -3.1065F, 0.0718F, 0.3864F, 0.1886F));

		PartDefinition TeethRight_r1 = Smile.addOrReplaceChild("TeethRight_r1", CubeListBuilder.create().texOffs(192, 41).addBox(0.0F, -2.5F, 0.0F, 15.0F, 9.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(10.0F, 1.3625F, -3.1065F, 0.0718F, -0.3864F, -0.1886F));

		PartDefinition LipsRightTop_r1 = Smile.addOrReplaceChild("LipsRightTop_r1", CubeListBuilder.create().texOffs(192, 24).mirror().addBox(0.0F, -2.0F, -2.0F, 26.0F, 4.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(10.0F, -2.6375F, -3.1065F, 0.0718F, -0.3864F, -0.1886F));

		PartDefinition LipsRightBottom_r1 = Smile.addOrReplaceChild("LipsRightBottom_r1", CubeListBuilder.create().texOffs(192, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 28.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.0F, 9.3625F, -3.1065F, 0.2333F, -0.3189F, -0.6485F));

		PartDefinition LipsLeftTop_r1 = Smile.addOrReplaceChild("LipsLeftTop_r1", CubeListBuilder.create().texOffs(192, 24).addBox(-26.0F, -2.0F, -2.0F, 26.0F, 4.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-10.0F, -2.6375F, -3.1065F, 0.0718F, 0.3864F, 0.1886F));

		PartDefinition LipsLeftBottom_r1 = Smile.addOrReplaceChild("LipsLeftBottom_r1", CubeListBuilder.create().texOffs(192, 16).addBox(-27.0F, -2.0F, -2.0F, 28.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 9.3625F, -3.1065F, 0.2333F, 0.3189F, 0.6485F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}