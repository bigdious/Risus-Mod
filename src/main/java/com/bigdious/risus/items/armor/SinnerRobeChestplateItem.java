package com.bigdious.risus.items.armor;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.render.RisusSimpleArmorRenderer;
import com.bigdious.risus.init.RisusDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class SinnerRobeChestplateItem extends RisusArmorItem {
	public SinnerRobeChestplateItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
		return
//			stack.get(RisusDataComponents.ABILITY_VARIANT) != null && stack.get(RisusDataComponents.ABILITY_VARIANT).equals("hand_of_greed") ?
				super.getDefaultAttributeModifiers().withModifierAdded(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(Risus.prefix("reach_modifier"), 3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
//				: this.getDefaultAttributeModifiers()
				;
	}

	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null) {
			return layer.texture(false).equals(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/chestplate/upgrade_layer_1.png")) ? ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/chestplate/" + stack.get(RisusDataComponents.ABILITY_VARIANT) + ".png") :
				layer.dyeable() && stack.get(RisusDataComponents.ABILITY_VARIANT).equals("guts") ? ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/chestplate/" + stack.get(RisusDataComponents.ABILITY_VARIANT) + "_dyed.png") : null;
		}
		return null;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null) {
			ChatFormatting color = CHESTPLATE_ABILITY_COLOR.getOrDefault(stack.get(RisusDataComponents.ABILITY_VARIANT), ChatFormatting.GRAY);
			tooltipComponents.add(Component.translatable("tooltip.risus.ability").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_chestplate." + stack.get(RisusDataComponents.ABILITY_VARIANT)).withStyle(color));
			if (stack.get(RisusDataComponents.ABILITY_VARIANT).equals("great_stool")) {
				tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_chestplate.great_stool.button_press", Component.translatable("tooltip.risus.great_stool.button_press.outline", Component.keybind("keybind.summon_greatness").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.WHITE)).withStyle(color));
			} else {
				tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_chestplate." + stack.get(RisusDataComponents.ABILITY_VARIANT) + ".desc").withStyle(color));
			}
		}
		super.appendHoverText(stack, context, tooltipComponents, isAdvanced);
	}

	public static final Map<String, ChatFormatting> CHESTPLATE_ABILITY_COLOR = Map.ofEntries(
		Map.entry("guts", ChatFormatting.RED),
		Map.entry("great_stool", ChatFormatting.DARK_GRAY)
	);

	public static final class ArmorRender extends RisusSimpleArmorRenderer {
		public ArmorRender() {
			super(HumanoidArmorModel::new, RisusModelLayers.SINNER_ROBES_CHESTPLATE_INNER, RisusModelLayers.SINNER_ROBES_CHESTPLATE_OUTER);
		}

		@Override
		public int getDefaultDyeColor(ItemStack stack) {
			return DEFAULT_COLOR;
		}

		@Override
		public int getArmorLayerTintColor(ItemStack stack, LivingEntity entity, ArmorMaterial.Layer layer, int layerIdx, int fallbackColor) {
			return layer.dyeable() ? stack.get(DataComponents.DYED_COLOR) != null ? FastColor.ARGB32.opaque(stack.get(DataComponents.DYED_COLOR).rgb()) : DEFAULT_COLOR : -1;
		}
	}
}
