package com.bigdious.risus.compat.curios;

import com.bigdious.risus.compat.curios.renderers.HandCuriosRenderer;
import com.bigdious.risus.compat.curios.renderers.HeadCuriosRenderer;
import com.bigdious.risus.compat.curios.renderers.BodyCuriosRenderer;
import com.bigdious.risus.init.RisusItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosCompat {
	public static void registerCuriosCapabilities(final RegisterCapabilitiesEvent evt) {
		evt.registerItem(
			CuriosCapability.ITEM,
			(stack, context) -> new ICurio() {

				@Override
				public ItemStack getStack() {
					return stack;
				}


				@Override
				public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id) {
					ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
					stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY).forEach(EquipmentSlotGroup.HAND, builder::putAll);
					return builder.build();
				}

			}, RisusItems.HAND_OF_GREED, RisusItems.BLOOD_FEATHER);
	}

	public static void registerCurioRenderers(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			CuriosRendererRegistry.register(RisusItems.HAND_OF_GREED.get(), HandCuriosRenderer::new);
			CuriosRendererRegistry.register(RisusItems.BLOODWYRM_HEAD.get(), HeadCuriosRenderer::new);
			CuriosRendererRegistry.register(RisusItems.TOTEM_OF_UNYIELDING.get(), BodyCuriosRenderer::new);
			CuriosRendererRegistry.register(RisusItems.BLOOD_FEATHER.get(), HeadCuriosRenderer::new);
		});
	}
}
