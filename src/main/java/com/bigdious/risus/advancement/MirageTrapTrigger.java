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

public class MirageTrapTrigger extends SimpleCriterionTrigger<MirageTrapTrigger.TriggerInstance> {

	@Override
	public Codec<MirageTrapTrigger.TriggerInstance> codec() {
		return MirageTrapTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {

		public static final Codec<MirageTrapTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(MirageTrapTrigger.TriggerInstance::player))
			.apply(instance, MirageTrapTrigger.TriggerInstance::new));

		public static Criterion<MirageTrapTrigger.TriggerInstance> getFooled() {
			return RisusAdvancements.MIRAGE_TRAP.get().createCriterion(new MirageTrapTrigger.TriggerInstance(Optional.empty()));
		}
	}
}
