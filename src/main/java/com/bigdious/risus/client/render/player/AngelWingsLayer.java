package com.bigdious.risus.client.render.player;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.player.AngelWingsModel;
import com.bigdious.risus.client.model.entity.player.ThreadWingsModel;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
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
public class AngelWingsLayer <T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
	private static final RenderType ANGEL_WINGS_RENDER = RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/player/angel_wings.png"));
	private static final RenderType ASHEN_WINGS_RENDER = RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/player/ashen_wings.png"));
	private final AngelWingsModel model;

	public AngelWingsLayer(RenderLayerParent<T, M> parent) {
		super(parent);
		this.model = new AngelWingsModel(Minecraft.getInstance().getEntityModels().bakeLayer(RisusModelLayers.ANGEL_WINGS));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T parent, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = parent.getItemBySlot(EquipmentSlot.CHEST);
		if (shouldRender(itemstack)){
			VertexConsumer vertexConsumer = buffer.getBuffer(itemstack.getHoverName().getString().equalsIgnoreCase("ashen wings") ? ASHEN_WINGS_RENDER : ANGEL_WINGS_RENDER);
			this.model.setupAnim(parent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			this.model.prepareMobModel(parent, limbSwing, limbSwingAmount, ageInTicks);
			this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
		}
	}
	public boolean shouldRender(ItemStack stack) {
		return stack.is(RisusItems.ANGEL_WINGS);
	}
}
