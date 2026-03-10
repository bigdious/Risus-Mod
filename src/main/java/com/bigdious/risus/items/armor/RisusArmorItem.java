package com.bigdious.risus.items.armor;

import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;


public class RisusArmorItem extends ArmorItem {
	public RisusArmorItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}
	public static final int DEFAULT_COLOR = 0xFFb50404;
	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return stack.is(RisusItems.SKIN_BOOTS.get()) || stack.is(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
	}

	public static ItemAttributeModifiers createSkinAttributes(ArmorItem.Type type, int armor) {
		ResourceLocation armorLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
		EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(type.getSlot());
		return ItemAttributeModifiers.builder()
			.add(Attributes.ARMOR, new AttributeModifier(armorLocation, armor, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.MAX_HEALTH, new AttributeModifier(armorLocation, 5.0, AttributeModifier.Operation.ADD_VALUE), group)
			.build();
	}

	public static ItemAttributeModifiers createSinnerAttributes(ArmorItem.Type type, int armor) {
		ResourceLocation armorLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
		EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(type.getSlot());
		return ItemAttributeModifiers.builder()
			.add(Attributes.ARMOR, new AttributeModifier(armorLocation, armor, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.MAX_HEALTH, new AttributeModifier(armorLocation, 2.0, AttributeModifier.Operation.ADD_VALUE), group)
			.build();
	}

	public static ItemAttributeModifiers createBloodFeatherAttributes(ArmorItem.Type type, int armor) {
		ResourceLocation armorLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
		EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(type.getSlot());
		return ItemAttributeModifiers.builder()
			.add(Attributes.ARMOR, new AttributeModifier(armorLocation, armor, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.STEP_HEIGHT, new AttributeModifier(armorLocation, 0.4, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.SNEAKING_SPEED, new AttributeModifier(armorLocation, 0.50, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), group)
			.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(armorLocation, 1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), group)
			.add(Attributes.JUMP_STRENGTH, new AttributeModifier(armorLocation, 1.00, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), group)
			.add(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(armorLocation, 10, AttributeModifier.Operation.ADD_VALUE), group)
			.add(Attributes.FALL_DAMAGE_MULTIPLIER, new AttributeModifier(armorLocation, -0.50, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), group)
			.build();
	}
}
