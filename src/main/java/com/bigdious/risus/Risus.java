package com.bigdious.risus;

import com.bigdious.risus.client.RisusClientEvents;
import com.bigdious.risus.data.*;
import com.bigdious.risus.event.RisusEvents;
import com.bigdious.risus.init.*;
import com.bigdious.risus.init.RisusDataMaps;
import com.bigdious.risus.network.CreateCritParticlePacket;
import com.bigdious.risus.network.UnyieldingTotemPacket;
import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Mod(Risus.MODID)
public class Risus {
	public static final String MODID = "risus";

	public static final Supplier<GameRules.Key<GameRules.BooleanValue>> HOLDERS_STEAL_FROM_MONSTERS = Suppliers.memoize(() -> GameRules.register("holdersStealFromMonsters", GameRules.Category.MOBS, GameRules.BooleanValue.create(true)));
	public static final Supplier<GameRules.Key<GameRules.BooleanValue>> ILLEGAL_LITTERS = Suppliers.memoize(() -> GameRules.register("illegalLitters", GameRules.Category.MOBS, GameRules.BooleanValue.create(false)));

	public static final Logger LOGGER = LogManager.getLogger();

	public Risus(IEventBus bus, Dist dist) {
		Util.backgroundExecutor().execute(HOLDERS_STEAL_FROM_MONSTERS::get);
		Util.backgroundExecutor().execute(ILLEGAL_LITTERS::get);
		RisusBlockEntities.BLOCK_ENTITIES.register(bus);
		RisusBlocks.BLOCKS.register(bus);
		RisusDataAttachments.ATTACHMENT_TYPES.register(bus);
		RisusArmorMaterials.ARMOR_MATERIALS.register(bus);
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
		RisusStructures.STRUCTURES.register(bus);
		RisusDataComponents.COMPONENTS.register(bus);
		RisusAdvancements.TRIGGERS.register(bus);

		bus.addListener(this::registerPackets);
		bus.addListener(this::registerTypes);
		bus.addListener(this::gatherData);
		bus.addListener(RegisterDataMapTypesEvent.class, event -> event.register(RisusDataMaps.LOVER_CONVERSION));
		RisusEvents.initEvents(bus);

		if (dist.isClient()) {
			RisusClientEvents.initEvents(bus);
		}
	}

	public void registerPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();
		registrar.playToClient(CreateCritParticlePacket.TYPE, CreateCritParticlePacket.STREAM_CODEC, CreateCritParticlePacket::handle);
		registrar.playToClient(UnyieldingTotemPacket.TYPE, UnyieldingTotemPacket.STREAM_CODEC, UnyieldingTotemPacket::handle);
	}

	public void registerTypes(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.MOB_SPAWNER, RisusBlocks.FLESHY_SPAWNER.get());
	}

	private void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(packOutput, event.getLookupProvider());
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		boolean isClient = event.includeClient();
		generator.addProvider(isClient, new BlockModelGenerator(packOutput, existingFileHelper));
		generator.addProvider(isClient, new ItemModelGenerator(packOutput, existingFileHelper));
		generator.addProvider(isClient, new SoundDefinitionGenerator(packOutput, existingFileHelper));
		generator.addProvider(isClient, new SpriteReferenceGenerator(packOutput, event.getLookupProvider(), existingFileHelper));
		generator.addProvider(isClient, new LangGenerator(packOutput));

		boolean isServer = event.includeServer();
		RegistryDataGenerator registryDataGenerator = new RegistryDataGenerator(packOutput, datapackProvider.getRegistryProvider());
		var lookupProvider = registryDataGenerator.getRegistryProvider();
		generator.addProvider(isServer, registryDataGenerator);
		generator.addProvider(isServer, new RisusAdvancementProvider(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(isServer, new StructureUpdater("structures", packOutput, existingFileHelper));
		generator.addProvider(isServer, new LootGenerator(packOutput, lookupProvider));
		generator.addProvider(isServer, new CraftingGenerator(packOutput, lookupProvider));
		generator.addProvider(isServer, new DataMapGenerator(packOutput, lookupProvider));

		var blocktags = new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
		generator.addProvider(isServer, blocktags);
		generator.addProvider(isServer, new BannerPatternTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(isServer, new BiomeTagsGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(isServer, new DamageTypeTagGenerator(packOutput, registryDataGenerator.getRegistryProvider(), existingFileHelper));
		generator.addProvider(isServer, new EnchantmentTagGenerator(packOutput, registryDataGenerator.getRegistryProvider(), existingFileHelper));
		generator.addProvider(isServer, new EntityTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(isServer, new FluidTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(isServer, new ItemTagGenerator(packOutput, lookupProvider, blocktags.contentsGetter(), existingFileHelper));
	}

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}

}
