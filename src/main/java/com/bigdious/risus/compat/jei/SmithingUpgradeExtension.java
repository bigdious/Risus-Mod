package com.bigdious.risus.compat.jei;

import com.bigdious.risus.data.custom.SmithingUpgradeRecipe;
import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.recipe.category.extensions.vanilla.smithing.ISmithingCategoryExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;

public class SmithingUpgradeExtension implements ISmithingCategoryExtension<SmithingUpgradeRecipe> {

	@Override
	public <T extends IIngredientAcceptor<T>> void setTemplate(SmithingUpgradeRecipe recipe, T acceptor) {
		acceptor.addIngredients(recipe.getTemplate());
	}

	@Override
	public <T extends IIngredientAcceptor<T>> void setBase(SmithingUpgradeRecipe recipe, T acceptor) {
		acceptor.addIngredients(recipe.getBase());
	}

	@Override
	public <T extends IIngredientAcceptor<T>> void setAddition(SmithingUpgradeRecipe recipe, T acceptor) {
		acceptor.addIngredients(recipe.getAddition());
	}

	@Override
	public <T extends IIngredientAcceptor<T>> void setOutput(SmithingUpgradeRecipe recipe, T acceptor) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		assert level != null;
		RegistryAccess registryAccess = level.registryAccess();
		ItemStack resultItem = recipe.getResultItem(registryAccess);
		acceptor.addItemStack(resultItem);
	}
}
