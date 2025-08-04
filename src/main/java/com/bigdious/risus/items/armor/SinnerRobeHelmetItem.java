package com.bigdious.risus.items.armor;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.render.RisusSimpleArmorRenderer;
import com.bigdious.risus.init.RisusDataComponents;
import com.mojang.datafixers.util.Pair;
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
import net.minecraft.world.item.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;


public class SinnerRobeHelmetItem extends RisusArmorItem {
	public SinnerRobeHelmetItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}
	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null && layer.texture(false).equals(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/helmet/upgrade_layer_1.png"))){
			return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/helmet/" + stack.get(RisusDataComponents.ABILITY_VARIANT) + ".png");
		}
		return null;
	}



	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null) {
			tooltipComponents.add(Component.translatable("tooltip.risus.ability").withStyle(ChatFormatting.GRAY));
			tooltipComponents.add(Component.translatable("tooltip.risus.sinner_robes_helmet." + stack.get(RisusDataComponents.ABILITY_VARIANT)).withStyle(ABILITY_COLOR.get(stack.get(RisusDataComponents.ABILITY_VARIANT))));
		}
		super.appendHoverText(stack, context, tooltipComponents, isAdvanced);
	}

	public static final Map<String, ChatFormatting> ABILITY_COLOR = Map.ofEntries(
		Map.entry("skeleton", ChatFormatting.WHITE),
		Map.entry("creeper", ChatFormatting.GREEN),
		Map.entry("wither_skeleton", ChatFormatting.DARK_GRAY ),
		Map.entry("zombie", ChatFormatting.DARK_GREEN ),
		Map.entry("piglin", ChatFormatting.RED ),
		Map.entry("tuxedo_cat", ChatFormatting.WHITE ),
		Map.entry("black_cat", ChatFormatting.WHITE ),
		Map.entry("british_cat", ChatFormatting.WHITE ),
		Map.entry("calico_cat", ChatFormatting.WHITE ),
		Map.entry("jellie_cat", ChatFormatting.WHITE ),
		Map.entry("persian_cat", ChatFormatting.WHITE ),
		Map.entry("ragdoll_cat", ChatFormatting.WHITE ),
		Map.entry("orange_cat", ChatFormatting.WHITE ),
		Map.entry("siamese_cat", ChatFormatting.WHITE ),
		Map.entry("tabby_cat", ChatFormatting.WHITE ),
		Map.entry("white_cat", ChatFormatting.WHITE ),
		Map.entry("pumpkin", ChatFormatting.GOLD ),
		Map.entry("pale_wolf", ChatFormatting.WHITE ),
		Map.entry("ashen_wolf", ChatFormatting.WHITE ),
		Map.entry("black_wolf", ChatFormatting.WHITE ),
		Map.entry("chestnut_wolf", ChatFormatting.WHITE ),
		Map.entry("rusty_wolf", ChatFormatting.WHITE ),
		Map.entry("snowy_wolf", ChatFormatting.WHITE ),
		Map.entry("spotted_wolf", ChatFormatting.WHITE ),
		Map.entry("striped_wolf", ChatFormatting.WHITE ),
		Map.entry("woods_wolf", ChatFormatting.WHITE ),
		Map.entry("bleached_eye", ChatFormatting.WHITE ),
		Map.entry("bloodshot_eye", ChatFormatting.DARK_RED ),
		Map.entry("emerald_eye", ChatFormatting.DARK_GREEN ),
		Map.entry("ender_eye", ChatFormatting.DARK_BLUE ),
		Map.entry("golden_eye", ChatFormatting.GOLD ),
		Map.entry("abyssal_eye", ChatFormatting.DARK_GRAY ),
		Map.entry("smile", ChatFormatting.DARK_RED )
	);

	public static final class ArmorRender extends RisusSimpleArmorRenderer {
		public ArmorRender() {
			super(HumanoidArmorModel::new, RisusModelLayers.SINNER_ROBES_HELMET_INNER, RisusModelLayers.SINNER_ROBES_HELMET_OUTER);
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
