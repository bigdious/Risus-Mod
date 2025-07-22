package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class LuckyCharmItem extends Item {
	public LuckyCharmItem(Properties properties) {
		super(properties);
	}
	public static ItemAttributeModifiers createLuckyCharmAttributes() {
		return ItemAttributeModifiers.builder()
			.add(Attributes.LUCK,
				new AttributeModifier(
					Risus.prefix("charm_luck"),
					3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND)
			.build();
	}
}
