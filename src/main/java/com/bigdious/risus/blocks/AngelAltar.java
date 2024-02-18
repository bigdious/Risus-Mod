package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;


public class AngelAltar extends Block {
	public AngelAltar(Properties properties) {
		super(properties);

	}
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		ItemStack held = player.getItemInHand(hand);
		if (held.is(Items.TOTEM_OF_UNDYING)) {
			if(level.getBlockState(pos.above()).is(Blocks.AIR)) {
				if (level.isClientSide) {
					this.explode(level, pos);
				}

			}
		}
		return super.use(state, level, pos, player, hand, result);
	}
	private void explode(Level level,BlockPos pos) {
		Vec3 vec3 = pos.getCenter().add(0,2,0);
		level.explode((Entity)null, level.damageSources().badRespawnPointExplosion(vec3), null, vec3, 5.0F, true, Level.ExplosionInteraction.BLOCK);
	}
}
