package com.bigdious.risus.effects;

import com.bigdious.risus.capability.ExBurnCapability;
import com.bigdious.risus.init.RisusCapabilities;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.w3c.dom.Attr;

import java.util.UUID;

public class ExBurnEffect extends MobEffect{
	public ExBurnEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (!entity.level().isClientSide()) {
			if (entity.getAttribute(Attributes.MAX_HEALTH) != null && entity.getAttribute(Attributes.MAX_HEALTH).getValue() > 6.0D) {
				entity.getCapability(RisusCapabilities.EX_BURN).ifPresent(ExBurnCapability::decrementHealth);
			}
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return duration % 100 == 0;
	}


}
