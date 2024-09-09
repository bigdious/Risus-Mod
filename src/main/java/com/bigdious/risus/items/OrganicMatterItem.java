package com.bigdious.risus.items;

import com.bigdious.risus.RisusEvents;
import com.bigdious.risus.blocks.OrganicMatterableBlock;
import com.bigdious.risus.event.OrganicMatterEvent;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusDataAttachments;
import com.bigdious.risus.init.RisusParticles;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

public class OrganicMatterItem extends Item {

	public static final Supplier<BiMap<Block, Block>> PRESERVABLE = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
			.put(RisusBlocks.TISSUE.get(), RisusBlocks.LIVING_TISSUE.get())
			.put(RisusBlocks.ROTTING_TISSUE.get(), RisusBlocks.ROTTED_TISSUE.get())
			.put(RisusBlocks.DECOMPOSING_TISSUE.get(), RisusBlocks.DECOMPOSED_TISSUE.get())
			.put(RisusBlocks.DECAYING_TISSUE.get(), RisusBlocks.DECAYED_TISSUE.get())
			.build());

	public OrganicMatterItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		entity.getData(RisusDataAttachments.EX_BURN).incrementHealth(entity);
		return super.finishUsingItem(stack, level, entity);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
		if (applyOrganicMatter(context.getItemInHand(), level, blockpos, context.getPlayer())) {
			if (!level.isClientSide) {
				context.getPlayer().gameEvent(GameEvent.ITEM_INTERACT_FINISH);
				level.levelEvent(1505, blockpos, 15);
			}
			addGrowthParticles(level, blockpos,10);
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
//			boolean flag = blockstate.isFaceSturdy(level, blockpos, context.getClickedFace());
//			if (flag && growNeuron(context.getItemInHand(), level, blockpos1, context.getClickedFace())) {
//				if (!level.isClientSide) {
//					context.getPlayer().gameEvent(GameEvent.ITEM_INTERACT_FINISH);
//					level.levelEvent(1505, blockpos1, 15);
//				}
//
//				return InteractionResult.sidedSuccess(level.isClientSide);
//			} else {
				return getWaxed(blockstate).map(state -> {
					Player player = context.getPlayer();
					ItemStack itemstack = context.getItemInHand();
					if (player instanceof ServerPlayer) {
						CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
					}

					itemstack.shrink(1);
					level.setBlock(blockpos, state, 11);
					level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, state));
					ParticleUtils.spawnParticlesOnBlockFaces(level, blockpos, new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(RisusBlocks.TISSUE.get())), UniformInt.of(6, 12));
					level.playSound(null, blockpos, SoundEvents.SCULK_VEIN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
					return InteractionResult.sidedSuccess(level.isClientSide());
				}).orElse(InteractionResult.PASS);
			}
		}
	public static boolean applyOrganicMatter(ItemStack p_40628_, Level p_40629_, BlockPos p_40630_, @Nullable net.minecraft.world.entity.player.Player player) {
		BlockState blockstate = p_40629_.getBlockState(p_40630_);
		var event = RisusEvents.fireOrganicMatterEvent(player, p_40629_, p_40630_, blockstate, p_40628_);
		if (event.isCanceled()) return event.isSuccessful();
		if (blockstate.getBlock() instanceof OrganicMatterableBlock organicMatterableBlock && organicMatterableBlock.isValidOrganicMatterTarget(p_40629_, p_40630_, blockstate)) {
			if (p_40629_ instanceof ServerLevel) {
				if (organicMatterableBlock.isOrganicMatterSuccess(p_40629_, p_40629_.random, p_40630_, blockstate)) {
					organicMatterableBlock.performOrganicMatter((ServerLevel)p_40629_, p_40629_.random, p_40630_, blockstate);
				}

				p_40628_.shrink(1);
			}

			return true;
		}

		return false;
	}
	public static void addGrowthParticles(LevelAccessor pLevel, BlockPos pPos, int pData) {
		BlockState blockstate = pLevel.getBlockState(pPos);
		if (blockstate.getBlock() instanceof OrganicMatterableBlock organicMatterableBlock) {
			BlockPos blockpos = organicMatterableBlock.getOMBParticlePos(pPos);
			switch (organicMatterableBlock.getOMBType()) {
				case NEIGHBOR_SPREADER:
					ParticleUtils.spawnParticles(pLevel, blockpos, pData * 3, 3.0, 1.0, false, RisusParticles.BLOCK_ORGANIC_PARTICLE.get());
					break;
				case GROWER:
					ParticleUtils.spawnParticleInBlock(pLevel, blockpos, pData, RisusParticles.BLOCK_ORGANIC_PARTICLE.get());
			}
		} else if (blockstate.is(Blocks.WATER)) {
			ParticleUtils.spawnParticles(pLevel, pPos, pData * 3, 3.0, 1.0, false, RisusParticles.BLOCK_ORGANIC_PARTICLE.get());
		}
	}
	public static Optional<BlockState> getWaxed(BlockState state) {
		return Optional.ofNullable(PRESERVABLE.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
	}
}
