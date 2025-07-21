package com.bigdious.risus.components.item;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.List;
import java.util.function.Consumer;

public record WarhornComponent (PotionContents potion) {
	public static final WarhornComponent EMPTY = new WarhornComponent(PotionContents.EMPTY);

	public static final Codec<WarhornComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		PotionContents.CODEC.optionalFieldOf("potion", PotionContents.EMPTY).forGetter(WarhornComponent::potion)
	).apply(instance, WarhornComponent::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, WarhornComponent> STREAM_CODEC = StreamCodec.composite(
		PotionContents.STREAM_CODEC, WarhornComponent::potion,
		WarhornComponent::new);

	public WarhornComponent updateContents(PotionContents potion) {
		return new WarhornComponent(potion);
	}
	public void addPotionTooltip(Consumer<Component> tooltipAdder, float durationFactor, float ticksPerSecond) {
		addPotionTooltip(this.potion.getAllEffects(), tooltipAdder, durationFactor, ticksPerSecond);
	}

	public static void addPotionTooltip(Iterable<MobEffectInstance> effects, Consumer<Component> tooltipAdder, float durationFactor, float ticksPerSecond) {
		List<Pair<Holder<Attribute>, AttributeModifier>> list = Lists.newArrayList();
		boolean flag = true;

		for (MobEffectInstance mobeffectinstance : effects) {
			flag = false;
			MutableComponent mutablecomponent = Component.translatable(mobeffectinstance.getDescriptionId());
			Holder<MobEffect> holder = mobeffectinstance.getEffect();
			( holder.value()).createModifiers(mobeffectinstance.getAmplifier(), (p_331556_, p_330860_) -> list.add(new Pair<>(p_331556_, p_330860_)));
			if (mobeffectinstance.getAmplifier() > 0) {
				mutablecomponent = Component.translatable("potion.withAmplifier", new Object[]{mutablecomponent, Component.translatable("potion.potency." + mobeffectinstance.getAmplifier())});
			}

			if (!mobeffectinstance.endsWithin(20)) {
				mutablecomponent = Component.translatable("potion.withDuration", new Object[]{mutablecomponent, MobEffectUtil.formatDuration(mobeffectinstance, durationFactor/2, ticksPerSecond)});
			}

			tooltipAdder.accept(mutablecomponent.withStyle((holder.value()).getCategory().getTooltipFormatting()));
		}
	}
}
