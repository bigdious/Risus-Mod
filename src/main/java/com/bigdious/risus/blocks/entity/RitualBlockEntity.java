package com.bigdious.risus.blocks.entity;

import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.bigdious.risus.init.RisusBlockEntities;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.items.weapons.ScytheItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;

public class RitualBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {

	protected ItemStack item = ItemStack.EMPTY;
	private int timer;

	public RitualBlockEntity(BlockPos pos, BlockState blockState) {
		super(RisusBlockEntities.RITUAL.get(), pos, blockState);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, RitualBlockEntity entity) {
		if (!ScytheItem.RITUAL_CONVERSIONS.containsKey(level.getBlockState(pos.above()).getBlock())) {
			var item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), entity.getTheItem());
			level.addFreshEntity(item);
			level.setBlockAndUpdate(pos, RisusBlocks.BLOOD_FLUID_BLOCK.get().defaultBlockState());
			return;
		}
		entity.timer++;
		if (level.isClientSide()) {
			if (entity.timer < 60) {
				for (int i = 0; i < 5; i++) {
					RandomSource random = level.getRandom();
					BlockPos randomPos = pos.offset(Mth.floor(random.nextFloat() * 3.0F * (random.nextBoolean() ? 1.0F : -1.0F)), 2, Mth.floor(random.nextFloat() * 3.0F * (random.nextBoolean() ? 1.0F : -1.0F)));
					Vec3 vec3 = new Vec3(pos.getX() + 0.5D, pos.getY() + 3.0D, pos.getZ() + 0.5D);

					BlockPos blockpos1 = randomPos.subtract(pos);
					float f = -0.5F + random.nextFloat() + (float) blockpos1.getX();
					float f1 = -4.0F + random.nextFloat() + (float) blockpos1.getY();
					float f2 = -0.5F + random.nextFloat() + (float) blockpos1.getZ();
					level.addAlwaysVisibleParticle(AlterationParticleOptions.ALTERATION_FADE, vec3.x(), vec3.y(), vec3.z(), f, f1, f2);
				}
			} else if (entity.timer > 65) {
				for (int i = 0; i < 20; i++) {
					level.addAlwaysVisibleParticle(RisusParticles.ALTERATION_FINISHED.get(),
						(pos.getX() - 2F) + (level.getRandom().nextFloat() * 4F),
						pos.getY() + 1,
						(pos.getZ() - 2F) + (level.getRandom().nextFloat() * 4F),
						0.0F, 0.1F, 0.0F);
				}
			}
		}

		if (entity.timer > 100) {
			var campfire = level.getBlockState(pos.above());
			var resultPair = ScytheItem.RITUAL_CONVERSIONS.get(campfire.getBlock());
			var ritual = ScytheItem.RITUAL.apply(resultPair.getFirst()).find(level, pos);
			if (ritual != null) {
				var item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), entity.transformAndRemoveEnchants(entity.item, resultPair.getSecond()));
				level.addFreshEntity(item);
				level.setBlock(pos.above(), campfire.trySetValue(CampfireBlock.LIT, false), 11);
				for (int x = 0; x < ritual.getWidth(); x++) {
					for (int y = 0; y < ritual.getHeight(); y++) {
						for (int z = 0; z < ritual.getDepth(); z++) {
							var worldBlock = ritual.getBlock(x, y, z);
							if (worldBlock.getState().is(RisusBlocks.CURVED_RITUAL_BLOCK) || worldBlock.getState().is(RisusBlocks.LINEAR_RITUAL_BLOCK)) {
								level.setBlockAndUpdate(worldBlock.getPos(), RisusBlocks.ASHEN_REMAINS.get().defaultBlockState());
								level.addDestroyBlockEffect(worldBlock.getPos(), RisusBlocks.ASHEN_REMAINS.get().defaultBlockState());
							} else if (worldBlock.getState().is(BlockTags.FIRE)) {
								level.removeBlock(worldBlock.getPos(), false);
							}
						}
					}
				}
				level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS);
				level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
			} else {
				var item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), entity.getTheItem());
				level.addFreshEntity(item);
				level.setBlock(pos.above(), level.getBlockState(pos.above()).trySetValue(CampfireBlock.LIT, false), 11);
				level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS);
				level.setBlockAndUpdate(pos, RisusBlocks.BLOOD_FLUID_BLOCK.get().defaultBlockState());
			}
		}
	}

	private ItemStack transformAndRemoveEnchants(ItemStack transformFrom, ItemLike transformTo) {
		var newStack = transformFrom.transmuteCopy(transformTo);
		var enchants = new ItemEnchantments.Mutable(newStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY));
		enchants.removeIf(enchant -> !newStack.supportsEnchantment(enchant));
		newStack.set(DataComponents.ENCHANTMENTS, enchants.toImmutable());
		return newStack;
	}

	public int getTimer() {
		return this.timer;
	}

	@Override
	public BlockEntity getContainerBlockEntity() {
		return this;
	}

	@Override
	public ItemStack getTheItem() {
		return this.item;
	}

	@Override
	public void setTheItem(ItemStack item) {
		this.item = item;
		this.setChanged();
	}
}
