package com.bigdious.risus.client;

import com.bigdious.risus.inventory.MawGutsMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MawGutsScreen extends AbstractContainerScreen<MawGutsMenu> {

	private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.withDefaultNamespace("textures/gui/container/generic_54.png");

	public MawGutsScreen(MawGutsMenu menu, Inventory inventory, Component component) {
		super(menu, inventory, component);
		this.imageHeight = 114 + 6 * 18;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	public void render(GuiGraphics graphics, int x, int y, float partialTicks) {
		this.renderBackground(graphics, x, y, partialTicks);
		super.render(graphics, x, y, partialTicks);
		this.renderTooltip(graphics, x, y);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTicks, int x, int y) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		graphics.blit(CONTAINER_BACKGROUND, i, j, 0, 0, this.imageWidth, 6 * 18 + 17);
		graphics.blit(CONTAINER_BACKGROUND, i, j + 6 * 18 + 17, 0, 126, this.imageWidth, 96);
	}
}
