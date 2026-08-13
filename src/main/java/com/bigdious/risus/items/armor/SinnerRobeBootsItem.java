package com.bigdious.risus.items.armor;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.RisusModelLayers;
import com.bigdious.risus.client.render.RisusSimpleArmorRenderer;
import com.bigdious.risus.components.item.ArmorUpgradingContent;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SinnerRobeBootsItem extends UpgradableRisusArmorItem {
	public SinnerRobeBootsItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
		super(armorMaterial, type, properties);
	}

	@Override
	public TagKey<Item> acceptedTag(){
		return RisusTags.Items.BOOT_UPGRADE;
	}

	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null && layer.texture(false).equals(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/boots/upgrade_layer_1.png"))) {
			return ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/models/armor/robe/boots/" + stack.get(RisusDataComponents.ABILITY_VARIANT) + ".png");
		}
		return null;
	}

	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return true;
	}


	public static final class ArmorRender extends RisusSimpleArmorRenderer {
		public ArmorRender() {
			super(HumanoidArmorModel::new, RisusModelLayers.SINNER_ROBES_BOOTS_INNER, RisusModelLayers.SINNER_ROBES_BOOTS_OUTER);
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

