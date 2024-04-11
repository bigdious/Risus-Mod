package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;


import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Risus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenSetup {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		PackOutput packOutput = event.getGenerator().getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		boolean isServer = event.includeServer();
		boolean isClient = event.includeClient();

		event.getGenerator().addProvider(isServer, new AdvancementProvider(packOutput, lookupProvider, existingFileHelper));
		event.getGenerator().addProvider(isClient, new BlockModelGenerator(packOutput, existingFileHelper));
		event.getGenerator().addProvider(isClient, new ItemModelGenerator(packOutput, existingFileHelper));
		event.getGenerator().addProvider(isServer, new LootGenerator(packOutput));
		event.getGenerator().addProvider(isServer, new CraftingGenerator(packOutput));
		var blocktags = new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
		event.getGenerator().addProvider(isServer, blocktags);
		event.getGenerator().addProvider(isServer, new ItemTagGenerator(packOutput, lookupProvider, blocktags.contentsGetter(), existingFileHelper));
		event.getGenerator().addProvider(isServer, new FluidTagGenerator(packOutput, lookupProvider, existingFileHelper));

		RegistryDataGenerator registryDataGenerator = new RegistryDataGenerator(packOutput, lookupProvider);
		event.getGenerator().addProvider(isServer, registryDataGenerator);
		event.getGenerator().addProvider(isServer, new DamageTypeTagGenerator(packOutput, registryDataGenerator.getRegistryProvider(), existingFileHelper));

		event.getGenerator().addProvider(isClient, new SpriteReferenceGenerator(packOutput, existingFileHelper));
	}
}
