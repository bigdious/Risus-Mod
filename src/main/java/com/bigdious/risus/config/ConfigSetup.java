package com.bigdious.risus.config;

import com.bigdious.risus.network.SyncCommonConfigPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigSetup {
	private static final ModConfigSpec CLIENT_SPEC;
	private static final RisusClientConfig CLIENT_CONFIG;
	private static final ModConfigSpec COMMON_SPEC;
	private static final RisusCommonConfig COMMON_CONFIG;

	static {
		{
			final Pair<RisusCommonConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(RisusCommonConfig::new);
			ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC = specPair.getRight());
			COMMON_CONFIG = specPair.getLeft();
		}
		{
			final Pair<RisusClientConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(RisusClientConfig::new);
			ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC = specPair.getRight());
			CLIENT_CONFIG = specPair.getLeft();
		}

	}

	public static void loadConfigs(ModConfigEvent.Loading event) {
		if (event.getConfig().getSpec() == CLIENT_SPEC) {
			RisusConfig.rebakeClientOptions(CLIENT_CONFIG);
		} else if (event.getConfig().getSpec() == COMMON_SPEC) {
			RisusConfig.rebakeCommonOptions(COMMON_CONFIG);
		}
	}

	public static void reloadConfigs(ModConfigEvent.Reloading event) {
		if (event.getConfig().getSpec() == CLIENT_SPEC) {
			RisusConfig.rebakeClientOptions(CLIENT_CONFIG);
		} else if (event.getConfig().getSpec() == ConfigSetup.COMMON_SPEC) {
			RisusConfig.rebakeCommonOptions(COMMON_CONFIG);
		}
	}

	public static void syncConfigOnLogin(PlayerEvent.PlayerLoggedInEvent event) {
		MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
		if (server != null && server.isDedicatedServer() && event.getEntity() instanceof ServerPlayer player) {
			PacketDistributor.sendToPlayer(player, new SyncCommonConfigPacket(RisusConfig.spinningSource));
		}
	}
}
