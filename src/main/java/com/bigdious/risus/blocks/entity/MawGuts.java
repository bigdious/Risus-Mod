package com.bigdious.risus.blocks.entity;

import net.minecraft.world.Container;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;

public interface MawGuts extends Container {
	AABB SUCK_AABB = Block.box(0.0, 11.0, 0.0, 16.0, 32.0, 16.0).toAabbs().get(0);

	default AABB getSuckAabb() {
		return SUCK_AABB;
	}

	double getLevelX();

	double getLevelY();

	double getLevelZ();

	boolean isGridAligned();
}
