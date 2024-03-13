package com.bigdious.risus.util;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.particle.BloodBitParticle;
import com.bigdious.risus.particle.BloodParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = Risus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RisusParticleUtil {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		Minecraft.getInstance().particleEngine.register(RisusParticles.BLOOD.get(), BloodParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(RisusParticles.BLOOD_BIT.get(), BloodBitParticle.Factory::new);
	}
}
