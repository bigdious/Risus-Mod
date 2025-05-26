package com.bigdious.risus.entity.projectile;

import com.bigdious.risus.init.RisusEntities;
import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;

import java.util.Objects;

public class ThrownEndlessPearl extends ThrownEnderpearl {

	public ThrownEndlessPearl(EntityType<? extends ThrownEndlessPearl> entityType, Level level) {
		super(entityType, level);
	}
	public ThrownEndlessPearl(EntityType<? extends ThrownEndlessPearl> entityType, LivingEntity shooter, Level world) {
		super(entityType, world);
		this.setPos(shooter.getX(), shooter.getEyeY() - 0.1D, shooter.getZ());
		this.setOwner(shooter);
	}
	public ThrownEndlessPearl(Level world, LivingEntity owner) {
		this(RisusEntities.ENDLESS_PEARL.get(), owner, world);
	}

	@Override
	protected Item getDefaultItem() {
		return RisusItems.ENDLESS_PEARL.get();
	}
	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
	}
	@Override
	protected void onHit(HitResult result) {
		//copied from Projectile to circumvent call to EnderPearl
		HitResult.Type hitresult$type = result.getType();
		if (hitresult$type == HitResult.Type.ENTITY) {
			EntityHitResult entityhitresult = (EntityHitResult)result;
			Entity entity = entityhitresult.getEntity();
			if (entity.getType().is(EntityTypeTags.REDIRECTABLE_PROJECTILE) && entity instanceof Projectile) {
				Projectile projectile = (Projectile)entity;
				projectile.deflect(ProjectileDeflection.AIM_DEFLECT, this.getOwner(), this.getOwner(), true);
			}

			this.onHitEntity(entityhitresult);
			this.level().gameEvent(GameEvent.PROJECTILE_LAND, result.getLocation(), GameEvent.Context.of(this, (BlockState)null));
		} else if (hitresult$type == HitResult.Type.BLOCK) {
			BlockHitResult blockhitresult = (BlockHitResult)result;
			this.onHitBlock(blockhitresult);
			BlockPos blockpos = blockhitresult.getBlockPos();
			this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
		}

		for(int i = 0; i < 32; ++i) {
			this.level().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * (double)2.0F, this.getZ(), this.random.nextGaussian(), (double)0.0F, this.random.nextGaussian());
		}

		Level levelentity = this.level();
		if (levelentity instanceof ServerLevel serverlevel) {
			if (!this.isRemoved()) {
				Entity entity = this.getOwner();
				if (entity != null && isAllowedToTeleportOwner(entity, serverlevel)) {
					if (entity.isPassenger()) {
						entity.unRide();
					}

					if (entity instanceof ServerPlayer) {
						ServerPlayer serverplayer = (ServerPlayer)entity;
						if (serverplayer.connection.isAcceptingMessages()) {
							EntityTeleportEvent.EnderPearl event = EventHooks.onEnderPearlLand(serverplayer, this.getX(), this.getY(), this.getZ(), this, 8.0F, result);
							if (!event.isCanceled()) {
								entity.changeDimension(new DimensionTransition(serverlevel, event.getTarget(), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING));
								entity.resetFallDistance();
								serverplayer.resetCurrentImpulseContext();
								serverplayer.hurt(this.damageSources().fall(), Objects.requireNonNull(serverplayer.getAttribute(Attributes.SAFE_FALL_DISTANCE)).getValue() - event.getAttackDamage()>= 0 ? 0 : (float)(Objects.requireNonNull(serverplayer.getAttribute(Attributes.SAFE_FALL_DISTANCE)).getValue() - event.getAttackDamage())*-1F);
								this.playSound(serverlevel, this.position());
							}
						}
					} else {
						entity.changeDimension(new DimensionTransition(serverlevel, this.position(), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING));
						entity.resetFallDistance();
						this.playSound(serverlevel, this.position());
					}

					this.discard();
					return;
				}

				this.discard();
				return;
			}
		}

	}

	private static boolean isAllowedToTeleportOwner(Entity entity, Level level) {
		if (entity.level().dimension() != level.dimension()) {
			return entity.canUsePortal(true);
		} else {
			boolean var10000;
			if (entity instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity)entity;
				var10000 = livingentity.isAlive() && !livingentity.isSleeping();
			} else {
				var10000 = entity.isAlive();
			}

			return var10000;
		}
	}

	private void playSound(Level level, Vec3 pos) {
		level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
	}
}
