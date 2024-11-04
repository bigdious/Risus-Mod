package com.bigdious.risus.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.level.block.EquipableCarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class EquipableEyeBlock extends ActuallyUseableDirectionalBlock implements Equipable {
	public static final MapCodec<EquipableEyeBlock> CODEC = simpleCodec(EquipableEyeBlock::new);

	public MapCodec<EquipableEyeBlock> codec() {
		return CODEC;
	}

	public EquipableEyeBlock(BlockBehaviour.Properties p_289677_) {
		super(p_289677_);
	}

	public EquipmentSlot getEquipmentSlot() {
		return EquipmentSlot.HEAD;
	}
}
