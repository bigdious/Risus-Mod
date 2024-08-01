package com.bigdious.risus.inventory.recipe;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusRecipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record AlterationRecipe(Ingredient input, ItemStack result) implements Recipe<RecipeInput> {

	@Override
	public boolean matches(RecipeInput recipeInput, Level level) {
		return this.input.test(recipeInput.getItem(0));
	}

	@Override
	public ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider pRegistries) {
		return this.result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
		return this.result;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(RisusBlocks.ALTERATION_CATALYST.get());
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

		public static final MapCodec<AlterationRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
				Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(AlterationRecipe::input),
				ItemStack.SIMPLE_ITEM_CODEC.fieldOf("result").forGetter(AlterationRecipe::result)
		).apply(instance, AlterationRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, AlterationRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.input,
			ItemStack.STREAM_CODEC, recipe -> recipe.result,
			AlterationRecipe::new);


		@Override
		public MapCodec<AlterationRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, AlterationRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
