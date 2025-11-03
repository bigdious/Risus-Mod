package com.bigdious.risus.client.patchouli;

import com.bigdious.risus.data.custom.SmithingUpgradeRecipe;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class AbilitySmithingProcessor implements IComponentProcessor {
	//ain't pretty but it works
	RecipeHolder<? extends SmithingUpgradeRecipe> holder;
	RecipeHolder<? extends SmithingUpgradeRecipe> holder2;

	@SuppressWarnings("unchecked")
	@Override
	public void setup(Level level, IVariableProvider variables) {
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		String recipeID = variables.get("recipe", level.registryAccess()).asString();
		holder = (RecipeHolder<? extends SmithingUpgradeRecipe>) manager.byKey(ResourceLocation.tryParse(recipeID)).orElse(null);
		if (variables.has("recipe2")) {
			String recipeID2 = variables.get("recipe2", level.registryAccess()).asString();
			holder2 = (RecipeHolder<? extends SmithingUpgradeRecipe>) manager.byKey(ResourceLocation.tryParse(recipeID2)).orElse(null);
		}
	}

	@Override
	public IVariable process(Level level, String key) {
		if (holder == null)
			return null;
		var recipe = holder.value();

		if (key.equals("template")) {
			return IVariable.from(recipe.getTemplate(), level.registryAccess());
		}
		if (key.equals("base")) {
			return IVariable.from(recipe.getBase(), level.registryAccess());
		}
		if (key.equals("addition")) {
			return IVariable.from(recipe.getAddition(), level.registryAccess());
		}
		if (key.equals("output")) {
			return IVariable.from(recipe.getResultItem(level.registryAccess()), level.registryAccess());
		}
		if (holder2 != null) {
			var recipe2 = holder2.value();
			if (key.equals("template2")) {
				return IVariable.from(recipe2.getTemplate(), level.registryAccess());
			}
			if (key.equals("base2")) {
				return IVariable.from(recipe2.getBase(), level.registryAccess());
			}
			if (key.equals("addition2")) {
				return IVariable.from(recipe2.getAddition(), level.registryAccess());
			}
			if (key.equals("output2")) {
				return IVariable.from(recipe2.getResultItem(level.registryAccess()), level.registryAccess());
			}
		}

		return null;
	}
}
