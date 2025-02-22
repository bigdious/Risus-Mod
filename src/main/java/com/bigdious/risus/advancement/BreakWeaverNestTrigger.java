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

public class BreakWeaverNestTrigger extends SimpleCriterionTrigger<BreakWeaverNestTrigger.TriggerInstance> {

	@Override
	public Codec<TriggerInstance> codec() {
		return BreakWeaverNestTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {

		public static final Codec<BreakWeaverNestTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(BreakWeaverNestTrigger.TriggerInstance::player))
			.apply(instance, BreakWeaverNestTrigger.TriggerInstance::new));

		public static Criterion<TriggerInstance> breakNest() {
			return RisusAdvancements.BREAK_WEAVER_NEST.get().createCriterion(new TriggerInstance(Optional.empty()));
		}
	}
}
