package com.bigdious.risus.event;

import com.bigdious.risus.Risus;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.entity.Stool;
import com.bigdious.risus.init.*;
import com.bigdious.risus.items.utility.EternalYouthItem;
import com.bigdious.risus.network.UnyieldingTotemPacket;
import com.bigdious.risus.util.ServerParticleUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.event.entity.EntityMountEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosCapability;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ItemEffectEvents {
	public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR;

	public static void explodeStick(LivingIncomingDamageEvent event) {
		Entity entity = event.getSource().getEntity();
		if (entity instanceof LivingEntity attacker && event.getSource().getWeaponItem() != null) {
			if (event.getSource().getWeaponItem().is(RisusItems.BOOMSTICK.get())) {
				ItemStack boomstick = attacker.getMainHandItem();
				int powerRadius = boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.POWER)) / 2;
				if (boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.WIND_BURST)) > 0) {
					int burstRadius = boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.WIND_BURST));
					windBurstExplode(attacker.level(), attacker.getX(), attacker.getY(), attacker.getZ(), burstRadius + powerRadius, boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.FLAME)) > 0);
					if (boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.MULTISHOT)) > 0) {
						windBurstExplode(attacker.level(), attacker.getRandomX(10), attacker.getY(), attacker.getRandomZ(10), burstRadius + powerRadius, boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.FLAME)) > 0);
						windBurstExplode(attacker.level(), attacker.getRandomX(10), attacker.getY(), attacker.getRandomZ(10), burstRadius + powerRadius, boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.FLAME)) > 0);
					}
				} else {
					//we explode stick in the attacker's crotch, this way the victim can use shield to defend
					explode(attacker.level(), attacker.getX(), attacker.getY(), attacker.getZ(), powerRadius, attacker, boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.FLAME)) > 0);
					if (boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.MULTISHOT)) > 0) {
						explode(attacker.level(), attacker.getRandomX(10), attacker.getY(), attacker.getRandomZ(10), powerRadius, attacker, boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.FLAME)) > 0);
						explode(attacker.level(), attacker.getRandomX(10), attacker.getY(), attacker.getRandomZ(10), powerRadius, attacker, boomstick.getEnchantmentLevel(attacker.level().registryAccess().holderOrThrow(Enchantments.FLAME)) > 0);
					}
				}
				attacker.getMainHandItem().hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
			}
		}
	}

	public static void explode(Level level, double x, double y, double z, int radius, LivingEntity entity, Boolean isFiery) {
		level.explode(null, level.damageSources().explosion(entity, null), null, x, y, z, radius+3F, isFiery, Level.ExplosionInteraction.BLOCK);
	}
	static {
		EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(true, false, Optional.empty(), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));
	}
	public static void windBurstExplode(Level level, double x, double y, double z, int radius ,Boolean isFiery) {
		level.explode(null, null, EXPLOSION_DAMAGE_CALCULATOR, x, y, z, radius+3, isFiery, Level.ExplosionInteraction.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.BREEZE_WIND_CHARGE_BURST);
	}

	//do not touch below scythe events. It's stupid, but they need to stay as is
	public static void fireScythe(LivingDamageEvent.Post event) {
		Entity entity = event.getSource().getEntity();
		LivingEntity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker &&  attacker.getMainHandItem().is(RisusItems.FIRE_SCYTHE.get())) {
			entity2.addEffect(new MobEffectInstance(RisusMobEffects.FLAME_FRAILTY, 200, 0, false, false, true));
			entity2.igniteForSeconds(2);
		}
	}

	public static void cindergleeScythe(LivingDamageEvent.Post event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker && entity2 instanceof LivingEntity victim && attacker.getMainHandItem().is(RisusItems.CINDERGLEE_SCYTHE.get())) {
			victim.addEffect(new MobEffectInstance(RisusMobEffects.EXBURN, 600, 1, false, false, true));
		}
	}

	public static void soulScythe(LivingIncomingDamageEvent event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker && entity2 instanceof LivingEntity victim && attacker.getMainHandItem().is(RisusItems.SOUL_SCYTHE.get())) {
			if (!victim.getType().is(EntityTypePredicate.of(EntityTypeTags.SENSITIVE_TO_SMITE).types()) && !(victim.getType().is(RisusTags.Entities.OFFSPRING))) {
				event.setAmount(event.getAmount() + 7);
			} else {
				event.setAmount(event.getAmount() - 3);
			}
		}
	}

	public static void sacrificeAccepted(LivingDeathEvent event) {
		Entity sacrifice = event.getEntity();
		Entity murderer = event.getEntity().getKillCredit();
		if (sacrifice instanceof Player ||
			sacrifice instanceof AbstractVillager ||
			sacrifice instanceof AbstractIllager ||
			sacrifice instanceof Witch
		) {
			if (murderer instanceof Player cultist && cultist.getOffhandItem().is(RisusItems.SACRIFICE_CATALYST)) {

				if (murderer.level() instanceof ServerLevel serverLevel) {
					for (int i = 0; i < 5; i++) {
						serverLevel.sendParticles(RisusParticles.FALLING_JOY.get(), sacrifice.getRandomX(0.5), sacrifice.getRandomY(), sacrifice.getRandomZ(0.5), 1, 0, 0, 0, 0);
					}
				}
				if (cultist.getOffhandItem().getDamageValue() > 1) {
					cultist.getOffhandItem().setDamageValue(cultist.getOffhandItem().getDamageValue() - 1);
				} else {
					cultist.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(RisusItems.THOUSAND_BLADE.asItem()));
				}
			}
		}
	}

	public static void stoolDiesOnDeath(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player player && player.hasEffect(RisusMobEffects.GREATNESS)) {
			player.level().getEntities((Entity) null, new AABB(player.getOnPos()).inflate(1, 10, 1), entity2 -> entity2 instanceof Stool).forEach(entity2 -> {
				if (entity2 instanceof Stool stool && stool.getOwnerUUID() == player.getUUID()) {
					stool.kill();
				}

			});
		}
	}

	public static void getWaxedRisusStyle(PlayerInteractEvent.RightClickBlock event) {
		final Map<Block, Block> WAXING_MAP = Map.of(
			RisusBlocks.COPPER_AMALGAM.get(), RisusBlocks.WAXED_COPPER_AMALGAM.get(),
			RisusBlocks.EXPOSED_COPPER_AMALGAM.get(), RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WEATHERED_COPPER_AMALGAM.get(), RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.OXIDIZED_COPPER_AMALGAM.get(), RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get()
		);
		final Map<Block, Block> WAXOFF_MAP = Map.of(
			RisusBlocks.WAXED_COPPER_AMALGAM.get(), RisusBlocks.COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_EXPOSED_COPPER_AMALGAM.get(), RisusBlocks.EXPOSED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_WEATHERED_COPPER_AMALGAM.get(), RisusBlocks.WEATHERED_COPPER_AMALGAM.get(),
			RisusBlocks.WAXED_OXIDIZED_COPPER_AMALGAM.get(), RisusBlocks.OXIDIZED_COPPER_AMALGAM.get()
		);
		Block checkingForBlock = event.getLevel().getBlockState(event.getPos()).getBlock();
		BlockState state = event.getLevel().getBlockState(event.getPos());
		if (event.getItemStack().is(Items.HONEYCOMB) && event.getLevel().getBlockState(event.getPos()).is(RisusTags.Blocks.COPPER_AMALGAM_VARIATION)) {
			if (WAXING_MAP.containsKey(checkingForBlock)) {
				event.getLevel().setBlock(event.getPos(), WAXING_MAP.get(checkingForBlock).withPropertiesOf(state), 11);
			}
			ServerParticleUtils.spawnParticlesOnBlockFaces(event.getLevel(), event.getPos(), ParticleTypes.WAX_ON, UniformInt.of(6, 12));
			event.getItemStack().shrink(1);
			event.getEntity().awardStat(Stats.ITEM_USED.get(event.getItemStack().getItem()));
			event.getLevel().playSound(null, event.getPos(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
		}
		if (event.getItemStack().is(ItemTags.AXES) && event.getLevel().getBlockState(event.getPos()).is(RisusTags.Blocks.WAXED_COPPER_AMALGAM_VARIATION)) {
			if (WAXOFF_MAP.containsKey(checkingForBlock)) {
				event.getLevel().setBlock(event.getPos(), WAXOFF_MAP.get(checkingForBlock).withPropertiesOf(state), 11);
				ServerParticleUtils.spawnParticlesOnBlockFaces(event.getLevel(), event.getPos(), ParticleTypes.WAX_OFF, UniformInt.of(6, 12));
				event.getItemStack().hurtAndBreak(1, event.getEntity(), LivingEntity.getSlotForHand(event.getHand()));
				event.getEntity().awardStat(Stats.ITEM_USED.get(event.getItemStack().getItem()));
				event.getLevel().playSound(null, event.getPos(), SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
			}
		}
	}

	public static boolean curiosSearch(LivingEntity entity, Item item) {
		if (ModList.get().isLoaded("curios")) {
			var handler = entity.getCapability(CuriosCapability.INVENTORY);
			if (handler == null) return false;
			var s = handler.findCurios(item);
			if (s.isEmpty()) return false; else return true;
		}
		return false;
	}

	public static void totemOfUnyieldingActivate(@NotNull LivingDeathEvent event) {
		//checking hands and curios slot to trigger totem of unyielding
		LivingEntity dyingEntity = event.getEntity();
		Level level = dyingEntity.level();
		//thanks for the curios check to Tom's Simple Storage Mod
		if (!level.isClientSide()) {
			for (InteractionHand interactionhand : InteractionHand.values()) {
				ItemStack stack = dyingEntity.getItemInHand(interactionhand);
				//this is just barely stupid enough to work
				if (stack.is(RisusItems.TOTEM_OF_UNYIELDING) || curiosSearch(dyingEntity, RisusItems.TOTEM_OF_UNYIELDING.get())) {
					dyingEntity.setHealth(1.0F);
					dyingEntity.removeAllEffects();
					dyingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 4, false, false));
					dyingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 2, false, false));
					dyingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1, false, false));
					dyingEntity.addEffect(new MobEffectInstance(RisusMobEffects.DESTINED_DEATH, 200, 0, false, false, true));
					level.playSound(null, dyingEntity.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.NEUTRAL);
					if (curiosSearch(dyingEntity, RisusItems.TOTEM_OF_UNYIELDING.get())) {
						var handler = dyingEntity.getCapability(CuriosCapability.INVENTORY);
						var s = handler.findCurios(RisusItems.TOTEM_OF_UNYIELDING.get());
						s.get(0).stack().shrink(1);
					} else stack.shrink(1);
					if (dyingEntity instanceof ServerPlayer player) {
						PacketDistributor.sendToPlayer(player, new UnyieldingTotemPacket(RisusItems.TOTEM_OF_UNYIELDING.toStack()));
					}
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void luckyCharmMiracle(@NotNull LivingDeathEvent event) {
		LivingEntity dyingEntity = event.getEntity();
		Level level = dyingEntity.level();
		if (!level.isClientSide()) {
			for (InteractionHand interactionhand : InteractionHand.values()) {
				ItemStack stack = dyingEntity.getItemInHand(interactionhand);
				if ((stack.is(RisusItems.LUCKY_CHARM) || curiosSearch(dyingEntity, RisusItems.LUCKY_CHARM.get())) && !event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY) && dyingEntity.getAttributes().getInstance(Attributes.LUCK) != null) {
					if (level.random.nextInt(20 - (dyingEntity.getAttribute(Attributes.LUCK).getValue() > 18 ? 19 : (int) dyingEntity.getAttribute(Attributes.LUCK).getValue())) < 2) {
						dyingEntity.setHealth(1.0F);
						level.playSound(null, dyingEntity.blockPosition(), RisusSoundEvents.FORTUNE_TRIGGERED.get(), SoundSource.PLAYERS);
						if (curiosSearch(dyingEntity, RisusItems.LUCKY_CHARM.get())) {
							var handler = dyingEntity.getCapability(CuriosCapability.INVENTORY);
							var s = handler.findCurios(RisusItems.LUCKY_CHARM.get());
							s.get(0).stack().hurtAndBreak(1, dyingEntity, s.get(0).stack().getEquipmentSlot());
						} else stack.hurtAndBreak(1, dyingEntity, stack.getEquipmentSlot());
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}

	public static void luckyCharmBenefit(LivingIncomingDamageEvent event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		Level level = entity2.level();
		if (entity instanceof Player attacker && entity2 instanceof LivingEntity victim) {
			for (InteractionHand interactionhand : InteractionHand.values()) {
				ItemStack stack = attacker.getItemInHand(interactionhand);
				if ((stack.is(RisusItems.LUCKY_CHARM) || curiosSearch(attacker, RisusItems.LUCKY_CHARM.get())) && attacker.getAttributes().getInstance(Attributes.LUCK) != null) {
					switch (level.random.nextInt(20 - (attacker.getAttribute(Attributes.LUCK).getValue() > 18 ? 19 : (int) attacker.getAttribute(Attributes.LUCK).getValue()))) {
						case 1 -> {
							if (attacker.getHealth() < attacker.getMaxHealth()) {
								attacker.heal(1);
								level.playSound(null, attacker.blockPosition(), RisusSoundEvents.FORTUNE_TRIGGERED.get(), SoundSource.PLAYERS);
								if (curiosSearch(attacker, RisusItems.LUCKY_CHARM.get())) {
									var handler = attacker.getCapability(CuriosCapability.INVENTORY);
									var s = handler.findCurios(RisusItems.LUCKY_CHARM.get());
									s.get(0).stack().hurtAndBreak(1, attacker, s.get(0).stack().getEquipmentSlot());
								} else stack.hurtAndBreak(1, attacker, stack.getEquipmentSlot());
							}
						}
						case 0 -> {
							if (!victim.isDeadOrDying()) {
								event.setAmount(event.getAmount()*2);
								level.playSound(null, attacker.blockPosition(), RisusSoundEvents.FORTUNE_TRIGGERED.get(), SoundSource.PLAYERS);
								if (curiosSearch(attacker, RisusItems.LUCKY_CHARM.get())) {
									var handler = attacker.getCapability(CuriosCapability.INVENTORY);
									var s = handler.findCurios(RisusItems.LUCKY_CHARM.get());
									s.get(0).stack().hurtAndBreak(1, attacker, s.get(0).stack().getEquipmentSlot());
								} else stack.hurtAndBreak(1, attacker, stack.getEquipmentSlot());
							}
						}
						default -> {
							return;
						}
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void wretchedCharmDeath(@NotNull LivingDeathEvent event) {
		LivingEntity dyingEntity = event.getEntity();
		Level level = dyingEntity.level();
		if (!level.isClientSide()) {
			for (InteractionHand interactionhand : InteractionHand.values()) {
				ItemStack stack = dyingEntity.getItemInHand(interactionhand);
				if ((stack.is(RisusItems.WRETCHED_CHARM) || curiosSearch(dyingEntity, RisusItems.WRETCHED_CHARM.get())) && dyingEntity.getAttributes().getInstance(Attributes.LUCK) != null) {
					if (level.random.nextInt(20 + (dyingEntity.getAttribute(Attributes.LUCK).getValue() < -18 ? -19 : (int) dyingEntity.getAttribute(Attributes.LUCK).getValue())) < 2) {
						level.playSound(null, dyingEntity.blockPosition(), RisusSoundEvents.FORTUNE_TRIGGERED.get(), SoundSource.PLAYERS);
						if (curiosSearch(dyingEntity, RisusItems.WRETCHED_CHARM.get())) {
							var handler = dyingEntity.getCapability(CuriosCapability.INVENTORY);
							var s = handler.findCurios(RisusItems.WRETCHED_CHARM.get());
							s.get(0).stack().hurtAndBreak(1, dyingEntity, s.get(0).stack().getEquipmentSlot());
						} else stack.hurtAndBreak(1, dyingEntity, stack.getEquipmentSlot());
						level.explode(null, level.damageSources().explosion(dyingEntity, null), null, dyingEntity.getX(), dyingEntity.getY(), dyingEntity.getZ(), 5F, false, Level.ExplosionInteraction.BLOCK);
						return;
					}
				}
			}
		}
	}

	public static void wretchedCharmMisfortune(LivingIncomingDamageEvent event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		Level level = entity2.level();
		if (entity instanceof Player attacker && entity2 instanceof LivingEntity victim) {
			for (InteractionHand interactionhand : InteractionHand.values()) {
				ItemStack stack = attacker.getItemInHand(interactionhand);
				if ((stack.is(RisusItems.WRETCHED_CHARM) || curiosSearch(attacker, RisusItems.WRETCHED_CHARM.get())) && attacker.getAttributes().getInstance(Attributes.LUCK) != null && !victim.isDeadOrDying()) {
					boolean activated = false;
					switch (level.random.nextInt(100 + (attacker.getAttribute(Attributes.LUCK).getValue() > -18 ? 5 * (int) attacker.getAttribute(Attributes.LUCK).getValue() : -93))) {
						case 1 -> {
							LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
							lightning.setPos(victim.getX(), victim.getEyeY(), victim.getZ());
							level.addFreshEntity(lightning);
							activated = true;
						}
						case 2 -> {
							victim.igniteForTicks(400);
							activated = true;
						}
						case 3 -> {
							victim.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0));
							activated = true;
						}
						case 4 -> {
							victim.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
							activated = true;
						}
						case 5 -> {
							victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
							activated = true;
						}
						case 6 -> {
							victim.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
							activated = true;
						}
						case 7 -> {
							victim.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
							activated = true;
						}
						case 0 -> {
							victim.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
							activated = true;
						}
						default -> {
						}
					}
					if (activated) {
						if (curiosSearch(attacker, RisusItems.WRETCHED_CHARM.get())) {
							var handler = attacker.getCapability(CuriosCapability.INVENTORY);
							var s = handler.findCurios(RisusItems.WRETCHED_CHARM.get());
							s.get(0).stack().hurtAndBreak(1, attacker, s.get(0).stack().getEquipmentSlot());
						} else stack.hurtAndBreak(1, attacker, stack.getEquipmentSlot());
					}

				}
			}
		}
	}

	public static void hurtWings(PlayerInteractEvent.RightClickItem event) {
		if (event.getItemStack().is(RisusTags.Items.HURTS_ANGEL_WINGS) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(RisusItems.ANGEL_WINGS) && event.getEntity().isFallFlying()) {
			event.getEntity().getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(30, event.getEntity(), EquipmentSlot.CHEST);
		}
		if (event.getItemStack().is(RisusTags.Items.LIGHTLY_HURTS_ANGEL_WINGS) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(RisusItems.ANGEL_WINGS) && (event.getEntity().isFallFlying() || event.getEntity().isInWaterRainOrBubble() || event.getEntity().isInLava())) {
			event.getEntity().getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(10, event.getEntity(), EquipmentSlot.CHEST);
		}
	}

	public static void roseCrownBehavior(LivingIncomingDamageEvent event) {
		Entity attacker = event.getSource().getEntity();
		LivingEntity victim = event.getEntity();
		if (victim.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.ROSE_CROWN)) {
			victim.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120));
			if (attacker instanceof LivingEntity livingAttacker){
				livingAttacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 120));
			}
		};
	}

	public static void eternalizeTamables(PlayerInteractEvent.EntityInteract event){
		//copy of interaction from EternalYouthItem to handle tamed animals (order of events issue)
		if (event.getItemStack().is(RisusItems.ETERNAL_YOUTH)) {
			if (event.getTarget() instanceof TamableAnimal tamableAnimal && tamableAnimal.getOwner() == event.getEntity()) {
				boolean itemUsed = false;
				if (tamableAnimal.getAge() > -24000 && !tamableAnimal.getAttribute(Attributes.SCALE).hasModifier(Risus.prefix("eternal_youth_scale"))) {
					EternalYouthItem.youthEnable(event.getLevel(), event.getEntity(), tamableAnimal);
					itemUsed = true;
				} else if (tamableAnimal.getAge() < -24000) {
					itemUsed = true;
					EternalYouthItem.youthDisable(event.getLevel(), event.getEntity(), tamableAnimal);
				} else if ((tamableAnimal.getType().is(RisusTags.Entities.YOUTH_SHRINKS) || RisusConfig.everythingYouthable)  && tamableAnimal.getAttributes().getInstance(Attributes.SCALE) != null) {
					if (tamableAnimal.getAttribute(Attributes.SCALE).hasModifier(Risus.prefix("eternal_youth_scale"))) {
						EternalYouthItem.youthDisable(event.getLevel(), event.getEntity(), tamableAnimal);
					} else {
						EternalYouthItem.youthEnable(event.getLevel(), event.getEntity(), tamableAnimal);
					}
					itemUsed = true;
				}
				if (itemUsed) {
					event.getItemStack().shrink(1);
					event.getLevel().playSound(null, tamableAnimal.blockPosition(), RisusSoundEvents.ETERNAL_YOUTH_BREAK.get(), SoundSource.PLAYERS);
				}
			}
		}
	}

	public static void updateVisibility (LivingEvent.LivingVisibilityEvent event) {
		if (event.getEntity() instanceof Player player && player.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.SINNER_ROBES_HELMET)) {
			String ability = player.getItemBySlot(EquipmentSlot.HEAD).get(RisusDataComponents.ABILITY_VARIANT);
			Entity lookingEntity = event.getLookingEntity();
			if (ability != null) {
				if (ability.equals("skeleton") && lookingEntity.getType() == EntityType.SKELETON ||
					ability.equals("creeper") && lookingEntity.getType() == EntityType.CREEPER ||
					ability.equals("zombie") && lookingEntity.getType() == EntityType.ZOMBIE ||
					ability.equals("wither_skeleton") && lookingEntity.getType() == EntityType.WITHER_SKELETON ||
					ability.equals("piglin") && lookingEntity.getType() == EntityType.PIGLIN ||
					(
						ability.equals("tuxedo_cat") ||
							ability.equals("black_cat") ||
							ability.equals("british_cat") ||
							ability.equals("calico_cat") ||
							ability.equals("jellie_cat") ||
							ability.equals("persian_cat") ||
							ability.equals("ragdoll_cat") ||
							ability.equals("orange_cat") ||
							ability.equals("siamese_cat") ||
							ability.equals("tabby_cat") ||
							ability.equals("white_cat")
					) && lookingEntity.getType() == EntityType.PHANTOM
				) {
					event.modifyVisibility(0.2);
				} else if
				((
						ability.equals("pale_wolf") ||
							ability.equals("ashen_wolf") ||
							ability.equals("black_wolf") ||
							ability.equals("chestnut_wolf") ||
							ability.equals("rusty_wolf") ||
							ability.equals("spotted_wolf") ||
							ability.equals("snowy_wolf") ||
							ability.equals("striped_wolf") ||
							ability.equals("woods_wolf"))
						&& lookingEntity.getType() == EntityType.SKELETON) {
					event.modifyVisibility(0.5);
				} else if ((ability.equals("smile") || (ability.equals("abyssal_eye"))) && lookingEntity.getType().is(RisusTags.Entities.OFFSPRING)) {
					event.modifyVisibility(0.6);
				}
			}

		}

	}

	public static void increaseItemPickupRange(PlayerTickEvent.Pre event) {
		Player player = event.getEntity();
		if (player.getHealth() > 0.0F && !player.isSpectator() && player.getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.CHEST).get(RisusDataComponents.ABILITY_VARIANT).equals("guts") ) {
			AABB aabb;
			if (player.isPassenger() && !player.getVehicle().isRemoved()) {
				aabb = player.getBoundingBox().minmax(player.getVehicle().getBoundingBox()).inflate(4.0F, 0.0F, 4.0F);
			} else {
				aabb = player.getBoundingBox().inflate(4.0F, 0.5F, 4.0F);
			}

			List<Entity> list = player.level().getEntities(player, aabb);
			List<Entity> list1 = Lists.newArrayList();

			for(Entity entity : list) {
				if (entity.getType() == EntityType.EXPERIENCE_ORB) {
					list1.add(entity);
				} else if (!entity.isRemoved()) {
					entity.playerTouch(player);
				}
			}

			if (!list1.isEmpty()) {
				(Util.getRandom(list1, player.getRandom())).playerTouch(player);
			}
		}
	}

	public static void shadowWalk(PlayerTickEvent.Pre event){
		Player player = event.getEntity();
		Level level = player.level();

		if (player.level() instanceof ServerLevel serverlevel1) {
			BlockPos blockpos1 = player.blockPosition();
			ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
			if (!Objects.equal(player.lastPos, blockpos1)) {
				if (stack.get(RisusDataComponents.ABILITY_VARIANT) != null && stack.get(RisusDataComponents.ABILITY_VARIANT).equals("shadow_walker")) {
					BlockState blockstate = RisusBlocks.FADING_SHADOW.get().defaultBlockState();
					int i = 2;
					BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
					//if the logic gives you a headache, you are not alone
					for(BlockPos blockpos : BlockPos.betweenClosed(blockpos1.offset(-i, -1, -i), blockpos1.offset(i, -1, i))) {
						if ((!level.canSeeSky(blockpos) || ((level.canSeeSky(blockpos) && !level.isDay()))) && level.getBrightness(LightLayer.BLOCK, blockpos) < 1) {
							if (blockpos.closerToCenterThan(player.position(), i)) {
								blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
								BlockState blockstate1 = level.getBlockState(blockpos$mutableblockpos);
								if (blockstate1.isAir()) {
									BlockState blockstate2 = level.getBlockState(blockpos);
									if (blockstate2.isAir() && blockstate.canSurvive(level, blockpos) && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty())) {
										level.setBlockAndUpdate(blockpos, blockstate);
										level.scheduleTick(blockpos, RisusBlocks.FADING_SHADOW.get(), Mth.nextInt(player.getRandom(), 60, 120));
									}
								}
							}
						}
					}

				}
			}
		}
	}



}
