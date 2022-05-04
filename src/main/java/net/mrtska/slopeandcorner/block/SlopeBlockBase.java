package net.mrtska.slopeandcorner.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.mrtska.slopeandcorner.slope.SlopeBlockEntity;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Base class of slope blocks.
 */
public abstract class SlopeBlockBase extends Block implements EntityBlock, SimpleWaterloggedBlock {

    public SlopeBlockBase() {
        super(Properties.of(Material.STONE));
    }

    @OverridingMethodsMustInvokeSuper
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.WATERLOGGED);
        builder.add(SlopeBlockStateProperties.TRANSPARENT);
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return true;
    }

    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull PathComputationType type) {
        return false;
    }

    public @Nonnull FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nonnull BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction direction, @Nonnull BlockState state2, @Nonnull LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockPos pos2) {

        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, state2, level, pos, pos2);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {

        if (level.getBlockEntity(pos) instanceof SlopeBlockEntity blockEntity) {

            var blockName = blockEntity.getBlockName();

            var block = Registry.BLOCK.get(new ResourceLocation(blockName));

            return block.getSoundType(block.defaultBlockState(), level, BlockPos.ZERO, entity);
        }

        return super.getSoundType(state, level, pos, entity);
    }
}
