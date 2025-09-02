package com.bigdious.risus.execrations;

import com.bigdious.risus.init.RisusDamageTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record TakeRevengeOnImbecileEffect() implements EnchantmentEntityEffect {
	public static final MapCodec<TakeRevengeOnImbecileEffect> CODEC = MapCodec.unit(TakeRevengeOnImbecileEffect::new);

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		revenge( enchantedItemInUse.owner(), enchantedItemInUse.itemStack(), entity);
	}

	public static void revenge(LivingEntity attacker, ItemStack item, Entity entity) {
		if (item.getDamageValue()==item.getMaxDamage()-1) {
			//item set to 20%
			item.setDamageValue((item.getMaxDamage()/5)*4);
			attacker.hurt(entity.damageSources().source(RisusDamageTypes.REVENGE), attacker.getMaxHealth());

		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
