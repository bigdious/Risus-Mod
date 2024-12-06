package com.bigdious.risus.init;

import com.bigdious.risus.data.RisusBiomes;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public interface RisusCauldronInteractions extends CauldronInteraction {
	CauldronInteraction FILL_BLOOD = (state, world, pos, player, hand, stack) ->
		CauldronInteraction.emptyBucket(world, pos, player, hand, stack, RisusBlocks.BLOOD_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);

	CauldronInteraction EMPTY_BLOOD = (state, world, pos, player, hand, stack) ->
		CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(RisusItems.BLOOD_BUCKET.get()), blockState -> true, SoundEvents.BUCKET_FILL);

	CauldronInteraction.InteractionMap BLOOD = CauldronInteraction.newInteractionMap("blood");

	static void register() {
		EMPTY.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		WATER.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		LAVA.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		POWDER_SNOW.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);
		BLOOD.map().put(RisusItems.BLOOD_BUCKET.get(), FILL_BLOOD);

		BLOOD.map().put(Items.BUCKET, EMPTY_BLOOD);

		CauldronInteraction.addDefaultInteractions(BLOOD.map());
	}
}
