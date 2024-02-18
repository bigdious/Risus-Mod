package com.bigdious.risus.blocks;

import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusEntities;
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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class AngelAltar extends Block {
	public AngelAltar(Properties properties) {
		super(properties);

	}
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 13.0D, 14.0D, 13.0D);
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		ItemStack held = player.getItemInHand(hand);
		if (held.is(Items.TOTEM_OF_UNDYING)) {
			if(level.getBlockState(pos.above()).is(Blocks.AIR)) {
				if (level.isClientSide) {
					this.explode(level, pos);
				}
				Angel summonedAngel = RisusEntities.ANGEL.get().create(level);
				summonedAngel.moveTo(pos.getCenter().x, pos.getCenter().y+1, pos.getCenter().z, 0.0F, 0.0F);
				level.addFreshEntity(summonedAngel);
				player.getMainHandItem().shrink(1);
				return InteractionResult.SUCCESS;
				}

			}

		return super.use(state, level, pos, player, hand, result);
	}
	private void explode(Level level,BlockPos pos) {
		Vec3 vec3 = pos.getCenter().add(0,2,0);
		level.explode((Entity)null, level.damageSources().magic(), null, vec3, 50.0F, true, Level.ExplosionInteraction.BLOCK);
	}
}
