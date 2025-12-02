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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosCapability;
import vazkii.patchouli.api.PatchouliAPI;

public class ScopePacket implements CustomPacketPayload {
	public static final Type<ScopePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "scope"));
	public static final ScopePacket INSTANCE = new ScopePacket();
	public static final StreamCodec<ByteBuf, ScopePacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				if (player.isScoping()) {
					player.level().playSound(null, player.getOnPos().above(), SoundEvents.SPYGLASS_USE, SoundSource.NEUTRAL);
				} else {
					player.level().playSound(null, player.getOnPos().above(), SoundEvents.SPYGLASS_STOP_USING, SoundSource.NEUTRAL);
				}
			});
		}
	}

}
