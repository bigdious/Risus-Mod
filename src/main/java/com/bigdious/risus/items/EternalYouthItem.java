package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class EternalYouthItem extends Item {
	public EternalYouthItem(Properties properties) {
		super(properties);
	}

	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity.getType().is(RisusTags.Entities.YOUTH_BANNED)) {
			return InteractionResult.PASS;
		}
		boolean itemUsed = false;
		if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() > -24000 && !entity.getAttribute(Attributes.SCALE).hasModifier(Risus.prefix("eternal_youth_scale"))) {
			youthEnable(entity.level(), player, entity);
			itemUsed = true;
		} else if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() < -24000) {
			itemUsed = true;
			youthDisable(entity.level(), player, entity);
		} else if ((entity.getType().is(RisusTags.Entities.YOUTH_SHRINKS) || RisusConfig.everythingYouthable) && entity.getAttributes().getInstance(Attributes.SCALE) != null) {
			if (entity.getAttribute(Attributes.SCALE).hasModifier(Risus.prefix("eternal_youth_scale"))) {
				youthDisable(entity.level(), player, entity);
			} else {
				youthEnable(entity.level(), player, entity);
			}
			itemUsed = true;
		}
		if (itemUsed) {
			boolean isClient = entity.level().isClientSide();
			if (!isClient) {
				stack.shrink(1);
			}
			return InteractionResult.sidedSuccess(isClient);
		}
		return InteractionResult.PASS;
	}

	public static void youthEnable(Level level, Player player, LivingEntity target) {
		if (target instanceof AgeableMob targetAnimal) {
			targetAnimal.setBaby(true);
			targetAnimal.setAge(-2000000000);
			targetAnimal.setInvulnerable(true);
			targetAnimal.setPersistenceRequired();
			//pay attention to the stacking of (). Correct order is mandatory
			if ((!targetAnimal.isBaby() && (targetAnimal.getType().is(RisusTags.Entities.YOUTH_SHRINKS) || RisusConfig.everythingYouthable)) && target.getAttribute(Attributes.SCALE) != null) {
				targetAnimal.getAttribute(Attributes.SCALE).addPermanentModifier(new AttributeModifier(Risus.prefix("eternal_youth_scale"), -0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
			}
			if (target.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
				targetAnimal.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(-1024.0F);
			}
		} else if ((target.getType().is(RisusTags.Entities.YOUTH_SHRINKS) || RisusConfig.everythingYouthable) && target.getAttribute(Attributes.SCALE) != null && target.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
			target.setInvulnerable(true);
			if (target instanceof Mob mob) mob.setPersistenceRequired();
			target.getAttribute(Attributes.SCALE).addPermanentModifier(new AttributeModifier(Risus.prefix("eternal_youth_scale"), -0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
			if (target instanceof Player){
				target.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(new AttributeModifier(Risus.prefix("eternal_youth_passive"), -1024F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
			} else target.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(-1024.0F);

		}
		if (level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.POOF, target.getX(), target.getRandomY(), target.getZ(), 20, 0, 0.0, 0.0, 0.1);
			serverLevel.sendParticles(RisusParticles.RISING_SMILE.get(), target.getX(), target.getEyeY(), target.getZ(), 1, 0, 0.0, 0.0, 0.2);
			serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.ETERNAL_YOUTH.get())), target.getX(), target.getEyeY(), target.getZ(), 6, 0, 0.0, 0.0, 0.1);
		}
		player.playSound(RisusSoundEvents.ETERNAL_YOUTH_BREAK.get());
	}
	public static void youthDisable(Level level, Player player, LivingEntity target){
		target.kill();
		if (level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getEyeY(), target.getZ(), 1, 0, 0.0, 0.0, 0.1);
			serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.ETERNAL_YOUTH.get())), target.getX(), target.getEyeY(), target.getZ(), 6, 0, 0.0, 0.0, 0.1);
		}
		player.playSound(RisusSoundEvents.ETERNAL_YOUTH_BREAK.get());
	}
}
