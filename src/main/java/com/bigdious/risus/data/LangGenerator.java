package com.bigdious.risus.data;

import com.bigdious.risus.data.helper.RisusLangProvider;
import com.bigdious.risus.init.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import org.apache.commons.lang3.text.WordUtils;

import java.util.HashMap;
import java.util.Map;

public class LangGenerator extends RisusLangProvider {

	public static final Map<String, String> SUBTITLE_GENERATOR = new HashMap<>();

	public LangGenerator(PackOutput output) {
		super(output);
	}

	@Override
	protected void addTranslations() {
		this.addBlock(RisusBlocks.ACTIVE_GRIMSTONE, "Active Grimstone");
		this.addBlock(RisusBlocks.ALTERATION_CATALYST, "Alteration Catalyst");
		this.addBlock(RisusBlocks.ANGEL_ALTAR, "Angel Altar");
		this.addBlock(RisusBlocks.ASHEN_REMAINS, "Ashen Remains");
		this.addBlock(RisusBlocks.ASHEN_SPIRE, "Ashen Spire");
		this.addBlock(RisusBlocks.BABY_RIBCAGE, "Baby Ribcage");
		this.addBlock(RisusBlocks.BIG_CHAIN, "Big Chain");
		this.addBlock(RisusBlocks.BLOOD_CAULDRON, "Blood Cauldron");
		this.addBlock(RisusBlocks.BLOOD_FLUID_BLOCK, "Blood");
		this.addBlock(RisusBlocks.BLOODWEAVE, "Bloodweave");
		this.addBlock(RisusBlocks.BLOODWYRM_HEAD, "False Bloodwyrm Head");
		this.addBlock(RisusBlocks.BLOODY_SPONGE, "Bloody Sponge");
		this.addBlock(RisusBlocks.BOND_GLASS, "Crystallized Bond Glass");
		this.addBlock(RisusBlocks.BOND_GLASS_PANE, "Crystallized Bond Glass Pane");
		this.addBlock(RisusBlocks.BONDKNOT_BUTTON, "Bondknot Button");
		this.addBlock(RisusBlocks.BONDKNOT_DOOR, "Bondknot Door");
		this.addBlock(RisusBlocks.BONDKNOT_FENCE, "Bondknot Fence");
		this.addBlock(RisusBlocks.BONDKNOT_FENCE_GATE, "Bondknot Fence Gate");
		this.addBlock(RisusBlocks.BONDKNOT_HANGING_SIGN, "Bondknot Hanging Sign");
		this.addBlock(RisusBlocks.BONDKNOT_LOG, "Bondknot Log");
		this.addBlock(RisusBlocks.BONDKNOT_PLANKS, "Bondknot Planks");
		this.addBlock(RisusBlocks.BONDKNOT_PRESSURE_PLATE, "Bondknot Pressure Plate");
		this.addBlock(RisusBlocks.BONDKNOT_SIGN, "Bondknot Sign");
		this.addBlock(RisusBlocks.BONDKNOT_SLAB, "Bondknot Slab");
		this.addBlock(RisusBlocks.BONDKNOT_STAIRS, "Bondknot Stairs");
		this.addBlock(RisusBlocks.BONDKNOT_TRAPDOOR, "Bondknot Trapdoor");
		this.addBlock(RisusBlocks.BONDKNOT_WOOD, "Bondknot Wood");
		this.addBlock(RisusBlocks.BONE_PILLAR, "Bone Pillar");
		this.addBlock(RisusBlocks.BONE_SLAB, "Bone Slab");
		this.addBlock(RisusBlocks.BONE_STAIRS, "Bone Stairs");
		this.addBlock(RisusBlocks.BONE_WALL, "Bone Wall");
		this.addBlock(RisusBlocks.BUDDING_IMITATION_SCALEPLATE, "Budding Imitation Scaleplate");
		this.addBlock(RisusBlocks.BUDDING_UNALLOYED_SCALEPLATE, "Budding Unalloyed Scaleplate");
		this.addBlock(RisusBlocks.BUNDLE_OF_HAIR, "Bundle of Hair");
		this.addBlock(RisusBlocks.BURNT_HYPHAE, "Burnt Hyphae");
		this.addBlock(RisusBlocks.CHISELED_GRIMSTONE, "Chiseled Grimstone");
		this.addBlock(RisusBlocks.COAGULATED_BLOOD_BLOCK, "Coagulated Blood Block");
		this.addBlock(RisusBlocks.COALIFICATION, "Coalification");
		this.addBlock(RisusBlocks.CONTAINMENT_GLASS, "Containment Glass");
		this.addBlock(RisusBlocks.CONTAINMENT_GLASS_PANE, "Containment Glass Pane");
		this.addBlock(RisusBlocks.COPPER_AMALGAM, "Copper Amalgam");
		this.addBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS, "Cracked Grimstone Bricks");
		this.addBlock(RisusBlocks.CRYSTALLIZED_BONDS, "Crystallized Bonds");
		this.addBlock(RisusBlocks.CURVED_FLESHY_SKIN, "Curved Fleshy Skin");
		this.addBlock(RisusBlocks.CURVED_RITUAL_BLOCK, "Curved Ritual Block");
		this.addBlock(RisusBlocks.DARK_FENCE, "Dark Fence");
		this.addBlock(RisusBlocks.DARK_TRAPDOOR, "Dark Trapdoor");
		this.addBlock(RisusBlocks.DARK_GATE, "Dark Small Gate");
		this.addBlock(RisusBlocks.DARK_LARGE_GATE, "Dark Large Gate");
		this.addBlock(RisusBlocks.DARKNESS, "Darkness");
		this.addBlock(RisusBlocks.DECAYED_TISSUE, "Decayed Tissue");
		this.addBlock(RisusBlocks.DECAYING_TISSUE, "Decaying Tissue");
		this.addBlock(RisusBlocks.DECOMPOSED_TISSUE, "Decomposed Tissue");
		this.addBlock(RisusBlocks.DECOMPOSING_TISSUE, "Decomposing Tissue");
		this.addBlock(RisusBlocks.DEPTH_VASE, "Depth Vase");
		this.addBlock(RisusBlocks.DISPLAY_NOTCH, "Display Notch");
		this.addBlock(RisusBlocks.EERIE_FENCE, "Eerie Fence");
		this.addBlock(RisusBlocks.EERIE_GATE, "Eerie Small Gate");
		this.addBlock(RisusBlocks.EERIE_LARGE_GATE, "Eerie Large Gate");
		this.addBlock(RisusBlocks.EERIE_TRAPDOOR, "Eerie Trapdoor");
		this.addBlock(RisusBlocks.ENGRAVED_BASALT, "Engraved Basalt");
		this.addBlock(RisusBlocks.EXPOSED_COPPER_AMALGAM, "Exposed Copper Amalgam");
		this.addBlock(RisusBlocks.EYE_BLEACHED, "Bleached Eye Block");
		this.addBlock(RisusBlocks.EYE_BLOODSHOT, "Bloodshot Eye Block");
		this.addBlock(RisusBlocks.EYE_EMERALD, "Emerald Eye Block");
		this.addBlock(RisusBlocks.EYE_ENDER, "Ender Eye Block");
		this.addBlock(RisusBlocks.EYE_GOLDEN, "Golden Eye Block");
		this.addBlock(RisusBlocks.EYE_BLEACHED_GLOWING, "Glowing Bleached Eye Block");
		this.addBlock(RisusBlocks.EYE_BLOODSHOT_GLOWING, "Glowing Bloodshot Eye Block");
		this.addBlock(RisusBlocks.EYE_EMERALD_GLOWING, "Glowing Emerald Eye Block");
		this.addBlock(RisusBlocks.EYE_ENDER_GLOWING, "Glowing Ender Eye Block");
		this.addBlock(RisusBlocks.EYE_GOLDEN_GLOWING, "Glowing Golden Eye Block");
		this.addBlock(RisusBlocks.FADING_SHADOW, "Fading Shadow");
		this.addBlock(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK, "Imitation Scales Block");
		this.addBlock(RisusBlocks.FLATTENED_SCALES_BLOCK, "Gluttonous Scales Block");
		this.addBlock(RisusBlocks.FLESHY_SKIN, "Fleshy Skin");
		this.addBlock(RisusBlocks.FLESHY_SPAWNER, "Fleshy Monster Spawner");
		this.addBlock(RisusBlocks.FLOWERING_IMITATION_SCALEPLATE, "Flowering Imitation Scaleplate");
		this.addBlock(RisusBlocks.FLOWERING_UNALLOYED_SCALEPLATE, "Flowering Unalloyed Scaleplate");
		this.addBlock(RisusBlocks.FOSSIL, "Fossil");
		this.addBlock(RisusBlocks.FOSSIL_PILLAR, "Fossil Pillar");
		this.addBlock(RisusBlocks.FOSSIL_SLAB, "Fossil Slab");
		this.addBlock(RisusBlocks.FOSSIL_STAIRS, "Fossil Stairs");
		this.addBlock(RisusBlocks.FOSSIL_WALL, "Fossil Wall");
		this.addBlock(RisusBlocks.FULL_BONE_BLOCK, "Full Bone Block");
		this.addBlock(RisusBlocks.FULL_BONE_SLAB, "Full Bone Slab");
		this.addBlock(RisusBlocks.FULL_BONE_STAIRS, "Full Bone Stairs");
		this.addBlock(RisusBlocks.FULL_FOSSIL, "Full Fossil");
		this.addBlock(RisusBlocks.FULL_FOSSIL_SLAB, "Full Fossil Slab");
		this.addBlock(RisusBlocks.FULL_FOSSIL_STAIRS, "Full Fossil Stairs");
		this.addBlock(RisusBlocks.GLUTTONY_SCALEPLATE, "Gluttonous Scaleplate");
		this.addBlock(RisusBlocks.GRILLED_TISSUE, "Grilled Tissue");
		this.addBlock(RisusBlocks.GRIMSTONE, "Grimstone");
		this.addBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES, "Grimstone Blood Tiles");
		this.addBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_STAIRS, "Grimstone Blood Tiles Stairs");
		this.addBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_SLAB, "Grimstone Blood Tiles Slab");
		this.addBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_WALL, "Grimstone Blood Tiles Wall");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS, "Grimstone Bricks");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS_SLAB, "Grimstone Brick Slab");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS_STAIRS, "Grimstone Brick Stairs");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS_WALL, "Grimstone Brick Wall");
		this.addBlock(RisusBlocks.GRIMSTONE_PILLAR, "Grimstone Pillar");
		this.addBlock(RisusBlocks.GRIMSTONE_SLAB, "Grimstone Slab");
		this.addBlock(RisusBlocks.GRIMSTONE_STAIRS, "Grimstone Stairs");
		this.addBlock(RisusBlocks.GRIMSTONE_WALL, "Grimstone Wall");
		this.addBlock(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN, "Hairy Curved Fleshy Skin");
		this.addBlock(RisusBlocks.HAIRY_FLESHY_SKIN, "Hairy Fleshy Skin");
		this.addBlock(RisusBlocks.HAIRY_SKIN, "Hairy Skin");
		this.addBlock(RisusBlocks.HEART_TRANSPLANT, "Heart Transplant");
		this.addBlock(RisusBlocks.IMITATION_SCALEPLATE, "Imitation Scaleplate");
		this.addBlock(RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS, "Imitation Scales Stairs");
		this.addBlock(RisusBlocks.IMITATION_SCALES_BLOCK_SLAB, "Imitation Scales Slab");
		this.addBlock(RisusBlocks.IMITATION_SCALES_BLOCK_WALL, "Imitation Scales Wall");
		this.addBlock(RisusBlocks.INACTIVE_HOLDER, "Inactive Holder");
		this.addBlock(RisusBlocks.JOYFLAME_CAMPFIRE, "Cinderglee Campfire");
		this.addBlock(RisusBlocks.JOYFLAME_FIRE, "Cinderglee Fire");
		this.addBlock(RisusBlocks.JOYFLAME_LANTERN, "Cinderglee Lantern");
		this.addBlock(RisusBlocks.JOYFLAME_TORCH, "Cinderglee Torch");
		this.addBlock(RisusBlocks.LAUGHING_OBSIDIAN, "Smiling Obsidian");
		this.addBlock(RisusBlocks.LAUGHING_STALK, "Laughing Stalk");
		this.addBlock(RisusBlocks.LIGHT_EXCREMENT, "Light Dropping");
		this.addBlock(RisusBlocks.LINEAR_RITUAL_BLOCK, "Linear Ritual Block");
		this.addBlock(RisusBlocks.LIVING_TISSUE, "Tissue");
		this.addBlock(RisusBlocks.MAW_GUTS, "Gorger Guts");
		this.addBlock(RisusBlocks.MEMORY1, "Faded Statue");
		this.addBlock(RisusBlocks.MIRAGE_END_STONE, "Mirage End Stone");
		this.addBlock(RisusBlocks.MIRAGE_GRASS_BLOCK, "Mirage Grass Block");
		this.addBlock(RisusBlocks.MIRAGE_NETHERRACK, "Mirage Netherrack");
		this.addBlock(RisusBlocks.MIRAGE_SAND, "Mirage Sand");
		this.addBlock(RisusBlocks.NEURON_HEAD, "Neuron");
		this.addBlock(RisusBlocks.NEURON_STEM, "Neuron Stem");
		this.addBlock(RisusBlocks.ORGANIC_MATTER_BLOCK, "Organic Matter Block");
		this.addBlock(RisusBlocks.OXIDIZED_COPPER_AMALGAM, "Oxidized Copper Amalgam");
		this.addBlock(RisusBlocks.PEELED_GRILLED_TISSUE, "Peeled Grilled Tissue");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE, "Polished Grimstone");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE_SLAB, "Polished Grimstone Slab");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE_STAIRS, "Polished Grimstone Stairs");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE_WALL, "Polished Grimstone Wall");
		this.addBlock(RisusBlocks.POPPING_BONDKNOT_LOG, "Popping Bondknot Log");
		this.addBlock(RisusBlocks.POPPING_BONDKNOT_WOOD, "Popping Bondknot Wood");
		this.addBlock(RisusBlocks.POTTED_HEART_TRANSPLANT, "Potted Heart Transplant");
		this.addBlock(RisusBlocks.POTTED_REGEN_ROSE, "Potted Regeneration Rose");
		this.addBlock(RisusBlocks.REGEN_ROSE, "Regeneration Rose");
		this.addBlock(RisusBlocks.RIBCAGE, "Ribcage");
		this.addBlock(RisusBlocks.RITUAL, "Ritual");
		this.addBlock(RisusBlocks.ROTTED_TISSUE, "Rotted Tissue");
		this.addBlock(RisusBlocks.ROTTING_TISSUE, "Rotting Tissue");
		this.addBlock(RisusBlocks.SCAB, "Scab");
		this.addBlock(RisusBlocks.SKIN, "Skin");
		this.addBlock(RisusBlocks.SMILING_REMAINS, "Smiling Remains");
		this.addBlock(RisusBlocks.SPREADING_REMAINS, "Spreading Remains");
		this.addBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG, "Stripped Bondknot Log");
		this.addBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD, "Stripped Bondknot Wood");
		this.addBlock(RisusBlocks.TALL_HAIR, "Hair Strands");
		this.addBlock(RisusBlocks.TEETH, "Teeth");
		this.addBlock(RisusBlocks.TESSERACT, "Tesseract");
		this.addBlock(RisusBlocks.TISSUE, "Dying Tissue");
		this.addBlock(RisusBlocks.TISSUE_SLAB, "Tissue Slab");
		this.addBlock(RisusBlocks.TISSUE_STAIRS, "Tissue Stairs");
		this.addBlock(RisusBlocks.TISSUE_WALL, "Tissue Wall");
		this.addBlock(RisusBlocks.UNALLOYED_SCALEPLATE, "Unalloyed Scaleplate");
		this.addBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK, "Unalloyed Scales Block");
		this.addBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_STAIRS, "Unalloyed Scales Stairs");
		this.addBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_SLAB, "Unalloyed Scales Slab");
		this.addBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_WALL, "Unalloyed Scales Wall");
		this.addBlock(RisusBlocks.VEINS, "Veins");
		this.addBlock(RisusBlocks.VEINS_END, "Veins");
		this.addBlock(RisusBlocks.WAXED_COPPER_AMALGAM, "Waxed Copper Amalgam");
		this.addBlock(RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM, "Waxed Exposed Copper Amalgam");
		this.addBlock(RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM, "Waxed Oxidized Copper Amalgam");
		this.addBlock(RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM, "Waxed Weathered Copper Amalgam");
		this.addBlock(RisusBlocks.WEATHERED_COPPER_AMALGAM, "Weathered Copper Amalgam");
		this.addBlock(RisusBlocks.WEAVER_NEST, "Weaver Nest");
		this.addBlock(RisusBlocks.WEAVING_MECHANISM, "Weaving Mechanism");
		this.addBlock(RisusBlocks.ZIT, "Zit");

		for (DyeColor color : DyeColor.values()) {
			this.add("block.risus." + color.getName() + "_mosaic_glass", WordUtils.capitalize(color.getName().replace('_', ' ')) + " Mosaic Glass");
		}

		for (DyeColor color : DyeColor.values()) {
			this.add("block.risus." + color.getName() + "_mosaic_lamp", WordUtils.capitalize(color.getName().replace('_', ' ')) + " Mosaic Lamp");
		}

		for (DyeColor color : DyeColor.values()) {
			this.add("block.risus." + color.getName() + "_mosaic_glass_pane", WordUtils.capitalize(color.getName().replace('_', ' ')) + " Mosaic Glass Pane");
		}

		for (DyeColor color : DyeColor.values()) {
			if (color != DyeColor.BLACK) {
				this.add("block.risus." + color.getName() + "_display_notch", WordUtils.capitalize(color.getName().replace('_', ' ')) + " Display Notch");
			}
		}
		this.addBlock(RisusBlocks.INVISIBLE_DISPLAY_NOTCH, "Invisible Display Notch");

		this.add("risus.configuration.title", "Risus Config");
		this.add("risus.configuration.section.obtrophies.common.toml", "Common Settings");
		this.add("risus.configuration.section.obtrophies.common.toml.title", "Common Settings");

		this.add("config.risus.spinning_source.signal", "Redstone Signal");
		this.add("config.risus.spinning_source.torch_item", "Redstone Torch Right-click");

		this.addBannerPattern("smile", "Smile", DyeColor.RED);
		this.addBannerPattern("divinity", "Divinity", DyeColor.WHITE);
		this.addBannerPattern("tree", "Tree", DyeColor.GRAY);
		this.addBannerPattern("rose", "Rose", DyeColor.BLUE);
		this.add("block.minecraft.banner.risus.smile.red", "True Smile");
		this.add("block.minecraft.banner.risus.divinity.white", "Unified Divinity");
		this.add("block.minecraft.banner.risus.tree.gray", "Ashen Yggdrasil");
		this.add("block.minecraft.banner.risus.rose.blue", "Forgotten Rose");

		this.addMusicDisc(RisusItems.MUSIC_DISC_FEIGR, "Kizbe - Feigr");
		this.addMusicDisc(RisusItems.MUSIC_DISC_MORK, "Kizbe - Mǫrk");
		this.addMusicDisc(RisusItems.MUSIC_DISC_RAK, "Kizbe - Rak");
		this.addMusicDisc(RisusItems.MUSIC_DISC_REGN, "Kizbe - Regn");
		this.addMusicDisc(RisusItems.MUSIC_DISC_CYCLE, "Akiak - Cycle");

		this.addTrim("gluttony_scales", "Gluttonous");
		this.addTrim("skin", "Skin");
		this.addTrim("organic_matter", "Organic Matter");

		this.addItem(RisusItems.ANGEL_WINGS, "Ophanim Wings");
		this.addItem(RisusItems.BLOOD_BUCKET, "Blood Bucket");
		this.addItem(RisusItems.BLOOD_FEATHER, "Blood Feather");
		this.addItem(RisusItems.BLOODWYRM_HEAD_WEAPON, "False Bloodwyrm Spewer");
		this.addItem(RisusItems.BONDKNOT_BOAT, "Bondknot Boat");
		this.addItem(RisusItems.BOOMSTICK, "Boomstick");
		this.addItem(RisusItems.CINDERGLEE_SCYTHE, "Existential Executioner");
		this.addItem(RisusItems.CONCENTRATION_CORE, "Concentration Core");
		this.addItem(RisusItems.CRESCENT_DISASTER, "Crescent Disaster");
		this.addItem(RisusItems.CROWN_OF_BONES, "Ivory Crown");
		this.addItem(RisusItems.CRYSTALLIZED_BOND, "Crystallized Bond");
		this.addItem(RisusItems.ECHO_PEARL, "Echo Pearl");
		this.addItem(RisusItems.EGG_SAC, "Egg Sac");
		this.addItem(RisusItems.EMBODIMENT_OF_COURTSHIP, "Embodiment of Courtship");
		this.addItem(RisusItems.EMBODIMENT_OF_DEVOTION, "Embodiment of Devotion");
		this.addItem(RisusItems.EMBODIMENT_OF_INTIMACY, "Embodiment of Intimacy");
		this.addItem(RisusItems.EMBODIMENT_OF_LANGUISH, "Embodiment of Languish");
		this.addItem(RisusItems.ENDLESS_PEARL, "Endless Pearl");
		this.addItem(RisusItems.ESSENCE_OF_GLUTTONY, "Essence of Gluttony");
		this.addItem(RisusItems.ESSENCE_OF_GREED, "Essence of Greed");
		this.addItem(RisusItems.ESSENCE_OF_LUST, "Essence of Lust");
		this.addItem(RisusItems.ESSENCE_OF_MELANCHOLY, "Essence of Melancholy");
		this.addItem(RisusItems.ESSENCE_OF_SLOTH, "Essence of Sloth");
		this.addItem(RisusItems.ETERNAL_YOUTH, "Eternal Youth");
		this.addItem(RisusItems.EYE_SANDWICH, "Eye Sandwich");
		this.addItem(RisusItems.FIRE_SCYTHE, "Flame Fiend");
		this.addItem(RisusItems.GLUTTONY_SCALES, "Gluttonous Scales");
		this.addItem(RisusItems.GOLD_FIST, "Stripper");
		this.addItem(RisusItems.GUILTY_APPLE, "Guilty Apple");
		this.addItem(RisusItems.GUTS_BOAT, "Bondknot Boat with Gorger Guts");
		this.addItem(RisusItems.HAIR_FOLLICLES, "Hair Follicles");
		this.addItem(RisusItems.HAND_OF_GREED, "Hand of Greed");
		this.addItem(RisusItems.HEXHORN, "Hex Horn");
		this.addItem(RisusItems.KILLJOY, "Killjoy");
		this.addItem(RisusItems.LIGHT_DEVOURER, "Light Devourer");
		this.addItem(RisusItems.LITTER, "Litter");
		this.addItem(RisusItems.LOVER_CREAM, "Lover's Cream");
		this.addItem(RisusItems.LUCKY_CHARM, "Lucky Charm");
		this.addItem(RisusItems.MEMORY_CORE, "Memory Core");
		this.addItem(RisusItems.ORGANIC_MATTER, "Organic Matter");
		this.addItem(RisusItems.PURIFYING_PASTE, "Purifying Paste");
		this.addItem(RisusItems.RESEARCHERS_NOTES, "Researcher's Notes");
		this.addItem(RisusItems.ROSE_CROWN, "Crown Of Roses");
		this.addItem(RisusItems.ROSE_PETAL, "Rose Petal");
		this.addItem(RisusItems.SACRIFICE_CATALYST, "Sacrificial Catalyst");
		this.addItem(RisusItems.SCYTHE, "Unlit Vessel");
		this.addItem(RisusItems.SINNER_ROBES_HELMET, "Sinner's Robe Helmet");
		this.addItem(RisusItems.SINNER_ROBES_CHESTPLATE, "Sinner's Robe Chestplate");
		this.addItem(RisusItems.SINNER_ROBES_LEGGINGS, "Sinner's Robe Leggings");
		this.addItem(RisusItems.SINNER_ROBES_BOOTS, "Sinner's Robe Boots");
		this.addItem(RisusItems.SKIN_BOOTS, "Skin Socks");
		this.addItem(RisusItems.SKIN_CHESTPLATE, "Skin Flabs");
		this.addItem(RisusItems.SKIN_HELMET, "Skin Head");
		this.addItem(RisusItems.SKIN_LEGGINGS, "Skin Jeans");
		this.addItem(RisusItems.SOUL_SCYTHE, "Soul Scratcher");
		this.addItem(RisusItems.STALKER_EYE, "Stalker Eye");
		this.addItem(RisusItems.STITCHING_NEEDLE, "Stitching Needle");
		this.addItem(RisusItems.THOUSAND_BLADE, "Blade of a Thousand");
		this.addItem(RisusItems.THREADERS_OF_THE_FIRMAMENT, "Threads Of The Firmament");
		this.addItem(RisusItems.TOOTHKNOCKER, "Toothknocker");
		this.addItem(RisusItems.TOTEM_OF_UNYIELDING, "Totem of Unyielding");
		this.addItem(RisusItems.UNAWAKENED_VESSEL, "Unawakened Vessel");
		this.addItem(RisusItems.WARHORN, "War Horn");
		this.addItem(RisusItems.WRETCHED_CHARM, "Wretched Charm");

		this.add("item.minecraft.potion.effect.mating_frenzy", "Potion of Love");
		this.add("item.minecraft.splash_potion.effect.mating_frenzy", "Splash Potion of Love");
		this.add("item.minecraft.lingering_potion.effect.mating_frenzy", "Lingering Potion of Love");
		this.add("item.minecraft.tipped_arrow.effect.mating_frenzy", "Cupid's Arrow");

		this.add("item.minecraft.potion.effect.amnesia", "Potion of Forgetfulness");
		this.add("item.minecraft.splash_potion.effect.amnesia", "Splash Potion of Forgetfulness");
		this.add("item.minecraft.lingering_potion.effect.amnesia", "Lingering Potion of Forgetfulness");
		this.add("item.minecraft.tipped_arrow.effect.amnesia", "Arrow of Forgetfulness");

		this.add("item.minecraft.potion.effect.life_smouldering", "Potion of Life Smouldering");
		this.add("item.minecraft.potion.effect.long_life_smouldering", "Potion of Life Smouldering");
		this.add("item.minecraft.potion.effect.strong_life_smouldering", "Potion of Life Smouldering");
		this.add("item.minecraft.splash_potion.effect.long_life_smouldering", "Splash Potion of Life Smouldering");
		this.add("item.minecraft.splash_potion.effect.life_smouldering", "Splash Potion of Life Smouldering");
		this.add("item.minecraft.splash_potion.effect.strong_life_smouldering", "Splash Potion of Life Smouldering");
		this.add("item.minecraft.lingering_potion.effect.life_smouldering", "Lingering Potion of Life Smouldering");
		this.add("item.minecraft.lingering_potion.effect.long_life_smouldering", "Lingering Potion of Life Smouldering");
		this.add("item.minecraft.lingering_potion.effect.strong_life_smouldering", "Lingering Potion of Life Smouldering");
		this.add("item.minecraft.tipped_arrow.effect.life_smouldering", "Arrow of Life Smouldering");
		this.add("item.minecraft.tipped_arrow.effect.long_life_smouldering", "Arrow of Life Smouldering");
		this.add("item.minecraft.tipped_arrow.effect.strong_life_smouldering", "Arrow of Life Smouldering");

		this.add("item.minecraft.potion.effect.long_luck", "Potion of Luck");
		this.add("item.minecraft.potion.effect.strong_luck", "Potion of Luck");
		this.add("item.minecraft.splash_potion.effect.long_luck", "Splash Potion of Luck");
		this.add("item.minecraft.splash_potion.effect.strong_luck", "Splash Potion of Luck");
		this.add("item.minecraft.lingering_potion.effect.long_luck", "Lingering Potion of Luck");
		this.add("item.minecraft.lingering_potion.effect.strong_luck", "Lingering Potion of Luck");
		this.add("item.minecraft.tipped_arrow.effect.long_luck", "Arrow of Luck");
		this.add("item.minecraft.tipped_arrow.effect.strong_luck", "Arrow of Luck");

		this.add("item.minecraft.potion.effect.bad_luck", "Potion of Misfortune");
		this.add("item.minecraft.potion.effect.long_bad_luck", "Potion of Misfortune");
		this.add("item.minecraft.potion.effect.strong_bad_luck", "Potion of Misfortune");
		this.add("item.minecraft.splash_potion.effect.bad_luck", "Splash Potion of Misfortune");
		this.add("item.minecraft.splash_potion.effect.long_bad_luck", "Splash Potion of Misfortune");
		this.add("item.minecraft.splash_potion.effect.strong_bad_luck", "Splash Potion of Misfortune");
		this.add("item.minecraft.lingering_potion.effect.bad_luck", "Lingering Potion of Misfortune");
		this.add("item.minecraft.lingering_potion.effect.long_bad_luck", "Lingering Potion of Misfortune");
		this.add("item.minecraft.lingering_potion.effect.strong_bad_luck", "Lingering Potion of Misfortune");
		this.add("item.minecraft.tipped_arrow.effect.bad_luck", "Arrow of Misfortune");
		this.add("item.minecraft.tipped_arrow.effect.long_bad_luck", "Arrow of Misfortune");
		this.add("item.minecraft.tipped_arrow.effect.strong_bad_luck", "Arrow of Misfortune");

		this.add("item.minecraft.potion.effect.glowing", "Potion of Radiance");
		this.add("item.minecraft.potion.effect.long_glowing", "Potion of Radiance");
		this.add("item.minecraft.splash_potion.effect.glowing", "Splash Potion of Radiance");
		this.add("item.minecraft.splash_potion.effect.long_glowing", "Splash Potion of Radiance");
		this.add("item.minecraft.lingering_potion.effect.glowing", "Lingering Potion of Radiance");
		this.add("item.minecraft.lingering_potion.effect.long_glowing", "Lingering Potion of Radiance");
		this.add("item.minecraft.tipped_arrow.effect.glowing", "Arrow of Radiance");
		this.add("item.minecraft.tipped_arrow.effect.long_glowing", "Arrow of Radiance");

		this.add("item.minecraft.potion.effect.clotting", "Potion of Clotting");
		this.add("item.minecraft.potion.effect.long_clotting", "Potion of Clotting");
		this.add("item.minecraft.splash_potion.effect.clotting", "Splash Potion of Clotting");
		this.add("item.minecraft.splash_potion.effect.long_clotting", "Splash Potion of Clotting");
		this.add("item.minecraft.lingering_potion.effect.clotting", "Lingering Potion of Clotting");
		this.add("item.minecraft.lingering_potion.effect.long_clotting", "Lingering Potion of Clotting");
		this.add("item.minecraft.tipped_arrow.effect.clotting", "Arrow of Clotting");
		this.add("item.minecraft.tipped_arrow.effect.long_clotting", "Arrow of Clotting");

		this.add("item.minecraft.potion.effect.golden_glory", "Potion of Golden Glory");
		this.add("item.minecraft.splash_potion.effect.golden_glory", "Splash Potion of Golden Glory");
		this.add("item.minecraft.lingering_potion.effect.golden_glory", "Lingering Potion of Golden Glory");
		this.add("item.minecraft.tipped_arrow.effect.golden_glory", "Arrow of Golden Glory");

		this.add("item.minecraft.potion.effect.copper_age", "Potion of The Copper Age");
		this.add("item.minecraft.splash_potion.effect.copper_age", "Splash Potion of The Copper Age");
		this.add("item.minecraft.lingering_potion.effect.copper_age", "Lingering Potion of The Copper Age");
		this.add("item.minecraft.tipped_arrow.effect.copper_age", "Arrow of The Copper Age");

		this.add("tooltip.risus.gluttony_scales", "Harvested from the bodies of Gorgers.");
		this.add("tooltip.risus.blood_feather", "Obtained from Blood Ophanims.");
		this.add("tooltip.risus.memory_core", "Uncommonly dropped by Weavers.");
		this.add("tooltip.risus.hand_of_greed", "Uncommonly dropped by Holders.");
		this.add("tooltip.risus.crystallized_bond", "Found on Family Trees.");
		this.add("tooltip.risus.researchers_notes", "Carry in hotbar to access item lookup feature.");
		this.add("tooltip.risus.book_button_press", "Press the %s key to open while in the inventory.");
		this.add("tooltip.risus.book_button_press.outline", "[%s]");
		this.add("tooltip.risus.great_stool.button_press.outline", "[%s]");
		this.add("tooltip.risus.spyglass.button_press.outline", "[%s]");
		this.add("tooltip.risus.rose_crown", "Once painful, then sweet - yet now puzzling memories...");
		this.add("tooltip.risus.warhorn_dunk", "Right click with a Potion to fill it.");
		this.add("tooltip.risus.spawnentity.risus.maw", "Gorger");
		this.add("tooltip.risus.spawnentity.risus.angel", "Blood Ophanim");
		this.add("tooltip.risus.spawnentity.risus.weaver", "Weaver");
		this.add("tooltip.risus.spawnentity.risus.holder", "Holder");
		this.add("tooltip.risus.spawnentity.risus.hex", "Hex");
		this.add("tooltip.risus.spawnentity.risus.lover", "Lover");
		this.add("tooltip.risus.spawnentity.risus.singer", "Singer");
		this.add("tooltip.risus.spawnentity.risus.stalker", "Stalker");
		this.add("tooltip.risus.spawnentity.risus.licker", "Licker");
		this.add("keybind.researchers_notes_open", "Open Researcher's Notes");
		this.add("keybind.summon_greatness", "Summon Great Stool");
		this.add("keybind.spyglass_mode", "Toggle Zoom With Spyglass Ability");

		this.add("tooltip.risus.ability", "Selected Ability:");
		//don't forget, a space before
		this.add("tooltip.risus.sinner_robes_helmet.skeleton", " Skeleton Mask");
		this.add("tooltip.risus.sinner_robes_helmet.creeper", " Creeper Mask");
		this.add("tooltip.risus.sinner_robes_helmet.wither_skeleton", " Wither Skeleton Mask");
		this.add("tooltip.risus.sinner_robes_helmet.zombie", " Zombie Mask");
		this.add("tooltip.risus.sinner_robes_helmet.piglin", " Piglin Mask");
		this.add("tooltip.risus.sinner_robes_helmet.tuxedo_cat", " Tuxedo Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.black_cat", " Black Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.british_cat", " Shorthair Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.calico_cat", " Calico Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.jellie_cat", " Jellie Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.persian_cat", " Persian Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.ragdoll_cat", " Ragdoll Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.orange_cat", " Orange Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.siamese_cat", " Siamese Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.tabby_cat", " Tabby Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.white_cat", " White Cat Mask");
		this.add("tooltip.risus.sinner_robes_helmet.audrey_cat", " Audrey Mask");
		this.add("tooltip.risus.sinner_robes_helmet.spyglass", " Spyglass Goggles");
		this.add("tooltip.risus.sinner_robes_helmet.pumpkin", " Pumpkin Mask");
		this.add("tooltip.risus.sinner_robes_helmet.pale_wolf", " Pale Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.ashen_wolf", " Ashen Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.black_wolf", " Black Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.chestnut_wolf", " Chestnut Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.rusty_wolf", " Rusty Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.snowy_wolf", " Snowy Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.spotted_wolf", " Spotted Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.striped_wolf", " Striped Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.woods_wolf", " Woods Wolf Mask");
		this.add("tooltip.risus.sinner_robes_helmet.bleached_eye", " Bleached Eye Mask");
		this.add("tooltip.risus.sinner_robes_helmet.bloodshot_eye", " Bloodshot Eye Mask");
		this.add("tooltip.risus.sinner_robes_helmet.emerald_eye", " Emerald Eye Mask");
		this.add("tooltip.risus.sinner_robes_helmet.ender_eye", " Ender Eye Mask");
		this.add("tooltip.risus.sinner_robes_helmet.golden_eye", " Golden Eye Mask");
		this.add("tooltip.risus.sinner_robes_helmet.abyssal_eye", " Abyssal Eye Mask");
		this.add("tooltip.risus.sinner_robes_helmet.smile", " Smile Mask");
		this.add("tooltip.risus.sinner_robes_helmet.great_stool", " Great Stool");
		this.add("tooltip.risus.sinner_robes_helmet.fox", " Fox Mask");
		this.add("tooltip.risus.sinner_robes_helmet.snow_fox", " Snow Fox Mask");

		this.add("tooltip.risus.sinner_robes_helmet.skeleton.desc", " Less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.creeper.desc", " Less noticed by Creepers");
		this.add("tooltip.risus.sinner_robes_helmet.wither_skeleton.desc", " Less noticed by Wither Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.zombie.desc", " Less noticed by Zombies");
		this.add("tooltip.risus.sinner_robes_helmet.piglin.desc", " Piglins are Neutral");
		this.add("tooltip.risus.sinner_robes_helmet.tuxedo_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.black_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.british_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.calico_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.jellie_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.persian_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.ragdoll_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.orange_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.siamese_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.tabby_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.white_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.audrey_cat.desc", " Barely noticed by Phantoms");
		this.add("tooltip.risus.sinner_robes_helmet.pumpkin.desc", " Ignored by Endermen");
		this.add("tooltip.risus.sinner_robes_helmet.pale_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.ashen_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.black_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.chestnut_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.rusty_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.snowy_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.spotted_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.striped_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.woods_wolf.desc", " Slightly less noticed by Skeletons");
		this.add("tooltip.risus.sinner_robes_helmet.bleached_eye.desc", " Immune to Stalkers");
		this.add("tooltip.risus.sinner_robes_helmet.bloodshot_eye.desc", " Immune to Stalkers");
		this.add("tooltip.risus.sinner_robes_helmet.emerald_eye.desc", " Immune to Stalkers");
		this.add("tooltip.risus.sinner_robes_helmet.ender_eye.desc", " Immune to Stalkers");
		this.add("tooltip.risus.sinner_robes_helmet.golden_eye.desc", " Immune to Stalkers");
		this.add("tooltip.risus.sinner_robes_helmet.abyssal_eye.desc", " Less noticed by Offsprings");
		this.add("tooltip.risus.sinner_robes_helmet.smile.desc", " Less noticed by Offsprings");
		this.add("tooltip.risus.sinner_robes_helmet.fox.desc", " Less noticed by Rabbits");
		this.add("tooltip.risus.sinner_robes_helmet.snow_fox.desc", " Less noticed by Rabbits");
		this.add("tooltip.risus.sinner_robes_helmet.great_stool.button_press", " Press the %s key for Authority.");
		this.add("tooltip.risus.sinner_robes_helmet.spyglass.button_press", " Press the %s key to toggle Zoom.");

		this.add("tooltip.risus.sinner_robes_chestplate.guts", " Gorger Guts");
		this.add("tooltip.risus.sinner_robes_chestplate.great_stool", " Great Stool");
		this.add("tooltip.risus.sinner_robes_chestplate.hand_of_greed", " Hand of Greed");

		this.add("tooltip.risus.sinner_robes_chestplate.guts.desc", " Increased Pickup Range");
		this.add("tooltip.risus.sinner_robes_chestplate.hand_of_greed.desc", " Increased Block Interaction Range");
		this.add("tooltip.risus.sinner_robes_chestplate.great_stool.button_press", " Press the %s key for Superiority.");

		this.add("tooltip.risus.sinner_robes_leggings.great_stool", " Great Stool");
		this.add("tooltip.risus.sinner_robes_leggings.book", " Researcher's Notes");

		this.add("tooltip.risus.sinner_robes_leggings.book.button_press", " Press the %s key to open the book.");
		this.add("tooltip.risus.sinner_robes_leggings.book.button_press.outline", "[%s]");
		this.add("tooltip.risus.sinner_robes_leggings.great_stool.button_press", " Press the %s key for Ascension.");

		this.add("tooltip.risus.sinner_robes_boots.shadow_walker", " Shadow Walker");
		this.add("tooltip.risus.sinner_robes_boots.great_stool", " Great Stool");

		this.add("tooltip.risus.sinner_robes_boots.shadow_walker.desc", " Turns Darkness solid when crouching");
		this.add("tooltip.risus.sinner_robes_boots.great_stool.button_press", " Press the %s key for Dominance.");

		this.add("tooltip.risus.display_notch.hidden", "[Hold Shift for Usages]");
		this.add("tooltip.risus.display_notch.start", "Once an Item is inserted, you can manipulate it:");
		this.add("tooltip.risus.display_notch.pickaxe", " - Rotate the item with a Pickaxe");
		this.add("tooltip.risus.display_notch.axe", " - Flip the item between horizontal or vertical with an Axe");
		this.add("tooltip.risus.display_notch.glow_ink_sac", " - Make the item glow with a Glow Inc Sac");
		this.add("tooltip.risus.display_notch.dye", " - Color the notch with Dyes");
		this.add("tooltip.risus.display_notch.phantom_membrane", " - Make the notch non-solid with a Phantom Membrane");
		this.add("tooltip.risus.display_notch.tripwire_hook", " - Lock the notch with a Tripwire Hook");
		this.add("tooltip.risus.display_notch.glass", " - Make the notch invisible with a Glass Block");
		this.add("tooltip.risus.display_notch.signal", " - Make the item continuously rotate by powering it with a Redstone Signal");
		this.add("tooltip.risus.display_notch.shovel", " - Change the item's elevation using a Shovel");
		this.add("tooltip.risus.display_notch.redstone_torch", " - Make the item continuously rotate by using a Redstone Torch");

		this.add("lockable_trapdoor.locked", "Locked");
		this.add("lockable_trapdoor.unlocked", "Unlocked");

		this.addEntityAndEgg(RisusEntities.ANGEL, "Blood Ophanim");
		this.addEntityAndEgg(RisusEntities.BABY_SPIDER, "Baby Spider");
		this.addEntityAndEgg(RisusEntities.HEX, "Hex");
		this.addEntityAndEgg(RisusEntities.HOLDER, "Holder");
		this.addEntityAndEgg(RisusEntities.LICKER, "Licker");
		this.addEntityAndEgg(RisusEntities.LOVER, "Lover");
		this.addEntityAndEgg(RisusEntities.MAW, "Gorger");
		this.addEntityAndEgg(RisusEntities.QUESTION_MARK, "???");
		this.addEntityAndEgg(RisusEntities.SINGER, "Singer");
		this.addEntityAndEgg(RisusEntities.STALKER, "Stalker");
		this.addEntityAndEgg(RisusEntities.WEAVER, "Weaver");
		this.addEntityType(RisusEntities.BLOODSLASH, "Sanguine Slash");
		this.addEntityType(RisusEntities.ENDLESS_PEARL, "Endless Pearl");
		this.addEntityType(RisusEntities.BLOODWYRM_BREATH, "False Bloodwyrm Breath");
		this.addEntityType(RisusEntities.BOAT, "Boat");
		this.addEntityType(RisusEntities.EGG_SAC, "Thrown Egg Sac");
		this.addEntityType(RisusEntities.GUTS_BOAT, "Boat with Gorger Guts");
		this.addEntityType(RisusEntities.LITTER, "Litter");
		this.addEntityType(RisusEntities.GREAT_STOOL, "Great Stool");
		this.addEntityType(RisusEntities.THROWN_AXE, "Thrown Crescent Disaster");

		this.addEnchantment("hunters_exultation", "Hunter's Exultation", "Combines the effects of Smite, Bane of Arthropods and Impaling, but decreases damage against non-affected creatures.");
		this.addEnchantment("elemental_deviation", "Elemental Deviation", "Combines the effects of Fire, Blast and Projectile Protection, in return - if worn - weakening any Protection enchanted pieces.");
		this.addEnchantment("dream_eater", "Dream Eater", "Consumes the player's experience directly to repair an item, with half the effectiveness of Mending.");
		this.addEnchantment("pull", "Pull", "Pulls the target towards the attacker, with distance increasing the pulling power.");
		this.addEnchantment("denial", "Denial", "When the item breaks, it will murder its user and repair itself.");
		this.addEnchantment("cackling_craze", "Cackling Craze", "Increases damage, speed, knockback resistance and decreases max health while on Remains blocks.");
		this.addEnchantment("agony", "Agony", "Reflects the attacker's damage, boosted by the amount of health missing from the user. Whenever it triggers, it will also increase the damage taken by half.");
		this.addEnchantment("perpetuity", "Perpetuity", "On top of the Curse of Binding effect, the item will not be dropped upon death. To remove the item, you must break it.");
		this.addEnchantment("genocide", "Genocide", "Increases the area of effect of sweeping attacks.");
		this.addEnchantment("empyrean_conduit", "Empyrean Conduit", "Hitting enemies with a thrown trident will cause a lightning bolt to strike down from the sky on both the user and the target, regardless of weather.");
		this.addEnchantment("battering", "Battering", "Increases damage based on the armor points of the target and increases knockback.");
		this.addEnchantment("star_release", "Star Release", "Shoots eight projectiles out from the user in evenly distributed directions.");
		this.addEnchantment("overload", "Overload", "Triple the mining power of Efficiency at level five, but each level adds a chance for the drops to be destroyed.");
		this.addEnchantment("maritime_snare", "Maritime Snare", "Adds a small chance to fish out creatures, level increasing the likelihood and the size of the catch.");
		this.addEnchantment("gravity_well", "Gravity Well", "Fish bite faster than with Lure but there's a chance to pull in all entities nearby whenever retrieving a catch.");
		this.addEnchantment("relocation", "Relocation", "Adds a chance to relocate any durability damage received onto a different equipped item.");
		this.addEnchantment("pyromaniac", "Pyromania", "Increases speed when on fire.");
		this.addEnchantment("avaricious_ambit", "Avaricious Ambit", "Increases block interaction range and combat range, but decreases attack speed. Harvested drops are pulled to the user.");
		this.addEnchantment("preservation", "Preservation", "Adds a 50% chance not to use arrows. Extends beyond base arrows. Unaffected arrows can still be picked up.");
		this.addEnchantment("vigor", "Vigor", "Increases max health with each level.");
		this.addEnchantment("xenophobia", "Xenophobia", "Increases damage against creatures that aren't undead, insectoid or aquatic.");
		this.addEnchantment("defiance", "Defiance", "Increases projectile velocity and damage. Thrown Trident will glow.");
		this.addEnchantment("eruption", "Eruption", "If the user is on fire when it hits a block, the user will erupt at the location, setting everyone aflame.");
		this.addEnchantment("soar", "Soaring", "Sends the target up. Applies Slow Falling.");
		this.addEnchantment("fervour", "Fervour", "On hit, ignites the user and creatures around it.");
		this.addEnchantment("proliferation", "Proliferation", "When moving and crouching, turns soils and rocks below the user into Ashen and Smiling Remains respectively.");
		this.addEnchantment("hypersomnia", "Hypersomnia", "Makes the user fall asleep at night, with level determining how commonly.");

		SUBTITLE_GENERATOR.forEach(this::add);

		this.addDeathMessage("inexistence", "%1$s has stepped into inexistence");
		this.addDeathMessage("inexistence.player", "%1$s has stepped into inexistence while trying to escape %2$s");
		this.addDeathMessage("melancholy", "%1$s lost all their memories");
		this.addDeathMessage("melancholy.player", "%1$s's memories were weaved into Bloodweave");
		this.addDeathMessage("pleasure", "%1$s's body drowned in ecstasy");
		this.addDeathMessage("pleasure.player", "%1$s's body drowned in ecstasy while dancing with %2$s");
		this.addDeathMessage("gluttony", "%1$s was devoured");
		this.addDeathMessage("gluttony.player", "%1$s was devoured");
		this.addDeathMessage("gluttony.item", "%1$s was devoured");
		this.addDeathMessage("vampirism", "%1$s got sucked dry");
		this.addDeathMessage("vampirism.player", "%1$s got sucked dry while trying to escape %2$s");
		this.addDeathMessage("vampirism.item", "%1$s got sucked dry while trying to escape %2$s");
		this.addDeathMessage("revenge", "%1$s neglected their loyal friend");
		this.addDeathMessage("revenge.player", "%1$s neglected their loyal friend while trying to escape %2$s");
		this.addDeathMessage("revenge.item", "%1$s neglected their loyal friend while trying to escape %2$s");
		this.addDeathMessage("agony", "%1$s couldn't handle the agony they inflicted");
		this.addDeathMessage("agony.player", "%1$s couldn't handle the agony they inflicted while trying to escape %2$s");
		this.addDeathMessage("agony.item", "%1$s couldn't handle the agony they inflicted while trying to escape %2$s");
		this.addDeathMessage("bloodslash", "%1$s was cut in half");
		this.addDeathMessage("bloodslash.player", "%1$s was cut in half while trying to escape %2$s");
		this.addDeathMessage("bloodslash.item", "%1$s was cut in half while trying to escape %2$s using %3$s");
		this.addDeathMessage("axed", "%1$s had an axe thrown through their skull");
		this.addDeathMessage("axed.player", "%1$s had an axe thrown through their skull while trying to escape %2$s");
		this.addDeathMessage("axed.item", "%1$s had an axe thrown through their skull while trying to escape %2$s using %3$s");
		this.addDeathMessage("destined_death", "%1$s couldn't flee from their Destined Death");
		this.addDeathMessage("destined_death.player", "%1$s couldn't flee from their Destined Death while trying to escape %2$s");
		this.addDeathMessage("destined_death.item", "%1$s couldn't flee from their Destined Death while trying to escape %2$s using %3$s");

		this.addEffect(RisusMobEffects.AMNESIA, "Amnesia");
		this.addEffect(RisusMobEffects.BLOODCLOGGED, "Bloodclogged");
		this.addEffect(RisusMobEffects.DESTINED_DEATH, "Destined Death");
		this.addEffect(RisusMobEffects.EXBURN, "Existential Burn");
		this.addEffect(RisusMobEffects.FLAME_FRAILTY, "Flame Frailty");
		this.addEffect(RisusMobEffects.MATING_FRENZY, "Mating Frenzy");
		this.add("effect.risus.mating_frenzy.message", "You are incapable of Love");
		this.addEffect(RisusMobEffects.PLEASURE, "Pleasure");
		this.addEffect(RisusMobEffects.TOOTHLUSTER, "Toothluster");
		this.addEffect(RisusMobEffects.GREATNESS, "Greatness");
		this.addEffect(RisusMobEffects.GOLD_RUSH, "Gold Rush");
		this.addEffect(RisusMobEffects.VERDIGRIS_VEHEMENCE, "Verdigris Vehemence");

		this.addAdvancement("first", "Risus", "The Joyplague");
		this.addAdvancement("mod_book", "Researcher's Notes", "Alter a book to gain access to lost knowledge");
		this.addAdvancement("family", "Father...?", "Discover a Family Tree");
		this.addAdvancement("fleshing", "Did You Just Flesh Me?!", "Use Organic Matter on Tissue to stop the flesh from disintegrating");
		this.addAdvancement("step", "Watch Your Step!", "Wearing Leather Boots could save your life");
		this.addAdvancement("devour", "Devoured", "Fall into a Gorger's trap");
		this.addAdvancement("satiate", "Satiated", "Obtain the Gorger's Guts");
		this.addAdvancement("cupid", "Cupid", "Bring love to the world");
		this.addAdvancement("warlove", "Make Love, Not War", "Fill a Warhorn with a Potion of Love");
		this.addAdvancement("hornlove", "Horny Love", "Fill a Hexhorn with a Potion of Love");
		this.addAdvancement("irresistible", "Irresistible", "Taste Pleasure");
		this.addAdvancement("crusade", "Crusade", "Kill an Ophanim");
		this.addAdvancement("potential", "Potential", "Craft an Unawakened Vessel");
		this.addAdvancement("unleashed", "Unleashed", "Fill an Unawakened Vessel with divine remnants");
		this.addAdvancement("site_zero", "Site Zero", "Find an Alteration Site");
		this.addAdvancement("little", "You Little F-!", "Get attacked by a Holder");
		this.addAdvancement("great_body", "Cord Crawler", "Enter a Great Body");
		this.addAdvancement("rainbow", "Rainbow Destined To Burn", "Obtain all Scorched Scythes");
		this.addAdvancement("tight", "IT'S SO TIGHT!", "Obtain a set of Skin armor");
		this.addAdvancement("scythe", "It Craves Fire", "Obtain an Unlit Vessel");
		this.addAdvancement("thousand", "Wanted For Warcrimes", "Obtain the Blade of a Thousand by sacrificing a thousand people");
		this.addAdvancement("licked", "Mmmm, Tasty~", "Get licked!");
		this.addAdvancement("boomstick", "That's A Bad Idea...", "Obtain a Boomstick. Be careful with it...");
		this.addAdvancement("shave", "Smooth Operator", "Shave some Skin");
		this.addAdvancement("light_devourer", "False Genesis", "Obtain a Light Devourer, that can be used to place Darkness");
		this.addAdvancement("angel", "Hallowed Grounds", "Trespass onto land protected by an Ophanim.");
		this.addAdvancement("gluttony", "Gateway To A Corrupted Divinity", "Obtain Gluttonous Scales");
		this.addAdvancement("lab", "A Simulacrum Of Success", "Approach the recreation of the last experiment.");
		this.addAdvancement("homewrecker", "Homewrecker", "Destroy a Weaver Nest");
		this.addAdvancement("parentmode", "Parent Mode: Dedicated", "Witness the creation of a Weaver Nest through the self-willed death of a memory fed Weaver.");
		this.addAdvancement("knuckles", "Left-Right, Goodnight", "Obtain a Toothknocker. Equip one in each hand for better damage");
		this.addAdvancement("stripper", "Mine! Mine! Mine!", "Obtain the ability for non-consensual property acquisition");
		this.addAdvancement("revenge", "Who's Laughing Now, You Little F-?!", "Obtain a Hand of Greed");
		this.addAdvancement("cream", "WHY WOULD YOU SWALLOW THAT?!", "Drink some Lover's Cream");
		this.addAdvancement("church", "May God's Gaze Reach You", "Go to church.");

		this.addBiome(RisusBiomes.COALIFICATION, "Coalification");
		this.addBiome(RisusBiomes.COALIFICATION_FEIGR, "Coalification");
		this.addBiome(RisusBiomes.COALIFICATION_MORK, "Coalification");

		this.addStructure(RisusStructures.ALTERATION_SITE, "Alteration Site");
		this.addStructure(RisusStructures.ANGEL_ALTAR, "Angel Altar");
		this.addStructure(RisusStructures.BLOOD_WELL, "Blood Well");
		this.addStructure(RisusStructures.DRAXOLOTL_REMAINS, "Draxolotl Remains");
		this.addStructure(RisusStructures.DUNGEON, "Dungeon");
		this.addStructure(RisusStructures.ENDY_MAW, "End Gorger Trap");
		this.addStructure(RisusStructures.FAMILY_TREE, "Family Tree");
		this.addStructure(RisusStructures.FLOWER_FIELD, "Flower Field");
		this.addStructure(RisusStructures.GRASSY_MAW, "Grass Gorger Trap");
		this.addStructure(RisusStructures.GREAT_BODY, "Great Body");
		this.addStructure(RisusStructures.LAB_START, "Lab");
		this.addStructure(RisusStructures.SANDY_MAW, "Sand Gorger Trap");

		this.add("fluid_type.risus.blood_fluid", "Blood");
		this.add("container.risus.maw_guts", "Gorger Guts");
		this.add("itemGroup.risus.blocks", "Risus Blocks");
		this.add("itemGroup.risus.gear", "Risus Gear and Materials");
		this.add("itemGroup.risus.summoner", "Risus Entity Summoners");
		this.add("gui.risus.alteration_jei", "Alteration");
		this.add("rarity.risus.blood.name", "Blood");
		this.add("entity.risus.player_doesnt_own", "I don't like you");
		this.translateListOfStrings("entity.risus.thrown_axe.message",
			"I missed you",
			"I love the way you grip me",
			"Where am I...?",
			"I remember... You",
			"I hear them",
			"They are calling for me",
			"Beware the edge where the Old Witness crossed",
			"No one will believe you",
			"I yearn for more",
			"How many have I killed?",
			"I recall that pig...",
			"Let me feel your fingers on me",
			"Please hold me with both of your hands",
			"I adore the color of your blood",
			"Just a little more...",
			"Who's that behind you?",
			"Are you sure that you are alone?",
			"These lands have changed so much",
			"I feel the Birthplace trembling",
			"You didn't abandon me...?",
			"Till Death do us part",
			"I can sense my... Composer",
			"I love the skin you're in");
		//23
	}
}
