package com.bigdious.risus.items.utility;

import com.bigdious.risus.Risus;
import com.bigdious.risus.client.particle.MobEffectParticleOption;
import com.bigdious.risus.components.item.WarhornComponent;
import com.bigdious.risus.entity.TamableMonster;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class WarhornItem extends InstrumentItem {
	private final TagKey<Instrument> instruments;
	public WarhornItem(Properties properties, TagKey<Instrument> instruments) {
		super(properties, instruments);
		this.instruments = instruments;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		tooltipComponents.add(Component.literal("Potion:" + stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY).potion()).withStyle(ChatFormatting.GRAY));
	}

	private Optional<Holder<Instrument>> getInstrument(ItemStack stack) {
		Holder<Instrument> holder = stack.get(DataComponents.INSTRUMENT);
		if (holder != null) {
			return Optional.of(holder);
		} else {
			Iterator<Holder<Instrument>> iterator = BuiltInRegistries.INSTRUMENT.getTagOrEmpty(this.instruments).iterator();
			return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
		}
	}

	@Override
	public ItemStack getDefaultInstance() {
		ItemStack itemstack = super.getDefaultInstance();
		itemstack.set(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
		return itemstack;
	}

	@Override
	public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
		WarhornComponent warhornContents = stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
		PotionContents potionContents = other.get(DataComponents.POTION_CONTENTS);

		if (action == ClickAction.SECONDARY && potionContents != null) {
				if (!player.getAbilities().instabuild) {
					other.shrink(1);
					if (!player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE))) {
						player.drop(new ItemStack(Items.GLASS_BOTTLE), false);
					}
				}
				this.changeAndConsumeWarhorn(stack, warhorn -> warhorn.update(RisusDataComponents.WARHORN_CONTENT, warhornContents, component -> component.updateContents(potionContents)));
				return true;
		}
		return false;
	}

	private void changeAndConsumeWarhorn(ItemStack stack, Consumer<ItemStack> onDrink) {
		onDrink.accept(stack);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		WarhornComponent warhornContent = stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
		Optional<? extends Holder<Instrument>> optional = this.getInstrument(stack);
		boolean used = false;
		if (optional.isPresent()) {
			Instrument instrument = (Instrument)((Holder)optional.get()).value();
			player.startUsingItem(hand);
			play(level, player, instrument);
			player.getCooldowns().addCooldown(this, 1200);
			player.awardStat(Stats.ITEM_USED.get(this));
			used = true;
		}
		if (warhornContent.potion() != PotionContents.EMPTY) {
			for (MobEffectInstance mobeffectinstance : warhornContent.potion().getAllEffects()) {
				player.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), mobeffectinstance.getDuration()/2, mobeffectinstance.getAmplifier()));
				if (level instanceof ServerLevel serverLevel) {
					serverLevel.sendParticles(new MobEffectParticleOption(RisusParticles.MOB_EFFECT_ICON.get(), new MobEffectInstance(mobeffectinstance)), player.getX(), player.getEyeY(), player.getZ(), 1, 0, 0.0, 0.0, 0.2);
				}
			}
			List<Entity> targets = level.getEntities(player, player.getBoundingBox().inflate(20D));
			if (player.getTeam() != null) {
				for (Entity maybeBingo : targets) {
					if (maybeBingo instanceof LivingEntity living && living.getTeam() == player.getTeam()) {
						for (MobEffectInstance mobeffectinstance : warhornContent.potion().getAllEffects()) {
							living.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), mobeffectinstance.getDuration()/2, mobeffectinstance.getAmplifier()));
							if (level instanceof ServerLevel serverLevel) {
								serverLevel.sendParticles(new MobEffectParticleOption(RisusParticles.MOB_EFFECT_ICON.get(), new MobEffectInstance(mobeffectinstance)), maybeBingo.getX(), maybeBingo.getEyeY(), maybeBingo.getZ(), 1, 0, 0.0, 0.0, 0.2);
							}
						}
						used = true;
					}
				}
			} else {
				for (Entity maybeBingo : targets) {
					if (maybeBingo instanceof LivingEntity living) {
						if (living instanceof Player || living.getType().is(RisusTags.Entities.HORN_BUFFS) ||(living instanceof TamableAnimal tamableAnimal && tamableAnimal.getOwner().is(player)) || (living instanceof TamableMonster tamableMonster && tamableMonster.getOwner().is(player))) {
							for (MobEffectInstance mobeffectinstance : warhornContent.potion().getAllEffects()) {
								living.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), mobeffectinstance.getDuration()/2, mobeffectinstance.getAmplifier()));
								if (level instanceof ServerLevel serverLevel) {
									serverLevel.sendParticles(new MobEffectParticleOption(RisusParticles.MOB_EFFECT_ICON.get(), new MobEffectInstance(mobeffectinstance)), maybeBingo.getX(), maybeBingo.getEyeY(), maybeBingo.getZ(), 1, 0, 0.0, 0.0, 0.2);
								}
							}
							used = true;
						}
					}
				}
			}
		}


		if (used) {
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			return InteractionResultHolder.consume(stack);
		}
		return InteractionResultHolder.fail(player.getItemInHand(hand));
	}
	private static void play(Level level, Player player, Instrument instrument) {
		SoundEvent soundevent = instrument.soundEvent().value();
		float f = instrument.range() / 16.0F;
		level.playSound(player, player, soundevent, SoundSource.RECORDS, f, 1.0F);
		level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
	}

//	@Override
//	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
//		WarhornComponent warhornContent = stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
//		if (warhornContent.potion() != PotionContents.EMPTY) {
//			if (entity instanceof Player player) {
//				if (!level.isClientSide()) {
//					for (MobEffectInstance mobeffectinstance : warhornContent.potion().getAllEffects()) {
//						if (mobeffectinstance.getEffect().value().isInstantenous()) {
//							mobeffectinstance.getEffect().value().applyInstantenousEffect(player, player, player, mobeffectinstance.getAmplifier(), 1.0D);
//						} else {
//							player.addEffect(new MobEffectInstance(mobeffectinstance));
//						}
//					}
//				}
//				player.awardStat(Stats.ITEM_USED.get(this));
//			}
//		}
//		return super.finishUsingItem(stack, level, entity);
//	}
}
