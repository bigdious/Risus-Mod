package com.bigdious.risus.block;

public class ModSkullBlock {

/*    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_0_15;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    
    public ModSkullBlock(SkullType type, Properties p_i48446_1_) {
        super(p_i48446_1_);
        this.setDefaultState(this.stateContainer.getBaseState().with(ROTATION, Integer.valueOf(0)));
    }
    
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
     }

     public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return VoxelShapes.empty();
     }

     public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(ROTATION, Integer.valueOf(MathHelper.floor((double)(context.getPlacementYaw() * 16.0F / 360.0F) + 0.5D) & 15));
     }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new ModSkullTileEntity();
    }
    
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ROTATION, Integer.valueOf(rot.rotate(state.get(ROTATION), 16)));
     }

     public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(ROTATION, Integer.valueOf(mirrorIn.mirrorRotation(state.get(ROTATION), 16)));
     }

     protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
     }

     public interface SkullType {
     }

     public static enum Types implements SkullType {
         BLOODWYRM
     }*/

}
