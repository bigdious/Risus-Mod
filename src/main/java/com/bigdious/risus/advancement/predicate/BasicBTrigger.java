package com.bigdious.risus.advancement.predicate;

import com.bigdious.risus.init.RisusCriterionTriggers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class BasicBTrigger extends SimpleCriterionTrigger<BasicBTrigger.TriggerInstance> {

	@Override
	public Codec<TriggerInstance> codec() {
		return BasicBTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (instance) -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {

		public static final Codec<BasicBTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(BasicBTrigger.TriggerInstance::player))
			.apply(instance, BasicBTrigger.TriggerInstance::new));

		public static Criterion<TriggerInstance> breakNest() {
			return RisusCriterionTriggers.BREAK_WEAVER_NEST.get().createCriterion(new TriggerInstance(Optional.empty()));
		}

		public static Criterion<TriggerInstance> createWeavingMechanism() {
			return RisusCriterionTriggers.CREATE_WEAVING_MECHANISM.get().createCriterion(new TriggerInstance(Optional.empty()));
		}

		public static Criterion<TriggerInstance> witnessNest() {
			return RisusCriterionTriggers.WITNESS_WEAVER_NEST.get().createCriterion(new TriggerInstance(Optional.empty()));
		}

		public static Criterion<TriggerInstance> getFooled() {
			return RisusCriterionTriggers.MIRAGE_TRAP.get().createCriterion(new TriggerInstance(Optional.empty()));
		}

		public static Criterion<TriggerInstance> getSmitten() {
			return RisusCriterionTriggers.HOLY_GROUNDS.get().createCriterion(new TriggerInstance(Optional.empty()));
		}
		public static Criterion<TriggerInstance> getGood() {
			return RisusCriterionTriggers.KILLED_BY_DEVOUR.get().createCriterion(new TriggerInstance(Optional.empty()));
		}
		public static Criterion<TriggerInstance> getJuked() {
			return RisusCriterionTriggers.DIVINE_AUTHORITY.get().createCriterion(new TriggerInstance(Optional.empty()));
		}
	}
}
