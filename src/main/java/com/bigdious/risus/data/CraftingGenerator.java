package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import com.bigdious.risus.block.RisusBlocks;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CraftingGenerator extends RecipeProvider {
	public CraftingGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.BONDKNOT_WOOD.get(),  3)
				.patternLine("##")
				.patternLine("##")
				.key('#', Ingredient.fromItems(RisusBlocks.BONDKNOT_LOG.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.BONDKNOT_LOG.get()))
				.build(consumer, Risus.risusRL("bondknot_wood"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(),  3)
				.patternLine("##")
				.patternLine("##")
				.key('#', Ingredient.fromItems(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.build(consumer, Risus.risusRL("stripped_bondknot_wood"));

		ShapelessRecipeBuilder.shapelessRecipe(RisusBlocks.BONDKNOT_PLANKS.get(),  4)
				.addIngredient(Ingredient.fromTag(ItemTagGenerator.BONDKNOT_LOGS))
				.addCriterion("has_item", hasItem(ItemTagGenerator.BONDKNOT_LOGS))
				.build(consumer, Risus.risusRL("bondknot_planks"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.BONDKNOT_STAIRS.get(),  8)
				.patternLine("#  ")
				.patternLine("## ")
				.patternLine("###")
				.key('#', Ingredient.fromItems(RisusBlocks.BONDKNOT_PLANKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.BONDKNOT_PLANKS.get()))
				.build(consumer, Risus.risusRL("bondknot_stairs"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.BONDKNOT_SLAB.get(),  6)
				.patternLine("###")
				.key('#', Ingredient.fromItems(RisusBlocks.BONDKNOT_PLANKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.BONDKNOT_PLANKS.get()))
				.build(consumer, Risus.risusRL("bondknot_slab"));

		ShapelessRecipeBuilder.shapelessRecipe(RisusBlocks.BONDKNOT_BUTTON.get())
				.addIngredient(Ingredient.fromItems(RisusBlocks.BONDKNOT_PLANKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.BONDKNOT_PLANKS.get()))
				.build(consumer, Risus.risusRL("bondknot_button"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.BONDKNOT_FENCE.get(),  6)
				.patternLine("#/#")
				.patternLine("#/#")
				.key('#', Ingredient.fromItems(RisusBlocks.BONDKNOT_PLANKS.get()))
				.key('/', Ingredient.fromTag(Tags.Items.RODS_WOODEN))
				.addCriterion("has_item", hasItem(RisusBlocks.BONDKNOT_PLANKS.get()))
				.build(consumer, Risus.risusRL("bondknot_fence"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.BONDKNOT_FENCE_GATE.get())
				.patternLine("/#/")
				.patternLine("/#/")
				.key('#', Ingredient.fromItems(RisusBlocks.BONDKNOT_PLANKS.get()))
				.key('/', Ingredient.fromTag(Tags.Items.RODS_WOODEN))
				.addCriterion("has_item", hasItem(RisusBlocks.BONDKNOT_PLANKS.get()))
				.build(consumer, Risus.risusRL("bondknot_fence_gate"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get())
				.patternLine("##")
				.key('#', Ingredient.fromItems(RisusBlocks.BONDKNOT_PLANKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.BONDKNOT_PLANKS.get()))
				.build(consumer, Risus.risusRL("bondknot_pressure_plate"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.GRIMSTONE_BRICKS.get(),  4)
				.patternLine("##")
				.patternLine("##")
				.key('#', Ingredient.fromItems(RisusBlocks.GRIMSTONE.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.GRIMSTONE.get()))
				.build(consumer, Risus.risusRL("grimstone_bricks"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.GRIMSTONE_STAIRS.get(),  8)
				.patternLine("#  ")
				.patternLine("## ")
				.patternLine("###")
				.key('#', Ingredient.fromItems(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.build(consumer, Risus.risusRL("grimstone_stairs"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.GRIMSTONE_SLAB.get(),  6)
				.patternLine("###")
				.key('#', Ingredient.fromItems(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.build(consumer, Risus.risusRL("grimstone_slab"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.GRIMSTONE_WALL.get(),  6)
				.patternLine("###")
				.patternLine("###")
				.key('#', Ingredient.fromItems(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.build(consumer, Risus.risusRL("grimstone_wall"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.CHISELED_GRIMSTONE.get())
				.patternLine(" # ")
				.patternLine(" # ")
				.key('#', Ingredient.fromItems(RisusBlocks.GRIMSTONE_SLAB.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.GRIMSTONE_SLAB.get()))
				.build(consumer, Risus.risusRL("chiseled_grimstone"));

		ShapedRecipeBuilder.shapedRecipe(RisusBlocks.POLISHED_GRIMSTONE.get(),  4)
				.patternLine("##")
				.patternLine("##")
				.key('#', Ingredient.fromItems(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.addCriterion("has_item", hasItem(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.build(consumer, Risus.risusRL("polished_grimstone"));
	}
}
