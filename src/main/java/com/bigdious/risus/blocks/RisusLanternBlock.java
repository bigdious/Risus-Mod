//package com.bigdious.risus.blocks;
//
//import com.bigdious.risus.blocks.interfaces.SimpleMultiloggedBlock;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.LanternBlock;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.StateDefinition;
//import net.minecraft.world.level.block.state.properties.EnumProperty;
//
//public class RisusLanternBlock extends LanternBlock implements SimpleMultiloggedBlock {
//
//	public static final EnumProperty<MultiloggingEnum> FLUIDLOGGED = MultiloggingEnum.FLUIDLOGGED;
//
//	public RisusLanternBlock(Properties properties) {
//		super(properties);
//		this.registerDefaultState(this.getStateDefinition().any()
//			.setValue(FLUIDLOGGED, MultiloggingEnum.EMPTY));
//	}
//	@Override
//	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//		builder.add(FLUIDLOGGED);
//	}
//
//}
