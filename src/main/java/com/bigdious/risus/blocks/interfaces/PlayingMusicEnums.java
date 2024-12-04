package com.bigdious.risus.blocks.interfaces;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Locale;

public interface PlayingMusicEnums {
	enum PlayingMusicEnum implements StringRepresentable {
		RAK, MORK, FEIGR, REGN, NONE;

		public static final EnumProperty<PlayingMusicEnum> MUSIC_PLAYING = EnumProperty.create("music_playing", PlayingMusicEnum.class);

		@Override
		public String getSerializedName() {
			return this.name().toLowerCase(Locale.ROOT);
		}
	}
}
