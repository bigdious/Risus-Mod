package com.bigdious.risus.items.armor;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.render.RisusSimpleArmorRenderer;
import com.bigdious.risus.init.RisusDataComponents;
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
import org.jetbrains.annotations.Nullable;


public class SinnerRobeHelmetItem extends RisusArmorItem {
	public SinnerRobeHelmetItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}
	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null && layer.texture(false).equals(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/helmet/upgrade_layer_1.png"))){
			switch (stack.get(RisusDataComponents.ABILITY_VARIANT)) {
				//check SmithingUpgradeRecipe for cases
				case "skeleton": return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/helmet/skeleton.png");
				case "zombie": return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/helmet/zombie.png");
				case "wither_skeleton": return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/helmet/wither_skeleton.png");
				case "creeper": return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/helmet/creeper.png");
			}
		}
		return null;
	}


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
