package com.bigdious.risus.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;

public class LightDevourerItem extends BlockItem {

	@Override
	public InteractionResult place(BlockPlaceContext p_40577_) {
		if (!p_40577_.canPlace()) {
			return InteractionResult.FAIL;
		} else {
			BlockPlaceContext blockplacecontext = this.updatePlacementContext(p_40577_);
			if (blockplacecontext == null) {
				return InteractionResult.FAIL;
			} else {
				BlockState blockstate = this.getPlacementState(blockplacecontext);
				if (blockstate == null) {
					return InteractionResult.FAIL;
				} else if (!this.placeBlock(blockplacecontext, blockstate)) {
					return InteractionResult.FAIL;
				} else {
					BlockPos blockpos = blockplacecontext.getClickedPos();
					Level level = blockplacecontext.getLevel();
					Player player = blockplacecontext.getPlayer();
					ItemStack itemstack = blockplacecontext.getItemInHand();
					BlockState blockstate1 = level.getBlockState(blockpos);
					if (blockstate1.is(blockstate.getBlock())) {
						blockstate1 = this.updateBlockStateFromTag(blockpos, level, itemstack, blockstate1);
						this.updateCustomBlockEntityTag(blockpos, level, player, itemstack, blockstate1);
						blockstate1.getBlock().setPlacedBy(level, blockpos, blockstate1, player, itemstack);
						if (player instanceof ServerPlayer) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
						}
					}

					level.gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(player, blockstate1));
					SoundType soundtype = blockstate1.getSoundType(level, blockpos, p_40577_.getPlayer());
					level.playSound(player, blockpos, this.getPlaceSound(blockstate1, level, blockpos, p_40577_.getPlayer()), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

					return InteractionResult.sidedSuccess(level.isClientSide);
				}
			}
		}
	}

	private BlockState updateBlockStateFromTag(BlockPos p_40603_, Level p_40604_, ItemStack p_40605_, BlockState p_40606_) {
		BlockState blockstate = p_40606_;
		CompoundTag compoundtag = p_40605_.getTag();
		if (compoundtag != null) {
			CompoundTag compoundtag1 = compoundtag.getCompound("BlockStateTag");
			StateDefinition<Block, BlockState> statedefinition = p_40606_.getBlock().getStateDefinition();

			for (String s : compoundtag1.getAllKeys()) {
				Property<?> property = statedefinition.getProperty(s);
				if (property != null) {
					String s1 = compoundtag1.get(s).getAsString();
					blockstate = updateState(blockstate, property, s1);
				}
			}
		}

		if (blockstate != p_40606_) {
			p_40604_.setBlock(p_40603_, blockstate, 2);
		}

		return blockstate;
	}

	private static <T extends Comparable<T>> BlockState updateState(BlockState p_40594_, Property<T> p_40595_, String p_40596_) {
		return p_40595_.getValue(p_40596_).map((p_40592_) -> {
			return p_40594_.setValue(p_40595_, p_40592_);
		}).orElse(p_40594_);
	}

	@Override
	public String getDescriptionId() {
		return getOrCreateDescriptionId();
	}

	public LightDevourerItem(Block p_40565_, Properties p_40566_) {
		super(p_40565_, p_40566_);
	}
}
