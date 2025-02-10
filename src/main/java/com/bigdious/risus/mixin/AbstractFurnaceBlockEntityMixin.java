package com.bigdious.risus.mixin;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

	@Inject(method = "burn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
	private static void risus$bloodBucket(RegistryAccess access, RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, AbstractFurnaceBlockEntity furnace, CallbackInfoReturnable<Boolean> cir) {
		if (inventory.getFirst().is(RisusBlocks.BLOODY_SPONGE.asItem()) && inventory.get(1).is(Items.BUCKET)) {
			inventory.set(1, new ItemStack(RisusItems.BLOOD_BUCKET.get()));
		}
	}

	@WrapOperation(method = "canTakeItemThroughFace", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
	private boolean risus$bloodBucketExtraction(ItemStack instance, Item item, Operation<Boolean> original) {
		return original.call(instance, item) || instance.is(RisusItems.BLOOD_BUCKET);
	}
}