package com.bigdious.risus.data.custom;

import com.bigdious.risus.init.RisusRecipes;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class AlterationRecipeBuilder implements RecipeBuilder {
	private final Item result;
	private final Ingredient ingredient;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();
	@Nullable
	private String group;

	private AlterationRecipeBuilder(ItemLike result, Ingredient input) {
		this.result = result.asItem();
		this.ingredient = input;
	}

	public static AlterationRecipeBuilder alteration(Ingredient input, ItemLike result) {
		return new AlterationRecipeBuilder(result, input);
	}

	public AlterationRecipeBuilder unlockedBy(String id, CriterionTriggerInstance instance) {
		this.advancement.addCriterion(id, instance);
		return this;
	}

	public AlterationRecipeBuilder group(@Nullable String id) {
		this.group = id;
		return this;
	}

	public Item getResult() {
		return this.result;
	}

	@Override
	public void save(Consumer<FinishedRecipe> consumer) {
		this.save(consumer, Registries.ITEM.getKey(this.getResult()).getNamespace() + ":alteration/" + BuiltInRegistries.ITEM.getKey(this.getResult()).getPath());
	}

	public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
		this.ensureValid(id);
		this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
		consumer.accept(new Result(id, this.group == null ? "" : this.group, this.ingredient, this.result, this.advancement, new ResourceLocation(id.getNamespace(), "recipes/risus/" + id.getPath())));
	}

	private void ensureValid(ResourceLocation id) {
		if (this.advancement.getCriteria().isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + id);
		}
	}

	public static class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final String group;
		private final Ingredient ingredient;
		private final Item result;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation id, String group, Ingredient input, Item result, Advancement.Builder builder, ResourceLocation advId) {
			this.id = id;
			this.group = group;
			this.ingredient = input;
			this.result = result;
			this.advancement = builder;
			this.advancementId = advId;
		}

		@Override
		public void serializeRecipeData(JsonObject object) {
			if (!this.group.isEmpty()) {
				object.addProperty("group", this.group);
			}

			object.add("input", this.ingredient.toJson());
			object.addProperty("result", Objects.requireNonNull(Registries.ITEM.getKey(this.result)).toString());
		}

		@Override
		public RecipeSerializer<?> getType() {
			return RisusRecipes.ALTERATION_SERIALIZER.get();
		}

		@Override
		public ResourceLocation getId() {
			return this.id;
		}

		@Nullable
		@Override
		public JsonObject serializeAdvancement() {
			return this.advancement.serializeToJson();
		}

		@Nullable
		@Override
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
	}
}
