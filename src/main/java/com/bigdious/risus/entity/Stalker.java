package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusMobType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Stalker extends Monster {
	public Stalker(EntityType<? extends Monster> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 0.0D)
				.add(Attributes.FOLLOW_RANGE, 30.0D);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(4, new FloatGoal(this));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.85D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.2D));
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	@Override
	public void playerTouch(Player player) {
		if (this.isAlive()) {
			int i = 5;
			if (this.level().getDifficulty() == Difficulty.NORMAL) {
				i = 10;
			} else if (this.level().getDifficulty() == Difficulty.HARD) {
				i = 15;
			}
			player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, i * 20, 0, false, false, true), this);
			player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, i * 20, 0, false, false, true), this);
		}
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (super.doHurtTarget(entity)) {
			if (entity instanceof LivingEntity living) {
				int i = 3;
				if (this.level().getDifficulty() == Difficulty.NORMAL) {
					i = 5;
				} else if (this.level().getDifficulty() == Difficulty.HARD) {
					i = 8;
				}
				living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, i * 20, 0), this);
				living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, i * 20, 0), this);
			}
		}
		return false;
	}

	public RisusMobType getRisusMobType() {
		return RisusMobType.OFFSPING;
	}

	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		return SoundEvents.CREEPER_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.CREEPER_DEATH;
	}
}
