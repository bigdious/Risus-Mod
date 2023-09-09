package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class RisusDamageTypes {
	public static final ResourceKey<DamageType> INEXISTENCE = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("inexistence"));
	public static final ResourceKey<DamageType> GLUTTONY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("gluttony"));

	public static void bootstrap(BootstapContext<DamageType> context) {
		context.register(INEXISTENCE, new DamageType("inexistence", 0));
		context.register(GLUTTONY, new DamageType("gluttony", 0));
	}
}
