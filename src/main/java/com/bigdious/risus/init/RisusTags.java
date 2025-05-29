package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;

public class RisusTags {
	public static class Blocks {
		public static final TagKey<Block> BONDKNOT_LOGS = BlockTags.create(Risus.prefix("bondknot_logs"));
		public static final TagKey<Block> JOYFLAME_FIRE_BASE_BLOCKS = BlockTags.create(Risus.prefix("joyflame_fire_base_blocks"));
		public static final TagKey<Block> COPPER_AMALGAM_VARIATION = create("copper_amalgam_variation");
		public static final TagKey<Block> WAXED_COPPER_AMALGAM_VARIATION = create("waxed_copper_amalgam_variation");
		public static final TagKey<Block> LITTER_ALLOWED_LIGHT_BLOCKS = create("litter_allowed_light_blocks");
		public static final TagKey<Block> ILLEGAL_LITTER_ALLOWED_LIGHT_BLOCKS = create("non_canon_litter_allowed_light_blocks");

		private static TagKey<Block> create(String name) {
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Biomes {
		public static final TagKey<Biome> HAS_ALTERATION_SITE = create("has_structure/alteration_site");
		public static final TagKey<Biome> HAS_GRASSY_MAW = create("has_structure/grassy_maw");
		public static final TagKey<Biome> HAS_SANDY_MAW = create("has_structure/sandy_maw");
		public static final TagKey<Biome> HAS_ENDY_MAW = create("has_structure/endy_maw");
		public static final TagKey<Biome> HAS_FAMILY_TREE = create("has_structure/family_tree");
		public static final TagKey<Biome> HAS_ANGEL_ALTAR = create("has_structure/angel_altar");
		public static final TagKey<Biome> HAS_GREAT_BODY = create("has_structure/great_body");
		public static final TagKey<Biome> HAS_FLOWER_FIELD = create("has_structure/flower_field");
		public static final TagKey<Biome> HAS_DUNGEON = create("has_structure/dungeon");
		public static final TagKey<Biome> HAS_BLOOD_WELL = create("has_structure/blood_well");
		public static final TagKey<Biome> HAS_LAB = create("has_structure/lab");
		public static final TagKey<Biome> HAS_DRAXOLOTL_REMAINS = create("has_structure/draxolotl_remains");

		private static TagKey<Biome> create(String name) {
			return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> BONDKNOT_LOGS = ItemTags.create(Risus.prefix("bondknot_logs"));
		public static final TagKey<Item> JOYFLAME_FIRE_BASE_BLOCKS = ItemTags.create(Risus.prefix("joyflame_fire_base_blocks"));
		public static final TagKey<Item> FROGLIGHT_BLOCKS = ItemTags.create(Risus.prefix("froglight_blocks"));
		public static final TagKey<Item> BONE_BLOCK_VARIATION = create("bone_block_variation");
		public static final TagKey<Item> WILLFUL_WEAPON = create("willful_weapon");
		public static final TagKey<Item> EYE = create("eye");
		public static final TagKey<Item> BASE_TISSUE = create("base_tissue");
		public static final TagKey<Item> HURTS_ANGEL_WINGS = create("hurts_angel_wings");
		public static final TagKey<Item> LIGHTLY_HURTS_ANGEL_WINGS = create("lightly_hurts_angel_wings");
		public static final TagKey<Item> STOPS_THREAD_WINGS_RENDERING = create("stops_thread_wings_rendering");
		public static final TagKey<Item> ALTERABLE_GATES = create("alterable_gates");
		public static final TagKey<Item> ALTERABLE_FENCES = create("alterable_fences");
		public static final TagKey<Item> ALTERABLE_TRAPDOORS = create("alterable_trapdoors");
		public static final TagKey<Item> ALTERABLE_HANGING_SIGNS = create("alterable_hanging_signs");
		public static final TagKey<Item> ALTERABLE_SIGNS = create("alterable_signs");
		public static final TagKey<Item> ALTERABLE_DOORS = create("alterable_doors");
		public static final TagKey<Item> ALTERABLE_BUTTONS = create("alterable_buttons");
		public static final TagKey<Item> ALTERABLE_PRESSURE_PLATES = create("alterable_pressure_plates");
		public static final TagKey<Item> ALTERABLE_SLABS = create("alterable_slabs");
		public static final TagKey<Item> ALTERABLE_STAIRS = create("alterable_stairs");
		public static final TagKey<Item> ALTERABLE_PLANKS = create("alterable_planks");
		public static final TagKey<Item> ALTERABLE_LOGS = create("alterable_logs");
		public static final TagKey<Item> CURIOS_HANDS = createCurios("hands");
		public static final TagKey<Item> CURIOS_BELT = createCurios("belt");
		public static final TagKey<Item> CURIOS_CHARM = createCurios("charm");
		public static final TagKey<Item> CURIOS_HEAD = createCurios("head");

		private static TagKey<Item> create(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
		private static TagKey<Item> createCurios(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", name));
		}

	}

	public static class Entities {
		public static final TagKey<EntityType<?>> OFFSPRING = create("offspring");
		public static final TagKey<EntityType<?>> BELOVED = create("beloved");
		public static final TagKey<EntityType<?>> CANT_BE_STOLEN_FROM = create("cant_be_stolen_from");
		public static final TagKey<EntityType<?>> BREAKS_DEPTH_VASES = create("breaks_depth_vases");

		private static TagKey<EntityType<?>> create(String name) {
			return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Enchantments {
		public static final TagKey<Enchantment> SCYTHE_ALLOWED_ENCHANTS = create("scythe_allowed_enchants");
		public static final TagKey<Enchantment> SOUL_SCYTHE_ALLOWED_ENCHANTS = create("soul_scythe_allowed_enchants");
		public static final TagKey<Enchantment> FIRE_SCYTHE_ALLOWED_ENCHANTS = create("fire_scythe_allowed_enchants");
		public static final TagKey<Enchantment> CINDERGLEE_SCYTHE_ALLOWED_ENCHANTS = create("cinderglee_scythe_allowed_enchants");
		public static final TagKey<Enchantment> CRESCENT_DISASTER_ALLOWED_ENCHANTS = create("crescent_disaster_allowed_enchants");
		public static final TagKey<Enchantment> THOUSAND_BLADE_ALLOWED_ENCHANTS = create("thousand_blade_allowed_enchants");
		public static final TagKey<Enchantment> TOOTHKNOCKER_ALLOWED_ENCHANTS = create("toothknocker_allowed_enchants");
		public static final TagKey<Enchantment> BOOMSTICK_ALLOWED_ENCHANTS = create("boomstick_allowed_enchants");

		private static TagKey<Enchantment> create(String name) {
			return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class BannerPatterns {
		public static final TagKey<BannerPattern> SMILE_PATTERN = create("pattern_item/smile");
		public static final TagKey<BannerPattern> DIVINITY_PATTERN = create("pattern_item/divinity");
		public static final TagKey<BannerPattern> TREE_PATTERN = create("pattern_item/tree");

		private static TagKey<BannerPattern> create(String name) {
			return TagKey.create(Registries.BANNER_PATTERN, Risus.prefix(name));
		}
	}
}
