package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.CrystallizedBondsBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class PoppingBondknotBlockEntity extends BlockEntity {

	private int popTimer = 0;
	private boolean popping = false;
	public Direction popDir;

	public PoppingBondknotBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.POPPING_BONDKNOT.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, PoppingBondknotBlockEntity te) {
		while (te.popDir == null) {
			Direction dir = Direction.values()[level.getRandom().nextInt(Direction.values().length)];
			if (level.getBlockState(pos.relative(dir)).isAir()) {
				te.popDir = dir;
			}
		}

		if (level.getRandom().nextInt(50) == 0 && te.popDir != null && !te.popping && state.isAir()) {
			te.popping = true;
		}

		if (te.popping) {
			if (level.isClientSide()) te.particles();
			te.popTimer++;
		}

		if (te.popTimer > 100) {
			if (!level.isClientSide()) te.createCrystal();
			te.popping = false;
			te.popTimer = 0;
		}
	}

	private void particles() {
		if (this.popDir != null && Objects.requireNonNull(this.getLevel()).getBlockState(this.getBlockPos().relative(this.popDir)).isAir()) {
			BlockPos crystal = this.getBlockPos().relative(this.popDir);
			this.getLevel().addParticle(DustParticleOptions.REDSTONE, crystal.getX() + this.getLevel().getRandom().nextFloat(), crystal.getY() + this.getLevel().getRandom().nextFloat(), crystal.getZ() + this.getLevel().getRandom().nextFloat(), 0.5D, 0.5D, 0.5D);
		}
	}

	private void createCrystal() {
		if (this.popDir != null && Objects.requireNonNull(this.getLevel()).getBlockState(this.getBlockPos().relative(this.popDir)).isAir()) {
			this.getLevel().setBlockAndUpdate(this.getBlockPos().relative(this.popDir), RisusBlocks.CRYSTALLIZED_BONDS.get().defaultBlockState().setValue(CrystallizedBondsBlock.FACING, this.popDir));
			this.getLevel().playSound(null, this.getBlockPos().relative(this.popDir), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0f);
		}
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		if (nbt.contains("direction")) this.popDir = Direction.byName(nbt.getString("direction"));
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		if (this.popDir != null) tag.putString("direction", this.popDir.name());
	}
}
