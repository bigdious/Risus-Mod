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

public class KilledByDevourTrigger extends SimpleCriterionTrigger<KilledByDevourTrigger.TriggerInstance> {

	@Override
	public Codec<KilledByDevourTrigger.TriggerInstance> codec() {
		return KilledByDevourTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {

		public static final Codec<KilledByDevourTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(KilledByDevourTrigger.TriggerInstance::player))
			.apply(instance, KilledByDevourTrigger.TriggerInstance::new));

		public static Criterion<KilledByDevourTrigger.TriggerInstance> getgood() {
			return RisusAdvancements.KILLED_BY_DEVOUR.get().createCriterion(new KilledByDevourTrigger.TriggerInstance(Optional.empty()));
		}
	}
}
