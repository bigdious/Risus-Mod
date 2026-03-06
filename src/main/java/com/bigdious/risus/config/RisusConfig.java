package com.bigdious.risus.config;

import com.bigdious.risus.network.SyncCommonConfigPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ByIdMap;
import net.neoforged.neoforge.common.TranslatableEnum;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.Locale;
import java.util.function.IntFunction;

public class RisusConfig {

	// -- COMMON CONFIG --
	public static SpinningSource spinningSource = SpinningSource.SIGNAL;
	public static boolean holdersStealFromMonsters = true;
	public static boolean illegalLitters = false;
	public static boolean stripperWorksOnMobArmor = true;
	public static boolean customWeaponAnims = true;
	public static boolean canonExBurn = false;
	public static boolean reverseHornsPlayerBehavior = false;
	public static boolean loverSpreads = true;
	public static boolean hornsPrioritizeTeams = false;

	static void rebakeCommonOptions(RisusCommonConfig config) {
		spinningSource = config.spinningSource.get();
		holdersStealFromMonsters = config.holdersStealFromMonsters.get();
		illegalLitters = config.illegalLitters.get();
		stripperWorksOnMobArmor = config.stripperWorksOnMobArmor.get();
		customWeaponAnims = config.customWeaponAnims.get();
		canonExBurn = config.canonExBurn.get();
		reverseHornsPlayerBehavior = config.reverseHornsPlayerBehavior.get();
		loverSpreads = config.loverSpreads.get();
		hornsPrioritizeTeams = config.hornsPrioritizeTeams.get();

		MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
		if (server != null && server.isDedicatedServer()) {
			PacketDistributor.sendToAllPlayers(new SyncCommonConfigPacket(spinningSource));
		}
	}

	public enum SpinningSource implements TranslatableEnum {
		SIGNAL,
		TORCH_ITEM;

		public static final IntFunction<SpinningSource> BY_ID = ByIdMap.continuous(SpinningSource::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
		public static final StreamCodec<ByteBuf, SpinningSource> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, SpinningSource::ordinal);

		@Override
		public Component getTranslatedName() {
			return Component.translatable("config.risus.spinning_source." + this.name().toLowerCase(Locale.ROOT));
		}
	}
}
