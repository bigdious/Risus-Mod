package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.inventory.recipe.AlterationRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class DisplayNotchBlockEntity extends BlockEntity implements WorldlyContainer {
	protected ItemStack item = ItemStack.EMPTY;
	public float rotationDegrees;
	public DisplayNotchBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.DISPLAY_NOTCH.get(), pos, state);
	}
	public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, E e) {
	}
	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		if (this.item != null) {
			CompoundTag reagentTag = new CompoundTag();
			this.item.save(reagentTag);
			tag.put("item", reagentTag);
		}
		tag.putFloat("itemRotation", this.rotationDegrees);
	}
	@Override
	public void load(CompoundTag tag) {
		this.item = ItemStack.of((CompoundTag) tag.get("item"));
		this.rotationDegrees = tag.getFloat("itemRotation");
		super.load(tag);
	}
	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		tag.putFloat("itemRotation", this.rotationDegrees);
		this.saveAdditional(tag);
		return tag;
	}
	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
		super.onDataPacket(net, packet);
		this.handleUpdateTag(packet.getTag() == null ? new CompoundTag() : packet.getTag());
	}
	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	public boolean updateBlock() {
		if (this.getLevel() != null) {
			BlockState state = this.getLevel().getBlockState(this.getBlockPos());
			this.getLevel().sendBlockUpdated(this.getBlockPos(), state, state, 2);
			this.setChanged();
			return true;
		}
		return false;
	}
	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return this.item.isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.item;
	}

	@Override
	public ItemStack removeItem(int slot, int count) {
		ItemStack stack = item.copy().split(count);
		item.shrink(count);
		this.updateBlock();
		return stack;
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_18951_) {
		return this.item;
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.item = stack;
		this.rotationDegrees = this.getLevel().getRandom().nextFloat() * 360.0F;
		this.updateBlock();
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public boolean stillValid(Player player) {
		return false;
	}

	@Override
	public void clearContent() {
		this.item = ItemStack.EMPTY;
	}

	@Nullable
	public ItemStack getInputItem() {
		return this.getItem(0);
	}

	public void setInputItem(ItemStack item) {
		this.setItem(0, item);
		this.setChanged();
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		return new int[]{0};
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
		return this.item.isEmpty();
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
		return direction == Direction.DOWN && !this.item.isEmpty();
	}
}