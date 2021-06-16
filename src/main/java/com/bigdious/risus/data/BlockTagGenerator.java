package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.block.RisusBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BlockTagGenerator extends BlockTagsProvider {

	public static final ITag.INamedTag<Block> BONDKNOT_LOGS = BlockTags.makeWrapperTag(Risus.risusRL("bondknot_logs").toString());

	public BlockTagGenerator(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
		super(generatorIn, Risus.ID, existingFileHelper);
	}

	@Override
	protected void registerTags() {
		getOrCreateBuilder(BONDKNOT_LOGS)
				.add(RisusBlocks.BONDKNOT_LOG.get(), RisusBlocks.STRIPPED_BONDKNOT_LOG.get())
				.add(RisusBlocks.BONDKNOT_WOOD.get(), RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());

		getOrCreateBuilder(BlockTags.LOGS).addTag(BONDKNOT_LOGS);
		getOrCreateBuilder(BlockTags.LOGS_THAT_BURN).addTag(BONDKNOT_LOGS);
		getOrCreateBuilder(BlockTags.PLANKS).add(RisusBlocks.BONDKNOT_PLANKS.get());

		getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		getOrCreateBuilder(BlockTags.FENCE_GATES).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		getOrCreateBuilder(Tags.Blocks.FENCES).add(RisusBlocks.BONDKNOT_FENCE.get());
		getOrCreateBuilder(Tags.Blocks.FENCE_GATES).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());
		getOrCreateBuilder(Tags.Blocks.FENCES_WOODEN).add(RisusBlocks.BONDKNOT_FENCE.get());
		getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN).add(RisusBlocks.BONDKNOT_FENCE_GATE.get());

		getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(RisusBlocks.BONDKNOT_SLAB.get());
		getOrCreateBuilder(BlockTags.SLABS).add(RisusBlocks.BONDKNOT_SLAB.get(), RisusBlocks.GRIMSTONE_SLAB.get());
		getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(RisusBlocks.BONDKNOT_STAIRS.get());
		getOrCreateBuilder(BlockTags.STAIRS).add(RisusBlocks.BONDKNOT_STAIRS.get(), RisusBlocks.BONDKNOT_STAIRS.get());
		getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(RisusBlocks.BONDKNOT_BUTTON.get());
		getOrCreateBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get());

		getOrCreateBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS).addTag(BONDKNOT_LOGS)
				.add(RisusBlocks.BONDKNOT_PLANKS.get(), RisusBlocks.BONDKNOT_SLAB.get(), RisusBlocks.BONDKNOT_STAIRS.get())
				.add(RisusBlocks.BONDKNOT_FENCE_GATE.get(), RisusBlocks.BONDKNOT_FENCE.get());

	}
}
