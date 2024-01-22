package com.bigdious.risus.init;

import com.bigdious.risus.Risus;
import com.bigdious.risus.util.EntityExcludedDamageSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class RisusDamageTypes {
	public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type, EntityType<?>... toIgnore) {
		return getEntityDamageSource(level, type, null, toIgnore);
	}

	public static DamageSource getEntityDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity attacker, EntityType<?>... toIgnore) {
		return getIndirectEntityDamageSource(level, type, attacker, attacker, toIgnore);
	}

	public static DamageSource getIndirectEntityDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity attacker, @Nullable Entity indirectAttacker, EntityType<?>... toIgnore) {
		return toIgnore.length > 0 ? new EntityExcludedDamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), toIgnore) : new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), attacker, indirectAttacker);
	}
	public static final ResourceKey<DamageType> INEXISTENCE = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("inexistence"));
	public static final ResourceKey<DamageType> GLUTTONY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("gluttony"));
	public static final ResourceKey<DamageType> MELANCHOLY = ResourceKey.create(Registries.DAMAGE_TYPE, Risus.prefix("melancholy"));




	public static void bootstrap(BootstapContext<DamageType> context) {
		context.register(INEXISTENCE, new DamageType("inexistence", 0));
		context.register(GLUTTONY, new DamageType("gluttony", 0));
		context.register(MELANCHOLY, new DamageType("melancholy", 0));
	}
}
