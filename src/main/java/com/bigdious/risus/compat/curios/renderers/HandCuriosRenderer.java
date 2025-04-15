package com.bigdious.risus.compat.curios.renderers;

import com.bigdious.risus.init.RisusItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class HandCuriosRenderer implements ICurioRenderer {
	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack item, SlotContext slotContext, PoseStack stack, RenderLayerParent<T, M> parent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (parent.getModel() instanceof HumanoidModel<?> model) {
			stack.pushPose();

			if (item.is(RisusItems.HAND_OF_GREED)) {
				stack.translate(0.0D, 0.0D, 0.0D);
				stack.mulPose(Axis.YP.rotationDegrees(model.leftArm.yRot));
				stack.mulPose(Axis.ZP.rotationDegrees(model.leftArm.yRot));
				stack.scale(1F, 1F, 1F);
			}
			ItemInHandRenderer renderer = new ItemInHandRenderer(Minecraft.getInstance(), Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer());
			renderer.renderItem(slotContext.entity(), item, ItemDisplayContext.FIXED, false, stack, buffer, light);
			stack.popPose();
		}
	}
}