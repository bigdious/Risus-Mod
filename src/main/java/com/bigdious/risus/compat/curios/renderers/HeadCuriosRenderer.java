package com.bigdious.risus.compat.curios.renderers;

import com.bigdious.risus.init.RisusItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class HeadCuriosRenderer implements ICurioRenderer {
//from https://github.com/TeamTwilight/twilightforest/blob/1.21.1/src/main/java/twilightforest/compat/curios/renderer/CurioHeadRenderer.java

	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (renderLayerParent.getModel() instanceof HeadedModel headModel) {
			matrixStack.pushPose();
			if (stack.is(RisusItems.BLOODWYRM_HEAD)) {
				headModel.getHead().translateAndRotate(matrixStack);
				matrixStack.translate(0.0D, -0.60D, 0.0D);
				matrixStack.scale(1.20F, -1.20F, -1.20F);
			}
			ItemInHandRenderer renderer = new ItemInHandRenderer(Minecraft.getInstance(), Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer());
			renderer.renderItem(slotContext.entity(), stack, ItemDisplayContext.HEAD, false, matrixStack, renderTypeBuffer, light);
			matrixStack.popPose();
		}
	}
}