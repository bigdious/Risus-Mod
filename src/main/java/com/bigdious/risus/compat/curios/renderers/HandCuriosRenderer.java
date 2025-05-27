package com.bigdious.risus.compat.curios.renderers;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.player.HandOfGreedPlayerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class HandCuriosRenderer implements ICurioRenderer {
	private final HandOfGreedPlayerModel model;

	public HandCuriosRenderer(){
		this.model = new HandOfGreedPlayerModel(Minecraft.getInstance().getEntityModels().bakeLayer(RisusModelLayers.HAND_OF_GREED));
	}

	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack item, SlotContext slotContext, PoseStack stack, RenderLayerParent<T, M> parent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		this.model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.model.prepareMobModel(slotContext.entity(), limbSwing, limbSwingAmount, partialTicks);
		ICurioRenderer.followBodyRotations(slotContext.entity(), this.model);
		VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/player/hand_of_greed.png")));
		this.model.renderToBuffer(stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
	}
}
