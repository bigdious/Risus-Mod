package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.DisplayNotchBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class DisplayNotchBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {
	protected ItemStack item = ItemStack.EMPTY;
	public boolean glowing;
	public boolean stand;
	public boolean rotate;
	public int ticks;

	public DisplayNotchBlockEntity(BlockPos pos, BlockState state) {
		super(RisusBlockEntities.DISPLAY_NOTCH.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, DisplayNotchBlockEntity entity) {
		if (entity.rotate) {
			entity.ticks++;
		} else {
			entity.ticks = 0;
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		if (this.item != null && !this.item.isEmpty()) {
			tag.put("item", this.item.save(registries));
		}
		tag.putInt("ticks", this.ticks);
		tag.putBoolean("rotate", this.rotate);
		tag.putBoolean("glowing", this.glowing);
		tag.putBoolean("stand", this.stand);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		this.item = ItemStack.parse(registries, tag.getCompound("item")).orElse(ItemStack.EMPTY);
		this.ticks = tag.getInt("ticks");
		super.loadAdditional(tag, registries);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return this.saveWithoutMetadata(registries);
	}

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public ItemStack getTheItem() {
		return this.item;
	}

	@Override
	public void setTheItem(ItemStack item) {
		this.item = item;
		this.setChanged();
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public BlockEntity getContainerBlockEntity() {
		return this;
	}

	public boolean handleBEInteractions(ItemStack stack, Level level, BlockPos pos, BlockState state) {
		if (stack.is(Items.GLOW_INK_SAC) && !this.glowing) {
			this.glowing = true;
			this.setChanged();
			return true;
		} else if (stack.is(Tags.Items.DYES)) {
			DyeColor color = DyeColor.getColor(stack);
			if (color != null) {
				var oldBe = level.getBlockEntity(pos);
				level.setBlockAndUpdate(pos, DisplayNotchBlock.NOTCH_BY_DYE.get(color).get().withPropertiesOf(state));
				level.setBlockEntity(oldBe);
				this.setChanged();
				return true;
			}
		} else if (stack.is(Items.REDSTONE_TORCH)) {
			this.rotate = !this.rotate;
			this.setChanged();
			return true;
		} else if (stack.is(ItemTags.AXES)) {
			this.stand = !this.stand;
			this.setChanged();
			return true;
		}
		return false;
	}
}
