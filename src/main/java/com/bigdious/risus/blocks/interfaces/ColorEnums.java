package com.bigdious.risus.blocks.interfaces;

import com.bigdious.risus.blocks.DisplayNotchBlock;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Locale;
public interface ColorEnums {
	enum ColorEnum implements StringRepresentable {
		BLACK, GRAY, LIGHT_GRAY, WHITE, BROWN, RED, ORANGE, YELLOW, LIME, GREEN, CYAN, LIGHT_BLUE, BLUE, PURPLE, MAGENTA, PINK;

		public static final EnumProperty<ColorEnum> COLOR = EnumProperty.create("color", ColorEnum.class);

		@Override
		public String getSerializedName() {
			return this.name().toLowerCase(Locale.ROOT);
		}
	}
}
