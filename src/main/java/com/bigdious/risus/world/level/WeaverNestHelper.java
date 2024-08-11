package com.bigdious.risus.world.level;

import com.bigdious.risus.entity.Weaver;
import com.bigdious.risus.init.RisusEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

public class WeaverNestHelper {
	//this is so ducking stupid. Why is static fine when done through helper
	private int spawnDelay;
	public int maxNearbyEntities = 10;
	public int requiredPlayerRange = 64;
	public int spawnRange = 10;
	private boolean isNearPlayer(Level pLevel, BlockPos pPos) {
		return pLevel.hasNearbyAlivePlayer(
			(double)pPos.getX() + 0.5, (double)pPos.getY() + 0.5, (double)pPos.getZ() + 0.5, (double)this.requiredPlayerRange
		);
	}
	public void tick(BlockState state, ServerLevel level, BlockPos pos) {
		if(this.isNearPlayer(level, pos)){
			int k = level.getEntities(
					EntityTypeTest.forExactClass(Weaver.class),
					new AABB(
						(double)pos.getX(),
						(double)pos.getY(),
						(double)pos.getZ(),
						(double)(pos.getX() + 1),
						(double)(pos.getY() + 1),
						(double)(pos.getZ() + 1)
					)
						.inflate((double)this.spawnRange),
					EntitySelector.NO_SPECTATORS
				)
				.size();
			if (k >= this.maxNearbyEntities) {
				return;
			}
			if(spawnDelay>12000) {
				Weaver weaver = RisusEntities.WEAVER.get().create(level);
				weaver.moveTo(pos.getCenter().x, pos.getCenter().y, pos.getCenter().z, 0.0F, 0.0F);
				level.addFreshEntity(weaver);
				spawnDelay=0;
			} else {
				spawnDelay++;
			}
		}
	}
}
