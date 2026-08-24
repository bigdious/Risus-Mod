package com.bigdious.risus.entity.creatures.pets;

import com.bigdious.risus.Risus;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.entity.goals.MonsterFollowOwnerGoal;
import com.bigdious.risus.init.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Holder extends TamableMonster implements EmptyBucketable {
	protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID;
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Holder.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> GREED = SynchedEntityData.defineId(Holder.class, EntityDataSerializers.BOOLEAN);
	private static final String TAG_GREED = "GREED";
	boolean isGreed;
	private boolean shouldAvoidEntity;
	@Nullable
	private UUID avoidedEntityUUID;
	@Nullable
	private UUID OwnerUUID;

	public Holder(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 0;
	}

	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 1.0D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(6, new MonsterFollowOwnerGoal(this, 1.0D, 5.0F, 2.0F));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, LivingEntity.class, 64.0F));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, entity -> this.avoidedEntityUUID != null && Objects.equals(this.avoidedEntityUUID, entity.getUUID()), 8.0F, 1.5D, 1.75D, entity -> this.shouldAvoidEntity));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class , true, living ->
			this.getMainHandItem().isEmpty() && !living.getMainHandItem().isEmpty() && !living.getMainHandItem().is(Items.BUCKET) && (RisusConfig.holdersStealFromMonsters && this.isGreed ? !(living.getType().is(RisusTags.Entities.CANT_BE_STOLEN_FROM)) : living instanceof Player)
		));
	}

	@Override
	public void tick() {
		if (this.tickCount % 40 == 0){
			if (this.getTarget() != null) {
				if (this.getTarget().getMainHandItem().isEmpty() || this.getTarget().getMainHandItem().is(Items.BUCKET)) this.setTarget(null);
			}
		}
		super.tick();
	}

	@Override
	public boolean canSwimInFluidType(FluidType type) {
		if (type == RisusFluids.BLOOD_FLUID_TYPE.get()) {
			return false;
		} else {
			return super.canSwimInFluidType(type);
		}
	}

	@Nullable
	@Override
	public UUID getOwnerUUID() {
		return this.OwnerUUID;
	}


	//let's not pick up anything for now, it causes weird AI and potential item deletion
//	@Override
//	public boolean canPickUpLoot() {
//		return this.getMainHandItem().isEmpty() && this.hurtTime <= 0;
//	}
//

	//Only pick up 1 item
//	@Override
//	protected void pickUpItem(ItemEntity item) {
//		ItemStack itemstack = item.getItem();
//		if (this.canHoldItem(itemstack)) {
//			int i = itemstack.getCount();
//			if (i > 1) {
//				this.dropItemStack(itemstack.split(i - 1));
//			}
//
//			this.onItemPickup(item);
//			this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
//			this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
//			this.take(item, itemstack.getCount());
//			item.discard();
//			var thrower = item.getOwner();
//			if (thrower != null) {
//				this.shouldAvoidEntity = true;
//				this.avoidedEntityUUID = thrower.getUUID();
//			}
//		}
//	}

	private void dropItemStack(ItemStack stack) {
		ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), stack);
		this.level().addFreshEntity(itementity);
	}

	@Override
	public void checkDespawn() {
		if (this.getMainHandItem().isEmpty()) {
			super.checkDespawn();
		}
	}

	//let's keep this commented, might be reused in future
//	@Override
//	public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand hand) {
//		if (!player.getItemInHand(hand).isEmpty() && this.getMainHandItem().isEmpty()) {
//			this.setItemInHand(InteractionHand.MAIN_HAND, player.getItemInHand(hand).split(1));
//			return InteractionResult.sidedSuccess(this.level().isClientSide());
//		}
//		return super.interactAt(player, vec3, hand);
//	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean flag = super.hurt(source, amount);
		if (flag && this.hasItemInSlot(EquipmentSlot.MAINHAND) && source.getEntity() instanceof LivingEntity) {
			this.spawnAtLocation(this.getMainHandItem());
			this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
				this.shouldAvoidEntity = false;
				this.avoidedEntityUUID = null;
				this.OwnerUUID = null;
				if (this.getAttribute(Attributes.ATTACK_DAMAGE).getModifier(Risus.prefix("holder_friendly")) != null) {
					this.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(Risus.prefix("holder_friendly"));
				}
				if (this.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(Risus.prefix("holder_friendly_speed")) != null) {
					this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(Risus.prefix("holder_friendly_speed"));
				}
		}
		return flag;
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean flag = super.doHurtTarget(entity);
		if (flag && entity instanceof LivingEntity living && this.getMainHandItem().isEmpty() && !living.getMainHandItem().isEmpty() && !this.isDeadOrDying()) {
			this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, living.getMainHandItem().split(1));
			entity.level().playSound(null, entity.getOnPos(), RisusSoundEvents.CHEEKY_LAUGH.get(), SoundSource.HOSTILE, 1, 1);
				if (this.getMainHandItem().is(RisusItems.ORGANIC_MATTER.get())) {
					this.shouldAvoidEntity = false;
					this.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(Risus.prefix("holder_friendly"),  -3, AttributeModifier.Operation.ADD_VALUE));
					this.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(new AttributeModifier(Risus.prefix("holder_friendly_speed"), 1.8, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
					this.OwnerUUID = living.getUUID();
				} else {
					this.avoidedEntityUUID = living.getUUID();
					this.shouldAvoidEntity = true;
				}
		}
		return flag;
	}


	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("AvoidingEntity", this.shouldAvoidEntity);
		tag.putBoolean("FromBucket", this.fromBucket());
		tag.putBoolean("GREED", this.isGreed);
		if (this.avoidedEntityUUID != null) {
			tag.putUUID("AvoidingUUID", this.avoidedEntityUUID);
		}
		if (this.OwnerUUID != null) {
			tag.putUUID("OwnerUUID", this.OwnerUUID);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.shouldAvoidEntity = tag.getBoolean("AvoidingEntity");
		this.setFromBucket(tag.getBoolean("FromBucket"));
		if (tag.contains("AvoidingUUID")) {
			this.avoidedEntityUUID = tag.getUUID("AvoidingUUID");
		}
		if (tag.contains("OwnerUUID")) {
			this.OwnerUUID = tag.getUUID("OwnerUUID");
		}
		if (tag.contains("GREED", 99)) {
			this.isGreed = tag.getBoolean("GREED");
		}
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_OWNERUUID_ID, Optional.empty());
		builder.define(FROM_BUCKET, false);
		builder.define(GREED, false);
	}

	static {
		DATA_OWNERUUID_ID = SynchedEntityData.defineId(Holder.class, EntityDataSerializers.OPTIONAL_UUID);
	}

	public void setCustomName(@javax.annotation.Nullable Component name) {
		super.setCustomName(name);
		if (!this.isGreed && name != null && name.getString().equals("GREED")) {
			this.isGreed = true;
		}

	}
	@Override
	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBucket();
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !this.fromBucket() && !this.hasCustomName();
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		return EmptyBucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
	}

	@Override
	public boolean fromBucket() {
		return this.entityData.get(FROM_BUCKET);
	}

	@Override
	public void setFromBucket(boolean fromBucket) {
		this.entityData.set(FROM_BUCKET, fromBucket);
	}

	@Override
	public void saveToBucketTag(ItemStack stack) {
		Bucketable.saveDefaultDataToBucketTag(this, stack);
	}

	@Override
	public void loadFromBucketTag(CompoundTag tag) {
		Bucketable.loadDefaultDataFromBucketTag(this, tag);
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(RisusItems.HOLDER_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL;
	}
}
