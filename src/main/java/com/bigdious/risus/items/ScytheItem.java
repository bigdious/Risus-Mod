package com.bigdious.risus.items;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.RisusCampfireBlock;
import com.bigdious.risus.init.RisusBlocks;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

import java.util.Map;

public class ScytheItem extends SwordItem {

	public static final BlockPattern RITUAL = BlockPatternBuilder.start().aisle("crc").aisle("rbr").aisle("crc")
		.where('c', BlockInWorld.hasState(BlockStatePredicate.forBlock(RisusBlocks.CURVED_RITUAL_BLOCK.get())))
		.where('r', BlockInWorld.hasState(BlockStatePredicate.forBlock(RisusBlocks.LINEAR_RITUAL_BLOCK.get())))
		.where('b', BlockInWorld.hasState(BlockStatePredicate.forBlock(RisusBlocks.BLOOD_FLUID_BLOCK.get()))).build();

	public static final Map<Block, ItemLike> RITUAL_CONVERSIONS = Map.of(
		Blocks.CAMPFIRE, RisusItems.FIRE_SCYTHE,
		Blocks.SOUL_CAMPFIRE, RisusItems.SOUL_SCYTHE,
		RisusBlocks.JOYFLAME_CAMPFIRE.get(), RisusItems.CINDERGLEE_SCYTHE
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
		if (RITUAL.find(level, blockpos.below()) != null) {
			if (RITUAL_CONVERSIONS.containsKey(blockstate.getBlock()) && blockstate.getValue(CampfireBlock.LIT)) {
				player.spawnAtLocation(this.transformAndRemoveEnchants(scythe, RITUAL_CONVERSIONS.get(blockstate.getBlock())));
				level.setBlock(blockpos, blockstate.trySetValue(CampfireBlock.LIT, false), 11);
				scythe.shrink(1);
				if (level.isClientSide()) {
					for (int i = 0; i < 20; i++) {
						level.addParticle(RisusParticles.ALTERATION_FINISHED.get(),
							(blockpos.getX() - 1F) + (level.getRandom().nextFloat() * 2.75F),
							blockpos.getY(),
							(blockpos.getZ() - 1F) + (level.getRandom().nextFloat() * 2.75F),
							0.0F, 0.1F, 0.0F);
					}
				}
				level.playSound(null, blockpos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	private ItemStack transformAndRemoveEnchants(ItemStack transformFrom, ItemLike transformTo) {
		var newStack = transformFrom.transmuteCopy(transformTo);
		var enchants = new ItemEnchantments.Mutable(newStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY));
		enchants.removeIf(enchant -> !newStack.supportsEnchantment(enchant));
		newStack.set(DataComponents.ENCHANTMENTS, enchants.toImmutable());
		return newStack;
	}
}

