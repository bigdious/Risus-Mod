package com.bigdious.risus;

import com.bigdious.risus.entity.*;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.*;
import com.bigdious.risus.network.CreateCritParticlePacket;
import com.google.common.collect.Maps;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.network.PacketDistributor;

public class RisusEvents {

	public static void initEvents(IEventBus bus) {
		bus.addListener(RisusEvents::commonSetup);
		bus.addListener(RisusEvents::registerAttributes);
		bus.addListener(RisusEvents::registerSpawnPlacements);
		NeoForge.EVENT_BUS.addListener(RisusEvents::registerPotionRecipes);
		NeoForge.EVENT_BUS.addListener(RisusEvents::knockOutSomeTeeth);
	}

	private static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
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

	}

	private static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(RisusEntities.ANGEL.get(), Angel.attributes().build());
		event.put(RisusEntities.HOLDER.get(), Holder.attributes().build());
		event.put(RisusEntities.MAW.get(), Maw.attributes().build());
		event.put(RisusEntities.WEAVER.get(), Weaver.attributes().build());
		event.put(RisusEntities.LOVER.get(), Lover.attributes().build());
		event.put(RisusEntities.STALKER.get(), Stalker.attributes().build());
		event.put(RisusEntities.QUESTION_MARK.get(), QuestionMark.attributes().build());
		event.put(RisusEntities.MEMORY1.get(), Memory1.attributes().build());
	}
	private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(RisusEntities.LOVER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lover::canLoverSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		}

	private static void knockOutSomeTeeth(LivingIncomingDamageEvent event) {
		Entity source = event.getSource().getEntity();

		if (source instanceof Player player) {
			if (player.hasEffect(RisusMobEffects.TOOTHLUSTER)) {
				if (!event.getEntity().level().isClientSide()) {
					PacketDistributor.sendToPlayersTrackingEntity(event.getEntity(), new CreateCritParticlePacket(event.getEntity().getId(), 1, RisusParticles.TOOTHICAL.get()));
				}
			}
		}
	}
}
