package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;

public class RisusTags {
	public class Enchantments {

//		public static final TagKey<Enchantment> CRESCENT_DISASTER_CUSTOM = tag("custom_set/crescent_disaster");

		private static TagKey<Enchantment> tag(String name) {
			return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}
	public static class Biomes {

		public static final TagKey<Biome> HAS_ALTERATION_SITE = tag("has_structure/alteration_site");
		public static final TagKey<Biome> HAS_GRASSY_MAW = tag("has_structure/grassy_maw");

		private static TagKey<Biome> tag(String name) {
			return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Items {
//		public static final TagKey<Item> LOOTING_ENCHANTABLE = tag("enchantable/looting");
		private static TagKey<Item> tag(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}

	}
}
