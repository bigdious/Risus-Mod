package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.blocks.WeavingMechanismBlock;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WeavingMechanismBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem{
	protected ItemStack item = ItemStack.EMPTY;
	AABB SUCK_AABB = Block.box(-16.0F, 0.0F, -16.0F, 32.0F, 32.0F, 32.0F).toAabbs().get(0);
	public int xpStored;
	public boolean isWeaving = false;
	private int weavingCounter;
	private int xpCollectionCooldown;

	public WeavingMechanismBlockEntity(BlockPos pos, BlockState blockState) {
		super(RisusBlockEntities.WEAVING_MECHANISM.get(), pos, blockState);
	}
	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return false;
	}
	@Override
	public boolean canTakeItem(Container target, int slot, ItemStack stack) {
		return false;
	}

	public Direction getDirection() {
		return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
	}

	AABB getSuckAabb() {
		return SUCK_AABB;
	}

	public static void tick(Level level, BlockPos pos, BlockState state, WeavingMechanismBlockEntity weaver) {
		int weavingTime=100;
		if (weaver.xpStored > 12 && weaver.weavingCounter < 2) {
			if (weaver.item != ItemStack.EMPTY) {
				Direction direction = weaver.getBlockState().getValue(WeavingMechanismBlock.HORIZONTAL_FACING).getOpposite();
				Position position = pos.getCenter().add(0.7 * direction.getStepX() + Vec3.ZERO.x(), 0.7 * direction.getStepY() + Vec3.ZERO.y(), 0.7 * direction.getStepZ() + Vec3.ZERO.z());
				ItemStack itemstack = new ItemStack(RisusItems.MEMORY_CORE.get());
				DefaultDispenseItemBehavior.spawnItem(level, itemstack, 0, direction, position);
				weaver.item = ItemStack.EMPTY;
				level.playSound(null, pos, RisusSoundEvents.ITEM_POPS_OUT.get(), SoundSource.BLOCKS, 1.0F, 1.2F);
				weaver.updateBlock();
			}
			weaver.isWeaving = true;
			weaver.updateBlock();
		}

		if (weaver.isWeaving && !level.hasNeighborSignal(pos)) {
			weaver.weavingCounter++;
			if (weaver.weavingCounter % 21 == 0 || weaver.weavingCounter == 2)
				level.playSound(null, pos, RisusSoundEvents.WEAVING.get(), SoundSource.BLOCKS, 0.3F, 0.3F);
			if (level.isClientSide()) {
				Direction direction = weaver.getBlockState().getValue(WeavingMechanismBlock.HORIZONTAL_FACING);
				switch (direction) {
					case NORTH -> level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.MEMORY_CORE.get())), ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().x(), ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().y()-0.1, pos.getCenter().z()+0.25, 0, 0,0);
					case SOUTH -> level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.MEMORY_CORE.get())), ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().x(), ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().y()-0.1, pos.getCenter().z()-0.25, 0, 0,0);
					case EAST -> level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.MEMORY_CORE.get())), pos.getCenter().x()-0.25, ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().y()-0.1, ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().z(), 0, 0,0);
					default -> level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.MEMORY_CORE.get())), pos.getCenter().x()+0.25, ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().y()-0.1, ((double) level.getRandom().nextIntBetweenInclusive(-1, 1) /10)+pos.getCenter().z(), 0, 0, 0);
				}
			}
		}

		if (weaver.xpCollectionCooldown > 1 && weaver.xpStored < 1500 && !level.hasNeighborSignal(pos)) {
			weaver.xpCollectionCooldown--;
		}

		if (weaver.xpCollectionCooldown < 2 && weaver.xpStored < 1500) {
			List<ExperienceOrb> xpOrbs = level.getEntitiesOfClass(ExperienceOrb.class, weaver.getSuckAabb().move(weaver.getBlockPos().getX(), weaver.getBlockPos().getY(), weaver.getBlockPos().getZ()), EntitySelector.ENTITY_STILL_ALIVE);
			for (ExperienceOrb xpOrb : xpOrbs) {
				if (weaver.xpStored < 1500) {
					weaver.xpStored = weaver.xpStored+xpOrb.getValue();
					if (level instanceof ServerLevel serverLevel) {
						serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.MEMORY_CORE.get())), xpOrb.getX(), xpOrb.getY()+0.1, xpOrb.getZ(), 1, 0, 0, 0, 0);
					}
					xpOrb.remove(Entity.RemovalReason.DISCARDED);
				}
			}
			weaver.xpCollectionCooldown=50;
		}

		if (weaver.weavingCounter > weavingTime) {
			weaver.weavingCounter = 1;
			if (weaver.isWeaving) {
				ItemStack core = new ItemStack(RisusItems.MEMORY_CORE.get());
				weaver.item = core;
				weaver.isWeaving = false;
				weaver.weavingCounter = 0;
				weaver.xpStored = weaver.xpStored-12;
				weaver.setChanged();
			}
			weaver.updateBlock();
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		if (this.item != null && !this.item.isEmpty()) {
			tag.put("item", this.item.save(registries));
		}
		tag.putInt("xpStored", this.xpStored);
		tag.putBoolean("isWeaving", this.isWeaving);
		tag.putInt("counter", this.weavingCounter);
		tag.putInt("xpCollectionCooldown", this.xpCollectionCooldown);
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		if (tag.contains("item")) {
			this.item = ItemStack.parse(registries, tag.getCompound("item")).orElse(ItemStack.EMPTY);
		} else {
			this.item = ItemStack.EMPTY;
		}
		this.xpStored = tag.getInt("xpStored");
		this.weavingCounter = tag.getInt("counter");
		this.xpCollectionCooldown = tag.getInt("xpCollectionCooldown");
		this.isWeaving = tag.getBoolean("isWeaving");
		super.loadAdditional(tag, registries);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();
		if (this.item != null && !this.item.isEmpty()) {
			Tag reagentTag = this.item.save(registries);
			tag.put("item", reagentTag);
		}
		tag.putInt("counter", this.weavingCounter);
		tag.putInt("xpStored", this.xpStored);
		tag.putInt("xpCollectionCooldown", this.xpCollectionCooldown);
		tag.putBoolean("isWeaving", this.isWeaving);
		super.saveAdditional(tag, registries);
		return tag;
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider pRegistries) {
		super.onDataPacket(net, packet, pRegistries);
		this.handleUpdateTag(packet.getTag() == null ? new CompoundTag() : packet.getTag(), pRegistries);
	}

	@Override
	@Nullable
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public ItemStack splitTheItem(int amount) {
		return BlockContainerSingleItem.super.splitTheItem(amount);
	}

	@Override
	public ItemStack getTheItem() {
		return this.item;
	}

	public boolean canStartWeaving() {
		return this.getTheItem() == ItemStack.EMPTY;
	}

	@Override
	public void setTheItem(ItemStack item) {
		this.item = ItemStack.EMPTY;
		this.updateBlock();
	}

	public boolean updateBlock() {
		if (this.weavingCounter == 0) {
			this.weavingCounter = 1;
		}
		if (this.getLevel() != null) {
			BlockState state = this.getLevel().getBlockState(this.getBlockPos());
			this.getLevel().sendBlockUpdated(this.getBlockPos(), state, state, 2);
			this.setChanged();
			return true;
		}
		return false;
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public BlockEntity getContainerBlockEntity() {
		return this;
	}
}
