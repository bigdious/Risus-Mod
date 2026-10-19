package com.bigdious.risus.client.render;

import com.bigdious.risus.entity.QuestionMark;
import com.bigdious.risus.entity.RaidCoreEntity;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.client.renderer.entity.LivingEntityRenderer.getOverlayCoords;

public abstract class MultiLayerBasicEntityRenderer<T extends Entity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
	// partial copy of LivingEntityRenderer
	protected M model;
	protected final List<RenderLayer<T, M>> layers = Lists.newArrayList();

	protected MultiLayerBasicEntityRenderer(EntityRendererProvider.Context context, M model) {
		super(context);
		this.model = model;
	}

	public final boolean addLayer(RenderLayer<T, M> layer) {
		return this.layers.add(layer);
	}

	@Override
	public M getModel() {
		return this.model;
	}

	public void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();

		this.model.setupAnim(entity, 0, 0, 0, 0, 0);

		poseStack.mulPose(Axis.XP.rotationDegrees(180));
		if (entity instanceof RaidCoreEntity) {
			poseStack.scale(1.5F,1.5F,1.5F);
			poseStack.translate(0.0F, -1.701F, 0.0F);
		}
		if (entity instanceof QuestionMark) {
			poseStack.scale(2,2,2);
			poseStack.translate(0.0F, -1.5F, 0.0F);
		}
		float f6 = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());

		Minecraft minecraft = Minecraft.getInstance();
		boolean flag = this.isBodyVisible(entity);
		boolean flag1 = !flag && !entity.isInvisibleTo(minecraft.player);
		RenderType rendertype = this.getRenderType(entity, flag, flag1, false);
		if (rendertype != null) {
			VertexConsumer vertexconsumer = buffer.getBuffer(rendertype);
			int i = getOverlayCoords(entity, this.getWhiteOverlayProgress(entity, partialTicks));
			this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, i, flag1 ? 654311423 : -1);
		}
		for(RenderLayer<T, M> renderlayer : this.layers) {
			renderlayer.render(poseStack, buffer, packedLight, entity, 0, 0, partialTicks, 0, 0, 0);
		}
		poseStack.popPose();

		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	protected boolean isBodyVisible(T livingEntity) {
		return !livingEntity.isInvisible();
	}

	protected float getWhiteOverlayProgress(T livingEntity, float partialTicks) {
		return 0.0F;
	}

	public static int getOverlayCoords(Entity entity, float u) {
		return OverlayTexture.pack(OverlayTexture.u(u), OverlayTexture.v(false));
	}

	@Nullable
	protected RenderType getRenderType(T livingEntity, boolean bodyVisible, boolean translucent, boolean glowing) {
		ResourceLocation resourcelocation = this.getTextureLocation(livingEntity);
		if (translucent) {
			return RenderType.itemEntityTranslucentCull(resourcelocation);
		} else if (bodyVisible) {
			return this.model.renderType(resourcelocation);
		} else {
			return glowing ? RenderType.outline(resourcelocation) : null;
		}
	}
}
