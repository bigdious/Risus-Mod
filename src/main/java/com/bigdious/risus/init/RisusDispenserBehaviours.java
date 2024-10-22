package com.bigdious.risus.init;

import com.bigdious.risus.dispenser.OrganicMatterDispenseBehaviour;
import com.bigdious.risus.dispenser.ShavingDispenserBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.neoforge.common.Tags;

public class RisusDispenserBehaviours {
	public static void register() {
		DispenseItemBehavior bucketBehavior = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource source, ItemStack stack) {
				BucketItem bucketitem = (BucketItem) stack.getItem();
				BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
				Level world = source.level().getLevel();
				if (bucketitem.emptyContents(null, world, blockpos, null)) {
					bucketitem.checkExtraContent(null, world, stack, blockpos);
					return new ItemStack(Items.BUCKET);
				} else {
					return this.defaultBehavior.dispense(source, stack);
				}
			}
		};

		DispenserBlock.registerBehavior(RisusItems.BLOOD_BUCKET.get(), bucketBehavior);


		DispenserBlock.registerBehavior(RisusItems.ORGANIC_MATTER, new OrganicMatterDispenseBehaviour());
		DispenserBlock.registerBehavior(Items.SHEARS, new ShavingDispenserBehaviour());
	}
}
