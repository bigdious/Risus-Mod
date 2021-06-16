package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTagGenerator extends ItemTagsProvider {
	public static final ITag.INamedTag<Item> BONDKNOT_LOGS = ItemTags.makeWrapperTag(Risus.risusRL("bondknot_logs").toString());

	public ItemTagGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, Risus.ID, existingFileHelper);
	}

	@Override
	protected void registerTags() {
		this.copy(BlockTagGenerator.BONDKNOT_LOGS, BONDKNOT_LOGS);
		getOrCreateBuilder(ItemTags.LOGS).addTag(BONDKNOT_LOGS);
		getOrCreateBuilder(ItemTags.LOGS_THAT_BURN).addTag(BONDKNOT_LOGS);
		this.copy(BlockTags.PLANKS, ItemTags.PLANKS);

		this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
		this.copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
		this.copy(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES);
		this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
		this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);

		this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
		this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);

	}
}
