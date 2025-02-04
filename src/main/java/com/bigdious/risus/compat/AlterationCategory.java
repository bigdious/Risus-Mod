package com.bigdious.risus.compat;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class AlterationCategory implements IRecipeCategory<AlterationRecipe> {
	public static final RecipeType<AlterationRecipe> ALTERATION = RecipeType.create(Risus.MODID, "alteration", AlterationRecipe.class);
	private final IDrawable background;
	private final IDrawable icon;

	private final Component localizedName;

	public AlterationCategory(IGuiHelper helper) {
		ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Risus.MODID, "textures/gui/alteration_gui.png");
		this.background = helper.createDrawable(location, 0, 0, 116, 54);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, RisusBlocks.ALTERATION_CATALYST.asItem().getDefaultInstance());
		this.localizedName = Component.translatable("gui.risus.alteration_jei");
	}
	@Override
	public RecipeType<AlterationRecipe> getRecipeType() {
		return ALTERATION;
	}

	@Override
	public Component getTitle() {
		return this.localizedName;
	}
	@Override
	@SuppressWarnings("removal")
	public IDrawable getBackground() {
		return this.background;
	}
	@Override
	public IDrawable getIcon() {
		return this.icon;
	}
	@Override
	public void draw(AlterationRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		this.background.draw(graphics, 0, 0);
//		if (mouseX > 8 && mouseX < 43 && mouseY > 9 && mouseY < 44) {
//			AbstractContainerScreen.renderSlotHighlight(graphics, 10, 11, 0);
//			AbstractContainerScreen.renderSlotHighlight(graphics, 26, 11, 0);
//			AbstractContainerScreen.renderSlotHighlight(graphics, 10, 27, 0);
//			AbstractContainerScreen.renderSlotHighlight(graphics, 26, 27, 0);
//		}
}
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, AlterationRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 19, 19).addIngredients(recipe.input());
		builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 19).addItemStack(new ItemStack(recipe.result().getItem()));
	}
}
