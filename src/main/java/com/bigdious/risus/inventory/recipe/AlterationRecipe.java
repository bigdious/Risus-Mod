package com.bigdious.risus.inventory.recipe;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusRecipes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;

public record AlterationRecipe(ResourceLocation id, Ingredient input, ItemStack result) implements Recipe<Container> {

	@Override
	public boolean matches(Container container, Level level) {
		return this.input.test(container.getItem(0));
	}

	@Override
	public ItemStack assemble(Container container, RegistryAccess registryAccess) {
		return this.result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return this.result;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(RisusItems.ALTERATION_CATALYST.get());
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RisusRecipes.ALTERATION_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return RisusRecipes.ALTERATION_RECIPE.get();
	}

	public static class Serializer implements RecipeSerializer<AlterationRecipe> {

		public AlterationRecipe fromJson(ResourceLocation id, JsonObject object) {
			JsonElement inputElement = GsonHelper.isArrayNode(object, "input") ? GsonHelper.getAsJsonArray(object, "input") : GsonHelper.getAsJsonObject(object, "input");
			Ingredient input = Ingredient.fromJson(inputElement);
			if (!object.has("result"))
				throw new JsonSyntaxException("Missing result, expected to find a string or object");
			ItemStack result;
			if (object.get("result").isJsonObject())
				result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(object, "result"), true, true);
			else {
				String s1 = GsonHelper.getAsString(object, "result");
				ResourceLocation resourcelocation = new ResourceLocation(s1);
				result = new ItemStack(ForgeRegistries.ITEMS.getHolder(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
			}
			return new AlterationRecipe(id, input, result);
		}

		public AlterationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
			Ingredient input = Ingredient.fromNetwork(buffer);
			ItemStack result = buffer.readItem();
			return new AlterationRecipe(id, input, result);
		}

		public void toNetwork(FriendlyByteBuf buffer, AlterationRecipe recipe) {
			recipe.input.toNetwork(buffer);
			buffer.writeItem(recipe.result);
		}
	}
}
