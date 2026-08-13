package com.bigdious.risus.compat.jei;

import com.bigdious.risus.Risus;
import com.bigdious.risus.compat.AlterationCategory;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusRecipes;
import com.bigdious.risus.init.RisusTags;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import com.google.common.collect.Lists;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.common.util.RegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.enchantment.*;

import java.util.*;
import java.util.stream.IntStream;

@JeiPlugin
@SuppressWarnings("unused")
public class JeiCompat implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return Risus.prefix("jei_plugin");
	}
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new AlterationCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(RisusBlocks.ALTERATION_CATALYST), AlterationCategory.ALTERATION);
	}


	@Override
	@SuppressWarnings("unchecked")
	public void registerRecipes(IRecipeRegistration registration) {
		//adding all risus enchanted items as they use different tags from normal
		Registry<Enchantment> registry = RegistryUtil.getRegistry(Registries.ENCHANTMENT);
		IVanillaRecipeFactory vanillaRecipeFactory = registration.getVanillaRecipeFactory();
		List<IJeiAnvilRecipe> recipes = new ArrayList<>();
		//TODO clean up these for loops somehow
		ItemStack crescent = new ItemStack(RisusItems.CRESCENT_DISASTER.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.CRESCENT_DISASTER_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(crescent, enchantedWith, getEnchantedResults(crescent, enchantedWith)
			));
		}
		ItemStack bigAxe = new ItemStack(RisusItems.UNAWAKENED_VESSEL.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.CRESCENT_DISASTER_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(bigAxe, enchantedWith, getEnchantedResults(bigAxe, enchantedWith)
			));
		}
		ItemStack blade = new ItemStack(RisusItems.THOUSAND_BLADE.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.THOUSAND_BLADE_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(blade, enchantedWith, getEnchantedResults(blade, enchantedWith)
			));
		}
		ItemStack killjoy = new ItemStack(RisusItems.KILLJOY.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.KILLJOY_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(killjoy, enchantedWith, getEnchantedResults(killjoy, enchantedWith)
			));
		}
		ItemStack scythe = new ItemStack(RisusItems.SCYTHE.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.SCYTHE_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(scythe, enchantedWith, getEnchantedResults(scythe, enchantedWith)
			));
		}
		ItemStack soulScythe = new ItemStack(RisusItems.SOUL_SCYTHE.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.SOUL_SCYTHE_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(soulScythe, enchantedWith, getEnchantedResults(soulScythe, enchantedWith)
			));
		}
		ItemStack fireScythe = new ItemStack(RisusItems.FIRE_SCYTHE.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.FIRE_SCYTHE_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(fireScythe, enchantedWith, getEnchantedResults(fireScythe, enchantedWith)
			));
		}
		ItemStack cindergleeScythe = new ItemStack(RisusItems.CINDERGLEE_SCYTHE.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.CINDERGLEE_SCYTHE_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(cindergleeScythe, enchantedWith, getEnchantedResults(cindergleeScythe, enchantedWith)
			));
		}
		ItemStack toothknocker = new ItemStack(RisusItems.TOOTHKNOCKER.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.TOOTHKNOCKER_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(toothknocker, enchantedWith, getEnchantedResults(toothknocker, enchantedWith)
			));
		}
		ItemStack goldfist = new ItemStack(RisusItems.GOLD_FIST.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.TOOTHKNOCKER_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(goldfist, enchantedWith, getEnchantedResults(goldfist, enchantedWith)
			));
		}
		ItemStack boomstick = new ItemStack(RisusItems.BOOMSTICK.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.BOOMSTICK_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(boomstick, enchantedWith, getEnchantedResults(boomstick, enchantedWith)
			));
		}
		ItemStack warhorn = new ItemStack(RisusItems.WARHORN.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.WARHORN_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(warhorn, enchantedWith, getEnchantedResults(warhorn, enchantedWith)
			));
		}
		ItemStack hexhorn = new ItemStack(RisusItems.HEXHORN.get());
		for (Holder<Enchantment> enchant : registry.getTag(RisusTags.Enchantments.HEXHORN_ALLOWED_ENCHANTS).get()) {
			List<ItemStack> enchantedWith = getEnchantments(enchant);
			recipes.add(vanillaRecipeFactory.createAnvilRecipe(hexhorn, enchantedWith, getEnchantedResults(hexhorn, enchantedWith)
			));
		}
		registration.addRecipes(RecipeTypes.ANVIL, recipes);

		RecipeManager manager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		List<RecipeHolder<? extends AlterationRecipe>> alterationRecipes = getAllAlterationRecipes(manager);
		registration.addRecipes(AlterationCategory.ALTERATION, (List<AlterationRecipe>) alterationRecipes.stream().map(RecipeHolder::value).toList());
	}

	public static List<RecipeHolder<? extends AlterationRecipe>> getAllAlterationRecipes(RecipeManager manager) {
			List<RecipeHolder<? extends AlterationRecipe>> recipes = new ArrayList<>(manager.getAllRecipesFor(RisusRecipes.ALTERATION_RECIPE.get()));
			recipes = new ArrayList<>(recipes);
			recipes.addAll(manager.getAllRecipesFor(RisusRecipes.ALTERATION_RECIPE.get()));
			return recipes;
	}

	private static List<ItemStack> getEnchantments(Holder<Enchantment> enchantment) {
		return IntStream.rangeClosed(1, enchantment.value().getMaxLevel())
			.mapToObj(level -> {
				ItemStack bookEnchant = new ItemStack(Items.ENCHANTED_BOOK);
				ItemEnchantments.Mutable itemEnchantments = new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(bookEnchant));
				itemEnchantments.set(enchantment, level);
				EnchantmentHelper.setEnchantments(bookEnchant, itemEnchantments.toImmutable());
				return bookEnchant;
			})
			.toList();
	}

	private static List<ItemStack> getEnchantedResults(ItemStack ingredient, List<ItemStack> enchantedBooks) {
		return Lists.transform(enchantedBooks, enchantedBook -> getEnchantedItem(ingredient, enchantedBook));
	}

	private static ItemStack getEnchantedItem(ItemStack ingredient, ItemStack enchantedBook) {
		ItemStack enchantedIngredient = ingredient.copy();
		ItemEnchantments enchantments = EnchantmentHelper.getEnchantmentsForCrafting(enchantedBook);
		EnchantmentHelper.setEnchantments(enchantedIngredient, enchantments);
		return enchantedIngredient;
	}

}
