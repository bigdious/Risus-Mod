package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.util.EntityExcludedDamageSource;
import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class RisusDamageTypes {
	public static DamageSource getEntityDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity attacker, EntityType<?>... toIgnore) {
		return getIndirectEntityDamageSource(level, type, attacker, attacker, toIgnore);
	}

	public static DamageSource getIndirectEntityDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity attacker, @Nullable Entity indirectAttacker, EntityType<?>... toIgnore) {
		return toIgnore.length > 0 ? new EntityExcludedDamageSource(level.registryAccess().holderOrThrow(type), attacker, indirectAttacker, toIgnore) : new DamageSource(level.registryAccess().holderOrThrow(type), attacker, indirectAttacker);
	}
	public static final ResourceKey<DamageType> INEXISTENCE = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("inexistence"));
	public static final ResourceKey<DamageType> GLUTTONY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("gluttony"));
	public static final ResourceKey<DamageType> MELANCHOLY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("melancholy"));
	public static final ResourceKey<DamageType> PLEASURE = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("pleasure"));
	public static final ResourceKey<DamageType> BLOODSLASH = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("bloodslash"));
	public static final ResourceKey<DamageType> AXED = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("axed"));
	public static final ResourceKey<DamageType> VAMPIRISM = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("vampirism"));
	public static final ResourceKey<DamageType> DESTINED_DEATH = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("destined_death"));
	public static final ResourceKey<DamageType> REVENGE = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("revenge"));
	public static final ResourceKey<DamageType> AGONY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("agony"));

	public static void bootstrap(BootstrapContext<DamageType> context) {
		context.register(INEXISTENCE, new DamageType("risus.inexistence", 0));
		context.register(GLUTTONY, new DamageType("risus.gluttony", 0));
		context.register(MELANCHOLY, new DamageType("risus.melancholy", 0));
		context.register(PLEASURE, new DamageType("risus.pleasure", 0));
		context.register(BLOODSLASH, new DamageType("risus.bloodslash", 0));
		context.register(VAMPIRISM, new DamageType("risus.vampirism", 0));
		context.register(AXED, new DamageType("risus.axed", 0));
		context.register(DESTINED_DEATH, new DamageType("risus.destined_death", 0));
		context.register(REVENGE, new DamageType("risus.revenge", 0));
		context.register(AGONY, new DamageType("risus.agony", 0));
	}
}
