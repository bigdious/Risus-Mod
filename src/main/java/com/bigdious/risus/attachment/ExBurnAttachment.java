package com.bigdious.risus.attachment;

import com.bigdious.risus.Risus;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Objects;
import java.util.UUID;

public class ExBurnAttachment {

	public static final Codec<ExBurnAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					Codec.INT.fieldOf("lost_health").forGetter(o -> o.lostHealth))
			.apply(instance, ExBurnAttachment::new));


	private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("6e279bb5-440e-4498-ae26-01b7f0347e01");
	private int lostHealth;

	public ExBurnAttachment() {
		this(0);
	}

	private ExBurnAttachment(int lostHealth) {
		this.lostHealth = lostHealth;
	}

	public void incrementHealth(LivingEntity entity) {
		if (this.lostHealth > 0) {
			this.lostHealth = Math.max(0, this.lostHealth - 1);
		}
		this.update(entity);
	}

	public void decrementHealth(LivingEntity entity) {
		this.lostHealth = this.lostHealth + 1;
		this.update(entity);
	}

	public void update(LivingEntity entity) {
		if (entity.getAttribute(Attributes.MAX_HEALTH) != null) {
			if (Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH)).getModifier(Risus.prefix("exburn_health_loss")) != null) {
				Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH)).removeModifier(Risus.prefix("exburn_health_loss"));
			}
			Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH)).addPermanentModifier(new AttributeModifier(Risus.prefix("exburn_health_loss"),  -this.lostHealth, AttributeModifier.Operation.ADD_VALUE));
			entity.setHealth(entity.getHealth());
		}
	}
}

