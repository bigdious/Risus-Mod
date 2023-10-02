package com.bigdious.risus.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.w3c.dom.Attr;

import java.util.UUID;

public class ExBurnEffect extends MobEffect {
	public ExBurnEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
			for (int i = 0; i < 10000000; i++){
				if (i/10000==1 || i/1000==2 || i/1000==3 || i/1000==4 || i/1000==5 || i/1000==6 || i/1000==7 || i/1000==8 || i/1000==9 || i/1000==10 || i/1000==11 || i/1000==12 || i/1000==13 || i/1000==14){
					if (entity.getAttribute(Attributes.MAX_HEALTH).getModifier(UUID.fromString("6e279bb5-440e-4498-ae26-01b7f0347e01"))==null){
				entity.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(new AttributeModifier(UUID.fromString("6e279bb5-440e-4498-ae26-01b7f0347e01"), "ExBurn", -i/1000, AttributeModifier.Operation.ADDITION));}
				};
				if (i==999 || i==1999 || i==2999 || i==3999 || i==4999 || i==5999 || i==6999 || i==7999 || i==8999 || i==9999 || i==10999 || i==11999 || i==12999 || i==13999){
					if (entity.getAttribute(Attributes.MAX_HEALTH).getModifier(UUID.fromString("6e279bb5-440e-4498-ae26-01b7f0347e01"))!=null){
						entity.getAttribute(Attributes.MAX_HEALTH).removeModifier(UUID.fromString("6e279bb5-440e-4498-ae26-01b7f0347e01"));
					}
				}

			}
	}
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return duration % 10 == 0;
	}


}
