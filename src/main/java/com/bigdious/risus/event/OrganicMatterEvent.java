package com.bigdious.risus.event;

import com.bigdious.risus.blocks.OrganicMatterableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jetbrains.annotations.Nullable;

public class OrganicMatterEvent extends Event implements ICancellableEvent {
	@Nullable
	private final Player player;
	private final Level level;
	private final BlockPos pos;
	private final BlockState state;
	private final ItemStack stack;
	private final boolean isValidOrganicMatterTarget;
	private boolean isSuccess = false;

	public OrganicMatterEvent(@Nullable Player player, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		this.player = player;
		this.level = level;
		this.pos = pos;
		this.state = state;
		this.stack = stack;
		this.isValidOrganicMatterTarget = state.getBlock() instanceof OrganicMatterableBlock organicmatterable && organicmatterable.isValidOrganicMatterTarget(level, pos, state);
	}
	@Nullable
	public Player getPlayer() {
		return this.player;
	}
	public Level getLevel() {
		return this.level;
	}
	public BlockPos getPos() {
		return this.pos;
	}

	public BlockState getState() {
		return this.state;
	}

	public ItemStack getStack() {
		return this.stack;
	}

	public boolean isValidOrganicMatterTarget() {
		return this.isValidOrganicMatterTarget();
	}

	public void setSuccessful(boolean success) {
		this.isSuccess = success;
		this.setCanceled(true);
	}
	public boolean isSuccessful() {
		return this.isSuccess;
	}
	@Override
	public void setCanceled(boolean canceled) {
		ICancellableEvent.super.setCanceled(canceled);
	}
}
