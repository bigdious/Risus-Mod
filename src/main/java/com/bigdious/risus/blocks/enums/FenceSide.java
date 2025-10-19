package com.bigdious.risus.blocks.enums;

import net.minecraft.util.StringRepresentable;

public enum FenceSide implements StringRepresentable {
	NONE("none"),
	LOW("low"),
	DOWN_LOW("down_low"),
	TALL("tall"),
	DOWN_TALL("down_tall");

	private final String name;

	private FenceSide(String name) {
		this.name = name;
	}

	public String toString() {
		return this.getSerializedName();
	}

	public String getSerializedName() {
		return this.name;
	}
}
