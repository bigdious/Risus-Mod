package com.bigdious.risus.block;

import com.bigdious.risus.tileentity.ModSkullTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AbstractModSkullBlock extends ContainerBlock {
	private final ModSkullBlock.SkullType skullType;

	public AbstractModSkullBlock(ModSkullBlock.SkullType skullType, AbstractBlock.Properties properties) {
		super(properties);
		this.skullType = skullType;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new ModSkullTileEntity();
	}

	@OnlyIn(Dist.CLIENT)
	public ModSkullBlock.SkullType getSkullType() {
		return this.skullType;
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
}