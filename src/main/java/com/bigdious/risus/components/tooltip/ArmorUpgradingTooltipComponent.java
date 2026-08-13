package com.bigdious.risus.components.tooltip;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
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
import net.minecraft.world.item.Items;
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
		Item item = this.contents.getItem();
		ChatFormatting color = ABILITIES.containsKey(item) ? ABILITIES.get(item).getSecond() : ChatFormatting.WHITE;
		String ability = ABILITIES.containsKey(item) ? ABILITIES.get(item).getFirst() : "default";

		guiGraphics.blitSprite(BACKGROUND_SPRITE, x + 3, y + 12, this.backgroundWidth(), this.backgroundHeight());
		guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability").withStyle(ChatFormatting.GRAY), x, y, 115, 11184810);
		guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability." + ability).withStyle(color), x + 28, y + 10, 120, 11184810);
		if (item == Items.SPYGLASS) {
			guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability.spyglass.button_press", Component.translatable("tooltip.risus.spyglass.button_press.outline", Component.keybind("keybind.spyglass_mode").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.WHITE)).withStyle(color), x + 28, y + 19, 120, 11184810);
		} else if (item == RisusBlocks.BONDKNOT_LOG.asItem()) {
			guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability.great_stool.button_press", Component.translatable("tooltip.risus.great_stool.button_press.outline", Component.keybind("keybind.summon_greatness").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.WHITE)).withStyle(color), x + 28, y + 19, 120, 11184810);
		} else if (item == RisusItems.RESEARCHERS_NOTES.get()) {
			guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability.book.button_press", Component.translatable("tooltip.risus.great_stool.button_press.outline", Component.keybind("keybind.researchers_notes_open").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.WHITE)).withStyle(color), x + 28, y + 19, 120, 11184810);
		} else {
			guiGraphics.drawWordWrap(font, Component.translatable("tooltip.risus.ability." + ability + ".desc").withStyle(color), x + 28, y + 19, 120, 11184810);
		}
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
		return this.backgroundWidth() + 120;
	}

	public static final Map<Item, Pair<String, ChatFormatting>> ABILITIES = Map.ofEntries(
		Map.entry(RisusItems.LIGHT_DEVOURER.asItem(), Pair.of("shadow_walker", ChatFormatting.DARK_GRAY)),
		Map.entry(RisusBlocks.BONDKNOT_LOG.asItem(), Pair.of("great_stool", ChatFormatting.DARK_GRAY)),
		Map.entry(RisusBlocks.MAW_GUTS.asItem(), Pair.of("guts", ChatFormatting.RED)),
		Map.entry(RisusItems.HAND_OF_GREED.asItem(), Pair.of("hand_of_greed", ChatFormatting.DARK_GRAY)),
		Map.entry(RisusItems.RESEARCHERS_NOTES.asItem(), Pair.of("book", ChatFormatting.WHITE)),
		Map.entry(Items.SKELETON_SKULL.asItem(), Pair.of("skeleton", ChatFormatting.WHITE)),
		Map.entry(Items.CREEPER_HEAD.asItem(), Pair.of("skeleton", ChatFormatting.GREEN)),
		Map.entry(Items.WITHER_SKELETON_SKULL.asItem(), Pair.of("wither_skeleton", ChatFormatting.DARK_GRAY)),
		Map.entry(Items.ZOMBIE_HEAD.asItem(), Pair.of("zombie", ChatFormatting.DARK_GREEN)),
		Map.entry(Items.PIGLIN_HEAD.asItem(), Pair.of("piglin", ChatFormatting.RED)),
		Map.entry(Items.LIME_WOOL.asItem(), Pair.of("tuxedo_cat", ChatFormatting.WHITE)),
		Map.entry(Items.BLACK_WOOL.asItem(), Pair.of("black_cat", ChatFormatting.WHITE)),
		Map.entry(Items.LIGHT_GRAY_WOOL.asItem(), Pair.of("british_cat", ChatFormatting.WHITE)),
		Map.entry(Items.YELLOW_WOOL.asItem(), Pair.of("calico_cat", ChatFormatting.WHITE)),
		Map.entry(Items.GRAY_WOOL.asItem(), Pair.of("jellie_cat", ChatFormatting.WHITE)),
		Map.entry(Items.CYAN_WOOL.asItem(), Pair.of("persian_cat", ChatFormatting.WHITE)),
		Map.entry(Items.LIGHT_BLUE_WOOL.asItem(), Pair.of("ragdoll_cat", ChatFormatting.WHITE)),
		Map.entry(Items.ORANGE_WOOL.asItem(), Pair.of("orange_cat", ChatFormatting.WHITE)),
		Map.entry(Items.BLUE_WOOL.asItem(), Pair.of("siamese_cat", ChatFormatting.WHITE)),
		Map.entry(Items.BROWN_WOOL.asItem(), Pair.of("tabby_cat", ChatFormatting.WHITE)),
		Map.entry(Items.WHITE_WOOL.asItem(), Pair.of("white_cat", ChatFormatting.WHITE)),
		Map.entry(Items.GREEN_WOOL.asItem(), Pair.of("audrey_cat", ChatFormatting.WHITE)),
		Map.entry(Items.CARVED_PUMPKIN.asItem(), Pair.of("pumpkin", ChatFormatting.GOLD)),
		Map.entry(Items.TERRACOTTA.asItem(), Pair.of("pale_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.WHITE_TERRACOTTA.asItem(), Pair.of("snowy_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.LIGHT_GRAY_TERRACOTTA.asItem(), Pair.of("ashen_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.BLACK_TERRACOTTA.asItem(), Pair.of("black_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.GRAY_TERRACOTTA.asItem(), Pair.of("chestnut_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.RED_TERRACOTTA.asItem(), Pair.of("rusty_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.ORANGE_TERRACOTTA.asItem(), Pair.of("spotted_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.YELLOW_TERRACOTTA.asItem(), Pair.of("striped_wolf", ChatFormatting.WHITE)),
		Map.entry(Items.BROWN_TERRACOTTA.asItem(), Pair.of("woods_wolf", ChatFormatting.WHITE)),
		Map.entry(RisusBlocks.EYE_BLEACHED.asItem(), Pair.of("bleached_eye", ChatFormatting.WHITE)),
		Map.entry(RisusBlocks.EYE_BLOODSHOT.asItem(), Pair.of("bloodshot_eye", ChatFormatting.DARK_RED)),
		Map.entry(RisusBlocks.EYE_EMERALD.asItem(), Pair.of("emerald_eye", ChatFormatting.DARK_GREEN)),
		Map.entry(RisusBlocks.EYE_ENDER.asItem(), Pair.of("ender_eye", ChatFormatting.DARK_BLUE)),
		Map.entry(RisusBlocks.EYE_GOLDEN.asItem(), Pair.of("golden_eye", ChatFormatting.GOLD)),
		Map.entry(RisusBlocks.ASHEN_REMAINS.asItem(), Pair.of("abyssal_eye", ChatFormatting.DARK_GRAY)),
		Map.entry(RisusBlocks.SMILING_REMAINS.asItem(), Pair.of("smile", ChatFormatting.DARK_RED)),
		Map.entry(Items.SPYGLASS.asItem(), Pair.of("spyglass", ChatFormatting.GOLD)),
		Map.entry(Items.SWEET_BERRIES.asItem(), Pair.of("fox", ChatFormatting.GOLD)),
		Map.entry(Items.RABBIT_FOOT.asItem(), Pair.of("snow_fox", ChatFormatting.WHITE))
	);
}