package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.advancement.BreakWeaverNestTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusAdvancements {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Risus.MODID);

	public static final DeferredHolder<CriterionTrigger<?>, BreakWeaverNestTrigger> BREAK_WEAVER_NEST = TRIGGERS.register("break_weaver_nest", BreakWeaverNestTrigger::new);
}
