package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.entity.creatures.BabySpider;
import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
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

	@Override
	protected Item getDefaultItem() {
		return RisusItems.EGG_SAC.get();
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level().isClientSide() && result.getType() != HitResult.Type.MISS) {
			((ServerLevel) this.level()).sendParticles(ParticleTypes.ITEM_COBWEB, this.getRandomX(0.5), this.getY(), this.getRandomZ(0.5), 7, 0, 0, 0, 0);
			this.level().playSound(this, this.blockPosition(), RisusSoundEvents.EGG_SAC_BREAK.get(), SoundSource.NEUTRAL, 1, 1);
			for (int i = 0; i < 4; i++) {
				BabySpider babySpider = RisusEntities.BABY_SPIDER.get().create(this.level());
				babySpider.moveTo(this.getRandomX(0.5), this.getY(), this.getRandomZ(0.5), 0.0F, 0.0F);
				this.level().addFreshEntity(babySpider);
			}
			this.discard();
		}
	}
}
