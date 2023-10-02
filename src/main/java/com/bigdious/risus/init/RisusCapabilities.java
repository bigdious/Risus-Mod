package com.bigdious.risus.init;

import com.bigdious.risus.capability.ExBurn;
import com.bigdious.risus.capability.ExBurnCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nonnull;

public class RisusCapabilities {
	public static final Capability<ExBurnCapability> EX_BURN = CapabilityManager.get(new CapabilityToken<>() {});

	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(ExBurnCapability.class);
	}

	public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> e) {
		if (e.getObject() instanceof LivingEntity living) {
			e.addCapability(ExBurn.ID, new ICapabilitySerializable<CompoundTag>() {

				final LazyOptional<ExBurn> inst = LazyOptional.of(() -> {
					ExBurnCapability i = new ExBurnCapability();
					i.setEntity(living);
					return i;
				});

				@Nonnull
				@Override
				public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction facing) {
					return EX_BURN.orEmpty(capability, inst.cast());
				}

				@Override
				public CompoundTag serializeNBT() {
					return inst.orElseThrow(NullPointerException::new).serializeNBT();
				}

				@Override
				public void deserializeNBT(CompoundTag nbt) {
					inst.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
				}
			});
		}
	}
}
