package com.bigdious.risus.client.event;

import com.bigdious.risus.components.item.WarhornComponent;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.items.armor.SinnerRobeHelmetItem;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ColorHandler {
	//from Twilight Forest's ColorHandler class
	protected static void registerItemColors(RegisterColorHandlersEvent.Item event) {

		event.register((stack, index) -> {
			if (index > 0) return -1;
			var contents = stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
			if (contents.potion().potion().isEmpty()) return -1;
			return contents.potion().getColor();
		}, RisusItems.WARHORN.get());

		event.register((stack, index) -> {
			if (index > 0) return -1;
			var contents = stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
			if (contents.potion().potion().isEmpty()) return -1;
			return contents.potion().getColor();
		}, RisusItems.HEXHORN.get());

		event.register((stack, index) -> index != 1 ? -1 : DyedItemColor.getOrDefault(stack, SinnerRobeHelmetItem.DEFAULT_COLOR), RisusItems.SINNER_ROBES_HELMET.get());
	}
}
