package com.bigdious.risus.components.tooltip;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.items.armor.SinnerRobeBootsItem;
import com.bigdious.risus.items.armor.UpgradableRisusArmorItem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.CaretListener;
import java.util.Map;

public class ArmorUpgradingTooltipComponent implements ClientTooltipComponent {
	//based off of https://github.com/TeamTwilight/twilightforest/blob/1.21.1/src/main/java/twilightforest/components/item/ItemDisplayContents.java
	private static final ResourceLocation BACKGROUND_SPRITE = ResourceLocation.withDefaultNamespace("container/bundle/background");
	private static final ResourceLocation SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/bundle/slot");
	private static final int SLOT_WIDTH = 18;
	private static final int SLOT_HEIGHT = 20;

	private final ItemStack contents;

	public ArmorUpgradingTooltipComponent(UpgradableRisusArmorItem.Tooltip tooltip) {
		this.contents = tooltip.content().getItem();
	}

	@Override
	public void renderImage(@NotNull Font font, int x, int y, GuiGraphics guiGraphics) {
		Item itemstack = this.contents.getItem();
		ChatFormatting color = ABILITIES.containsKey(itemstack) ? ABILITIES.get(itemstack).getSecond() : ChatFormatting.WHITE;
		String ability = ABILITIES.containsKey(itemstack) ? ABILITIES.get(itemstack).getFirst() : "default";

		guiGraphics.blitSprite(BACKGROUND_SPRITE, x+3, y+12, this.backgroundWidth(), this.backgroundHeight());
		guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability").withStyle(ChatFormatting.GRAY) ,x, y, 115, 11184810);
		guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability." + ability).withStyle(color) ,x+28, y+10, 120, 11184810);
		guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability." + ability + ".desc").withStyle(color) ,x+28, y+19, 120, 11184810);
		int k = 0;
		int renderX = x + 4;
		int renderY = y + 13;
		this.renderSlot(renderX, renderY, k++, guiGraphics, font);

	}

	private void renderSlot(int x, int y, int itemIndex, GuiGraphics graphics, Font font) {
		graphics.blitSprite(SLOT_SPRITE, x, y, 0, SLOT_WIDTH, SLOT_HEIGHT);

		if (itemIndex < 1) {
			ItemStack itemstack = this.contents;
				graphics.renderItem(itemstack, x + 1, y + 1, itemIndex);
				graphics.renderItemDecorations(font, itemstack, x + 1, y + 1);
		}
	}

	private int backgroundWidth() {
		return this.gridSizeX() * SLOT_WIDTH + 2;
	}

	private int backgroundHeight() {
		return this.gridSizeY() * SLOT_HEIGHT;
	}

	private int gridSizeX() {
		return 1;
	}

	private int gridSizeY() {
		return 1;
	}

	@Override
	public int getHeight() {
		return this.backgroundHeight() + 20;
	}

	@Override
	public int getWidth(@NotNull Font font) {
		return this.backgroundWidth() + 150;
	}

	public static final Map<Item, Pair<String, ChatFormatting>> ABILITIES = Map.ofEntries(
		Map.entry(RisusItems.LIGHT_DEVOURER.asItem(), Pair.of("shadow_walker", ChatFormatting.DARK_GRAY)),
		Map.entry(RisusBlocks.BONDKNOT_LOG.asItem(), Pair.of("great_stool", ChatFormatting.DARK_GRAY))
	);
}