package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class RisusDamageTypes {
	public static final ResourceKey<DamageType> INEXISTENCE = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("inexistence"));
	public static final ResourceKey<DamageType> GLUTTONY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("gluttony"));
	public static final ResourceKey<DamageType> MELANCHOLY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("melancholy"));
	public static final ResourceKey<DamageType> PLEASURE = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("pleasure"));
	public static final ResourceKey<DamageType> BLOODSLASH = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("bloodslash"));
	public static final ResourceKey<DamageType> AXED = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("axed"));
	public static final ResourceKey<DamageType> VAMPIRISM = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("vampirism"));
	public static final ResourceKey<DamageType> DESTINED_DEATH = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("destined_death"));

	public static void bootstrap(BootstrapContext<DamageType> context) {
		context.register(INEXISTENCE, new DamageType("risus.inexistence", 0));
		context.register(GLUTTONY, new DamageType("risus.gluttony", 0));
		context.register(MELANCHOLY, new DamageType("risus.melancholy", 0));
		context.register(PLEASURE, new DamageType("risus.pleasure", 0));
		context.register(BLOODSLASH, new DamageType("risus.bloodslash", 0));
		context.register(VAMPIRISM, new DamageType("risus.vampirism", 0));
		context.register(AXED, new DamageType("risus.axed", 0));
		context.register(DESTINED_DEATH, new DamageType("risus.destined_death", 0));
	}
}
