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
	RecipeHolder<? extends AlterationRecipe> holder;
	RecipeHolder<? extends AlterationRecipe> holder2;

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

		return null;
	}
}
