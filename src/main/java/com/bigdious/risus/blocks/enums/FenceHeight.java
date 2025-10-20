package com.bigdious.risus.blocks.enums;

import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public enum FenceHeight implements StringRepresentable {
	BASE("base"),
	DOWN("down");

	private final String name;

	private FenceHeight(String name) {
		this.name = name;
	}

	public String toString() {
		return this.getSerializedName();
	}

	public String getSerializedName() {
		return this.name;
	}

	public static final EnumProperty<FenceHeight> HEIGHT_TYPE = EnumProperty.create("height_type", FenceHeight.class);
}
