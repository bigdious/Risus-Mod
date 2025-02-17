package com.bigdious.risus.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Litter extends TamableAnimal implements NeutralMob {


	private BlockState blockState;

	public Litter(EntityType<? extends TamableAnimal> entityType, Level level, @Nullable UUID ownerUUID) {
		super(entityType, level);
		this.setOwnerUUID(ownerUUID);
		blockState = null;
	}

	public Litter(EntityType<Litter> litterEntityType, Level level) {
		super(litterEntityType, level);
		blockState = null;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.put("BlockState", NbtUtils.writeBlockState(this.blockState));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("BlockState", 10)) {
			this.blockState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), tag.getCompound("BlockState"));
		} else {
			this.blockState = Blocks.STONE.defaultBlockState();
		}
	}


	public void setBlockState(BlockState state) {
		this.blockState = state;
	}

	public BlockState getBlockState() {
		return this.blockState;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return TamableAnimal.createLivingAttributes()
			.add(Attributes.MAX_HEALTH, 20.0)  // Set a reasonable health value
			.add(Attributes.MOVEMENT_SPEED, 0.3)  // Adjust speed
			.add(Attributes.ATTACK_DAMAGE, 4.0)  // If applicable
			.add(Attributes.FOLLOW_RANGE, 32.0);  // Optional
	}





	@Override
	public boolean isFood(ItemStack itemStack) {
		return false;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return null;
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return 0;
	}

	@Override
	public void setRemainingPersistentAngerTime(int i) {

	}

	@Nullable
	@Override
	public UUID getPersistentAngerTarget() {
		return null;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable UUID uuid) {

	}

	@Override
	public void startPersistentAngerTimer() {

	}
}
