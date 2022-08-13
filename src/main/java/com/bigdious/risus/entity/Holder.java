package com.bigdious.risus.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Holder extends Monster {

	private boolean shouldAvoidPlayer;
	@Nullable
	private UUID avoidedPlayerUUID;

	public Holder(EntityType<? extends Monster> type, Level level) {
		super(type, level);
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
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 64.0F));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, entity -> this.avoidedPlayerUUID != null && Objects.equals(this.avoidedPlayerUUID, entity.getUUID()), 8.0F, 1.5D, 1.75D, entity -> this.shouldAvoidPlayer));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, living -> {
			List<Monster> nearbyMonsters = this.getLevel().getEntitiesOfClass(Monster.class, this.getBoundingBox().inflate(12.0D), monster -> !(monster instanceof Holder));
			return this.getMainHandItem().isEmpty() && nearbyMonsters.isEmpty();
		}));
	}

	@Override
	public float getStepHeight() {
		return 1.0F;
	}

	@Override
	public boolean canPickUpLoot() {
		return this.getMainHandItem().isEmpty() && this.hurtTime <= 0;
	}

	//Only pick up 1 item
	@Override
	protected void pickUpItem(ItemEntity item) {
		ItemStack itemstack = item.getItem();
		if (this.canHoldItem(itemstack)) {
			int i = itemstack.getCount();
			if (i > 1) {
				this.dropItemStack(itemstack.split(i - 1));
			}

			this.onItemPickup(item);
			this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
			this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
			this.take(item, itemstack.getCount());
			item.discard();
			if (item.getThrower() != null) {
				this.shouldAvoidPlayer = true;
				this.avoidedPlayerUUID = item.getThrower();
			}
		}
	}

	private void dropItemStack(ItemStack stack) {
		ItemEntity itementity = new ItemEntity(this.getLevel(), this.getX(), this.getY(), this.getZ(), stack);
		this.getLevel().addFreshEntity(itementity);
	}

	@Override
	public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand hand) {
		if (!player.getItemInHand(hand).isEmpty() && this.getMainHandItem().isEmpty()) {
			this.setItemInHand(InteractionHand.MAIN_HAND, player.getItemInHand(hand).split(1));
			return InteractionResult.sidedSuccess(this.getLevel().isClientSide());
		}
		return super.interactAt(player, vec3, hand);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean flag = super.hurt(source, amount);
		if (flag && this.hasItemInSlot(EquipmentSlot.MAINHAND) && source.getEntity() instanceof Player) {
			this.spawnAtLocation(this.getMainHandItem());
			this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
			this.shouldAvoidPlayer = false;
			this.avoidedPlayerUUID = null;
		}
		return flag;
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean flag = super.doHurtTarget(entity);
		if (flag && entity instanceof LivingEntity living && this.getMainHandItem().isEmpty() && !living.getMainHandItem().isEmpty()) {
			this.setItemInHand(InteractionHand.MAIN_HAND, living.getMainHandItem().split(1));
			if(living instanceof Player player) {
				this.avoidedPlayerUUID = player.getUUID();
				this.shouldAvoidPlayer = true;
			}
		}
		return flag;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("AvoidingPlayer", this.shouldAvoidPlayer);
		if (this.avoidedPlayerUUID != null) {
			tag.putUUID("AvoidingUUID", this.avoidedPlayerUUID);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.shouldAvoidPlayer = tag.getBoolean("AvoidingPlayer");
		if (tag.contains("AvoidingUUID")) {
			this.avoidedPlayerUUID = tag.getUUID("AvoidingUUID");
		}
	}
}
