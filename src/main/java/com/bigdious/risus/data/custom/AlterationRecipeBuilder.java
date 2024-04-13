package com.bigdious.risus.data.custom;

import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AlterationRecipeBuilder implements RecipeBuilder {
	private final Item result;
	private final Ingredient ingredient;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	private AlterationRecipeBuilder(ItemLike result, Ingredient input) {
		this.result = result.asItem();
		this.ingredient = input;
	}

	public static AlterationRecipeBuilder alteration(Ingredient input, ItemLike result) {
		return new AlterationRecipeBuilder(result, input);
	}

	@Override
	public AlterationRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		this.criteria.put(name, criterion);
		return this;
	}

	@Override
	public AlterationRecipeBuilder group(@Nullable String id) {
		return this;
	}

	@Override
	public Item getResult() {
		return this.result;
	}

	@Override
	public void save(RecipeOutput output) {
		this.save(output, BuiltInRegistries.ITEM.getKey(this.getResult()).getNamespace() + ":alteration/" + BuiltInRegistries.ITEM.getKey(this.getResult()).getPath());
	}

	@Override
	public void save(RecipeOutput output, ResourceLocation id) {
		this.ensureValid(id);
		Advancement.Builder builder = output.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
				.rewards(AdvancementRewards.Builder.recipe(id))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(builder::addCriterion);
		AlterationRecipe recipe = new AlterationRecipe(this.ingredient, new ItemStack(this.result));
		output.accept(id, recipe, builder.build(id.withPrefix("recipes/")));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}
}
