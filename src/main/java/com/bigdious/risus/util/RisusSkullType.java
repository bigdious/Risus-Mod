package com.bigdious.risus.util;

import net.minecraft.world.level.block.SkullBlock;

import java.util.Locale;

public enum RisusSkullType implements SkullBlock.Type {
	BLOODWYRM;

	@Override
	public String getSerializedName() {
		return this.name().toLowerCase(Locale.ROOT);
	}
}
