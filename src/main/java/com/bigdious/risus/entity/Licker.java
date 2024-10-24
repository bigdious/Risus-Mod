package com.bigdious.risus.entity;

import com.bigdious.risus.entity.ai.LickerAi;
import com.bigdious.risus.init.RisusEntities;
import com.mojang.serialization.Dynamic;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;
import java.util.Optional;

public class Licker extends Monster {
	public AnimationState idle = new AnimationState();
	public AnimationState walk = new AnimationState();
	public AnimationState run = new AnimationState();
	public AnimationState attack = new AnimationState();
	public Licker(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}
	public static AttributeSupplier.Builder attributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 32.0)
			.add(Attributes.MOVEMENT_SPEED, 0.5)
			.add(Attributes.FOLLOW_RANGE, 24)
			.add(Attributes.ATTACK_DAMAGE, 5.0);
	}
	protected Brain<?> makeBrain(Dynamic<?> p_312201_) {
		return LickerAi.makeBrain(this, this.brainProvider().makeBrain(p_312201_));
	}

	public Brain<Licker> getBrain() {
		return super.getBrain();
	}

	protected Brain.Provider<Licker> brainProvider() {
		return Brain.provider(LickerAi.MEMORY_TYPES, LickerAi.SENSOR_TYPES);
	}
	public Optional<LivingEntity> getHurtBy() {
		return this.getBrain().getMemory(MemoryModuleType.HURT_BY).map(DamageSource::getEntity).filter((p_321467_) -> {
			return p_321467_ instanceof LivingEntity;
		}).map((p_321468_) -> {
			return (LivingEntity)p_321468_;
		});
	}
	@Contract("null->false")
	public boolean canTargetEntity(@Nullable Entity p_219386_) {
		if (p_219386_ instanceof LivingEntity livingentity) {
			if (this.level() == p_219386_.level() && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(p_219386_) && !this.isAlliedTo(p_219386_) && livingentity.getType() != EntityType.ARMOR_STAND && livingentity.getType() != RisusEntities.LICKER.get() && !livingentity.isInvulnerable() && !livingentity.isDeadOrDying() && this.level().getWorldBorder().isWithinBounds(livingentity.getBoundingBox())) {
				return true;
			}
		}

		return false;
	}
	protected void customServerAiStep() {
		this.level().getProfiler().push("lickerBrain");
		this.getBrain().tick((ServerLevel)this.level(), this);
		this.level().getProfiler().popPush("lickerActivityUpdate");
		LickerAi.updateActivity(this);
		this.level().getProfiler().pop();
		super.customServerAiStep();
	}
	protected void sendDebugPackets() {
		super.sendDebugPackets();
		DebugPackets.sendEntityBrain(this);
	}
	@Nullable
	public LivingEntity getTarget() {
		return this.getTargetFromBrain();
	}
}
