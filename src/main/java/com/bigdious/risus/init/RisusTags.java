package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class RisusTags {
	public class Enchantments {

		public static final TagKey<Enchantment> CRESCENT_DISASTER_CUSTOM = tag("custom_set/crescent_disaster");

		private static TagKey<Enchantment> tag(String name) {
			return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}
	public static class Items {
		public static final TagKey<Item> CRESCENT_DISASTER_ENCHANTABLE = tag("crescent_disaster");
		private static TagKey<Item> tag(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}

	}
}
