package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.execrations.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.*;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.EnchantmentLevelProvider;

import java.util.List;
import java.util.Optional;

public class Execrations {
	public static final ResourceKey<Enchantment> HUNTERS_EXULTATION = registerKey("hunters_exultation");
	public static final ResourceKey<Enchantment> ELEMENTAL_DEVIATION = registerKey("elemental_deviation");
	public static final ResourceKey<Enchantment> DREAM_EATER = registerKey("dream_eater");
	public static final ResourceKey<Enchantment> PULL = registerKey("pull");
	public static final ResourceKey<Enchantment> DENIAL = registerKey("denial");
	public static final ResourceKey<Enchantment> CACKLING_CRAZE = registerKey("cackling_craze");
	public static final ResourceKey<Enchantment> AGONY = registerKey("agony");
	public static final ResourceKey<Enchantment> PERPETUITY = registerKey("perpetuity");
	public static final ResourceKey<Enchantment> GENOCIDE = registerKey("genocide");
	public static final ResourceKey<Enchantment> EMPYREAN_CONDUIT = registerKey("empyrean_conduit");
	public static final ResourceKey<Enchantment> BATTERING = registerKey("battering");
	public static final ResourceKey<Enchantment> STAR_RELEASE = registerKey("star_release");
	public static final ResourceKey<Enchantment> OVERLOAD = registerKey("overload");
	public static final ResourceKey<Enchantment> MARITIME_SNARE = registerKey("maritime_snare");
	public static final ResourceKey<Enchantment> GRAVITY_WELL = registerKey("gravity_well");
	public static final ResourceKey<Enchantment> RELOCATION = registerKey("relocation");
	public static final ResourceKey<Enchantment> PYROMANIAC = registerKey("pyromaniac");
	public static final ResourceKey<Enchantment> AVARICIOUS_AMBIT = registerKey("avaricious_ambit");
	public static final ResourceKey<Enchantment> PRESERVATION = registerKey("preservation");
	public static final ResourceKey<Enchantment> VIGOR = registerKey("vigor");
	public static final ResourceKey<Enchantment> XENOPHOBIA = registerKey("xenophobia");
	public static final ResourceKey<Enchantment> DEFIANCE = registerKey("defiance");
	public static final ResourceKey<Enchantment> ERUPTION = registerKey("eruption");
	public static final ResourceKey<Enchantment> SOAR = registerKey("soar");
	public static final ResourceKey<Enchantment> FERVOUR = registerKey("fervour");
	public static final ResourceKey<Enchantment> PROLIFERATION = registerKey("proliferation");

	private static ResourceKey<Enchantment> registerKey(String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, Risus.prefix(name));
	}

	public static void bootstrap(BootstrapContext<Enchantment> context) {
		HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
		HolderGetter<Item> items = context.lookup(Registries.ITEM);
		HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

		register(context, HUNTERS_EXULTATION, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(RisusTags.Items.SWORD_AND_TRIDENT_ENCHANTABLE),
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
						LootContext.EntityTarget.THIS,
						EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(RisusTags.Entities.SENSITIVE_TO_HUNTERS))
					))
				.withEffect(
					EnchantmentEffectComponents.DAMAGE,
					new AddValue(LevelBasedValue.perLevel(-1, -0.5F))
				)
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new ApplyMobEffect(
						HolderSet.direct(MobEffects.MOVEMENT_SLOWDOWN),
						LevelBasedValue.constant(1.5F),
						LevelBasedValue.perLevel(1.5F, 0.5F),
						LevelBasedValue.constant(3.0F),
						LevelBasedValue.constant(3.0F)
					),
					LootItemEntityPropertyCondition.hasProperties(
							LootContext.EntityTarget.THIS,
							EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS))
						)
						.and(DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true)))
				)
		);

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
					new AddValue(LevelBasedValue.perLevel(-1.0F)),
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
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.elemental_deviation"),
						Attributes.BURNING_TIME,
						LevelBasedValue.perLevel(-0.15F),
						AttributeModifier.Operation.ADD_MULTIPLIED_BASE
					)
				)
				.withEffect(
					EnchantmentEffectComponents.ATTRIBUTES,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.elemental_deviation"),
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

		register(context, DENIAL, new Enchantment.Builder(Enchantment.definition(
			items.getOrThrow(ItemTags.VANISHING_ENCHANTABLE),
			1,
			1,
			Enchantment.constantCost(25),
			Enchantment.constantCost(50),
			8,
			EquipmentSlotGroup.ANY
		))
			.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.VANISHING_CURSE)))
			.withEffect(EnchantmentEffectComponents.TICK, new TakeRevengeOnImbecileEffect()));


		EntityPredicate.Builder entitypredicate$builder = EntityPredicate.Builder.entity()
			.periodicTick(5)
			.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false).setOnGround(true))
			.moving(MovementPredicate.horizontalSpeed(MinMaxBounds.Doubles.atLeast(1.0E-5F)))
			.movementAffectedBy(
				LocationPredicate.Builder.location()
					.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
			);
		register(context, CACKLING_CRAZE, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
				1,
				3,
				Enchantment.dynamicCost(10, 10),
				Enchantment.dynamicCost(25, 10),
				8,
				EquipmentSlotGroup.FEET
			))
				//TODO get this to work while standing in Spreading Remains
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.SOUL_SPEED)))
				.withEffect(
					EnchantmentEffectComponents.LOCATION_CHANGED,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.cackling_craze_speed"),
						Attributes.MOVEMENT_SPEED,
						LevelBasedValue.perLevel(0.0205F, 0.0055F),
						AttributeModifier.Operation.ADD_VALUE
					),
					AllOfCondition.allOf(
						InvertedLootItemCondition.invert(
							LootItemEntityPropertyCondition.hasProperties(
								LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity())
							)
						),
						AnyOfCondition.anyOf(
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentActiveCheck(),
								LootItemEntityPropertyCondition.hasProperties(
									LootContext.EntityTarget.THIS,
									EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
								),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.ANY)
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(false)).build()
									)
								)
							),
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentInactiveCheck(),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									)
								)
							)
						)
					)
				)
				.withEffect(
					EnchantmentEffectComponents.LOCATION_CHANGED,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.cackling_craze_attack"),
						Attributes.ATTACK_DAMAGE,
						LevelBasedValue.perLevel(1F),
						AttributeModifier.Operation.ADD_VALUE
					),
					AllOfCondition.allOf(
						InvertedLootItemCondition.invert(
							LootItemEntityPropertyCondition.hasProperties(
								LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity())
							)
						),
						AnyOfCondition.anyOf(
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentActiveCheck(),
								LootItemEntityPropertyCondition.hasProperties(
									LootContext.EntityTarget.THIS,
									EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
								),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.ANY)
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(false)).build()
									)
								)
							),
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentInactiveCheck(),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									)
								)
							)
						)
					)
				)
				.withEffect(
					EnchantmentEffectComponents.LOCATION_CHANGED,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.cackling_craze_health"),
						Attributes.MAX_HEALTH,
						LevelBasedValue.perLevel(-1F),
						AttributeModifier.Operation.ADD_VALUE
					),
					AllOfCondition.allOf(
						InvertedLootItemCondition.invert(
							LootItemEntityPropertyCondition.hasProperties(
								LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity())
							)
						),
						AnyOfCondition.anyOf(
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentActiveCheck(),
								LootItemEntityPropertyCondition.hasProperties(
									LootContext.EntityTarget.THIS,
									EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
								),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.ANY)
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(false)).build()
									)
								)
							),
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentInactiveCheck(),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									)
								)
							)
						)
					)
				)
				.withEffect(
					EnchantmentEffectComponents.LOCATION_CHANGED,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.cackling_craze_kb_res"),
						Attributes.KNOCKBACK_RESISTANCE,
						LevelBasedValue.perLevel(0.1F),
						AttributeModifier.Operation.ADD_VALUE
					),
					AllOfCondition.allOf(
						InvertedLootItemCondition.invert(
							LootItemEntityPropertyCondition.hasProperties(
								LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity())
							)
						),
						AnyOfCondition.anyOf(
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentActiveCheck(),
								LootItemEntityPropertyCondition.hasProperties(
									LootContext.EntityTarget.THIS,
									EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
								),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.ANY)
													.setBlock(
														net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS)
													)
											)
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(false)).build()
									)
								)
							),
							AllOfCondition.allOf(
								EnchantmentActiveCheck.enchantmentInactiveCheck(),
								AnyOfCondition.anyOf(
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									),
									LootItemEntityPropertyCondition.hasProperties(
										LootContext.EntityTarget.THIS,
										EntityPredicate.Builder.entity()
											.movementAffectedBy(
												LocationPredicate.Builder.location()
													.setBlock(net.minecraft.advancements.critereon.BlockPredicate.Builder.block().of(RisusTags.Blocks.REMAINS))
											)
											.flags(EntityFlagsPredicate.Builder.flags().setIsFlying(false))
									)
								)
							)
						)
					)
				)
				.withEffect(
					EnchantmentEffectComponents.TICK,
					new SpawnParticlesEffect(
						RisusParticles.RISUS_SOUL_PARTICLE.get(),
						SpawnParticlesEffect.inBoundingBox(),
						SpawnParticlesEffect.offsetFromEntityPosition(0.1F),
						SpawnParticlesEffect.movementScaled(-0.2F),
						SpawnParticlesEffect.fixedVelocity(ConstantFloat.of(0.1F)),
						ConstantFloat.of(1.0F)
					),
					LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, entitypredicate$builder)
				)
				.withEffect(
					EnchantmentEffectComponents.TICK,
					new PlaySoundEffect(RisusSoundEvents.LAUGHTER_ECHOES, ConstantFloat.of(0.6F), UniformFloat.of(0.6F, 1.0F)),
					AllOfCondition.allOf(
						LootItemRandomChanceCondition.randomChance(0.15F),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, entitypredicate$builder)
					)
				)
		);

		register(context, AGONY, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
				items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
				1,
				3,
				Enchantment.dynamicCost(10, 20),
				Enchantment.dynamicCost(60, 20),
				8,
				EquipmentSlotGroup.ANY
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.THORNS)))
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.VICTIM,
					EnchantmentTarget.ATTACKER,
					AllOf.entityEffects(
						new ConferAgonyEffect()
					),
					LootItemRandomChanceCondition.randomChance(EnchantmentLevelProvider.forEnchantmentLevel(LevelBasedValue.perLevel(0.10F)))
				)
		);

		register(context, PERPETUITY, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.EQUIPPABLE_ENCHANTABLE),
				1,
				1,
				Enchantment.constantCost(25),
				Enchantment.constantCost(50),
				8,
				EquipmentSlotGroup.ARMOR
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.BINDING_CURSE), enchantments.getOrThrow(Enchantments.VANISHING_CURSE)))
				.withEffect(EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)
		);

		register(context, GENOCIDE, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
				2,
				3,
				Enchantment.dynamicCost(5, 9),
				Enchantment.dynamicCost(20, 9),
				4,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.SWEEPING_EDGE)))
		);

		register(context, EMPYREAN_CONDUIT, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
				1,
				1,
				Enchantment.constantCost(25),
				Enchantment.constantCost(50),
				8,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.CHANNELING)))
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					AllOf.entityEffects(
						new DoubleSummonEntityEffect(HolderSet.direct(EntityType.LIGHTNING_BOLT.builtInRegistryHolder()), false),
						new PlaySoundEffect(SoundEvents.TRIDENT_THUNDER, ConstantFloat.of(5.0F), ConstantFloat.of(1.0F))
					),
					AllOfCondition.allOf(
						LootItemEntityPropertyCondition.hasProperties(
							LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setCanSeeSky(true))
						),
						LootItemEntityPropertyCondition.hasProperties(
							LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setCanSeeSky(true))
						),
						LootItemEntityPropertyCondition.hasProperties(
							LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(EntityType.TRIDENT)
						)
					)
				)
				.withEffect(
					EnchantmentEffectComponents.HIT_BLOCK,
					AllOf.entityEffects(
						new DoubleSummonEntityEffect(HolderSet.direct(EntityType.LIGHTNING_BOLT.builtInRegistryHolder()), false),
						new PlaySoundEffect(SoundEvents.TRIDENT_THUNDER, ConstantFloat.of(5.0F), ConstantFloat.of(1.0F))
					),
					AllOfCondition.allOf(
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.TRIDENT)),
						LocationCheck.checkLocation(LocationPredicate.Builder.location().setCanSeeSky(true)),
						LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.LIGHTNING_ROD)
					)
				)
		);

		register(context, BATTERING, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
				10,
				4,
				Enchantment.dynamicCost(1, 10),
				Enchantment.constantCost(50),
				1,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.CROSSBOW_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					AllOf.entityEffects(
						new BatteringEffect(LevelBasedValue.perLevel(1.0F))
					),
					LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(EntityTypeTags.ARROWS).build()
					)
				)
				.withEffect(
					EnchantmentEffectComponents.KNOCKBACK,
					new AddValue(LevelBasedValue.perLevel(0.5F)),
					LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(EntityTypeTags.ARROWS).build()
					)
				)
		);

		register(context, STAR_RELEASE, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
				2,
				1,
				Enchantment.constantCost(20),
				Enchantment.constantCost(50),
				4,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.CROSSBOW_EXCLUSIVE))
				.withEffect(EnchantmentEffectComponents.PROJECTILE_COUNT, new AddValue(LevelBasedValue.perLevel(7.0F)))
				.withEffect(EnchantmentEffectComponents.PROJECTILE_SPREAD, new AddValue(LevelBasedValue.perLevel(157.5F)))
				.withEffect(EnchantmentEffectComponents.PROJECTILE_SPAWNED, new SummonStarParticlesEffect())
		);

		register(context, OVERLOAD, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
				10,
				5,
				Enchantment.dynamicCost(1, 10),
				Enchantment.dynamicCost(51, 10),
				1,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.EFFICIENCY)))
				.withEffect(
					EnchantmentEffectComponents.ATTRIBUTES,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.overload"),
						Attributes.MINING_EFFICIENCY,
						new LevelBasedValue.Linear(15, 15),
						AttributeModifier.Operation.ADD_VALUE
					)
				)
		);

		register(context, MARITIME_SNARE, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
				2,
				3,
				Enchantment.dynamicCost(15, 9),
				Enchantment.dynamicCost(65, 9),
				4,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.LUCK_OF_THE_SEA)))
		);

		register(context, GRAVITY_WELL, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
				2,
				3,
				Enchantment.dynamicCost(15, 9),
				Enchantment.dynamicCost(65, 9),
				4,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.LURE)))
				.withEffect(EnchantmentEffectComponents.FISHING_TIME_REDUCTION, new AddValue(LevelBasedValue.perLevel(7.5F)))
		);

		register(context, RELOCATION, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
				5,
				3,
				Enchantment.dynamicCost(5, 8),
				Enchantment.dynamicCost(55, 8),
				2,
				EquipmentSlotGroup.ANY
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.UNBREAKING)))
		);

		register(context, PYROMANIAC, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
				2,
				3,
				Enchantment.dynamicCost(10, 10),
				Enchantment.dynamicCost(25, 10),
				4,
				EquipmentSlotGroup.FEET
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOOTS_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.TICK,
					new FierySpeedEffect()
				)
		);

		register(context, AVARICIOUS_AMBIT, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
				1,
				1,
				Enchantment.constantCost(15),
				Enchantment.constantCost(65),
				8,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.ATTRIBUTES,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.avaricious_ambit"),
						Attributes.BLOCK_INTERACTION_RANGE,
						LevelBasedValue.perLevel(3F),
						AttributeModifier.Operation.ADD_VALUE
					)
				)
				.withEffect(
				EnchantmentEffectComponents.ATTRIBUTES,
				new EnchantmentAttributeEffect(
					ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.avaricious_ambit"),
					Attributes.ENTITY_INTERACTION_RANGE,
					LevelBasedValue.perLevel(1F),
					AttributeModifier.Operation.ADD_VALUE
					)
				)
				.withEffect(
					EnchantmentEffectComponents.ATTRIBUTES,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.avaricious_ambit"),
						Attributes.ATTACK_SPEED,
						LevelBasedValue.perLevel(-0.5F),
						AttributeModifier.Operation.ADD_VALUE
					)

				)
		);

		register(context, PRESERVATION, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
				1,
				1,
				Enchantment.constantCost(20),
				Enchantment.constantCost(50),
				8,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOW_EXCLUSIVE))
				.withEffect(EnchantmentEffectComponents.PROJECTILE_SPAWNED, new ReduceAmmoEffect())
		);

		register(context, VIGOR, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
				10,
				4,
				Enchantment.dynamicCost(1, 11),
				Enchantment.dynamicCost(12, 11),
				1,
				EquipmentSlotGroup.ARMOR
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.ARMOR_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.ATTRIBUTES,
					new EnchantmentAttributeEffect(
						ResourceLocation.fromNamespaceAndPath(Risus.MODID, "execration.vigor"),
						Attributes.MAX_HEALTH,
						LevelBasedValue.perLevel(1F),
						AttributeModifier.Operation.ADD_VALUE
					)
				)
		);

		register(context, XENOPHOBIA, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
				items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
				10,
				5,
				Enchantment.dynamicCost(1, 11),
				Enchantment.dynamicCost(21, 11),
				1,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.DAMAGE,
					new AddValue(LevelBasedValue.perLevel(2F)),
					InvertedLootItemCondition.invert(
						LootItemEntityPropertyCondition.hasProperties(
							LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(RisusTags.Entities.SENSITIVE_TO_HUNTERS))
						)
					)
				)
		);

		register(context, DEFIANCE, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
				5,
				3,
				Enchantment.dynamicCost(12, 7),
				Enchantment.constantCost(50),
				2,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.LOYALTY)))
				.withEffect(
					EnchantmentEffectComponents.DAMAGE,
					new AddValue(LevelBasedValue.perLevel(2F)),
					LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(RisusTags.Entities.TRIDENT_LIKE_PROJECTILES).build()
					)
				)
		);

		register(context, ERUPTION, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
				2,
				3,
				Enchantment.dynamicCost(17, 7),
				Enchantment.constantCost(50),
				4,
				EquipmentSlotGroup.HAND
			))
				.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.RIPTIDE_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					AllOf.entityEffects(
						new EruptEffect(LevelBasedValue.perLevel(3.0F))
					),
					AllOfCondition.allOf(
						LootItemEntityPropertyCondition.hasProperties(
							LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().of(RisusTags.Entities.TRIDENT_LIKE_PROJECTILES).build()
						)
					)
				)
				.withEffect(
					EnchantmentEffectComponents.HIT_BLOCK,
					AllOf.entityEffects(
						new EruptEffect(LevelBasedValue.perLevel(3.0F))
					),
					AllOfCondition.allOf(
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(RisusTags.Entities.TRIDENT_LIKE_PROJECTILES))
					)
				)
		);

		register(context, SOAR, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
				5,
				2,
				Enchantment.dynamicCost(5, 20),
				Enchantment.dynamicCost(55, 20),
				2,
				EquipmentSlotGroup.MAINHAND
			))
				.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.KNOCKBACK)))
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new SoarEffect(LevelBasedValue.perLevel(1.0F)),
					DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true))
				)
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new ApplyMobEffect(
						HolderSet.direct(MobEffects.SLOW_FALLING),
						LevelBasedValue.constant(2F),
						LevelBasedValue.perLevel(2F),
						LevelBasedValue.constant(1.0F),
						LevelBasedValue.constant(1.0F)
					),
					DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true))
				)
		);

		register(context, FERVOUR, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.FIRE_ASPECT_ENCHANTABLE),
				items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
			2,
			2,
			Enchantment.dynamicCost(10, 20),
			Enchantment.dynamicCost(60, 20),
			4,
			EquipmentSlotGroup.MAINHAND
			))
			.exclusiveWith(HolderSet.direct(enchantments.getOrThrow(Enchantments.FIRE_ASPECT)))
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					AllOf.entityEffects(
						new FervourEffect(LevelBasedValue.perLevel(3.0F))
					),
					DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true))
				)

		);

		register(context, PROLIFERATION, new Enchantment.Builder(Enchantment.definition(
				items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
			2,
			2,
			Enchantment.dynamicCost(10, 10),
			Enchantment.dynamicCost(25, 10),
			4,
			EquipmentSlotGroup.FEET
			))
			.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOOTS_EXCLUSIVE))
			.withEffect(
				EnchantmentEffectComponents.DAMAGE_IMMUNITY,
				DamageImmunity.INSTANCE,
				DamageSourceCondition.hasDamageSource(
					DamageSourcePredicate.Builder.damageType()
						.tag(TagPredicate.is(DamageTypeTags.BURN_FROM_STEPPING))
						.tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
				)
			)
			.withEffect(
				EnchantmentEffectComponents.LOCATION_CHANGED,
				new ReplaceDisk(
					new LevelBasedValue.Clamped(LevelBasedValue.perLevel(3.0F, 1.0F), 0.0F, 16.0F),
					LevelBasedValue.constant(1.0F),
					new Vec3i(0, -1, 0),
					Optional.of(
						net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.allOf(
							net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.unobstructed(new Vec3i(0, 1, 0)),
							net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.matchesTag(RisusTags.Blocks.PROLIFERABLE_SOILS),
							BlockPredicate.alwaysTrue()
						)
					),
					BlockStateProvider.simple(RisusBlocks.ASHEN_REMAINS.get()),
					Optional.of(GameEvent.BLOCK_PLACE)
				),
				LootItemEntityPropertyCondition.hasProperties(
					LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(true).setCrouching(true))
				)
			)
			.withEffect(
				EnchantmentEffectComponents.LOCATION_CHANGED,
				new ReplaceDisk(
					new LevelBasedValue.Clamped(LevelBasedValue.perLevel(3.0F, 1.0F), 0.0F, 16.0F),
					LevelBasedValue.constant(1.0F),
					new Vec3i(0, -1, 0),
					Optional.of(
						net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.allOf(
							net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.unobstructed(new Vec3i(0, 1, 0)),
							net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.matchesTag(RisusTags.Blocks.PROLIFERABLE_ROCKS),
							BlockPredicate.alwaysTrue()
						)
					),
					BlockStateProvider.simple(RisusBlocks.SMILING_REMAINS.get()),
					Optional.of(GameEvent.BLOCK_PLACE)
				),
				LootItemEntityPropertyCondition.hasProperties(
					LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(true).setCrouching(true))
				)
			)

		);
	}

	private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
		context.register(key, builder.build(key.location()));
	}
}
