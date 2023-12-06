package com.bigdious.risus.items;

import com.bigdious.risus.entity.projectile.BloodwyrmBreathEntity;
import com.bigdious.risus.init.RisusSoundEvents;
import com.bigdious.risus.init.RisusSoundTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class BloodwyrmHeadItem extends Item {
	@Nullable
	@Override
	public UseAnim getUseAnimation(ItemStack stack)
	{
		return UseAnim.BOW;
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		player.startUsingItem(hand);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}
	@Override
	public void onUseTick(Level level, LivingEntity player, ItemStack stack, int remainingUseDuration)
	{

		if(stack.getDamageValue()!=1)
		{
			Vec3 v = player.getLookAngle();
			int split = 8;
			float scatter = .15f;
			float range = 1f;
				for(int i = 0; i < split; i++) {
					Vec3 vecDir = v.add(player.getRandom().nextGaussian() * scatter, player.getRandom().nextGaussian() * scatter, player.getRandom().nextGaussian() * scatter);
					BloodwyrmBreathEntity breath = new BloodwyrmBreathEntity(player.level(), player);

					// Apply momentum from the player.
					breath.setDeltaMovement(player.getDeltaMovement().add(vecDir.scale(range)));

					// Apply a small amount of backforce.
					if (!player.onGround())
						player.setDeltaMovement(player.getDeltaMovement().subtract(vecDir.scale(0.0025 * range)));
					if (!player.level().isClientSide)
						player.level().addFreshEntity(breath);
					}


				{
						player.level().playSound(null, player.getX(), player.getY(), player.getZ(), RisusSoundEvents.SQUIRT.get(), SoundSource.PLAYERS, .5f, .75f);
				}
		}
		else
			player.releaseUsingItem();
	}




	public BloodwyrmHeadItem(Properties p_41383_) {
		super(p_41383_);
	}
}
