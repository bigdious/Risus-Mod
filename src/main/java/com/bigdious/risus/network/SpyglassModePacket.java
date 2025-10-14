package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosCapability;
import vazkii.patchouli.api.PatchouliAPI;

public class SpyglassModePacket implements CustomPacketPayload {
	public static final Type<SpyglassModePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "spyglass_mode"));
	public static final SpyglassModePacket INSTANCE = new SpyglassModePacket();
	public static final StreamCodec<ByteBuf, SpyglassModePacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
			});
		}
	}

}
