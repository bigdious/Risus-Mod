package com.bigdious.risus.network;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.MusicHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record MusicPacketFromServer (ResourceLocation musicRL, boolean play) implements CustomPacketPayload {
	//based on Bumblezone's code used for structure specific music
	//https://github.com/TelepathicGrunt/Bumblezone/blob/1.21-MDG/common/src/main/java/com/telepathicgrunt/the_bumblezone/packets/MusicPacketFromServer.java

	public static final Type<MusicPacketFromServer> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Risus.MODID,"music_packet_from_server"));
	public static final StreamCodec<RegistryFriendlyByteBuf, MusicPacketFromServer> STREAM_CODEC = CustomPacketPayload.codec(MusicPacketFromServer::write, MusicPacketFromServer::new);

	public MusicPacketFromServer(RegistryFriendlyByteBuf buf) {
		this(ResourceLocation.STREAM_CODEC.decode(buf), buf.readBoolean());
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void write(RegistryFriendlyByteBuf buf) {
		ResourceLocation.STREAM_CODEC.encode(buf, this.musicRL());
		buf.writeBoolean(this.play());
	}

	public static void handle(MusicPacketFromServer packet, IPayloadContext context) {
		context.enqueueWork(() -> {
			Player player = Minecraft.getInstance().player;
			MusicHandler.playStopStructureMusic(player, packet.musicRL(), packet.play()
//				&& BzClientConfigs.playSempiternalSanctumMusic
			);
//			MusicHandler.playStopEssenceEventMusic(player, packet.musicRL(), packet.play()
////				&& BzClientConfigs.playSempiternalSanctumMusic
//			);
		});
	}


//	private static final class Handler implements ClientboundPacketType<MusicPacketFromServer> {
//
//		@Override
//		public void encode(MusicPacketFromServer message, RegistryFriendlyByteBuf buffer) {
//			buffer.writeResourceLocation(message.musicRL());
//			buffer.writeBoolean(message.play());
//		}
//
//		@Override
//		public MusicPacketFromServer decode(RegistryFriendlyByteBuf buffer) {
//			return new MusicPacketFromServer(buffer.readResourceLocation(), buffer.readBoolean());
//		}
//
//		@Override
//		public Runnable handle(MusicPacketFromServer message) {
//			return () -> MusicPacketFromServerHandleBody.handle(message);
//		}
//
//		@Override
//		public Class<MusicPacketFromServer> type() {
//			return MusicPacketFromServer.class;
//		}
//
//		@Override
//		public ResourceLocation id() {
//			return ID;
//		}
//	}
}

