package com.bigdious.risus.client.render.player;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.player.HandAnimHelper;
import com.bigdious.risus.client.model.entity.player.LeftHandPlayerModel;
import com.bigdious.risus.client.model.entity.player.RightHandPlayerModel;
import com.bigdious.risus.init.RisusItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HandOfGreedLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> implements HandAnimHelper {
	//will be expanded later, when sinner armor is added
	public static final RenderType LEFT_RENDER_TYPE = RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/player/left_hand_of_greed.png"));
	public final LeftHandPlayerModel model;

	public HandOfGreedLayer(RenderLayerParent<T, M> parent) {
		super(parent);
		this.model = new LeftHandPlayerModel(Minecraft.getInstance().getEntityModels().bakeLayer(RisusModelLayers.LEFT_HAND_OF_GREED));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T parent, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = parent.getItemBySlot(EquipmentSlot.OFFHAND);
		if (this.shouldRender(itemstack)) {
			boolean slim = false;
			if (getParentModel() instanceof PlayerModel<?> player) {
				slim = player.slim;
			}
			this.model.setupArmSize(slim);
			this.model.setupAnim(parent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			this.model.prepareMobModel(parent, limbSwing, limbSwingAmount, partialTicks);
//			((ArmedModel) this.getParentModel()).translateToHand(HumanoidArm.LEFT, poseStack);
			HandAnimHelper.followBodyRotations(parent, this.model);
			VertexConsumer vertexConsumer = buffer.getBuffer(LEFT_RENDER_TYPE);
			this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
		}
	}

	public boolean shouldRender(ItemStack stack) {
		return stack.is(RisusItems.HAND_OF_GREED);
	}
}
