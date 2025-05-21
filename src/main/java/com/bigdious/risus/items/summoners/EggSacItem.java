package com.bigdious.risus.items.summoners;

import com.bigdious.risus.entity.projectile.EggSac;
import com.bigdious.risus.init.RisusSoundEvents;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class EggSacItem extends Item implements ProjectileItem {
	public EggSacItem(Properties props) {
		super(props);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		level.playSound(null, player.getX(), player.getY(), player.getZ(), RisusSoundEvents.EGG_SAC_THROW, SoundSource.NEUTRAL, 0.5F, 1F);
		player.getCooldowns().addCooldown(this, 20);
		if (!level.isClientSide()) {
			EggSac eggSac = new EggSac(level, player);
			eggSac.setItem(itemstack);
			eggSac.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
			level.addFreshEntity(eggSac);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		itemstack.consume(1, player);
		return InteractionResultHolder.success(itemstack);
	}

	@Override
	public Projectile asProjectile(Level level, Position position, ItemStack stack, Direction direction) {
		return new EggSac(level, position.x(), position.y(), position.z());
	}
}
