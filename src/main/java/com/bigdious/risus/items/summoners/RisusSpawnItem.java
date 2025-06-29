package com.bigdious.risus.items.summoners;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RisusSpawnItem extends Item {
	//personalized SpawnEggItem class
	private final EntityType<?> entity;

	public RisusSpawnItem(EntityType<? extends Mob> entity, Properties properties) {
		super(properties);
		this.entity = entity;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.risus.spawn" + entity.getDescriptionId()).withStyle(ChatFormatting.GRAY));
	}

	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		if (!context.getPlayer().isCreative()) {
			return InteractionResult.FAIL;
		}
		else if (!(level instanceof ServerLevel)) {
			return InteractionResult.SUCCESS;
		} else {
			ItemStack itemstack = context.getItemInHand();
			BlockPos blockpos = context.getClickedPos();
			Direction direction = context.getClickedFace();
			BlockState blockstate = level.getBlockState(blockpos);
			BlockEntity blockpos1 = level.getBlockEntity(blockpos);
			if (blockpos1 instanceof Spawner) {
				Spawner spawner = (Spawner)blockpos1;
				EntityType<?> entitytype1 = this.entity;
				spawner.setEntityId(entitytype1, level.getRandom());
				level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
				level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
				itemstack.shrink(1);
				return InteractionResult.CONSUME;
			} else {
				BlockPos blockpos2;
				if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
					blockpos2 = blockpos;
				} else {
					blockpos2 = blockpos.relative(direction);
				}

				EntityType<?> entitytype = this.entity;
				if (level instanceof ServerLevel serverLevel && entitytype.spawn(serverLevel, itemstack, context.getPlayer(), blockpos2, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
					itemstack.shrink(1);
					level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
				}

				return InteractionResult.CONSUME;
			}
		}
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (!player.isCreative()) {
			return InteractionResultHolder.fail(itemstack);
		} else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else if (!(level instanceof ServerLevel)) {
			return InteractionResultHolder.success(itemstack);
		} else {
			BlockPos blockpos = blockhitresult.getBlockPos();
			if (!(level.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
				return InteractionResultHolder.pass(itemstack);
			} else if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos, blockhitresult.getDirection(), itemstack)) {
				EntityType<?> entitytype = this.entity;
				Entity entity = entitytype.spawn((ServerLevel)level, itemstack, player, blockpos, MobSpawnType.SPAWN_EGG, false, false);
				if (entity == null) {
					return InteractionResultHolder.pass(itemstack);
				} else {
					itemstack.consume(1, player);
					player.awardStat(Stats.ITEM_USED.get(this));
					level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position());
					return InteractionResultHolder.consume(itemstack);
				}
			} else {
				return InteractionResultHolder.fail(itemstack);
			}
		}
	}
}
