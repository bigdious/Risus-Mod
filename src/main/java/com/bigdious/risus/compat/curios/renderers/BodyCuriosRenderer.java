package com.bigdious.risus.compat.curios.renderers;

import com.bigdious.risus.init.RisusItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class BodyCuriosRenderer implements ICurioRenderer {

	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack item, SlotContext slotContext, PoseStack stack, RenderLayerParent<T, M> parent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (parent.getModel() instanceof HumanoidModel<?> model) {
			stack.pushPose();
			model.body.translateAndRotate(stack);
			if (item.is(RisusItems.TOTEM_OF_UNYIELDING)) {
				stack.translate(0.50D, -0.25D, 0.15D);
				stack.scale(-0.4F, -0.4F, 0.4F);
			}
			if (item.is(RisusItems.LUCKY_CHARM)) {
				stack.translate(-0.16D, 0.7D, -0.15D);
				stack.scale(-0.4F, -0.4F, 0.4F);
			}
			if (item.is(RisusItems.WRETCHED_CHARM)) {
				stack.translate(0.16D, 0.7D, -0.15D);
				stack.rotateAround(Axis.YP.rotation(3.15F), 0, 0, 0);
				stack.scale(-0.4F, -0.4F, 0.4F);
			}
			if (item.is(RisusItems.COUNTERWEIGHT)) {
				stack.translate(0.01D, 0.3D, -0.15D);
				stack.rotateAround(Axis.YP.rotation(3.15F), 0, 0, 0);
				stack.scale(-0.3F, -0.3F, 0.3F);
			}
			ItemInHandRenderer renderer = new ItemInHandRenderer(Minecraft.getInstance(), Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().getItemRenderer());
			renderer.renderItem(slotContext.entity(), item, ItemDisplayContext.FIXED, false, stack, buffer, light);
			stack.popPose();
		}
	}
}
