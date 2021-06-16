package com.bigdious.risus.data;

import com.bigdious.risus.Risus;
import net.minecraft.data.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Risus.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenSetup {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		event.getGenerator().addProvider(new BlockModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(new ItemModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(new LootGenerator(event.getGenerator()));
		event.getGenerator().addProvider(new CraftingGenerator(event.getGenerator()));
		BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator(), event.getExistingFileHelper());
		event.getGenerator().addProvider(blocktags);
		event.getGenerator().addProvider(new ItemTagGenerator(event.getGenerator(), blocktags, event.getExistingFileHelper()));
	}
}
