package com.bigdious.risus;

import com.bigdious.risus.fluid.RisusFluidTypes;
import com.bigdious.risus.fluid.RisusFluids;
import com.bigdious.risus.init.*;
import com.bigdious.risus.init.RisusTab;
import com.bigdious.risus.network.RisusPacketHandler;
import com.google.common.collect.Maps;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@Mod(Risus.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Risus {
	public static final String MODID = "risus";
	public static final Logger LOGGER = LogManager.getLogger();

	private static final Rarity RISUS = Rarity.create("RISUS", ChatFormatting.DARK_RED);
	private static final Rarity PURE = Rarity.create("Vanilla", ChatFormatting.WHITE);

	public Risus() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		RisusBlockEntities.BLOCK_ENTITIES.register(bus);
		RisusBlocks.BLOCKS.register(bus);
		RisusEntities.ENTITIES.register(bus);
		RisusItems.ITEMS.register(bus);
		RisusMobEffects.MOB_EFFECTS.register(bus);
		RisusMenuTypes.MENU_TYPES.register(bus);
		RisusParticles.PARTICLES.register(bus);
		RisusRecipes.RECIPE_SERIALIZERS.register(bus);
		RisusRecipes.RECIPE_TYPES.register(bus);
		RisusPotions.POTIONS.register(bus);
		RisusEntities.SPAWN_EGGS.register(bus);
		RisusTab.CREATIVE_TABS.register(bus);
		RisusSoundEvents.SOUNDS.register(bus);
		RisusFluidTypes.FLUID_TYPES.register(bus);
		RisusFluids.FLUIDS.register(bus);

		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, RisusCapabilities::attachEntityCapability);
		bus.addListener(RisusCapabilities::registerCapabilities);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			RisusDispenserRegistry.init();
			RisusPacketHandler.init();

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

			PotionBrewing.addMix(Potions.AWKWARD, RisusItems.GUILTY_APPLE.get(), RisusPotions.MATING_FRENZY.get());
			PotionBrewing.addMix(RisusPotions.MATING_FRENZY.get(), Items.REDSTONE, RisusPotions.LONG_MATING_FRENZY.get());

			//wood types
			WoodType.register(RisusBlocks.BONDKNOT_TYPE);

			//fluid
			RisusFluids.registerFluidInteractions();

		}

		);
	}

	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
	}

	public static Rarity getRarity() {
		return RISUS;
	}
	public static Rarity getVanilla() {
		return RISUS;
	}
}
