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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class SinnerRobeLeggingsItem extends RisusArmorItem{
	public SinnerRobeLeggingsItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}

	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null && layer.texture(false).equals(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/leggings/upgrade_layer_1.png"))){
			return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/leggings/" + stack.get(RisusDataComponents.ABILITY_VARIANT) + ".png");
		}
		return null;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null) {
			ChatFormatting color = LEGGINGS_ABILITY_COLOR.getOrDefault(stack.get(RisusDataComponents.ABILITY_VARIANT), ChatFormatting.GRAY);
			tooltipComponents.add(Component.translatable("tooltip.risus.ability").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_leggings." + stack.get(RisusDataComponents.ABILITY_VARIANT)).withStyle(color));
			if (stack.get(RisusDataComponents.ABILITY_VARIANT).equals("great_stool")) {
				tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_leggings.great_stool.button_press", Component.translatable("tooltip.risus.great_stool.button_press.outline", Component.keybind("keybind.summon_greatness").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.WHITE)).withStyle(color));
			} else if (stack.get(RisusDataComponents.ABILITY_VARIANT).equals("book") ){
				tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_leggings.book.button_press", Component.translatable("tooltip.risus.great_stool.button_press.outline", Component.keybind("keybind.researchers_notes_open").withStyle(ChatFormatting.DARK_RED)).withStyle(ChatFormatting.WHITE)).withStyle(color));
			} else {
				tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_leggings." + stack.get(RisusDataComponents.ABILITY_VARIANT) + ".desc").withStyle(color));
			}
		}
		super.appendHoverText(stack, context, tooltipComponents, isAdvanced);
	}

	public static final Map<String, ChatFormatting> LEGGINGS_ABILITY_COLOR = Map.ofEntries(
		Map.entry("great_stool", ChatFormatting.DARK_GRAY),
		Map.entry("book", ChatFormatting.WHITE)
	);

	public static final class ArmorRender extends RisusSimpleArmorRenderer {
		public ArmorRender() {
			super(HumanoidArmorModel::new, RisusModelLayers.SINNER_ROBES_LEGGINGS_INNER, RisusModelLayers.SINNER_ROBES_LEGGINGS_OUTER);
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
