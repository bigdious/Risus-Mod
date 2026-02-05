package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EternalYouthItem extends Item {
	public EternalYouthItem(Properties properties) {
		super(properties);
	}

	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity.getType().is(RisusTags.Entities.YOUTH_BANNED)) {
			return InteractionResult.PASS;
		}
		boolean itemUsed = false;
		if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() > -24000 && !(targetAnimal instanceof TamableAnimal)) {
			youthEnable(entity.level(), player, targetAnimal);
			itemUsed = true;
		} else if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() < -24000) {
			itemUsed = true;
			youthDisable(entity.level(), player, entity);
		}
		if (itemUsed) {
			if (!entity.level().isClientSide()) {
				stack.shrink(1);
			}
			return InteractionResult.sidedSuccess(entity.level().isClientSide());
		}
		return InteractionResult.PASS;
	}

	public static void youthEnable(Level level, Player player, AgeableMob target) {
		target.setBaby(true);
		target.setAge(-2000000000);
		target.setInvulnerable(true);
		target.setPersistenceRequired();
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
