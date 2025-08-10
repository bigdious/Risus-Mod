package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.entity.Stool;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusEntities;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SummonGreatnessPacket implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<SummonGreatnessPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID, "summon_greatness"));
	public static final SummonGreatnessPacket INSTANCE = new SummonGreatnessPacket();
	public static final StreamCodec<ByteBuf, SummonGreatnessPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(() -> {
				Player player = ctx.player();
				Level level = player.level();
				if (player.onGround() && player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT) != null && player.getItemBySlot(EquipmentSlot.FEET).get(RisusDataComponents.ABILITY_VARIANT).equals("great_stool")) {
					Stool great_stool = new Stool(RisusEntities.GREAT_STOOL.get(), level);
					great_stool.setPos(player.getX(), player.getY(), player.getZ());
					great_stool.setOwnerUUID(player.getUUID());
					level.addFreshEntity(player);
					player.moveTo(player.getX(), player.getY()+1.5, player.getZ());
				}
			});
		}
	}
}
