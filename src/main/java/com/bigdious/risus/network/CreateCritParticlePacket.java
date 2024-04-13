package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record CreateCritParticlePacket(int entityId, int duration,
									   ParticleType<?> particle) implements CustomPacketPayload {

	public static final ResourceLocation ID = Risus.prefix("create_custom_crit_particle");

	public CreateCritParticlePacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readInt(), buf.readById(BuiltInRegistries.PARTICLE_TYPE));
	}

	@Override
	public void write(FriendlyByteBuf buf) {
		buf.writeInt(this.entityId);
		buf.writeInt(this.duration);
		buf.writeId(BuiltInRegistries.PARTICLE_TYPE, this.particle);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@SuppressWarnings("Convert2Lambda")
	public static void handle(CreateCritParticlePacket message, PlayPayloadContext context) {
		context.workHandler().execute(new Runnable() {
			@Override
			public void run() {
				Entity entity = context.level().orElseThrow().getEntity(message.entityId);
				if (entity != null) {
					Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, (ParticleOptions) message.particle, message.duration);
				}
			}
		});
	}
}
