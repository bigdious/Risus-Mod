package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.RisusBoat;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class RisusBoatRenderer extends EntityRenderer<RisusBoat> {
	private final Map<RisusBoat.Type, Pair<ResourceLocation, BoatModel>> boatResources;

	public RisusBoatRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.shadowRadius = 0.8F;
		this.boatResources = Stream.of(RisusBoat.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> type, (type) -> Pair.of(Risus.prefix(getTextureLocation(type)), this.createBoatModel(context, type))));
	}

	private static ModelLayerLocation createLocation(String path, String model) {
		return new ModelLayerLocation(Risus.prefix(path), model);
	}

	public static ModelLayerLocation createBoatModelName(RisusBoat.Type pType) {
		return createLocation("boat/" + pType.getName(), "main");
	}

	public static ModelLayerLocation createChestBoatModelName(RisusBoat.Type type) {
		return createLocation("chest_boat/" + type.getName(), "main");
	}

	private BoatModel createBoatModel(EntityRendererProvider.Context context, RisusBoat.Type type) {
		ModelLayerLocation modellayerlocation = createBoatModelName(type);
		return new BoatModel(context.bakeLayer(modellayerlocation));
	}

	private static String getTextureLocation(RisusBoat.Type type) {
		return "textures/entity/boat/" + type.getName() + ".png";
	}

	@Override
	public void render(RisusBoat entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		poseStack.translate(0.0D, 0.375D, 0.0D);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
		float f = (float)entity.getHurtTime() - partialTicks;
		float f1 = entity.getDamage() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)entity.getHurtDir()));
		}

		float bubbleAngle = entity.getBubbleAngle(partialTicks) * ((float)Math.PI / 360F);
		if (!Mth.equal(bubbleAngle, 0.0F)) {
			float sin = Mth.sin(bubbleAngle);
			poseStack.mulPose(new Quaternionf(sin, 0.0F, sin, Mth.cos(bubbleAngle)));
		}

		Pair<ResourceLocation, BoatModel> boatResources = this.boatResources.get(entity.getRisusBoatType());
		ResourceLocation texture = boatResources.getFirst();
		BoatModel boat = boatResources.getSecond();
		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
		boat.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexconsumer = bufferSource.getBuffer(boat.renderType(texture));
		boat.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		if (!entity.isUnderWater()) {
			VertexConsumer vertexconsumer1 = bufferSource.getBuffer(RenderType.waterMask());
			boat.waterPatch().render(poseStack, vertexconsumer1, packedLight, OverlayTexture.NO_OVERLAY);
		}

		poseStack.popPose();
		super.render(entity, yaw, partialTicks, poseStack, bufferSource, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(RisusBoat entity) {
		return this.boatResources.get(entity.getRisusBoatType()).getFirst();
	}
}
