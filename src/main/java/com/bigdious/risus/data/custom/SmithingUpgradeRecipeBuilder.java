package com.bigdious.risus.data.custom;

import com.bigdious.risus.Risus;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SmithingUpgradeRecipeBuilder {

	private final RecipeCategory category;
	private final Ingredient template;
	private final Ingredient base;
	private final Ingredient addition;
	private List<TypedDataComponent<?>> additionalData = new ArrayList<>();
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public SmithingUpgradeRecipeBuilder(RecipeCategory category, Ingredient template, Ingredient base, Ingredient addition) {
		this.category = category;
		this.template = template;
		this.base = base;
		this.addition = addition;
	}

	public static SmithingUpgradeRecipeBuilder smithingUpgrade(Ingredient template, Ingredient base, Ingredient addition, RecipeCategory category) {
		return new SmithingUpgradeRecipeBuilder(category, template, base, addition);
	}

	public SmithingUpgradeRecipeBuilder unlocks(String key, Criterion<?> criterion) {
		this.criteria.put(key, criterion);
		return this;
	}

	public <T> SmithingUpgradeRecipeBuilder attachData(Supplier<DataComponentType<T>> type, T element) {
		return attachData(new TypedDataComponent<>(type.get(), element));
	}

	public SmithingUpgradeRecipeBuilder attachData(TypedDataComponent<?> component) {
		this.additionalData.add(component);
		return this;
	}

	public void save(RecipeOutput recipeOutput, ResourceLocation recipeId) {
		this.ensureValid(recipeId);
		Advancement.Builder advancement$builder = recipeOutput.advancement()
			.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
			.rewards(AdvancementRewards.Builder.recipe(recipeId))
			.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancement$builder::addCriterion);
		SmithingUpgradeRecipe smithingUpgradeRecipe = new SmithingUpgradeRecipe(this.template, this.base, this.addition, this.additionalData);
		recipeOutput.accept(recipeId, smithingUpgradeRecipe, advancement$builder.build(recipeId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
	}

	private void ensureValid(ResourceLocation location) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + location);
		}
	}
}
