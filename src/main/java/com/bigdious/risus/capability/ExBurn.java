package com.bigdious.risus.capability;

import com.bigdious.risus.Risus;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.util.INBTSerializable;

public interface ExBurn extends INBTSerializable<CompoundTag> {
	ResourceLocation ID = Risus.prefix("cap_ex_burn");

	void setEntity(LivingEntity entity);

	void decrementHealth();

	void incrementHealth();
}
