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

        public Memory1Model(ModelPart root) {
            this.root = root;
            this.man = root.getChild("man");
            this.woman = root.getChild("woman");
        }

        public static LayerDefinition create() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition man = partdefinition.addOrReplaceChild("man", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

             man.addOrReplaceChild("manHead_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.0F, -1.0F, 0.0F, 0.0F, 0.4363F));

             man.addOrReplaceChild("manUpperTorso_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, -3.5F, -6.0F, 6.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2315F, -16.3104F, 0.0F, 0.0F, 0.0F, 0.1745F));

             man.addOrReplaceChild("manBottomTorso_r1", CubeListBuilder.create().texOffs(-6, -6).addBox(-2.5F, -5.5F, -4.0F, 5.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -8.5F, 0.0F, 0.0F, 0.0F, -0.2182F));

             man.addOrReplaceChild("manTighRight_r1", CubeListBuilder.create().texOffs(-2, -2).addBox(-8.0F, -6.0F, -5.5F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0503F, 0.1209F, 0.3957F));

             man.addOrReplaceChild("manShinRight_r1", CubeListBuilder.create().texOffs(-1, -1).addBox(-8.0F, -2.0F, -5.0F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

             man.addOrReplaceChild("manBotArmRight_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.3807F, -1.0799F, -0.8427F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.695F, -11.6117F, -8.7118F, -0.0909F, 0.1128F, 0.8674F));

             man.addOrReplaceChild("manUpArmRight_r1", CubeListBuilder.create().texOffs(-1, -1).addBox(-3.5F, 2.0F, -3.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1111F, -16.3456F, -6.4098F, 0.1967F, 0.4118F, 1.0169F));

             man.addOrReplaceChild("manUpArmLeft_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.3807F, -1.0799F, -1.1573F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.695F, -11.6117F, 8.7118F, 0.1192F, -0.2155F, 0.5902F));

            man.addOrReplaceChild("manUpArmLeft_r2", CubeListBuilder.create().texOffs(-1, -1).addBox(-3.5F, 2.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1111F, -16.3456F, 6.4098F, -0.1967F, -0.4118F, 1.0169F));

             man.addOrReplaceChild("manTighLeft_r1", CubeListBuilder.create().texOffs(-2, -2).addBox(-8.0F, -6.0F, 1.5F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0503F, -0.1209F, 0.3957F));

             man.addOrReplaceChild("manShinLeft_r1", CubeListBuilder.create().texOffs(-1, -1).addBox(-8.0F, -2.0F, 2.0F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

            PartDefinition woman = partdefinition.addOrReplaceChild("woman", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 16.0F, 12.0F, 1.6602F, -0.2173F, -1.5901F));

             woman.addOrReplaceChild("womanHead_r1", CubeListBuilder.create().texOffs(0, 80).addBox(-2.0F, -5.0F, -3.02F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -22.0F, -1.0F, 0.0F, 0.0F, 0.4363F));

             woman.addOrReplaceChild("womanUpperTorso_r1", CubeListBuilder.create().texOffs(0, 114).addBox(-1.5F, -2.5F, -4.01F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2315F, -16.3104F, 0.0F, 0.0F, 0.0F, 0.1745F));

             woman.addOrReplaceChild("womanBottomTorso_r1", CubeListBuilder.create().texOffs(63, 84).addBox(-1.5F, -5.5F, -4.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -8.5F, 0.0F, 0.0F, 0.0F, -0.2182F));

             woman.addOrReplaceChild("womanTighRight_r1", CubeListBuilder.create().texOffs(32, 122).addBox(-3.5F, 0.5F, -1.0F, 11.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2601F, -5.0837F, -3.3643F, 0.1106F, 0.0702F, 1.0075F));

             woman.addOrReplaceChild("womanShinRight_r1", CubeListBuilder.create().texOffs(96, 89).addBox(-6.5F, -2.0F, -1.5F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.763F, 7.0218F, -2.8893F, -0.0706F, 0.1103F, -1.0947F));

             woman.addOrReplaceChild("womanBotArmRight_r1", CubeListBuilder.create().texOffs(101, 123).addBox(-4.0F, 9.0F, 3.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8243F, -10.6901F, -11.7305F, -0.022F, 0.143F, 1.3946F));

             woman.addOrReplaceChild("womanUpArmRight_r1", CubeListBuilder.create().texOffs(106, 112).addBox(-0.4386F, -1.8501F, 1.0422F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.6827F, -18.8747F, -5.7217F, 0.4216F, 0.4547F, 2.1803F));

             woman.addOrReplaceChild("womanUpArmLeft_r1", CubeListBuilder.create().texOffs(15, 117).addBox(1.6193F, -1.0799F, -1.1573F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.695F, -11.6117F, 8.7118F, 0.5961F, 1.3623F, 1.2018F));

                woman.addOrReplaceChild("womanUpArmLeft_r2", CubeListBuilder.create().texOffs(104, 96).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.731F, -14.9881F, 5.6725F, -0.3133F, -0.3341F, 1.3344F));

             woman.addOrReplaceChild("womanTighLeft_r1", CubeListBuilder.create().texOffs(50, 108).addBox(-8.0F, -6.0F, 1.5F, 11.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0503F, -0.1209F, 0.3957F));

             woman.addOrReplaceChild("womanShinLeft_r1", CubeListBuilder.create().texOffs(48, 99).addBox(-13.9487F, -1.0F, 2.7832F, 13.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -3.0F, 0.0F, 0.0F, -0.1309F, -0.5236F));

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

