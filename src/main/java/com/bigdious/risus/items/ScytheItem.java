package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.RisusCampfireBlock;
import com.bigdious.risus.client.particle.AlterationParticleOptions;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

public class ScytheItem extends SwordItem {

	public ScytheItem(Tier material, Properties properties) {
		super(material, properties);
	}

	public static ItemAttributeModifiers createScytheAttributes(Tier tier, int damage, float speed) {
		return SwordItem.createAttributes(tier, damage, speed)
			.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Risus.prefix("range_modifier"), 2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO, new AttributeModifier(Risus.prefix("range_modifier"), damage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
	}
	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return material.is(RisusItems.GLUTTONY_SCALES);
	}
	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		Level level = pContext.getLevel();
		BlockPos blockpos = pContext.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		Player player = pContext.getPlayer();
		ItemStack scythe = player.getMainHandItem();
		if (level.getBlockState(blockpos.below().east()).is(RisusBlocks.LINEAR_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below().west()).is(RisusBlocks.LINEAR_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below().south()).is(RisusBlocks.LINEAR_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below().north()).is(RisusBlocks.LINEAR_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below().north().east()).is(RisusBlocks.CURVED_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below().north().west()).is(RisusBlocks.CURVED_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below().south().east()).is(RisusBlocks.CURVED_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below().south().west()).is(RisusBlocks.CURVED_RITUAL_BLOCK) &&
			level.getBlockState(blockpos.below()).is(RisusBlocks.BLOOD_FLUID_BLOCK)
		) {
			if (blockstate.getValue(RisusCampfireBlock.LIT) && blockstate.is(RisusBlocks.JOYFLAME_CAMPFIRE) && scythe.is(RisusItems.SCYTHE.get())) {
				ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), new ItemStack(RisusItems.CINDERGLEE_SCYTHE.get()));
				item.getItem().applyComponents(scythe.getComponentsPatch());
				player.level().addFreshEntity(item);
				if (!level.isClientSide()) {
					level.setBlock(blockpos, RisusBlocks.JOYFLAME_CAMPFIRE.get().defaultBlockState().setValue(RisusCampfireBlock.LIT, false).setValue(RisusCampfireBlock.FACING, blockstate.getValue(RisusCampfireBlock.FACING)), 11);
				}
				scythe.shrink(1);
				if (level.isClientSide()) {
					for (int i = 0; i < 20; i++) {
						level.addParticle(RisusParticles.ALTERATION_FINISHED.get(),
							(blockpos.getX() -1F) + (level.getRandom().nextFloat() * 2.75F),
							blockpos.getY(),
							(blockpos.getZ() -1F) + (level.getRandom().nextFloat() * 2.75F),
							0.0F, 0.1F, 0.0F);
					}
				}
				level.playSound(null, blockpos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1F, 1F);
				return InteractionResult.SUCCESS;
			}
			if (blockstate.getValue(CampfireBlock.LIT) && blockstate.is(Blocks.SOUL_CAMPFIRE) && scythe.is(RisusItems.SCYTHE.get())) {
				ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), new ItemStack(RisusItems.SOUL_SCYTHE.get()));
				item.getItem().applyComponents(scythe.getComponentsPatch());
				player.level().addFreshEntity(item);
				if (!level.isClientSide()) {
					level.setBlock(blockpos, Blocks.SOUL_CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false).setValue(RisusCampfireBlock.FACING, blockstate.getValue(CampfireBlock.FACING)), 11);
				}
				scythe.shrink(1);
				if (level.isClientSide()) {
					for (int i = 0; i < 20; i++) {
						level.addParticle(RisusParticles.ALTERATION_FINISHED.get(),
							(blockpos.getX() -1F) + (level.getRandom().nextFloat() * 2.75F),
							blockpos.getY(),
							(blockpos.getZ() -1F) + (level.getRandom().nextFloat() * 2.75F),
							0.0F, 0.1F, 0.0F);
					}
				}
				level.playSound(null, blockpos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1F);
				return InteractionResult.SUCCESS;
			}
			if (blockstate.getValue(CampfireBlock.LIT) && blockstate.is(Blocks.CAMPFIRE) && scythe.is(RisusItems.SCYTHE.get())) {
				ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), new ItemStack(RisusItems.FIRE_SCYTHE.get()));
				item.getItem().applyComponents(scythe.getComponentsPatch());
				player.level().addFreshEntity(item);
				if (!level.isClientSide()) {
					level.setBlock(blockpos, Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false).setValue(RisusCampfireBlock.FACING, blockstate.getValue(CampfireBlock.FACING)), 11);
				}
				scythe.shrink(1);
				if (level.isClientSide()) {
					for (int i = 0; i < 20; i++) {
						level.addParticle(RisusParticles.ALTERATION_FINISHED.get(),
							(blockpos.getX() -1F) + (level.getRandom().nextFloat() * 2.75F),
							blockpos.getY(),
							(blockpos.getZ() -1F) + (level.getRandom().nextFloat() * 2.75F),
							0.0F, 0.1F, 0.0F);
					}
				}
				level.playSound(null, blockpos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1F);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}

}

