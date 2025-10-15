package com.bigdious.risus.init;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;

public interface RisusCauldronInteractions extends CauldronInteraction {
	CauldronInteraction FILL_BLOOD = (state, world, pos, player, hand, stack) ->
		CauldronInteraction.emptyBucket(world, pos, player, hand, stack, RisusBlocks.BLOOD_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);

	CauldronInteraction EMPTY_BLOOD = (state, world, pos, player, hand, stack) ->
		CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(RisusItems.BLOOD_BUCKET.get()), blockState -> true, SoundEvents.BUCKET_FILL);

	CauldronInteraction.InteractionMap BLOOD = CauldronInteraction.newInteractionMap("blood");
	CauldronInteraction ABILITY_ITEM = (state, world, pos, player, hand, stack) -> {
		if (!stack.has(RisusDataComponents.ABILITY_VARIANT)) {
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			if (!world.isClientSide) {
				stack.remove(RisusDataComponents.ABILITY_VARIANT);
				player.awardStat(Stats.CLEAN_ARMOR);
				world.playSound(null, pos, SoundEvents.GENERIC_BURN, SoundSource.BLOCKS);
				world.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 11);
			}

			return ItemInteractionResult.sidedSuccess(world.isClientSide);
		}
	};

	static void register() {
		EMPTY.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		WATER.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		LAVA.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		POWDER_SNOW.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		BLOOD.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);

		BLOOD.map().put(Items.BUCKET, EMPTY_BLOOD);

		LAVA.map().put(RisusItems.SINNER_ROBES_HELMET.asItem(), ABILITY_ITEM);
		LAVA.map().put(RisusItems.SINNER_ROBES_CHESTPLATE.asItem(), ABILITY_ITEM);
		LAVA.map().put(RisusItems.SINNER_ROBES_LEGGINGS.asItem(), ABILITY_ITEM);
		LAVA.map().put(RisusItems.SINNER_ROBES_BOOTS.asItem(), ABILITY_ITEM);

		CauldronInteraction.addDefaultInteractions(BLOOD.map());
	}
}
