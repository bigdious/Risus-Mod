package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {

	public static final TagKey<Block> BONDKNOT_LOGS = BlockTags.create(Risus.prefix("bondknot_logs"));
	public static final TagKey<Block> JOYFLAME_FIRE_BASE_BLOCKS = BlockTags.create(Risus.prefix("joyflame_fire_base_blocks"));

	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BONDKNOT_LOGS)
				.add(RisusBlocks.BONDKNOT_LOG.get(), RisusBlocks.STRIPPED_BONDKNOT_LOG.get())
				.add(RisusBlocks.BONDKNOT_WOOD.get(), RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());

		this.tag(JOYFLAME_FIRE_BASE_BLOCKS).add(RisusBlocks.SMILING_REMAINS.get(), RisusBlocks.ASHEN_REMAINS.get());

		this.tag(BlockTags.LOGS).addTag(BONDKNOT_LOGS);
		this.tag(BlockTags.LOGS_THAT_BURN).addTag(BONDKNOT_LOGS);
		this.tag(BlockTags.PLANKS).add(RisusBlocks.BONDKNOT_PLANKS.get());

		this.tag(BlockTags.FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(BlockTags.WOODEN_FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(BlockTags.FENCE_GATES).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		this.tag(Tags.Blocks.FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(Tags.Blocks.FENCE_GATES).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		this.tag(Tags.Blocks.FENCES_WOODEN).add(RisusBlocks.BONDKNOT_FENCE.get());
		this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());

		this.tag(BlockTags.WOODEN_SLABS).add(RisusBlocks.BONDKNOT_SLAB.get());
		this.tag(BlockTags.SLABS).add(RisusBlocks.BONDKNOT_SLAB.get(), RisusBlocks.GRIMSTONE_SLAB.get());
		this.tag(BlockTags.WOODEN_STAIRS).add(RisusBlocks.BONDKNOT_STAIRS.get());
		this.tag(BlockTags.STAIRS).add(RisusBlocks.BONDKNOT_STAIRS.get(), RisusBlocks.BONDKNOT_STAIRS.get());
		this.tag(BlockTags.WOODEN_BUTTONS).add(RisusBlocks.BONDKNOT_BUTTON.get());
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());

		this.tag(BlockTags.DOORS).add(RisusBlocks.BONDKNOT_DOOR.get());
		this.tag(BlockTags.WOODEN_DOORS).add(RisusBlocks.BONDKNOT_DOOR.get());

		this.tag(BlockTags.TRAPDOORS).add(RisusBlocks.BONDKNOT_TRAPDOOR.get());
		this.tag(BlockTags.WOODEN_TRAPDOORS).add(RisusBlocks.BONDKNOT_TRAPDOOR.get());

		this.tag(BlockTags.SIGNS).add(RisusBlocks.BONDKNOT_SIGN.get()).add(RisusBlocks.BONDKNOT_WALL_SIGN.get());
		this.tag(BlockTags.STANDING_SIGNS).add(RisusBlocks.BONDKNOT_SIGN.get());
		this.tag(BlockTags.WALL_SIGNS).add(RisusBlocks.BONDKNOT_WALL_SIGN.get());

		this.tag(BlockTags.WALLS).add(RisusBlocks.GRIMSTONE_WALL.get(), RisusBlocks.BONE_WALL.get(),
				RisusBlocks.ROTTING_TISSUE.get(), RisusBlocks.DECOMPOSING_TISSUE.get(), RisusBlocks.DECAYING_TISSUE.get(),
				RisusBlocks.ROTTED_TISSUE.get(), RisusBlocks.DECOMPOSED_TISSUE.get(), RisusBlocks.DECAYED_TISSUE.get());

		this.tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).addTag(BONDKNOT_LOGS)
				.add(RisusBlocks.BONDKNOT_PLANKS.get(), RisusBlocks.BONDKNOT_SLAB.get(), RisusBlocks.BONDKNOT_STAIRS.get())
				.add(RisusBlocks.BONDKNOT_FENCE_GATE.get(), RisusBlocks.BONDKNOT_FENCE.get());

		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				RisusBlocks.ALTERATION_CATALYST.get(),
				RisusBlocks.ASHEN_REMAINS.get(),
				RisusBlocks.SMILING_REMAINS.get(),
				RisusBlocks.LAUGHING_OBSIDIAN.get(),
				RisusBlocks.ENGRAVED_BASALT.get(),
				RisusBlocks.MAW_GUTS.get(),
				RisusBlocks.BABY_RIBCAGE.get(),
				RisusBlocks.RIBCAGE.get(),
				RisusBlocks.GRIMSTONE.get(),
				RisusBlocks.GRIMSTONE_BRICKS.get(),
				RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get(),
				RisusBlocks.GRIMSTONE_SLAB.get(),
				RisusBlocks.GRIMSTONE_STAIRS.get(),
				RisusBlocks.GRIMSTONE_WALL.get(),
				RisusBlocks.CHISELED_GRIMSTONE.get(),
				RisusBlocks.POLISHED_GRIMSTONE.get(),
				RisusBlocks.GLUTTONY_SCALEPLATE.get(),
				RisusBlocks.JOYFLAME_LANTERN.get(),
				RisusBlocks.BONE_WALL.get());

		this.tag(BlockTags.MINEABLE_WITH_HOE).add(RisusBlocks.TISSUE.get());

		this.tag(BlockTags.MINEABLE_WITH_AXE).add(RisusBlocks.BURNT_HYPHAE.get(), RisusBlocks.JOYFLAME_CAMPFIRE.get(), RisusBlocks.SCAB.get());

		this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(RisusBlocks.LAUGHING_OBSIDIAN.get(), RisusBlocks.ALTERATION_CATALYST.get(), RisusBlocks.GLUTTONY_SCALEPLATE.get());
	}
}
