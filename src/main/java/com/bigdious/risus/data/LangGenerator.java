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
		this.addBlock(RisusBlocks.BABY_RIBCAGE, "Baby Ribcage");
		this.addBlock(RisusBlocks.BIG_CHAIN, "Big Chain");
		this.addBlock(RisusBlocks.BLOOD_CAULDRON, "Blood Cauldron");
		this.addBlock(RisusBlocks.BLOOD_FLUID_BLOCK, "Blood");
		this.addBlock(RisusBlocks.BLOODWEAVE, "Bloodweave");
		this.addBlock(RisusBlocks.BLOODWYRM_HEAD, "False Bloodwyrm Head");
		this.addBlock(RisusBlocks.BLOODY_SPONGE, "Bloody Sponge");
		this.addBlock(RisusBlocks.BOND_GLASS, "Crystallized Bond Glass");
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
		this.addBlock(RisusBlocks.BONE_SLAB, "Bone Slab");
		this.addBlock(RisusBlocks.BONE_STAIRS, "Bone Stairs");
		this.addBlock(RisusBlocks.BONE_WALL, "Bone Wall");
		this.addBlock(RisusBlocks.BUDDING_IMITATION_SCALEPLATE, "Budding Scaleplate");
		this.addBlock(RisusBlocks.BUNDLE_OF_HAIR, "Bundle of Hair");
		this.addBlock(RisusBlocks.BURNT_HYPHAE, "Burnt Hyphae");
		this.addBlock(RisusBlocks.CHISELED_GRIMSTONE, "Chiseled Grimstone");
		this.addBlock(RisusBlocks.COAGULATED_BLOOD_BLOCK, "Coagulated Blood Block");
		this.addBlock(RisusBlocks.CONTAINMENT_GLASS, "Containment Glass");
		this.addBlock(RisusBlocks.COPPER_AMALGAM, "Copper Amalgam");
		this.addBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS, "Cracked Grimstone Bricks");
		this.addBlock(RisusBlocks.CRYSTALLIZED_BONDS, "Crystallized Bonds");
		this.addBlock(RisusBlocks.CURVED_FLESHY_SKIN, "Curved Fleshy Skin");
		this.addBlock(RisusBlocks.CURVED_RITUAL_BLOCK, "Curved Ritual Block");
		this.addBlock(RisusBlocks.DARKNESS, "Darkness");
		this.addBlock(RisusBlocks.DECAYED_TISSUE, "Decayed Tissue");
		this.addBlock(RisusBlocks.DECAYING_TISSUE, "Decaying Tissue");
		this.addBlock(RisusBlocks.DECOMPOSED_TISSUE, "Decomposed Tissue");
		this.addBlock(RisusBlocks.DECOMPOSING_TISSUE, "Decomposing Tissue");
		this.addBlock(RisusBlocks.DEPTH_VASE, "Depth Vase");
		this.addBlock(RisusBlocks.DISPLAY_NOTCH, "Display Notch");
		this.addBlock(RisusBlocks.ENGRAVED_BASALT, "Engraved Basalt");
		this.addBlock(RisusBlocks.EXPOSED_COPPER_AMALGAM, "Exposed Copper Amalgam");
		this.addBlock(RisusBlocks.EYE_BLEACHED, "Bleached Eye Block");
		this.addBlock(RisusBlocks.EYE_BLOODSHOT, "Bloodshot Eye Block");
		this.addBlock(RisusBlocks.EYE_EMERALD, "Emerald Eye Block");
		this.addBlock(RisusBlocks.EYE_ENDER, "Ender Eye Block");
		this.addBlock(RisusBlocks.EYE_GOLDEN, "Golden Eye Block");
		this.addBlock(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK, "Imitation Scales Block");
		this.addBlock(RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS, "Imitation Scales Stairs");
		this.addBlock(RisusBlocks.IMITATION_SCALES_BLOCK_SLAB, "Imitation Scales Slab");
		this.addBlock(RisusBlocks.IMITATION_SCALES_BLOCK_WALL, "Imitation Scales Wall");
		this.addBlock(RisusBlocks.FLATTENED_SCALES_BLOCK, "Gluttonous Scales Block");
		this.addBlock(RisusBlocks.FLESHY_SKIN, "Fleshy Skin");
		this.addBlock(RisusBlocks.FLESHY_SPAWNER, "Fleshy Monster Spawner");
		this.addBlock(RisusBlocks.FLOWERING_IMITATION_SCALEPLATE, "Flowering Scaleplate");
		this.addBlock(RisusBlocks.FULL_BONE_BLOCK, "Full Bone Block");
		this.addBlock(RisusBlocks.FULL_BONE_SLAB, "Full Bone Slab");
		this.addBlock(RisusBlocks.FULL_BONE_STAIRS, "Full Bone Stairs");
		this.addBlock(RisusBlocks.GLUTTONY_SCALEPLATE, "Gluttonous Scaleplate");
		this.addBlock(RisusBlocks.GRIMSTONE, "Grimstone");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS, "Grimstone Bricks");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS_SLAB, "Grimstone Brick Slab");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS_STAIRS, "Grimstone Brick Stairs");
		this.addBlock(RisusBlocks.GRIMSTONE_BRICKS_WALL, "Grimstone Brick Wall");
		this.addBlock(RisusBlocks.GRIMSTONE_SLAB, "Grimstone Slab");
		this.addBlock(RisusBlocks.GRIMSTONE_STAIRS, "Grimstone Stairs");
		this.addBlock(RisusBlocks.GRIMSTONE_WALL, "Grimstone Wall");
		this.addBlock(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN, "Hairy Curved Fleshy Skin");
		this.addBlock(RisusBlocks.HAIRY_FLESHY_SKIN, "Hairy Fleshy Skin");
		this.addBlock(RisusBlocks.HAIRY_SKIN, "Hairy Skin");
		this.addBlock(RisusBlocks.HEART_TRANSPLANT, "Heart Transplant");
		this.addBlock(RisusBlocks.IMITATION_SCALEPLATE, "Imitation Scaleplate");
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
		this.addBlock(RisusBlocks.MIRAGE_END_STONE, "Mirage End Stone");
		this.addBlock(RisusBlocks.MIRAGE_GRASS_BLOCK, "Mirage Grass Block");
		this.addBlock(RisusBlocks.MIRAGE_NETHERRACK, "Mirage Netherrack");
		this.addBlock(RisusBlocks.MIRAGE_SAND, "Mirage Sand");
		this.addBlock(RisusBlocks.NEURON_HEAD, "Neuron");
		this.addBlock(RisusBlocks.NEURON_STEM, "Neuron Stem");
		this.addBlock(RisusBlocks.ORGANIC_MATTER_BLOCK, "Organic Matter Block");
		this.addBlock(RisusBlocks.OXIDIZED_COPPER_AMALGAM, "Oxidized Copper Amalgam");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE, "Polished Grimstone");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE_SLAB, "Polished Grimstone Slab");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE_STAIRS, "Polished Grimstone Stairs");
		this.addBlock(RisusBlocks.POLISHED_GRIMSTONE_WALL, "Polished Grimstone Wall");
		this.addBlock(RisusBlocks.POPPING_BONDKNOT_LOG, "Popping Bondknot Log");
		this.addBlock(RisusBlocks.POPPING_BONDKNOT_WOOD, "Popping Bondknot Wood");
		this.addBlock(RisusBlocks.POTTED_HEART_TRANSPLANT, "Potted Heart Transplant");
		this.addBlock(RisusBlocks.POTTED_REGEN_ROSE, "Pottered Regeneration Rose");
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
		this.addBlock(RisusBlocks.TISSUE, "Living Tissue");
		this.addBlock(RisusBlocks.TISSUE_SLAB, "Tissue Slab");
		this.addBlock(RisusBlocks.TISSUE_STAIRS, "Tissue Stairs");
		this.addBlock(RisusBlocks.VEINS, "Veins");
		this.addBlock(RisusBlocks.VEINS_END, "Veins");
		this.addBlock(RisusBlocks.WAXED_COPPER_AMALGAM, "Waxed Copper Amalgam");
		this.addBlock(RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM, "Waxed Exposed Copper Amalgam");
		this.addBlock(RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM, "Waxed Oxidized Copper Amalgam");
		this.addBlock(RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM, "Waxed Weathered Copper Amalgam");
		this.addBlock(RisusBlocks.WEATHERED_COPPER_AMALGAM, "Weathered Copper Amalgam");
		this.addBlock(RisusBlocks.WEAVER_NEST, "Weaver Nest");
		this.addBlock(RisusBlocks.ZIT, "Zit");

		for (DyeColor color : DyeColor.values()) {
			if (color != DyeColor.BLACK) {
				this.add("block.risus." + color.getName() + "_display_notch", WordUtils.capitalize(color.getName().replace('_', ' ')) + " Display Notch");
			}
		}

		this.addBannerPattern("smile", "Smile", DyeColor.RED);
		this.addBannerPattern("divinity", "Divinity", DyeColor.WHITE);
		this.addBannerPattern("tree", "Tree", DyeColor.GRAY);
		this.add("block.minecraft.banner.risus.smile.red", "True Smile");
		this.add("block.minecraft.banner.risus.divinity.white", "Unified Divinity");
		this.add("block.minecraft.banner.risus.tree.gray", "Ashen Yggdrasil");

		this.addMusicDisc(RisusItems.MUSIC_DISC_FEIGR, "Kizbe - Feigr");
		this.addMusicDisc(RisusItems.MUSIC_DISC_MORK, "Kizbe - Mǫrk");
		this.addMusicDisc(RisusItems.MUSIC_DISC_RAK, "Kizbe - Rak");
		this.addMusicDisc(RisusItems.MUSIC_DISC_REGN, "Kizbe - Regn");

		this.addTrim("gluttony", "Gluttony");
		this.addTrim("skin", "Skin");

		this.addItem(RisusItems.ANGEL_WINGS, "Ophanim Wings");
		this.addItem(RisusItems.BLOOD_BUCKET, "Blood Bucket");
		this.addItem(RisusItems.BLOOD_FEATHER, "Blood Feather");
		this.addItem(RisusItems.BLOODWYRM_HEAD_WEAPON, "False Bloodwyrm Spewer");
		this.addItem(RisusItems.BONDKNOT_BOAT, "Bondknot Boat");
		this.addItem(RisusItems.BOOMSTICK, "Boomstick");
		this.addItem(RisusItems.CINDERGLEE_SCYTHE, "Existential Executioner");
		this.addItem(RisusItems.CONCENTRATION_CORE, "Concentration Core");
		this.addItem(RisusItems.CRESCENT_DISASTER, "Crescent Disaster");
		this.addItem(RisusItems.CRYSTALLIZED_BOND, "Crystallized Bond");
		this.addItem(RisusItems.EGG_SAC, "Egg Sac");
		this.addItem(RisusItems.EMBODIMENT_OF_COURTSHIP, "Singer Icon");
		this.addItem(RisusItems.EMBODIMENT_OF_DEVOTION, "Stalker Icon");
		this.addItem(RisusItems.EMBODIMENT_OF_INTIMACY, "Licker Icon");
		this.addItem(RisusItems.ENDLESS_PEARL, "Endless Pearl");
		this.addItem(RisusItems.ESSENCE_OF_GLUTTONY, "Essence of Gluttony");
		this.addItem(RisusItems.ESSENCE_OF_GREED, "Essence of Greed");
		this.addItem(RisusItems.ESSENCE_OF_LUST, "Essence of Lust");
		this.addItem(RisusItems.ESSENCE_OF_MELANCHOLY, "Essence of Melancholy");
		this.addItem(RisusItems.ESSENCE_OF_SLOTH, "Essence of Sloth");
		this.addItem(RisusItems.EYE_SANDWICH, "Eye Sandwich");
		this.addItem(RisusItems.FIRE_SCYTHE, "Flame Fiend");
		this.addItem(RisusItems.GLUTTONY_SCALES, "Gluttonous Scales");
		this.addItem(RisusItems.GOLD_FIST, "Stripper");
		this.addItem(RisusItems.GUILTY_APPLE, "Guilty Apple");
		this.addItem(RisusItems.GUTS_BOAT, "Bondknot Boat with Gorger Guts");
		this.addItem(RisusItems.HAIR_FOLLICLES, "Hair Follicles");
		this.addItem(RisusItems.HAND_OF_GREED, "Hand of Greed");
		this.addItem(RisusItems.LIGHT_DEVOURER, "Light Devourer");
		this.addItem(RisusItems.LITTER, "Litter");
		this.addItem(RisusItems.MEMORY1_ITEM, "Faded Statue");
		this.addItem(RisusItems.MEMORY_CORE, "Memory Core");
		this.addItem(RisusItems.ORGANIC_MATTER, "Organic Matter");
		this.addItem(RisusItems.RESEARCHERS_NOTES, "Researcher's Notes");
		this.addItem(RisusItems.SACRIFICE_CATALYST, "Sacrificial Catalyst");
		this.addItem(RisusItems.SCYTHE, "Unlit Vessel");
		this.addItem(RisusItems.THREADERS_OF_THE_FIRMAMENT, "Threads Of The Firmament");
		this.addItem(RisusItems.SKIN_BOOTS, "Skin Socks");
		this.addItem(RisusItems.SKIN_CHESTPLATE, "Skin Flabs");
		this.addItem(RisusItems.SKIN_HELMET, "Skin Head");
		this.addItem(RisusItems.SKIN_LEGGINGS, "Skin Jeans");
		this.addItem(RisusItems.SMILE, "Smile");
		this.addItem(RisusItems.SOUL_SCYTHE, "Soul Scratcher");
		this.addItem(RisusItems.STALKER_EYE, "Stalker Eye");
		this.addItem(RisusItems.THOUSAND_BLADE, "Blade of a Thousand");
		this.addItem(RisusItems.TOOTHKNOCKER, "Toothknocker");
		this.addItem(RisusItems.TOTEM_OF_UNYIELDING, "Totem of Unyielding");
		this.addItem(RisusItems.UNAWAKENED_VESSEL, "Unawakened Vessel");

		this.add("item.minecraft.potion.effect.mating_frenzy", "Potion of Love");
		this.add("item.minecraft.splash_potion.effect.mating_frenzy", "Splash Potion of Love");
		this.add("item.minecraft.lingering_potion.effect.mating_frenzy", "Lingering Potion of Love");
		this.add("item.minecraft.tipped_arrow.effect.mating_frenzy", "Cupid's Arrow");

		this.add("tooltip.risus.gluttony_scales", "Harvested from the bodies of Gorgers.");
		this.add("tooltip.risus.blood_feather", "Obtained from Blood Ophanims.");
		this.add("tooltip.risus.memory_core", "Uncommonly dropped by Weavers.");
		this.add("tooltip.risus.hand_of_greed", "Uncommonly dropped by Holders.");
		this.add("tooltip.risus.crystallized_bond", "Found on Family Trees.");
		this.add("tooltip.risus.researchers_notes", "Carry in hotbar to access item lookup feature.");
		this.add("tooltip.risus.book_button_press", "Press the %s key to open while in the inventory.");
		this.add("tooltip.risus.book_button_press.outline", "[%s]");
		this.add("keybind.researchers_notes_open", "Open Researcher's Notes");

		this.addEntityAndEgg(RisusEntities.ANGEL, "Blood Ophanim");
		this.addEntityAndEgg(RisusEntities.BABY_SPIDER, "Baby Spider");
		this.addEntityAndEgg(RisusEntities.HOLDER, "Holder");
		this.addEntityAndEgg(RisusEntities.LICKER, "Licker");
		this.addEntityAndEgg(RisusEntities.LOVER, "Lover");
		this.addEntityAndEgg(RisusEntities.MAW, "Gorger");
		this.addEntityAndEgg(RisusEntities.QUESTION_MARK, "???");
		this.addEntityAndEgg(RisusEntities.SINGER, "Singer");
		this.addEntityAndEgg(RisusEntities.STALKER, "Stalker");
		this.addEntityAndEgg(RisusEntities.WEAVER, "Weaver");
		this.addEntityType(RisusEntities.BLOODSLASH, "Sanguine Slash");
		this.addEntityType(RisusEntities.BLOODWYRM_BREATH, "False Bloodwyrm Breath");
		this.addEntityType(RisusEntities.BOAT, "Boat");
		this.addEntityType(RisusEntities.EGG_SAC, "Thrown Egg Sac");
		this.addEntityType(RisusEntities.GUTS_BOAT, "Boat with Gorger Guts");
		this.addEntityType(RisusEntities.LITTER, "Litter");
		this.addEntityType(RisusEntities.MEMORY1, "Faded Statue");
		this.addEntityType(RisusEntities.THROWN_AXE, "Thrown Crescent Disaster");

		SUBTITLE_GENERATOR.forEach(this::add);

		this.addDeathMessage("inexistence", "%1$s has stepped into inexistence");
		this.addDeathMessage("inexistence.player", "%1$s has stepped into inexistence while trying to escape %2$s");
		this.addDeathMessage("melancholy", "%1$s lost all memories");
		this.addDeathMessage("melancholy.player", "%1$s's memories were weaved into Bloodweave");
		this.addDeathMessage("pleasure", "%1$s's body drowned in ecstasy");
		this.addDeathMessage("pleasure.player", "%1$s's body drowned in ecstasy while dancing with %2$s");
		this.addDeathMessage("gluttony", "%1$s was devoured");
		this.addDeathMessage("gluttony.player", "%1$s was devoured");
		this.addDeathMessage("vampirism", "%1$s got sucked dry");
		this.addDeathMessage("vampirism.player", "%1$s got sucked dry while trying to escape %2$s");
		this.addDeathMessage("vampirism.item", "%1$s got sucked dry while trying to escape %2$s");
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

		this.addAdvancement("first", "Risus", "The Joyplague");
		this.addAdvancement("mod_book", "Researcher's Notes", "Alter a book to gain access to lost knowledge");
		this.addAdvancement("family", "Father...?", "Discover a Family Tree");
		this.addAdvancement("fleshing", "Did You Just Flesh Me?!", "Use Organic Matter on Tissue to stop the flesh from disintegrating");
		this.addAdvancement("step", "Watch Your Step!", "Wearing Leather Boots could save your life");
		this.addAdvancement("devour", "Devoured", "Fall into a Gorger's trap");
		this.addAdvancement("satiate", "Satiated", "Obtain the Gorger's Guts");
		this.addAdvancement("cupid", "Cupid", "Bring love to the world");
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
		this.addAdvancement("licked", "Mmmm, Tasty~", "Get licked");
		this.addAdvancement("boomstick", "That's A Bad Idea...", "Obtain a Boomstick. Be careful with it.");
		this.addAdvancement("shave", "Smooth Operator", "Shave some Skin");
		this.addAdvancement("light_devourer", "False Genesis", "Obtain a Light Devourer that can be used to place Darkness");
		this.addAdvancement("angel", "Holy Grounds", "Trespass into the Altar of the Ophanim");
		this.addAdvancement("gluttony", "Gateway To Divinity", "Obtain Gluttony Scales");
		this.addAdvancement("lab", "Deep Core Fallout", "Approach a failed experiment");
		this.addAdvancement("homewrecker", "Homewrecker", "Destroy a Weaver Nest");
		this.addAdvancement("knuckles", "Left-Right, Goodnight", "Obtain a Toothknocker. Equip one in each hand for better damage");
		this.addAdvancement("stripper", "Mine! Mine! Mine!", "Obtain the ability for non-consensual property acquisition");
		this.addAdvancement("revenge", "Who's Laughing Now, You Little F-?!", "Obtain a Hand of Greed");

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
		this.add("itemGroup.risus.main", "Risus");
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
			"I can sense my... Composer");
	}
}
