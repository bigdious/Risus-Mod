package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class WretchedCharmItem extends Item {
	public WretchedCharmItem(Properties properties) {
		super(properties);
	}
	public static ItemAttributeModifiers createWretchedCharmAttributes() {
		return ItemAttributeModifiers.builder()
			.add(Attributes.LUCK,
				new AttributeModifier(
					Risus.prefix("charm_unluck"),
					-3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND)
			.build();
	}
}
