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

public class CreateWeavingMechanismTrigger extends SimpleCriterionTrigger<CreateWeavingMechanismTrigger.TriggerInstance> {

	@Override
	public Codec<TriggerInstance> codec() {
		return CreateWeavingMechanismTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {

		public static final Codec<CreateWeavingMechanismTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(CreateWeavingMechanismTrigger.TriggerInstance::player))
			.apply(instance, CreateWeavingMechanismTrigger.TriggerInstance::new));

		public static Criterion<TriggerInstance> createWeavingMechanism() {
			return RisusAdvancements.CREATE_WEAVING_MECHANISM.get().createCriterion(new TriggerInstance(Optional.empty()));
		}
	}
}
