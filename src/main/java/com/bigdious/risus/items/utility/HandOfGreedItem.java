package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class HandOfGreedItem extends Item {

	public HandOfGreedItem(Item.Properties properties) {
		super(properties);
	}

	public static ItemAttributeModifiers createHandOfGreedAttributes() {
		return ItemAttributeModifiers.builder()
			.add(Attributes.BLOCK_INTERACTION_RANGE,
				new AttributeModifier(
					Risus.prefix("reach_modifier"),
					3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND)
			.build();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.risus.hand_of_greed").withStyle(ChatFormatting.GRAY));
	}
}
