package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Holder extends Monster {

	private boolean shouldAvoidPlayer;
	@Nullable
	private UUID avoidedPlayerUUID;
	private final List<ServerPlayer> hurtBy = new ArrayList<>();

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
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, LivingEntity.class, 64.0F));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, entity -> this.avoidedPlayerUUID != null && Objects.equals(this.avoidedPlayerUUID, entity.getUUID()), 8.0F, 1.5D, 1.75D, entity -> this.shouldAvoidPlayer));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, living -> {
				List<Monster> nearbyMonsters = this.level().getEntitiesOfClass(Monster.class, this.getBoundingBox().inflate(12.0D), monster -> !(monster instanceof Holder));
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
			var thrower = item.getOwner();
			if (thrower != null) {
				this.shouldAvoidPlayer = true;
				this.avoidedPlayerUUID = thrower.getUUID();
			}
		}
	}

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

	@Override
	public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand hand) {
		if (!player.getItemInHand(hand).isEmpty() && this.getMainHandItem().isEmpty()) {
			this.setItemInHand(InteractionHand.MAIN_HAND, player.getItemInHand(hand).split(1));
			return InteractionResult.sidedSuccess(this.level().isClientSide());
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
			if(this.getAttribute(Attributes.ATTACK_DAMAGE).getModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee")) != null){
				this.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee"));}
			if(this.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(UUID.fromString("c4a665d0-2fb4-4ba3-b3e9-8dc7bcdcb92d")) != null){
					this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(UUID.fromString("c4a665d0-2fb4-4ba3-b3e9-8dc7bcdcb92d"));}
		}
		return flag;
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean flag = super.doHurtTarget(entity);
		if (flag && entity instanceof LivingEntity living && this.getMainHandItem().isEmpty() && !living.getMainHandItem().isEmpty()) {
			this.setItemInHand(InteractionHand.MAIN_HAND, living.getMainHandItem().split(1));
			if (living instanceof Player player) {
				if (this.getMainHandItem().is(RisusItems.ORGANIC_MATTER.get())){
				this.shouldAvoidPlayer = false;
				this.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(new AttributeModifier(UUID.fromString("c4bd2a6a-67cd-4c8f-911d-559ac181b5ee"), "Holder friendly", -3, AttributeModifier.Operation.ADDITION));
				this.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(new AttributeModifier(UUID.fromString("c4a665d0-2fb4-4ba3-b3e9-8dc7bcdcb92d"), "Holderpet fast", 2, AttributeModifier.Operation.MULTIPLY_BASE));}
				else {this.avoidedPlayerUUID = player.getUUID();
					this.shouldAvoidPlayer = true;
					}
			}}
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
