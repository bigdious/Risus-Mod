package com.bigdious.risus.event;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import com.bigdious.risus.dispenser.RisusDispenserBehaviours;
import com.bigdious.risus.entity.*;
import com.bigdious.risus.init.*;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacementTypes;
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
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import org.jetbrains.annotations.Nullable;


public class RisusEvents {


	public static void initEvents(IEventBus bus) {
		bus.addListener(RisusEvents::commonSetup);
		bus.addListener(RisusEvents::registerAttributes);
		bus.addListener(RisusEvents::registerSpawnPlacements);
		NeoForge.EVENT_BUS.addListener(RisusEvents::registerPotionRecipes);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::knockOutSomeTeeth);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::addExBurnParticles);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::addDeathParticles);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::addHearts);
		NeoForge.EVENT_BUS.addListener(ParticleEvents::addEggSack);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::sacrificeAccepted);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::stoolDiesOnDeath);
		NeoForge.EVENT_BUS.addListener(RisusEvents::welcomePlayer);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::explodeStick);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::fireScythe);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::cindergleeScythe);
		NeoForge.EVENT_BUS.addListener(ItemEffectEvents::soulScythe);
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

		builder.addMix(Potions.AWKWARD, RisusItems.LUCKY_CHARM.get(), Potions.LUCK);
		builder.addMix(Potions.LUCK, Items.REDSTONE, RisusPotions.LONG_LUCK);
		builder.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, RisusPotions.STRONG_LUCK);

		builder.addMix(Potions.AWKWARD, RisusItems.WRETCHED_CHARM.get(), RisusPotions.BAD_LUCK);
		builder.addMix(Potions.LUCK, Items.REDSTONE, RisusPotions.LONG_BAD_LUCK);
		builder.addMix(RisusPotions.BAD_LUCK, Items.GLOWSTONE_DUST, RisusPotions.STRONG_BAD_LUCK);

		builder.addMix(Potions.AWKWARD, RisusBlocks.LIGHT_EXCREMENT.asItem(), RisusPotions.GLOWING);
		builder.addMix(RisusPotions.GLOWING, Items.REDSTONE, RisusPotions.LONG_GLOWING);

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

}
