package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.BaseRotatableBlock;
import com.bigdious.risus.blocks.entity.RitualBlockEntity;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

public class ScytheItem extends SwordItem {

	public static final Function<Block, BlockPattern> RITUAL = block -> BlockPatternBuilder.start()
		//for some reason, south is up
		.aisle("     f     ")
		.aisle("     z     ")
		.aisle("    nye    ")
		.aisle("    fzf    ")
		.aisle("  nfnyefe  ")
		.aisle("fxyxybyxyxf")
		.aisle("  wfwysfs  ")
		.aisle("    fzf    ")
		.aisle("    wys    ")
		.aisle("     z     ")
		.aisle("     f     ")
		.where('n', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.NORTH))
		.where('e', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.EAST))
		.where('s', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.SOUTH))
		.where('w', BlockInWorld.hasState(state -> state.is(RisusBlocks.CURVED_RITUAL_BLOCK.get()) && state.getValue(BaseRotatableBlock.FACING) == Direction.WEST))
		.where('y', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y))
		.where('z', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Z))
		.where('x', BlockInWorld.hasState(state -> state.is(RisusBlocks.LINEAR_RITUAL_BLOCK.get()) && state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.X))
		.where('f', BlockInWorld.hasState(state -> state.is(block)))
		.where('b', BlockInWorld.hasState(state -> state.is(RisusBlocks.BLOOD_FLUID_BLOCK) || state.is(RisusBlocks.RITUAL))).build();

	public static final Map<Block, Pair<Block, ItemLike>> RITUAL_CONVERSIONS = Map.of(
		Blocks.CAMPFIRE, Pair.of(Blocks.FIRE, RisusItems.FIRE_SCYTHE),
		Blocks.SOUL_CAMPFIRE,  Pair.of(Blocks.SOUL_FIRE, RisusItems.SOUL_SCYTHE),
		RisusBlocks.JOYFLAME_CAMPFIRE.get(),  Pair.of(RisusBlocks.JOYFLAME_FIRE.get(), RisusItems.CINDERGLEE_SCYTHE)
	);
	private final TagKey<Enchantment> allowedEnchants;

	public ScytheItem(Tier material, TagKey<Enchantment> allowedEnchants, Properties properties) {
		super(material, properties);
		this.allowedEnchants = allowedEnchants;
	}

	public static ItemAttributeModifiers createScytheAttributes(Tier tier, int damage, float speed) {
		return SwordItem.createAttributes(tier, damage, speed)
			.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Risus.prefix("range_modifier"), 1.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.withModifierAdded(Attributes.SWEEPING_DAMAGE_RATIO, new AttributeModifier(Risus.prefix("range_modifier"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(this.allowedEnchants);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(this.allowedEnchants);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		Player player = context.getPlayer();
		ItemStack scythe = player.getMainHandItem();
		if (!scythe.is(RisusItems.SCYTHE)) return super.useOn(context);
		if (RITUAL_CONVERSIONS.containsKey(blockstate.getBlock()) && blockstate.getValue(CampfireBlock.LIT)) {
			if (RITUAL.apply(RITUAL_CONVERSIONS.get(blockstate.getBlock()).getFirst()).find(level, blockpos.below()) != null) {
				level.setBlockAndUpdate(blockpos.below(), RisusBlocks.RITUAL.get().defaultBlockState());
				if (level.getBlockEntity(blockpos.below()) instanceof RitualBlockEntity ritual) {
					ritual.setTheItem(scythe.consumeAndReturn(1, context.getPlayer()));
				}
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}
	public static class ItemExtensions implements IClientItemExtensions {

		public static final ItemExtensions INSTANCE = new ItemExtensions();

		@Override
		public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
			return HumanoidModel.ArmPose.valueOf("RISUS_SCYTHE");
		}
	}
}

