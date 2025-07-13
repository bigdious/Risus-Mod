package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.RisusItems;
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
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class EternalYouthItem extends Item {
	public EternalYouthItem(Properties properties) {
		super(properties);
	}

	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity.getType().is(RisusTags.Entities.YOUTH_BANNED)) {
			return InteractionResult.PASS;
		}
		boolean did = false;
		boolean itemUsed = false;
		boolean killed = false;
		if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() > -24000){
			targetAnimal.setBaby(true);
			targetAnimal.setAge(-2000000000);
			targetAnimal.setInvulnerable(true);
			if ((!targetAnimal.isBaby() || targetAnimal.getType().is(RisusTags.Entities.YOUTH_SHRINKS)) && !entity.getAttribute(Attributes.SCALE).hasModifier(Risus.prefix("eternal_youth_scale"))) {
				Objects.requireNonNull(targetAnimal.getAttribute(Attributes.SCALE)).addPermanentModifier(new AttributeModifier(Risus.prefix("eternal_youth_scale"),  -0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
			}
			did = true;
			itemUsed = true;
		} else if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() < -24000){
			targetAnimal.kill();
			itemUsed = true;
			killed = true;
		} else if (entity.getType().is(RisusTags.Entities.YOUTH_SHRINKS)) {
			if (Objects.requireNonNull(entity.getAttribute(Attributes.SCALE)).hasModifier(Risus.prefix("eternal_youth_scale"))) {
				entity.kill();
				killed = true;
			} else {
				entity.setInvulnerable(true);
				Objects.requireNonNull(entity.getAttribute(Attributes.SCALE)).addPermanentModifier(new AttributeModifier(Risus.prefix("eternal_youth_scale"),  -0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
				did = true;
			}
			itemUsed = true;
		}
		if (entity.level() instanceof ServerLevel serverLevel) {
			if (did) {
				serverLevel.sendParticles(ParticleTypes.POOF, entity.getRandomX(0.5), entity.getRandomY(), entity.getRandomZ(0.5), 20, 0, 0.0, 0.0, 0.1);
			}
			if (killed) {
				serverLevel.sendParticles(ParticleTypes.ANGRY_VILLAGER, entity.getRandomX(0.5), entity.getRandomY(), entity.getRandomZ(0.5), 3, 0, 0.0, 0.0, 0.1);
			}

			if (itemUsed) {
				boolean isClient = entity.level().isClientSide();
				if (!isClient) {
					stack.shrink(1);
					entity.level().playSound(player, entity.getX(), entity.getY(), entity.getY(), RisusSoundEvents.ETERNAL_YOUTH_BREAK.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
				}
				serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusItems.ETERNAL_YOUTH.get())), entity.getRandomX(1), entity.getRandomY(), entity.getRandomZ(1), 5, 0, 0.0, 0.0, 0.1);
				return InteractionResult.sidedSuccess(isClient);
			}
		}

		return InteractionResult.PASS;
	}
}
