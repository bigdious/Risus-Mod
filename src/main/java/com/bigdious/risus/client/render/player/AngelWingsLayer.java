package com.bigdious.risus.client.render.player;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public  class AngelWingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T,M> {
	private static final ResourceLocation WINGS_LOCATION = ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/angel_wings.png");
	private final ElytraModel<T> elytraModel;

	public AngelWingsLayer(RenderLayerParent<T, M> p_174493_, EntityModelSet p_174494_) {
		super(p_174493_);
		this.elytraModel = new ElytraModel<>(p_174494_.bakeLayer(ModelLayers.ELYTRA));
	}

	@Override
	public void render(PoseStack pose, MultiBufferSource p_116952_, int p_116953_, T player, float p_116955_, float p_116956_, float p_116957_, float p_116958_, float p_116959_, float p_116960_) {
		ItemStack itemstack = player.getItemBySlot(EquipmentSlot.CHEST);
		if (this.shouldRender(itemstack)) {
			pose.pushPose();
			pose.translate(0.0F, 0.0F, 0.125F);
			this.getParentModel().copyPropertiesTo(this.elytraModel);
			this.elytraModel.setupAnim(player, p_116955_, p_116956_, p_116958_, p_116959_, p_116960_);
			VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(p_116952_, RenderType.armorCutoutNoCull(WINGS_LOCATION), itemstack.hasFoil());
			this.elytraModel.renderToBuffer(pose, vertexconsumer, p_116953_, OverlayTexture.NO_OVERLAY);
			pose.popPose();
		}

	}

	public boolean shouldRender(ItemStack stack) {
		return stack.is(RisusItems.ANGEL_WINGS);
	}
}
