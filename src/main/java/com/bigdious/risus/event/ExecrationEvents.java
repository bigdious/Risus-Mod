package com.bigdious.risus.event;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.*;
import com.bigdious.risus.util.RisusItemStackUtil;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.SweepAttackEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

import java.util.*;

public class ExecrationEvents {

	public static final String SAVED_INV_TAG = "RisusSavedInventory";

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void staysUponDeath(LivingDeathEvent event) {
		LivingEntity living = event.getEntity();
		ListTag tagList = new ListTag();
		if (event.isCanceled() || living.level().isClientSide() || !(living instanceof Player player) || living instanceof FakePlayer || player.isCreative() || player.isSpectator() || living.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
			return;
		}
		Inventory keepInventory = new Inventory(player);

		for (int i = 0; i < player.getInventory().armor.size(); i++) {
			ItemStack armor = player.getInventory().armor.get(i);
			if (armor.has(DataComponents.ENCHANTMENTS) && armor.get(DataComponents.ENCHANTMENTS).getLevel(living.level().registryAccess().holderOrThrow(Execrations.PERPETUITY)) > 0 && armor.isDamageableItem() && !armor.is(RisusTags.Items.PERPETUITY_BLACKLIST)) {
				keepInventory.armor.set(i, armor.copy());
				player.getInventory().armor.set(i, ItemStack.EMPTY);
			}
		}

		if (!keepInventory.isEmpty()) {
			keepInventory.save(tagList);
			getPlayerData(player).put(SAVED_INV_TAG, tagList);
		}
	}

	public static CompoundTag getPlayerData(Player player) {
		if (!player.getPersistentData().contains(Player.PERSISTED_NBT_TAG)) {
			player.getPersistentData().put(Player.PERSISTED_NBT_TAG, new CompoundTag());
		}
		return player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
	}

	public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
		if (!event.isEndConquered()) {
			returnStoredItems(serverPlayer);
		}
	}

	private static void returnStoredItems(Player player) {
		Risus.LOGGER.debug("Player {} ({}) respawned and received items held in storage", player.getName().getString(), player.getUUID());
		CompoundTag playerData = getPlayerData(player);
		if (!player.level().isClientSide() && playerData.contains(SAVED_INV_TAG)) {
			ListTag tagList = playerData.getList(SAVED_INV_TAG, 10);
			RisusItemStackUtil.loadNoClear(player.registryAccess(), tagList, player.getInventory());
			getPlayerData(player).getList(SAVED_INV_TAG, 10).clear();
			getPlayerData(player).remove(SAVED_INV_TAG);
		}
	}

	public static void genocideSweep(SweepAttackEvent event) {
		Player player = event.getEntity();
		Entity victim = event.getTarget();
		ItemStack stack = event.getEntity().getWeaponItem();
		if (stack.has(DataComponents.ENCHANTMENTS) && stack.get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.GENOCIDE)) > 0) {
			//mostly copy from Player attack()
			float f = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
			DamageSource damagesource = player.damageSources().playerAttack(player);
			f += stack.getItem().getAttackDamageBonus(victim, f, damagesource);
			float f7 = 1.0F + (float) player.getAttributeValue(Attributes.SWEEPING_DAMAGE_RATIO) * f;
			int strength = stack.get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.GENOCIDE));
			for (LivingEntity livingentity2 : player.level()
				.getEntitiesOfClass(LivingEntity.class, victim.getBoundingBox().inflate(1.0 + strength, 0.25, 1.0 + strength))) {
				double entityReachSq = Mth.square(player.entityInteractionRange() + strength * 2);
				if (livingentity2 != player
					&& livingentity2 != victim
					&& !player.isAlliedTo(livingentity2)
					&& (!(livingentity2 instanceof ArmorStand) || !((ArmorStand) livingentity2).isMarker())
					&& player.distanceToSqr(livingentity2) < entityReachSq) {
					float f2 = player.getAttackStrengthScale(0.5F);
					float f5 = player.getEnchantedDamage(livingentity2, f7, damagesource) * f2;
					livingentity2.knockback(
						0.4F,
						Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)),
						(-Mth.cos(player.getYRot() * (float) (Math.PI / 180.0)))
					);
					livingentity2.hurt(damagesource, f5);
					if (player.level() instanceof ServerLevel serverlevel) {
						EnchantmentHelper.doPostAttackEffects(serverlevel, livingentity2, damagesource);
					}
					player.level()
						.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
					player.sweepAttack();
					event.setCanceled(true);
				}
			}
		}
	}

	public static void onOverload(BlockDropsEvent event) {
		if (event.getBreaker() instanceof Player player) {
			if (player.getWeaponItem().has(DataComponents.ENCHANTMENTS) && player.getWeaponItem().get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.OVERLOAD)) > 0) {
				int i = player.getWeaponItem().get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.OVERLOAD));
				if (player.level().getRandom().nextFloat() <= i * 0.15) {
					event.getDrops().clear();
					event.setDroppedExperience(0);
					ServerParticleUtils.spawnParticleInBlock(player.level(), event.getPos(), 6, RisusParticles.JOYFLAME.get());
					player.level().playSound(null, event.getPos(), SoundEvents.GENERIC_BURN, player.getSoundSource(), 0.1F, 1);
				}
			}
		}
	}

	public static void onMaritimeSnare(ItemFishedEvent event) {
		Player player = event.getEntity();
		if (player.getMainHandItem().has(DataComponents.ENCHANTMENTS) && player.getMainHandItem().get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.MARITIME_SNARE)) > 0) {
			int level = player.getMainHandItem().get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.MARITIME_SNARE));
			if (player.level().getRandom().nextFloat() <= level * 0.10) {
				TagKey<EntityType<?>> tagKey = level < 2 ? RisusTags.Entities.SMALL_MARITIME_SNARE_POOL : level == 2 ? RisusTags.Entities.MEDIUM_MARITIME_SNARE_POOL : RisusTags.Entities.LARGE_MARITIME_SNARE_POOL;
				Optional<Holder<EntityType<?>>> optional = BuiltInRegistries.ENTITY_TYPE.getTag(tagKey).map(t -> t.getRandomElement(player.level().getRandom())).orElse(null);
				if (!optional.isEmpty()) {
					Entity entity = ((EntityType) ((Holder) optional.get()).value()).spawn((ServerLevel) player.level(), event.getHookEntity().getOnPos().below(), MobSpawnType.TRIGGERED);
					entity.moveTo(event.getHookEntity().getX(), event.getHookEntity().getY(), event.getHookEntity().getZ());
					Vec3 vec3 = (new Vec3(player.getX() - event.getHookEntity().getX(), player.getY() - event.getHookEntity().getY(), player.getZ() - event.getHookEntity().getZ())).scale(0.2);
					entity.setDeltaMovement(entity.getDeltaMovement().add(vec3));
					if (entity instanceof ThrownTrident trident) {
						trident.pickup = AbstractArrow.Pickup.ALLOWED;
						trident.getPickupItemStackOrigin().setDamageValue(200);
					}
				}
			}
		}
	}

	public static void onGravityWell(ItemFishedEvent event) {
		Player player = event.getEntity();
		if (player.getMainHandItem().has(DataComponents.ENCHANTMENTS) && player.getMainHandItem().get(DataComponents.ENCHANTMENTS).getLevel(player.registryAccess().holderOrThrow(Execrations.GRAVITY_WELL)) > 0) {
			int level = player.getMainHandItem().get(DataComponents.ENCHANTMENTS).getLevel(player.level().registryAccess().holderOrThrow(Execrations.GRAVITY_WELL));
			if (player.level().getRandom().nextFloat() <= level * 0.10) {
				FishingHook hook = event.getHookEntity();
				ServerParticleUtils.spawnParticles(player.level(), hook.getOnPos().above(), 1, 0, 0, true, ParticleTypes.GUST);
				player.level().playSound(null, hook.getOnPos(), RisusSoundEvents.AIR_SUCKED_IN.get(), player.getSoundSource(), 0.1F, 1);
				List<Entity> list = player.level().getEntities(hook, hook.getBoundingBox().inflate(level * 10));
				for (Entity pulledEntity : list) {
					Vec3 vec3 = (new Vec3(event.getHookEntity().getX() - pulledEntity.getX(), event.getHookEntity().getY() - pulledEntity.getY(), event.getHookEntity().getZ() - pulledEntity.getZ())).scale(level * 0.08);
					if (pulledEntity instanceof Player player1) {
						player1.move(MoverType.PISTON, new Vec3(0.0, 1.1999999F, 0.0));
						player1.setDeltaMovement(pulledEntity.getDeltaMovement().add(vec3));
						player1.hurtMarked = true;
					} else {
						pulledEntity.setDeltaMovement(pulledEntity.getDeltaMovement().add(vec3));
					}
				}
			}
		}
	}

	//not an event, but fits here
	public static void performRelocation(int i, LivingEntity user, ItemStack original,int chance) {
		if (user.level().getRandom().nextFloat() <= 0.25 + chance) {
			List<ItemStack> list = new ArrayList<>();
			user.getAllSlots().iterator().forEachRemaining(list::add);
			Collections.shuffle(list);
			for (ItemStack itemStack : list) {
				if (!itemStack.isEmpty() &&
					itemStack.isDamageableItem() &&
					!itemStack.is(RisusTags.Items.NOT_RELOCATABLE_TO) &&
					itemStack.getDamageValue() < itemStack.getMaxDamage()-1 &&
					itemStack != original &&
					(!itemStack.has(DataComponents.ENCHANTMENTS) || itemStack.get(DataComponents.ENCHANTMENTS).getLevel(user.registryAccess().holderOrThrow(Execrations.RELOCATION)) < 1)) {
					EquipmentSlot targetSlot = user.getItemBySlot(EquipmentSlot.OFFHAND) == itemStack ? EquipmentSlot.OFFHAND :
						user.getItemBySlot(EquipmentSlot.MAINHAND) == itemStack ? EquipmentSlot.MAINHAND :
						user.getItemBySlot(EquipmentSlot.HEAD) == itemStack ? EquipmentSlot.HEAD :
						user.getItemBySlot(EquipmentSlot.CHEST) == itemStack ? EquipmentSlot.CHEST :
						user.getItemBySlot(EquipmentSlot.LEGS) == itemStack ? EquipmentSlot.LEGS :
						EquipmentSlot.FEET;
					original.setDamageValue(original.getDamageValue()-1);
					itemStack.hurtAndBreak(1, user, targetSlot);
					break;
				}
			}
		}
	}

	public static void clearFierySpeed(LivingEquipmentChangeEvent event) {
		if (event.getFrom().has(DataComponents.ENCHANTMENTS) &&
			event.getFrom().get(DataComponents.ENCHANTMENTS).getLevel(event.getEntity().registryAccess().holderOrThrow(Execrations.PYROMANIAC)) > 0 &&
			Objects.requireNonNull(event.getEntity().getAttribute(Attributes.MOVEMENT_SPEED)).getModifier(Risus.prefix("fiery_speed")) != null) {
			Objects.requireNonNull(event.getEntity().getAttribute(Attributes.MOVEMENT_SPEED)).removeModifier(Risus.prefix("fiery_speed"));
		}
	}

	public static void boostDefiantTrident(EntityJoinLevelEvent event) {
		//let's make all tridents that can be picked up not despawn
		if (event.getEntity() instanceof ThrownTrident trident && trident.pickup == AbstractArrow.Pickup.ALLOWED) {
			trident.life = -10000000;
			if (trident.getPickupItemStackOrigin().has(DataComponents.ENCHANTMENTS) && trident.getPickupItemStackOrigin().get(DataComponents.ENCHANTMENTS).getLevel(event.getEntity().registryAccess().holderOrThrow(Execrations.DEFIANCE)) > 0) {
				float strength = trident.getPickupItemStackOrigin().get(DataComponents.ENCHANTMENTS).getLevel(event.getEntity().registryAccess().holderOrThrow(Execrations.DEFIANCE));
				trident.setDeltaMovement(trident.getDeltaMovement().scale(1+strength*0.20));
				trident.setBaseDamage(trident.getBaseDamage()+strength*2);
				trident.setGlowingTag(true);
				if (trident.level() instanceof ServerLevel serverLevel) {
					for (int i = 0; i < 5; ++i) {
						serverLevel.sendParticles(ParticleTypes.CRIT, trident.getRandomX(1), trident.getRandomY(), trident.getRandomZ(1F), 1, 0.0F, 0.0F, 0.0F, 0.1);
					}
				}
			}
		}
	}


	public static void continueHypersomnia(CanContinueSleepingEvent event) {
		LivingEntity victim = event.getEntity();
		if (victim.getItemBySlot(EquipmentSlot.HEAD).has(DataComponents.ENCHANTMENTS) && victim.getItemBySlot(EquipmentSlot.HEAD).get(DataComponents.ENCHANTMENTS).getLevel(event.getEntity().registryAccess().holderOrThrow(Execrations.HYPERSOMNIA)) > 0) {
			event.setContinueSleeping(true);
		}
	}
}
