package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.data.custom.AlterationRecipeBuilder;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CraftingGenerator extends RecipeProvider {
	private final HolderLookup.Provider provider;
	public CraftingGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
		super(packOutput, provider);
		this.provider = provider.join();
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {

		AlterationRecipeBuilder.alteration(Ingredient.of(Items.NETHERRACK), RisusItems.SMILING_REMAINS.get()).unlockedBy("has_item", has(Items.STONE)).unlockedBy("has_item", has(Items.COBBLESTONE)).unlockedBy("has_item", has(Items.NETHERRACK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.SOUL_SAND, Items.SOUL_SOIL), RisusItems.ASHEN_REMAINS.get()).unlockedBy("has_item", has(Items.SAND)).unlockedBy("has_item", has(Items.SOUL_SAND)).unlockedBy("has_item", has(Items.SOUL_SOIL)).unlockedBy("has_item", has(Items.DIRT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.VINE), RisusItems.SPREADING_REMAINS.get()).unlockedBy("has_item", has(Items.VINE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.TWISTING_VINES), RisusItems.NEURON_STEM.get()).unlockedBy("has_item", has(Items.TWISTING_VINES)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GOLDEN_APPLE), RisusItems.GUILTY_APPLE.get()).unlockedBy("has_item", has(Items.GOLDEN_APPLE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.COBWEB), RisusItems.BLOODWEAVE.get()).unlockedBy("has_item", has(Items.COBWEB)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BASALT, Items.POLISHED_BASALT, Items.SMOOTH_BASALT), RisusItems.ENGRAVED_BASALT.get()).unlockedBy("has_item", has(Items.BASALT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DRAGON_HEAD), RisusItems.BLOODWYRM_HEAD.get()).unlockedBy("has_item", has(Items.DRAGON_HEAD)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRYING_OBSIDIAN), RisusItems.LAUGHING_OBSIDIAN.get()).unlockedBy("has_item", has(Items.CRYING_OBSIDIAN)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ROTTEN_FLESH), RisusItems.ORGANIC_MATTER.get()).unlockedBy("has_item", has(Items.ROTTEN_FLESH)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE), RisusItems.GRIMSTONE.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE), RisusItems.POLISHED_GRIMSTONE.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICKS), RisusItems.GRIMSTONE_BRICKS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS), RisusItems.CRACKED_GRIMSTONE_BRICKS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CHISELED_POLISHED_BLACKSTONE), RisusItems.CHISELED_GRIMSTONE.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE_STAIRS), RisusItems.GRIMSTONE_STAIRS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE_SLAB), RisusItems.GRIMSTONE_SLAB.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE_WALL), RisusItems.GRIMSTONE_WALL.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_STAIRS), RisusItems.POLISHED_GRIMSTONE_STAIRS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_SLAB), RisusItems.POLISHED_GRIMSTONE_SLAB.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_WALL), RisusItems.POLISHED_GRIMSTONE_WALL.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_STAIRS), RisusItems.GRIMSTONE_BRICKS_STAIRS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_SLAB), RisusItems.GRIMSTONE_BRICKS_SLAB.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_WALL), RisusItems.GRIMSTONE_BRICKS_WALL.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		//TODO enchanted book to corrupt book handler
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.PORKCHOP, Items.BEEF, Items.MUTTON, Items.RABBIT, Items.CHICKEN, Items.COD, Items.TROPICAL_FISH, Items.SALMON), RisusItems.TISSUE.get()).unlockedBy("has_item", has(Items.PORKCHOP)).unlockedBy("has_item", has(Items.BEEF)).unlockedBy("has_item", has(Items.MUTTON)).unlockedBy("has_item", has(Items.RABBIT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRIMSON_HYPHAE, Items.CRIMSON_STEM, Items.WARPED_HYPHAE, Items.WARPED_STEM), RisusItems.BURNT_HYPHAE.get()).unlockedBy("has_item", has(Items.CRIMSON_HYPHAE)).unlockedBy("has_item", has(Items.CRIMSON_STEM)).unlockedBy("has_item", has(Items.WARPED_HYPHAE)).unlockedBy("has_item", has(Items.WARPED_STEM)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.WITHER_ROSE), RisusItems.REGEN_ROSE.get()).unlockedBy("has_item", has(Items.WITHER_ROSE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ENDER_EYE), RisusItems.EYE_ENDER.get()).unlockedBy("has_item", has(Items.ENDER_EYE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DECORATED_POT), RisusItems.DEPTH_VASE.get()).unlockedBy("has_item", has(Items.DECORATED_POT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.MILK_BUCKET), RisusItems.BLOOD_BUCKET.get()).unlockedBy("has_item", has(Items.MILK_BUCKET)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusItems.GLUTTONY_SCALES.get()), RisusItems.GLUTTONY_SCALEPLATE.get()).unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get())).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.LEATHER, Items.RABBIT_HIDE), RisusItems.SKIN.get()).unlockedBy("has_item", has(Items.LEATHER)).unlockedBy("has_item", has(Items.RABBIT_HIDE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.SEA_PICKLE), RisusItems.ZIT.get()).unlockedBy("has_item", has(Items.SEA_PICKLE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ITEM_FRAME, Items.GLOW_ITEM_FRAME), RisusItems.DISPLAY_NOTCH.get()).unlockedBy("has_item", has(Items.ITEM_FRAME)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BOOK), RisusItems.RESEARCHERS_NOTES).unlockedBy("has_item", has(Items.BOOK)).save(consumer);
		//bunch of bondknot
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_FENCE_GATE, Items.DARK_OAK_FENCE_GATE, Items.BIRCH_FENCE_GATE, Items.SPRUCE_FENCE_GATE, Items.JUNGLE_FENCE_GATE, Items.ACACIA_FENCE_GATE, Items.MANGROVE_FENCE_GATE, Items.CHERRY_FENCE_GATE), RisusItems.BONDKNOT_FENCE_GATE.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_FENCE, Items.DARK_OAK_FENCE, Items.BIRCH_FENCE, Items.SPRUCE_FENCE, Items.JUNGLE_FENCE, Items.ACACIA_FENCE, Items.MANGROVE_FENCE, Items.CHERRY_FENCE), RisusItems.BONDKNOT_FENCE.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_TRAPDOOR, Items.DARK_OAK_TRAPDOOR, Items.BIRCH_TRAPDOOR, Items.SPRUCE_TRAPDOOR, Items.JUNGLE_TRAPDOOR, Items.ACACIA_TRAPDOOR, Items.MANGROVE_TRAPDOOR, Items.CHERRY_TRAPDOOR), RisusItems.BONDKNOT_TRAPDOOR.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_HANGING_SIGN, Items.DARK_OAK_HANGING_SIGN, Items.BIRCH_HANGING_SIGN, Items.SPRUCE_HANGING_SIGN, Items.JUNGLE_HANGING_SIGN, Items.ACACIA_HANGING_SIGN, Items.MANGROVE_HANGING_SIGN, Items.CHERRY_HANGING_SIGN), RisusItems.BONDKNOT_HANGING_SIGN.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_SIGN, Items.DARK_OAK_SIGN, Items.BIRCH_SIGN, Items.SPRUCE_SIGN, Items.JUNGLE_SIGN, Items.ACACIA_SIGN, Items.MANGROVE_SIGN, Items.CHERRY_SIGN), RisusItems.BONDKNOT_SIGN.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_DOOR, Items.DARK_OAK_DOOR, Items.BIRCH_DOOR, Items.SPRUCE_DOOR, Items.JUNGLE_DOOR, Items.ACACIA_DOOR, Items.MANGROVE_DOOR, Items.CHERRY_DOOR), RisusItems.BONDKNOT_DOOR.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_BUTTON, Items.DARK_OAK_BUTTON, Items.BIRCH_BUTTON, Items.SPRUCE_BUTTON, Items.JUNGLE_BUTTON, Items.ACACIA_BUTTON, Items.MANGROVE_BUTTON, Items.CHERRY_BUTTON), RisusItems.BONDKNOT_BUTTON.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_PRESSURE_PLATE, Items.DARK_OAK_PRESSURE_PLATE, Items.BIRCH_PRESSURE_PLATE, Items.SPRUCE_PRESSURE_PLATE, Items.JUNGLE_PRESSURE_PLATE, Items.ACACIA_PRESSURE_PLATE, Items.MANGROVE_PRESSURE_PLATE, Items.CHERRY_PRESSURE_PLATE), RisusItems.BONDKNOT_PRESSURE_PLATE.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_SLAB, Items.DARK_OAK_SLAB, Items.BIRCH_SLAB, Items.SPRUCE_SLAB, Items.JUNGLE_SLAB, Items.ACACIA_SLAB, Items.MANGROVE_SLAB, Items.CHERRY_SLAB), RisusItems.BONDKNOT_SLAB.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_STAIRS, Items.DARK_OAK_STAIRS, Items.BIRCH_STAIRS, Items.SPRUCE_STAIRS, Items.JUNGLE_STAIRS, Items.ACACIA_STAIRS, Items.MANGROVE_STAIRS, Items.CHERRY_STAIRS), RisusItems.BONDKNOT_STAIRS.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_PLANKS, Items.DARK_OAK_PLANKS, Items.BIRCH_PLANKS, Items.SPRUCE_PLANKS, Items.JUNGLE_PLANKS, Items.ACACIA_PLANKS, Items.MANGROVE_PLANKS, Items.CHERRY_PLANKS), RisusItems.BONDKNOT_PLANKS.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_LOG, Items.DARK_OAK_LOG, Items.BIRCH_LOG, Items.SPRUCE_LOG, Items.JUNGLE_LOG, Items.ACACIA_LOG, Items.MANGROVE_LOG, Items.CHERRY_LOG), RisusItems.BONDKNOT_LOG.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_WOOD, Items.DARK_OAK_WOOD, Items.BIRCH_WOOD, Items.SPRUCE_WOOD, Items.JUNGLE_WOOD, Items.ACACIA_WOOD, Items.MANGROVE_WOOD, Items.CHERRY_WOOD), RisusItems.BONDKNOT_WOOD.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.STRIPPED_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD, Items.STRIPPED_BIRCH_WOOD, Items.STRIPPED_SPRUCE_WOOD, Items.STRIPPED_JUNGLE_WOOD, Items.STRIPPED_ACACIA_WOOD, Items.STRIPPED_MANGROVE_WOOD, Items.STRIPPED_CHERRY_WOOD), RisusItems.STRIPPED_BONDKNOT_WOOD.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.STRIPPED_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, Items.STRIPPED_BIRCH_LOG, Items.STRIPPED_SPRUCE_LOG, Items.STRIPPED_JUNGLE_LOG, Items.STRIPPED_ACACIA_LOG, Items.STRIPPED_MANGROVE_LOG, Items.STRIPPED_CHERRY_LOG), RisusItems.STRIPPED_BONDKNOT_LOG.get())
				.unlockedBy("has_item", has(Items.OAK_LOG)).unlockedBy("has_item", has(Items.DARK_OAK_LOG)).unlockedBy("has_item", has(Items.BIRCH_LOG)).unlockedBy("has_item", has(Items.SPRUCE_LOG)).unlockedBy("has_item", has(Items.JUNGLE_LOG)).unlockedBy("has_item", has(Items.ACACIA_LOG)).unlockedBy("has_item", has(Items.MANGROVE_LOG)).unlockedBy("has_item", has(Items.CHERRY_LOG)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONDKNOT_WOOD.get(), 3)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_LOG.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_LOG.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), 3)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.unlockedBy("has_item", has(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.ORGANIC_MATTER_BLOCK.get(), 1)
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusItems.ORGANIC_MATTER.get()))
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONDKNOT_PLANKS.get(), 4)
				.requires(Ingredient.of(ItemTagGenerator.BONDKNOT_LOGS))
				.unlockedBy("has_item", has(ItemTagGenerator.BONDKNOT_LOGS))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.DISPLAY_NOTCH_STAND.get(), 1)
			.requires(Ingredient.of(RisusBlocks.DISPLAY_NOTCH))
			.unlockedBy("has_item", has(RisusBlocks.DISPLAY_NOTCH))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.DISPLAY_NOTCH.get(), 1)
			.requires(Ingredient.of(RisusBlocks.DISPLAY_NOTCH_STAND))
			.unlockedBy("has_item", has(RisusBlocks.DISPLAY_NOTCH_STAND))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONDKNOT_STAIRS.get(), 4)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONDKNOT_SLAB.get(), 6)
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, RisusBlocks.BONDKNOT_BUTTON.get())
				.requires(Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BONDKNOT_FENCE.get(), 3)
				.pattern("#/#")
				.pattern("#/#")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.IMITATION_SCALEPLATE.get(), 32)
				.pattern("###")
				.pattern("#/#")
				.pattern("###")
				.define('#', Ingredient.of(Tags.Items.OBSIDIANS))
				.define('/', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get(), 5)
				.pattern("/ /")
				.pattern(" / ")
				.pattern("/ /")
				.define('/', Ingredient.of(RisusItems.IMITATION_SCALEPLATE.get()))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get(), 5)
				.pattern(" / ")
				.pattern("///")
				.pattern(" / ")
				.define('/', Ingredient.of(RisusItems.IMITATION_SCALEPLATE.get()))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.TALL_HAIR.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusItems.HAIR_FOLLICLES.get()))
				.unlockedBy("has_item", has(RisusItems.HAIR_FOLLICLES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.BONDKNOT_FENCE_GATE.get())
				.pattern("/#/")
				.pattern("/#/")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.BONDKNOT_PRESSURE_PLATE.get())
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.BONDKNOT_DOOR.get(), 3)
				.pattern("##")
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.BONDKNOT_TRAPDOOR.get(), 2)
				.pattern("###")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BONDKNOT_SIGN.get().asItem(), 3)
				.pattern("###")
				.pattern("###")
				.pattern(" / ")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BONDKNOT_HANGING_SIGN.get().asItem(), 6)
			.pattern("/ /")
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
			.define('/', Ingredient.of(Tags.Items.CHAINS))
			.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, RisusItems.BONDKNOT_BOAT, 1)
			.pattern("# #")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
			.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, RisusItems.GUTS_BOAT, 1)
			.requires(RisusItems.MAW_GUTS.get())
			.requires(RisusItems.BONDKNOT_BOAT.get())
			.unlockedBy("has_item", has(RisusItems.MAW_GUTS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BUNDLE_OF_HAIR.get(), 1)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusItems.TALL_HAIR.get()))
				.unlockedBy("has_item", has(RisusItems.HAIR_FOLLICLES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LINEAR_RITUAL_BLOCK.get(), 1)
				.requires(RisusItems.CURVED_RITUAL_BLOCK.get())
				.unlockedBy("has_item", has(RisusItems.CURVED_RITUAL_BLOCK.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CURVED_RITUAL_BLOCK.get(), 1)
				.requires(RisusItems.LINEAR_RITUAL_BLOCK.get())
				.unlockedBy("has_item", has(RisusItems.LINEAR_RITUAL_BLOCK.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_STAIRS.get(), 4)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_SLAB.get(), 6)
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.GRIMSTONE_BRICKS_WALL.get(), 6)
				.pattern("###")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.GRIMSTONE_WALL.get(), 6)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GRIMSTONE.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.POLISHED_GRIMSTONE_WALL.get(), 6)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CHISELED_GRIMSTONE.get())
				.pattern(" # ")
				.pattern(" # ")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS_SLAB.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLATTENED_SCALES_BLOCK.get(), 1)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(), 32)
				.pattern("###")
				.pattern("#/#")
				.pattern("###")
				.define('#', Ingredient.of(Blocks.OBSIDIAN))
				.define('/', Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK.get()))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLESHY_SKIN.get(), 2)
			.pattern("#")
			.pattern("/")
			.define('#', Ingredient.of(RisusBlocks.SKIN.get()))
			.define('/', Ingredient.of(RisusBlocks.TISSUE.get()))
			.unlockedBy("has_item", has(RisusItems.SKIN.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CURVED_FLESHY_SKIN.get(), 4)
			.pattern("##")
			.pattern("#/")
			.define('#', Ingredient.of(RisusBlocks.SKIN.get()))
			.define('/', Ingredient.of(RisusBlocks.TISSUE.get()))
			.unlockedBy("has_item", has(RisusItems.SKIN.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.HAIRY_SKIN.get(), 1)
			.requires(RisusItems.HAIR_FOLLICLES.get())
			.requires(RisusItems.SKIN.get())
			.unlockedBy("has_item", has(RisusItems.SKIN.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.HAIRY_FLESHY_SKIN.get(), 1)
			.requires(RisusItems.HAIR_FOLLICLES.get())
			.requires(RisusItems.FLESHY_SKIN.get())
			.unlockedBy("has_item", has(RisusItems.SKIN.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get(), 1)
			.requires(RisusItems.HAIR_FOLLICLES.get())
			.requires(RisusItems.CURVED_FLESHY_SKIN.get())
			.unlockedBy("has_item", has(RisusItems.SKIN.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.TISSUE_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.TISSUE.get()))
			.unlockedBy("has_item", has(RisusBlocks.TISSUE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.TISSUE_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.TISSUE.get()))
			.unlockedBy("has_item", has(RisusBlocks.TISSUE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.UNAWAKENED_VESSEL.get())
				.pattern("BGC")
				.pattern("BGL")
				.pattern("BGL")
				.define('B', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
				.define('C', Ingredient.of(RisusItems.CONCENTRATION_CORE.get()))
				.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('L', Ingredient.of(ItemTags.LOGS))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.SCYTHE.get())
			.pattern("GGC")
			.pattern("GBL")
			.pattern("BLL")
			.define('B', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
			.define('C', Ingredient.of(RisusItems.CONCENTRATION_CORE.get()))
			.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
			.define('L', Ingredient.of(RisusTags.Items.BONE_BLOCK_VARIATION))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.BOOMSTICK.get())
			.pattern("TST")
			.pattern("TST")
			.pattern("GSG")
			.define('T', Ingredient.of(Items.TNT))
			.define('S', Ingredient.of(Items.STICK))
			.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, RisusItems.CRESCENT_DISASTER.get())
				.requires(RisusItems.UNAWAKENED_VESSEL.get())
				.requires(RisusItems.BLOOD_FEATHER.get())
				.unlockedBy("has_item", has(RisusItems.BLOOD_FEATHER.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.TOOTHKNOCKER.get())
				.pattern("BBB")
				.pattern("LCL")
				.pattern("LVL")
				.define('B', Ingredient.of(RisusItems.TEETH.get()))
				.define('C', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
				.define('L', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('V', Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK.get()))
				.unlockedBy("has_item", has(RisusItems.TEETH.get()))
				.unlockedBy("has_item", has(RisusItems.CRYSTALLIZED_BOND.get()))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.GOLD_FIST.get())
				.pattern("BVB")
				.pattern("BCB")
				.pattern("LHL")
				.define('B', Ingredient.of(Blocks.GOLD_BLOCK))
				.define('C', Ingredient.of(RisusItems.TOOTHKNOCKER.get()))
				.define('L', Ingredient.of(RisusItems.COAGULATED_BLOOD_BLOCK.get()))
				.define('V', Ingredient.of(Items.GOLD_INGOT))
				.define('H', Ingredient.of(RisusItems.HAND_OF_GREED.get()))
				.unlockedBy("has_item", has(RisusItems.TOOTHKNOCKER.get()))
				.unlockedBy("has_item", has(RisusItems.HAND_OF_GREED.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RisusItems.CONCENTRATION_CORE.get())
				.pattern("BGB")
				.pattern("GMG")
				.pattern("BGB")
				.define('G', Ingredient.of(RisusItems.NEURON_STEM.get()))
				.define('M', Ingredient.of(RisusItems.MEMORY_CORE.get()))
				.define('B', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_item", has(RisusItems.MEMORY_CORE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RisusItems.LIGHT_DEVOURER.get())
				.pattern("FBS")
				.pattern("BPB")
				.pattern("GBL")
				.define('F', Ingredient.of(ItemTagGenerator.FROGLIGHT_BLOCKS))
				.define('P', Ingredient.of(Items.ENDER_PEARL))
				.define('B', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('S', Ingredient.of(Items.GLOWSTONE))
				.define('L', Ingredient.of(Items.SHROOMLIGHT))
				.define('G', Ingredient.of(Items.SEA_LANTERN))
				.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BIG_CHAIN.get(), 8)
				.pattern("I I")
				.pattern("III")
				.pattern("I I")
				.define('I', Ingredient.of(Items.IRON_INGOT))
				.unlockedBy("has_item", has(Items.IRON_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.JOYFLAME_CAMPFIRE.get())
				.pattern(" S ")
				.pattern("S#S")
				.pattern("LLL")
				.define('L', ItemTags.LOGS)
				.define('S', Items.STICK)
				.define('#', ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS)
				.unlockedBy("has_item", has(Items.STICK))
				.unlockedBy("has_item", has(ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.JOYFLAME_TORCH.get(), 4)
				.pattern("X")
				.pattern("#")
				.pattern("S")
				.define('X', Ingredient.of(Items.COAL, Items.CHARCOAL))
				.define('#', Items.STICK)
				.define('S', ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS)
				.unlockedBy("has_item", has(ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.JOYFLAME_LANTERN.get())
				.pattern("XXX")
				.pattern("X#X")
				.pattern("XXX")
				.define('#', RisusItems.JOYFLAME_TORCH.get())
				.define('X', Items.IRON_NUGGET)
				.unlockedBy("has_item", has(RisusItems.JOYFLAME_TORCH.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.TEETH.get(), 16)
				.pattern("XXX")
				.pattern("X#X")
				.pattern("Y Y")
				.define('#', RisusItems.TISSUE.get())
				.define('X', RisusTags.Items.BONE_BLOCK_VARIATION)
				.define('Y', RisusItems.BONE_WALL.get())
				.unlockedBy("has_item", has(RisusItems.TISSUE.get()))
				.unlockedBy("has_item", has(RisusItems.TEETH.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONE_WALL.get(), 6)
			.pattern("XXX")
			.pattern("XXX")
			.define('X', Items.BONE_BLOCK)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONE_STAIRS.get(), 4)
			.pattern("X  ")
			.pattern("XX ")
			.pattern("XXX")
			.define('X', Items.BONE_BLOCK)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONE_SLAB.get(), 6)
			.pattern("XXX")
			.define('X', Items.BONE_BLOCK)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_BONE_BLOCK.get(), 4)
			.pattern("XX")
			.pattern("XX")
			.define('X', Items.BONE_BLOCK)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_BONE_STAIRS.get(), 4)
			.pattern("X  ")
			.pattern("XX ")
			.pattern("XXX")
			.define('X', RisusBlocks.FULL_BONE_BLOCK.get())
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_BONE_SLAB.get(), 6)
			.pattern("XXX")
			.define('X', RisusBlocks.FULL_BONE_BLOCK.get())
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_BLEACHED.get(), 1)
				.requires(RisusItems.TEETH.get())
				.requires(RisusItems.EYE_ENDER.get())
				.requires(Items.CHARCOAL)
				.unlockedBy("has_item", has(RisusItems.EYE_ENDER.get()))
				.unlockedBy("has_item", has(RisusItems.TEETH.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_GOLDEN.get(), 1)
				.requires(RisusItems.EYE_ENDER.get())
				.requires(Items.GOLD_INGOT)
				.unlockedBy("has_item", has(RisusItems.EYE_ENDER.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_EMERALD.get(), 1)
				.requires(RisusItems.EYE_ENDER.get())
				.requires(Items.EMERALD)
				.unlockedBy("has_item", has(RisusItems.EYE_ENDER.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_BLOODSHOT.get(), 1)
				.requires(RisusItems.EYE_ENDER.get())
				.requires(RisusItems.ORGANIC_MATTER.get())
				.unlockedBy("has_item", has(RisusItems.EYE_ENDER.get()))
				.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
				.save(consumer);

		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.CHISELED_GRIMSTONE,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.POLISHED_GRIMSTONE,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_STAIRS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_STAIRS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.POLISHED_GRIMSTONE_STAIRS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_SLAB,2);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_SLAB,2);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.POLISHED_GRIMSTONE_SLAB,2);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_WALL,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_WALL,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.POLISHED_GRIMSTONE_WALL,1);

		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.CHISELED_GRIMSTONE,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_STAIRS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.POLISHED_GRIMSTONE_STAIRS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_SLAB,2);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.POLISHED_GRIMSTONE_SLAB,2);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_WALL,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.POLISHED_GRIMSTONE_WALL,1);

		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.CHISELED_GRIMSTONE,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_STAIRS,1);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_SLAB,2);
		SingleItemRecipeBuilder.stonecutting( Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS,RisusBlocks.GRIMSTONE_BRICKS_WALL,1);

		smeltingRecipe(RisusBlocks.GRIMSTONE_BRICKS.get(),RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get().asItem(),0.1F,1).save(consumer, prefix("smelt_cracked_grimstone_bricks"));
	}

	public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike input, ItemLike result, float exp) {
		return smeltingRecipe(input, result, exp, 1);
	}

	public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike input, ItemLike result, float exp, int count) {
		return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(input, count)), RecipeCategory.MISC, result, exp, 200)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(input.asItem()), has(input));
	}
	private ResourceLocation prefix(String prefix) {
		return ResourceLocation.fromNamespaceAndPath(Risus.MODID, prefix);
	}
}
