package com.bigdious.risus.execrations;

import com.bigdious.risus.Risus;
import com.bigdious.risus.init.Execrations;
import com.google.common.collect.HashMultimap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public record FierySpeedEffect() implements EnchantmentEntityEffect {
	public static final MapCodec<FierySpeedEffect> CODEC = MapCodec.unit(FierySpeedEffect::new);

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		fierySpeed(enchantedItemInUse.itemStack(), entity);
	}

	public static void fierySpeed(ItemStack item, Entity entity) {
		if (entity instanceof LivingEntity living) {
			int strength = item.get(DataComponents.ENCHANTMENTS).getLevel(living.level().registryAccess().holderOrThrow(Execrations.PYROMANIAC));
			if (living.isOnFire() && Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).getModifier(Risus.prefix("fiery_speed")) == null) {
				Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).addPermanentModifier(new AttributeModifier(Risus.prefix("fiery_speed"),  0.30*strength, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
			}
			if (!entity.isOnFire() && Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).getModifier(Risus.prefix("fiery_speed")) != null) {
				Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).removeModifier(Risus.prefix("fiery_speed"));
			}
		}
	}

	@Override
	public MapCodec<? extends EnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
