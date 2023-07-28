package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Risus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenSetup {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {

		event.getGenerator().addProvider(true, new AdvancementGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(true, new BlockModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(true, new ItemModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(true, new LootGenerator(event.getGenerator()));
		event.getGenerator().addProvider(true, new CraftingGenerator(event.getGenerator()));
		BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator(), event.getExistingFileHelper());
		event.getGenerator().addProvider(true, blocktags);
		event.getGenerator().addProvider(true, new ItemTagGenerator(event.getGenerator(), blocktags, event.getExistingFileHelper()));

	}
}
