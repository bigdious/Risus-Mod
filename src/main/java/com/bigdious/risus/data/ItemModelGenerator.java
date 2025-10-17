package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nullable;
import java.util.Objects;

public class ItemModelGenerator extends ItemModelProvider {

	public ItemModelGenerator(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//blocks
		toBlockModel(RisusBlocks.ALTERATION_CATALYST.get(), Risus.prefix("block/alteration_catalyst_inventory"));
		toBlock(RisusBlocks.ANGEL_ALTAR.get());
		toBlock(RisusBlocks.FLESHY_SPAWNER.get());
		toBlock(RisusBlocks.INACTIVE_HOLDER.get());
		toBlock(RisusBlocks.DISPLAY_NOTCH.get());
		toBlock(RisusBlocks.ORGANIC_MATTER_BLOCK.get());
		toBlock(RisusBlocks.COPPER_AMALGAM.get());
		toBlock(RisusBlocks.EXPOSED_COPPER_AMALGAM.get());
		toBlock(RisusBlocks.WEATHERED_COPPER_AMALGAM.get());
		toBlock(RisusBlocks.OXIDIZED_COPPER_AMALGAM.get());
		toBlock(RisusBlocks.OXIDIZED_COPPER_AMALGAM.get());
		toBlockModel(RisusBlocks.WAXED_COPPER_AMALGAM.get(), Risus.prefix("block/copper_amalgam"));
		toBlockModel(RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(), Risus.prefix("block/exposed_copper_amalgam"));
		toBlockModel(RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(), Risus.prefix("block/weathered_copper_amalgam"));
		toBlockModel(RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get(), Risus.prefix("block/oxidized_copper_amalgam"));
		toBlock(RisusBlocks.LAUGHING_STALK.get());
		toBlock(RisusBlocks.ASHEN_REMAINS.get());
		toBlockModel(RisusBlocks.SMILING_REMAINS.get(), Risus.prefix("block/smiling_remains/1"));
		toBlock(RisusBlocks.LAUGHING_OBSIDIAN.get());
		toBlock(RisusBlocks.ENGRAVED_BASALT.get());
		toBlock(RisusBlocks.LIGHT_EXCREMENT.get());
		toBlock(RisusBlocks.MAW_GUTS.get());
		toBlock(RisusBlocks.GLUTTONY_SCALEPLATE.get());
		toBlockModel(RisusBlocks.IMITATION_SCALEPLATE.get(), Risus.prefix("block/gluttony_scaleplate"));
		toBlock(RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get());
		toBlock(RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get());
		toBlock(RisusBlocks.UNALLOYED_SCALEPLATE.get());
		toBlock(RisusBlocks.FLOWERING_UNALLOYED_SCALEPLATE.get());
		toBlock(RisusBlocks.BUDDING_UNALLOYED_SCALEPLATE.get());
		toBlock(RisusBlocks.FLATTENED_SCALES_BLOCK.get());
		toBlockModel(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(), Risus.prefix("block/flattened_scales_block"));
		toBlock(RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS.get());
		toBlock(RisusBlocks.IMITATION_SCALES_BLOCK_SLAB.get());
		getBuilder(RisusBlocks.IMITATION_SCALES_BLOCK_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/flat_scales_block_side"));
		toBlockModel(RisusBlocks.UNALLOYED_SCALES_BLOCK.get(), Risus.prefix("block/unalloyed_scales_block"));
		toBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_STAIRS.get());
		toBlock(RisusBlocks.UNALLOYED_SCALES_BLOCK_SLAB.get());
		getBuilder(RisusBlocks.UNALLOYED_SCALES_BLOCK_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/unalloyed_scales/side"));
		toBlock(RisusBlocks.CRYSTALLIZED_BONDS.get());
		toBlock(RisusBlocks.MIRAGE_GRASS_BLOCK.get());
		toBlock(RisusBlocks.MIRAGE_SAND.get());
		toBlock(RisusBlocks.MIRAGE_NETHERRACK.get());
		toBlock(RisusBlocks.MIRAGE_END_STONE.get());
		toBlockModel(RisusBlocks.BLOODWEAVE.get(), Risus.prefix("block/bloodweave_core"));
		toBlock(RisusBlocks.SCAB.get());
		toBlock(RisusBlocks.COAGULATED_BLOOD_BLOCK.get());
		toBlock(RisusBlocks.WEAVER_NEST.get());
		toBlock(RisusBlocks.BOND_GLASS.get());
		toBlock(RisusBlocks.CONTAINMENT_GLASS.get());
		toBlock(RisusBlocks.BONDKNOT_LOG.get());
		toBlock(RisusBlocks.BONDKNOT_WOOD.get());
		toBlockModel(RisusBlocks.POPPING_BONDKNOT_LOG.get(), Risus.prefix("block/popping_bondknot_log_north"));
		toBlockModel(RisusBlocks.POPPING_BONDKNOT_WOOD.get(), Risus.prefix("block/popping_bondknot_wood_north"));
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
		toBlock(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
		toBlock(RisusBlocks.BONDKNOT_PLANKS.get());
		toBlock(RisusBlocks.BONDKNOT_SLAB.get());
		toBlock(RisusBlocks.BONDKNOT_STAIRS.get());
		toBlock(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		toBlock(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
		getBuilder(RisusBlocks.BONDKNOT_FENCE.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/fence_inventory")))
			.texture("texture", Risus.prefix("block/bondknot_planks"));
		getBuilder(RisusBlocks.BONDKNOT_BUTTON.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/button_inventory")))
			.texture("texture", Risus.prefix("block/bondknot_planks"));
		toBlockModel(RisusBlocks.BONDKNOT_TRAPDOOR.get(), Risus.prefix("block/bondknot_trapdoor_bottom"));
		generated(RisusBlocks.BONDKNOT_DOOR.getId().getPath(), Risus.prefix("item/bondknot_door"));
		generated(RisusBlocks.BONDKNOT_SIGN.getId().getPath(), Risus.prefix("item/bondknot_sign"));
		generated(RisusBlocks.BONDKNOT_HANGING_SIGN.getId().getPath(), Risus.prefix("item/bondknot_hanging_sign"));
		toBlock(RisusBlocks.BABY_RIBCAGE.get());
		toBlock(RisusBlocks.RIBCAGE.get());
		toBlock(RisusBlocks.GRIMSTONE.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.ACTIVE_GRIMSTONE.get());
		toBlock(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
		toBlock(RisusBlocks.GRIMSTONE_SLAB.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS_SLAB.get());
		toBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_SLAB.get());
		toBlock(RisusBlocks.POLISHED_GRIMSTONE_SLAB.get());
		toBlock(RisusBlocks.GRIMSTONE_STAIRS.get());
		toBlock(RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get());
		toBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES_STAIRS.get());
		toBlock(RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get());
		getBuilder(RisusBlocks.GRIMSTONE_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/grimstone"));
		getBuilder(RisusBlocks.GRIMSTONE_BRICKS_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/grimstone_bricks"));
		getBuilder(RisusBlocks.POLISHED_GRIMSTONE_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/polished_grimstone"));
		toBlock(RisusBlocks.CHISELED_GRIMSTONE.get());
		toBlock(RisusBlocks.LINEAR_RITUAL_BLOCK.get());
		toBlockModel(RisusBlocks.CURVED_RITUAL_BLOCK.get(), Risus.prefix("block/curved_ritual_block"));
		toBlock(RisusBlocks.POLISHED_GRIMSTONE.get());
		toBlock(RisusBlocks.GRIMSTONE_BLOOD_TILES.get());
		toBlock(RisusBlocks.BURNT_HYPHAE.get());
		toBlock(RisusBlocks.ZIT.get());
		toBlock(RisusBlocks.TISSUE.get());
		toBlock(RisusBlocks.GRILLED_TISSUE.get());
		toBlock(RisusBlocks.PEELED_GRILLED_TISSUE.get());
		toBlockModel(RisusBlocks.ROTTING_TISSUE.get(), Risus.prefix("block/tissue/rotting_tissue_post"));
		toBlockModel(RisusBlocks.DECOMPOSING_TISSUE.get(), Risus.prefix("block/tissue/decomposing_tissue_post"));
		toBlockModel(RisusBlocks.DECAYING_TISSUE.get(), Risus.prefix("block/tissue/decaying_tissue_post"));
		toBlock(RisusBlocks.LIVING_TISSUE.get());
		toBlockModel(RisusBlocks.ROTTED_TISSUE.get(), Risus.prefix("block/tissue/rotting_tissue_post"));
		toBlockModel(RisusBlocks.DECOMPOSED_TISSUE.get(), Risus.prefix("block/tissue/decomposing_tissue_post"));
		toBlockModel(RisusBlocks.DECAYED_TISSUE.get(), Risus.prefix("block/tissue/decaying_tissue_post"));
		toBlock(RisusBlocks.TISSUE_STAIRS.get());
		toBlock(RisusBlocks.TISSUE_SLAB.get());
		getBuilder(RisusBlocks.TISSUE_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/tissue"));
		getBuilder(RisusBlocks.BONE_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", ResourceLocation.withDefaultNamespace("block/bone_block_side"));
		getBuilder(RisusBlocks.FOSSIL_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", Risus.prefix("block/fossil_side"));
		toBlock(RisusBlocks.BONE_STAIRS.get());
		toBlock(RisusBlocks.BONE_SLAB.get());
		toBlock(RisusBlocks.FULL_BONE_STAIRS.get());
		toBlock(RisusBlocks.FULL_BONE_SLAB.get());
		toBlock(RisusBlocks.FULL_BONE_BLOCK.get());
		toBlock(RisusBlocks.BLOODY_SPONGE.get());
		toBlock(RisusBlocks.FOSSIL.get());
		toBlock(RisusBlocks.FOSSIL_STAIRS.get());
		toBlock(RisusBlocks.FOSSIL_SLAB.get());
		toBlock(RisusBlocks.FULL_FOSSIL_STAIRS.get());
		toBlock(RisusBlocks.FULL_FOSSIL_SLAB.get());
		toBlock(RisusBlocks.FULL_FOSSIL.get());
		toBlock(RisusBlocks.BLOODY_SPONGE.get());

		toBlockModel(RisusBlocks.EYE_ENDER.get(), Risus.prefix("block/eye/ender"));
		toBlockModel(RisusBlocks.EYE_BLEACHED.get(), Risus.prefix("block/eye/bleached"));
		toBlockModel(RisusBlocks.EYE_BLOODSHOT.get(), Risus.prefix("block/eye/bloodshot"));
		toBlockModel(RisusBlocks.EYE_EMERALD.get(), Risus.prefix("block/eye/emerald"));
		toBlockModel(RisusBlocks.EYE_GOLDEN.get(), Risus.prefix("block/eye/golden"));
		toBlockModel(RisusBlocks.EYE_ENDER_GLOWING.get(), Risus.prefix("block/eye/ender_glowing"));
		toBlockModel(RisusBlocks.EYE_BLEACHED_GLOWING.get(), Risus.prefix("block/eye/bleached_glowing"));
		toBlockModel(RisusBlocks.EYE_BLOODSHOT_GLOWING.get(), Risus.prefix("block/eye/bloodshot_glowing"));
		toBlockModel(RisusBlocks.EYE_EMERALD_GLOWING.get(), Risus.prefix("block/eye/emerald_glowing"));
		toBlockModel(RisusBlocks.EYE_GOLDEN_GLOWING.get(), Risus.prefix("block/eye/golden_glowing"));
		toBlock(RisusBlocks.SKIN.get());
		toBlock(RisusBlocks.FLESHY_SKIN.get());
		toBlock(RisusBlocks.CURVED_FLESHY_SKIN.get());
		toBlock(RisusBlocks.HAIRY_SKIN.get());
		toBlock(RisusBlocks.HAIRY_FLESHY_SKIN.get());
		toBlock(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get());
		toBlock(RisusBlocks.TALL_HAIR.get());
		toBlock(RisusBlocks.BUNDLE_OF_HAIR.get());
		getBuilder(RisusBlocks.BONE_WALL.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/wall_inventory")))
			.texture("wall", ResourceLocation.withDefaultNamespace("block/bone_block_side"));

		//items
		singleTex(RisusItems.RESEARCHERS_NOTES);
		singleTex(RisusItems.MUSIC_DISC_RAK);
		singleTex(RisusItems.MUSIC_DISC_REGN);
		singleTex(RisusItems.MUSIC_DISC_FEIGR);
		singleTex(RisusItems.MUSIC_DISC_MORK);
		singleTex(RisusItems.ANGEL_WINGS)
			.override().predicate(Risus.prefix("broken"), 1.0F).model(singleTex("angel_wings_broken")).end()
			.override().predicate(Risus.prefix("ashen"), 1.0F).model(singleTex("ashen_wings")).end()
			.override().predicate(Risus.prefix("ashen"), 1.0F).predicate(Risus.prefix("broken"), 1.0F).model(singleTex("ashen_wings_broken")).end()
			.override().predicate(Risus.prefix("oily"), 1.0F).model(singleTex("oily_wings")).end()
			.override().predicate(Risus.prefix("oily"), 1.0F).predicate(Risus.prefix("broken"), 1.0F).model(singleTex("oily_wings_broken"));
		singleTex(RisusItems.ESSENCE_OF_GLUTTONY);
		singleTex(RisusItems.ESSENCE_OF_GREED);
		singleTex(RisusItems.ESSENCE_OF_SLOTH);
		singleTex(RisusItems.ESSENCE_OF_LUST);
		singleTex(RisusItems.ESSENCE_OF_MELANCHOLY);
		singleTex(RisusItems.EMBODIMENT_OF_COURTSHIP);
		singleTex(RisusItems.EMBODIMENT_OF_INTIMACY);
		singleTex(RisusItems.EMBODIMENT_OF_DEVOTION);
		singleTex(RisusItems.EMBODIMENT_OF_LANGUISH);
		singleTex(RisusItems.ETERNAL_YOUTH);
		singleTex(RisusItems.BLOOD_FEATHER);
		singleTex(RisusItems.BLOOD_BUCKET);
		singleTex(RisusItems.BONDKNOT_BOAT);
		singleTex(RisusItems.GUTS_BOAT);
		singleTex(RisusItems.CONCENTRATION_CORE);
		singleTex(RisusItems.CRYSTALLIZED_BOND);
		singleTex(RisusItems.GLUTTONY_SCALES);
		singleTex(RisusItems.GUILTY_APPLE);
		singleTex(RisusItems.MEMORY_CORE);
		singleTex(RisusItems.ORGANIC_MATTER);
		trimmedArmor(RisusItems.SKIN_HELMET);
		trimmedArmor(RisusItems.SKIN_CHESTPLATE);
		trimmedArmor(RisusItems.SKIN_LEGGINGS);
		trimmedArmor(RisusItems.SKIN_BOOTS);
		trimmedArmor(RisusItems.THREADERS_OF_THE_FIRMAMENT);
		trimmedArmor(RisusItems.ROSE_CROWN);
		trimmedArmor(RisusItems.CROWN_OF_BONES);
		trimmedLayeredArmor(RisusItems.SINNER_ROBES_HELMET);
		trimmedLayeredArmor(RisusItems.SINNER_ROBES_CHESTPLATE);
		trimmedLayeredArmor(RisusItems.SINNER_ROBES_LEGGINGS);
		trimmedLayeredArmor(RisusItems.SINNER_ROBES_BOOTS);
		singleTex(RisusItems.ROSE_PETAL);
		singleTex(RisusItems.SMILE_PATTERN);
		singleTex(RisusItems.DIVINITY_PATTERN);
		singleTex(RisusItems.TREE_PATTERN);
		singleTex(RisusItems.ROSE_PATTERN);
		singleTex(RisusItems.STALKER_EYE);
		singleTex(RisusItems.EYE_SANDWICH);
		singleTex(RisusItems.EGG_SAC);
		singleTex(RisusItems.SACRIFICE_CATALYST);
		singleTex(RisusItems.TOTEM_OF_UNYIELDING);
		singleTex(RisusItems.LOVER_CREAM);
		singleTex(RisusItems.PURIFYING_PASTE);
		singleTex(RisusItems.ECHO_PEARL);
		singleTex(RisusItems.LUCKY_CHARM);
		singleTex(RisusItems.WRETCHED_CHARM);
		singleTex(RisusItems.STITCHING_NEEDLE);

		var warhornFilled = withExistingParent("warhorn_2", Risus.prefix("item/default_warhorn")).texture("under_texture", "item/warhorn_1").texture("over_texture", "item/warhorn");
		var warhornFilledTooting = withExistingParent("warhorn_3", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "item/warhorn_1").texture("over_texture", "item/warhorn");
		var warhornTooting = withExistingParent("warhorn_1", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/warhorn");
		var warhornFilledActive = withExistingParent("warhorn_4", Risus.prefix("item/default_warhorn")).texture("under_texture", "item/warhorn_1").texture("over_texture", "item/warhorn_active");
		var warhornFilledTootingActive = withExistingParent("warhorn_5", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "item/warhorn_1").texture("over_texture", "item/warhorn_active");
		var warhornTootingActive = withExistingParent("warhorn_6", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/warhorn_active");
		var warhornActive = withExistingParent("warhorn_7", Risus.prefix("item/default_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/warhorn_active");
		withExistingParent(RisusItems.WARHORN.getId().getPath(), Risus.prefix("item/default_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/warhorn")
			.override().predicate(Risus.prefix("filled"), 1).model(warhornFilled).end()
			.override().predicate(Risus.prefix("toot"), 1).model(warhornTooting).end()
			.override().predicate(Risus.prefix("active"), 1).model(warhornActive).end()
			.override().predicate(Risus.prefix("filled"), 1).predicate(Risus.prefix("active"), 1).model(warhornFilledActive).end()
			.override().predicate(Risus.prefix("toot"), 1).predicate(Risus.prefix("active"), 1).model(warhornTootingActive).end()
			.override().predicate(Risus.prefix("toot"), 1).predicate(Risus.prefix("filled"), 1).model(warhornFilledTooting).end()
			.override().predicate(Risus.prefix("toot"), 1).predicate(Risus.prefix("filled"), 1).predicate(Risus.prefix("active"), 1).model(warhornFilledTootingActive).end();

		var hexhornFilled = withExistingParent("hexhorn_2", Risus.prefix("item/default_warhorn")).texture("under_texture", "item/hexhorn_1").texture("over_texture", "item/hexhorn");
		var hexhornFilledTooting = withExistingParent("hexhorn_3", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "item/hexhorn_1").texture("over_texture", "item/hexhorn");
		var hexhornTooting = withExistingParent("hexhorn_1", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/hexhorn");
		var hexhornFilledActive = withExistingParent("hexhorn_4", Risus.prefix("item/default_warhorn")).texture("under_texture", "item/hexhorn_1").texture("over_texture", "item/hexhorn_active");
		var hexhornFilledTootingActive = withExistingParent("hexhorn_5", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "item/hexhorn_1").texture("over_texture", "item/hexhorn_active");
		var hexhornTootingActive = withExistingParent("hexhorn_6", Risus.prefix("item/default_tooting_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/hexhorn_active");
		var hexhornActive = withExistingParent("hexhorn_7", Risus.prefix("item/default_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/hexhorn_active");
		withExistingParent(RisusItems.HEXHORN.getId().getPath(), Risus.prefix("item/default_warhorn")).texture("under_texture", "block/no_texture").texture("over_texture", "item/hexhorn")
			.override().predicate(Risus.prefix("filled"), 1).model(hexhornFilled).end()
			.override().predicate(Risus.prefix("toot"), 1).model(hexhornTooting).end()
			.override().predicate(Risus.prefix("active"), 1).model(hexhornActive).end()
			.override().predicate(Risus.prefix("filled"), 1).predicate(Risus.prefix("active"), 1).model(hexhornFilledActive).end()
			.override().predicate(Risus.prefix("toot"), 1).predicate(Risus.prefix("active"), 1).model(hexhornTootingActive).end()
			.override().predicate(Risus.prefix("toot"), 1).predicate(Risus.prefix("filled"), 1).model(hexhornFilledTooting).end()
			.override().predicate(Risus.prefix("toot"), 1).predicate(Risus.prefix("filled"), 1).predicate(Risus.prefix("active"), 1).model(hexhornFilledTootingActive).end();

		var crescentNamed = handheldItem(RisusItems.CRESCENT_DISASTER, "_named", getExistingFile(Risus.prefix("item/base_axe_model")), Risus.prefix("item/croissant_disaster"), Risus.prefix("item/croissant_disaster_item"), "axe");
		var crescentCharged = handheldItem(RisusItems.CRESCENT_DISASTER, "_charged", getExistingFile(Risus.prefix("item/base_axe_model")), Risus.prefix("item/crescent_disaster_pulled"), Risus.prefix("item/crescent_disaster_item_pulled"), "axe");
		var croissantCharged = handheldItem(RisusItems.CRESCENT_DISASTER, "_named_charged", getExistingFile(Risus.prefix("item/base_axe_model")), Risus.prefix("item/croissant_disaster_pulled"), Risus.prefix("item/croissant_disaster_item_pulled"), "axe");
		handheldItem(RisusItems.CRESCENT_DISASTER, getExistingFile(Risus.prefix("item/base_axe_model")), Risus.prefix("item/crescent_disaster"), Risus.prefix("item/crescent_disaster_item"), "axe")
			.override().predicate(Risus.prefix("croissant"), 1).model(crescentNamed).end()
			.override().predicate(Risus.prefix("charged"), 1).model(crescentCharged).end()
			.override().predicate(Risus.prefix("charged"), 1).predicate(Risus.prefix("croissant"), 1).model(croissantCharged).end();

		handheldItem(RisusItems.BOOMSTICK, getExistingFile(Risus.prefix("item/boomstick_held")), Risus.prefix("item/boomstick"), Risus.prefix("item/boomstick_item"), "boomstick");

		var noAnimScythe = handheldItem(RisusItems.SCYTHE,"_noanim", withExistingParent("noanim_scythe_held", Risus.prefix("item/template_held_scythe_no_anim")), Risus.prefix("item/scythe"), Risus.prefix("item/scythe_item"), "texture");
		handheldItem(RisusItems.SCYTHE, withExistingParent("scythe_held", Risus.prefix("item/template_held_scythe")), Risus.prefix("item/scythe"), Risus.prefix("item/scythe_item"), "texture")
			.override().predicate(Risus.prefix("noanim"), 1).model(noAnimScythe).end();

		var noAnimSoulScythe = handheldItem(RisusItems.SOUL_SCYTHE,"_noanim", withExistingParent("noanim_soul_scythe_held", Risus.prefix("item/template_held_scythe_no_anim")), Risus.prefix("item/soul_scythe"), Risus.prefix("item/soul_scythe_item"), "texture");
		handheldItem(RisusItems.SOUL_SCYTHE, withExistingParent("soul_scythe_held", Risus.prefix("item/template_held_scythe")), Risus.prefix("item/soul_scythe"), Risus.prefix("item/soul_scythe_item"), "texture")
			.override().predicate(Risus.prefix("noanim"), 1).model(noAnimSoulScythe).end();

		var noAnimFireScythe = handheldItem(RisusItems.FIRE_SCYTHE, "_noanim",withExistingParent("noanim_fire_scythe_held", Risus.prefix("item/template_held_scythe_no_anim")), Risus.prefix("item/fire_scythe"), Risus.prefix("item/fire_scythe_item"), "texture");
		handheldItem(RisusItems.FIRE_SCYTHE, withExistingParent("fire_scythe_held", Risus.prefix("item/template_held_scythe")), Risus.prefix("item/fire_scythe"), Risus.prefix("item/fire_scythe_item"), "texture")
			.override().predicate(Risus.prefix("noanim"), 1).model(noAnimFireScythe).end();

		var noAnimCindergleeScythe = handheldItem(RisusItems.CINDERGLEE_SCYTHE, "_noanim",withExistingParent("noanim_cinderglee_scythe_held", Risus.prefix("item/template_held_scythe_no_anim")), Risus.prefix("item/cinderglee_scythe"), Risus.prefix("item/cinderglee_scythe_item"), "texture");
		handheldItem(RisusItems.CINDERGLEE_SCYTHE, withExistingParent("cinderglee_scythe_held", Risus.prefix("item/template_held_scythe")), Risus.prefix("item/cinderglee_scythe"), Risus.prefix("item/cinderglee_scythe_item"), "texture")
			.override().predicate(Risus.prefix("noanim"), 1).model(noAnimCindergleeScythe).end();

		var bladeCharged = handheldItem(RisusItems.THOUSAND_BLADE, "_charged", getExistingFile(Risus.prefix("item/thousand_blade_held")), Risus.prefix("item/intact_thousand_blade_pulled"), Risus.prefix("item/thousand_blade_item_pulled"), "thousand_blade");
		handheldItem(RisusItems.THOUSAND_BLADE, getExistingFile(Risus.prefix("item/thousand_blade_held")), Risus.prefix("item/intact_thousand_blade"), Risus.prefix("item/thousand_blade_item"), "thousand_blade")
			.override().predicate(Risus.prefix("charged"), 1).model(bladeCharged).end();
		handheldItem(RisusItems.UNAWAKENED_VESSEL, getExistingFile(Risus.prefix("item/base_axe_model")), Risus.prefix("entity/unawakened_vessel"), Risus.prefix("item/unawakened_vessel"), "axe");
		handheldItem(RisusItems.HAND_OF_GREED, getExistingFile(Risus.prefix("item/hand_of_greed_held")), Risus.prefix("item/hand_of_greed"), Risus.prefix("item/hand_of_greed_item"), "hand_of_greed");
		handheldItem(RisusItems.TOOTHKNOCKER, getExistingFile(Risus.prefix("item/toothknocker_held")), Risus.prefix("item/toothknocker"), Risus.prefix("item/toothknocker_item"), "texture");
		handheldItem(RisusItems.GOLD_FIST, getExistingFile(Risus.prefix("item/gold_fist_held")), Risus.prefix("item/gold_fist"), Risus.prefix("item/gold_fist_item"), "texture");
		handheldItem(RisusItems.KILLJOY, getExistingFile(Risus.prefix("item/killjoy_held")), Risus.prefix("item/killjoy"), Risus.prefix("item/killjoy_item"), "texture");


		singleTex(RisusItems.SMILE);
		singleTex(RisusBlocks.JOYFLAME_CAMPFIRE);
		singleTex(RisusBlocks.JOYFLAME_LANTERN);
		singleTex(RisusItems.JOYFLAME_TORCH);
		singleTex(RisusBlocks.HEART_TRANSPLANT);
		singleTex(RisusItems.VEINS);
		singleTex(RisusBlocks.BIG_CHAIN);
		generatedRenderType(RisusBlocks.SPREADING_REMAINS.getId().getPath(), "minecraft:translucent", Risus.prefix("item/spreading_remains"));
		generatedRenderType(RisusBlocks.ASHEN_SPIRE.getId().getPath(), "minecraft:cutout", Risus.prefix("block/ashen_spire_top"));
		singleTex(RisusItems.MEMORY1_ITEM);
		singleTex(RisusBlocks.REGEN_ROSE);
		singleTex(RisusItems.LIGHT_DEVOURER);
		singleTex(RisusItems.ENDLESS_PEARL);
		singleTex(RisusBlocks.TEETH);

		singleTex(RisusBlocks.NEURON_HEAD.asItem());
		singleTex(RisusItems.HAIR_FOLLICLES);


		//spawn eggs
		for (DeferredHolder<Item, ?> item : RisusEntities.SPAWN_EGGS.getEntries()) {
			if (item.get() instanceof SpawnEggItem) {
				getBuilder(item.getId().getPath()).parent(getExistingFile(ResourceLocation.withDefaultNamespace("item/template_spawn_egg")));
			}
		}

	}

	private void toBlock(Block b) {
		toBlockModel(b, Risus.prefix("block/" + Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath()));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath(), model);
	}

	private ItemModelBuilder generated(String name, String parent, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		return generated(name, "item/generated", layers);
	}

	private ItemModelBuilder singleTex(String texture) {
		return generated(texture, "item/generated", Risus.prefix("item/" + texture));
	}

	private ItemModelBuilder singleTex(ItemLike item) {
		return generated(BuiltInRegistries.ITEM.getKey(item.asItem()).getPath(), "item/generated", Risus.prefix("item/" + BuiltInRegistries.ITEM.getKey(item.asItem()).getPath()));
	}

	private ItemModelBuilder singleTexTool(DeferredHolder<Item, Item> item) {
		return generated(item.getId().getPath(), "item/handheld", Risus.prefix("item/" + item.getId().getPath()));
	}

	private ItemModelBuilder generatedRenderType(String name, @Nullable String renderType, ResourceLocation... layers) {
		return buildItem(name, "item/generated", renderType, layers);
	}

	private ItemModelBuilder buildItem(String name, String parent, @Nullable String renderType, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (renderType != null) {
			builder = builder.renderType(renderType);
		}
		return builder;
	}

	//from Aether
	public String itemName(Item item) {
		ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);
		if (location != null) {
			return location.getPath();
		} else {
			throw new IllegalStateException("Unknown item: " + item.toString());
		}
	}

	private void trimmedArmor(DeferredItem<ArmorItem> armor) {
		ItemModelBuilder base = this.singleTex(armor);
		for (ItemModelGenerators.TrimModelData trim : ItemModelGenerators.GENERATED_TRIM_MODELS) {
			String material = trim.name();
			String name = armor.getId().getPath() + "_" + material + "_trim";
			ModelFile trimModel = this.withExistingParent(name, this.mcLoc("item/generated"))
				.texture("layer0", Risus.prefix("item/" + armor.getId().getPath()))
				.texture("layer1", this.mcLoc("trims/items/" + armor.get().getType().getName() + "_trim_" + material));
			base.override().predicate(ResourceLocation.withDefaultNamespace("trim_type"), trim.itemModelIndex()).model(trimModel).end();
		}
	}

	private ItemModelBuilder handheldItem(DeferredItem<Item> item, ModelFile heldModel, ResourceLocation heldTexture, ResourceLocation itemTexture, String textureName) {
		return handheldItem(item, "", heldModel, heldTexture, itemTexture, textureName);
	}

	private ItemModelBuilder handheldItem(DeferredItem<Item> item, String suffix, ModelFile heldModel, ResourceLocation heldTexture, ResourceLocation itemTexture, String textureName) {
		ItemModelBuilder heldVersion = nested().parent(heldModel).texture(textureName, heldTexture);
		return withExistingParent(item.getId().getPath() + suffix, "item/handheld").customLoader(SeparateTransformsModelBuilder::begin)
			.base(generated(item.getId() + suffix + "_base", itemTexture))
			.perspective(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, heldVersion)
			.perspective(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, heldVersion)
			.perspective(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, heldVersion)
			.perspective(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, heldVersion)
			.perspective(ItemDisplayContext.HEAD, heldVersion).end();
	}

	private void trimmedLayeredArmor(DeferredHolder<Item, ArmorItem> armor) {
		ItemModelBuilder base = this.generated(armor.getId().getPath(), Risus.prefix("item/" + armor.getId().getPath()), Risus.prefix("item/" + armor.getId().getPath() + "_0"));
		var baseUpgraded = this.generated(armor.getId().getPath() + "_upgraded", Risus.prefix("item/" + armor.getId().getPath() + "_upgraded"), Risus.prefix("item/" + armor.getId().getPath() + "_0"));
		var trueBase = base.override().predicate(Risus.prefix("upgraded"), 1).model(baseUpgraded).end();
		for (ItemModelGenerators.TrimModelData trim : ItemModelGenerators.GENERATED_TRIM_MODELS) {
			String material = trim.name();
			String name = armor.getId().getPath() + "_" + material + "_trim";
			String nameUpgraded = armor.getId().getPath() + "_upgraded" +"_" + material + "_trim";
			var trimModel = this.withExistingParent(name, this.mcLoc("item/generated"))
				.texture("layer0", Risus.prefix("item/" + armor.getId().getPath()))
				.texture("layer1", Risus.prefix("item/" + armor.getId().getPath() + "_0"))
				.texture("layer2", this.mcLoc("trims/items/" + armor.get().getType().getName() + "_trim_" + material));
			var trimUpgradedModel = this.withExistingParent(nameUpgraded, this.mcLoc("item/generated"))
				.texture("layer0", Risus.prefix("item/" + armor.getId().getPath() + "_upgraded"))
				.texture("layer1", Risus.prefix("item/" + armor.getId().getPath() + "_0"))
				.texture("layer2", this.mcLoc("trims/items/" + armor.get().getType().getName() + "_trim_" + material));
			trueBase.override().predicate(ResourceLocation.withDefaultNamespace("trim_type"), trim.itemModelIndex()).model(trimModel).end()
				.override().predicate(Risus.prefix("upgraded"), 1).predicate(ResourceLocation.withDefaultNamespace("trim_type"), trim.itemModelIndex()).model(trimUpgradedModel).end();
		}
	}



	@Override
	public String getName() {
		return "Risus Item Models";
	}
}

