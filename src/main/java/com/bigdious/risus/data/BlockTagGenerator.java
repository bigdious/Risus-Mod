package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {

	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(RisusTags.Blocks.BONDKNOT_LOGS)
			.add(RisusBlocks.BONDKNOT_LOG.get(), RisusBlocks.STRIPPED_BONDKNOT_LOG.get())
			.add(RisusBlocks.BONDKNOT_WOOD.get(), RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());

		this.tag(RisusTags.Blocks.JOYFLAME_FIRE_BASE_BLOCKS).add(RisusBlocks.SMILING_REMAINS.get(), RisusBlocks.ASHEN_REMAINS.get());

		this.tag(BlockTags.LOGS).addTag(RisusTags.Blocks.BONDKNOT_LOGS);
		this.tag(BlockTags.LOGS_THAT_BURN).addTag(RisusTags.Blocks.BONDKNOT_LOGS);
		this.tag(BlockTags.PLANKS).add(RisusBlocks.BONDKNOT_PLANKS.get());

		this.tag(BlockTags.FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(BlockTags.WOODEN_FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(BlockTags.FENCE_GATES).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		this.tag(Tags.Blocks.FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(Tags.Blocks.FENCE_GATES).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		this.tag(Tags.Blocks.FENCES_WOODEN).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());

		this.tag(BlockTags.WOODEN_SLABS).add(RisusBlocks.BONDKNOT_SLAB.get());
		this.tag(BlockTags.SLABS).add(
			RisusBlocks.BONDKNOT_SLAB.get(),
			RisusBlocks.TISSUE_SLAB.get(),
			RisusBlocks.GRIMSTONE_SLAB.get(),
			RisusBlocks.BONE_SLAB.get(),
			RisusBlocks.FULL_BONE_SLAB.get(),
			RisusBlocks.GRIMSTONE_BRICKS_SLAB.get(),
			RisusBlocks.POLISHED_GRIMSTONE_SLAB.get()
		);
		this.tag(BlockTags.WOODEN_STAIRS).add(RisusBlocks.BONDKNOT_STAIRS.get());
		this.tag(BlockTags.STAIRS).add(
			RisusBlocks.BONDKNOT_STAIRS.get(),
			RisusBlocks.BONE_STAIRS.get(),
			RisusBlocks.FULL_BONE_STAIRS.get(),
			RisusBlocks.GRIMSTONE_STAIRS.get(),
			RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get(),
			RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get(),
			RisusBlocks.TISSUE_STAIRS.get()
		);
		this.tag(BlockTags.WOODEN_BUTTONS).add(RisusBlocks.BONDKNOT_BUTTON.get());
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());

		this.tag(BlockTags.DOORS).add(RisusBlocks.BONDKNOT_DOOR.get());
		this.tag(BlockTags.WOODEN_DOORS).add(RisusBlocks.BONDKNOT_DOOR.get());

		this.tag(BlockTags.TRAPDOORS).add(RisusBlocks.BONDKNOT_TRAPDOOR.get());
		this.tag(BlockTags.WOODEN_TRAPDOORS).add(RisusBlocks.BONDKNOT_TRAPDOOR.get());

		this.tag(BlockTags.SIGNS).add(
			RisusBlocks.BONDKNOT_SIGN.get(),
			RisusBlocks.BONDKNOT_WALL_SIGN.get()
		);

		this.tag(BlockTags.STANDING_SIGNS).add(RisusBlocks.BONDKNOT_SIGN.get());
		this.tag(BlockTags.WALL_SIGNS).add(RisusBlocks.BONDKNOT_WALL_SIGN.get());

		this.tag(BlockTags.WALLS).add(
			RisusBlocks.GRIMSTONE_WALL.get(),
			RisusBlocks.GRIMSTONE_BRICKS_WALL.get(),
			RisusBlocks.POLISHED_GRIMSTONE_WALL.get(),
			RisusBlocks.BONE_WALL.get(),
			RisusBlocks.ROTTING_TISSUE.get(),
			RisusBlocks.DECOMPOSING_TISSUE.get(),
			RisusBlocks.DECAYING_TISSUE.get(),
			RisusBlocks.ROTTED_TISSUE.get(),
			RisusBlocks.DECOMPOSED_TISSUE.get(),
			RisusBlocks.DECAYED_TISSUE.get(),
			RisusBlocks.IMITATION_SCALES_BLOCK_WALL.get());


		this.tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).addTag(RisusTags.Blocks.BONDKNOT_LOGS)
			.add(RisusBlocks.BONDKNOT_PLANKS.get(), RisusBlocks.BONDKNOT_SLAB.get(), RisusBlocks.BONDKNOT_STAIRS.get())
			.add(RisusBlocks.BONDKNOT_FENCE_GATE.get(), RisusBlocks.BONDKNOT_FENCE.get());

		this.tag(BlockTags.CAMPFIRES).add(RisusBlocks.JOYFLAME_CAMPFIRE.get());

		this.tag(BlockTags.NYLIUM).add(RisusBlocks.EYE_BLEACHED.get())
			.add(RisusBlocks.EYE_BLOODSHOT.get())
			.add(RisusBlocks.EYE_EMERALD.get())
			.add(RisusBlocks.EYE_ENDER.get())
			.add(RisusBlocks.EYE_GOLDEN.get());

		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
			RisusBlocks.BIG_CHAIN.get(),
			RisusBlocks.ASHEN_REMAINS.get(),
			RisusBlocks.SMILING_REMAINS.get(),
			RisusBlocks.LAUGHING_OBSIDIAN.get(),
			RisusBlocks.ENGRAVED_BASALT.get(),
			RisusBlocks.MAW_GUTS.get(),
			RisusBlocks.BABY_RIBCAGE.get(),
			RisusBlocks.RIBCAGE.get(),
			RisusBlocks.GRIMSTONE_BRICKS.get(),
			RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get(),
			RisusBlocks.ACTIVE_GRIMSTONE.get(),

			RisusBlocks.MIRAGE_END_STONE.get(),
			RisusBlocks.MIRAGE_NETHERRACK.get(),

			RisusBlocks.GRIMSTONE.get(),
			RisusBlocks.GRIMSTONE_SLAB.get(),
			RisusBlocks.GRIMSTONE_STAIRS.get(),
			RisusBlocks.GRIMSTONE_WALL.get(),

			RisusBlocks.CHISELED_GRIMSTONE.get(),

			RisusBlocks.GRIMSTONE_BRICKS.get(),
			RisusBlocks.GRIMSTONE_BRICKS_SLAB.get(),
			RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get(),
			RisusBlocks.GRIMSTONE_BRICKS_WALL.get(),

			RisusBlocks.POLISHED_GRIMSTONE.get(),
			RisusBlocks.POLISHED_GRIMSTONE_SLAB.get(),
			RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get(),
			RisusBlocks.POLISHED_GRIMSTONE_WALL.get(),

			RisusBlocks.GLUTTONY_SCALEPLATE.get(),
			RisusBlocks.JOYFLAME_LANTERN.get(),
			RisusBlocks.CURVED_RITUAL_BLOCK.get(),
			RisusBlocks.TEETH.get(),
			RisusBlocks.LINEAR_RITUAL_BLOCK.get(),
			RisusBlocks.IMITATION_SCALEPLATE.get(),
			RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get(),
			RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get(),
			RisusBlocks.FLATTENED_SCALES_BLOCK.get(),
			RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(),
			RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS.get(),
			RisusBlocks.IMITATION_SCALES_BLOCK_SLAB.get(),
			RisusBlocks.IMITATION_SCALES_BLOCK_WALL.get(),
			RisusBlocks.BONE_WALL.get(),
			RisusBlocks.FULL_BONE_BLOCK.get(),
			RisusBlocks.BONE_SLAB.get(),
			RisusBlocks.FULL_BONE_SLAB.get(),
			RisusBlocks.FULL_BONE_STAIRS.get(),
			RisusBlocks.BONE_STAIRS.get(),

			RisusBlocks.FLESHY_SPAWNER.get(),

			RisusBlocks.COPPER_AMALGAM.get(),
			RisusBlocks.EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.OXIDIZED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get(),
			RisusBlocks.BLOOD_CAULDRON.get()
		);

		this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(RisusBlocks.ASHEN_REMAINS.get());

		this.tag(BlockTags.MINEABLE_WITH_AXE)
			.add(RisusBlocks.BONDKNOT_HANGING_SIGN.get(),
				RisusBlocks.BURNT_HYPHAE.get(),
				RisusBlocks.JOYFLAME_CAMPFIRE.get(),
				RisusBlocks.SCAB.get(),
				RisusBlocks.TISSUE.get(),
				RisusBlocks.TISSUE_SLAB.get(),
				RisusBlocks.TISSUE_STAIRS.get(),
				RisusBlocks.LIVING_TISSUE.get(),
				RisusBlocks.SKIN.get(),
				RisusBlocks.FLESHY_SKIN.get(),
				RisusBlocks.CURVED_FLESHY_SKIN.get(),
				RisusBlocks.HAIRY_SKIN.get(),
				RisusBlocks.HAIRY_FLESHY_SKIN.get(),
				RisusBlocks.EYE_BLEACHED.get(),
				RisusBlocks.EYE_GOLDEN.get(),
				RisusBlocks.EYE_EMERALD.get(),
				RisusBlocks.EYE_BLOODSHOT.get(),
				RisusBlocks.EYE_ENDER.get(),
				RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get()
			);

		this.tag(BlockTags.CLIMBABLE)
			.add(RisusBlocks.VEINS.get(),
				RisusBlocks.NEURON_STEM.get(),
				RisusBlocks.BIG_CHAIN.get()
			);

		this.tag(BlockTags.MINEABLE_WITH_HOE)
			.add(RisusBlocks.EYE_BLEACHED.get(),
				RisusBlocks.EYE_GOLDEN.get(),
				RisusBlocks.EYE_EMERALD.get(),
				RisusBlocks.EYE_BLOODSHOT.get(),
				RisusBlocks.EYE_ENDER.get(),
				RisusBlocks.BLOODY_SPONGE.get(),
				RisusBlocks.SCAB.get(),
				RisusBlocks.TISSUE.get(),
				RisusBlocks.TISSUE_SLAB.get(),
				RisusBlocks.TISSUE_STAIRS.get(),
				RisusBlocks.LIVING_TISSUE.get(),
				RisusBlocks.SKIN.get(),
				RisusBlocks.FLESHY_SKIN.get(),
				RisusBlocks.CURVED_FLESHY_SKIN.get(),
				RisusBlocks.HAIRY_SKIN.get(),
				RisusBlocks.HAIRY_FLESHY_SKIN.get(),
				RisusBlocks.BUNDLE_OF_HAIR.get(),
				RisusBlocks.SPREADING_REMAINS.get()
			);
		this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
			.add(RisusBlocks.LAUGHING_OBSIDIAN.get(),
				RisusBlocks.GLUTTONY_SCALEPLATE.get(),
				RisusBlocks.IMITATION_SCALEPLATE.get(),
				RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get(),
				RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get(),
				RisusBlocks.FLATTENED_SCALES_BLOCK.get(),
				RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(),
				RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS.get(),
				RisusBlocks.IMITATION_SCALES_BLOCK_SLAB.get(),
				RisusBlocks.IMITATION_SCALES_BLOCK_WALL.get()
			);
		this.tag(BlockTags.NEEDS_STONE_TOOL).add(
			RisusBlocks.COPPER_AMALGAM.get(),
			RisusBlocks.EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.OXIDIZED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get()
		);

		this.tag(RisusTags.Blocks.COPPER_AMALGAM_VARIATION).add(
			RisusBlocks.COPPER_AMALGAM.get(),
			RisusBlocks.EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.OXIDIZED_COPPER_AMALGAM.get()
		);
		this.tag(RisusTags.Blocks.WAXED_COPPER_AMALGAM_VARIATION).add(
			RisusBlocks.WAXED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get()
		);
		this.tag(BlockTags.FIRE).add(
			RisusBlocks.JOYFLAME_FIRE.get()
		);
		this.tag(BlockTags.CAMPFIRES).add(
			RisusBlocks.JOYFLAME_CAMPFIRE.get()
		);
		this.tag(RisusTags.Blocks.LITTER_ALLOWED_LIGHT_BLOCKS).add(
			Blocks.GLOWSTONE,
			Blocks.SHROOMLIGHT,
			Blocks.JACK_O_LANTERN,
			Blocks.BEACON,
			Blocks.SEA_LANTERN,
			Blocks.OCHRE_FROGLIGHT,
			Blocks.PEARLESCENT_FROGLIGHT,
			Blocks.VERDANT_FROGLIGHT,
			Blocks.SCULK_CATALYST,
			Blocks.CRYING_OBSIDIAN,
			RisusBlocks.LAUGHING_OBSIDIAN.get());

			this.tag(RisusTags.Blocks.ILLEGAL_LITTER_ALLOWED_LIGHT_BLOCKS).add(
			Blocks.ENCHANTING_TABLE,
			Blocks.COPPER_BULB,
			Blocks.EXPOSED_COPPER_BULB,
			Blocks.OXIDIZED_COPPER_BULB,
			Blocks.WEATHERED_COPPER_BULB,
			Blocks.WAXED_COPPER_BULB,
			Blocks.WAXED_EXPOSED_COPPER_BULB,
			Blocks.WAXED_OXIDIZED_COPPER_BULB,
			Blocks.WAXED_WEATHERED_COPPER_BULB,
			Blocks.REDSTONE_LAMP,
			Blocks.REDSTONE_ORE,
			Blocks.DEEPSLATE_REDSTONE_ORE,
			Blocks.SMALL_AMETHYST_BUD,
			Blocks.MEDIUM_AMETHYST_BUD,
			Blocks.LARGE_AMETHYST_BUD,
			Blocks.AMETHYST_CLUSTER,
			Blocks.BROWN_MUSHROOM,
			Blocks.FIRE,
			Blocks.CAMPFIRE,
			Blocks.TORCH,
			Blocks.WALL_TORCH,
			Blocks.LANTERN,
			Blocks.SOUL_FIRE,
			Blocks.SOUL_CAMPFIRE,
			Blocks.SOUL_TORCH,
			Blocks.SOUL_WALL_TORCH,
			Blocks.SOUL_LANTERN,
			RisusBlocks.JOYFLAME_TORCH.get(),
			RisusBlocks.JOYFLAME_FIRE.get(),
			RisusBlocks.JOYFLAME_CAMPFIRE.get(),
			RisusBlocks.JOYFLAME_WALL_TORCH.get(),
			RisusBlocks.JOYFLAME_LANTERN.get(),
			Blocks.END_ROD,
			Blocks.CONDUIT,
			Blocks.REDSTONE_TORCH,
			Blocks.REDSTONE_WALL_TORCH,
			Blocks.REPEATER,
			Blocks.COMPARATOR,
			Blocks.SCULK_SENSOR,
			Blocks.CALIBRATED_SCULK_SENSOR,
			Blocks.BREWING_STAND,
			Blocks.FURNACE,
			Blocks.BLAST_FURNACE,
			Blocks.SMOKER,
			Blocks.GLOW_LICHEN,
			Blocks.CAVE_VINES,
			Blocks.CAVE_VINES_PLANT,
			Blocks.SEA_PICKLE,
			Blocks.RESPAWN_ANCHOR,
			Blocks.DRAGON_EGG,
			Blocks.CANDLE,
			Blocks.CANDLE_CAKE,
			Blocks.BLACK_CANDLE_CAKE,
			Blocks.YELLOW_CANDLE_CAKE,
			Blocks.BLUE_CANDLE_CAKE,
			Blocks.BROWN_CANDLE_CAKE,
			Blocks.CYAN_CANDLE_CAKE,
			Blocks.GRAY_CANDLE_CAKE,
			Blocks.GREEN_CANDLE_CAKE,
			Blocks.LIGHT_BLUE_CANDLE_CAKE,
			Blocks.LIGHT_GRAY_CANDLE_CAKE,
			Blocks.WHITE_CANDLE_CAKE,
			Blocks.RED_CANDLE_CAKE,
			Blocks.PURPLE_CANDLE_CAKE,
			Blocks.PINK_CANDLE_CAKE,
			Blocks.ORANGE_CANDLE_CAKE,
			Blocks.MAGENTA_CANDLE_CAKE,
			Blocks.LIME_CANDLE_CAKE,
			Blocks.BLACK_CANDLE,
			Blocks.YELLOW_CANDLE,
			Blocks.BLUE_CANDLE,
			Blocks.BROWN_CANDLE,
			Blocks.CYAN_CANDLE,
			Blocks.GRAY_CANDLE,
			Blocks.GREEN_CANDLE,
			Blocks.LIGHT_BLUE_CANDLE,
			Blocks.LIGHT_GRAY_CANDLE,
			Blocks.WHITE_CANDLE,
			Blocks.RED_CANDLE,
			Blocks.PURPLE_CANDLE,
			Blocks.PINK_CANDLE,
			Blocks.ORANGE_CANDLE,
			Blocks.MAGENTA_CANDLE,
			Blocks.LIME_CANDLE,
			RisusBlocks.LIGHT_EXCREMENT.get()
		)
			;
		tag(BlockTags.CAULDRONS).add(RisusBlocks.BLOOD_CAULDRON.get());
		tag(BlockTags.FLOWERS).add(RisusBlocks.REGEN_ROSE.get());
		tag(BlockTags.SMALL_FLOWERS).add(RisusBlocks.REGEN_ROSE.get());
	}
}
