package com.bigdious.risus.network;


import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.DisplayNotchBlock;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public record SyncRisusConfigPacket(Map<ResourceLocation, DisplayNotchBlock> notches) implements CustomPacketPayload {
	public static final Type<SyncRisusConfigPacket> TYPE = new Type<>(Risus.prefix("sync_risus_configs"));

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
