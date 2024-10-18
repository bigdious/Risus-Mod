package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
	public static final TagKey<Item> BONDKNOT_LOGS = ItemTags.create(Risus.prefix("bondknot_logs"));
	public static final TagKey<Item> JOYFLAME_FIRE_BASE_BLOCKS = ItemTags.create(Risus.prefix("joyflame_fire_base_blocks"));
	public static final TagKey<Item> FROGLIGHT_BLOCKS = ItemTags.create(Risus.prefix("froglight_blocks"));

	public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTagLookup, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.copy(BlockTagGenerator.BONDKNOT_LOGS, BONDKNOT_LOGS);
		this.copy(BlockTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS, JOYFLAME_FIRE_BASE_BLOCKS);
		this.tag(ItemTags.LOGS).addTag(BONDKNOT_LOGS);
		this.tag(ItemTags.LOGS_THAT_BURN).addTag(BONDKNOT_LOGS);
		this.tag(ItemTagGenerator.FROGLIGHT_BLOCKS).add(Items.OCHRE_FROGLIGHT).add(Items.VERDANT_FROGLIGHT).add(Items.PEARLESCENT_FROGLIGHT);
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

		this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
		this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
		this.tag(ItemTags.SIGNS).add(RisusBlocks.BONDKNOT_SIGN.get().asItem());

		//enchantment lists
		this.tag(ItemTags.SWORDS).add(
			RisusItems.UNAWAKENED_VESSEL.get(),
			RisusItems.CRESCENT_DISASTER.get(),
			RisusItems.SCYTHE.get(),
			RisusItems.SOUL_SCYTHE.get(),
			RisusItems.FIRE_SCYTHE.get(),
			RisusItems.CINDERGLEE_SCYTHE.get(),
			RisusItems.TOOTHKNOCKER.get(),
			RisusItems.GOLD_FIST.get()
		);

		this.tag(ItemTags.DURABILITY_ENCHANTABLE).add(
			RisusItems.UNAWAKENED_VESSEL.get(),
			RisusItems.CRESCENT_DISASTER.get(),
			RisusItems.SCYTHE.get(),
			RisusItems.SOUL_SCYTHE.get(),
			RisusItems.FIRE_SCYTHE.get(),
			RisusItems.CINDERGLEE_SCYTHE.get(),
			RisusItems.TOOTHKNOCKER.get(),
			RisusItems.GOLD_FIST.get()
		);
	}
}
