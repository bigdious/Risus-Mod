package com.bigdious.risus.data.tags;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {

	public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTagLookup, Risus.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.copy(RisusTags.Blocks.BONDKNOT_LOGS, RisusTags.Items.BONDKNOT_LOGS);
		this.copy(RisusTags.Blocks.JOYFLAME_FIRE_BASE_BLOCKS, RisusTags.Items.JOYFLAME_FIRE_BASE_BLOCKS);
		this.tag(ItemTags.LOGS).addTag(RisusTags.Items.BONDKNOT_LOGS);
		this.tag(ItemTags.LOGS_THAT_BURN).addTag(RisusTags.Items.BONDKNOT_LOGS);
		this.tag(RisusTags.Items.FROGLIGHT_BLOCKS).add(Items.OCHRE_FROGLIGHT).add(Items.VERDANT_FROGLIGHT).add(Items.PEARLESCENT_FROGLIGHT);
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
		this.tag(RisusTags.Items.BONE_BLOCK_VARIATION).add(RisusBlocks.FULL_BONE_BLOCK.get().asItem(), Blocks.BONE_BLOCK.asItem());
		this.tag(RisusTags.Items.FOSSIL_VARIATION).add(RisusBlocks.FULL_FOSSIL.get().asItem(),RisusBlocks.FOSSIL.asItem());

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
			RisusItems.BLOOD_FEATHER.get(),
			RisusItems.STITCHING_NEEDLE.get());

		this.tag(RisusTags.Items.EYE).add(
			RisusBlocks.EYE_EMERALD.asItem(),
			RisusBlocks.EYE_BLOODSHOT.asItem(),
			RisusBlocks.EYE_ENDER.asItem(),
			RisusBlocks.EYE_GOLDEN.asItem(),
			RisusBlocks.EYE_BLEACHED.asItem(),
			RisusBlocks.EYE_EMERALD_GLOWING.asItem(),
			RisusBlocks.EYE_BLOODSHOT_GLOWING.asItem(),
			RisusBlocks.EYE_ENDER_GLOWING.asItem(),
			RisusBlocks.EYE_GOLDEN_GLOWING.asItem(),
			RisusBlocks.EYE_BLEACHED_GLOWING.asItem());

		this.tag(RisusTags.Items.BASE_TISSUE).add(
			RisusBlocks.TISSUE.asItem(),
			RisusBlocks.LIVING_TISSUE.asItem());

		this.tag(ItemTags.TRIMMABLE_ARMOR).add(
				RisusItems.SKIN_HELMET.get(),
				RisusItems.SKIN_CHESTPLATE.get(),
				RisusItems.SKIN_LEGGINGS.get(),
				RisusItems.SKIN_BOOTS.get(),
				RisusItems.THREADERS_OF_THE_FIRMAMENT.get(),
				RisusItems.ROSE_CROWN.get(),
				RisusItems.CROWN_OF_BONES.get(),
				RisusItems.SINNER_ROBES_BOOTS.get(),
				RisusItems.SINNER_ROBES_LEGGINGS.get(),
				RisusItems.SINNER_ROBES_CHESTPLATE.get(),
				RisusItems.SINNER_ROBES_HELMET.get()
		);

		this.tag(Tags.Items.ARMORS).add(
			RisusItems.SKIN_BOOTS.get(),
			RisusItems.SKIN_CHESTPLATE.get(),
			RisusItems.SKIN_HELMET.get(),
			RisusItems.SKIN_LEGGINGS.get(),
			RisusItems.THREADERS_OF_THE_FIRMAMENT.get(),
			RisusItems.ROSE_CROWN.get(),
			RisusItems.CROWN_OF_BONES.get(),
			RisusItems.SINNER_ROBES_BOOTS.get(),
			RisusItems.SINNER_ROBES_LEGGINGS.get(),
			RisusItems.SINNER_ROBES_CHESTPLATE.get(),
			RisusItems.SINNER_ROBES_HELMET.get()
		);

		this.tag(ItemTags.HEAD_ARMOR).add(
			RisusItems.SKIN_HELMET.get(),
			RisusItems.ROSE_CROWN.get(),
			RisusItems.CROWN_OF_BONES.get(),
			RisusItems.SINNER_ROBES_HELMET.get()

		);
		this.tag(ItemTags.CHEST_ARMOR).add(RisusItems.SKIN_CHESTPLATE.get(), RisusItems.SINNER_ROBES_CHESTPLATE.get());
		this.tag(ItemTags.LEG_ARMOR).add(RisusItems.SKIN_LEGGINGS.get(), RisusItems.SINNER_ROBES_LEGGINGS.get());
		this.tag(ItemTags.FOOT_ARMOR).add(RisusItems.SKIN_BOOTS.get(), RisusItems.THREADERS_OF_THE_FIRMAMENT.get(), RisusItems.SINNER_ROBES_BOOTS.get());

		this.tag(ItemTags.ARMOR_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
		this.tag(ItemTags.FOOT_ARMOR_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
		this.tag(ItemTags.VANISHING_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
		this.tag(ItemTags.EQUIPPABLE_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());

		this.tag(ItemTags.TRIM_MATERIALS).add(RisusItems.GLUTTONY_SCALES.get(), RisusBlocks.SKIN.asItem(), RisusItems.ORGANIC_MATTER.get());
		this.tag(RisusTags.Items.HURTS_ANGEL_WINGS).add(Items.FIREWORK_ROCKET);
		this.tag(RisusTags.Items.LIGHTLY_HURTS_ANGEL_WINGS).add(Items.TRIDENT, RisusItems.CRESCENT_DISASTER.get());
		this.tag(Tags.Items.FOODS_RAW_MEAT).add(Items.COD, Items.SALMON, Items.TROPICAL_FISH);

		//for bondknot
		this.tag(RisusTags.Items.ALTERABLE_GATES).addTag(Tags.Items.FENCE_GATES_WOODEN).remove(RisusBlocks.BONDKNOT_FENCE_GATE.asItem());
		this.tag(RisusTags.Items.ALTERABLE_FENCES).addTag(ItemTags.WOODEN_FENCES).addTag(Tags.Items.FENCES_WOODEN).remove(RisusBlocks.BONDKNOT_FENCE.asItem());
		this.tag(RisusTags.Items.ALTERABLE_TRAPDOORS).addTag(ItemTags.WOODEN_TRAPDOORS).remove(RisusBlocks.BONDKNOT_TRAPDOOR.asItem());
		this.tag(RisusTags.Items.ALTERABLE_HANGING_SIGNS).addTag(ItemTags.HANGING_SIGNS).remove(RisusBlocks.BONDKNOT_HANGING_SIGN.asItem());
		this.tag(RisusTags.Items.ALTERABLE_SIGNS).addTag(ItemTags.SIGNS).remove(RisusBlocks.BONDKNOT_SIGN.asItem());
		this.tag(RisusTags.Items.ALTERABLE_DOORS).addTag(ItemTags.WOODEN_DOORS).remove(RisusBlocks.BONDKNOT_DOOR.asItem());
		this.tag(RisusTags.Items.ALTERABLE_BUTTONS).addTag(ItemTags.WOODEN_BUTTONS).remove(RisusBlocks.BONDKNOT_BUTTON.asItem());
		this.tag(RisusTags.Items.ALTERABLE_PRESSURE_PLATES).addTag(ItemTags.WOODEN_PRESSURE_PLATES).remove(RisusBlocks.BONDKNOT_PRESSURE_PLATE.asItem());
		this.tag(RisusTags.Items.ALTERABLE_SLABS).addTag(ItemTags.WOODEN_SLABS).remove(RisusBlocks.BONDKNOT_SIGN.asItem());
		this.tag(RisusTags.Items.ALTERABLE_STAIRS).addTag(ItemTags.WOODEN_STAIRS).remove(RisusBlocks.BONDKNOT_STAIRS.asItem());
		this.tag(RisusTags.Items.ALTERABLE_PLANKS).addTag(ItemTags.PLANKS).remove(RisusBlocks.BONDKNOT_PLANKS.asItem());
		this.tag(RisusTags.Items.ALTERABLE_LOGS).addTag(ItemTags.LOGS).remove(
			RisusBlocks.BONDKNOT_LOG.asItem(),
			RisusBlocks.POPPING_BONDKNOT_LOG.asItem(),
			RisusBlocks.STRIPPED_BONDKNOT_LOG.asItem(),
			RisusBlocks.BONDKNOT_WOOD.asItem(),
			RisusBlocks.POPPING_BONDKNOT_WOOD.asItem(),
			RisusBlocks.STRIPPED_BONDKNOT_WOOD.asItem()
		);

		this.tag(ItemTags.BEE_FOOD).add(
			RisusBlocks.REGEN_ROSE.asItem()
		);

		this.tag(Tags.Items.STORAGE_BLOCKS).add(
			RisusBlocks.FLATTENED_SCALES_BLOCK.asItem(),
			RisusBlocks.ORGANIC_MATTER_BLOCK.asItem(),
			RisusBlocks.FULL_BONE_BLOCK.asItem()
		);

		this.tag(RisusTags.Items.STOPS_THREAD_WINGS_RENDERING).add(
			Items.ELYTRA,
			RisusItems.ANGEL_WINGS.get()
		);

		this.tag(RisusTags.Items.HORNS).add(
			Items.GOAT_HORN,
			RisusItems.WARHORN.get(),
			RisusItems.HEXHORN.get()
		);

		this.tag(RisusTags.Items.ROBES).add(
			RisusItems.SINNER_ROBES_HELMET.get(),
			RisusItems.SINNER_ROBES_CHESTPLATE.get(),
			RisusItems.SINNER_ROBES_LEGGINGS.get(),
			RisusItems.SINNER_ROBES_BOOTS.get()
		);

		this.tag(ItemTags.DYEABLE).add(
			RisusItems.SINNER_ROBES_HELMET.get(),
			RisusItems.SINNER_ROBES_CHESTPLATE.get(),
			RisusItems.SINNER_ROBES_LEGGINGS.get(),
			RisusItems.SINNER_ROBES_BOOTS.get()
		);

		this.tag(RisusTags.Items.SWORD_AND_TRIDENT_ENCHANTABLE)
			.addTag(ItemTags.WEAPON_ENCHANTABLE)
			.addTag(ItemTags.TRIDENT_ENCHANTABLE)
		;

		this.tag(RisusTags.Items.PERPETUITY_BLACKLIST).add(
			Items.ELYTRA,
			RisusItems.ANGEL_WINGS.get()
		);

		//curios
		this.tag(RisusTags.Items.CURIOS_HANDS).add(
			RisusItems.HAND_OF_GREED.get()
		);

		this.tag(RisusTags.Items.CURIOS_HEAD).add(
			RisusItems.BLOODWYRM_HEAD.get()
		);

		this.tag(RisusTags.Items.CURIOS_CHARM).add(
			RisusItems.TOTEM_OF_UNYIELDING.get(),
			RisusItems.BLOOD_FEATHER.get(),
			RisusItems.LUCKY_CHARM.get(),
			RisusItems.WRETCHED_CHARM.get()
		);

		this.tag(RisusTags.Items.CURIOS_BELT).add(
			RisusItems.RESEARCHERS_NOTES.get()
		);
	}
}
