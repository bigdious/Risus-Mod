package com.bigdious.risus.data.custom;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusRecipes;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class SmithingUpgradeRecipe implements SmithingRecipe {
	private final Ingredient template;
	private final Ingredient base;
	private final Ingredient addition;
	private final List<TypedDataComponent<?>> additionalData;

	public SmithingUpgradeRecipe(Ingredient template, Ingredient base, Ingredient addition, List<TypedDataComponent<?>> additionalData) {
		this.template = template;
		this.base = base;
		this.addition = addition;
		this.additionalData = additionalData;
	}

	@Override
	public boolean matches(SmithingRecipeInput input, Level level) {
		if (!this.template.test(input.getItem(0)) || !this.base.test(input.getItem(1)) || !this.addition.test(input.getItem(2))) return false;
		ItemStack armor = input.getItem(0);

		for (TypedDataComponent<?> data : this.additionalData)
			if (armor.has(data.type()))
				return false;

		return true;
	}

	//checking for nonnull is to prevent item diversity issue
	public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
		return Util.make(input.getItem(0).copy(), this::setComponents);
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider access) {
		for (ItemStack itemstack : this.template.getItems()) {
			return Util.make(new ItemStack(itemstack.getItem()), this::setComponents);
		}

		return Util.make(new ItemStack(RisusItems.SINNER_ROBES_CHESTPLATE.get()), this::setComponents);
	}

	@Override
	public boolean isTemplateIngredient(ItemStack stack) {
		return this.template.test(stack);
	}

	@Override
	public boolean isBaseIngredient(ItemStack stack) {
		return this.base.test(stack);
	}

	@Override
	public boolean isAdditionIngredient(ItemStack stack) {
		return this.addition.test(stack);
	}

	public Ingredient getTemplate() {
		return this.template;
	}

	public Ingredient getBase() {
		return this.base;
	}

	public Ingredient getAddition() {
		return this.addition;
	}

	private List<TypedDataComponent<?>> additionalData() {
		return this.additionalData;
	}

	private void setComponents(ItemStack itemstack) {
		for (TypedDataComponent<?> data : this.additionalData)
			setComponent(data, itemstack);
	}

	private static <T> void setComponent(TypedDataComponent<T> data, ItemStack stack) {
		stack.set(data.type(), data.value());
	}

	private static <T> void setComponent(TypedDataComponent<T> data, DataComponentMap.Builder builder) {
		builder.set(data.type(), data.value());
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RisusRecipes.SMITHING_UPGRADE_SERIALIZER.get();
	}

	@Override
	public boolean isIncomplete() {
		return Stream.of(this.base, this.addition).anyMatch(Ingredient::hasNoItems);
	}

	private static final Codec<List<TypedDataComponent<?>>> DATA_COMPONENT_CODEC = DataComponentMap.CODEC.xmap(typedDataComponents -> typedDataComponents.stream().toList(), typedDataComponents -> {
		DataComponentMap.Builder builder = DataComponentMap.builder();

		for (TypedDataComponent<?> typedDataComponent : typedDataComponents)
			setComponent(typedDataComponent, builder);

		return builder.build();
	});

	public static class Serializer implements RecipeSerializer<SmithingUpgradeRecipe> {
		private static final MapCodec<SmithingUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("template").forGetter(SmithingUpgradeRecipe::getTemplate),
			Ingredient.CODEC.fieldOf("base").forGetter(SmithingUpgradeRecipe::getBase),
			Ingredient.CODEC.fieldOf("addition").forGetter(SmithingUpgradeRecipe::getAddition),
			DATA_COMPONENT_CODEC.optionalFieldOf("additional_data", List.of()).forGetter(SmithingUpgradeRecipe::additionalData)
		).apply(instance, SmithingUpgradeRecipe::new));

		private static final StreamCodec<RegistryFriendlyByteBuf, SmithingUpgradeRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, SmithingUpgradeRecipe::getTemplate,
			Ingredient.CONTENTS_STREAM_CODEC, SmithingUpgradeRecipe::getBase,
			Ingredient.CONTENTS_STREAM_CODEC, SmithingUpgradeRecipe::getAddition,
			TypedDataComponent.STREAM_CODEC.apply(ByteBufCodecs.list()), SmithingUpgradeRecipe::additionalData,
			SmithingUpgradeRecipe::new
		);

		@Override
		public MapCodec<SmithingUpgradeRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, SmithingUpgradeRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
