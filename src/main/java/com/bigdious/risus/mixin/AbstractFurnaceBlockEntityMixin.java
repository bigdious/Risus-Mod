package com.bigdious.risus.mixin;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

	@Inject(
		method = "burn",
		at = @At("HEAD"),
		cancellable = true
	)
	private static void burn(RegistryAccess registryAccess, RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, AbstractFurnaceBlockEntity furnace, CallbackInfoReturnable<Boolean> cir) {
		if (recipe != null && canBurn(registryAccess, recipe, inventory, maxStackSize, furnace)) {
			ItemStack itemstack = inventory.get(0); // Input slot
			ItemStack itemstack1 = ((AbstractCookingRecipe)recipe.value()).assemble(new SingleRecipeInput(furnace.getItem(0)), registryAccess); // Result of the recipe
			ItemStack itemstack2 = inventory.get(2); // Output slot

			tryBurnBloodySponge(furnace, inventory); // Custom method to handle bloody sponge conversion
			itemstack.shrink(1); // Consume the input item
			cir.setReturnValue(true); // Return true to indicate the burn was successful
		} else {
			cir.setReturnValue(false); // Return false if the burn was not successful
		}
	}


	private static void tryBurnBloodySponge(AbstractFurnaceBlockEntity furnace, NonNullList<ItemStack> inventory) {
		ItemStack itemstack = inventory.get(0); // Input slot
		ItemStack itemstack1 = inventory.get(2); // Output slot

		if (itemstack.is(RisusBlocks.BLOODY_SPONGE.asItem()) && !itemstack1.isEmpty() && itemstack1.is(Items.BUCKET)) {
			inventory.set(1, new ItemStack(RisusItems.BLOOD_BUCKET.asItem())); // Replace with your mod's blood bucket item
		}
	}

	private static boolean canBurn(RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, AbstractFurnaceBlockEntity furnace) {
		if (!inventory.get(0).isEmpty() && recipe != null) {
			ItemStack itemstack = ((AbstractCookingRecipe)recipe.value()).assemble(new SingleRecipeInput(furnace.getItem(0)), registryAccess);
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = inventory.get(2);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!ItemStack.isSameItemSameComponents(itemstack1, itemstack)) {
					return false;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= maxStackSize && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize() || itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}
}