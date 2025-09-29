package com.bigdious.risus.client.patchouli;

import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AlterationProcessor implements IComponentProcessor {
	//ain't pretty but it works
	RecipeHolder<? extends AlterationRecipe> holder;
	RecipeHolder<? extends AlterationRecipe> holder2;
	RecipeHolder<? extends AlterationRecipe> holder3;
	RecipeHolder<? extends AlterationRecipe> holder4;
	RecipeHolder<? extends AlterationRecipe> holder5;

	@SuppressWarnings("unchecked")
	@Override
	public void setup(Level level, IVariableProvider variables) {
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		String recipeID = variables.get("recipe", level.registryAccess()).asString();
		holder = (RecipeHolder<? extends AlterationRecipe>) manager.byKey(ResourceLocation.tryParse(recipeID)).orElse(null);
		if (variables.has("recipe2")) {
			String recipeID2 = variables.get("recipe2", level.registryAccess()).asString();
			holder2 = (RecipeHolder<? extends AlterationRecipe>) manager.byKey(ResourceLocation.tryParse(recipeID2)).orElse(null);
		}
		if (variables.has("recipe3")) {
			String recipeID3 = variables.get("recipe3", level.registryAccess()).asString();
			holder3 = (RecipeHolder<? extends AlterationRecipe>) manager.byKey(ResourceLocation.tryParse(recipeID3)).orElse(null);
		}
		if (variables.has("recipe4")) {
			String recipeID4 = variables.get("recipe4", level.registryAccess()).asString();
			holder4 = (RecipeHolder<? extends AlterationRecipe>) manager.byKey(ResourceLocation.tryParse(recipeID4)).orElse(null);
		}
		if (variables.has("recipe5")) {
			String recipeID5 = variables.get("recipe5", level.registryAccess()).asString();
			holder5 = (RecipeHolder<? extends AlterationRecipe>) manager.byKey(ResourceLocation.tryParse(recipeID5)).orElse(null);
		}
	}

	@Override
	public IVariable process(Level level, String key) {
		if (holder == null)
			return null;
		var recipe = holder.value();

		if (key.equals("input")) {
			return IVariable.from(recipe.input(), level.registryAccess());
		}
		if (key.equals("output")) {
			return IVariable.from(recipe.result(), level.registryAccess());
		}
		if (holder2 != null) {
			var recipe2 = holder2.value();
			if (key.equals("input2")) {
				return IVariable.from(recipe2.input(), level.registryAccess());
			}
			if (key.equals("output2")) {
				return IVariable.from(recipe2.result(), level.registryAccess());
			}
		}
		if (holder3 != null) {
			var recipe3 = holder3.value();
			if (key.equals("input3")) {
				return IVariable.from(recipe3.input(), level.registryAccess());
			}
			if (key.equals("output3")) {
				return IVariable.from(recipe3.result(), level.registryAccess());
			}
		}
		if (holder4 != null) {
			var recipe4 = holder4.value();
			if (key.equals("input4")) {
				return IVariable.from(recipe4.input(), level.registryAccess());
			}
			if (key.equals("output4")) {
				return IVariable.from(recipe4.result(), level.registryAccess());
			}
		}
		if (holder5 != null) {
			var recipe5 = holder5.value();
			if (key.equals("input5")) {
				return IVariable.from(recipe5.input(), level.registryAccess());
			}
			if (key.equals("output5")) {
				return IVariable.from(recipe5.result(), level.registryAccess());
			}
		}

		return null;
	}
}
