package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import com.bigdious.risus.blocks.DecomposingTissueBlock;
import com.bigdious.risus.blocks.interfaces.DecomposingBlock;
import com.bigdious.risus.init.*;
import com.bigdious.risus.util.ServerParticleUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.util.AttributeUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CarvingKnifeItem extends SwordItem {

	public CarvingKnifeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}
	public static ItemAttributeModifiers createKnifeAttributes(Tier tier, int damage, float speed) {
		return SwordItem.createAttributes(tier, damage, speed)
			.withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(AttributeUtil.BASE_ENTITY_REACH_ID, -1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
		if (state.getDestroySpeed(level, pos) != 0.0F) {
			itemstack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
		}

		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		//self hurt
		if (player.isCrouching()) {
			player.hurt(player.damageSources().source(RisusDamageTypes.MUTILATION), 1);
			if (level instanceof ServerLevel serverLevel) {
				bleed(serverLevel, player, 6, RisusBlocks.TISSUE.asItem());
			}
			stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
			player.awardStat(Stats.ITEM_USED.get(this));
			return InteractionResultHolder.success(stack);
		}
		return InteractionResultHolder.fail(stack);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
		if (!target.isAlive()) {
			return InteractionResult.PASS;
		}
		boolean success = false;
		//goat
		if (target instanceof Goat goat && player.level() instanceof ServerLevel) {
			goat.dropHorn();
			goat.hurt(player.damageSources().playerAttack(player), 2);
			if (player.level() instanceof ServerLevel serverLevel) {
				bleed(serverLevel, goat, 4, RisusBlocks.TISSUE.asItem());
			}
			player.level().playSound(null, goat, goat.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_HORN_BREAK : SoundEvents.GOAT_HORN_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
			success = true;
		}
		//turtle
		if (target instanceof Turtle turtle && player.level() instanceof ServerLevel) {
			turtle.hurt(player.damageSources().playerAttack(player), 2);turtle.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(turtle.getAttributes().getInstance(Attributes.MAX_HEALTH).getBaseValue() - 4);
			if (player.level() instanceof ServerLevel serverLevel) {
				bleed(serverLevel, turtle, 8, RisusBlocks.TISSUE.asItem());
			}
			ItemEntity scute = EntityType.ITEM.create(player.level());
			scute.setItem(Items.TURTLE_SCUTE.getDefaultInstance());
			scute.moveTo(turtle.getX(), turtle.getY(), turtle.getZ());
			player.level().addFreshEntity(scute);
			player.level().playSound(null, turtle, SoundEvents.TURTLE_HURT, SoundSource.NEUTRAL, 1.0F, 1.0F);
			success = true;
		}
		//rabbit
		if (target instanceof Rabbit rabbit && player.level() instanceof ServerLevel) {
			ItemEntity foot = EntityType.ITEM.create(player.level());
			foot.setItem(Items.RABBIT_FOOT.getDefaultInstance());
			foot.moveTo(rabbit.getX(), rabbit.getY(), rabbit.getZ());
			player.level().addFreshEntity(foot);
			if (player.level() instanceof ServerLevel serverLevel) {
				bleed(serverLevel, rabbit, 8, RisusBlocks.TISSUE.asItem());
			}
			rabbit.hurt(player.damageSources().playerAttack(player), rabbit.getMaxHealth()+1);
			success = true;
		}
		//skeleton
		if (target instanceof Skeleton skeleton && player.level() instanceof ServerLevel sl && skeleton.getHealth() <= skeleton.getMaxHealth()/2) {
			if (sl.getRandom().nextFloat() > 0.75) {
				ItemEntity skull = EntityType.ITEM.create(player.level());
				skull.setItem(Items.SKELETON_SKULL.getDefaultInstance());
				skull.moveTo(skeleton.getX(), skeleton.getY(), skeleton.getZ());
				player.level().addFreshEntity(skull);
				if (player.level() instanceof ServerLevel serverLevel) {
					bleed(serverLevel, skeleton, 8, Items.SKELETON_SKULL);
				}
				skeleton.hurt(player.damageSources().playerAttack(player), skeleton.getMaxHealth()+1);
				success = true;
			} else {
				player.level().playSound(null, skeleton, SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
				stack.hurtAndBreak(50, player, player.getEquipmentSlotForItem(stack));
				player.awardStat(Stats.ITEM_USED.get(this));
				return InteractionResult.SUCCESS;
			}
		}
		//below interaction idea by Meme Dream
		//wither skelly
		if (target instanceof WitherSkeleton skeleton && player.level() instanceof ServerLevel sl && skeleton.getHealth() <= skeleton.getMaxHealth()/2) {
			if (sl.getRandom().nextFloat() > 0.95) {
				ItemEntity skull = EntityType.ITEM.create(player.level());
				skull.setItem(Items.WITHER_SKELETON_SKULL.getDefaultInstance());
				skull.moveTo(skeleton.getX(), skeleton.getY(), skeleton.getZ());
				player.level().addFreshEntity(skull);
				if (player.level() instanceof ServerLevel serverLevel) {
					bleed(serverLevel, skeleton, 8, Items.WITHER_SKELETON_SKULL);
				}
				skeleton.hurt(player.damageSources().playerAttack(player), skeleton.getMaxHealth()+1);
				success = true;
				if (player instanceof ServerPlayer sp) {
					RisusCriterionTriggers.CARVED.get().trigger(sp);
				}
			} else {
				player.level().playSound(null, skeleton, SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
				stack.hurtAndBreak(50, player, player.getEquipmentSlotForItem(stack));
				player.awardStat(Stats.ITEM_USED.get(this));
				return InteractionResult.SUCCESS;
			}

		}
		if (success) {
			stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
			player.awardStat(Stats.ITEM_USED.get(this));
			return InteractionResult.SUCCESS;
		}
		return super.interactLivingEntity(stack, player,target, hand);
	}

	public static void bleed(ServerLevel level, Entity victim, int severity, Item item) {
		for (int i = 0; i < severity; i++) {
			level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(item)), victim.getRandomX(0.5), victim.getRandomY(), victim.getRandomZ(0.5), 1, 0, 0.0, 0.0, 0.15);
		}
	}


	//tissue carving
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		ItemStack stack = context.getItemInHand();
		Player player = context.getPlayer();
		if (player != null) {
			if (state.getBlock() instanceof DecomposingTissueBlock decomp && DecomposingBlock.getNext(state.getBlock()).isPresent()) {
				level.setBlock(pos, decomp.getNext(state).get(), 11);
				stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
				player.awardStat(Stats.ITEM_USED.get(this));
				player.level().playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WART_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
				ServerParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.CRIT, UniformInt.of(1, 6));
				return InteractionResult.SUCCESS;
			} else
				if (state.is(RisusTags.Blocks.CARVEABLE_TISSUE)) {
					level.setBlock(pos,
						state.is(RisusBlocks.LIVING_TISSUE.get()) ? RisusBlocks.ROTTED_TISSUE.get().withPropertiesOf(state) :
						state.is(RisusBlocks.ROTTED_TISSUE.get()) ? RisusBlocks.DECOMPOSED_TISSUE.get().withPropertiesOf(state) :
						state.is(RisusBlocks.DECOMPOSED_TISSUE.get()) ? RisusBlocks.DECAYED_TISSUE.get().withPropertiesOf(state) :
						RisusBlocks.BONE_WALL.get().withPropertiesOf(state)
						, 11);
					stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
					player.awardStat(Stats.ITEM_USED.get(this));
					player.level().playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WART_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
					ServerParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.CRIT, UniformInt.of(1, 6));
					return InteractionResult.SUCCESS;
				}
		}
		return super.useOn(context);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
		return false;
	}
}

