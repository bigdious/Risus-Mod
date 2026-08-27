package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.advancement.*;
import com.bigdious.risus.advancement.predicate.BasicBTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RisusCriterionTriggers {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Risus.MODID);

	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> BREAK_WEAVER_NEST = TRIGGERS.register("break_weaver_nest", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> CREATE_WEAVING_MECHANISM = TRIGGERS.register("create_weaving_mechanism", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> WITNESS_WEAVER_NEST = TRIGGERS.register("witness_weaver_nest", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> KILLED_BY_DEVOUR = TRIGGERS.register("killed_by_devour", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> HOLY_GROUNDS = TRIGGERS.register("holy_grounds", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> MIRAGE_TRAP = TRIGGERS.register("mirage_trap", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> DIVINE_AUTHORITY = TRIGGERS.register("divine_authority", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> FIVE_BOOMS = TRIGGERS.register("five_booms", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, BasicBTrigger> AGONY = TRIGGERS.register("agony", BasicBTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, LostWillTrigger> LOST_WILL = TRIGGERS.register("lost_will", LostWillTrigger::new);
}
