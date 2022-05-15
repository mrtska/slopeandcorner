package net.mrtska.slopeandcorner.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.HitResult;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;
import net.mrtska.slopeandcorner.util.SlopeType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Base class of slope blocks.
 */
public abstract class SlopeBlockBase extends Block implements EntityBlock, SimpleWaterloggedBlock {

    public SlopeBlockBase() {
        super(Properties.of(Material.STONE));
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE)
                .setValue(SlopeBlockStateProperties.TRANSPARENT, false).setValue(SlopeBlockStateProperties.SLOPE_TYPE, SlopeType.north));
    }

    @OverridingMethodsMustInvokeSuper
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.WATERLOGGED);
        builder.add(SlopeBlockStateProperties.TRANSPARENT);
        builder.add(SlopeBlockStateProperties.SLOPE_TYPE);
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return true;
    }

    @Override
    public boolean isCollisionShapeFullBlock(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return false;
    }

    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public float getShadeBrightness(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return 1F;
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

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {

        if (level.getBlockEntity(pos) instanceof SlopeBlockEntity entity) {

            var stack = new ItemStack(this);
            var tag = new CompoundTag();
            tag.putString("BlockName", entity.getBlockName());
            tag.putString("Texture", entity.getTexture());

            var type = entity.getBlockType();
            var newType = "";
            if (type.startsWith("R")) {
                newType += "R";
            }
            // Only use south direction.
            newType += "SOUTH";
            if (type.endsWith("2")) {
                newType += "2";
            }
            tag.putString("BlockType", newType);

            stack.setTag(tag);

            return stack;
        } else {

            var stack = new ItemStack(this);
            var tag = new CompoundTag();
            tag.putString("BlockName", "spruce_planks");
            tag.putString("BlockType", "SOUTH");
            tag.putString("Texture", "spruce_planks");

            stack.setTag(tag);

            return stack;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SlopeBlockEntity(pos, state);
    }
}
