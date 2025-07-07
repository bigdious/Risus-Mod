package com.bigdious.risus.items.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class RoseCrownItem extends RisusArmorItem {
	public RoseCrownItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}
	public static ItemAttributeModifiers createRoseAttributes(ArmorItem.Type type, int armor) {
		ResourceLocation armorLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
		EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(type.getSlot());
		return ItemAttributeModifiers.builder()
			.add(Attributes.ARMOR, new AttributeModifier(armorLocation, armor, AttributeModifier.Operation.ADD_VALUE), group)
			.build();
	}
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.risus.rose_crown").withStyle(ChatFormatting.BLUE));
	}
}
