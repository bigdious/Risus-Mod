package com.bigdious.risus.blocks;

import com.bigdious.risus.init.RisusMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class BloodFluidBlock extends LiquidBlock {
	public BloodFluidBlock(FlowingFluid supplier, Properties properties) {
		super(supplier, properties);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity.isAlive() && entity instanceof LivingEntity livingEntity) {
//			if (livingEntity.getType().is(RisusTags.Entities.IMMUNE_TO_BLOOD))
//				return;
			if (livingEntity.getAttribute(Attributes.MAX_HEALTH) != null && livingEntity.getAttribute(Attributes.MAX_HEALTH).getValue()>livingEntity.getHealth()) {
			livingEntity.addEffect(new MobEffectInstance(RisusMobEffects.BLOODCLOGGED, 40, (int)livingEntity.getMaxHealth()-(int)livingEntity.getHealth()-1, false, false, true));
			}
		}
	}

}
