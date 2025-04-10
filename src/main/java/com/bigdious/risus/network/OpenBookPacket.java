package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosCapability;
import vazkii.patchouli.api.PatchouliAPI;

public class OpenBookPacket implements CustomPacketPayload {
	public static final Type<OpenBookPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "open_book"));
	public static final OpenBookPacket INSTANCE = new OpenBookPacket();
	public static final StreamCodec<ByteBuf, OpenBookPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				if (ModList.get().isLoaded("curios")) {
					var handler = player.getCapability(CuriosCapability.INVENTORY);
					if (handler == null) return;
					var s = handler.findCurios(RisusItems.RESEARCHERS_NOTES.get());
					if (s.isEmpty())return;
					if (s.getFirst().stack().is(RisusItems.RESEARCHERS_NOTES.get()) && player instanceof ServerPlayer playerser) {
						PatchouliAPI.get().openBookGUI(playerser, BuiltInRegistries.ITEM.getKey(RisusItems.RESEARCHERS_NOTES.get()));
						return;
					}
				}
				for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
					ItemStack checkStack = player.getInventory().getItem(i);
					if (checkStack.is(RisusItems.RESEARCHERS_NOTES)) {
						if (player instanceof ServerPlayer playerser) {
							PatchouliAPI.get().openBookGUI(playerser, BuiltInRegistries.ITEM.getKey(RisusItems.RESEARCHERS_NOTES.get()));
							break;
						}
					}
				}
			});
		}
	}

}
