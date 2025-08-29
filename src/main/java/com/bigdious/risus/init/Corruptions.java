package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.EnchantmentLevelProvider;

public class Corruptions {
	public static final ResourceKey<Enchantment> HUNTERS_EXULTATION = registerKey("hunters_exultation");

	private static ResourceKey<Enchantment> registerKey(String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, Risus.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Enchantment> context) {
		HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
		HolderGetter<Item> items = context.lookup(Registries.ITEM);
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

		register(context, HUNTERS_EXULTATION, new Enchantment.Builder(Enchantment.definition(
			items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
			items.getOrThrow(RisusTags.Items.SWORD_AND_TRIDENT_ENCHANTABLE),
			1,
			5,
			Enchantment.dynamicCost(5, 8),
			Enchantment.dynamicCost(25, 8),
			2,
			EquipmentSlotGroup.MAINHAND
		))
			.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
			.withEffect(
				EnchantmentEffectComponents.DAMAGE,
				new AddValue(LevelBasedValue.perLevel(3F)),
				LootItemEntityPropertyCondition.hasProperties(
					LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(RisusTags.Entities.SENSITIVE_TO_HUNTERS))
				))
			.withEffect(
				EnchantmentEffectComponents.DAMAGE,
				new AddValue(LevelBasedValue.perLevel(-1, -0.5F))
		));
	}
	private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
		context.register(key, builder.build(key.location()));
	}
}
