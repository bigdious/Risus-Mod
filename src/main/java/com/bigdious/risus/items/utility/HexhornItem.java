package com.bigdious.risus.items.utility;

import com.bigdious.risus.client.particle.MobEffectParticleOption;
import com.bigdious.risus.components.item.WarhornComponent;
import com.bigdious.risus.config.RisusConfig;
import com.bigdious.risus.entity.creatures.pets.TamableMonster;
import com.bigdious.risus.init.RisusDataComponents;
import com.bigdious.risus.init.RisusItems;
import com.bigdious.risus.init.RisusParticles;
import com.bigdious.risus.init.RisusTags;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class HexhornItem extends WarhornItem{
	public HexhornItem(Properties properties, TagKey<Instrument> instruments) {
		super(properties, instruments);
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		WarhornComponent warhornContent = stack.getOrDefault(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
		Optional<? extends Holder<Instrument>> optional = this.getInstrument(stack);
		boolean used = false;
		if (optional.isPresent()) {
			Instrument instrument = (Instrument)((Holder)optional.get()).value();
			player.startUsingItem(hand);
			play(level, player, instrument);
			player.getCooldowns().addCooldown(this, 1200-100*stack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.QUICK_CHARGE))));
			player.awardStat(Stats.ITEM_USED.get(this));
			used = true;
		}
		if (warhornContent.potion() != PotionContents.EMPTY && (!RisusConfig.hornsUsePotionCharges || stack.getOrDefault(RisusDataComponents.POTION_CHARGES, 0) > 0)) {
			List<Entity> targets = level.getEntities(player, player.getBoundingBox().inflate(50D + 10*stack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.POWER)))));
			if (player.getTeam() != null && RisusConfig.hornsPrioritizeTeams) {
				for (Entity maybeBingo : targets) {
					if (maybeBingo instanceof LivingEntity living && living.getTeam() != player.getTeam()) {
						for (MobEffectInstance mobeffectinstance : warhornContent.potion().getAllEffects()) {
							if (mobeffectinstance.getEffect().value().isInstantenous()) {
								mobeffectinstance.getEffect().value().applyInstantenousEffect(player, player, living, mobeffectinstance.getAmplifier(), 1.0D);
							} else {
								living.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), mobeffectinstance.getDuration() / (player.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.CROWN_OF_BONES.get()) ? 3 : 2), mobeffectinstance.getAmplifier() + (player.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.CROWN_OF_BONES.get()) ? 1 : 0)));
							}
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
						if ((living instanceof Monster || living.getType().is(RisusTags.Entities.HEXHORN_ALLOWED) || (RisusConfig.reverseHornsPlayerBehavior ? living instanceof Player : living.getType().is(RisusTags.Entities.HEXHORN_ALLOWED))) && !living.getType().is(RisusTags.Entities.HEXHORN_BANNED) && !(living instanceof TamableMonster tamableMonster && tamableMonster.getOwner() != null)) {
							for (MobEffectInstance mobeffectinstance : warhornContent.potion().getAllEffects()) {
								if (mobeffectinstance.getEffect().value().isInstantenous()) {
									mobeffectinstance.getEffect().value().applyInstantenousEffect(player, player, living, mobeffectinstance.getAmplifier(), 1.0D);
								} else {
									living.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), mobeffectinstance.getDuration() / (player.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.CROWN_OF_BONES.get()) ? 3 : 2), mobeffectinstance.getAmplifier() + (player.getItemBySlot(EquipmentSlot.HEAD).is(RisusItems.CROWN_OF_BONES.get()) ? 1 : 0)));
								}
								living.igniteForTicks(stack.getEnchantmentLevel((level.registryAccess().holderOrThrow(Enchantments.FLAME)))>0 ? 200 : 0);
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
			player.awardStat(Stats.ITEM_USED.get(this));
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			if (RisusConfig.hornsUsePotionCharges) {
				stack.set(RisusDataComponents.POTION_CHARGES, stack.getOrDefault(RisusDataComponents.POTION_CHARGES, 1) - 1);
				if (stack.getOrDefault(RisusDataComponents.POTION_CHARGES, 0) < 1) {
					stack.set(RisusDataComponents.WARHORN_CONTENT, WarhornComponent.EMPTY);
					stack.set(RisusDataComponents.POTION_CHARGES, 0);
				}
			}
			return InteractionResultHolder.consume(stack);
		}
		return InteractionResultHolder.fail(player.getItemInHand(hand));
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.HEXHORN_ALLOWED_ENCHANTS);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment.is(RisusTags.Enchantments.HEXHORN_ALLOWED_ENCHANTS);
	}
}
