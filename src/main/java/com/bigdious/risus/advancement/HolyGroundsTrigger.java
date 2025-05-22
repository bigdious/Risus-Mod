package com.bigdious.risus.advancement;

import com.bigdious.risus.init.RisusAdvancements;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class HolyGroundsTrigger extends SimpleCriterionTrigger<HolyGroundsTrigger.TriggerInstance> {

	@Override
	public Codec<HolyGroundsTrigger.TriggerInstance> codec() {
		return HolyGroundsTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {

		public static final Codec<HolyGroundsTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(HolyGroundsTrigger.TriggerInstance::player))
			.apply(instance, HolyGroundsTrigger.TriggerInstance::new));

		public static Criterion<HolyGroundsTrigger.TriggerInstance> getsmitten() {
			return RisusAdvancements.HOLY_GROUNDS.get().createCriterion(new HolyGroundsTrigger.TriggerInstance(Optional.empty()));
		}
	}
}
