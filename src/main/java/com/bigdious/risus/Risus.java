package com.bigdious.risus;

import com.bigdious.risus.client.RisusClientEvents;
import com.bigdious.risus.data.*;
import com.bigdious.risus.init.RisusFluids;
import com.bigdious.risus.init.*;
import com.bigdious.risus.network.CreateCritParticlePacket;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Mod(Risus.MODID)
public class Risus {
	public static final String MODID = "risus";
	public static final Logger LOGGER = LogManager.getLogger();

	private static final Rarity RISUS = Rarity.create("risus", Risus.prefix("rarity"), ChatFormatting.DARK_RED);

	public Risus(IEventBus bus, Dist dist) {
		RisusBlockEntities.BLOCK_ENTITIES.register(bus);
		RisusBlocks.BLOCKS.register(bus);
		RisusDataAttachments.ATTACHMENT_TYPES.register(bus);
		RisusEntities.ENTITIES.register(bus);
		RisusItems.ITEMS.register(bus);
		RisusMobEffects.MOB_EFFECTS.register(bus);
		RisusMenuType.MENU_TYPES.register(bus);
		RisusParticles.PARTICLES.register(bus);
		RisusRecipes.RECIPE_SERIALIZERS.register(bus);
		RisusRecipes.RECIPE_TYPES.register(bus);
		RisusPotions.POTIONS.register(bus);
		RisusEntities.SPAWN_EGGS.register(bus);
		RisusTab.CREATIVE_TABS.register(bus);
		RisusSoundEvents.SOUNDS.register(bus);
		RisusFluids.FLUIDS.register(bus);
		RisusFluids.FLUID_TYPES.register(bus);

		bus.addListener(this::registerPackets);
		bus.addListener(this::gatherData);
		RisusEvents.initEvents(bus);

		if (dist.isClient()) {
			RisusClientEvents.initEvents(bus);
		}
	}

	public void registerPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();
		registrar.playToClient(CreateCritParticlePacket.TYPE, CreateCritParticlePacket.STREAM_CODEC, CreateCritParticlePacket::handle);
	}


	private void gatherData(GatherDataEvent event) {
		PackOutput packOutput = event.getGenerator().getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		boolean isServer = event.includeServer();
		boolean isClient = event.includeClient();

//		event.getGenerator().addProvider(isServer, new RisusAdvancementProvider(packOutput, lookupProvider, existingFileHelper));
		event.getGenerator().addProvider(isClient, new BlockModelGenerator(packOutput, existingFileHelper));
		event.getGenerator().addProvider(isClient, new StructureUpdater("structures", packOutput, existingFileHelper));
		event.getGenerator().addProvider(isClient, new ItemModelGenerator(packOutput, existingFileHelper));
		event.getGenerator().addProvider(isServer, new LootGenerator(packOutput, lookupProvider));
		event.getGenerator().addProvider(isServer, new CraftingGenerator(packOutput, lookupProvider));
		var blocktags = new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
		event.getGenerator().addProvider(isServer, blocktags);
		event.getGenerator().addProvider(isServer, new ItemTagGenerator(packOutput, lookupProvider, blocktags.contentsGetter(), existingFileHelper));
		event.getGenerator().addProvider(isServer, new FluidTagGenerator(packOutput, lookupProvider, existingFileHelper));

		RegistryDataGenerator registryDataGenerator = new RegistryDataGenerator(packOutput, lookupProvider);
		event.getGenerator().addProvider(isServer, registryDataGenerator);
		event.getGenerator().addProvider(isServer, new DamageTypeTagGenerator(packOutput, registryDataGenerator.getRegistryProvider(), existingFileHelper));

		event.getGenerator().addProvider(isClient, new SpriteReferenceGenerator(packOutput, lookupProvider, existingFileHelper));
	}

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}

	public static Rarity getRarity() {
		return RISUS;
	}
}
