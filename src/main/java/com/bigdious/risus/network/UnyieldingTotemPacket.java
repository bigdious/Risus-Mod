package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UnyieldingTotemPacket (ItemStack totem, ResourceKey<SoundEvent> event) implements CustomPacketPayload {

	public static final Type<UnyieldingTotemPacket> TYPE = new Type<>(Risus.prefix("totem_unyielding"));
	public static final StreamCodec<RegistryFriendlyByteBuf, UnyieldingTotemPacket> STREAM_CODEC = CustomPacketPayload.codec(UnyieldingTotemPacket::write, UnyieldingTotemPacket::new);

	public UnyieldingTotemPacket(RegistryFriendlyByteBuf buf) {
		this(ItemStack.STREAM_CODEC.decode(buf), buf.readResourceKey(Registries.SOUND_EVENT));
	}



	public void write(RegistryFriendlyByteBuf buf) {
		ItemStack.STREAM_CODEC.encode(buf, this.totem());
		buf.writeResourceKey(this.event());
	}

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
					Player player = ctx.player();
					ClientLevel level = (ClientLevel) player.level();
					Entity camera = Minecraft.getInstance().getCameraEntity();

						Minecraft.getInstance().gameRenderer.displayItemActivation(packet.totem());
						//prefer the camera pos over the player as the player position isnt quite synced to the client yet
						Minecraft.getInstance().particleEngine.createTrackingEmitter(camera != null ? camera : player, new ItemParticleOption(ParticleTypes.ITEM, packet.totem()), 20);

					SoundEvent event = BuiltInRegistries.SOUND_EVENT.get(packet.event());
					if (camera != null && event != null) {
						level.playLocalSound(camera.getX(), camera.getY(), camera.getZ(), event, player.getSoundSource(), 1.5F, 1.0F, false);
					}
				}
			});
		}
	}
}
