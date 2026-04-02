package com.bigdious.risus.advancement;

import com.bigdious.risus.init.RisusCriterionTriggers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class LostWillTrigger extends SimpleCriterionTrigger<LostWillTrigger.TriggerInstance> {

	@Override
	public Codec<TriggerInstance> codec() {
		return LostWillTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, int ordering) {

		this.trigger(player, (instance) -> instance.matches(ordering));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<MinMaxBounds.Ints> ordering) implements SimpleInstance {

		public static final Codec<LostWillTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(LostWillTrigger.TriggerInstance::player),
				MinMaxBounds.Ints.CODEC.optionalFieldOf("ordering").forGetter(TriggerInstance::ordering))
			.apply(instance, LostWillTrigger.TriggerInstance::new));

		public static Criterion<TriggerInstance> readWill(int ordering) {
			return RisusCriterionTriggers.LOST_WILL.get().createCriterion(new TriggerInstance(Optional.empty(), Optional.of(MinMaxBounds.Ints.exactly(ordering))));
		}

		public boolean matches(int ordering) {
			return this.ordering.isEmpty() || this.ordering.get().matches(ordering);
		}
	}
}
