package com.bigdious.risus.items;

import com.bigdious.risus.init.RisusDataMaps;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EternalYouthItem extends Item {
	public EternalYouthItem(Properties properties) {
		super(properties);
	}

	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() > -24000){
			targetAnimal.setBaby(true);
			targetAnimal.setAge(-2000000000);
			targetAnimal.setInvulnerable(true);
			boolean isClient = entity.level().isClientSide();
			if (!isClient) {
				stack.shrink(1);
			}
			return InteractionResult.sidedSuccess(isClient);
		} else if (entity instanceof AgeableMob targetAnimal && targetAnimal.getAge() < -24000){
			targetAnimal.kill();
		}

		return InteractionResult.PASS;
	}
}
