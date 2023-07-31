package com.bigdious.risus.client.model.entity;

import com.bigdious.risus.entity.QuestionMark;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class QuestionMarkModel<T extends QuestionMark> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart rin1;
    private final ModelPart rin2;
    private final ModelPart rin3;
    private final ModelPart rin4;
    private final ModelPart rin5;
    private final ModelPart rin6;
    private final ModelPart rin7;
    private final ModelPart rin8;
    private final ModelPart rin9;
    private final ModelPart rin10;
    private final ModelPart rin11;
    private final ModelPart rin12;
    private final ModelPart rin13;

    public QuestionMarkModel(ModelPart root) {
        this.root = root;
        this.rin1 = root.getChild("rin1");
        this.rin2 = root.getChild("rin2");
        this.rin3 = root.getChild("rin3");
        this.rin4 = root.getChild("rin4");
        this.rin5 = root.getChild("rin5");
        this.rin6 = root.getChild("rin6");
        this.rin7 = root.getChild("rin7");
        this.rin8 = root.getChild("rin8");
        this.rin9 = root.getChild("rin9");
        this.rin10 = root.getChild("rin10");
        this.rin11 = root.getChild("rin11");
        this.rin12 = root.getChild("rin12");
        this.rin13 = root.getChild("rin13");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition rin1 = partdefinition.addOrReplaceChild("rin1", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        rin1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -1.8326F));
        rin1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin1.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin1.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin1.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin2 = partdefinition.addOrReplaceChild("rin2", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

       rin2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin2.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin2.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin2.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin2.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin2.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin2.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin2.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin2.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin2.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin2.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin2.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin2.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin3 = partdefinition.addOrReplaceChild("rin3", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

       rin3.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin3.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin3.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin3.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin3.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin3.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin3.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin3.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin3.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin3.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin3.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin3.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin3.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin3.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin3.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin3.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin3.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin3.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin3.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin3.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin3.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin3.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin3.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin4 = partdefinition.addOrReplaceChild("rin4", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

       rin4.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin4.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin4.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin4.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin4.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin4.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin4.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin4.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin4.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin4.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin4.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin4.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin4.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin4.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin4.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin4.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin4.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin4.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin4.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin4.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin4.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin4.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin4.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin5 = partdefinition.addOrReplaceChild("rin5", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

       rin5.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin5.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin5.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin5.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin5.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin5.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin5.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin5.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin5.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin5.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin5.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin5.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin5.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin5.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin5.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin5.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin5.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin5.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin5.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin5.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin5.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin5.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin5.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin6 = partdefinition.addOrReplaceChild("rin6", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 1.309F, 0.0F));

       rin6.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(29, 30).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.8326F));

       rin6.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(25, 26).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -2.0944F));

       rin6.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin6.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin6.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin6.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin6.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin6.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(29, 34).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));

       rin6.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(29, 38).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.309F));

       rin6.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(29, 42).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.0472F));

       rin6.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin6.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin6.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin6.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin6.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin6.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin6.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin6.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin6.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin6.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin6.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin6.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin6.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin7 = partdefinition.addOrReplaceChild("rin7", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

       rin7.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(7, 21).addBox(-2.0F, -0.1177F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(14.4889F, -3.8823F, 0.0F, 0.0F, 1.5708F, -1.8326F));

       rin7.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(7, 17).addBox(-2.0F, 14.9F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -2.0944F));

       rin7.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(7, 13).addBox(-2.0F, 14.85F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -2.3562F));

       rin7.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin7.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin7.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin7.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin7.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(7, 25).addBox(-2.0F, 14.9F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));

       rin7.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(7, 29).addBox(-2.0F, 14.9F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.309F));

       rin7.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(7, 33).addBox(-2.0F, 14.9F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.0472F));

       rin7.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin7.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin7.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(7, 37).addBox(-2.0F, 14.85F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -0.7854F));

       rin7.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin7.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin7.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin7.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin7.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin7.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin7.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin7.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin7.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin7.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin8 = partdefinition.addOrReplaceChild("rin8", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 1.8326F, 0.0F));

       rin8.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(37, 30).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.8326F));

       rin8.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(33, 26).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -2.0944F));

       rin8.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin8.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin8.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin8.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin8.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin8.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(37, 34).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));

       rin8.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(37, 38).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.309F));

       rin8.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(37, 42).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.0472F));

       rin8.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin8.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin8.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin8.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin8.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin8.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin8.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin8.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin8.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin8.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin8.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin8.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin8.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin9 = partdefinition.addOrReplaceChild("rin9", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

       rin9.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin9.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin9.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin9.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin9.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin9.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin9.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin9.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin9.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin9.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin9.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin9.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin9.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin9.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin9.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin9.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin9.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin9.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin9.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin9.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin9.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin9.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin9.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin10 = partdefinition.addOrReplaceChild("rin10", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 2.3562F, 0.0F));

       rin10.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin10.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin10.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin10.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin10.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin10.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin10.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin10.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin10.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin10.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin10.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin10.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin10.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin10.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin10.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin10.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin10.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin10.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin10.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin10.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin10.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin10.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin10.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin11 = partdefinition.addOrReplaceChild("rin11", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 2.618F, 0.0F));

       rin11.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin11.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin11.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin11.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin11.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin11.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin11.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin11.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin11.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin11.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin11.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin11.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin11.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin11.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin11.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin11.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin11.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin11.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin11.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin11.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin11.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin11.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin11.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin12 = partdefinition.addOrReplaceChild("rin12", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 2.8798F, 0.0F));

       rin12.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin12.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin12.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin12.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin12.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin12.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin12.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin12.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin12.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin12.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin12.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin12.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin12.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin12.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin12.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin12.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin12.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin12.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin12.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin12.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin12.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin12.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin12.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rin13 = partdefinition.addOrReplaceChild("rin13", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, 3.098F, 0.0F));

       rin13.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.8326F));

       rin13.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

       rin13.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

       rin13.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.8798F));

       rin13.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.618F));

       rin13.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.8798F));

       rin13.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

       rin13.addOrReplaceChild("cube_r284", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

       rin13.addOrReplaceChild("cube_r285", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

       rin13.addOrReplaceChild("cube_r286", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

       rin13.addOrReplaceChild("cube_r287", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

       rin13.addOrReplaceChild("cube_r288", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

       rin13.addOrReplaceChild("cube_r289", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

       rin13.addOrReplaceChild("cube_r290", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.618F));

       rin13.addOrReplaceChild("cube_r291", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

       rin13.addOrReplaceChild("cube_r292", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

       rin13.addOrReplaceChild("cube_r293", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.8326F));

       rin13.addOrReplaceChild("cube_r294", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

       rin13.addOrReplaceChild("cube_r295", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

       rin13.addOrReplaceChild("cube_r296", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

       rin13.addOrReplaceChild("cube_r297", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

       rin13.addOrReplaceChild("cube_r298", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

       rin13.addOrReplaceChild("cube_r299", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.0F, 15.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        return LayerDefinition.create(meshdefinition, 80, 80);
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
