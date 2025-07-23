package com.bigdious.risus.event;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.dispenser.RisusDispenserBehaviours;
import com.bigdious.risus.entity.*;
import com.bigdious.risus.init.*;
import com.bigdious.risus.items.utility.EternalYouthItem;
import com.bigdious.risus.network.UnyieldingTotemPacket;
import com.bigdious.risus.util.ServerParticleUtils;
import com.google.common.collect.Maps;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosCapability;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class RisusEvents {
	public static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR;

	public static void initEvents(IEventBus bus) {
		bus.addListener(RisusEvents::commonSetup);
		bus.addListener(RisusEvents::registerAttributes);
		bus.addListener(RisusEvents::registerSpawnPlacements);
		NeoForge.EVENT_BUS.addListener(RisusEvents::registerPotionRecipes);
		NeoForge.EVENT_BUS.addListener(RisusEvents::knockOutSomeTeeth);
		NeoForge.EVENT_BUS.addListener(RisusEvents::addExBurnParticles);
		NeoForge.EVENT_BUS.addListener(RisusEvents::addDeathParticles);
		NeoForge.EVENT_BUS.addListener(RisusEvents::addHearts);
		NeoForge.EVENT_BUS.addListener(RisusEvents::addEggSack);
		NeoForge.EVENT_BUS.addListener(RisusEvents::sacrificeAccepted);
		NeoForge.EVENT_BUS.addListener(RisusEvents::welcomePlayer);
		NeoForge.EVENT_BUS.addListener(RisusEvents::explodeStick);
		NeoForge.EVENT_BUS.addListener(RisusEvents::fireScythe);
		NeoForge.EVENT_BUS.addListener(RisusEvents::cindergleeScythe);
		NeoForge.EVENT_BUS.addListener(RisusEvents::soulScythe);
		NeoForge.EVENT_BUS.addListener(RisusEvents::hurtWings);
		NeoForge.EVENT_BUS.addListener(RisusEvents::getWaxedRisusStyle);
		NeoForge.EVENT_BUS.addListener(RisusEvents::onLivingDeath);
		NeoForge.EVENT_BUS.addListener(RisusEvents::luckyCharmMiracle);
		NeoForge.EVENT_BUS.addListener(RisusEvents::luckyCharmBenefit);
		NeoForge.EVENT_BUS.addListener(RisusEvents::wretchedCharmDeath);
		NeoForge.EVENT_BUS.addListener(RisusEvents::wretchedCharmMisfortune);
		NeoForge.EVENT_BUS.addListener(RisusEvents::onSpongeBlockPlacedEvent);
		NeoForge.EVENT_BUS.addListener(RisusEvents::onSpongeBlockNeighborUpdatedEvent);
		NeoForge.EVENT_BUS.addListener(RisusEvents::roseCrownBehavior);
		NeoForge.EVENT_BUS.addListener(RisusEvents::eternalizeTamables);
	}

	private static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			RisusCauldronInteractions.register();
			RisusDispenserBehaviours.register();

			//block stripping
			AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
			AxeItem.STRIPPABLES.put(RisusBlocks.BONDKNOT_LOG.get(), RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
			AxeItem.STRIPPABLES.put(RisusBlocks.BONDKNOT_WOOD.get(), RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());

			//Flammable blocks
			FireBlock fireblock = (FireBlock) Blocks.FIRE;
			fireblock.setFlammable(RisusBlocks.BONDKNOT_LOG.get(), 5, 5);
			fireblock.setFlammable(RisusBlocks.BONDKNOT_WOOD.get(), 5, 5);
			fireblock.setFlammable(RisusBlocks.STRIPPED_BONDKNOT_LOG.get(), 5, 5);
			fireblock.setFlammable(RisusBlocks.STRIPPED_BONDKNOT_WOOD.get(), 5, 5);
			fireblock.setFlammable(RisusBlocks.BONDKNOT_PLANKS.get(), 5, 20);
			fireblock.setFlammable(RisusBlocks.BONDKNOT_STAIRS.get(), 5, 20);
			fireblock.setFlammable(RisusBlocks.BONDKNOT_SLAB.get(), 5, 20);
			fireblock.setFlammable(RisusBlocks.BONDKNOT_FENCE.get(), 5, 20);
			fireblock.setFlammable(RisusBlocks.BONDKNOT_FENCE_GATE.get(), 5, 20);

			FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
			pot.addPlant(RisusBlocks.HEART_TRANSPLANT.getId(), RisusBlocks.POTTED_HEART_TRANSPLANT);
			FlowerPotBlock pot2 = (FlowerPotBlock) Blocks.FLOWER_POT;
			pot2.addPlant(RisusBlocks.REGEN_ROSE.getId(), RisusBlocks.POTTED_REGEN_ROSE);

			//wood types
			WoodType.register(RisusBlocks.BONDKNOT_TYPE);

			//fluid
			FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
				RisusFluids.BLOOD_FLUID_TYPE.get(),
				fluidState -> {
					if (!fluidState.isSource()) {
						return RisusBlocks.SCAB.get().defaultBlockState();
					} else {
						return RisusBlocks.LAUGHING_OBSIDIAN.get().defaultBlockState();
					}
				}
			));
			FluidInteractionRegistry.addInteraction(NeoForgeMod.WATER_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
				RisusFluids.BLOOD_FLUID_TYPE.get(),
				fluidState -> RisusBlocks.COAGULATED_BLOOD_BLOCK.get().defaultBlockState()
			));
		});
	}

	private static void registerPotionRecipes(RegisterBrewingRecipesEvent event) {
		PotionBrewing.Builder builder = event.getBuilder();
		builder.addMix(Potions.AWKWARD, RisusItems.GUILTY_APPLE.get(), RisusPotions.MATING_FRENZY);
		builder.addMix(RisusPotions.MATING_FRENZY, Items.REDSTONE, RisusPotions.LONG_MATING_FRENZY);

		builder.addMix(Potions.AWKWARD, RisusItems.MEMORY_CORE.get(), RisusPotions.AMNESIA);
		builder.addMix(RisusPotions.MATING_FRENZY, Items.REDSTONE, RisusPotions.LONG_AMNESIA);

		builder.addMix(Potions.AWKWARD, RisusBlocks.JOYFLAME_CAMPFIRE.asItem(), RisusPotions.LIFE_SMOULDERING);
		builder.addMix(RisusPotions.LIFE_SMOULDERING, Items.REDSTONE, RisusPotions.LONG_LIFE_SMOULDERING);
		builder.addMix(RisusPotions.LIFE_SMOULDERING, Items.GLOWSTONE_DUST, RisusPotions.STRONG_LIFE_SMOULDERING);

	}

	private static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(RisusEntities.ANGEL.get(), Angel.attributes().build());
		event.put(RisusEntities.HOLDER.get(), Holder.attributes().build());
		event.put(RisusEntities.MAW.get(), Maw.attributes().build());
		event.put(RisusEntities.WEAVER.get(), Weaver.attributes().build());
		event.put(RisusEntities.LOVER.get(), Lover.attributes().build());
		event.put(RisusEntities.SINGER.get(), Singer.attributes().build());
		event.put(RisusEntities.LICKER.get(), Licker.attributes().build());
		event.put(RisusEntities.BABY_SPIDER.get(), BabySpider.attributes().build());
		event.put(RisusEntities.STALKER.get(), Stalker.attributes().build());
		event.put(RisusEntities.QUESTION_MARK.get(), QuestionMark.attributes().build());
		event.put(RisusEntities.MEMORY1.get(), Memory1.attributes().build());
		event.put(RisusEntities.LITTER.get(), Litter.createAttributes().build());
	}

	private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(RisusEntities.LOVER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lover::canLoverSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	private static void knockOutSomeTeeth(LivingIncomingDamageEvent event) {
		Entity source = event.getSource().getEntity();

		if (source instanceof Player player) {
			if (player.hasEffect(RisusMobEffects.TOOTHLUSTER)) {
				if (event.getEntity().level() instanceof ServerLevel serverLevel) {
					serverLevel.sendParticles(RisusParticles.TOOTHICAL.get(), event.getEntity().getX(), event.getEntity().getEyeY(), event.getEntity().getZ(), 10, 0, 0, 0, 1);
				}
			}
		}
	}

	public static OrganicMatterEvent fireOrganicMatterEvent(@Nullable Player player, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		return NeoForge.EVENT_BUS.post(new OrganicMatterEvent(player, level, pos, state, stack));
	}

	private static void addExBurnParticles(EntityTickEvent.Post event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity living) {
			if (living.tickCount % 5 == 0 && living.hasEffect(RisusMobEffects.EXBURN)) {
				if (living.level() instanceof ServerLevel serverLevel) {
					for (int i = 0; i < 2; i++) {
						serverLevel.sendParticles(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5), 1, 0.0, 0.0, 0.0, 0);
					}
				}
			}
		}
	}

	private static void addDeathParticles(EntityTickEvent.Post event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity living) {
			if (living.tickCount % 5 == 0 && living.hasEffect(RisusMobEffects.DESTINED_DEATH)) {
				if (living.level() instanceof ServerLevel serverLevel) {
					for (int i = 0; i < 2; i++) {
						serverLevel.sendParticles(RisusParticles.DESTINED_DEATH_PARTICLE.get(), living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5), 1, 0.0, 0.0, 0.0, 0);
					}
				}
			}
		}
	}


	private static void addHearts(EntityTickEvent.Pre event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity living) {
			if (living.tickCount % 5 == 0 && living.hasEffect(RisusMobEffects.MATING_FRENZY)) {
				if (living.level() instanceof ServerLevel serverLevel) {
					serverLevel.sendParticles(ParticleTypes.HEART, living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5), 3, 0, 0, 0, 0);
				}
			}
		}
	}


	private static void welcomePlayer(AdvancementEvent.AdvancementEarnEvent event) {
		Player player = event.getEntity();
		Level level = player.level();
		if (event.getAdvancement().id().equals(Risus.prefix("first"))) {
			for (int i = 0; i < 13; i++) {
				QuestionMark witness = RisusEntities.QUESTION_MARK.get().create(level);
				witness.setTransient();
				witness.moveTo(getBoxAround(player, 4, 40), 0.0F, 0.0F);
				level.addFreshEntity(witness);
			}
		}
	}

	private static BlockPos getBoxAround(Entity entity, int padding, int radius) {
		RandomSource random = RandomSource.create();
		BlockPos pos = entity.blockPosition();
		AABB paddingBox = new AABB(pos).inflate(padding);
		//check 10 random spots in a box around the player, excluding a small box defined by the padding
		for (BlockPos checkPos : BlockPos.randomInCube(entity.getRandom(), 10, pos, radius)) {
			if (paddingBox.intersects(new AABB(checkPos))) continue;
			return checkPos.atY( Math.max(pos.getY()-10, pos.getY()-5+random.nextInt(40)));
		}
		//didnt find a spot? Spawn 6 blocks above player
		return pos.atY(pos.getY() + 6);
	}

	private static void explodeStick(LivingIncomingDamageEvent event) {
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

	private static void explode(Level level, double x, double y, double z, int radius, LivingEntity entity, Boolean isFiery) {
		level.explode(null, level.damageSources().explosion(entity, null), null, x, y, z, radius+3F, isFiery, Level.ExplosionInteraction.BLOCK);
	}
	static {
		EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(true, false, Optional.empty(), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));
	}
	private static void windBurstExplode(Level level, double x, double y, double z, int radius ,Boolean isFiery) {
		level.explode(null, null, EXPLOSION_DAMAGE_CALCULATOR, x, y, z, radius+3, isFiery, Level.ExplosionInteraction.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.BREEZE_WIND_CHARGE_BURST);
	}

	//do not touch below scythe events. It's stupid, but they need to stay as is
	private static void fireScythe(LivingDamageEvent.Post event) {
		Entity entity = event.getSource().getEntity();
		LivingEntity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker &&  attacker.getMainHandItem().is(RisusItems.FIRE_SCYTHE.get())) {
			entity2.addEffect(new MobEffectInstance(RisusMobEffects.FLAME_FRAILTY, 200, 0, false, false, true));
			entity2.igniteForSeconds(2);
		}
	}

	private static void cindergleeScythe(LivingDamageEvent.Post event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker && entity2 instanceof LivingEntity victim && attacker.getMainHandItem().is(RisusItems.CINDERGLEE_SCYTHE.get())) {
			victim.addEffect(new MobEffectInstance(RisusMobEffects.EXBURN, 600, 1, false, false, true));
		}
	}

	private static void soulScythe(LivingIncomingDamageEvent event) {
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

	private static void addEggSack(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Licker licker) {
			if (licker.level() instanceof ServerLevel serverLevel) {
				serverLevel.sendParticles(ParticleTypes.ITEM_COBWEB, licker.getRandomX(0.5), licker.getY() + 1, licker.getRandomZ(0.5), 7, 0, 0, 0, 0);
			}
		}
	}

	private static void sacrificeAccepted(LivingDeathEvent event) {
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

	private static void hurtWings(PlayerInteractEvent.RightClickItem event) {
		if (event.getItemStack().is(RisusTags.Items.HURTS_ANGEL_WINGS) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(RisusItems.ANGEL_WINGS) && event.getEntity().isFallFlying()) {
			event.getEntity().getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(30, event.getEntity(), EquipmentSlot.CHEST);
		}
		if (event.getItemStack().is(RisusTags.Items.LIGHTLY_HURTS_ANGEL_WINGS) && event.getEntity().getItemBySlot(EquipmentSlot.CHEST).is(RisusItems.ANGEL_WINGS) && (event.getEntity().isFallFlying() || event.getEntity().isInWaterRainOrBubble() || event.getEntity().isInLava())) {
			event.getEntity().getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(10, event.getEntity(), EquipmentSlot.CHEST);
		}
	}

	private static void getWaxedRisusStyle(PlayerInteractEvent.RightClickBlock event) {
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

	private static boolean curiosSearch(LivingEntity entity, Item item) {
		if (ModList.get().isLoaded("curios")) {
			var handler = entity.getCapability(CuriosCapability.INVENTORY);
			if (handler == null) return false;
			var s = handler.findCurios(item);
			if (s.isEmpty()) return false; else return true;
		}
		return false;
	}


	private static void onLivingDeath(@NotNull LivingDeathEvent event) {
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
	private static void luckyCharmMiracle(@NotNull LivingDeathEvent event) {
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

	private static void luckyCharmBenefit(LivingIncomingDamageEvent event) {
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
	private static void wretchedCharmDeath(@NotNull LivingDeathEvent event) {
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

	private static void wretchedCharmMisfortune(LivingIncomingDamageEvent event) {
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

	public static void onSpongeBlockNeighborUpdatedEvent(BlockEvent.NeighborNotifyEvent event) {
		LevelAccessor accessor = event.getLevel();
		BlockPos pos = event.getPos();
		// get all the neighbors of the block, if any of them are a sponge block, run the sponge block logic
		for (Direction direction : event.getNotifiedSides()) {
			BlockPos neighborPos = pos.relative(direction);
			BlockState neighborState = accessor.getBlockState(neighborPos);
			if (neighborState.is(Blocks.SPONGE)) {
				handleSpongeBlockPlaceOrUpdate(accessor, neighborPos);
			}
		}
	}

	public static void onSpongeBlockPlacedEvent(BlockEvent.EntityPlaceEvent event) {
		handleSpongeBlockPlaceOrUpdate(event.getLevel(), event.getPos());
	}

	private static void handleSpongeBlockPlaceOrUpdate(LevelAccessor accessor, BlockPos pos) {
		if (!accessor.getBlockState(pos).is(Blocks.SPONGE)) return;

		if (absorbBlood(accessor, pos)) {
			accessor.setBlock(pos, RisusBlocks.BLOODY_SPONGE.get().defaultBlockState(), 2); // Replace with Bloody Sponge
			accessor.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0F, 1.0F); // Play absorb sound
		}
	}

	private static boolean absorbBlood(LevelAccessor accessor, BlockPos pos) {
		return BlockPos.breadthFirstTraversal(pos, 6, 65, (currentPos, consumer) -> {
			for (Direction direction : Direction.values()) {
				consumer.accept(currentPos.relative(direction));
			}
		}, (targetPos) -> {
			if (targetPos.equals(pos)) {
				return true;
			} else {
				BlockState state = accessor.getBlockState(targetPos);

				if (state.hasProperty(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED) && state.getValue(SimpleMultiloggedBlock.MultiloggingEnum.FLUIDLOGGED) == SimpleMultiloggedBlock.MultiloggingEnum.BLOOD) {
					return state.getBlock() instanceof BucketPickup bucketpickup && !bucketpickup.pickupBlock(null, accessor, targetPos, state).isEmpty();
				} else if (state.is(RisusBlocks.BLOOD_FLUID_BLOCK)) {
					accessor.setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
					return true;
				}
			}

			return false;
		}) > 1;
	}

	private static void roseCrownBehavior(LivingIncomingDamageEvent event) {
		Entity attacker = event.getSource().getEntity();
		LivingEntity victim = event.getEntity();
		if (victim.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.ROSE_CROWN)) {
			victim.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120));
			if (attacker instanceof LivingEntity livingAttacker){
				livingAttacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 120));
			}
		};
	}

	private static void eternalizeTamables(PlayerInteractEvent.EntityInteract event){
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

}
