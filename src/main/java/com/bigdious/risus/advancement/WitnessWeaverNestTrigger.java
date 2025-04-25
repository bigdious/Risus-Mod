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

public class WitnessWeaverNestTrigger extends SimpleCriterionTrigger<WitnessWeaverNestTrigger.TriggerInstance> {

	@Override
	public Codec<WitnessWeaverNestTrigger.TriggerInstance> codec() {
		return WitnessWeaverNestTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {

		public static final Codec<WitnessWeaverNestTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(WitnessWeaverNestTrigger.TriggerInstance::player))
			.apply(instance, WitnessWeaverNestTrigger.TriggerInstance::new));

		public static Criterion<WitnessWeaverNestTrigger.TriggerInstance> witnessNest() {
			return RisusAdvancements.WITNESS_WEAVER_NEST.get().createCriterion(new WitnessWeaverNestTrigger.TriggerInstance(Optional.empty()));
		}
	}
}