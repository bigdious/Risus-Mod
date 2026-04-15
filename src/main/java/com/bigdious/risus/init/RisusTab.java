package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusTab {
	private static final ResourceLocation RISUS_TABS = Risus.prefix("textures/gui/tabs.png");


	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Risus.MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = CREATIVE_TABS.register("blocks", () -> CreativeModeTab.builder()
		.title(Component.translatable("itemGroup.risus.blocks"))
		.icon(() -> new ItemStack(RisusBlocks.ORGANIC_MATTER_BLOCK.get()))
		.displayItems(
			(parameters, output) -> {
				output.accept(RisusBlocks.ALTERATION_CATALYST.get());
				output.accept(RisusBlocks.ANGEL_ALTAR.get());
				output.accept(RisusBlocks.DEPTH_VASE.get());
				output.accept(RisusBlocks.DISPLAY_NOTCH.get());

				output.accept(RisusBlocks.ORGANIC_MATTER_BLOCK.get());
				output.accept(RisusBlocks.SMILING_REMAINS.get());
				output.accept(RisusBlocks.ASHEN_REMAINS.get());
				output.accept(RisusBlocks.ASHEN_SPIRE.get());
				output.accept(RisusBlocks.SPREADING_REMAINS.get());
				output.accept(RisusBlocks.INACTIVE_HOLDER.get());

				output.accept(RisusBlocks.JOYFLAME_CAMPFIRE.get());
				output.accept(RisusBlocks.JOYFLAME_LANTERN.get());
				output.accept(RisusBlocks.JOYFLAME_TORCH.get());

				output.accept(RisusBlocks.LAUGHING_OBSIDIAN.get());
				output.accept(RisusBlocks.BLOODWYRM_HEAD.get());
				output.accept(RisusBlocks.BURNT_HYPHAE.get());
				output.accept(RisusBlocks.CURVED_RITUAL_BLOCK.get());
				output.accept(RisusBlocks.LINEAR_RITUAL_BLOCK.get());
				output.accept(RisusBlocks.ENGRAVED_BASALT.get());
				output.accept(RisusBlocks.BLOODWEAVE.get());
				output.accept(RisusBlocks.WEAVER_NEST.get());
				output.accept(RisusBlocks.WEAVING_MECHANISM.get());
				output.accept(RisusBlocks.BLOODY_SPONGE.get());
				output.accept(RisusBlocks.BEATING_HEART.get());
				output.accept(RisusBlocks.COAGULATED_BLOOD_BLOCK.get());
				output.accept(RisusBlocks.SCAB.get());
				output.accept(RisusBlocks.TESSERACT.get());

				output.accept(RisusBlocks.BONDKNOT_LOG.get());
				output.accept(RisusBlocks.POPPING_BONDKNOT_LOG.get());
				output.accept(RisusBlocks.BONDKNOT_WOOD.get());
				output.accept(RisusBlocks.POPPING_BONDKNOT_WOOD.get());
				output.accept(RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
				output.accept(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
				output.accept(RisusBlocks.BONDKNOT_PLANKS.get());
				output.accept(RisusBlocks.BONDKNOT_STAIRS.get());
				output.accept(RisusBlocks.BONDKNOT_SLAB.get());
				output.accept(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());
				output.accept(RisusBlocks.BONDKNOT_BUTTON.get());
				output.accept(RisusBlocks.BONDKNOT_DOOR.get());
				output.accept(RisusBlocks.BONDKNOT_TRAPDOOR.get());
				output.accept(RisusBlocks.BONDKNOT_SIGN.get());
				output.accept(RisusBlocks.BONDKNOT_HANGING_SIGN.get());
				output.accept(RisusBlocks.BONDKNOT_FENCE.get());
				output.accept(RisusBlocks.BONDKNOT_FENCE_GATE.get());

				output.accept(RisusBlocks.CRYSTALLIZED_BONDS.get());
				output.accept(RisusBlocks.BOND_GLASS.get());
				output.accept(RisusBlocks.BOND_GLASS_PANE.get());
				output.accept(RisusBlocks.CONTAINMENT_GLASS.get());
				output.accept(RisusBlocks.CONTAINMENT_GLASS_PANE.get());
				output.accept(RisusBlocks.RIBCAGE.get());
				output.accept(RisusBlocks.BABY_RIBCAGE.get());
				output.accept(RisusBlocks.HEART_TRANSPLANT.get());
				output.accept(RisusBlocks.ZIT.get());

				output.accept(RisusBlocks.GRIMSTONE.get());
				output.accept(RisusBlocks.GRIMSTONE_STAIRS.get());
				output.accept(RisusBlocks.GRIMSTONE_SLAB.get());
				output.accept(RisusBlocks.GRIMSTONE_WALL.get());
				output.accept(RisusBlocks.GRIMSTONE_BRICKS.get());
				output.accept(RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get());
				output.accept(RisusBlocks.GRIMSTONE_BRICKS_SLAB.get());
				output.accept(RisusBlocks.GRIMSTONE_BRICKS_WALL.get());
				output.accept(RisusBlocks.POLISHED_GRIMSTONE.get());
				output.accept(RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get());
				output.accept(RisusBlocks.POLISHED_GRIMSTONE_SLAB.get());
				output.accept(RisusBlocks.POLISHED_GRIMSTONE_WALL.get());
				output.accept(RisusBlocks.GRIMSTONE_BLOOD_TILES.get());
				output.accept(RisusBlocks.GRIMSTONE_BLOOD_TILES_STAIRS.get());
				output.accept(RisusBlocks.GRIMSTONE_BLOOD_TILES_SLAB.get());
				output.accept(RisusBlocks.GRIMSTONE_BLOOD_TILES_WALL.get());
				output.accept(RisusBlocks.ACTIVE_GRIMSTONE.get());
				output.accept(RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get());
				output.accept(RisusBlocks.CHISELED_GRIMSTONE.get());
				output.accept(RisusBlocks.GRIMSTONE_PILLAR.get());

				output.accept(RisusBlocks.SKIN.get());
				output.accept(RisusBlocks.FLESHY_SKIN.get());
				output.accept(RisusBlocks.CURVED_FLESHY_SKIN.get());
				output.accept(RisusBlocks.HAIRY_SKIN.get());
				output.accept(RisusBlocks.HAIRY_FLESHY_SKIN.get());
				output.accept(RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get());
				output.accept(RisusBlocks.TALL_HAIR.get());
				output.accept(RisusBlocks.BUNDLE_OF_HAIR.get());

				output.accept(RisusBlocks.COPPER_AMALGAM.get());
				output.accept(RisusBlocks.EXPOSED_COPPER_AMALGAM.get());
				output.accept(RisusBlocks.WEATHERED_COPPER_AMALGAM.get());
				output.accept(RisusBlocks.OXIDIZED_COPPER_AMALGAM.get());
				output.accept(RisusBlocks.WAXED_COPPER_AMALGAM.get());
				output.accept(RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get());
				output.accept(RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get());
				output.accept(RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get());

				output.accept(RisusBlocks.FLESHY_SPAWNER.get());
				output.accept(RisusBlocks.TISSUE.get());
				output.accept(RisusBlocks.ROTTING_TISSUE.get());
				output.accept(RisusBlocks.DECOMPOSING_TISSUE.get());
				output.accept(RisusBlocks.DECAYING_TISSUE.get());
				output.accept(RisusBlocks.LIVING_TISSUE.get());
				output.accept(RisusBlocks.ROTTED_TISSUE.get());
				output.accept(RisusBlocks.DECOMPOSED_TISSUE.get());
				output.accept(RisusBlocks.DECAYED_TISSUE.get());
				output.accept(RisusBlocks.TISSUE_STAIRS.get());
				output.accept(RisusBlocks.TISSUE_SLAB.get());
				output.accept(RisusBlocks.TISSUE_WALL.get());
				output.accept(RisusBlocks.GRILLED_TISSUE.get());
				output.accept(RisusBlocks.PEELED_GRILLED_TISSUE.get());
				output.accept(RisusBlocks.BONE_WALL.get());
				output.accept(RisusBlocks.BONE_STAIRS.get());
				output.accept(RisusBlocks.BONE_SLAB.get());
				output.accept(RisusBlocks.FULL_BONE_BLOCK.get());
				output.accept(RisusBlocks.FULL_BONE_STAIRS.get());
				output.accept(RisusBlocks.FULL_BONE_SLAB.get());
				output.accept(RisusBlocks.BONE_PILLAR.get());
				output.accept(RisusBlocks.FOSSIL.get());
				output.accept(RisusBlocks.FOSSIL_FRAGMENTED.get());
				output.accept(RisusBlocks.FOSSIL_WALL.get());
				output.accept(RisusBlocks.FOSSIL_STAIRS.get());
				output.accept(RisusBlocks.FOSSIL_SLAB.get());
				output.accept(RisusBlocks.FULL_FOSSIL.get());
				output.accept(RisusBlocks.FULL_FOSSIL_STAIRS.get());
				output.accept(RisusBlocks.FULL_FOSSIL_SLAB.get());
				output.accept(RisusBlocks.FOSSIL_PILLAR.get());

				output.accept(RisusBlocks.EYE_GOLDEN.get());
				output.accept(RisusBlocks.EYE_ENDER.get());
				output.accept(RisusBlocks.EYE_BLEACHED.get());
				output.accept(RisusBlocks.EYE_BLOODSHOT.get());
				output.accept(RisusBlocks.EYE_EMERALD.get());
				output.accept(RisusBlocks.EYE_GOLDEN_GLOWING.get());
				output.accept(RisusBlocks.EYE_ENDER_GLOWING.get());
				output.accept(RisusBlocks.EYE_BLEACHED_GLOWING.get());
				output.accept(RisusBlocks.EYE_BLOODSHOT_GLOWING.get());
				output.accept(RisusBlocks.EYE_EMERALD_GLOWING.get());

				output.accept(RisusBlocks.TEETH.get());

				output.accept(RisusBlocks.MAW_GUTS.get());
				output.accept(RisusBlocks.MIRAGE_GRASS_BLOCK.get());
				output.accept(RisusBlocks.MIRAGE_SAND.get());
				output.accept(RisusBlocks.MIRAGE_NETHERRACK.get());
				output.accept(RisusBlocks.MIRAGE_END_STONE.get());
				output.accept(RisusBlocks.GLUTTONY_SCALEPLATE.get());
				output.accept(RisusBlocks.IMITATION_SCALEPLATE.get());
				output.accept(RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get());
				output.accept(RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get());
				output.accept(RisusBlocks.FLATTENED_SCALES_BLOCK.get());
				output.accept(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get());
				output.accept(RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS.get());
				output.accept(RisusBlocks.IMITATION_SCALES_BLOCK_SLAB.get());
				output.accept(RisusBlocks.IMITATION_SCALES_BLOCK_WALL.get());
				output.accept(RisusBlocks.UNALLOYED_SCALEPLATE.get());
				output.accept(RisusBlocks.FLOWERING_UNALLOYED_SCALEPLATE.get());
				output.accept(RisusBlocks.BUDDING_UNALLOYED_SCALEPLATE.get());
				output.accept(RisusBlocks.UNALLOYED_SCALES_BLOCK.get());
				output.accept(RisusBlocks.UNALLOYED_SCALES_BLOCK_STAIRS.get());
				output.accept(RisusBlocks.UNALLOYED_SCALES_BLOCK_SLAB.get());
				output.accept(RisusBlocks.UNALLOYED_SCALES_BLOCK_WALL.get());


				output.accept(RisusBlocks.BIG_CHAIN.get());
				output.accept(RisusBlocks.EERIE_FENCE.get());
				output.accept(RisusBlocks.DARK_FENCE.get());
				output.accept(RisusBlocks.EERIE_TRAPDOOR.get());
				output.accept(RisusBlocks.DARK_TRAPDOOR.get());
				output.accept(RisusBlocks.EERIE_GATE.get());
				output.accept(RisusBlocks.DARK_GATE.get());
				output.accept(RisusBlocks.EERIE_LARGE_GATE.get());
				output.accept(RisusBlocks.DARK_LARGE_GATE.get());
				output.accept(RisusBlocks.WHITE_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.GRAY_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.BLACK_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.BROWN_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.RED_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.ORANGE_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.YELLOW_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.LIME_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.GREEN_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.CYAN_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.BLUE_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.PURPLE_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.MAGENTA_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.PINK_MOSAIC_GLASS.get());
				output.accept(RisusBlocks.WHITE_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.GRAY_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.BLACK_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.BROWN_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.RED_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.ORANGE_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.YELLOW_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.LIME_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.GREEN_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.CYAN_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.BLUE_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.PURPLE_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.MAGENTA_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.PINK_MOSAIC_GLASS_PANE.get());
				output.accept(RisusBlocks.WHITE_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.LIGHT_GRAY_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.GRAY_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.BLACK_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.BROWN_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.RED_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.ORANGE_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.YELLOW_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.LIME_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.GREEN_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.CYAN_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.LIGHT_BLUE_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.BLUE_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.PURPLE_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.MAGENTA_MOSAIC_LAMP.get());
				output.accept(RisusBlocks.PINK_MOSAIC_LAMP.get());



				output.accept(RisusBlocks.LIGHT_EXCREMENT.get());
				output.accept(RisusBlocks.REGEN_ROSE.get());
				output.accept(RisusBlocks.NEURON_HEAD.get());
				output.accept(RisusItems.VEINS.get());
				output.accept(RisusBlocks.MEMORY1.get());
				output.accept(RisusBlocks.COALIFICATION.get());
			}
		)
		.build());

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GEAR = CREATIVE_TABS.register("gear", () -> CreativeModeTab.builder()
		.withTabsBefore(BLOCKS.getKey())
		.title(Component.translatable("itemGroup.risus.gear"))
		.icon(() -> new ItemStack(RisusItems.RESEARCHERS_NOTES.get()))
		.displayItems(
			(parameters, output) -> {
				output.accept(RisusItems.RESEARCHERS_NOTES.get());
				output.accept(RisusItems.ORGANIC_MATTER.get());
				output.accept(RisusItems.BLOODWYRM_HEAD_WEAPON.get());
				output.accept(RisusItems.BLOOD_BUCKET.get());
				output.accept(RisusItems.CROWN_OF_BONES.get());
				output.accept(RisusItems.SKIN_HELMET.get());
				output.accept(RisusItems.SKIN_CHESTPLATE.get());
				output.accept(RisusItems.SKIN_LEGGINGS.get());
				output.accept(RisusItems.SKIN_BOOTS.get());
				output.accept(RisusItems.STITCHING_NEEDLE.get());
				output.accept(RisusItems.SINNER_ROBES_HELMET.get());
				output.accept(RisusItems.SINNER_ROBES_CHESTPLATE.get());
				output.accept(RisusItems.SINNER_ROBES_LEGGINGS.get());
				output.accept(RisusItems.SINNER_ROBES_BOOTS.get());
				output.accept(RisusItems.STALKER_EYE.get());
				output.accept(RisusItems.EYE_SANDWICH.get());
				output.accept(RisusItems.TOOTHKNOCKER.get());
				output.accept(RisusItems.HAND_OF_GREED.get());
				output.accept(RisusItems.GOLD_FIST.get());
				output.accept(RisusItems.BLOOD_FEATHER.get());
				output.accept(RisusItems.COUNTERWEIGHT.get());
				output.accept(RisusItems.ANGEL_WINGS.get());
				output.accept(RisusItems.DIAMOND_TIPPED_ANGEL_WINGS.get());
				output.accept(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
				output.accept(RisusItems.CRYSTALLIZED_BOND.get());
				output.accept(RisusItems.CARVING_KNIFE.get());
				output.accept(RisusItems.GLUTTONY_SCALES.get());
				output.accept(RisusItems.BOOMSTICK.get());
				output.accept(RisusItems.LIGHT_DEVOURER.get());
				output.accept(RisusItems.MEMORY_CORE.get());
				output.accept(RisusItems.CONCENTRATION_CORE.get());
				output.accept(RisusItems.SCYTHE.get());
				output.accept(RisusItems.FIRE_SCYTHE.get());
				output.accept(RisusItems.SOUL_SCYTHE.get());
				output.accept(RisusItems.CINDERGLEE_SCYTHE.get());
				output.accept(RisusItems.UNAWAKENED_VESSEL.get());
				output.accept(RisusItems.CRESCENT_DISASTER.get());
				output.accept(RisusItems.SACRIFICE_CATALYST.get());
				output.accept(RisusItems.THOUSAND_BLADE.get());
				output.accept(RisusItems.KILLJOY.get());
				output.accept(RisusItems.ECHO_PEARL.get());
				output.accept(RisusItems.ENDLESS_PEARL.get());
				output.accept(RisusItems.LOVER_CREAM.get());
				output.accept(RisusItems.ETERNAL_YOUTH.get());
				output.accept(RisusItems.ROSE_PETAL.get());
				output.accept(RisusItems.PURIFYING_PASTE.get());
				output.accept(RisusItems.ROSE_CROWN.get());
				output.accept(RisusItems.BORN_TO_BURN.get());
				output.accept(RisusItems.LUCKY_CHARM.get());
				output.accept(RisusItems.WRETCHED_CHARM.get());
				output.accept(RisusItems.GUILTY_APPLE.get());
				output.accept(RisusItems.EGG_SAC.get());
				output.accept(RisusItems.HAIR_FOLLICLES.get());
				output.accept(RisusItems.TOTEM_OF_UNYIELDING.get());
				output.accept(RisusItems.LOST_WILL.get());
				output.accept(RisusItems.SMILE_PATTERN.get());
				output.accept(RisusItems.DIVINITY_PATTERN.get());
				output.accept(RisusItems.TREE_PATTERN.get());
				output.accept(RisusItems.ROSE_PATTERN.get());
				output.accept(RisusItems.MUSIC_DISC_RAK.get());
				output.accept(RisusItems.MUSIC_DISC_REGN.get());
				output.accept(RisusItems.MUSIC_DISC_FEIGR.get());
				output.accept(RisusItems.MUSIC_DISC_MORK.get());
				output.accept(RisusItems.MUSIC_DISC_CYCLE.get());
				parameters.holders()
					.lookup(Registries.INSTRUMENT)
					.ifPresent(
						instruments -> generateInstrumentTypes(
							output, instruments, RisusItems.WARHORN.get(), InstrumentTags.GOAT_HORNS, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
						)
					);
				parameters.holders()
					.lookup(Registries.INSTRUMENT)
					.ifPresent(
						instruments -> generateInstrumentTypes(
							output, instruments, RisusItems.HEXHORN.get(), InstrumentTags.GOAT_HORNS, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
						)
					);
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "skeleton");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "zombie");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "creeper");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "wither_skeleton");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "piglin");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "spyglass");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "pumpkin");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "great_stool");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "tuxedo_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "black_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "british_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "calico_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "jellie_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "persian_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "ragdoll_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "orange_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "siamese_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "tabby_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "white_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "audrey_cat");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "pale_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "ashen_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "black_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "chestnut_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "rusty_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "snowy_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "spotted_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "striped_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "woods_wolf");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "fox");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "snow_fox");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "bleached_eye");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "bloodshot_eye");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "emerald_eye");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "ender_eye");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "golden_eye");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "abyssal_eye");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_HELMET.get(), "smile");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_CHESTPLATE.get(), "guts");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_CHESTPLATE.get(), "hand_of_greed");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_CHESTPLATE.get(), "great_stool");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_LEGGINGS.get(), "book");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_LEGGINGS.get(), "great_stool");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_BOOTS.get(), "shadow_walker");
				generateArmorWithAbility(output, RisusItems.SINNER_ROBES_BOOTS.get(), "great_stool");

			}).build());

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SUMMONERS = CREATIVE_TABS.register("summoners", () -> CreativeModeTab.builder()
		.withTabsBefore(GEAR.getKey())
		.title(Component.translatable("itemGroup.risus.summoner"))
		.icon(() -> new ItemStack(RisusItems.ESSENCE_OF_LUST.get()))
		.displayItems(
			(parameters, output) -> {
				output.accept(RisusItems.BONDKNOT_BOAT.get());
				output.accept(RisusItems.GUTS_BOAT.get());
				output.accept(RisusItems.HOLDER_BUCKET.get());
				output.accept(RisusItems.ESSENCE_OF_GLUTTONY.get());
				output.accept(RisusItems.ESSENCE_OF_MELANCHOLY.get());
				output.accept(RisusItems.ESSENCE_OF_GREED.get());
				output.accept(RisusItems.ESSENCE_OF_LUST.get());
				output.accept(RisusItems.ESSENCE_OF_SLOTH.get());
				output.accept(RisusItems.EMBODIMENT_OF_COURTSHIP.get());
				output.accept(RisusItems.EMBODIMENT_OF_DEVOTION.get());
				output.accept(RisusItems.EMBODIMENT_OF_INTIMACY.get());
				output.accept(RisusItems.EMBODIMENT_OF_LANGUISH.get());
			}).build());

	private static void generateInstrumentTypes(CreativeModeTab.Output output, HolderLookup<Instrument> instruments, Item item, TagKey<Instrument> instrument, CreativeModeTab.TabVisibility tabVisibility
	) {
		instruments.get(instrument)
			.ifPresent(
				instrumentNamed -> instrumentNamed.stream()
					.map(instrumentHolder -> InstrumentItem.create(item, instrumentHolder))
					.forEach(stack -> output.accept(stack, tabVisibility))
			);
	}

	private static void generateArmorWithAbility(CreativeModeTab.Output output, ItemLike item, String ability) {
		ItemStack stack = new ItemStack(item);
		stack.set(RisusDataComponents.ABILITY_VARIANT, ability);
		output.accept(stack);
	}
}
