package com.bigdious.risus.items;

import com.bigdious.risus.entity.Memory1;
import com.bigdious.risus.init.RisusEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;


public class Memory1Item extends Item {

	public Memory1Item(Properties props) {
		super(props);
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!level.isClientSide()) {
			BlockHitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
			if (result.getType() != HitResult.Type.MISS) {
				Memory1 star = RisusEntities.MEMORY1.get().create(level);
				star.moveTo(result.getLocation());
				if (!level.noCollision(star, star.getBoundingBox())) {
					return InteractionResultHolder.pass(stack);
				}
				level.addFreshEntity(star);
				level.gameEvent(player, GameEvent.ENTITY_PLACE, BlockPos.containing(result.getLocation()));
				star.setYRot(90);
				if (!player.getAbilities().instabuild) {
					player.getItemInHand(hand).shrink(1);
				}


			}
		}

		return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));


	}
}
