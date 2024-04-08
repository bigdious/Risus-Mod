package com.bigdious.risus.entity;

import com.bigdious.risus.init.RisusMobType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.monster.Enemy;

import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Lover extends FlyingMob implements Enemy {
		public Lover(EntityType<? extends Lover> pEntityType, Level pLevel) {
			super(pEntityType, pLevel);
			this.xpReward = 5;
			this.moveControl = new Lover.LoverMoveControl(this);
		}
	public static AttributeSupplier.Builder attributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 30.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.25D)
			.add(Attributes.ATTACK_DAMAGE, 1.0D)
			.add(Attributes.FOLLOW_RANGE, 30.0D);
	}
	public RisusMobType getRisusMobType() {
		return RisusMobType.OFFSPING;
	}
	static class LoverMoveControl extends MoveControl {
		private final Lover lover;
		private int floatDuration;

		public LoverMoveControl(Lover pLover) {
			super(pLover);
			this.lover = pLover;
		}
		public void tick() {
			if (this.operation == MoveControl.Operation.MOVE_TO) {
				if (this.floatDuration-- <= 0) {
					this.floatDuration += this.lover.getRandom().nextInt(5) + 2;
					Vec3 vec3 = new Vec3(this.wantedX - this.lover.getX(), this.wantedY - this.lover.getY(), this.wantedZ - this.lover.getZ());
					double d0 = vec3.length();
					vec3 = vec3.normalize();
					if (this.canReach(vec3, Mth.ceil(d0))) {
						this.lover.setDeltaMovement(this.lover.getDeltaMovement().add(vec3.scale(0.1D)));
					} else {
						this.operation = MoveControl.Operation.WAIT;
					}
				}

			}
		}
		private boolean canReach(Vec3 pPos, int pLength) {
			AABB aabb = this.lover.getBoundingBox();

			for(int i = 1; i < pLength; ++i) {
				aabb = aabb.move(pPos);
				if (!this.lover.level().noCollision(this.lover, aabb)) {
					return false;
				}
			}

			return true;
		}
	}
}

