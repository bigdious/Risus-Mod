package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.config.RisusConfig;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SyncCommonConfigPacket(RisusConfig.SpinningSource drop) implements CustomPacketPayload {
	public static final Type<SyncCommonConfigPacket> TYPE = new Type<>(Risus.prefix("sync_common_config"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SyncCommonConfigPacket> STREAM_CODEC = StreamCodec.composite(RisusConfig.SpinningSource.STREAM_CODEC, SyncCommonConfigPacket::drop, SyncCommonConfigPacket::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(SyncCommonConfigPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			RisusConfig.spinningSource = message.drop();
		});
	}
}
