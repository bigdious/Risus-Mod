package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.data.custom.AlterationRecipeBuilder;
import com.bigdious.risus.data.custom.SmithingUpgradeRecipe;
import com.bigdious.risus.data.custom.SmithingUpgradeRecipeBuilder;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class CraftingGenerator extends RecipeProvider {
	public CraftingGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
		super(packOutput, provider);
		HolderLookup.Provider provider1 = provider.join();
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {

		AlterationRecipeBuilder.alteration(Ingredient.of(Items.NETHERRACK), RisusBlocks.SMILING_REMAINS.asItem()).unlockedBy("has_item", has(Items.STONE)).unlockedBy("has_item", has(Items.COBBLESTONE)).unlockedBy("has_item", has(Items.NETHERRACK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.SOUL_SAND, Items.SOUL_SOIL), RisusBlocks.ASHEN_REMAINS.get()).unlockedBy("has_item", has(Items.SAND)).unlockedBy("has_item", has(Items.SOUL_SAND)).unlockedBy("has_item", has(Items.SOUL_SOIL)).unlockedBy("has_item", has(Items.DIRT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GLOW_LICHEN), RisusBlocks.SPREADING_REMAINS.get()).unlockedBy("has_item", has(Items.VINE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.TWISTING_VINES), RisusBlocks.NEURON_HEAD.get()).unlockedBy("has_item", has(Items.TWISTING_VINES)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.WEEPING_VINES), RisusItems.VEINS.get()).unlockedBy("has_item", has(Items.WEEPING_VINES)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GOLDEN_APPLE), RisusItems.GUILTY_APPLE.get()).unlockedBy("has_item", has(Items.GOLDEN_APPLE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BASALT, Items.POLISHED_BASALT, Items.SMOOTH_BASALT), RisusBlocks.ENGRAVED_BASALT.get()).unlockedBy("has_item", has(Items.BASALT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.COBWEB), RisusBlocks.BLOODWEAVE.get()).unlockedBy("has_item", has(Items.COBWEB)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DRAGON_HEAD), RisusItems.BLOODWYRM_HEAD.get()).unlockedBy("has_item", has(Items.DRAGON_HEAD)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRYING_OBSIDIAN), RisusBlocks.LAUGHING_OBSIDIAN.get()).unlockedBy("has_item", has(Items.CRYING_OBSIDIAN)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ROTTEN_FLESH), RisusItems.ORGANIC_MATTER.get()).unlockedBy("has_item", has(Items.ROTTEN_FLESH)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE), RisusBlocks.GRIMSTONE.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GILDED_BLACKSTONE), RisusBlocks.ACTIVE_GRIMSTONE.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE), RisusBlocks.POLISHED_GRIMSTONE.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICKS), RisusBlocks.GRIMSTONE_BRICKS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS), RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CHISELED_POLISHED_BLACKSTONE), RisusBlocks.CHISELED_GRIMSTONE.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE_STAIRS), RisusBlocks.GRIMSTONE_STAIRS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE_SLAB), RisusBlocks.GRIMSTONE_SLAB.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACKSTONE_WALL), RisusBlocks.GRIMSTONE_WALL.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_STAIRS), RisusBlocks.POLISHED_GRIMSTONE_STAIRS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_SLAB), RisusBlocks.POLISHED_GRIMSTONE_SLAB.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_WALL), RisusBlocks.POLISHED_GRIMSTONE_WALL.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_STAIRS), RisusBlocks.GRIMSTONE_BRICKS_STAIRS.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_SLAB), RisusBlocks.GRIMSTONE_BRICKS_SLAB.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_WALL), RisusBlocks.GRIMSTONE_BRICKS_WALL.get()).unlockedBy("has_item", has(Items.BLACKSTONE)).save(consumer);
		//TODO enchanted book to corrupt book handler
		AlterationRecipeBuilder.alteration(Ingredient.of(Tags.Items.FOODS_RAW_MEAT), RisusBlocks.TISSUE.get()).unlockedBy("has_item", has(Items.PORKCHOP)).unlockedBy("has_item", has(Items.BEEF)).unlockedBy("has_item", has(Items.MUTTON)).unlockedBy("has_item", has(Items.RABBIT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.CRIMSON_HYPHAE, Items.CRIMSON_STEM, Items.WARPED_HYPHAE, Items.WARPED_STEM), RisusBlocks.BURNT_HYPHAE.get()).unlockedBy("has_item", has(Items.CRIMSON_HYPHAE)).unlockedBy("has_item", has(Items.CRIMSON_STEM)).unlockedBy("has_item", has(Items.WARPED_HYPHAE)).unlockedBy("has_item", has(Items.WARPED_STEM)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.WITHER_ROSE), RisusBlocks.REGEN_ROSE.get()).unlockedBy("has_item", has(Items.WITHER_ROSE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DECORATED_POT), RisusBlocks.DEPTH_VASE.get()).unlockedBy("has_item", has(Items.DECORATED_POT)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.MILK_BUCKET), RisusItems.BLOOD_BUCKET.get()).unlockedBy("has_item", has(Items.MILK_BUCKET)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.LEATHER, Items.RABBIT_HIDE), RisusBlocks.SKIN.get()).unlockedBy("has_item", has(Items.LEATHER)).unlockedBy("has_item", has(Items.LEATHER)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.LEATHER_HELMET), RisusItems.SKIN_HELMET.get()).unlockedBy("has_item", has(Items.LEATHER)).unlockedBy("has_item", has(Items.LEATHER)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.LEATHER_CHESTPLATE), RisusItems.SKIN_CHESTPLATE.get()).unlockedBy("has_item", has(Items.LEATHER)).unlockedBy("has_item", has(Items.LEATHER)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.LEATHER_LEGGINGS), RisusItems.SKIN_LEGGINGS.get()).unlockedBy("has_item", has(Items.LEATHER)).unlockedBy("has_item", has(Items.LEATHER)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.LEATHER_BOOTS), RisusItems.SKIN_BOOTS.get()).unlockedBy("has_item", has(Items.LEATHER)).unlockedBy("has_item", has(Items.LEATHER)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.SEA_PICKLE), RisusBlocks.ZIT.get()).unlockedBy("has_item", has(Items.SEA_PICKLE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.ITEM_FRAME, Items.GLOW_ITEM_FRAME), RisusBlocks.DISPLAY_NOTCH.get()).unlockedBy("has_item", has(Items.ITEM_FRAME)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BOOK), RisusItems.RESEARCHERS_NOTES).unlockedBy("has_item", has(Items.BOOK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BLACK_GLAZED_TERRACOTTA), RisusBlocks.CURVED_RITUAL_BLOCK).unlockedBy("has_item", has(Items.CLAY_BALL)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GLASS), RisusBlocks.CONTAINMENT_GLASS).unlockedBy("has_item", has(Items.GLASS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.PAPER), RisusItems.SMILE_PATTERN).unlockedBy("has_item", has(Items.PAPER)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.DISC_FRAGMENT_5), RisusItems.MUSIC_DISC_RAK).unlockedBy("has_item", has(Items.DISC_FRAGMENT_5)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.TOTEM_OF_UNDYING), RisusItems.TOTEM_OF_UNYIELDING).unlockedBy("has_item", has(Items.TOTEM_OF_UNDYING)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusItems.LUCKY_CHARM), RisusItems.WRETCHED_CHARM).unlockedBy("has_item", has(RisusBlocks.REGEN_ROSE)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.GOAT_HORN), RisusItems.WARHORN).unlockedBy("has_item", has(Items.GOAT_HORN)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.IRON_BARS), RisusBlocks.EERIE_FENCE).unlockedBy("has_item", has(Items.IRON_BARS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.IRON_TRAPDOOR), RisusBlocks.EERIE_TRAPDOOR).unlockedBy("has_item", has(Items.IRON_TRAPDOOR)).save(consumer);

		//bone to fossil
		AlterationRecipeBuilder.alteration(Ingredient.of(Items.BONE_BLOCK), RisusBlocks.FOSSIL).unlockedBy("has_item", has(Items.BONE_BLOCK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusBlocks.BONE_STAIRS), RisusBlocks.FOSSIL_STAIRS).unlockedBy("has_item", has(Items.BONE_BLOCK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusBlocks.BONE_SLAB), RisusBlocks.FOSSIL_SLAB).unlockedBy("has_item", has(Items.BONE_BLOCK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusBlocks.BONE_WALL), RisusBlocks.FOSSIL_WALL).unlockedBy("has_item", has(Items.BONE_BLOCK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusBlocks.FULL_BONE_BLOCK), RisusBlocks.FULL_FOSSIL).unlockedBy("has_item", has(Items.BONE_BLOCK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusBlocks.FULL_BONE_STAIRS), RisusBlocks.FULL_FOSSIL_STAIRS).unlockedBy("has_item", has(Items.BONE_BLOCK)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusBlocks.FULL_BONE_SLAB), RisusBlocks.FULL_FOSSIL_SLAB).unlockedBy("has_item", has(Items.BONE_BLOCK)).save(consumer);
		//bunch of bondknot
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_GATES), RisusBlocks.BONDKNOT_FENCE_GATE.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_FENCES), RisusBlocks.BONDKNOT_FENCE.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_TRAPDOORS), RisusBlocks.BONDKNOT_TRAPDOOR.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_HANGING_SIGNS), RisusItems.BONDKNOT_HANGING_SIGN.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_SIGNS), RisusItems.BONDKNOT_SIGN.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_DOORS), RisusBlocks.BONDKNOT_DOOR.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_BUTTONS), RisusBlocks.BONDKNOT_BUTTON.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_PRESSURE_PLATES), RisusBlocks.BONDKNOT_PRESSURE_PLATE.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_SLABS), RisusBlocks.BONDKNOT_SLAB.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_STAIRS), RisusBlocks.BONDKNOT_STAIRS.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_PLANKS), RisusBlocks.BONDKNOT_PLANKS.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);
		AlterationRecipeBuilder.alteration(Ingredient.of(RisusTags.Items.ALTERABLE_LOGS), RisusBlocks.BONDKNOT_LOG.get())
			.unlockedBy("has_item", has(RisusTags.Items.ALTERABLE_LOGS)).save(consumer);

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

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.ASHEN_SPIRE.get(), 8)
			.pattern("#")
			.pattern("#")
			.define('#', Ingredient.of(RisusBlocks.ASHEN_REMAINS.get()))
			.unlockedBy("has_item", has(RisusBlocks.ASHEN_REMAINS.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusItems.ORGANIC_MATTER.get(), 9)
			.requires(Ingredient.of(RisusBlocks.ORGANIC_MATTER_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusItems.ROSE_PATTERN.get(), 1)
			.requires(Ingredient.of(RisusBlocks.REGEN_ROSE.get()))
			.requires(Ingredient.of(Items.PAPER))
			.unlockedBy("has_item", has(Items.PAPER))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.MAW_GUTS.asItem(), 1)
			.requires(Ingredient.of(RisusItems.GUTS_BOAT.get()))
			.unlockedBy("has_item", has(RisusItems.GUTS_BOAT.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusItems.CONCENTRATION_CORE.get(), 1)
			.requires(Ingredient.of(RisusItems.LITTER.get()))
			.unlockedBy("has_item", has(RisusItems.LITTER.get()))
			.save(consumer, "litter_to_concentration_core");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.COPPER_AMALGAM.get(), 1)
			.pattern("###")
			.pattern("#M#")
			.pattern("###")
			.define('#', Ingredient.of(Items.COPPER_INGOT))
			.define('M', Ingredient.of(RisusBlocks.MAW_GUTS.get()))
			.unlockedBy("has_item", has(RisusBlocks.MAW_GUTS.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONDKNOT_PLANKS.get(), 4)
			.requires(Ingredient.of(RisusTags.Items.BONDKNOT_LOGS))
			.unlockedBy("has_item", has(RisusTags.Items.BONDKNOT_LOGS))
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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.IMITATION_SCALEPLATE.get(), 4)
			.pattern("#")
			.define('#', Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer, "imit_scaleplate_from_imit_block");

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.FLOWERING_IMITATION_SCALEPLATE.get(), 5)
			.pattern("/ /")
			.pattern(" / ")
			.pattern("/ /")
			.define('/', Ingredient.of(RisusBlocks.IMITATION_SCALEPLATE.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BUDDING_IMITATION_SCALEPLATE.get(), 5)
			.pattern(" / ")
			.pattern("///")
			.pattern(" / ")
			.define('/', Ingredient.of(RisusBlocks.IMITATION_SCALEPLATE.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get(), 32)
			.pattern("###")
			.pattern("#/#")
			.pattern("###")
			.define('#', Ingredient.of(Tags.Items.OBSIDIANS))
			.define('/', Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.IMITATION_SCALES_BLOCK_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.IMITATION_SCALES_BLOCK_WALL.get(), 6)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.UNALLOYED_SCALEPLATE.get(), 4)
			.pattern("#")
			.define('#', Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.FLOWERING_UNALLOYED_SCALEPLATE.get(), 5)
			.pattern("/ /")
			.pattern(" / ")
			.pattern("/ /")
			.define('/', Ingredient.of(RisusBlocks.UNALLOYED_SCALEPLATE.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BUDDING_UNALLOYED_SCALEPLATE.get(), 5)
			.pattern(" / ")
			.pattern("///")
			.pattern(" / ")
			.define('/', Ingredient.of(RisusBlocks.UNALLOYED_SCALEPLATE.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALES_BLOCK.get(), 8)
			.pattern("###")
			.pattern("#/#")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK))
			.define('/', Ingredient.of(RisusItems.PURIFYING_PASTE.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALES_BLOCK_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALES_BLOCK_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALES_BLOCK_WALL.get(), 6)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.TALL_HAIR.get(), 4)
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(RisusItems.HAIR_FOLLICLES.get()))
			.unlockedBy("has_item", has(RisusItems.HAIR_FOLLICLES.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EERIE_FENCE.get())
			.requires(Ingredient.of(RisusBlocks.DARK_FENCE))
			.unlockedBy("has_item", has(RisusBlocks.DARK_FENCE))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.DARK_FENCE.get())
			.requires(Ingredient.of(RisusBlocks.EERIE_FENCE))
			.unlockedBy("has_item", has(RisusBlocks.EERIE_FENCE))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EERIE_TRAPDOOR.get())
			.requires(Ingredient.of(RisusBlocks.DARK_TRAPDOOR))
			.unlockedBy("has_item", has(RisusBlocks.DARK_TRAPDOOR))
			.save(consumer, "eerie_trapdoor_from_dark");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.DARK_TRAPDOOR.get())
			.requires(Ingredient.of(RisusBlocks.EERIE_TRAPDOOR))
			.unlockedBy("has_item", has(RisusBlocks.EERIE_TRAPDOOR))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EERIE_GATE.get())
			.requires(Ingredient.of(RisusBlocks.BONDKNOT_FENCE_GATE))
			.requires(Ingredient.of(RisusBlocks.EERIE_FENCE))
			.unlockedBy("has_item", has(RisusBlocks.EERIE_FENCE))
			.save(consumer, "eerie_gate_from_fence");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.DARK_GATE.get())
			.requires(Ingredient.of(RisusBlocks.BONDKNOT_FENCE_GATE))
			.requires(Ingredient.of(RisusBlocks.DARK_FENCE))
			.unlockedBy("has_item", has(RisusBlocks.DARK_FENCE))
			.save(consumer, "dark_gate_from_fence");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EERIE_GATE.get())
			.requires(Ingredient.of(RisusBlocks.DARK_GATE))
			.unlockedBy("has_item", has(RisusBlocks.DARK_GATE))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.DARK_GATE.get())
			.requires(Ingredient.of(RisusBlocks.EERIE_GATE))
			.unlockedBy("has_item", has(RisusBlocks.EERIE_GATE))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.DARK_LARGE_GATE.get())
			.pattern("#")
			.pattern("#")
			.define('#', Ingredient.of(RisusBlocks.DARK_GATE.get()))
			.unlockedBy("has_item", has(RisusBlocks.DARK_GATE.get()))
			.save(consumer, "dark_large_gate_from_small");

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.EERIE_LARGE_GATE.get())
			.pattern("#")
			.pattern("#")
			.define('#', Ingredient.of(RisusBlocks.EERIE_GATE.get()))
			.unlockedBy("has_item", has(RisusBlocks.EERIE_GATE.get()))
			.save(consumer, "eerie_large_gate_from_small");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EERIE_LARGE_GATE.get())
			.requires(Ingredient.of(RisusBlocks.DARK_LARGE_GATE))
			.unlockedBy("has_item", has(RisusBlocks.DARK_LARGE_GATE))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.DARK_LARGE_GATE.get())
			.requires(Ingredient.of(RisusBlocks.EERIE_LARGE_GATE))
			.unlockedBy("has_item", has(RisusBlocks.EERIE_LARGE_GATE))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.WHITE_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.WHITE_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.LIGHT_GRAY_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRAY_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.GRAY_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BLACK_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.BLACK_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BROWN_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.BROWN_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.RED_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.RED_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.ORANGE_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.ORANGE_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.YELLOW_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.YELLOW_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIME_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.LIME_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GREEN_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.GREEN_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CYAN_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.CYAN_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.LIGHT_BLUE_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BLUE_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.BLUE_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.PURPLE_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.PURPLE_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.MAGENTA_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.MAGENTA_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.PINK_MOSAIC_GLASS.get())
			.requires(Ingredient.of(RisusTags.Items.GOTHIC_FENCES))
			.requires(Ingredient.of(Blocks.PINK_STAINED_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.WHITE_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.WHITE_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIGHT_GRAY_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRAY_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.GRAY_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BLACK_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.BLACK_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BROWN_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.BROWN_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.RED_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.RED_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.ORANGE_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.ORANGE_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.YELLOW_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.YELLOW_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIME_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.LIME_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GREEN_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.GREEN_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CYAN_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.CYAN_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIGHT_BLUE_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BLUE_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.BLUE_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.PURPLE_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.PURPLE_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.MAGENTA_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.MAGENTA_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.PINK_MOSAIC_LAMP.get())
			.requires(Ingredient.of(Items.GLOWSTONE_DUST))
			.requires(Ingredient.of(RisusBlocks.PINK_MOSAIC_GLASS))
			.unlockedBy("has_item", has(RisusTags.Items.GOTHIC_FENCES))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BOND_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.BOND_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.BOND_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CONTAINMENT_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.CONTAINMENT_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.CONTAINMENT_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.WHITE_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.WHITE_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.WHITE_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.LIGHT_GRAY_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRAY_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GRAY_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRAY_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BLACK_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.BLACK_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.BLACK_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BROWN_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.BROWN_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.BROWN_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.RED_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.RED_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.RED_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.ORANGE_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.ORANGE_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.ORANGE_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.YELLOW_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.YELLOW_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.YELLOW_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIME_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.LIME_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.LIME_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GREEN_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GREEN_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.GREEN_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CYAN_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.CYAN_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.CYAN_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.LIGHT_BLUE_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BLUE_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.BLUE_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.BLUE_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.PURPLE_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.PURPLE_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.PURPLE_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.MAGENTA_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.MAGENTA_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.MAGENTA_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.PINK_MOSAIC_GLASS_PANE.get(), 16)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.PINK_MOSAIC_GLASS.get()))
			.unlockedBy("has_item", has(RisusBlocks.PINK_MOSAIC_GLASS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RisusBlocks.TESSERACT.get())
			.pattern("/P/")
			.pattern("GFG")
			.pattern("/R/")
			.define('G', Ingredient.of(Items.GOLD_INGOT))
			.define('P', Ingredient.of(Items.PHANTOM_MEMBRANE))
			.define('R', Ingredient.of(Items.REDSTONE))
			.define('F', Ingredient.of(RisusItems.BLOOD_FEATHER.get()))
			.define('/', Ingredient.of(Items.FEATHER))
			.unlockedBy("has_item", has(RisusItems.BLOOD_FEATHER.get()))
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
			.requires(RisusBlocks.MAW_GUTS.get())
			.requires(RisusItems.BONDKNOT_BOAT.get())
			.unlockedBy("has_item", has(RisusBlocks.MAW_GUTS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BUNDLE_OF_HAIR.get(), 1)
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(RisusBlocks.TALL_HAIR.get()))
			.unlockedBy("has_item", has(RisusItems.HAIR_FOLLICLES.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.TALL_HAIR.get(), 4)
			.requires(RisusBlocks.BUNDLE_OF_HAIR.get())
			.unlockedBy("has_item", has(RisusBlocks.BUNDLE_OF_HAIR.get()))
			.save(consumer, "hair_unbundleing");

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS.get(), 4)
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BLOOD_TILES.get(), 2)
			.pattern("B#")
			.pattern("#B")
			.define('#', Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()))
			.define('B', Ingredient.of(RisusBlocks.COAGULATED_BLOOD_BLOCK.get()))
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.LINEAR_RITUAL_BLOCK.get(), 1)
			.requires(RisusBlocks.CURVED_RITUAL_BLOCK.get())
			.unlockedBy("has_item", has(RisusBlocks.CURVED_RITUAL_BLOCK.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CURVED_RITUAL_BLOCK.get(), 1)
			.requires(RisusBlocks.LINEAR_RITUAL_BLOCK.get())
			.unlockedBy("has_item", has(RisusBlocks.LINEAR_RITUAL_BLOCK.get()))
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

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BLOOD_TILES_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BLOOD_TILES.get()))
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

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BLOOD_TILES_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BLOOD_TILES.get()))
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
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.GRIMSTONE_BLOOD_TILES_WALL.get(), 6)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BLOOD_TILES.get()))
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

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusItems.GLUTTONY_SCALES.get(), 4)
			.requires(Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK.get()))
			.unlockedBy("has_item", has(RisusBlocks.FLATTENED_SCALES_BLOCK.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLESHY_SKIN.get(), 2)
			.pattern("#")
			.pattern("/")
			.define('#', Ingredient.of(RisusBlocks.SKIN.get()))
			.define('/', Ingredient.of(RisusTags.Items.BASE_TISSUE))
			.unlockedBy("has_item", has(RisusBlocks.SKIN.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CURVED_FLESHY_SKIN.get(), 4)
			.pattern("##")
			.pattern("#/")
			.define('#', Ingredient.of(RisusBlocks.SKIN.get()))
			.define('/', Ingredient.of(RisusTags.Items.BASE_TISSUE))
			.unlockedBy("has_item", has(RisusBlocks.SKIN.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.HAIRY_SKIN.get(), 1)
			.requires(RisusItems.HAIR_FOLLICLES.get())
			.requires(RisusBlocks.SKIN.get())
			.unlockedBy("has_item", has(RisusBlocks.SKIN.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.HAIRY_FLESHY_SKIN.get(), 1)
			.requires(RisusItems.HAIR_FOLLICLES.get())
			.requires(RisusBlocks.FLESHY_SKIN.get())
			.unlockedBy("has_item", has(RisusBlocks.SKIN.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.HAIRY_CURVED_FLESHY_SKIN.get(), 1)
			.requires(RisusItems.HAIR_FOLLICLES.get())
			.requires(RisusBlocks.CURVED_FLESHY_SKIN.get())
			.unlockedBy("has_item", has(RisusBlocks.SKIN.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.TISSUE_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(RisusTags.Items.BASE_TISSUE))
			.unlockedBy("has_item", has(RisusTags.Items.BASE_TISSUE))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.TISSUE_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(RisusTags.Items.BASE_TISSUE))
			.unlockedBy("has_item", has(RisusTags.Items.BASE_TISSUE))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.TISSUE_WALL.get(), 6)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(RisusTags.Items.BASE_TISSUE))
			.unlockedBy("has_item", has(RisusTags.Items.BASE_TISSUE))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BOND_GLASS.get(), 1)
			.requires(RisusItems.CRYSTALLIZED_BOND.get())
			.requires(Items.GLASS)
			.unlockedBy("has_item", has(RisusItems.CRYSTALLIZED_BOND.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.THREADERS_OF_THE_FIRMAMENT.get())
			.pattern("B B")
			.pattern("BRB")
			.pattern("BSB")
			.define('B', Ingredient.of(RisusItems.BLOOD_FEATHER.get()))
			.define('R', Ingredient.of(RisusBlocks.ASHEN_REMAINS.get()))
			.define('S', Ingredient.of(RisusItems.SKIN_BOOTS.get()))
			.unlockedBy("has_item", has(RisusItems.BLOOD_FEATHER.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, RisusItems.ROSE_PETAL.get(), 4)
			.requires(RisusBlocks.REGEN_ROSE.get())
			.unlockedBy("has_item", has(RisusBlocks.REGEN_ROSE.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.WHITE_DYE, 1)
			.requires(RisusItems.ROSE_PETAL.get())
			.unlockedBy("has_item", has(RisusBlocks.REGEN_ROSE.get()))
			.save(consumer, "petal_to_dye");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.SUGAR, 1)
			.requires(RisusItems.ROSE_PETAL.get())
			.requires(RisusItems.ROSE_PETAL.get())
			.unlockedBy("has_item", has(RisusBlocks.REGEN_ROSE.get()))
			.save(consumer, "petal_to_sugar");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, RisusItems.PURIFYING_PASTE.get(), 1)
			.requires(RisusItems.ROSE_PETAL.get())
			.requires(RisusItems.LOVER_CREAM.get())
			.requires(Items.BOWL)
			.unlockedBy("has_item", has(RisusItems.LOVER_CREAM.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RisusItems.LUCKY_CHARM.get())
			.pattern("G")
			.pattern("P")
			.pattern("F")
			.define('G', Ingredient.of(Items.GOLD_INGOT))
			.define('P', Ingredient.of(RisusItems.ROSE_PETAL.get()))
			.define('F', Ingredient.of(Items.RABBIT_FOOT))
			.unlockedBy("has_item", has(RisusBlocks.REGEN_ROSE.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, RisusItems.ETERNAL_YOUTH.get(), 1)
			.requires(Items.EGG)
			.requires(Items.SNIFFER_EGG)
			.requires(Items.TURTLE_EGG)
			.requires(Items.AXOLOTL_BUCKET)
			.requires(Items.TADPOLE_BUCKET)
			.requires(RisusItems.EGG_SAC)
			.requires(Items.CAKE)
			.requires(ItemTags.CANDLES)
			.requires(RisusItems.LOVER_CREAM.get())
			.unlockedBy("has_item", has(RisusItems.LOVER_CREAM.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.ROSE_CROWN.get())
			.pattern("WBR")
			.pattern("B B")
			.pattern("RBW")
			.define('B', Ingredient.of(Items.ROSE_BUSH))
			.define('W', Ingredient.of(Items.WITHER_ROSE))
			.define('R', Ingredient.of(RisusBlocks.REGEN_ROSE.get()))
			.unlockedBy("has_item", has(RisusBlocks.REGEN_ROSE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.CROWN_OF_BONES.get())
			.pattern("HBH")
			.pattern("BSB")
			.pattern("HBH")
			.define('B', Ingredient.of(Items.BONE))
			.define('H', Ingredient.of(RisusTags.Items.HORNS))
			.define('S', Ingredient.of(Items.SKELETON_SKULL))
			.unlockedBy("has_item", has(Items.BONE))
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
			.pattern(" TT")
			.pattern("GTT")
			.pattern("SG ")
			.define('T', Ingredient.of(Items.TNT))
			.define('S', Ingredient.of(Items.STICK))
			.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.TOOTHKNOCKER.get())
			.pattern("BBB")
			.pattern("LCL")
			.pattern("LLL")
			.define('B', Ingredient.of(RisusBlocks.TEETH.get()))
			.define('C', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
			.define('L', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
			.unlockedBy("has_item", has(RisusBlocks.TEETH.get()))
			.unlockedBy("has_item", has(RisusItems.CRYSTALLIZED_BOND.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RisusItems.GOLD_FIST.get())
			.pattern("BVB")
			.pattern("BCB")
			.pattern("LHL")
			.define('B', Ingredient.of(Blocks.GOLD_BLOCK))
			.define('C', Ingredient.of(RisusItems.TOOTHKNOCKER.get()))
			.define('L', Ingredient.of(RisusBlocks.COAGULATED_BLOOD_BLOCK.get()))
			.define('V', Ingredient.of(Items.GOLD_INGOT))
			.define('H', Ingredient.of(RisusItems.HAND_OF_GREED.get()))
			.unlockedBy("has_item", has(RisusItems.TOOTHKNOCKER.get()))
			.unlockedBy("has_item", has(RisusItems.HAND_OF_GREED.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RisusItems.CONCENTRATION_CORE.get())
			.pattern("BGB")
			.pattern("GMG")
			.pattern("BGB")
			.define('G', Ingredient.of(RisusBlocks.NEURON_HEAD.get()))
			.define('M', Ingredient.of(RisusItems.MEMORY_CORE.get()))
			.define('B', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.unlockedBy("has_item", has(RisusItems.MEMORY_CORE.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RisusItems.LIGHT_DEVOURER.get())
			.pattern("FBS")
			.pattern("BPB")
			.pattern("GBL")
			.define('F', Ingredient.of(RisusTags.Items.FROGLIGHT_BLOCKS))
			.define('P', Ingredient.of(Items.ENDER_PEARL))
			.define('B', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
			.define('S', Ingredient.of(Items.GLOWSTONE))
			.define('L', Ingredient.of(Items.SHROOMLIGHT))
			.define('G', Ingredient.of(Items.SEA_LANTERN))
			.unlockedBy("has_item", has(RisusItems.GLUTTONY_SCALES.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENDER_PEARL, 3)
			.requires(RisusItems.ECHO_PEARL.get())
			.unlockedBy("has_item", has(RisusItems.ECHO_PEARL.get()))
			.save(consumer, "echo_to_pearl");


		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.BIG_CHAIN.get(), 4)
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
			.define('#', RisusTags.Items.JOYFLAME_FIRE_BASE_BLOCKS)
			.unlockedBy("has_item", has(Items.STICK))
			.unlockedBy("has_item", has(RisusTags.Items.JOYFLAME_FIRE_BASE_BLOCKS))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RisusBlocks.JOYFLAME_TORCH.get(), 4)
			.pattern("X")
			.pattern("#")
			.pattern("S")
			.define('X', Ingredient.of(Items.COAL, Items.CHARCOAL))
			.define('#', Items.STICK)
			.define('S', RisusTags.Items.JOYFLAME_FIRE_BASE_BLOCKS)
			.unlockedBy("has_item", has(RisusTags.Items.JOYFLAME_FIRE_BASE_BLOCKS))
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
			.define('#', RisusTags.Items.BASE_TISSUE)
			.define('X', RisusTags.Items.BONE_BLOCK_VARIATION)
			.define('Y', RisusBlocks.BONE_WALL.get())
			.unlockedBy("has_item", has(RisusTags.Items.BASE_TISSUE))
			.save(consumer);

		//bone
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONE_WALL.get(), 6)
			.pattern("XXX")
			.pattern("XXX")
			.define('X', RisusTags.Items.BONE_BLOCK_VARIATION)
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
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 9)
			.requires(RisusBlocks.FULL_BONE_BLOCK.get())
			.unlockedBy("has_item", has(RisusBlocks.FULL_BONE_BLOCK.get()))
			.save(consumer, "full_bone_to_bone_meal");
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

		//fossil
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FOSSIL_WALL.get(), 6)
			.pattern("XXX")
			.pattern("XXX")
			.define('X', RisusTags.Items.FOSSIL_VARIATION)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FOSSIL_STAIRS.get(), 4)
			.pattern("X  ")
			.pattern("XX ")
			.pattern("XXX")
			.define('X', RisusBlocks.FOSSIL)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FOSSIL_SLAB.get(), 6)
			.pattern("XXX")
			.define('X', RisusBlocks.FOSSIL)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_FOSSIL.get(), 4)
			.pattern("XX")
			.pattern("XX")
			.define('X', RisusBlocks.FOSSIL)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 9)
			.requires(RisusTags.Items.FOSSIL_VARIATION)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer, "fossils_to_bone_meal");
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_FOSSIL_STAIRS.get(), 4)
			.pattern("X  ")
			.pattern("XX ")
			.pattern("XXX")
			.define('X', RisusBlocks.FULL_FOSSIL.get())
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_FOSSIL_SLAB.get(), 6)
			.pattern("XXX")
			.define('X', RisusBlocks.FULL_FOSSIL.get())
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer);

		//eye
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_GOLDEN.get(), 1)
			.requires(RisusTags.Items.BASE_TISSUE)
			.requires(RisusItems.STALKER_EYE.get())
			.unlockedBy("has_item", has(RisusTags.Items.BASE_TISSUE))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_GOLDEN.get(), 1)
			.requires(RisusTags.Items.EYE)
			.unlockedBy("has_item", has(RisusTags.Items.BASE_TISSUE))
			.save(consumer, "revert_to_golden_eye");
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_BLEACHED.get(), 1)
			.requires(RisusBlocks.TEETH.get())
			.requires(RisusTags.Items.EYE)
			.requires(Items.CHARCOAL)
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_ENDER.get(), 1)
			.requires(RisusTags.Items.EYE)
			.requires(Items.ENDER_PEARL)
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_EMERALD.get(), 1)
			.requires(RisusTags.Items.EYE)
			.requires(Items.EMERALD)
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_BLOODSHOT.get(), 1)
			.requires(RisusTags.Items.EYE)
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_GOLDEN_GLOWING.get(), 1)
			.requires(RisusBlocks.EYE_GOLDEN)
			.requires(Items.GLOW_INK_SAC)
			.unlockedBy("has_item", has(RisusTags.Items.BASE_TISSUE))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_BLEACHED_GLOWING.get(), 1)
			.requires(Items.GLOW_INK_SAC)
			.requires(RisusBlocks.EYE_BLEACHED.get())
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_ENDER_GLOWING.get(), 1)
			.requires(RisusBlocks.EYE_ENDER.get())
			.requires(Items.GLOW_INK_SAC)
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_EMERALD_GLOWING.get(), 1)
			.requires(RisusBlocks.EYE_EMERALD.get())
			.requires(Items.GLOW_INK_SAC)
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, RisusBlocks.EYE_BLOODSHOT_GLOWING.get(), 1)
			.requires(RisusBlocks.EYE_BLOODSHOT.get())
			.requires(Items.GLOW_INK_SAC)
			.unlockedBy("has_item", has(RisusBlocks.EYE_GOLDEN.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, RisusItems.EYE_SANDWICH.get(), 1)
			.pattern("B")
			.pattern("E")
			.pattern("B")
			.define('B', Items.BREAD)
			.define('E', RisusItems.STALKER_EYE)
			.unlockedBy("has_item", has(RisusItems.STALKER_EYE))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RisusItems.STITCHING_NEEDLE.get(), 1)
			.pattern("OR")
			.pattern("IB")
			.define('I', Items.IRON_INGOT)
			.define('R', Items.RED_WOOL)
			.define('O', RisusBlocks.ORGANIC_MATTER_BLOCK.asItem())
			.define('B', RisusBlocks.BLOODWEAVE.asItem())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.LIVING_TISSUE, 1)
			.requires(RisusBlocks.TISSUE.get())
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer, "tissue_to_living");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.ROTTED_TISSUE, 1)
			.requires(RisusBlocks.ROTTING_TISSUE.get())
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer, "rotting_to_rotted");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.DECOMPOSED_TISSUE, 1)
			.requires(RisusBlocks.DECOMPOSING_TISSUE.get())
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer, "decomposing_to_decomposed");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.DECAYED_TISSUE, 1)
			.requires(RisusBlocks.DECAYING_TISSUE.get())
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer, "decaying_to_decayed");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.HAIRY_SKIN, 1)
			.requires(RisusBlocks.SKIN.get())
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer, "adding_hair_to_skin");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.HAIRY_FLESHY_SKIN, 1)
			.requires(RisusBlocks.FLESHY_SKIN.get())
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer, "adding_hair_to_fleshy_skin");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RisusBlocks.HAIRY_CURVED_FLESHY_SKIN, 1)
			.requires(RisusBlocks.CURVED_FLESHY_SKIN.get())
			.requires(RisusItems.ORGANIC_MATTER.get())
			.unlockedBy("has_item", has(RisusItems.ORGANIC_MATTER.get()))
			.save(consumer, "adding_hair_to_curved_fleshy_skin");


		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_bricks"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CHISELED_GRIMSTONE, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_chiseled"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_polished"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_brick_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_polished_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_bricks_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_polished_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_wall"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_bricks_wall"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_to_polished_wall"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_bricks"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CHISELED_GRIMSTONE, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_chiseled"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_bricks_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_polished_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_bricks_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_polished_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_bricks_wall"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.POLISHED_GRIMSTONE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.POLISHED_GRIMSTONE_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_polished_grimstone_to_polished_wall"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.CHISELED_GRIMSTONE, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_bricks_to_chiseled"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_bricks_to_bricks_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_bricks_to_bricks_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BRICKS_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_bricks_to_bricks_wall"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE_BLOOD_TILES.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BLOOD_TILES_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_blood_to_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE_BLOOD_TILES.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BLOOD_TILES_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_blood_to_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.GRIMSTONE_BLOOD_TILES.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.GRIMSTONE_BLOOD_TILES_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE))
			.save(consumer, prefix("stonecutting_grimstone_blood_to_wall"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.IMITATION_SCALES_BLOCK_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK))
			.save(consumer, prefix("imitation_scales_block_to_slabs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.IMITATION_SCALEPLATE, 4)
			.unlockedBy("has_item", has(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK))
			.save(consumer, prefix("imitation_scales_block_to_scaleplate"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLOWERING_IMITATION_SCALEPLATE, 4)
			.unlockedBy("has_item", has(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK))
			.save(consumer, prefix("imitation_scales_block_to_flowering"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BUDDING_IMITATION_SCALEPLATE, 4)
			.unlockedBy("has_item", has(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK))
			.save(consumer, prefix("imitation_scales_block_to_budding"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.IMITATION_SCALES_BLOCK_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK))
			.save(consumer, prefix("imitation_scales_block_to_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.IMITATION_SCALES_BLOCK_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK))
			.save(consumer, prefix("imitation_scales_block_to_wall"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.IMITATION_SCALEPLATE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLOWERING_IMITATION_SCALEPLATE, 1)
			.unlockedBy("has_item", has(RisusBlocks.IMITATION_SCALEPLATE))
			.save(consumer, prefix("imitation_scaleplate_to_flowering"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.IMITATION_SCALEPLATE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BUDDING_IMITATION_SCALEPLATE, 1)
			.unlockedBy("has_item", has(RisusBlocks.IMITATION_SCALEPLATE))
			.save(consumer, prefix("imitation_scaleplate_to_budding"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALES_BLOCK_SLAB, 2)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALES_BLOCK))
			.save(consumer, prefix("unalloyed_scales_block_to_slabs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALEPLATE, 4)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALES_BLOCK))
			.save(consumer, prefix("unalloyed_scales_block_to_scaleplate"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLOWERING_UNALLOYED_SCALEPLATE, 4)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALES_BLOCK))
			.save(consumer, prefix("unalloyed_scales_block_to_flowering"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BUDDING_UNALLOYED_SCALEPLATE, 4)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALES_BLOCK))
			.save(consumer, prefix("unalloyed_scales_block_to_budding"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALES_BLOCK_STAIRS, 1)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALES_BLOCK))
			.save(consumer, prefix("unalloyed_scales_block_to_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALES_BLOCK.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.UNALLOYED_SCALES_BLOCK_WALL, 1)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALES_BLOCK))
			.save(consumer, prefix("unalloyed_scales_block_to_wall"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALEPLATE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FLOWERING_UNALLOYED_SCALEPLATE, 1)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALEPLATE))
			.save(consumer, prefix("unalloyed_scaleplate_to_flowering"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.UNALLOYED_SCALEPLATE.get()), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BUDDING_UNALLOYED_SCALEPLATE, 1)
			.unlockedBy("has_item", has(RisusBlocks.UNALLOYED_SCALEPLATE))
			.save(consumer, prefix("unalloyed_scaleplate_to_budding"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.BONE_BLOCK_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONE_SLAB, 2)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("bones_to_slabs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.BONE_BLOCK_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONE_STAIRS, 1)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("bones_to_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.BONE_BLOCK_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.BONE_WALL, 1)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("bones_to_walls"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.BONE_BLOCK), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_BONE_BLOCK, 1)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer, prefix("bones_to_full"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.BONE_BLOCK_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_BONE_SLAB, 2)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("bones_to_full_slabs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.BONE_BLOCK_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_BONE_STAIRS, 1)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("bones_to_full_stairs"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.FOSSIL_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FOSSIL_SLAB, 2)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("fossils_to_slabs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.FOSSIL_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FOSSIL_STAIRS, 1)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("fossils_to_stairs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.FOSSIL_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FOSSIL_WALL, 1)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("fossils_to_walls"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusBlocks.FOSSIL), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_FOSSIL, 1)
			.unlockedBy("has_item", has(Items.BONE_BLOCK))
			.save(consumer, prefix("fossils_to_full"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.FOSSIL_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_FOSSIL_SLAB, 2)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("fossils_to_full_slabs"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(RisusTags.Items.FOSSIL_VARIATION), RecipeCategory.BUILDING_BLOCKS, RisusBlocks.FULL_FOSSIL_STAIRS, 1)
			.unlockedBy("has_item", has(RisusTags.Items.BONE_BLOCK_VARIATION))
			.save(consumer, prefix("fossils_to_full_stairs"));


		smeltingRecipe(RisusBlocks.GRIMSTONE_BRICKS.get(), RisusBlocks.CRACKED_GRIMSTONE_BRICKS.get().asItem(), 0.1F, 1).save(consumer, prefix("smelt_cracked_grimstone_bricks"));
		smeltingRecipe(RisusBlocks.TISSUE.get(), RisusBlocks.GRILLED_TISSUE.get().asItem(), 0.1F, 1).save(consumer, prefix("smelt_tissue"));
		smokingRecipe(RisusBlocks.TISSUE.get(), RisusBlocks.GRILLED_TISSUE.get().asItem(), 0.1F, 1).save(consumer, prefix("smoke_tissue"));
		smeltingRecipe(RisusBlocks.LIVING_TISSUE.get(), RisusBlocks.GRILLED_TISSUE.get().asItem(), 0.1F, 1).save(consumer, prefix("smelt_living_tissue"));
		smokingRecipe(RisusBlocks.LIVING_TISSUE.get(), RisusBlocks.GRILLED_TISSUE.get().asItem(), 0.1F, 1).save(consumer, prefix("smoke_living_tissue"));
		smeltingRecipe(RisusBlocks.BLOODY_SPONGE, Blocks.SPONGE.asItem(), 0.1F, 1).save(consumer, prefix("smelt_bloody_sponge"));

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.BLOOD_FEATHER.get()),
				Ingredient.of(Items.ELYTRA),
				Ingredient.of(RisusItems.ORGANIC_MATTER),
				RecipeCategory.TRANSPORTATION,
				RisusItems.ANGEL_WINGS.get())
			.unlocks("has_item", has(RisusItems.BLOOD_FEATHER))
			.save(consumer, "elytra_to_angel_wings");

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.BLOOD_FEATHER.get()),
				Ingredient.of(RisusItems.UNAWAKENED_VESSEL),
				Ingredient.of(RisusItems.ORGANIC_MATTER),
				RecipeCategory.COMBAT,
				RisusItems.CRESCENT_DISASTER.get())
			.unlocks("has_item", has(RisusItems.BLOOD_FEATHER))
			.save(consumer, "unawakened_to_crescent");

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.BLOOD_FEATHER.get()),
				Ingredient.of(RisusItems.BLOODWYRM_HEAD),
				Ingredient.of(RisusItems.ORGANIC_MATTER),
				RecipeCategory.COMBAT,
				RisusItems.BLOODWYRM_HEAD_WEAPON.get())
			.unlocks("has_item", has(RisusItems.BLOOD_FEATHER))
			.save(consumer, "head_to_spewer");

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.BLOOD_FEATHER.get()),
				Ingredient.of(RisusItems.WARHORN),
				Ingredient.of(RisusItems.ECHO_PEARL),
				RecipeCategory.COMBAT,
				RisusItems.HEXHORN.get())
			.unlocks("has_item", has(RisusItems.BLOOD_FEATHER))
			.save(consumer, "warhorn_to_hexhorn");

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.STITCHING_NEEDLE.get()),
				Ingredient.of(RisusItems.SKIN_BOOTS),
				Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK),
				RecipeCategory.COMBAT,
				RisusItems.SINNER_ROBES_BOOTS.get())
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, "skin_boots_to_robes");

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.STITCHING_NEEDLE.get()),
				Ingredient.of(RisusItems.SKIN_LEGGINGS),
				Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK),
				RecipeCategory.COMBAT,
				RisusItems.SINNER_ROBES_LEGGINGS.get())
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, "skin_leggings_to_robes");

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.STITCHING_NEEDLE.get()),
				Ingredient.of(RisusItems.SKIN_CHESTPLATE),
				Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK),
				RecipeCategory.COMBAT,
				RisusItems.SINNER_ROBES_CHESTPLATE.get())
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, "skin_chestplate_to_robes");

		SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(RisusItems.STITCHING_NEEDLE.get()),
				Ingredient.of(RisusItems.SKIN_HELMET),
				Ingredient.of(RisusBlocks.FLATTENED_SCALES_BLOCK),
				RecipeCategory.COMBAT,
				RisusItems.SINNER_ROBES_HELMET.get())
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, "skin_helmet_to_robes");

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()), Ingredient.of(Items.SKELETON_SKULL), Ingredient.of(Items.STRING), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "skeleton")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("skeleton_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()), Ingredient.of(Items.ZOMBIE_HEAD), Ingredient.of(Items.STRING), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "zombie")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("zombie_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()), Ingredient.of(Items.CREEPER_HEAD), Ingredient.of(Items.STRING), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "creeper")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("creeper_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()), Ingredient.of(Items.WITHER_SKELETON_SKULL), Ingredient.of(Items.STRING), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "wither_skeleton")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("wither_skeleton_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()), Ingredient.of(Items.PIGLIN_HEAD), Ingredient.of(Items.STRING), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "piglin")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("piglin_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.SPYGLASS), Ingredient.of(Items.SPYGLASS), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "spyglass")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("spyglass_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.CARVED_PUMPKIN), Ingredient.of(Items.STRING), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "pumpkin")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("pumpkin_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.BONDKNOT_LOG), Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "great_stool")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("great_stool_helmet"));

//		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()), Ingredient.of(Items.DRAGON_HEAD), Ingredient.of(Items.STRING), RecipeCategory.COMBAT)
//			.attachData(RisusDataComponents.ABILITY_VARIANT, "dragon")
//			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
//			.save(consumer, Risus.prefix("dragon_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.WHITE_WOOL), Ingredient.of(Items.BLACK_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "tuxedo_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("tuxedo_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.BLACK_WOOL), Ingredient.of(Items.BLACK_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "black_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("black_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.LIGHT_GRAY_WOOL), Ingredient.of(Items.LIGHT_GRAY_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "british_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("british_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.ORANGE_WOOL), Ingredient.of(Items.WHITE_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "calico_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("calico_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.GRAY_WOOL), Ingredient.of(Items.WHITE_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "jellie_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("jellie_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.WHITE_WOOL), Ingredient.of(Items.YELLOW_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "persian_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("persian_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.WHITE_WOOL), Ingredient.of(Items.BROWN_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "ragdoll_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("ragdoll_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.YELLOW_WOOL), Ingredient.of(Items.ORANGE_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "orange_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("orange_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.BROWN_WOOL), Ingredient.of(Items.WHITE_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "siamese_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("siamese_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.BROWN_WOOL), Ingredient.of(Items.BROWN_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "tabby_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("tabby_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.WHITE_WOOL), Ingredient.of(Items.WHITE_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "white_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("white_cat_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.BLACK_WOOL), Ingredient.of(Items.YELLOW_WOOL), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "audrey_cat")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("audrey_cat_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.LIGHT_GRAY_TERRACOTTA), Ingredient.of(Items.WHITE_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "pale_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("pale_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.GRAY_TERRACOTTA), Ingredient.of(Items.WHITE_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "ashen_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("ashen_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.BLACK_TERRACOTTA), Ingredient.of(Items.BLACK_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "black_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("black_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.BROWN_TERRACOTTA), Ingredient.of(Items.ORANGE_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "chestnut_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("chestnut_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.ORANGE_TERRACOTTA), Ingredient.of(Items.BROWN_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "rusty_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("rusty_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.WHITE_TERRACOTTA), Ingredient.of(Items.WHITE_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "snowy_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("snowy_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.ORANGE_TERRACOTTA), Ingredient.of(Items.BLACK_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "spotted_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("spotted_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.YELLOW_TERRACOTTA), Ingredient.of(Items.BLACK_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "striped_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("striped_wolf_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(Items.BROWN_TERRACOTTA), Ingredient.of(Items.BROWN_TERRACOTTA), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "woods_wolf")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("woods_wolf_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.EYE_BLEACHED), Ingredient.of(RisusItems.ORGANIC_MATTER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "bleached_eye")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("bleached_eye_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.EYE_BLOODSHOT), Ingredient.of(RisusItems.ORGANIC_MATTER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "bloodshot_eye")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("bloodshot_eye_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.EYE_EMERALD), Ingredient.of(RisusItems.ORGANIC_MATTER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "emerald_eye")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("emerald_eye_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.EYE_ENDER), Ingredient.of(RisusItems.ORGANIC_MATTER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "ender_eye")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("ender_eye_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.EYE_GOLDEN), Ingredient.of(RisusItems.ORGANIC_MATTER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "golden_eye")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("golden_eye_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.ASHEN_REMAINS), Ingredient.of(RisusItems.ORGANIC_MATTER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "abyssal_eye")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("abyssal_eye_helmet"));
		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_HELMET.get()),Ingredient.of(RisusBlocks.SMILING_REMAINS), Ingredient.of(RisusBlocks.FOSSIL.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "smile")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("smile_helmet"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_CHESTPLATE.get()),Ingredient.of(RisusBlocks.MAW_GUTS), Ingredient.of(RisusItems.ORGANIC_MATTER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "guts")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("guts_chestplate"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_CHESTPLATE.get()),Ingredient.of(RisusItems.HAND_OF_GREED), Ingredient.of(RisusItems.HAND_OF_GREED.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "hand_of_greed")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("hand_of_greed_chestplate"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_CHESTPLATE.get()),Ingredient.of(RisusBlocks.BONDKNOT_LOG), Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "great_stool")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("great_stool_chestplate"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_LEGGINGS.get()),Ingredient.of(RisusBlocks.BONDKNOT_LOG), Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "great_stool")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("great_stool_leggings"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_LEGGINGS.get()),Ingredient.of(RisusItems.RESEARCHERS_NOTES), Ingredient.of(Items.LEATHER), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "book")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("book_leggings"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_BOOTS.get()),Ingredient.of(RisusItems.LIGHT_DEVOURER), Ingredient.of(RisusItems.BLOOD_FEATHER.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "shadow_walker")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("shadow_walker_boots"));

		SmithingUpgradeRecipeBuilder.smithingUpgrade(Ingredient.of(RisusItems.SINNER_ROBES_BOOTS.get()),Ingredient.of(RisusBlocks.BONDKNOT_LOG), Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()), RecipeCategory.COMBAT)
			.attachData(RisusDataComponents.ABILITY_VARIANT, "great_stool")
			.unlocks("has_item", has(RisusItems.GLUTTONY_SCALES))
			.save(consumer, Risus.prefix("great_stool_boots"));



	}



	public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike input, ItemLike result, float exp, int count) {
		return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(input, count)), RecipeCategory.MISC, result, exp, 200)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(input.asItem()), has(input));
	}

	public SimpleCookingRecipeBuilder smokingRecipe(ItemLike input, ItemLike result, float exp, int count) {
		return SimpleCookingRecipeBuilder.smoking(Ingredient.of(new ItemStack(input, count)), RecipeCategory.MISC, result, exp, 100)
			.unlockedBy("has_" + BuiltInRegistries.ITEM.getKey(input.asItem()), has(input));
	}


	private ResourceLocation prefix(String prefix) {
		return ResourceLocation.fromNamespaceAndPath(Risus.MODID, prefix);
	}
}
