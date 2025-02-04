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
import net.minecraft.world.level.block.Blocks;
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
		this.tag(RisusTags.Items.BONE_BLOCK_VARIATION).add(RisusBlocks.FULL_BONE_BLOCK.get().asItem()).add(Blocks.BONE_BLOCK.asItem());

		this.tag(Tags.Items.OBSIDIANS).add(RisusBlocks.LAUGHING_OBSIDIAN.asItem());

		this.tag(RisusTags.Items.WILLFUL_WEAPON).add(
				RisusItems.UNAWAKENED_VESSEL.get(),
				RisusItems.CRESCENT_DISASTER.get(),
				RisusItems.SCYTHE.get(),
				RisusItems.FIRE_SCYTHE.get(),
				RisusItems.SOUL_SCYTHE.get(),
				RisusItems.CINDERGLEE_SCYTHE.get(),
				RisusItems.THOUSAND_BLADE.get());

		this.tag(ItemTags.TRIM_TEMPLATES).add(
			RisusItems.BLOOD_FEATHER.get());
		this.tag(RisusTags.Items.EYE).add(
			RisusBlocks.EYE_EMERALD.asItem(),
			RisusBlocks.EYE_BLOODSHOT.asItem(),
			RisusBlocks.EYE_ENDER.asItem(),
			RisusBlocks.EYE_GOLDEN.asItem(),
			RisusBlocks.EYE_BLEACHED.asItem());
		this.tag(RisusTags.Items.BASE_TISSUE).add(
			RisusBlocks.TISSUE.asItem(),
			RisusBlocks.LIVING_TISSUE.asItem());

		this.tag(ItemTags.TRIMMABLE_ARMOR).add(
				RisusItems.SKIN_HELMET.get(),
				RisusItems.SKIN_CHESTPLATE.get(),
				RisusItems.SKIN_LEGGINGS.get(),
				RisusItems.SKIN_BOOTS.get());

		this.tag(ItemTags.TRIM_MATERIALS).add(RisusItems.GLUTTONY_SCALES.get(), RisusBlocks.SKIN.asItem());
		this.tag(RisusTags.Items.HURTS_ANGEL_WINGS).add(Items.FIREWORK_ROCKET);
		this.tag(RisusTags.Items.LIGHTLY_HURTS_ANGEL_WINGS).add(Items.TRIDENT, RisusItems.CRESCENT_DISASTER.get());
		this.tag(Tags.Items.FOODS_RAW_MEAT).add(Items.COD, Items.SALMON, Items.TROPICAL_FISH);

		//for bondknot
		this.tag(RisusTags.Items.ALTERABLE_GATES)
			.add(Items.OAK_FENCE_GATE,
				Items.DARK_OAK_FENCE_GATE,
				Items.BIRCH_FENCE_GATE,
				Items.SPRUCE_FENCE_GATE,
				Items.JUNGLE_FENCE_GATE,
				Items.ACACIA_FENCE_GATE,
				Items.MANGROVE_FENCE_GATE,
				Items.CHERRY_FENCE_GATE)
		;
		this.tag(RisusTags.Items.ALTERABLE_FENCES)
			.add(
				Items.OAK_FENCE,
				Items.DARK_OAK_FENCE,
				Items.BIRCH_FENCE,
				Items.SPRUCE_FENCE,
				Items.JUNGLE_FENCE,
				Items.ACACIA_FENCE,
				Items.MANGROVE_FENCE,
				Items.CHERRY_FENCE
			);
		this.tag(RisusTags.Items.ALTERABLE_TRAPDOORS)
			.add(
				Items.OAK_TRAPDOOR,
				Items.DARK_OAK_TRAPDOOR,
				Items.BIRCH_TRAPDOOR,
				Items.SPRUCE_TRAPDOOR,
				Items.JUNGLE_TRAPDOOR,
				Items.ACACIA_TRAPDOOR,
				Items.MANGROVE_TRAPDOOR,
				Items.CHERRY_TRAPDOOR
			);
		this.tag(RisusTags.Items.ALTERABLE_HANGING_SIGNS)
			.add(
				Items.OAK_HANGING_SIGN,
				Items.DARK_OAK_HANGING_SIGN,
				Items.BIRCH_HANGING_SIGN,
				Items.SPRUCE_HANGING_SIGN,
				Items.JUNGLE_HANGING_SIGN,
				Items.ACACIA_HANGING_SIGN,
				Items.MANGROVE_HANGING_SIGN,
				Items.CHERRY_HANGING_SIGN
			);
		this.tag(RisusTags.Items.ALTERABLE_SIGNS)
			.add(
				Items.OAK_SIGN,
				Items.DARK_OAK_SIGN,
				Items.BIRCH_SIGN,
				Items.SPRUCE_SIGN,
				Items.JUNGLE_SIGN,
				Items.ACACIA_SIGN,
				Items.MANGROVE_SIGN,
				Items.CHERRY_SIGN
			);
		this.tag(RisusTags.Items.ALTERABLE_DOORS)
			.add(
				Items.OAK_DOOR,
				Items.DARK_OAK_DOOR,
				Items.BIRCH_DOOR,
				Items.SPRUCE_DOOR,
				Items.JUNGLE_DOOR,
				Items.ACACIA_DOOR,
				Items.MANGROVE_DOOR,
				Items.CHERRY_DOOR
			);
		this.tag(RisusTags.Items.ALTERABLE_BUTTONS)
			.add(
				Items.OAK_BUTTON,
				Items.DARK_OAK_BUTTON,
				Items.BIRCH_BUTTON,
				Items.SPRUCE_BUTTON,
				Items.JUNGLE_BUTTON,
				Items.ACACIA_BUTTON,
				Items.MANGROVE_BUTTON,
				Items.CHERRY_BUTTON
			);
		this.tag(RisusTags.Items.ALTERABLE_PRESSURE_PLATES)
			.add(
				Items.OAK_PRESSURE_PLATE,
				Items.DARK_OAK_PRESSURE_PLATE,
				Items.BIRCH_PRESSURE_PLATE,
				Items.SPRUCE_PRESSURE_PLATE,
				Items.JUNGLE_PRESSURE_PLATE,
				Items.ACACIA_PRESSURE_PLATE,
				Items.MANGROVE_PRESSURE_PLATE,
				Items.CHERRY_PRESSURE_PLATE
			);
		this.tag(RisusTags.Items.ALTERABLE_SLABS)
			.add(
				Items.OAK_SLAB,
				Items.DARK_OAK_SLAB,
				Items.BIRCH_SLAB,
				Items.SPRUCE_SLAB,
				Items.JUNGLE_SLAB,
				Items.ACACIA_SLAB,
				Items.MANGROVE_SLAB,
				Items.CHERRY_SLAB
			);
		this.tag(RisusTags.Items.ALTERABLE_STAIRS)
			.add(
				Items.OAK_STAIRS,
				Items.DARK_OAK_STAIRS,
				Items.BIRCH_STAIRS,
				Items.SPRUCE_STAIRS,
				Items.JUNGLE_STAIRS,
				Items.ACACIA_STAIRS,
				Items.MANGROVE_STAIRS,
				Items.CHERRY_STAIRS
			);
		this.tag(RisusTags.Items.ALTERABLE_PLANKS)
			.add(
				Items.OAK_PLANKS,
				Items.DARK_OAK_PLANKS,
				Items.BIRCH_PLANKS,
				Items.SPRUCE_PLANKS,
				Items.JUNGLE_PLANKS,
				Items.ACACIA_PLANKS,
				Items.MANGROVE_PLANKS,
				Items.CHERRY_PLANKS
			);
		this.tag(RisusTags.Items.ALTERABLE_LOGS)
			.add(
				Items.OAK_LOG,
				Items.DARK_OAK_LOG,
				Items.BIRCH_LOG,
				Items.SPRUCE_LOG,
				Items.JUNGLE_LOG,
				Items.ACACIA_LOG,
				Items.MANGROVE_LOG,
				Items.CHERRY_LOG
			);
		this.tag(RisusTags.Items.ALTERABLE_STRIPPED_WOODS)
			.add(
				Items.STRIPPED_OAK_WOOD,
				Items.STRIPPED_DARK_OAK_WOOD,
				Items.STRIPPED_BIRCH_WOOD,
				Items.STRIPPED_SPRUCE_WOOD,
				Items.STRIPPED_JUNGLE_WOOD,
				Items.STRIPPED_ACACIA_WOOD,
				Items.STRIPPED_MANGROVE_WOOD,
				Items.STRIPPED_CHERRY_WOOD
			);
		this.tag(RisusTags.Items.ALTERABLE_WOODS)
			.add(
				Items.OAK_WOOD,
				Items.DARK_OAK_WOOD,
				Items.BIRCH_WOOD,
				Items.SPRUCE_WOOD,
				Items.JUNGLE_WOOD,
				Items.ACACIA_WOOD,
				Items.MANGROVE_WOOD,
				Items.CHERRY_WOOD
			);
		this.tag(RisusTags.Items.ALTERABLE_STRIPPED_LOGS)
			.add(
				Items.STRIPPED_OAK_LOG,
				Items.STRIPPED_DARK_OAK_LOG,
				Items.STRIPPED_BIRCH_LOG,
				Items.STRIPPED_SPRUCE_LOG,
				Items.STRIPPED_JUNGLE_LOG,
				Items.STRIPPED_ACACIA_LOG,
				Items.STRIPPED_MANGROVE_LOG,
				Items.STRIPPED_CHERRY_LOG
			);
	}
}
