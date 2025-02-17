	package com.bigdious.risus.client.model.entity;

	// Made with Blockbench 4.11.1
	// Exported for Minecraft version 1.17 or later with Mojang mappings
	// Paste this class into your mod and generate all required imports


	import com.bigdious.risus.entity.BabySpider;
	import com.bigdious.risus.entity.Litter;
	import net.minecraft.client.model.HierarchicalModel;
	import net.minecraft.client.model.geom.ModelPart;
	import net.minecraft.client.model.geom.PartPose;
	import net.minecraft.client.model.geom.builders.*;
	import net.minecraft.resources.ResourceLocation;
	import net.minecraft.util.Mth;
	import net.minecraft.world.level.block.state.BlockState;

	public class LitterModel<T extends Litter> extends HierarchicalModel<T> {
		private final ModelPart root;
		public final ModelPart BlockBody;
		private final ModelPart Base;
		private final ModelPart Legs;
		private final ModelPart LeftLegs;
		private final ModelPart LeftLeg3;
		private final ModelPart PartTwoLeg3;
		private final ModelPart PartThreeFourLeg3;
		private final ModelPart LeftLeg2;
		private final ModelPart PartTwoLeg2;
		private final ModelPart PartThreeFourLeg2;
		private final ModelPart LeftLeg1;
		private final ModelPart PartTwoLeg1;
		private final ModelPart PartThreeFourLeg1;
		private final ModelPart RightLegs;
		private final ModelPart RightLeg4;
		private final ModelPart PartTwoLeg4;
		private final ModelPart PartThreeFourLeg4;
		private final ModelPart RightLeg5;
		private final ModelPart PartTwoLeg5;
		private final ModelPart PartThreeFourLeg5;
		private final ModelPart RightLeg6;
		private final ModelPart PartTwoLeg6;
		private final ModelPart PartThreeFourLeg6;


		public LitterModel(ModelPart root) {
			this.root = root;
			this.BlockBody = root.getChild("bb_main");
			this.Base = root.getChild("Base");
			this.Legs = root.getChild("Legs");
			this.LeftLegs = this.Legs.getChild("LeftLegs");
			this.LeftLeg3 = this.LeftLegs.getChild("LeftLeg3");
			this.PartTwoLeg3 = this.LeftLeg3.getChild("PartTwoLeg3");
			this.PartThreeFourLeg3 = this.PartTwoLeg3.getChild("PartThreeFourLeg3");
			this.LeftLeg2 = this.LeftLegs.getChild("LeftLeg2");
			this.PartTwoLeg2 = this.LeftLeg2.getChild("PartTwoLeg2");
			this.PartThreeFourLeg2 = this.PartTwoLeg2.getChild("PartThreeFourLeg2");
			this.LeftLeg1 = this.LeftLegs.getChild("LeftLeg1");
			this.PartTwoLeg1 = this.LeftLeg1.getChild("PartTwoLeg1");
			this.PartThreeFourLeg1 = this.PartTwoLeg1.getChild("PartThreeFourLeg1");
			this.RightLegs = this.Legs.getChild("RightLegs");
			this.RightLeg4 = this.RightLegs.getChild("RightLeg4");
			this.PartTwoLeg4 = this.RightLeg4.getChild("PartTwoLeg4");
			this.PartThreeFourLeg4 = this.PartTwoLeg4.getChild("PartThreeFourLeg4");
			this.RightLeg5 = this.RightLegs.getChild("RightLeg5");
			this.PartTwoLeg5 = this.RightLeg5.getChild("PartTwoLeg5");
			this.PartThreeFourLeg5 = this.PartTwoLeg5.getChild("PartThreeFourLeg5");
			this.RightLeg6 = this.RightLegs.getChild("RightLeg6");
			this.PartTwoLeg6 = this.RightLeg6.getChild("PartTwoLeg6");
			this.PartThreeFourLeg6 = this.PartTwoLeg6.getChild("PartThreeFourLeg6");
		}

		public static LayerDefinition create() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();

			PartDefinition Base = partdefinition.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(23, 0).addBox(1.5F, -9.0F, -1.5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -2.0F, -3.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 21.0F, 0.5F));

			PartDefinition Legs = partdefinition.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

			PartDefinition LeftLegs = Legs.addOrReplaceChild("LeftLegs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition LeftLeg3 = LeftLegs.addOrReplaceChild("LeftLeg3", CubeListBuilder.create().texOffs(4, 1).addBox(3.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.5F, 0.0F, -0.5236F, 0.0F));

			PartDefinition PartTwoLeg3 = LeftLeg3.addOrReplaceChild("PartTwoLeg3", CubeListBuilder.create(), PartPose.offset(4.75F, 0.0F, 0.0F));

			PartDefinition MiddlePart2_r1 = PartTwoLeg3.addOrReplaceChild("MiddlePart2_r1", CubeListBuilder.create().texOffs(4, 1).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

			PartDefinition PartThreeFourLeg3 = PartTwoLeg3.addOrReplaceChild("PartThreeFourLeg3", CubeListBuilder.create().texOffs(5, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

			PartDefinition LeftLeg2 = LeftLegs.addOrReplaceChild("LeftLeg2", CubeListBuilder.create().texOffs(4, 1).addBox(3.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, 0.0F));

			PartDefinition PartTwoLeg2 = LeftLeg2.addOrReplaceChild("PartTwoLeg2", CubeListBuilder.create(), PartPose.offset(4.75F, 0.0F, 0.0F));

			PartDefinition MiddlePart2_r2 = PartTwoLeg2.addOrReplaceChild("MiddlePart2_r2", CubeListBuilder.create().texOffs(4, 1).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

			PartDefinition PartThreeFourLeg2 = PartTwoLeg2.addOrReplaceChild("PartThreeFourLeg2", CubeListBuilder.create().texOffs(5, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

			PartDefinition LeftLeg1 = LeftLegs.addOrReplaceChild("LeftLeg1", CubeListBuilder.create().texOffs(4, 1).addBox(3.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, -0.5F, 0.0F, 0.5236F, 0.0F));

			PartDefinition PartTwoLeg1 = LeftLeg1.addOrReplaceChild("PartTwoLeg1", CubeListBuilder.create(), PartPose.offset(4.75F, 0.0F, 0.0F));

			PartDefinition MiddlePart1_r1 = PartTwoLeg1.addOrReplaceChild("MiddlePart1_r1", CubeListBuilder.create().texOffs(4, 1).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

			PartDefinition PartThreeFourLeg1 = PartTwoLeg1.addOrReplaceChild("PartThreeFourLeg1", CubeListBuilder.create().texOffs(5, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(0.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

			PartDefinition RightLegs = Legs.addOrReplaceChild("RightLegs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition RightLeg4 = RightLegs.addOrReplaceChild("RightLeg4", CubeListBuilder.create().texOffs(4, 1).mirror().addBox(-5.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.5F, 0.5F, 0.0F, 0.5236F, 0.0F));

			PartDefinition PartTwoLeg4 = RightLeg4.addOrReplaceChild("PartTwoLeg4", CubeListBuilder.create(), PartPose.offset(-4.75F, 0.0F, 0.0F));

			PartDefinition MiddlePart3_r1 = PartTwoLeg4.addOrReplaceChild("MiddlePart3_r1", CubeListBuilder.create().texOffs(4, 1).mirror().addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.25F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

			PartDefinition PartThreeFourLeg4 = PartTwoLeg4.addOrReplaceChild("PartThreeFourLeg4", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-1.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

			PartDefinition RightLeg5 = RightLegs.addOrReplaceChild("RightLeg5", CubeListBuilder.create().texOffs(4, 1).mirror().addBox(-5.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -3.5F, 0.0F));

			PartDefinition PartTwoLeg5 = RightLeg5.addOrReplaceChild("PartTwoLeg5", CubeListBuilder.create(), PartPose.offset(-4.75F, 0.0F, 0.0F));

			PartDefinition MiddlePart3_r2 = PartTwoLeg5.addOrReplaceChild("MiddlePart3_r2", CubeListBuilder.create().texOffs(4, 1).mirror().addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.25F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

			PartDefinition PartThreeFourLeg5 = PartTwoLeg5.addOrReplaceChild("PartThreeFourLeg5", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-1.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

			PartDefinition RightLeg6 = RightLegs.addOrReplaceChild("RightLeg6", CubeListBuilder.create().texOffs(4, 1).mirror().addBox(-5.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.5F, -0.5F, 0.0F, -0.5236F, 0.0F));

			PartDefinition PartTwoLeg6 = RightLeg6.addOrReplaceChild("PartTwoLeg6", CubeListBuilder.create(), PartPose.offset(-4.75F, 0.0F, 0.0F));

			PartDefinition MiddlePart2_r3 = PartTwoLeg6.addOrReplaceChild("MiddlePart2_r3", CubeListBuilder.create().texOffs(4, 1).mirror().addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.25F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

			PartDefinition PartThreeFourLeg6 = PartTwoLeg6.addOrReplaceChild("PartThreeFourLeg6", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-1.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

			PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

			return LayerDefinition.create(meshdefinition, 32, 32);
		}


		@Override
		public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
		}
		@Override
		public ModelPart root() {
			return this.root;
		}

	}
