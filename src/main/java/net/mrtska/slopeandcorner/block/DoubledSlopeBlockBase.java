package net.mrtska.slopeandcorner.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Base class of doubled slope blocks.
 */
public class DoubledSlopeBlockBase extends SlopeBlockBase {

    @OverridingMethodsMustInvokeSuper
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SlopeBlockStateProperties.SLOPE_TYPE2);
    }

    @Override
    public @Nonnull VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public @Nonnull VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return Shapes.block();
    }


    @Override
    public @Nonnull VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos) {

        // No occlusion when block is transparent.
        if (state.getValue(SlopeBlockStateProperties.TRANSPARENT)) {
            return Shapes.empty();
        }

        return Shapes.block();
    }

    @Override
    public boolean canPlaceLiquid(@Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull BlockState state,  @Nonnull Fluid fluid) {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new DoubledSlopeBlockEntity(pos, state);
    }
}
