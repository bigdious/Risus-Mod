package com.bigdious.risus.data.custom;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
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

	public static final Map<Item, String> UPGRADE_GALLERY = Map.ofEntries(
		Map.entry(Items.SKELETON_SKULL, "skeleton"),
		Map.entry(Items.CREEPER_HEAD, "creeper"),
		Map.entry(Items.WITHER_SKELETON_SKULL, "wither_skeleton"),
		Map.entry(Items.ZOMBIE_HEAD, "zombie"),
		Map.entry(Items.PIGLIN_HEAD, "piglin"),
		Map.entry(Items.DRAGON_HEAD, "ender_dragon"),
		Map.entry(Items.LIME_WOOL, "tuxedo_cat"),
		Map.entry(Items.BLACK_WOOL, "black_cat"),
		Map.entry(Items.LIGHT_GRAY_WOOL, "british_cat"),
		Map.entry(Items.ORANGE_WOOL, "calico_cat"),
		Map.entry(Items.GRAY_WOOL, "jellie_cat"),
		Map.entry(Items.WHITE_WOOL, "persian_cat"),
		Map.entry(Items.LIGHT_BLUE_WOOL, "ragdoll_cat"),
		Map.entry(Items.GREEN_WOOL, "orange_cat"),
		Map.entry(Items.BLUE_WOOL, "siamese_cat"),
		Map.entry(Items.BROWN_WOOL, "tabby_cat"),
		Map.entry(Items.YELLOW_WOOL, "white_cat"),
		Map.entry(Items.CARVED_PUMPKIN, "pumpkin")
	);

	public boolean matches(SmithingRecipeInput input, Level level) {
		return this.template.test(input.template()) && this.base.test(input.base()) && this.addition.test(input.addition());
	}
//checking for nonnull is to prevent item diversity issue
	public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
		ItemStack itemstack = input.template();
		if (this.base.test(input.base()) && itemstack.get(RisusDataComponents.ABILITY_VARIANT) == null) {
				ItemStack itemstack1 = itemstack.copyWithCount(1);
				itemstack1.set(RisusDataComponents.ABILITY_VARIANT, UPGRADE_GALLERY.get(input.base().getItem()));
				return itemstack1;
			}
		return ItemStack.EMPTY;
	}

	public ItemStack getResultItem(HolderLookup.Provider registries) {
		ItemStack itemstack = new ItemStack(RisusItems.SINNER_ROBES_CHESTPLATE.get());
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
