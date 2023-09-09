package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RisusRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Risus.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Risus.MODID);

	public static final RegistryObject<RecipeSerializer<AlterationRecipe>> ALTERATION_SERIALIZER = RECIPE_SERIALIZERS.register("alteration", AlterationRecipe.Serializer::new);

	public static final RegistryObject<RecipeType<AlterationRecipe>> ALTERATION_RECIPE = RECIPE_TYPES.register("alteration", () -> RecipeType.simple(Risus.prefix("alteration")));

}
