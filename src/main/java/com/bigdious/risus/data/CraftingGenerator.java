package com.bigdious.risus.data;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CraftingGenerator extends RecipeProvider {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_WOOD.get(), 3)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_LOG.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_LOG.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), 3)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.unlockedBy("has_item", has(RisusBlocks.STRIPPED_BONDKNOT_LOG.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RisusBlocks.BONDKNOT_PLANKS.get(), 4)
				.requires(Ingredient.of(ItemTagGenerator.BONDKNOT_LOGS))
				.unlockedBy("has_item", has(ItemTagGenerator.BONDKNOT_LOGS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_STAIRS.get(), 8)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_SLAB.get(), 6)
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RisusBlocks.BONDKNOT_BUTTON.get())
				.requires(Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_FENCE.get(), 6)
				.pattern("#/#")
				.pattern("#/#")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_FENCE_GATE.get())
				.pattern("/#/")
				.pattern("/#/")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_PRESSURE_PLATE.get())
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_DOOR.get(), 3)
				.pattern("##")
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_TRAPDOOR.get(), 6)
				.pattern("###")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.BONDKNOT_SIGN.get().asItem(), 3)
				.pattern("###")
				.pattern("###")
				.pattern(" / ")
				.define('#', Ingredient.of(RisusBlocks.BONDKNOT_PLANKS.get()))
				.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
				.unlockedBy("has_item", has(RisusBlocks.BONDKNOT_PLANKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_BRICKS.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_STAIRS.get(), 8)
				.pattern("#  ")
				.pattern("## ")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_SLAB.get(), 6)
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.GRIMSTONE_WALL.get(), 6)
				.pattern("###")
				.pattern("###")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.CHISELED_GRIMSTONE.get())
				.pattern(" # ")
				.pattern(" # ")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_SLAB.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_SLAB.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusBlocks.POLISHED_GRIMSTONE.get(), 4)
				.pattern("##")
				.pattern("##")
				.define('#', Ingredient.of(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.unlockedBy("has_item", has(RisusBlocks.GRIMSTONE_BRICKS.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusItems.UNAWAKENED_VESSEL.get())
				.pattern(" BG")
				.pattern("BGC")
				.pattern("GLL")
				.define('B', Ingredient.of(RisusItems.CRYSTALLIZED_BOND.get()))
				.define('C', Ingredient.of(RisusItems.CONCENTRATION_CORE.get()))
				.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('L', Ingredient.of(ItemTags.LOGS))
				.unlockedBy("has_core", has(RisusItems.CONCENTRATION_CORE.get()))
				.unlockedBy("has_bonds", has(RisusItems.CRYSTALLIZED_BOND.get()))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RisusItems.CRESCENT_DISASTER.get())
				.requires(RisusItems.UNAWAKENED_VESSEL.get())
				.requires(RisusItems.BLOOD_FEATHER.get())
				.unlockedBy("has_feather", has(RisusItems.BLOOD_FEATHER.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RisusItems.CONCENTRATION_CORE.get())
				.pattern(" G ")
				.pattern("GMG")
				.pattern(" G ")
				.define('G', Ingredient.of(RisusItems.GLUTTONY_SCALES.get()))
				.define('M', Ingredient.of(RisusItems.MEMORY_CORE.get()))
				.unlockedBy("has_scales", has(RisusItems.GLUTTONY_SCALES.get()))
				.unlockedBy("has_core", has(RisusItems.MEMORY_CORE.get()))
				.save(consumer);
	}
}
