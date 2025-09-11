package com.bigdious.risus.execrations;

import com.bigdious.risus.entity.Angel;
import com.bigdious.risus.init.RisusEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public record ReduceAmmoEffect() implements EnchantmentEntityEffect {
	public static final MapCodec<ReduceAmmoEffect> CODEC = MapCodec.unit(ReduceAmmoEffect::new);

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		if (entity instanceof AbstractArrow arrow && serverLevel.random.nextFloat() <= 0.50) {
			ItemEntity returnedAmmo = EntityType.ITEM.create(serverLevel);
			ItemStack trial = arrow.getPickupItemStackOrigin().copy();
			returnedAmmo.moveTo(entity.getX(), entity.getY()-1, entity.getZ());
			returnedAmmo.setItem(trial);
			serverLevel.addFreshEntity(returnedAmmo);
			if (returnedAmmo.isAddedToLevel()) {
				arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
			}
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
