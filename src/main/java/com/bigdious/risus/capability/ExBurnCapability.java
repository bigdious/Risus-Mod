package com.bigdious.risus.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Objects;
import java.util.UUID;

public class ExBurnCapability implements ExBurn {

	private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("6e279bb5-440e-4498-ae26-01b7f0347e01");
	private LivingEntity host;
	private int lostHealth;

	@Override
	public void setEntity(LivingEntity entity) {
		this.host = entity;
	}

	@Override
	public void incrementHealth() {
		if (this.lostHealth > 0) {
			this.lostHealth = Math.max(0, this.lostHealth - 1);
			this.update();
		}
	}

	@Override
	public void decrementHealth() {
		this.lostHealth = this.lostHealth + 1;
		this.update();
	}

	public void update() {
		if (this.host != null && this.host.getAttribute(Attributes.MAX_HEALTH) != null) {
			if (Objects.requireNonNull(this.host.getAttribute(Attributes.MAX_HEALTH)).getModifier(HEALTH_MODIFIER_UUID) != null) {
				Objects.requireNonNull(this.host.getAttribute(Attributes.MAX_HEALTH)).removeModifier(HEALTH_MODIFIER_UUID);
			}
			Objects.requireNonNull(this.host.getAttribute(Attributes.MAX_HEALTH)).addPermanentModifier(new AttributeModifier(HEALTH_MODIFIER_UUID, "ExBurn Health Loss", -this.lostHealth, AttributeModifier.Operation.ADDITION));
			this.host.setHealth(this.host.getHealth());
		}
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("lostHealth", this.lostHealth);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		this.lostHealth = nbt.getInt("lostHealth");
		this.update();
	}
}
