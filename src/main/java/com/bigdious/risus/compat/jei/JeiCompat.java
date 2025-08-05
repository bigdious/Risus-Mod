package com.bigdious.risus.compat.jei;

import com.bigdious.risus.Risus;
import com.bigdious.risus.compat.AlterationCategory;
import com.bigdious.risus.data.custom.SmithingUpgradeRecipe;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusRecipes;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
		registration.getSmithingCategory().addExtension(SmithingUpgradeRecipe.class, new SmithingUpgradeExtension());
	}

	@Override
	@SuppressWarnings("unchecked")
	public void registerRecipes(IRecipeRegistration registration) {
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

}
