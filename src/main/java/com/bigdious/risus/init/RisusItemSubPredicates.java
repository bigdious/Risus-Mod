package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.advancement.predicate.ItemHornsPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicate;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusItemSubPredicates {
	public static final DeferredRegister<ItemSubPredicate.Type<?>> TYPES = DeferredRegister.create(Registries.ITEM_SUB_PREDICATE_TYPE, Risus.MODID);

	public static final DeferredHolder<ItemSubPredicate.Type<?>, ItemSubPredicate.Type<ItemHornsPredicate>> HORNS = TYPES.register("horns", () -> new ItemSubPredicate.Type<>(ItemHornsPredicate.CODEC));
}
