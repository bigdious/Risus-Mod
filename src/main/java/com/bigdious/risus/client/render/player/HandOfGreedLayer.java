package com.bigdious.risus.client.render.player;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.event.RisusClientEvents;
import com.bigdious.risus.client.model.entity.player.LeftHandPlayerModel;
import com.bigdious.risus.client.model.entity.player.RightHandPlayerModel;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
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
import net.neoforged.fml.ModList;

@OnlyIn(Dist.CLIENT)
public class HandOfGreedLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> implements HandAnimHelper {
	//will be expanded later, when sinner armor is added
	public static final RenderType LEFT_RENDER_TYPE = RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/player/left_hand_of_greed.png"));
	public final LeftHandPlayerModel model;
	public final RightHandPlayerModel model2;

	public HandOfGreedLayer(RenderLayerParent<T, M> parent) {
		super(parent);
		this.model = new LeftHandPlayerModel(Minecraft.getInstance().getEntityModels().bakeLayer(RisusModelLayers.LEFT_HAND_OF_GREED));
		this.model2 = new RightHandPlayerModel(Minecraft.getInstance().getEntityModels().bakeLayer(RisusModelLayers.RIGHT_HAND_OF_GREED));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T parent, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = parent.getItemBySlot(EquipmentSlot.OFFHAND);
		ItemStack itemstack2 = parent.getItemBySlot(EquipmentSlot.CHEST);
		if (this.shouldRender(itemstack) || (itemstack2.get(RisusDataComponents.ABILITY_VARIANT) != null && itemstack2.get(RisusDataComponents.ABILITY_VARIANT).equals("hand_of_greed"))) {
			boolean slim = false;
			if (getParentModel() instanceof PlayerModel<?> player) {
				slim = player.slim;
			}
			this.model.setupArmSize(slim);
			this.model.setupAnim(parent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			this.model.prepareMobModel(parent, limbSwing, limbSwingAmount, partialTicks);
			HandAnimHelper.followBodyRotations(parent, this.model);
			if (slim) {poseStack.translate(-0.05,0,0);}
			VertexConsumer vertexConsumer = buffer.getBuffer(LEFT_RENDER_TYPE);
			this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
			if (itemstack2.is(RisusItems.SINNER_ROBES_CHESTPLATE) && (!ModList.get().isLoaded("curios") || !RisusClientEvents.curiosForArm(parent))) {
				this.model2.setupArmSize(slim);
				this.model2.setupAnim(parent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				this.model2.prepareMobModel(parent, limbSwing, limbSwingAmount, partialTicks);
				HandAnimHelper.followBodyRotations(parent, this.model2);
				if (slim) {poseStack.translate(0.05, 0, 0);}
				this.model2.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
			}
		}
	}

	public boolean shouldRender(ItemStack stack) {
		return stack.is(RisusItems.HAND_OF_GREED);
	}
}
