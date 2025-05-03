package com.bigdious.risus.dispenser;

import com.bigdious.risus.init.RisusItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

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
		DispenseItemBehavior cachedShearsBehavior = DispenserBlock.DISPENSER_REGISTRY.get(Items.SHEARS);
		DispenseItemBehavior cachedHoneyCombBehavior = DispenserBlock.DISPENSER_REGISTRY.get(Items.HONEYCOMB);


		DispenserBlock.registerBehavior(RisusItems.BLOOD_BUCKET.get(), bucketBehavior);
		DispenserBlock.registerBehavior(RisusItems.ORGANIC_MATTER, new OrganicMatterDispenseBehaviour());
		DispenserBlock.registerBehavior(Items.SHEARS, new ShavingDispenserBehaviour(cachedShearsBehavior));
		DispenserBlock.registerBehavior(Items.HONEYCOMB, new WaxingDispenserBehaviour(cachedHoneyCombBehavior));
		DispenserBlock.registerProjectileBehavior(RisusItems.EGG_SAC);
	}
}
