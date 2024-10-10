package com.bigdious.risus.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class TransientQuestionMark extends QuestionMark{
	private int life;
	public TransientQuestionMark(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}
	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 1024.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.0D)
			.add(Attributes.ATTACK_DAMAGE, 10.0D);
	}
	@Override
	public void tick() {
		super.tick();
		if (!this.level().isClientSide) {
			this.tickDespawn();
		}
	}
	protected void tickDespawn() {
		if (getRandom().nextInt(3)>1) {
			this.life++;
		}
		if (this.life >= 60) {
			this.discard();
		}
	}
	@Override
	public void addAdditionalSaveData(CompoundTag pCompound) {
		super.addAdditionalSaveData(pCompound);
		pCompound.putShort("life", (short) this.life);
	}
	@Override
	public void readAdditionalSaveData(CompoundTag pCompound) {
		super.readAdditionalSaveData(pCompound);
		this.life = pCompound.getShort("life");
	}
}
