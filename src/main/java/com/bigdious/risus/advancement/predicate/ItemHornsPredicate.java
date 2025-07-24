package com.bigdious.risus.advancement.predicate;

import com.bigdious.risus.components.item.WarhornComponent;
import com.bigdious.risus.init.RisusDataComponents;
import com.mojang.serialization.Codec;
import net.minecraft.advancements.critereon.ItemPotionsPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicate;
import net.minecraft.advancements.critereon.SingleComponentItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.Optional;

public record ItemHornsPredicate(HolderSet<Potion> potions) implements SingleComponentItemPredicate<WarhornComponent> {
	//if you don't understand what's happening here, then we're in the same bucket. Somehow it just fucking works, so let's leave it at that
	public static final Codec<ItemHornsPredicate> CODEC;

	public ItemHornsPredicate(HolderSet<Potion> potions) {
		this.potions = potions;
	}

	public DataComponentType<WarhornComponent> componentType() {
		return RisusDataComponents.WARHORN_CONTENT.get();
	}

	public boolean matches(ItemStack stack, WarhornComponent value) {
		Optional<Holder<Potion>> optional = value.potion().potion();
		return !optional.isEmpty() && this.potions.contains(optional.get());
	}

	public static ItemSubPredicate potions(HolderSet<Potion> potions) {
		return new ItemHornsPredicate(potions);
	}

	public HolderSet<Potion> potions() {
		return this.potions;
	}

	static {
		CODEC = RegistryCodecs.homogeneousList(Registries.POTION).xmap(ItemHornsPredicate::new, ItemHornsPredicate::potions);
	}
}
