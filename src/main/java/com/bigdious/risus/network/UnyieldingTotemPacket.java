package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UnyieldingTotemPacket(ItemStack totem) implements CustomPacketPayload {

	public static final Type<UnyieldingTotemPacket> TYPE = new Type<>(Risus.prefix("totem_unyielding"));
	public static final StreamCodec<RegistryFriendlyByteBuf, UnyieldingTotemPacket> STREAM_CODEC = StreamCodec.composite(ItemStack.STREAM_CODEC, UnyieldingTotemPacket::totem, UnyieldingTotemPacket::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	@SuppressWarnings("Convert2Lambda")
	public static void handle(UnyieldingTotemPacket packet, IPayloadContext ctx) {
		if (ctx.flow().isClientbound()) {
			ctx.enqueueWork(new Runnable() {
				@Override
				public void run() {
					Minecraft.getInstance().gameRenderer.displayItemActivation(packet.totem());
					Minecraft.getInstance().particleEngine.createTrackingEmitter(ctx.player(), new ItemParticleOption(ParticleTypes.ITEM, packet.totem()), 20);
				}
			});
		}
	}
}
