package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class BloodwyrmBreathEntity extends AbstractArrow {

	public BloodwyrmBreathEntity(EntityType<BloodwyrmBreathEntity> type, Level level) {
		super(type, level);
	}
	public BloodwyrmBreathEntity(Level world, LivingEntity living)
	{
		super(RisusEntities.BLOODWYRM_BREATH.get(), living, world);
		this.pickup = Pickup.DISALLOWED;
	}

	public void handleEntityEvent(byte p_37402_) {
		if (p_37402_ == 3) {
			for(int i = 0; i < 8; ++i) {
				this.level().addParticle(RisusParticles.JOYFLAME.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@Override
	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		entity.hurt(this.damageSources().thrown(this, this.getOwner()), 1);
	}

	@Override
	protected ItemStack getPickupItem() {
		return null;
	}
}

