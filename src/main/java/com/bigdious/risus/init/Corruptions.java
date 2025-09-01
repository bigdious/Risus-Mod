package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.corruptions.AttractTargetEffect;
import com.bigdious.risus.corruptions.EatExperienceBarEffect;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.EnchantmentLevelProvider;

public class Corruptions {
	public static final ResourceKey<Enchantment> HUNTERS_EXULTATION = registerKey("hunters_exultation");
	public static final ResourceKey<Enchantment> ELEMENTAL_DEVIATION = registerKey("elemental_deviation");
	public static final ResourceKey<Enchantment> DREAM_EATER = registerKey("dream_eater");
	public static final ResourceKey<Enchantment> PULL = registerKey("pull");

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

		register(context, ELEMENTAL_DEVIATION, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
				5,
				4,
				Enchantment.dynamicCost(10, 8),
				Enchantment.dynamicCost(18, 8),
				2,
				EquipmentSlotGroup.ARMOR
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.ARMOR_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.DAMAGE_PROTECTION,
					new AddValue(LevelBasedValue.perLevel(2.0F)),
					AllOfCondition.allOf(
						DamageSourceCondition.hasDamageSource(
							DamageSourcePredicate.Builder.damageType()
								.tag(TagPredicate.is(DamageTypeTags.IS_FIRE))
								.tag(TagPredicate.is(DamageTypeTags.IS_EXPLOSION))
								.tag(TagPredicate.is(DamageTypeTags.IS_PROJECTILE))
								.tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
						)
					)
				)
				.withEffect(
					EnchantmentEffectComponents.DAMAGE_PROTECTION,
					new AddValue(LevelBasedValue.perLevel(-2.0F)),
					DamageSourceCondition.hasDamageSource(
						DamageSourcePredicate.Builder.damageType()
							.tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
							.tag(TagPredicate.isNot(DamageTypeTags.IS_FIRE))
							.tag(TagPredicate.isNot(DamageTypeTags.IS_EXPLOSION))
							.tag(TagPredicate.isNot(DamageTypeTags.IS_PROJECTILE))
					)
				)
				.withEffect(
					EnchantmentEffectComponents.ATTRIBUTES,
					new EnchantmentAttributeEffect(
						ResourceLocation.withDefaultNamespace("enchantment.fire_protection"),
						Attributes.BURNING_TIME,
						LevelBasedValue.perLevel(-0.15F),
						AttributeModifier.Operation.ADD_MULTIPLIED_BASE
					)
				)
				.withEffect(
					EnchantmentEffectComponents.ATTRIBUTES,
					new EnchantmentAttributeEffect(
						ResourceLocation.withDefaultNamespace("enchantment.blast_protection"),
						Attributes.EXPLOSION_KNOCKBACK_RESISTANCE,
						LevelBasedValue.perLevel(0.15F),
						AttributeModifier.Operation.ADD_VALUE
					)
				)
		);

		register(context, DREAM_EATER, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
				2,
				1,
				Enchantment.dynamicCost(25, 25),
				Enchantment.dynamicCost(75, 25),
				4,
				EquipmentSlotGroup.ANY
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.MENDING)))
				.withEffect(EnchantmentEffectComponents.TICK, new EatExperienceBarEffect())
		);

		register(context, PULL, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
				2,
				2,
				Enchantment.dynamicCost(12, 20),
				Enchantment.dynamicCost(37, 20),
				4,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.PUNCH)))
			.withEffect(
				EnchantmentEffectComponents.POST_ATTACK,
				EnchantmentTarget.ATTACKER,
				EnchantmentTarget.VICTIM,
				AllOf.entityEffects(
					new AttractTargetEffect(LevelBasedValue.perLevel(0.08F, 0.04F))
				)
			)
		);

	}
	private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
		context.register(key, builder.build(key.location()));
	}
}
