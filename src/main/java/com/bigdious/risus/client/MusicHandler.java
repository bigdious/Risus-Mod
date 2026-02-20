package com.bigdious.risus.client;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.mixin.SoundEngineAccessor;
import com.bigdious.risus.mixin.SoundManagerAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

public class MusicHandler {
	//based on Bumblezone's code used for structure specific music
	//https://github.com/TelepathicGrunt/Bumblezone/blob/1.21-MDG/common/src/main/java/com/telepathicgrunt/the_bumblezone/client/MusicHandler.java

	public static class MusicFader {
		public final SoundInstance music;
		public final Predicate<Minecraft> stopOtherMusic;
		public final int counterStart;
		public boolean fadeIn;
		public int counter;

		public MusicFader(SoundInstance music, Predicate<Minecraft> stopOtherMusic, int counterStart, boolean fadeIn) {
			this.music = music;
			this.stopOtherMusic = stopOtherMusic;
			this.counterStart = counterStart;
			this.fadeIn = fadeIn;

			this.counter = this.counterStart;
		}
	}

	private static final Map<ResourceLocation, MusicFader> MUSIC_FADERS = new HashMap<>();
	private static SoundInstance STRUCTURE_MUSIC = null;
	private static final ResourceLocation BIOME_MUSIC = ResourceLocation.fromNamespaceAndPath(Risus.MODID, "biome_music");
	public static boolean RISUS_MUSIC_PLAYING = false;

	public static void tickMusicFader() {
		Minecraft minecraftClient = Minecraft.getInstance();

		Iterator<Map.Entry<ResourceLocation, MusicFader>> iterator = MUSIC_FADERS.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<ResourceLocation, MusicFader> entry = iterator.next();
			MusicFader musicFader = entry.getValue();
			if (musicFader.fadeIn) {
				boolean success = musicFader.stopOtherMusic.test(minecraftClient);
				if (!success) {
					minecraftClient.getSoundManager().stop(musicFader.music);
					iterator.remove();
					continue;
				}

				boolean isPlaying = isMusicPlaying(minecraftClient, musicFader.music);
				if (!isPlaying) {
					minecraftClient.getSoundManager().play(musicFader.music);
				}
				else if (musicFader.counter == 0) {
					continue;
				}

				float newVolume = Math.max(0.01f, 1 - ((float)musicFader.counter / musicFader.counterStart));
				setMusicVolume(minecraftClient, musicFader.music, newVolume);
			}
			else {
				if (musicFader.counter == 0) {
					minecraftClient.getSoundManager().stop(musicFader.music);
					iterator.remove();
					continue;
				}

				float newVolume = ((float)musicFader.counter / musicFader.counterStart);
				setMusicVolume(minecraftClient, musicFader.music, newVolume);
			}

			if (musicFader.counter > 0) {
				musicFader.counter--;
			}
		}
	}

	// CLIENT-SIDED
	public static void playStopStructureMusic(Player entity, ResourceLocation resourceLocation, boolean play) {
		Minecraft minecraftClient = Minecraft.getInstance();

		SoundEvent SOUNDEVENT = RisusSoundEvents.MUSIC_DISC_MORK.get().getLocation().compareTo(resourceLocation) == 0 ? RisusSoundEvents.MUSIC_DISC_MORK.get() :
			RisusSoundEvents.MUSIC_DISC_FEIGR.get().getLocation().compareTo(resourceLocation) == 0 ? RisusSoundEvents.MUSIC_DISC_FEIGR.get() : null;

		if (SOUNDEVENT == null) {
			return;
		}

		if (SOUNDEVENT.getLocation().compareTo(resourceLocation) != 0 == play && STRUCTURE_MUSIC != null) {
			RISUS_MUSIC_PLAYING = false;
			addMusicFade(STRUCTURE_MUSIC, 200, false, (m) -> false);
		}
		else if (SOUNDEVENT.getLocation().compareTo(resourceLocation) == 0 && play && (STRUCTURE_MUSIC == null || !isMusicPlaying(minecraftClient, STRUCTURE_MUSIC))) {
			if (entity == minecraftClient.player && !isMusicPlaying(minecraftClient, STRUCTURE_MUSIC)) {
				STRUCTURE_MUSIC = SimpleSoundInstance.forMusic(SOUNDEVENT);

				RISUS_MUSIC_PLAYING = true;
				minecraftClient.getSoundManager().play(STRUCTURE_MUSIC);
				setMusicVolume(minecraftClient, STRUCTURE_MUSIC, 0.01f);
				addMusicFade(STRUCTURE_MUSIC, 200, true, (m) -> {
					m.getSoundManager().stop(SoundEvents.MUSIC_CREATIVE.key().location(), SoundSource.MUSIC);
					m.getSoundManager().stop(BIOME_MUSIC, SoundSource.MUSIC);
					m.getSoundManager().stop(SoundEvents.MUSIC_CREATIVE.key().location(), SoundSource.MUSIC);
					m.getSoundManager().stop(SoundEvents.MUSIC_GAME.key().location(), SoundSource.MUSIC);

					return true;
				});
			}
		}
	}

	private static void addMusicFade(SoundInstance soundInstance, int counterStart, boolean fadeIn, Predicate<Minecraft> stopOtherMusic) {
		if (MUSIC_FADERS.containsKey(soundInstance.getLocation())) {
			MusicFader musicFader = MUSIC_FADERS.get(soundInstance.getLocation());
			boolean originalFadeIn = musicFader.fadeIn;
			if (originalFadeIn != fadeIn) {
				musicFader.fadeIn = fadeIn;
				musicFader.counter = musicFader.counterStart - musicFader.counter;
			}
		}
		else {
			MUSIC_FADERS.put(soundInstance.getLocation(), new MusicFader(soundInstance, stopOtherMusic, counterStart, fadeIn));
		}
	}

	private static void setMusicVolume(Minecraft minecraftClient, SoundInstance soundInstance, float volume) {
		float playerSetVolume = minecraftClient.options.getSoundSourceVolume(soundInstance.getSource());

		ChannelAccess.ChannelHandle channelHandle = ((SoundEngineAccessor)((SoundManagerAccessor) minecraftClient.getSoundManager())
			.risus$getSoundEngine())
			.risus$getInstanceToChannel()
			.get(soundInstance);

		if (channelHandle != null) {
			channelHandle.execute((channel -> channel.setVolume(Math.min(volume, playerSetVolume))));
		}
	}

	private static boolean isMusicPlaying(Minecraft minecraftClient, SoundInstance soundInstance) {
		return minecraftClient.getSoundManager().isActive(soundInstance);
	}

}