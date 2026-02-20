package com.bigdious.risus.worldgen.structures;

import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import com.bigdious.risus.network.MusicPacketFromServer;
import com.bigdious.risus.network.UnyieldingTotemPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashSet;
import java.util.UUID;

public class StructureBehaviors {
	private static final HashSet<UUID> PLAYERS_IN_MORK_STRUCTURE = new HashSet<>();
	private static final HashSet<UUID> PLAYERS_IN_FEIGR_STRUCTURE = new HashSet<>();

	public static void runStructureEffects(ServerPlayer serverPlayer) {
		StructureManager structureManager = ((ServerLevel)serverPlayer.level()).structureManager();
		StructureStart morkStructure = structureManager.getStructureWithPieceAt(serverPlayer.blockPosition(), RisusTags.Structures.MORK_MUSIC_STRUCTURES);
		StructureStart feigrStructure = structureManager.getStructureWithPieceAt(serverPlayer.blockPosition(), RisusTags.Structures.FEIGR_MUSIC_STRUCTURES);
		if (morkStructure.isValid()) {
			if (serverPlayer.tickCount % 60 == 20) {
				if (!PLAYERS_IN_MORK_STRUCTURE.contains(serverPlayer.getUUID())) {
					PLAYERS_IN_MORK_STRUCTURE.add(serverPlayer.getUUID());
				}
				if (morkStructure.isValid()) {
					PacketDistributor.sendToPlayer(serverPlayer, new MusicPacketFromServer( RisusSoundEvents.MUSIC_DISC_MORK.get().getLocation(), true));
				}
			}
		} else if (serverPlayer.tickCount % 60 == 20 && PLAYERS_IN_MORK_STRUCTURE.contains(serverPlayer.getUUID())) {
			PacketDistributor.sendToPlayer(serverPlayer, new MusicPacketFromServer(RisusSoundEvents.MUSIC_DISC_MORK.get().getLocation(), false));
			PLAYERS_IN_MORK_STRUCTURE.remove(serverPlayer.getUUID());
		}

		if (feigrStructure.isValid()) {
			if (serverPlayer.tickCount % 60 == 20) {
				if (!PLAYERS_IN_FEIGR_STRUCTURE.contains(serverPlayer.getUUID())) {
					PLAYERS_IN_FEIGR_STRUCTURE.add(serverPlayer.getUUID());
				}
				if (feigrStructure.isValid()) {
					PacketDistributor.sendToPlayer(serverPlayer, new MusicPacketFromServer( RisusSoundEvents.MUSIC_DISC_FEIGR.get().getLocation(), true));
				}
			}
		} else if (serverPlayer.tickCount % 60 == 20 && PLAYERS_IN_FEIGR_STRUCTURE.contains(serverPlayer.getUUID())) {
			PacketDistributor.sendToPlayer(serverPlayer, new MusicPacketFromServer(RisusSoundEvents.MUSIC_DISC_FEIGR.get().getLocation(), false));
			PLAYERS_IN_FEIGR_STRUCTURE.remove(serverPlayer.getUUID());
		}
	}

}
