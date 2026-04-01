package com.bigdious.risus.event;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.dispenser.RisusDispenserBehaviours;
import com.bigdious.risus.entity.*;
import com.bigdious.risus.entity.creatures.*;
import com.bigdious.risus.entity.creatures.pets.Holder;
import com.bigdious.risus.entity.creatures.pets.Litter;
import com.bigdious.risus.init.*;
import com.bigdious.risus.init.RisusVillagers;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;


public class RisusEvents {


	public static void initEvents(IEventBus bus) {
		bus.addListener(RisusEvents::commonSetup);
		bus.addListener(RisusEvents::registerAttributes);
		bus.addListener(RisusEvents::registerSpawnPlacements);
		NeoForge.EVENT_BUS.addListener(RisusEvents::registerPotionRecipes);
		NeoForge.EVENT_BUS.addListener(RisusEvents::registerVillagerTrades);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::knockOutSomeTeeth);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::addParticles);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::addEggSack);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::sacrificeAccepted);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::stoolDiesOnDeath);
		NeoForge.EVENT_BUS.addListener(RisusEvents::welcomePlayer);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::explodeStick);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::handWeapons);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::handWeaponDamageEffects);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::hurtWings);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::getWaxedRisusStyle);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::totemOfUnyieldingActivate);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::luckyCharmMiracle);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::luckyCharmBenefit);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::wretchedCharmDeath);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::wretchedCharmMisfortune);
		NeoForge.EVENT_BUS.addListener(RisusEvents::onSpongeBlockPlacedEvent);
		NeoForge.EVENT_BUS.addListener(RisusEvents::onSpongeBlockNeighborUpdatedEvent);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::roseCrownBehavior);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::eternalizeTamables);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::updateVisibility);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::increaseItemPickupRange);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::shadowWalk);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::staysUponDeath);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::onPlayerRespawn);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::genocideSweep);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::onOverload);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::onMaritimeSnare);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::onGravityWell);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::clearFierySpeed);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::boostDefiantTrident);
		NeoForge.EVENT_BUS.addListener(ExecrationEvents::continueHypersomnia);
		NeoForge.EVENT_BUS.addListener(RisusEvents::playerDropEasterEgg);
		NeoForge.EVENT_BUS.addListener(RisusEvents::healingDenied);
	}

	private static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			RisusCauldronInteractions.register();
			RisusDispenserBehaviours.register();

			//wash off dye
			CauldronInteraction.WATER.map().put(RisusItems.SINNER_ROBES_HELMET.get(), CauldronInteraction.DYED_ITEM);
			CauldronInteraction.WATER.map().put(RisusItems.SINNER_ROBES_CHESTPLATE.get(), CauldronInteraction.DYED_ITEM);
			CauldronInteraction.WATER.map().put(RisusItems.SINNER_ROBES_LEGGINGS.get(), CauldronInteraction.DYED_ITEM);
			CauldronInteraction.WATER.map().put(RisusItems.SINNER_ROBES_BOOTS.get(), CauldronInteraction.DYED_ITEM);

			//block stripping
			AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
			AxeItem.STRIPPABLES.put(RisusBlocks.BONDKNOT_LOG.get(), RisusBlocks.STRIPPED_BONDKNOT_LOG.get());
			AxeItem.STRIPPABLES.put(RisusBlocks.BONDKNOT_WOOD.get(), RisusBlocks.STRIPPED_BONDKNOT_WOOD.get());
			AxeItem.STRIPPABLES.put(RisusBlocks.GRILLED_TISSUE.get(), RisusBlocks.PEELED_GRILLED_TISSUE.get());

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
		builder.addMix(RisusPotions.AMNESIA, Items.REDSTONE, RisusPotions.LONG_AMNESIA);

		builder.addMix(Potions.AWKWARD, RisusBlocks.JOYFLAME_CAMPFIRE.asItem(), RisusPotions.LIFE_SMOULDERING);
		builder.addMix(RisusPotions.LIFE_SMOULDERING, Items.REDSTONE, RisusPotions.LONG_LIFE_SMOULDERING);
		builder.addMix(RisusPotions.LIFE_SMOULDERING, Items.GLOWSTONE_DUST, RisusPotions.STRONG_LIFE_SMOULDERING);

		builder.addMix(Potions.AWKWARD, RisusItems.LUCKY_CHARM.get(), Potions.LUCK);
		builder.addMix(Potions.LUCK, Items.REDSTONE, RisusPotions.LONG_LUCK);
		builder.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, RisusPotions.STRONG_LUCK);

		builder.addMix(Potions.AWKWARD, RisusItems.WRETCHED_CHARM.get(), RisusPotions.BAD_LUCK);
		builder.addMix(Potions.LUCK, Items.REDSTONE, RisusPotions.LONG_BAD_LUCK);
		builder.addMix(RisusPotions.BAD_LUCK, Items.GLOWSTONE_DUST, RisusPotions.STRONG_BAD_LUCK);

		builder.addMix(Potions.AWKWARD, RisusBlocks.LIGHT_EXCREMENT.asItem(), RisusPotions.GLOWING);
		builder.addMix(RisusPotions.GLOWING, Items.REDSTONE, RisusPotions.LONG_GLOWING);

		builder.addMix(Potions.AWKWARD, RisusItems.KILLJOY.get(), RisusPotions.CLOTTING);
		builder.addMix(RisusPotions.CLOTTING, Items.REDSTONE, RisusPotions.LONG_CLOTTING);

		builder.addMix(Potions.AWKWARD, RisusItems.HAND_OF_GREED.get(), RisusPotions.GOLDEN_GLORY);
		builder.addMix(RisusPotions.GOLDEN_GLORY, Items.FERMENTED_SPIDER_EYE, RisusPotions.COPPER_AGE);

	}
	private static void registerVillagerTrades(VillagerTradesEvent event) {
		if (event.getType() == RisusVillagers.ASCETIC.value()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			trades.get(1).add(((new VillagerTrades.EmeraldForItems(Items.RED_DYE, 32, 16, 2))));
			trades.get(1).add(((new VillagerTrades.ItemsForEmeralds(RisusBlocks.HEART_TRANSPLANT.get().asItem(), 5, 1, 3))));
			trades.get(1).add(((new VillagerTrades.EmeraldForItems(RisusItems.MEMORY_CORE, 2, 12, 4))));
			trades.get(1).add(((new VillagerTrades.TreasureMapForEmeralds(
				12, RisusTags.Structures.ANGEL_ALTAR, "filled_map.angel_altar", RisusMapDecorations.ANGEL_ALTAR, 12, 10))));

			trades.get(2).add(((new VillagerTrades.TreasureMapForEmeralds(
				12, RisusTags.Structures.HEART_CHAMBER, "filled_map.heart_chamber", RisusMapDecorations.HEART_CHAMBER, 12, 10))));
			trades.get(2).add(((new VillagerTrades.TreasureMapForEmeralds(
				13, RisusTags.Structures.RIBS_FOSSIL, "filled_map.ribs_fossil", RisusMapDecorations.RIBS_FOSSIL, 12, 10))));
			trades.get(2).add(((new VillagerTrades.TreasureMapForEmeralds(
				13, RisusTags.Structures.SKULL_FOSSIL, "filled_map.skull_fossil", RisusMapDecorations.SKULL_FOSSIL, 12, 10))));
			trades.get(2).add(((new VillagerTrades.TreasureMapForEmeralds(
				10, RisusTags.Structures.GREAT_BODY, "filled_map.great_body", RisusMapDecorations.GREAT_BODY, 12, 10))));

			trades.get(3).add(((new VillagerTrades.EmeraldForItems(RisusBlocks.ORGANIC_MATTER_BLOCK.get().asItem(), 4, 16, 5))));
			trades.get(3).add(((new VillagerTrades.ItemsForEmeralds(RisusBlocks.BABY_RIBCAGE.get().asItem(), 5, 1, 7))));
			trades.get(3).add(((new VillagerTrades.ItemsForEmeralds(RisusBlocks.RIBCAGE.get().asItem(), 10, 1, 7))));
			trades.get(3).add(((new VillagerTrades.ItemsForEmeralds(RisusBlocks.FLATTENED_IMITATION_SCALES_BLOCK.get().asItem(), 3, 8, 7))));
			trades.get(3).add(((new VillagerTrades.ItemsForEmeralds(RisusBlocks.UNALLOYED_SCALES_BLOCK.get().asItem(), 4, 8, 7))));
			trades.get(3).add(((new VillagerTrades.ItemsForEmeralds(RisusBlocks.FOSSIL_FRAGMENTED.get().asItem(), 4, 8, 7))));


			trades.get(4).add(((new VillagerTrades.TreasureMapForEmeralds(
				24, RisusTags.Structures.CHURCH, "filled_map.church", RisusMapDecorations.CHURCH, 12, 20))));
			trades.get(4).add(((new VillagerTrades.TreasureMapForEmeralds(
				24, RisusTags.Structures.BLOOD_POOL, "filled_map.blood_pool", RisusMapDecorations.BLOOD_POOL, 12, 20))));
			trades.get(4).add(((new VillagerTrades.TreasureMapForEmeralds(
				24, RisusTags.Structures.FLOWER_FIELD, "filled_map.flower_field", RisusMapDecorations.FLOWER_FIELD, 12, 20))));

			trades.get(5).add(((new VillagerTrades.EnchantBookForEmeralds(20, RisusTags.Enchantments.EXECRATIONS))));
			trades.get(5).add(((new VillagerTrades.ItemsForEmeralds(RisusItems.TOTEM_OF_UNYIELDING.get(), 32, 1, 4, 30))));

		}
	}

	private static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(RisusEntities.ANGEL.get(), Angel.attributes().build());
		event.put(RisusEntities.HEX.get(), Hex.attributes().build());
		event.put(RisusEntities.HOLDER.get(), Holder.attributes().build());
		event.put(RisusEntities.MAW.get(), Maw.attributes().build());
		event.put(RisusEntities.WEAVER.get(), Weaver.attributes().build());
		event.put(RisusEntities.LOVER.get(), Lover.attributes().build());
		event.put(RisusEntities.SINGER.get(), Singer.attributes().build());
		event.put(RisusEntities.LICKER.get(), Licker.attributes().build());
		event.put(RisusEntities.BABY_SPIDER.get(), BabySpider.attributes().build());
		event.put(RisusEntities.STALKER.get(), Stalker.attributes().build());
		event.put(RisusEntities.QUESTION_MARK.get(), QuestionMark.attributes().build());
		event.put(RisusEntities.LITTER.get(), Litter.createAttributes().build());
	}

	private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(RisusEntities.LOVER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lover::canLoverSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}



	public static OrganicMatterEvent fireOrganicMatterEvent(@Nullable Player player, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		return NeoForge.EVENT_BUS.post(new OrganicMatterEvent(player, level, pos, state, stack));
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

	private static void playerDropEasterEgg(LivingDeathEvent event) {
		Map<String, ItemStack> PLAYERS_AND_DROPS = Map.ofEntries(
			Map.entry("68754cb0-8b5f-4c16-94b9-593c3eba3676", Blocks.COPPER_BLOCK.asItem().getDefaultInstance())
			,Map.entry("7a804249-c3da-4b35-b5a9-4f9b8cd9132e", RisusBlocks.SMILING_REMAINS.toStack())
			,Map.entry("853d0097-3169-4eaa-babd-e5a52c11c0f9", Items.CAT_SPAWN_EGG.getDefaultInstance())
			,Map.entry("c8649a16-96eb-4635-b150-6a4f04038a18", Items.GOAT_HORN.getDefaultInstance())
			,Map.entry("566dbb9b-ad89-41a7-9a73-65be81262e9e", Items.WRITABLE_BOOK.getDefaultInstance())
			,Map.entry("c47fe203-99e7-45c4-9c19-2c9281b74364", Items.PORKCHOP.getDefaultInstance())
			,Map.entry("c0159419-7eec-49fb-a4fe-b6fe76f84ade", Items.EMERALD_ORE.getDefaultInstance())
			,Map.entry("33ee90f3-2d59-4485-bbd7-b46d2e30d2cd", RisusBlocks.BLOODY_SPONGE.toStack())
			,Map.entry("95e2a3cb-e967-40fa-a50d-28b122b6d7cd", RisusBlocks.JOYFLAME_CAMPFIRE.toStack())
			,Map.entry("4b09d332-fa2f-4610-aa33-cec55326f5fb", Items.PUFFERFISH.getDefaultInstance())
			,Map.entry("54dd8419-6031-42a9-a195-a695bc5d3558", Items.MUSIC_DISC_CREATOR_MUSIC_BOX.getDefaultInstance())
		);
		if (event.getEntity() instanceof Player player && PLAYERS_AND_DROPS.containsKey(player.getUUID().toString())) {
			ItemEntity drop = EntityType.ITEM.create(player.level());
			if (drop != null) {
				drop.setItem(PLAYERS_AND_DROPS.get(player.getUUID().toString()));
				drop.moveTo(player.getX(), player.getY(), player.getZ());
				player.level().addFreshEntity(drop);
			}
		}
	}

	private static void healingDenied(LivingHealEvent event) {
		if (event.getEntity().hasEffect(RisusMobEffects.BLOODCLOGGED)) {
			event.setCanceled(true);
		}
	}


}
