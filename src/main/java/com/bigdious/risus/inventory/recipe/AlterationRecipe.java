package com.bigdious.risus.inventory.recipe;

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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record AlterationRecipe(Ingredient input, ItemStack result) implements Recipe<Container> {

	@Override
	public boolean matches(Container container, Level level) {
		return this.input.test(container.getItem(0));
	}

	@Override
	public ItemStack assemble(Container container, HolderLookup.Provider pRegistries) {
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
