package com.bigdious.risus.client.render;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.model.entity.HolderModel;
import com.bigdious.risus.entity.Holder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class HolderRenderer extends MobRenderer<Holder, HolderModel<Holder>> {

	protected static final ResourceLocation TEXTURE = Risus.prefix("textures/entity/holder.png");

	public HolderRenderer(EntityRendererProvider.Context context) {
		super(context, new HolderModel<>(context.bakeLayer(RisusModelLayers.HOLDER)), 0.0F);
		this.addLayer(new HolderItemLayer(this, context.getItemInHandRenderer()));
	}

	@Override
	protected void scale(Holder holder, PoseStack stack, float partialTicks) {
		stack.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	public ResourceLocation getTextureLocation(Holder holder) {
		return TEXTURE;
	}

	public static class HolderItemLayer extends ItemInHandLayer<Holder, HolderModel<Holder>> {

		private final ItemInHandRenderer itemInHandRenderer;

		public HolderItemLayer(RenderLayerParent<Holder, HolderModel<Holder>> parent, ItemInHandRenderer renderer) {
			super(parent, renderer);
			this.itemInHandRenderer = renderer;
		}

		@Override
		public void render(PoseStack stack, MultiBufferSource source, int light, Holder holder, float p_117208_, float p_117209_, float p_117210_, float p_117211_, float p_117212_, float p_117213_) {
			boolean flag = holder.getMainArm() == HumanoidArm.RIGHT;
			ItemStack itemstack = flag ? holder.getOffhandItem() : holder.getMainHandItem();
			ItemStack itemstack1 = flag ? holder.getMainHandItem() : holder.getOffhandItem();
			if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
				stack.pushPose();
				if (this.getParentModel().young) {
					float f = 0.5F;
					stack.translate(0.0D, 0.75D, 0.0D);
					stack.scale(0.5F, 0.5F, 0.5F);
				}

				this.renderArmWithItem(holder, itemstack1, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, stack, source, light);
				this.renderArmWithItem(holder, itemstack, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, stack, source, light);
				stack.popPose();
			}
		}

		protected void renderArmWithItem(LivingEntity entity, ItemStack item, ItemTransforms.TransformType type, HumanoidArm arm, PoseStack stack, MultiBufferSource source, int light) {
			if (!item.isEmpty()) {
				stack.pushPose();
				this.getParentModel().translateToHand(arm, stack);
				stack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
				stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
				stack.translate(-0.29D, -0.25D, 0.25D);
				boolean flag = arm == HumanoidArm.LEFT;
				stack.translate((float)(flag ? -1 : 1) / 16.0F, 0.125D, -0.625D);
				this.itemInHandRenderer.renderItem(entity, item, type, flag, stack, source, light);
				stack.popPose();
			}
		}
	}
}
