package com.bigdious.risus.data;

import com.bigdious.risus.data.custom.AlterationRecipeBuilder;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CraftingGenerator extends RecipeProvider {
	public CraftingGenerator(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

		AlterationRecipeBuilder.alteration(Ingredient.of(Items.NETHERRACK), RisusItems.SMILING_REMAINS.get()).unlockedBy("has_stone", has(Items.STONE)).unlockedBy("has_cobble", has(Items.COBBLESTONE)).unlockedBy("has_netherrack", has(Items.NETHERRACK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.SOUL_SAND, Items.SOUL_SOIL), RisusItems.ASHEN_REMAINS.get()).unlockedBy("has_sand", has(Items.SAND)).unlockedBy("has_soul_sand", has(Items.SOUL_SAND)).unlockedBy("has_soul_soil", has(Items.SOUL_SOIL)).unlockedBy("has_dirt", has(Items.DIRT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.VINE), RisusItems.SPREADING_REMAINS.get()).unlockedBy("has_vine", has(Items.VINE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.TWISTING_VINES), RisusItems.NEURON_STEM.get()).unlockedBy("has_twisting_vine", has(Items.TWISTING_VINES)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GOLDEN_APPLE), RisusItems.GUILTY_APPLE.get()).unlockedBy("has_apple", has(Items.GOLDEN_APPLE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.COBWEB), RisusItems.BLOODWEAVE.get()).unlockedBy("has_cobweb", has(Items.COBWEB)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BASALT, Items.POLISHED_BASALT, Items.SMOOTH_BASALT), RisusItems.ENGRAVED_BASALT.get()).unlockedBy("has_basalt", has(Items.BASALT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DRAGON_HEAD), RisusItems.BLOODWYRM_HEAD.get()).unlockedBy("has_dragon_head", has(Items.DRAGON_HEAD)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRYING_OBSIDIAN), RisusItems.LAUGHING_OBSIDIAN.get()).unlockedBy("has_crying_obsidian", has(Items.CRYING_OBSIDIAN)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ROTTEN_FLESH), RisusItems.ORGANIC_MATTER.get()).unlockedBy("has_flesh", has(Items.ROTTEN_FLESH)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE), RisusItems.GRIMSTONE.get()).unlockedBy("has_blackstone", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE), RisusItems.POLISHED_GRIMSTONE.get()).unlockedBy("has_blackstone", has(Items.POLISHED_BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICKS), RisusItems.GRIMSTONE_BRICKS.get()).unlockedBy("has_blackstone", has(Items.POLISHED_BLACKSTONE_BRICKS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS), RisusItems.CRACKED_GRIMSTONE_BRICKS.get()).unlockedBy("has_blackstone", has(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CHISELED_POLISHED_BLACKSTONE), RisusItems.CHISELED_GRIMSTONE.get()).unlockedBy("has_blackstone", has(Items.CHISELED_POLISHED_BLACKSTONE)).save(consumer);
		//TODO enchanted book to corrupt book handler
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.PORKCHOP, Items.BEEF, Items.MUTTON, Items.RABBIT, Items.CHICKEN, Items.COD, Items.TROPICAL_FISH, Items.SALMON), RisusItems.TISSUE.get()).unlockedBy("has_pork", has(Items.PORKCHOP)).unlockedBy("has_beef", has(Items.BEEF)).unlockedBy("has_mutton", has(Items.MUTTON)).unlockedBy("has_rabbit", has(Items.RABBIT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRIMSON_HYPHAE, Items.CRIMSON_STEM, Items.WARPED_HYPHAE, Items.WARPED_STEM), RisusItems.BURNT_HYPHAE.get()).unlockedBy("has_hyphae", has(Items.CRIMSON_HYPHAE)).unlockedBy("has_cstem", has(Items.CRIMSON_STEM)).unlockedBy("has_whyphae", has(Items.WARPED_HYPHAE)).unlockedBy("has_wstem", has(Items.WARPED_STEM)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.WITHER_ROSE), RisusItems.REGEN_ROSE.get()).unlockedBy("has_wither_rose", has(Items.WITHER_ROSE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ENDER_EYE), RisusItems.EYE_ENDER.get()).unlockedBy("has_ender_eye", has(Items.ENDER_EYE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DECORATED_POT), RisusItems.DEPTH_VASE.get()).unlockedBy("has_decorated_pot", has(Items.DECORATED_POT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.MILK_BUCKET), RisusItems.BLOOD_BUCKET.get()).unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusItems.GLUTTONY_SCALES.get()), RisusItems.GLUTTONY_SCALEPLATE.get()).unlockedBy("has_gluttony_scales", has(RisusItems.GLUTTONY_SCALES.get())).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.LEATHER, Items.RABBIT_HIDE), RisusItems.SKIN.get()).unlockedBy("has_leather", has(Items.LEATHER)).unlockedBy("has_rabbit_hide", has(Items.RABBIT_HIDE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.SEA_PICKLE), RisusItems.ZIT.get()).unlockedBy("has_sea_pickle", has(Items.SEA_PICKLE)).save(consumer);

		//bunch of bondknot
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_FENCE_GATE, Items.DARK_OAK_FENCE_GATE, Items.BIRCH_FENCE_GATE, Items.SPRUCE_FENCE_GATE, Items.JUNGLE_FENCE_GATE, Items.ACACIA_FENCE_GATE, Items.MANGROVE_FENCE_GATE, Items.CHERRY_FENCE_GATE), RisusItems.BONDKNOT_FENCE_GATE.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_FENCE, Items.DARK_OAK_FENCE, Items.BIRCH_FENCE, Items.SPRUCE_FENCE, Items.JUNGLE_FENCE, Items.ACACIA_FENCE, Items.MANGROVE_FENCE, Items.CHERRY_FENCE), RisusItems.BONDKNOT_FENCE.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_TRAPDOOR, Items.DARK_OAK_TRAPDOOR, Items.BIRCH_TRAPDOOR, Items.SPRUCE_TRAPDOOR, Items.JUNGLE_TRAPDOOR, Items.ACACIA_TRAPDOOR, Items.MANGROVE_TRAPDOOR, Items.CHERRY_TRAPDOOR), RisusItems.BONDKNOT_TRAPDOOR.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_HANGING_SIGN, Items.DARK_OAK_HANGING_SIGN, Items.BIRCH_HANGING_SIGN, Items.SPRUCE_HANGING_SIGN, Items.JUNGLE_HANGING_SIGN, Items.ACACIA_HANGING_SIGN, Items.MANGROVE_HANGING_SIGN, Items.CHERRY_HANGING_SIGN), RisusItems.BONDKNOT_HANGING_SIGN.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_SIGN, Items.DARK_OAK_SIGN, Items.BIRCH_SIGN, Items.SPRUCE_SIGN, Items.JUNGLE_SIGN, Items.ACACIA_SIGN, Items.MANGROVE_SIGN, Items.CHERRY_SIGN), RisusItems.BONDKNOT_SIGN.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_DOOR, Items.DARK_OAK_DOOR, Items.BIRCH_DOOR, Items.SPRUCE_DOOR, Items.JUNGLE_DOOR, Items.ACACIA_DOOR, Items.MANGROVE_DOOR, Items.CHERRY_DOOR), RisusItems.BONDKNOT_DOOR.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_BUTTON, Items.DARK_OAK_BUTTON, Items.BIRCH_BUTTON, Items.SPRUCE_BUTTON, Items.JUNGLE_BUTTON, Items.ACACIA_BUTTON, Items.MANGROVE_BUTTON, Items.CHERRY_BUTTON), RisusItems.BONDKNOT_BUTTON.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_PRESSURE_PLATE, Items.DARK_OAK_PRESSURE_PLATE, Items.BIRCH_PRESSURE_PLATE, Items.SPRUCE_PRESSURE_PLATE, Items.JUNGLE_PRESSURE_PLATE, Items.ACACIA_PRESSURE_PLATE, Items.MANGROVE_PRESSURE_PLATE, Items.CHERRY_PRESSURE_PLATE), RisusItems.BONDKNOT_PRESSURE_PLATE.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_SLAB, Items.DARK_OAK_SLAB, Items.BIRCH_SLAB, Items.SPRUCE_SLAB, Items.JUNGLE_SLAB, Items.ACACIA_SLAB, Items.MANGROVE_SLAB, Items.CHERRY_SLAB), RisusItems.BONDKNOT_SLAB.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_STAIRS, Items.DARK_OAK_STAIRS, Items.BIRCH_STAIRS, Items.SPRUCE_STAIRS, Items.JUNGLE_STAIRS, Items.ACACIA_STAIRS, Items.MANGROVE_STAIRS, Items.CHERRY_STAIRS), RisusItems.BONDKNOT_STAIRS.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_PLANKS, Items.DARK_OAK_PLANKS, Items.BIRCH_PLANKS, Items.SPRUCE_PLANKS, Items.JUNGLE_PLANKS, Items.ACACIA_PLANKS, Items.MANGROVE_PLANKS, Items.CHERRY_PLANKS), RisusItems.BONDKNOT_PLANKS.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_LOG, Items.DARK_OAK_LOG, Items.BIRCH_LOG, Items.SPRUCE_LOG, Items.JUNGLE_LOG, Items.ACACIA_LOG, Items.MANGROVE_LOG, Items.CHERRY_LOG), RisusItems.BONDKNOT_LOG.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.OAK_WOOD, Items.DARK_OAK_WOOD, Items.BIRCH_WOOD, Items.SPRUCE_WOOD, Items.JUNGLE_WOOD, Items.ACACIA_WOOD, Items.MANGROVE_WOOD, Items.CHERRY_WOOD), RisusItems.BONDKNOT_WOOD.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.STRIPPED_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD, Items.STRIPPED_BIRCH_WOOD, Items.STRIPPED_SPRUCE_WOOD, Items.STRIPPED_JUNGLE_WOOD, Items.STRIPPED_ACACIA_WOOD, Items.STRIPPED_MANGROVE_WOOD, Items.STRIPPED_CHERRY_WOOD), RisusItems.STRIPPED_BONDKNOT_WOOD.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.STRIPPED_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, Items.STRIPPED_BIRCH_LOG, Items.STRIPPED_SPRUCE_LOG, Items.STRIPPED_JUNGLE_LOG, Items.STRIPPED_ACACIA_LOG, Items.STRIPPED_MANGROVE_LOG, Items.STRIPPED_CHERRY_LOG), RisusItems.STRIPPED_BONDKNOT_LOG.get())
			.unlockedBy("has_oak_log", has(Items.OAK_LOG)).unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG)).unlockedBy("has_birch_log", has(Items.BIRCH_LOG)).unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG)).unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG)).unlockedBy("has_acacia_log", has(Items.ACACIA_LOG)).unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG)).unlockedBy("has_cherry_log", has(Items.CHERRY_LOG)).save(consumer);

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

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONDKNOT_PLANKS.get(), 4)
				.requires(Ingredient.of(ItemTagGenerator.BONDKNOT_LOGS))
				.unlockedBy("has_item", has(ItemTagGenerator.BONDKNOT_LOGS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONDKNOT_STAIRS.get(), 8)
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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BONDKNOT_FENCE.get(), 6)
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
			.define('#', Ingredient.of(Tags.Items.OBSIDIAN))
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

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.BONDKNOT_TRAPDOOR.get(), 6)
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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BUNDLE_OF_HAIR.get(), 1)
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(RisusItems.TALL_HAIR.get()))
			.unlockedBy("has_item", has(RisusItems.HAIR_FOLLICLES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LINEAR_RITUAL_BLOCK.get(), 1)
				.requires(RisusItems.CURVED_RITUAL_BLOCK.get())
				.unlockedBy("has_critual", has(RisusItems.CURVED_RITUAL_BLOCK.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CURVED_RITUAL_BLOCK.get(), 1)
				.requires(RisusItems.LINEAR_RITUAL_BLOCK.get())
				.unlockedBy("has_lritual", has(RisusItems.LINEAR_RITUAL_BLOCK.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_STAIRS.get(), 8)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_SLAB.get(), 6)
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.GRIMSTONE_WALL.get(), 6)
				.pattern("###")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CHISELED_GRIMSTONE.get())
				.pattern(" # ")
				.pattern(" # ")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_SLAB.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_SLAB.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
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

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.UNAWAKENED_VESSEL.get())
				.pattern("BGC")
				.pattern("BGL")
				.pattern("BGL")
				.define('B', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
				.define('C', Ingredient.of(RisusItems.CONCENTRATION_CORE.get()))
				.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('L', Ingredient.of(ItemTags.LOGS))
				.unlockedBy("has_core", has(RisusItems.CONCENTRATION_CORE.get()))
				.unlockedBy("has_bonds", has(RisusItems.CRYSTALLIZED_BOND.get()))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, RisusItems.CRESCENT_DISASTER.get())
				.requires(RisusItems.UNAWAKENED_VESSEL.get())
				.requires(RisusItems.BLOOD_FEATHER.get())
				.unlockedBy("has_feather", has(RisusItems.BLOOD_FEATHER.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.TOOTHKNOCKER.get())
				.pattern("BBB")
				.pattern("LCL")
				.pattern("LVL")
				.define('B', Ingredient.of(RisusItems.TEETH.get()))
				.define('C', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
				.define('L', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('V', Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK.get()))
				.unlockedBy("has_teeth", has(RisusItems.TEETH.get()))
				.unlockedBy("has_bonds", has(RisusItems.CRYSTALLIZED_BOND.get()))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
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
			.unlockedBy("has_toothknocker", has(RisusItems.TOOTHKNOCKER.get()))
			.unlockedBy("has_hand_of_greed", has(RisusItems.HAND_OF_GREED.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RisusItems.CONCENTRATION_CORE.get())
				.pattern("BGB")
				.pattern("GMG")
				.pattern("BGB")
				.define('G', Ingredient.of(RisusItems.NEURON_STEM.get()))
				.define('M', Ingredient.of(RisusItems.MEMORY_CORE.get()))
				.define('B', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_core", has(RisusItems.MEMORY_CORE.get()))
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
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
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
				.unlockedBy("has_stick", has(Items.STICK))
				.unlockedBy("has_remains", has(ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.JOYFLAME_TORCH.get(), 4)
				.pattern("X")
				.pattern("#")
				.pattern("S")
				.define('X', Ingredient.of(Items.COAL, Items.CHARCOAL))
				.define('#', Items.STICK)
				.define('S', ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS)
				.unlockedBy("has_remains", has(ItemTagGenerator.JOYFLAME_FIRE_BASE_BLOCKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.JOYFLAME_LANTERN.get())
				.pattern("XXX")
				.pattern("X#X")
				.pattern("XXX")
				.define('#', RisusItems.JOYFLAME_TORCH.get())
				.define('X', Items.IRON_NUGGET)
				.unlockedBy("has_soul_torch", has(RisusItems.JOYFLAME_TORCH.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.TEETH.get(), 16)
				.pattern("XXX")
				.pattern("X#X")
				.pattern("Y Y")
				.define('#', RisusItems.TISSUE.get())
				.define('X', Items.BONE_BLOCK)
				.define('Y', RisusItems.BONE_WALL.get())
				.unlockedBy("has_tissue", has(RisusItems.TISSUE.get()))
				.unlockedBy("has_teeth", has(RisusItems.TEETH.get()))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_BLEACHED.get(), 1)
				.requires(RisusItems.TEETH.get())
				.requires(RisusItems.EYE_ENDER.get())
				.requires(Items.CHARCOAL)
				.unlockedBy("has_item", has(RisusItems.EYE_ENDER.get()))
				.unlockedBy("has_item1", has(RisusItems.TEETH.get()))
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
				.unlockedBy("has_item1", has(RisusItems.ORGANIC_MATTER.get()))
				.save(consumer);
	}
}
