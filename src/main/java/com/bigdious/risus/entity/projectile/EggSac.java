package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.entity.BabySpider;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class EggSac extends ThrowableItemProjectile {
	public EggSac(EntityType<? extends EggSac> type, Level level) {
		super(type, level);
	}
	public EggSac(Level level, LivingEntity thrower) {
		super(RisusEntities.EGG_SAC.get(), thrower, level);
	}

	public EggSac(Level level, double x, double y, double z) {
		super(RisusEntities.EGG_SAC.get(), x, y, z, level);
	}

	protected Item getDefaultItem() {
		return RisusItems.EGG_SAC.get();
	}


	protected void onHit(HitResult result) {
		if (!this.level().isClientSide()) {
			if (result.getType() == HitResult.Type.ENTITY || result.getType() == HitResult.Type.BLOCK) {
				for (int i = 0; i<4; i++) {
					BabySpider babySpider = RisusEntities.BABY_SPIDER.get().create(this.level());
					babySpider.moveTo(this.getRandomX(0.5), this.getY(), this.getRandomZ(0.5), 0.0F, 0.0F);
					this.level().addFreshEntity(babySpider);
					}
				this.remove(RemovalReason.KILLED);
			}
		}
	}
}
