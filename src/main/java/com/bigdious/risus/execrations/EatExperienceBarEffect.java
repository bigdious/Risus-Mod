package com.bigdious.risus.execrations;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record EatExperienceBarEffect() implements EnchantmentEntityEffect {
	public static final MapCodec<EatExperienceBarEffect> CODEC = MapCodec.unit(EatExperienceBarEffect::new);

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		eat(enchantedItemInUse.itemStack(), entity);
	}

	public static void eat(ItemStack item, Entity entity) {
		if (entity instanceof Player player && item.getDamageValue()>0 && (player.experienceProgress>0 || player.experienceLevel>0)) {
			item.setDamageValue(item.getDamageValue()-1);
			player.giveExperiencePoints(-1);
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
