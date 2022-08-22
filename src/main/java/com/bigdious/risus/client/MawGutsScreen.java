package com.bigdious.risus.client;

import com.bigdious.risus.inventory.MawGutsMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MawGutsScreen extends AbstractContainerScreen<MawGutsMenu> {

	private static final ResourceLocation CONTAINER_BACKGROUND = new ResourceLocation("textures/gui/container/generic_54.png");

	public MawGutsScreen(MawGutsMenu menu, Inventory inventory, Component component) {
		super(menu, inventory, component);
		this.passEvents = false;
		this.imageHeight = 114 + 6 * 18;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	public void render(PoseStack stack, int x, int y, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, x, y, partialTicks);
		this.renderTooltip(stack, x, y);
	}

	@Override
	protected void renderBg(PoseStack stack, float partialTicks, int x, int y) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, CONTAINER_BACKGROUND);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(stack, i, j, 0, 0, this.imageWidth, 6 * 18 + 17);
		this.blit(stack, i, j + 6 * 18 + 17, 0, 126, this.imageWidth, 96);
	}
}
