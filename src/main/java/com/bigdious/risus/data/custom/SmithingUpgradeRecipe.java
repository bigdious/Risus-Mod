package com.bigdious.risus.data.custom;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusRecipes;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class SmithingUpgradeRecipe implements SmithingRecipe {
	final Ingredient template;
	final Ingredient base;
	final Ingredient addition;

	public SmithingUpgradeRecipe(Ingredient template, Ingredient base, Ingredient addition) {
		this.template = template;
		this.base = base;
		this.addition = addition;
	}

	public static final Map<Item, String> UPGRADE_GALLERY = Map.of(
		Items.SKELETON_SKULL, "skeleton",
		Items.CREEPER_HEAD, "creeper",
		Items.WITHER_SKELETON_SKULL, "wither_skeleton",
		Items.ZOMBIE_HEAD, "zombie"
	);

	public boolean matches(SmithingRecipeInput input, Level level) {
		return this.template.test(input.template()) && this.base.test(input.base()) && this.addition.test(input.addition());
	}

	public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
		ItemStack itemstack = input.base();
		if (this.base.test(itemstack)) {
				ItemStack itemstack1 = itemstack.copyWithCount(1);
				//I'm doing boolean cause I am lazy
				itemstack1.set(RisusDataComponents.ABILITY_VARIANT, UPGRADE_GALLERY.get(input.addition().getItem()));
				return itemstack1;
			}
		return ItemStack.EMPTY;
	}

	public ItemStack getResultItem(HolderLookup.Provider registries) {
		ItemStack itemstack = new ItemStack(Items.IRON_CHESTPLATE);
		Optional<Holder.Reference<TrimPattern>> optional = registries.lookupOrThrow(Registries.TRIM_PATTERN).listElements().findFirst();
		Optional<Holder.Reference<TrimMaterial>> optional1 = registries.lookupOrThrow(Registries.TRIM_MATERIAL).get(TrimMaterials.REDSTONE);
		if (optional.isPresent() && optional1.isPresent()) {
			itemstack.set(DataComponents.TRIM, new ArmorTrim(optional1.get(), optional.get()));
		}

		return itemstack;
	}

	public boolean isTemplateIngredient(ItemStack stack) {
		return this.template.test(stack);
	}

	public boolean isBaseIngredient(ItemStack stack) {
		return this.base.test(stack);
	}

	public boolean isAdditionIngredient(ItemStack stack) {
		return this.addition.test(stack);
	}

	public RecipeSerializer<?> getSerializer() {
		return RisusRecipes.SMITHING_UPGRADE_SERIALIZER.get();
	}

	public boolean isIncomplete() {
		return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::hasNoItems);
	}

	public static class Serializer implements RecipeSerializer<SmithingUpgradeRecipe> {
		private static final MapCodec<SmithingUpgradeRecipe> CODEC = RecordCodecBuilder.mapCodec((p_301227_) -> p_301227_.group(Ingredient.CODEC.fieldOf("template").forGetter((p_301070_) -> p_301070_.template), Ingredient.CODEC.fieldOf("base").forGetter((p_300969_) -> p_300969_.base), Ingredient.CODEC.fieldOf("addition").forGetter((p_300977_) -> p_300977_.addition)).apply(p_301227_, SmithingUpgradeRecipe::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, SmithingUpgradeRecipe> STREAM_CODEC = StreamCodec.of(SmithingUpgradeRecipe.Serializer::toNetwork, SmithingUpgradeRecipe.Serializer::fromNetwork);

		public Serializer() {
		}

		public MapCodec<SmithingUpgradeRecipe> codec() {
			return CODEC;
		}

		public StreamCodec<RegistryFriendlyByteBuf, SmithingUpgradeRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static SmithingUpgradeRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			Ingredient ingredient2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			return new SmithingUpgradeRecipe(ingredient, ingredient1, ingredient2);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, SmithingUpgradeRecipe recipe) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
		}
	}
}
