package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.advancement.BreakWeaverNestTrigger;
import com.bigdious.risus.advancement.KilledByDevourTrigger;
import com.bigdious.risus.advancement.WitnessWeaverNestTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusAdvancements {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Risus.MODID);

	public static final DeferredHolder<CriterionTrigger<?>, BreakWeaverNestTrigger> BREAK_WEAVER_NEST = TRIGGERS.register("break_weaver_nest", BreakWeaverNestTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, WitnessWeaverNestTrigger> WITNESS_WEAVER_NEST = TRIGGERS.register("witness_weaver_nest", WitnessWeaverNestTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, KilledByDevourTrigger> KILLED_BY_DEVOUR = TRIGGERS.register("killed_by_devour", KilledByDevourTrigger::new);
}
