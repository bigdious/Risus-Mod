package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.LightningResistantItemEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CounterweightItem extends Item {
	public CounterweightItem(Properties properties) {
		super(properties);
	}

	public static ItemAttributeModifiers createCounterweightAttributes() {
		return ItemAttributeModifiers.builder()
			.add(Attributes.GRAVITY,
				new AttributeModifier(
					Risus.prefix("gravity_modifier"),
					-0.50, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.HAND)
			.add(Attributes.SAFE_FALL_DISTANCE,
				new AttributeModifier(
					Risus.prefix("safe_fall_distance_modifier"),
					5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND)
			.build();
	}
}
