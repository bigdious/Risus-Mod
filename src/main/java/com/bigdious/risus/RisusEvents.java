package com.bigdious.risus;

import com.bigdious.risus.blocks.RisusCampfireBlock;
import com.bigdious.risus.entity.*;
import com.bigdious.risus.event.OrganicMatterEvent;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.*;
import com.bigdious.risus.network.CreateCritParticlePacket;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.level.PistonEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class RisusEvents {

	public static void initEvents(IEventBus bus) {
		bus.addListener(RisusEvents::commonSetup);
		bus.addListener(RisusEvents::registerAttributes);
		bus.addListener(RisusEvents::registerSpawnPlacements);
		NeoForge.EVENT_BUS.addListener(RisusEvents::registerPotionRecipes);
		NeoForge.EVENT_BUS.addListener(RisusEvents::knockOutSomeTeeth);
		NeoForge.EVENT_BUS.addListener(RisusEvents::addExBurnParticles);
		NeoForge.EVENT_BUS.addListener(RisusEvents::addHearts);
		NeoForge.EVENT_BUS.addListener(RisusEvents::welcomePlayer);
		NeoForge.EVENT_BUS.addListener(RisusEvents::explodeStick);
		NeoForge.EVENT_BUS.addListener(RisusEvents::fireScythe);
		NeoForge.EVENT_BUS.addListener(RisusEvents::cindergleeScythe);
		NeoForge.EVENT_BUS.addListener(RisusEvents::soulScythe);
	}

	private static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
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

	}

	private static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(RisusEntities.ANGEL.get(), Angel.attributes().build());
		event.put(RisusEntities.HOLDER.get(), Holder.attributes().build());
		event.put(RisusEntities.MAW.get(), Maw.attributes().build());
		event.put(RisusEntities.WEAVER.get(), Weaver.attributes().build());
		event.put(RisusEntities.LOVER.get(), Lover.attributes().build());
		event.put(RisusEntities.STALKER.get(), Stalker.attributes().build());
		event.put(RisusEntities.QUESTION_MARK.get(), QuestionMark.attributes().build());
		event.put(RisusEntities.TRANSIENT_QUESTION_MARK.get(), TransientQuestionMark.attributes().build());
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
					PacketDistributor.sendToPlayersTrackingEntity(event.getEntity(), new CreateCritParticlePacket(event.getEntity().getId(), 1, event.getEntity().getEyeHeight(), RisusParticles.TOOTHICAL.get()));
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
						serverLevel.sendParticles(RisusParticles.FIERY_ORGANIC_PARTICLE.get(), living.getRandomX(0.5), living.getRandomY(), living.getRandomZ(0.5),1 ,  0.0, 0.0, 0.0, 0);
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


	private static void welcomePlayer(AdvancementEvent.AdvancementEarnEvent event){
		Entity player = event.getEntity();
		Level level = player.level();
		if (event.getAdvancement().id().equals(Risus.prefix("first"))){
			for (int i = 0; i < 13; i++) {
				QuestionMark witness = RisusEntities.TRANSIENT_QUESTION_MARK.get().create(level);
				witness.moveTo(player.getRandomX(40), player.getRandomY()+2+11*(player.getRandomY()-player.getRandomY()), player.getRandomZ(40), 0.0F, 0.0F);
				level.addFreshEntity(witness);
			}
		}
	}

	private static void explodeStick(LivingIncomingDamageEvent event) {
		Entity entity = event.getSource().getEntity();
		if (entity instanceof LivingEntity attacker && attacker.getMainHandItem().is(RisusItems.BOOMSTICK.get())) {
			explode(attacker.level(), attacker.getOnPos(), attacker);
			attacker.getMainHandItem().hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
		}
	}

	private static void explode(Level level, BlockPos pos, LivingEntity entity) {
		Vec3 vec3 = pos.getCenter().add(0, 1, 0);
		level.explode(null, level.damageSources().explosion(entity, null), null, vec3, 3F, false, Level.ExplosionInteraction.BLOCK);
	}

	private static void fireScythe(LivingDamageEvent.Post event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker && entity2 instanceof Mob victim && attacker.getMainHandItem().is(RisusItems.FIRE_SCYTHE.get())) {
			victim.addEffect(new MobEffectInstance(RisusMobEffects.FLAME_FRAILTY, 200, 0, false, false, true));
			victim.igniteForSeconds(2);
		}
	}
	private static void cindergleeScythe(LivingDamageEvent.Post event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker && entity2 instanceof Mob victim && attacker.getMainHandItem().is(RisusItems.CINDERGLEE_SCYTHE.get())) {
			victim.addEffect(new MobEffectInstance(RisusMobEffects.EXBURN, 600, 0, false, false, true));
		}
	}

	private static void soulScythe(LivingDamageEvent.Pre event) {
		Entity entity = event.getSource().getEntity();
		Entity entity2 = event.getEntity();
		if (entity instanceof LivingEntity attacker && entity2 instanceof Mob victim && attacker.getMainHandItem().is(RisusItems.SOUL_SCYTHE.get())) {
			if (!victim.getType().is(EntityTypePredicate.of(EntityTypeTags.SENSITIVE_TO_SMITE).types())) {
				event.setNewDamage(event.getOriginalDamage()+5);
			} else {
				event.setNewDamage(event.getOriginalDamage()-3);
			}
		}
	}

	//this will be so complicated
	//I am definitely procrastinating this shit
	private static void pushBloodweave(PistonEvent event) {
		BlockState state = event.getState();
		if (state.is(RisusBlocks.BLOODWEAVE.get())){

		}
	}
}
