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
import net.minecraft.world.level.levelgen.structure.Structure;

public class RisusTags {
	public static class Blocks {
		public static final TagKey<Block> BONDKNOT_LOGS = BlockTags.create(Risus.prefix("bondknot_logs"));
		public static final TagKey<Block> JOYFLAME_FIRE_BASE_BLOCKS = BlockTags.create(Risus.prefix("joyflame_fire_base_blocks"));
		public static final TagKey<Block> COPPER_AMALGAM_VARIATION = create("copper_amalgam_variation");
		public static final TagKey<Block> WAXED_COPPER_AMALGAM_VARIATION = create("waxed_copper_amalgam_variation");
		public static final TagKey<Block> LITTER_ALLOWED_LIGHT_BLOCKS = create("litter_allowed_light_blocks");
		public static final TagKey<Block> SPAWN_SPIRE_ON = create("spawn_spire_on");
		public static final TagKey<Block> REMAINS = create("remains");
		public static final TagKey<Block> PROLIFERABLE_SOILS = create("proliferable_soils");
		public static final TagKey<Block> PROLIFERABLE_ROCKS = create("proliferable_rocks");

		private static TagKey<Block> create(String name) {
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Biomes {
		public static final TagKey<Biome> HAS_ALTERATION_SITE = create("has_structure/alteration_site");
		public static final TagKey<Biome> HAS_GRASSY_SITE = create("has_structure/grassy_site");
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
		public static final TagKey<Biome> HAS_CHURCH = create("has_structure/church");
		public static final TagKey<Biome> HAS_FOSSILS = create("has_structure/fossils");

		private static TagKey<Biome> create(String name) {
			return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> BONDKNOT_LOGS = ItemTags.create(Risus.prefix("bondknot_logs"));
		public static final TagKey<Item> JOYFLAME_FIRE_BASE_BLOCKS = ItemTags.create(Risus.prefix("joyflame_fire_base_blocks"));
		public static final TagKey<Item> FROGLIGHT_BLOCKS = ItemTags.create(Risus.prefix("froglight_blocks"));
		public static final TagKey<Item> BONE_BLOCK_VARIATION = create("bone_block_variation");
		public static final TagKey<Item> FOSSIL_VARIATION = create("fossil_variation");
		public static final TagKey<Item> WILLFUL_WEAPON = create("willful_weapon");
		public static final TagKey<Item> EYE = create("eye");
		public static final TagKey<Item> BASE_TISSUE = create("base_tissue");
		public static final TagKey<Item> HURTS_ANGEL_WINGS = create("hurts_angel_wings");
		public static final TagKey<Item> HORNS = create("horns");
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
		public static final TagKey<Item> ROBES = create("robes");
		public static final TagKey<Item> SWORD_AND_TRIDENT_ENCHANTABLE = create("sword_and_trident_enchantable");
		public static final TagKey<Item> PERPETUITY_BLACKLIST = create("perpetuity_blacklist");
		public static final TagKey<Item> NOT_RELOCATABLE_FROM = create("not_relocatable_from");
		public static final TagKey<Item> NOT_RELOCATABLE_TO = create("not_relocatable_to");
		public static final TagKey<Item> ENCHANTED_BOOK_EQUIVALENT = create("enchanted_book_equivalent");
		public static final TagKey<Item> GOTHIC_FENCES = create("gothic_fences");
		public static final TagKey<Item> GRIMSTONES = create("grimstones");
		public static final TagKey<Item> GLUTTONY_STUFF = create("gluttony_stuff");
		public static final TagKey<Item> RAW_MEAT = create("raw_meat");
		public static final TagKey<Item> SCYTHES = create("scythes");

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
		public static final TagKey<EntityType<?>> OFFSPRINGS_AND_BELOVEDS = create("offsprings_and_beloveds");
		public static final TagKey<EntityType<?>> CANT_BE_STOLEN_FROM = create("cant_be_stolen_from");
		public static final TagKey<EntityType<?>> BREAKS_DEPTH_VASES = create("breaks_depth_vases");
		public static final TagKey<EntityType<?>> YOUTH_BANNED = create("youth_banned");
		public static final TagKey<EntityType<?>> YOUTH_SHRINKS = create("youth_shrinks");
		public static final TagKey<EntityType<?>> HORN_BUFFS = create("horn_buffs");
		public static final TagKey<EntityType<?>> HEXHORN_BANNED = create("hexhorn_banned");
		public static final TagKey<EntityType<?>> HEXHORN_ALLOWED = create("hexhorn_allowed");
		public static final TagKey<EntityType<?>> SENSITIVE_TO_HUNTERS = create("sensitive_to_hunters");
		public static final TagKey<EntityType<?>> SMALL_MARITIME_SNARE_POOL = create("small_maritime_snare_pool");
		public static final TagKey<EntityType<?>> MEDIUM_MARITIME_SNARE_POOL = create("medium_maritime_snare_pool");
		public static final TagKey<EntityType<?>> LARGE_MARITIME_SNARE_POOL = create("large_maritime_snare_pool");
		public static final TagKey<EntityType<?>> TRIDENT_LIKE_PROJECTILES = create("trident_like_projectiles");

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
		public static final TagKey<Enchantment> KILLJOY_ALLOWED_ENCHANTS = create("killjoy_allowed_enchants");
		public static final TagKey<Enchantment> THOUSAND_BLADE_ALLOWED_ENCHANTS = create("thousand_blade_allowed_enchants");
		public static final TagKey<Enchantment> TOOTHKNOCKER_ALLOWED_ENCHANTS = create("toothknocker_allowed_enchants");
		public static final TagKey<Enchantment> BOOMSTICK_ALLOWED_ENCHANTS = create("boomstick_allowed_enchants");
		public static final TagKey<Enchantment> WARHORN_ALLOWED_ENCHANTS = create("warhorn_allowed_enchants");
		public static final TagKey<Enchantment> HEXHORN_ALLOWED_ENCHANTS = create("hexhorn_allowed_enchants");
		public static final TagKey<Enchantment> EXECRATIONS = create("execrations");
		public static final TagKey<Enchantment> ALTERABLE_ENCHANTS = create("alterable_enchants");

		private static TagKey<Enchantment> create(String name) {
			return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class BannerPatterns {
		public static final TagKey<BannerPattern> SMILE_PATTERN = create("pattern_item/smile");
		public static final TagKey<BannerPattern> DIVINITY_PATTERN = create("pattern_item/divinity");
		public static final TagKey<BannerPattern> TREE_PATTERN = create("pattern_item/tree");
		public static final TagKey<BannerPattern> ROSE_PATTERN = create("pattern_item/rose");

		private static TagKey<BannerPattern> create(String name) {
			return TagKey.create(Registries.BANNER_PATTERN, Risus.prefix(name));
		}
	}

	public static class Structures {
		public static final TagKey<Structure> RISUS = create("risus");

		private static TagKey<Structure> create(String name) {
			return TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}
}
