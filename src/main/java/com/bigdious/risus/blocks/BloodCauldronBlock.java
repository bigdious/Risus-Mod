package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusCauldronInteractions;
import com.bigdious.risus.init.RisusMobEffects;
import com.bigdious.risus.init.RisusTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class BloodCauldronBlock extends AbstractCauldronBlock {

	public static final MapCodec<BloodCauldronBlock> CODEC = simpleCodec(BloodCauldronBlock::new);

	public BloodCauldronBlock(Properties properties) {
		super(properties, RisusCauldronInteractions.BLOOD);
	}

	@Override
	protected MapCodec<? extends AbstractCauldronBlock> codec() {
		return CODEC;
	}

	@Override
	public boolean isFull(BlockState state) {
		return true;
	}

	@Override
	protected double getContentHeight(BlockState state) {
		return 0.9375D;
	}

	@Override
	public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
		return 3;
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity.isAlive() && entity instanceof LivingEntity livingEntity && !(livingEntity.getType().is(RisusTags.Entities.OFFSPRINGS_AND_BELOVEDS))) {
			livingEntity.addEffect(new MobEffectInstance(RisusMobEffects.BLOODCLOGGED, 40, 0, false, false, true));
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
		return new ItemStack(Items.CAULDRON);
	}
}