package net.latenighters.cits.common.blocks.jukebox;

import com.simibubi.create.content.contraptions.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.ITE;
import net.latenighters.cits.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.JukeboxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class BlockJukebox extends HorizontalKineticBlock implements ITE<TileJukebox> {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final VoxelShape JUKEBOX_SHAPE = makeCuboidShape(0,0,0,16,12,16);

    public BlockJukebox() {
        this(Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(1.5f)
                .setOpaque((state, foo, bar) -> false)
                .notSolid()
        );
    }

    public BlockJukebox(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public TileEntity createTileEntity(BlockState blockState, IBlockReader iBlockReader) {
        return Registration.JUKEBOX_TILE.create();
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState blockState) {
        return blockState.get(HORIZONTAL_FACING).getAxis();
    }

    @Override
    public Class<TileJukebox> getTileEntityClass() {
        return TileJukebox.class;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return JUKEBOX_SHAPE;
    }

    @Override
    public boolean hasShaftTowards(IWorldReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.get(HORIZONTAL_FACING);
    }

    @Override
    public boolean hasIntegratedCogwheel(IWorldReader world, BlockPos pos, BlockState state) {
        return false;
    }


}
