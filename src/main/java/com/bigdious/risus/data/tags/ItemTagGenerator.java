package com.bigdious.risus.data.tags;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
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

import java.util.Map;
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

		this.tag(ItemTags.BOATS).add(
			RisusItems.BONDKNOT_BOAT.get()
		);

		this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
			RisusItems.THREADERS_OF_THE_FIRMAMENT.get(),
			RisusItems.SKIN_BOOTS.get(),
			RisusItems.SKIN_LEGGINGS.get(),
			RisusItems.SKIN_CHESTPLATE.get(),
			RisusItems.SKIN_HELMET.get(),
			RisusItems.SINNER_ROBES_BOOTS.get(),
			RisusItems.SINNER_ROBES_CHESTPLATE.get(),
			RisusItems.SINNER_ROBES_LEGGINGS.get(),
			RisusItems.SINNER_ROBES_HELMET.get()
		);

		this.tag(ItemTags.SKULLS).add(
			RisusItems.BLOODWYRM_HEAD.get()
		);

		this.tag(ItemTags.SMALL_FLOWERS).add(
			RisusBlocks.REGEN_ROSE.get().asItem()
		);

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
		this.tag(ItemTags.HEAD_ARMOR_ENCHANTABLE).add(
			RisusItems.SKIN_HELMET.get(),
			RisusItems.ROSE_CROWN.get(),
			RisusItems.CROWN_OF_BONES.get(),
			RisusItems.SINNER_ROBES_HELMET.get()
		);
		this.tag(ItemTags.CHEST_ARMOR).add(RisusItems.SKIN_CHESTPLATE.get(), RisusItems.SINNER_ROBES_CHESTPLATE.get());
		this.tag(ItemTags.CHEST_ARMOR_ENCHANTABLE).add(RisusItems.SKIN_CHESTPLATE.get(), RisusItems.SINNER_ROBES_CHESTPLATE.get());

		this.tag(ItemTags.LEG_ARMOR).add(RisusItems.SKIN_LEGGINGS.get(), RisusItems.SINNER_ROBES_LEGGINGS.get());
		this.tag(ItemTags.LEG_ARMOR_ENCHANTABLE).add(RisusItems.SKIN_LEGGINGS.get(), RisusItems.SINNER_ROBES_LEGGINGS.get());

		this.tag(ItemTags.FOOT_ARMOR).add(RisusItems.SKIN_BOOTS.get(), RisusItems.THREADERS_OF_THE_FIRMAMENT.get(), RisusItems.SINNER_ROBES_BOOTS.get());
		this.tag(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(RisusItems.SKIN_BOOTS.get(), RisusItems.THREADERS_OF_THE_FIRMAMENT.get(), RisusItems.SINNER_ROBES_BOOTS.get());

		this.tag(ItemTags.ARMOR_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
		this.tag(ItemTags.FOOT_ARMOR_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
		this.tag(ItemTags.VANISHING_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());
		this.tag(ItemTags.EQUIPPABLE_ENCHANTABLE).remove(RisusItems.THREADERS_OF_THE_FIRMAMENT.get());

		this.tag(ItemTags.TRIM_MATERIALS).add(RisusItems.GLUTTONY_SCALES.get(), RisusBlocks.SKIN.asItem(), RisusItems.ORGANIC_MATTER.get());
		this.tag(RisusTags.Items.HURTS_ANGEL_WINGS).add(Items.FIREWORK_ROCKET);
		this.tag(RisusTags.Items.LIGHTLY_HURTS_ANGEL_WINGS).add(Items.TRIDENT, RisusItems.CRESCENT_DISASTER.get());
		this.tag(RisusTags.Items.RAW_MEAT).add(Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.RABBIT, Items.PORKCHOP, Items.BEEF, Items.MUTTON, Items.CHICKEN).addTag(Tags.Items.FOODS_RAW_MEAT);

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

		this.tag(ItemTags.DURABILITY_ENCHANTABLE).add(
			RisusItems.ANGEL_WINGS.get(),
			RisusItems.DIAMOND_TIPPED_ANGEL_WINGS.get()
		);

		this.tag(ItemTags.BEE_FOOD).add(
			RisusBlocks.REGEN_ROSE.asItem()
		);

		this.tag(Tags.Items.STORAGE_BLOCKS).add(
			RisusBlocks.FLATTENED_SCALES_BLOCK.asItem(),
			RisusBlocks.ORGANIC_MATTER_BLOCK.asItem()
		);

		this.tag(RisusTags.Items.STOPS_THREAD_WINGS_RENDERING).add(
			Items.ELYTRA,
			RisusItems.ANGEL_WINGS.get(),
			RisusItems.DIAMOND_TIPPED_ANGEL_WINGS.get()

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

		this.tag(ItemTags.SWORDS).add(
			RisusItems.CARVING_KNIFE.get()
		);
		this.tag(ItemTags.SWORD_ENCHANTABLE).add(
			RisusItems.CARVING_KNIFE.get()
		);

		this.tag(RisusTags.Items.SWORD_AND_TRIDENT_ENCHANTABLE)
			.addTag(ItemTags.WEAPON_ENCHANTABLE)
			.addTag(ItemTags.TRIDENT_ENCHANTABLE)
		;

		this.tag(RisusTags.Items.PERPETUITY_BLACKLIST).add(
			Items.ELYTRA,
			RisusItems.ANGEL_WINGS.get(),
			RisusItems.DIAMOND_TIPPED_ANGEL_WINGS.get()
		);

		this.tag(RisusTags.Items.ENCHANTED_BOOK_EQUIVALENT).add(
			Items.ENCHANTED_BOOK
		);

		this.tag(RisusTags.Items.GOTHIC_FENCES).add(
			RisusBlocks.DARK_FENCE.asItem()
			,RisusBlocks.EERIE_FENCE.asItem()
		);

		this.tag(RisusTags.Items.GRIMSTONES).add(
			RisusBlocks.GRIMSTONE.asItem()
			,RisusBlocks.POLISHED_GRIMSTONE.asItem()
			,RisusBlocks.GRIMSTONE_BRICKS.asItem()
		);

		this.tag(RisusTags.Items.SCYTHES).add(
			RisusItems.SCYTHE.get()
			,RisusItems.SOUL_SCYTHE.get()
			,RisusItems.FIRE_SCYTHE.get()
			,RisusItems.CINDERGLEE_SCYTHE.get()
		);

		this.tag(RisusTags.Items.ALTERATION_STACKING_EXCEPTION).add(
			Items.BOOK,
			Items.PAPER,
			Items.DISC_FRAGMENT_5
		);

		this.tag(RisusTags.Items.GLIDING_WINGS).add(
			RisusItems.ANGEL_WINGS.get(),
			RisusItems.DIAMOND_TIPPED_ANGEL_WINGS.get()
		);

		this.tag(RisusTags.Items.SACRIFICIAL_CATALYST).add(
			RisusItems.SACRIFICE_CATALYST.get()
		);

		this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(
			RisusItems.GLUTTONY_SCALES.get()
		);

		this.tag(Tags.Items.MUSIC_DISCS).add(
			RisusItems.MUSIC_DISC_CYCLE.get()
			,RisusItems.MUSIC_DISC_FEIGR.get()
			,RisusItems.MUSIC_DISC_MORK.get()
			,RisusItems.MUSIC_DISC_RAK.get()
			,RisusItems.MUSIC_DISC_REGN.get()

		);
		this.tag(RisusTags.Items.BOOT_UPGRADE).add(
			RisusItems.LIGHT_DEVOURER.get()
			,RisusItems.COUNTERWEIGHT.get()
			,RisusBlocks.BONDKNOT_LOG.get().asItem()
		);
		this.tag(RisusTags.Items.LEG_UPGRADE).add(
			RisusBlocks.BONDKNOT_LOG.get().asItem()
			,RisusItems.RESEARCHERS_NOTES.get().asItem()
			,Items.BUNDLE.asItem()
			,Items.SHULKER_BOX.asItem()
			,Items.ENDER_CHEST.asItem()
		);
		this.tag(RisusTags.Items.CHEST_UPGRADE).add(
			RisusBlocks.BONDKNOT_LOG.get().asItem()
			,RisusBlocks.MAW_GUTS.get().asItem()
			,RisusItems.HAND_OF_GREED.get().asItem()
			,RisusItems.BORN_TO_BURN.get().asItem()
			,Items.MILK_BUCKET.asItem()
		);
		this.tag(RisusTags.Items.HEAD_UPGRADE).add(
			RisusBlocks.BONDKNOT_LOG.get().asItem()
			,Items.CREEPER_HEAD.asItem()
			,Items.WITHER_SKELETON_SKULL.asItem()
			,Items.SKELETON_SKULL.asItem()
			,Items.ZOMBIE_HEAD.asItem()
			,Items.PIGLIN_HEAD.asItem()
			,Items.LIME_WOOL.asItem()
			,Items.BLACK_WOOL.asItem()
			,Items.LIGHT_GRAY_WOOL.asItem()
			,Items.YELLOW_WOOL.asItem()
			,Items.GRAY_WOOL.asItem()
			,Items.CYAN_WOOL.asItem()
			,Items.LIGHT_BLUE_WOOL.asItem()
			,Items.ORANGE_WOOL.asItem()
			,Items.BLUE_WOOL.asItem()
			,Items.BROWN_WOOL.asItem()
			,Items.WHITE_WOOL.asItem()
			,Items.GREEN_WOOL.asItem()
			,Items.CARVED_PUMPKIN.asItem()
			,Items.TERRACOTTA.asItem()
			,Items.WHITE_TERRACOTTA.asItem()
			,Items.LIGHT_GRAY_TERRACOTTA.asItem()
			,Items.BLACK_TERRACOTTA.asItem()
			,Items.GRAY_TERRACOTTA.asItem()
			,Items.RED_TERRACOTTA.asItem()
			,Items.ORANGE_TERRACOTTA.asItem()
			,Items.YELLOW_TERRACOTTA.asItem()
			,Items.BROWN_TERRACOTTA.asItem()
			,RisusBlocks.EYE_BLEACHED.asItem()
			,RisusBlocks.EYE_BLOODSHOT.asItem()
			,RisusBlocks.EYE_EMERALD.asItem()
			,RisusBlocks.EYE_ENDER.asItem()
			,RisusBlocks.EYE_GOLDEN.asItem()
			,RisusBlocks.ASHEN_REMAINS.asItem()
			,RisusBlocks.SMILING_REMAINS.asItem()
			,Items.SPYGLASS.asItem()
			,Items.SWEET_BERRIES.asItem()
			,Items.RABBIT_FOOT.asItem()
		);
		this.tag(RisusTags.Items.POCKETABLE).add(
			//don't forget to add to LEG_UPGRADE too so it shows up in list
			//if there are colored versions too, like Shulker Box, only add default to LEG_UPGRADE to not clog the list
			Items.BUNDLE.asItem()
			,Items.SHULKER_BOX.asItem()
			,Items.WHITE_SHULKER_BOX.asItem()
			,Items.LIGHT_GRAY_SHULKER_BOX.asItem()
			,Items.GRAY_SHULKER_BOX.asItem()
			,Items.BLACK_SHULKER_BOX.asItem()
			,Items.BROWN_SHULKER_BOX.asItem()
			,Items.RED_SHULKER_BOX.asItem()
			,Items.ORANGE_SHULKER_BOX.asItem()
			,Items.YELLOW_SHULKER_BOX.asItem()
			,Items.LIME_SHULKER_BOX.asItem()
			,Items.GREEN_SHULKER_BOX.asItem()
			,Items.CYAN_SHULKER_BOX.asItem()
			,Items.BLUE_SHULKER_BOX.asItem()
			,Items.LIGHT_BLUE_SHULKER_BOX.asItem()
			,Items.PURPLE_SHULKER_BOX.asItem()
			,Items.MAGENTA_SHULKER_BOX.asItem()
			,Items.PINK_SHULKER_BOX.asItem()
			,Items.ENDER_CHEST.asItem()
		);

		//curios
		this.tag(RisusTags.Items.CURIOS_HANDS).add(
			RisusItems.HAND_OF_GREED.get()
		);

		this.tag(RisusTags.Items.CURIOS_HEAD).add(
			RisusItems.BORN_TO_BURN.get(),
			RisusItems.BLOODWYRM_HEAD.get()
		);

		this.tag(RisusTags.Items.CURIOS_CHARM).add(
			RisusItems.TOTEM_OF_UNYIELDING.get(),
			RisusItems.LUCKY_CHARM.get(),
			RisusItems.WRETCHED_CHARM.get()
		);

		this.tag(RisusTags.Items.CURIOS_BELT).add(
			RisusItems.RESEARCHERS_NOTES.get()
		);

		this.tag(RisusTags.Items.CURIOS_BODY).add(
			RisusItems.COUNTERWEIGHT.get()
		);
	}
}
