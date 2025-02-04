package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
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
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class RisusTags {
	public static class Blocks {
		public static final TagKey<Block> COPPER_AMALGAM_VARIATION = tag("copper_amalgam_variation");
		public static final TagKey<Block> WAXED_COPPER_AMALGAM_VARIATION = tag("waxed_copper_amalgam_variation");

		private static TagKey<Block> tag(String name) {
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Biomes {
		public static final TagKey<Biome> HAS_ALTERATION_SITE = tag("has_structure/alteration_site");
		public static final TagKey<Biome> HAS_GRASSY_MAW = tag("has_structure/grassy_maw");
		public static final TagKey<Biome> HAS_SANDY_MAW = tag("has_structure/sandy_maw");
		public static final TagKey<Biome> HAS_ENDY_MAW = tag("has_structure/endy_maw");
		public static final TagKey<Biome> HAS_FAMILY_TREE = tag("has_structure/family_tree");
		public static final TagKey<Biome> HAS_ANGEL_ALTAR = tag("has_structure/angel_altar");
		public static final TagKey<Biome> HAS_GREAT_BODY = tag("has_structure/great_body");
		public static final TagKey<Biome> HAS_FLOWER_FIELD = tag("has_structure/flower_field");
		public static final TagKey<Biome> HAS_DUNGEON = tag("has_structure/dungeon");
		public static final TagKey<Biome> HAS_BLOOD_WELL = tag("has_structure/blood_well");
		public static final TagKey<Biome> HAS_LAB = tag("has_structure/lab");
		public static final TagKey<Biome> HAS_DRAXOLOTL_REMAINS = tag("has_structure/draxolotl_remains");

		private static TagKey<Biome> tag(String name) {
			return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> BONE_BLOCK_VARIATION = tag("bone_block_variation");
		public static final TagKey<Item> WILLFUL_WEAPON = tag("willful_weapon");
		public static final TagKey<Item> EYE = tag("eye");
		public static final TagKey<Item> BASE_TISSUE = tag("base_tissue");
		public static final TagKey<Item> HURTS_ANGEL_WINGS = tag("hurts_angel_wings");
		public static final TagKey<Item> LIGHTLY_HURTS_ANGEL_WINGS = tag("lightly_hurts_angel_wings");
		public static final TagKey<Item> ALTERABLE_GATES = tag("alterable_gates");
		public static final TagKey<Item> ALTERABLE_FENCES = tag("alterable_fences");
		public static final TagKey<Item> ALTERABLE_TRAPDOORS = tag("alterable_trapdoors");
		public static final TagKey<Item> ALTERABLE_HANGING_SIGNS = tag("alterable_hanging_signs");
		public static final TagKey<Item> ALTERABLE_SIGNS = tag("alterable_signs");
		public static final TagKey<Item> ALTERABLE_DOORS = tag("alterable_doors");
		public static final TagKey<Item> ALTERABLE_BUTTONS = tag("alterable_buttons");
		public static final TagKey<Item> ALTERABLE_PRESSURE_PLATES = tag("alterable_pressure_plates");
		public static final TagKey<Item> ALTERABLE_SLABS = tag("alterable_slabs");
		public static final TagKey<Item> ALTERABLE_STAIRS = tag("alterable_stairs");
		public static final TagKey<Item> ALTERABLE_PLANKS = tag("alterable_planks");
		public static final TagKey<Item> ALTERABLE_LOGS = tag("alterable_logs");
		public static final TagKey<Item> ALTERABLE_STRIPPED_LOGS = tag("alterable_logs");
		public static final TagKey<Item> ALTERABLE_WOODS = tag("alterable_woods");
		public static final TagKey<Item> ALTERABLE_STRIPPED_WOODS = tag("alterable_woods");

		private static TagKey<Item> tag(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}

	}

	public static class Entities {
		public static final TagKey<EntityType<?>> OFFSPRING = tag("offspring");
		public static final TagKey<EntityType<?>> BELOVED = tag("beloved");
		public static final TagKey<EntityType<?>> LOVEABLE = tag("loveable");
		public static final TagKey<EntityType<?>> CANT_BE_STOLEN_FROM = tag("cant_be_stolen_from");
		public static final TagKey<EntityType<?>> CANT_BE_STRIPPED = tag("cant_be_stripped");

		private static TagKey<EntityType<?>> tag(String name) {
			return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Risus.MODID, name));
		}
	}

	public static class BannerPatternTagGenerator extends TagsProvider<BannerPattern> {

		public static final TagKey<BannerPattern> SMILE_PATTERN = create("pattern_item/smile");
		public static final TagKey<BannerPattern> DIVINITY_PATTERN = create("pattern_item/divinity");
		public static final TagKey<BannerPattern> TREE_PATTERN = create("pattern_item/tree");

		public BannerPatternTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
			super(output, Registries.BANNER_PATTERN, provider, Risus.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {

			this.tag(SMILE_PATTERN).add(RisusBannerPatterns.SMILE);
			this.tag(DIVINITY_PATTERN).add(RisusBannerPatterns.DIVINITY);
			this.tag(TREE_PATTERN).add(RisusBannerPatterns.TREE);
		}

		private static TagKey<BannerPattern> create(String name) {
			return TagKey.create(Registries.BANNER_PATTERN, Risus.prefix(name));
		}

		@Override
		public String getName() {
			return "Risus Banner Pattern Tags";
		}
	}
}
