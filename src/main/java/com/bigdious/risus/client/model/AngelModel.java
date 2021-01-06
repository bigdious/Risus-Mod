package com.bigdious.risus.client.model;

import com.bigdious.risus.entity.AngelEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class AngelModel<T extends AngelEntity> extends EntityModel<T> {

    private final ModelRenderer innerRing;
    private final ModelRenderer middleRing;
    private final ModelRenderer outerRing;
    private final ModelRenderer headCluster1;
    private final ModelRenderer headCluster2;
    private final ModelRenderer topRightWing;
    private final ModelRenderer topLeftWing;
    private final ModelRenderer bottomRightWing;
    private final ModelRenderer bottomLeftWing;

    public AngelModel() {
        textureWidth = 74;
        textureHeight = 74;


        innerRing = new ModelRenderer(this);
        innerRing.setRotationPoint(0.0F, 3.0F, 0.0F);
        setRotationAngle(innerRing, -0.48F, 0.0F, 0.5672F);
        innerRing.setTextureOffset(0, 65).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
        innerRing.setTextureOffset(0, 58).addBox(-5.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, false);
        innerRing.setTextureOffset(0, 56).addBox(4.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, false);
        innerRing.setTextureOffset(0, 65).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);

        middleRing = new ModelRenderer(this);
        middleRing.setRotationPoint(0.0F, 3.0F, 0.0F);
        setRotationAngle(middleRing, 0.0F, 3.0543F, 1.3526F);
        middleRing.setTextureOffset(0, 60).addBox(-5.0F, 5.0F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
        middleRing.setTextureOffset(0, 60).addBox(-5.0F, -6.0F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
        middleRing.setTextureOffset(0, 56).addBox(-6.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);
        middleRing.setTextureOffset(0, 55).addBox(5.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, 0.0F, false);

        outerRing = new ModelRenderer(this);
        outerRing.setRotationPoint(0.0F, 3.0F, 0.0F);
        setRotationAngle(outerRing, -1.0908F, -2.3998F, 0.0F);
        outerRing.setTextureOffset(0, 55).addBox(-1.0F, 6.0F, -6.0F, 2.0F, 1.0F, 12.0F, 0.0F, false);
        outerRing.setTextureOffset(0, 55).addBox(-1.0F, -7.0F, -6.0F, 2.0F, 1.0F, 12.0F, 0.0F, false);
        outerRing.setTextureOffset(0, 55).addBox(-1.0F, -6.0F, 6.0F, 2.0F, 12.0F, 1.0F, 0.0F, false);
        outerRing.setTextureOffset(0, 55).addBox(-1.0F, -6.0F, -7.0F, 2.0F, 12.0F, 1.0F, 0.0F, false);

        headCluster1 = new ModelRenderer(this);
        headCluster1.setRotationPoint(0.0F, 3.0F, 0.0F);
        headCluster1.setTextureOffset(28, 58).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        headCluster2 = new ModelRenderer(this);
        headCluster2.setRotationPoint(0.0F, 3.0F, 0.0F);
        headCluster2.setTextureOffset(48, 62).addBox(-4.0F, 3.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        headCluster2.setTextureOffset(48, 62).addBox(2.0F, -2.0F, 2.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        headCluster2.setTextureOffset(48, 62).addBox(1.0F, -4.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        headCluster2.setTextureOffset(48, 62).addBox(-4.0F, -1.0F, -1.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        topRightWing = new ModelRenderer(this);
        topRightWing.setRotationPoint(0.0F, -2.0F, 0.0F);
        setRotationAngle(topRightWing, 1.4835F, -0.2182F, -0.5236F);
        topRightWing.setTextureOffset(0, 4).addBox(4.8203F, 1.5607F, -11.1438F, 31.0F, 0.0F, 13.0F, 0.0F, false);

        topLeftWing = new ModelRenderer(this);
        topLeftWing.setRotationPoint(0.0F, -2.0F, 0.0F);
        setRotationAngle(topLeftWing, 1.4835F, 0.2182F, 0.5236F);
        topLeftWing.setTextureOffset(0, 19).addBox(-36.0556F, 1.5607F, -11.1438F, 31.0F, 0.0F, 13.0F, 0.0F, false);

        bottomRightWing = new ModelRenderer(this);
        bottomRightWing.setRotationPoint(0.0F, 6.0F, 0.0F);
        setRotationAngle(bottomRightWing, 1.7017F, -0.0873F, 0.3927F);
        bottomRightWing.setTextureOffset(8, 34).addBox(5.7384F, 1.7759F, -4.5928F, 18.0F, 0.0F, 8.0F, 0.0F, false);

        bottomLeftWing = new ModelRenderer(this);
        bottomLeftWing.setRotationPoint(0.0F, 6.0F, 0.0F);
        setRotationAngle(bottomLeftWing, 1.7017F, 0.0873F, -0.3927F);
        bottomLeftWing.setTextureOffset(8, 42).addBox(-23.7384F, 1.7759F, -4.5928F, 18.0F, 0.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.headCluster1.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.headCluster1.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.headCluster2.rotateAngleX = headPitch * ((float)Math.PI / 270F);
        this.headCluster2.rotateAngleY = netHeadYaw * ((float)Math.PI / 270F);
        this.headCluster2.rotateAngleZ = ageInTicks * 0.01F;
        this.topLeftWing.rotateAngleX = ((float)Math.PI / 2F);
        this.topRightWing.rotateAngleX = ((float)Math.PI / 2F);
        this.bottomRightWing.rotateAngleX = ((float)Math.PI / 2F);
        this.bottomLeftWing.rotateAngleX = ((float)Math.PI / 2F);
        float circle = ageInTicks * 0.05F;
        float flapping = MathHelper.sin(ageInTicks * 0.1F) * 0.1F;
        this.innerRing.rotateAngleX = circle*0.5F;
        this.middleRing.rotateAngleY = circle;
        this.outerRing.rotateAngleZ = circle*1.5F;
        this.topRightWing.rotateAngleY = flapping;
        this.topLeftWing.rotateAngleY = -flapping;
        this.bottomRightWing.rotateAngleY = flapping;
        this.bottomLeftWing.rotateAngleY = -flapping;


    }
    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        innerRing.render(matrixStack, buffer, packedLight, packedOverlay);
        middleRing.render(matrixStack, buffer, packedLight, packedOverlay);
        outerRing.render(matrixStack, buffer, packedLight, packedOverlay);
        headCluster1.render(matrixStack, buffer, packedLight, packedOverlay);
        headCluster2.render(matrixStack, buffer, packedLight, packedOverlay);
        topRightWing.render(matrixStack, buffer, packedLight, packedOverlay);
        topLeftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        bottomRightWing.render(matrixStack, buffer, packedLight, packedOverlay);
        bottomLeftWing.render(matrixStack, buffer, packedLight, packedOverlay);

    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
