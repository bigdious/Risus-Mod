package com.bigdious.risus;

import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.entity.Holder;
import com.bigdious.risus.entity.Maw;
import com.bigdious.risus.entity.Weaver;
import com.bigdious.risus.init.RisusEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Risus.MODID, bus = Bus.MOD)
public class RisusEvents {

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(RisusEntities.ANGEL.get(), Angel.attributes().build());
		event.put(RisusEntities.HOLDER.get(), Holder.attributes().build());
		event.put(RisusEntities.MAW.get(), Maw.attributes().build());
		event.put(RisusEntities.WEAVER.get(), Weaver.attributes().build());
	}
}
