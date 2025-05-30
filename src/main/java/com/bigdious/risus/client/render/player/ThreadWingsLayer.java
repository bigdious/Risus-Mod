package com.bigdious.risus.client.render.player;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.player.LeftHandPlayerModel;
import com.bigdious.risus.client.model.entity.player.ThreadWingsModel;
import com.bigdious.risus.client.render.AnimationRenderHelper;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThreadWingsLayer <T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

	private static final RenderType THREAD_WINGS_RENDER = RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/entity/player/thread_wings.png"));
	private final ThreadWingsModel model;

	public ThreadWingsLayer(RenderLayerParent<T, M> parent) {
		super(parent);
		this.model = new ThreadWingsModel(Minecraft.getInstance().getEntityModels().bakeLayer(RisusModelLayers.THREAD_WINGS));
	}
	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T parent, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstackAvoid = parent.getItemBySlot(EquipmentSlot.CHEST);
		ItemStack itemstackNeed = parent.getItemBySlot(EquipmentSlot.FEET);
		if (shouldRender(itemstackNeed, itemstackAvoid)){
			VertexConsumer vertexConsumer = buffer.getBuffer(THREAD_WINGS_RENDER);
			this.model.setupAnim(parent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			this.model.prepareMobModel(parent, limbSwing, limbSwingAmount, ageInTicks);
			this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
		}
	}

	public boolean shouldRender(ItemStack stackNeeded, ItemStack stackAvoided) {
		return stackNeeded.is(RisusItems.THREADERS_OF_THE_FIRMAMENT) && !stackAvoided.is(RisusTags.Items.STOPS_THREAD_WINGS_RENDERING);
	}
}
