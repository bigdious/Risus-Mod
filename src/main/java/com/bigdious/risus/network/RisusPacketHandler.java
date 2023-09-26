package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class RisusPacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Risus.MODID, "channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SuppressWarnings("UnusedAssignment")
    public static void init() {
        int id = 0;
        CHANNEL.registerMessage(id++, CreateCritParticlePacket.class, CreateCritParticlePacket::encode, CreateCritParticlePacket::new, CreateCritParticlePacket.Handler::onMessage);
    }
}
