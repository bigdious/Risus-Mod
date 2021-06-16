package com.bigdious.risus.client.model;

import com.bigdious.risus.entity.MawEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MawModel<T extends MawEntity> extends EntityModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer bigPincer1;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer bigPincer2;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer bigPincer3;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer cube_r21;
	private final ModelRenderer bigPincer4;
	private final ModelRenderer cube_r22;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer bigPincer5;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer cube_r31;
	private final ModelRenderer bigPincer6;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;
	private final ModelRenderer cube_r35;
	private final ModelRenderer cube_r36;
	private final ModelRenderer bigPincer7;
	private final ModelRenderer cube_r37;
	private final ModelRenderer cube_r38;
	private final ModelRenderer cube_r39;
	private final ModelRenderer cube_r40;
	private final ModelRenderer cube_r41;
	private final ModelRenderer bigPincer8;
	private final ModelRenderer cube_r42;
	private final ModelRenderer cube_r43;
	private final ModelRenderer cube_r44;
	private final ModelRenderer cube_r45;
	private final ModelRenderer cube_r46;

	public MawModel() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(-2.0F, 24.0F, -2.0F);
		body.setTextureOffset(16, 0).addBox(-11.5F, 0.0F, -11.5F, 27.0F, 0.0F, 27.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -11.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, 11.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(8.0F, 3.0F, 0.0F);
		body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
		cube_r1.setTextureOffset(34, 27).addBox(-7.0F, -1.0F, -3.0F, 18.0F, 2.0F, 18.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(12.0F, 1.0F, 0.0F);
		body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
		cube_r2.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -3.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-10.0F, 1.0F, 0.0F);
		body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -1.5708F, 0.0F);
		cube_r3.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -3.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(2.0F, 1.0F, 2.0F);
		body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 2.3562F, 0.0F);
		cube_r4.setTextureOffset(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(2.0F, 1.0F, 2.0F);
		body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, -2.3562F, 0.0F);
		cube_r5.setTextureOffset(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(2.0F, 1.0F, 2.0F);
		body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, -0.7854F, 0.0F);
		cube_r6.setTextureOffset(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(2.0F, 1.0F, 2.0F);
		body.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.7854F, 0.0F);
		cube_r7.setTextureOffset(0, 0).addBox(-5.1213F, -0.99F, 9.0208F, 10.0F, 2.0F, 4.0F, 0.0F, false);

		bigPincer1 = new ModelRenderer(this);
		bigPincer1.setRotationPoint(-12.0F, 24.0F, 0.0F);
		bigPincer1.setTextureOffset(0, 14).addBox(-3.0F, -7.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(1.0F, -3.0F, -1.0F);
		bigPincer1.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 1.3963F);
		cube_r8.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(4.0F, -8.0F, -1.0F);
		bigPincer1.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 1.3963F);
		cube_r9.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(7.0F, -13.0F, -1.0F);
		bigPincer1.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 1.3963F);
		cube_r10.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, -8.0F, 0.0F);
		bigPincer1.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.7418F);
		cube_r11.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		bigPincer2 = new ModelRenderer(this);
		bigPincer2.setRotationPoint(0.0F, 24.0F, -12.0F);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(1.0F, -3.0F, 1.0F);
		bigPincer2.addChild(cube_r12);
		setRotationAngle(cube_r12, -1.5708F, -0.1745F, 1.5708F);
		cube_r12.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(1.0F, -8.0F, 4.0F);
		bigPincer2.addChild(cube_r13);
		setRotationAngle(cube_r13, -1.5708F, -0.1745F, 1.5708F);
		cube_r13.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(1.0F, -13.0F, 7.0F);
		bigPincer2.addChild(cube_r14);
		setRotationAngle(cube_r14, -1.5708F, -0.1745F, 1.5708F);
		cube_r14.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, 1.0F, -1.0F);
		bigPincer2.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, -1.5708F, 0.0F);
		cube_r15.setTextureOffset(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(0.0F, -8.0F, 0.0F);
		bigPincer2.addChild(cube_r16);
		setRotationAngle(cube_r16, -1.5708F, -0.829F, 1.5708F);
		cube_r16.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		bigPincer3 = new ModelRenderer(this);
		bigPincer3.setRotationPoint(12.0F, 24.0F, 0.0F);

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(-1.0F, -3.0F, 1.0F);
		bigPincer3.addChild(cube_r17);
		setRotationAngle(cube_r17, 3.1416F, 0.0F, 1.7453F);
		cube_r17.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setRotationPoint(-4.0F, -8.0F, 1.0F);
		bigPincer3.addChild(cube_r18);
		setRotationAngle(cube_r18, 3.1416F, 0.0F, 1.7453F);
		cube_r18.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setRotationPoint(-7.0F, -13.0F, 1.0F);
		bigPincer3.addChild(cube_r19);
		setRotationAngle(cube_r19, 3.1416F, 0.0F, 1.7453F);
		cube_r19.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setRotationPoint(1.0F, 1.0F, 0.0F);
		bigPincer3.addChild(cube_r20);
		setRotationAngle(cube_r20, -3.1416F, 0.0F, 3.1416F);
		cube_r20.setTextureOffset(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setRotationPoint(0.0F, -8.0F, 0.0F);
		bigPincer3.addChild(cube_r21);
		setRotationAngle(cube_r21, 3.1416F, 0.0F, 2.3998F);
		cube_r21.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		bigPincer4 = new ModelRenderer(this);
		bigPincer4.setRotationPoint(0.0F, 24.0F, 12.0F);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setRotationPoint(-1.0F, -3.0F, -1.0F);
		bigPincer4.addChild(cube_r22);
		setRotationAngle(cube_r22, 1.5708F, 0.1745F, 1.5708F);
		cube_r22.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setRotationPoint(-1.0F, -8.0F, -4.0F);
		bigPincer4.addChild(cube_r23);
		setRotationAngle(cube_r23, 1.5708F, 0.1745F, 1.5708F);
		cube_r23.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setRotationPoint(-1.0F, -13.0F, -7.0F);
		bigPincer4.addChild(cube_r24);
		setRotationAngle(cube_r24, 1.5708F, 0.1745F, 1.5708F);
		cube_r24.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setRotationPoint(0.0F, 1.0F, 1.0F);
		bigPincer4.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, 1.5708F, 0.0F);
		cube_r25.setTextureOffset(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setRotationPoint(0.0F, -8.0F, 0.0F);
		bigPincer4.addChild(cube_r26);
		setRotationAngle(cube_r26, 1.5708F, 0.829F, 1.5708F);
		cube_r26.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		bigPincer5 = new ModelRenderer(this);
		bigPincer5.setRotationPoint(9.0F, 24.0F, 9.0F);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(-1.9289F, -3.0F, -0.5147F);
		bigPincer5.addChild(cube_r27);
		setRotationAngle(cube_r27, 2.3638F, 0.1231F, 1.6948F);
		cube_r27.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(-4.0503F, -8.0F, -2.636F);
		bigPincer5.addChild(cube_r28);
		setRotationAngle(cube_r28, 2.3638F, 0.1231F, 1.6948F);
		cube_r28.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setRotationPoint(-6.1716F, -13.0F, -4.7574F);
		bigPincer5.addChild(cube_r29);
		setRotationAngle(cube_r29, 2.3638F, 0.1231F, 1.6948F);
		cube_r29.setTextureOffset(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setRotationPoint(0.1924F, 1.0F, 0.1924F);
		bigPincer5.addChild(cube_r30);
		setRotationAngle(cube_r30, -3.1416F, 0.7854F, 3.1416F);
		cube_r30.setTextureOffset(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setRotationPoint(-0.5147F, -8.0F, -0.5147F);
		bigPincer5.addChild(cube_r31);
		setRotationAngle(cube_r31, 2.5474F, 0.5484F, 2.228F);
		cube_r31.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		bigPincer6 = new ModelRenderer(this);
		bigPincer6.setRotationPoint(-9.0F, 24.0F, 9.0F);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setRotationPoint(0.5147F, -3.0F, -1.9289F);
		bigPincer6.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.7777F, 0.1231F, 1.4468F);
		cube_r32.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setRotationPoint(2.636F, -8.0F, -4.0503F);
		bigPincer6.addChild(cube_r33);
		setRotationAngle(cube_r33, 0.7777F, 0.1231F, 1.4468F);
		cube_r33.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setRotationPoint(4.7574F, -13.0F, -6.1716F);
		bigPincer6.addChild(cube_r34);
		setRotationAngle(cube_r34, 0.7777F, 0.1231F, 1.4468F);
		cube_r34.setTextureOffset(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r35 = new ModelRenderer(this);
		cube_r35.setRotationPoint(-0.1924F, 1.0F, 0.1924F);
		bigPincer6.addChild(cube_r35);
		setRotationAngle(cube_r35, 0.0F, 0.7854F, 0.0F);
		cube_r35.setTextureOffset(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r36 = new ModelRenderer(this);
		cube_r36.setRotationPoint(0.5147F, -8.0F, -0.5147F);
		bigPincer6.addChild(cube_r36);
		setRotationAngle(cube_r36, 0.5942F, 0.5484F, 0.9136F);
		cube_r36.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		bigPincer7 = new ModelRenderer(this);
		bigPincer7.setRotationPoint(-9.0F, 24.0F, -9.0F);

		cube_r37 = new ModelRenderer(this);
		cube_r37.setRotationPoint(1.9289F, -3.0F, 0.5147F);
		bigPincer7.addChild(cube_r37);
		setRotationAngle(cube_r37, -0.7777F, -0.1231F, 1.4468F);
		cube_r37.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r38 = new ModelRenderer(this);
		cube_r38.setRotationPoint(4.0503F, -8.0F, 2.636F);
		bigPincer7.addChild(cube_r38);
		setRotationAngle(cube_r38, -0.7777F, -0.1231F, 1.4468F);
		cube_r38.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r39 = new ModelRenderer(this);
		cube_r39.setRotationPoint(6.1716F, -13.0F, 4.7574F);
		bigPincer7.addChild(cube_r39);
		setRotationAngle(cube_r39, -0.7777F, -0.1231F, 1.4468F);
		cube_r39.setTextureOffset(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r40 = new ModelRenderer(this);
		cube_r40.setRotationPoint(-0.1924F, 1.0F, -0.1924F);
		bigPincer7.addChild(cube_r40);
		setRotationAngle(cube_r40, 0.0F, -0.7854F, 0.0F);
		cube_r40.setTextureOffset(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r41 = new ModelRenderer(this);
		cube_r41.setRotationPoint(0.5147F, -8.0F, 0.5147F);
		bigPincer7.addChild(cube_r41);
		setRotationAngle(cube_r41, -0.5942F, -0.5484F, 0.9136F);
		cube_r41.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		bigPincer8 = new ModelRenderer(this);
		bigPincer8.setRotationPoint(9.0F, 24.0F, -9.0711F);

		cube_r42 = new ModelRenderer(this);
		cube_r42.setRotationPoint(-0.5147F, -3.0F, 2.0F);
		bigPincer8.addChild(cube_r42);
		setRotationAngle(cube_r42, -2.3638F, -0.1231F, 1.6948F);
		cube_r42.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r43 = new ModelRenderer(this);
		cube_r43.setRotationPoint(-2.636F, -8.0F, 4.1213F);
		bigPincer8.addChild(cube_r43);
		setRotationAngle(cube_r43, -2.3638F, -0.1231F, 1.6948F);
		cube_r43.setTextureOffset(0, 43).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r44 = new ModelRenderer(this);
		cube_r44.setRotationPoint(-4.7574F, -13.0F, 6.2426F);
		bigPincer8.addChild(cube_r44);
		setRotationAngle(cube_r44, -2.3638F, -0.1231F, 1.6948F);
		cube_r44.setTextureOffset(0, 43).addBox(-0.2608F, -2.4336F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		cube_r45 = new ModelRenderer(this);
		cube_r45.setRotationPoint(0.1924F, 1.0F, -0.1213F);
		bigPincer8.addChild(cube_r45);
		setRotationAngle(cube_r45, -3.1416F, -0.7854F, 3.1416F);
		cube_r45.setTextureOffset(0, 14).addBox(-2.0F, -8.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		cube_r46 = new ModelRenderer(this);
		cube_r46.setRotationPoint(-0.5147F, -8.0F, 0.5858F);
		bigPincer8.addChild(cube_r46);
		setRotationAngle(cube_r46, -2.5474F, -0.5484F, 2.228F);
		cube_r46.setTextureOffset(0, 29).addBox(-1.0F, -7.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer1.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer2.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer3.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer4.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer5.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer6.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer7.render(matrixStack, buffer, packedLight, packedOverlay);
		bigPincer8.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}