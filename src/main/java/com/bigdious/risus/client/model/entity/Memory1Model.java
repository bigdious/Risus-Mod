package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.Memory1;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class Memory1Model<T extends Memory1> extends HierarchicalModel<T> {
        private final ModelPart root;
        private final ModelPart man;
        private final ModelPart woman;

        private final ModelPart chain1;
        private final ModelPart chain2;
        private final ModelPart chain3;
        private final ModelPart chain4;
        private final ModelPart chain5;
        private final ModelPart chain6;
        private final ModelPart chain7;
        private final ModelPart chain8;
        private final ModelPart chain9;
        private final ModelPart chain10;
        private final ModelPart chain11;
        private final ModelPart chain12;
        private final ModelPart chain13;
        private final ModelPart chain14;

        public Memory1Model(ModelPart root) {
            this.root = root;
            this.man = root.getChild("man");
            this.woman = root.getChild("woman");
            this.chain1 = root.getChild("chain1");
            this.chain2 = root.getChild("chain2");
            this.chain3 = root.getChild("chain3");
            this.chain4 = root.getChild("chain4");
            this.chain5 = root.getChild("chain5");
            this.chain6 = root.getChild("chain6");
            this.chain7 = root.getChild("chain7");
            this.chain8 = root.getChild("chain8");
            this.chain9 = root.getChild("chain9");
            this.chain10 = root.getChild("chain10");
            this.chain11 = root.getChild("chain11");
            this.chain12 = root.getChild("chain12");
            this.chain13 = root.getChild("chain13");
            this.chain14 = root.getChild("chain14");
        }

        public static LayerDefinition create() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition man = partdefinition.addOrReplaceChild("man", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

            man.addOrReplaceChild("manHead_r1", CubeListBuilder.create().texOffs(27, 7).addBox(-2.0F, -6.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.0F, -1.0F, 0.0F, 0.0F, 0.4363F));

            man.addOrReplaceChild("manUpperTorso_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, -3.5F, -6.0F, 6.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2315F, -16.3104F, 0.0F, 0.0F, 0.0F, 0.1745F));

            man.addOrReplaceChild("manBottomTorso_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.5F, -4.0F, 5.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -8.5F, 0.0F, 0.0F, 0.0F, -0.2182F));

            man.addOrReplaceChild("manTighRight_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -6.0F, -5.5F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0503F, 0.1209F, 0.3957F));

            man.addOrReplaceChild("manShinRight_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -2.0F, -5.0F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

            man.addOrReplaceChild("manBotArmRight_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.3807F, -1.0799F, -0.8427F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.695F, -11.6117F, -8.7118F, -0.0909F, 0.1128F, 0.8674F));

            man.addOrReplaceChild("manUpArmRight_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 2.0F, -3.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1111F, -16.3456F, -6.4098F, 0.1967F, 0.4118F, 1.0169F));

            man.addOrReplaceChild("manUpArmLeft_r1", CubeListBuilder.create().texOffs(106, 0).addBox(-0.3807F, -1.0799F, -1.1573F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.695F, -11.6117F, 8.7118F, 0.1192F, -0.2155F, 0.5902F));

            man.addOrReplaceChild("manUpArmLeft_r2", CubeListBuilder.create().texOffs(106, 0).addBox(-3.5F, 2.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1111F, -16.3456F, 6.4098F, -0.1967F, -0.4118F, 1.0169F));

            man.addOrReplaceChild("manTighLeft_r1", CubeListBuilder.create().texOffs(94, 0).addBox(-8.0F, -6.0F, 1.5F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0503F, -0.1209F, 0.3957F));

            man.addOrReplaceChild("manShinLeft_r1", CubeListBuilder.create().texOffs(96, 0).addBox(-8.0F, -2.0F, 2.0F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

            PartDefinition woman = partdefinition.addOrReplaceChild("woman", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 16.0F, 12.0F, 1.6602F, -0.2173F, -1.5901F));

            woman.addOrReplaceChild("womanHead_r1", CubeListBuilder.create().texOffs(0, 93).addBox(-2.0F, -5.0F, -3.02F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.0F, -1.0F, 0.0F, 0.0F, 0.4363F));

            woman.addOrReplaceChild("womanUpperTorso_r1", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, -2.5F, -4.01F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2315F, -16.3104F, 0.0F, 0.0F, 0.0F, 0.1745F));

            woman.addOrReplaceChild("womanBottomTorso_r1", CubeListBuilder.create().texOffs(72, 97).addBox(-1.5F, -5.5F, -4.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -8.5F, 0.0F, 0.0F, 0.0F, -0.2182F));

            woman.addOrReplaceChild("womanTighRight_r1", CubeListBuilder.create().texOffs(32, 122).addBox(-3.5F, 0.5F, -1.0F, 11.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2601F, -5.0837F, -3.3643F, 0.1106F, 0.0702F, 1.0075F));

            woman.addOrReplaceChild("womanShinRight_r1", CubeListBuilder.create().texOffs(96, 123).addBox(-6.5F, -2.0F, -1.5F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.763F, 7.0218F, -2.8893F, -0.0706F, 0.1103F, -1.0947F));

            woman.addOrReplaceChild("womanBotArmRight_r1", CubeListBuilder.create().texOffs(101, 123).addBox(-4.0F, 9.0F, 3.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8243F, -10.6901F, -11.7305F, -0.022F, 0.143F, 1.3946F));

            woman.addOrReplaceChild("womanUpArmRight_r1", CubeListBuilder.create().texOffs(106, 112).addBox(-0.4386F, -1.8501F, 1.0422F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.6827F, -18.8747F, -5.7217F, 0.4216F, 0.4547F, 2.1803F));

            woman.addOrReplaceChild("womanUpArmLeft_r1", CubeListBuilder.create().texOffs(15, 117).addBox(1.6193F, -1.0799F, -1.1573F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.695F, -11.6117F, 8.7118F, 0.5961F, 1.3623F, 1.2018F));

            woman.addOrReplaceChild("womanUpArmLeft_r2", CubeListBuilder.create().texOffs(104, 96).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.731F, -14.9881F, 5.6725F, -0.3133F, -0.3341F, 1.3344F));

            woman.addOrReplaceChild("womanTighLeft_r1", CubeListBuilder.create().texOffs(50, 108).addBox(-8.0F, -6.0F, 1.5F, 11.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0503F, -0.1209F, 0.3957F));

            woman.addOrReplaceChild("womanShinLeft_r1", CubeListBuilder.create().texOffs(96, 104).addBox(-13.9487F, -1.0F, 2.7832F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -3.0F, 0.0F, 0.0F, -0.1309F, -0.5236F));

            PartDefinition chain1 = partdefinition.addOrReplaceChild("chain1", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 2.5F, 0.5F, 0.0F, 0.0F, -0.3491F));

            PartDefinition chain7 = partdefinition.addOrReplaceChild("chain7", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1987F, 9.4941F, -5.7752F, 1.8044F, -1.2589F, -0.295F));

            PartDefinition chain8 = partdefinition.addOrReplaceChild("chain8", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5953F, 0.3216F, -3.4897F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5953F, -1.6784F, -3.4897F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5953F, -1.6784F, -4.4897F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5953F, -1.6784F, -0.4897F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9827F, 10.4787F, -5.5621F, 1.7229F, 0.2734F, 0.2214F));

            PartDefinition chain6 = partdefinition.addOrReplaceChild("chain6", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.511F, 6.6148F, -6.056F, 1.8242F, 0.3447F, -0.2111F));

            PartDefinition chain5 = partdefinition.addOrReplaceChild("chain5", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7811F, 4.2851F, -6.4964F, 1.4255F, -1.0499F, -0.9711F));

            PartDefinition chain4 = partdefinition.addOrReplaceChild("chain4", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.1349F, 3.2109F, -6.1884F, 0.975F, 0.2926F, -1.4149F));

            PartDefinition chain3 = partdefinition.addOrReplaceChild("chain3", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.3633F, 2.5F, -4.5765F, 0.4927F, -0.9149F, -0.5954F));

            PartDefinition chain2 = partdefinition.addOrReplaceChild("chain2", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -3.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -3.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 2.5F, -0.5F, -0.1685F, -0.0019F, -1.8361F));

            PartDefinition chain9 = partdefinition.addOrReplaceChild("chain9", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, 0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, 3.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 2.5F, 1.5F, 0.1685F, 0.0019F, -1.8361F));

            PartDefinition chain10 = partdefinition.addOrReplaceChild("chain10", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.8493F, 2.7106F, 5.8934F, -0.3295F, 0.7268F, -0.3008F));

            PartDefinition chain12 = partdefinition.addOrReplaceChild("chain12", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0012F, 5.9025F, 7.7235F, -1.5642F, 0.7691F, -0.8625F));

            PartDefinition chain11 = partdefinition.addOrReplaceChild("chain11", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0339F, 4.2805F, 7.562F, -0.943F, -0.5841F, -1.2094F));

            PartDefinition chain14 = partdefinition.addOrReplaceChild("chain14", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8452F, 9.9965F, 8.8006F, -1.0522F, 0.832F, -0.0269F));

            PartDefinition chain13 = partdefinition.addOrReplaceChild("chain13", CubeListBuilder.create().texOffs(48, 78).addBox(-0.5F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(49, 77).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 79).addBox(-0.5F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 79).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0949F, 7.5425F, 7.8609F, -1.2711F, -0.5838F, -0.8702F));

            return LayerDefinition.create(meshdefinition, 128, 128);
        }

        @Override
        public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        }

    @Override
    public ModelPart root() {
        return this.root;
    } 
        
    }

