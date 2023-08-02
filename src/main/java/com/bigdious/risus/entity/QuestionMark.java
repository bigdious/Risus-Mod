package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusDamageSources;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class QuestionMark extends Monster {
	public QuestionMark(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1024.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.0D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.5F;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.setYRot(0.0F);
		this.yRotO = 0.0F;
		this.setYHeadRot(0.0F);
		this.yHeadRotO = 0.0F;
		this.setYBodyRot(0.0F);
		this.yBodyRotO = 0.0F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.AMBIENT_CAVE;
	}

	@Override
	public int getAmbientSoundInterval() {
		return 200;
	}

	@Override
	public boolean isNoGravity() {
		return true;
	}

	@Override
	public boolean canBeSeenAsEnemy() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public void knockback(double x, double y, double z) {
	}

	@Override
	public boolean removeWhenFarAway(double dist) {
		return false;
	}

	@Override
	public boolean attackable() {
		return false;
	}

	@Override
	public void playerTouch(Player player) {
		if (this.isAlive()) {
			if (this.hasLineOfSight(player) && player.hurt(RisusDamageSources.INEXISTENCE, (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue())) {
				this.doEnchantDamageEffects(this, player);
			}
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return source.isBypassInvul() && super.hurt(source, amount);
	}
}