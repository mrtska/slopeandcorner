package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.block.SlopeBlockBase;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;


public class SlopeBlock extends SlopeBlockBase {

    private static final HashMap<SlopeType, VoxelShape> collisionShapeMap = new HashMap<>();
    private static final HashMap<SlopeType, VoxelShape> visualShapeMap = new HashMap<>();

    public SlopeBlock() {
        this.setRegistryName(SlopeAndCorner.MODID, "slopeblock");
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE)
                .setValue(SlopeBlockStateProperties.TRANSPARENT, false).setValue(SlopeBlockStateProperties.SLOPE_TYPE, SlopeType.north));
    }


    static {

        var bottom = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

        visualShapeMap.put(SlopeType.north, Shapes.or(bottom, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D)));
        visualShapeMap.put(SlopeType.east, Shapes.or(bottom, Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D)));
        visualShapeMap.put(SlopeType.south, Shapes.or(bottom, Block.box(0.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D)));
        visualShapeMap.put(SlopeType.west, Shapes.or(bottom, Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 16.0D)));

        var shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D, i, 16.0D - i));
        }
        collisionShapeMap.put(SlopeType.north, shape.optimize());

        shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(i, 0.0D, 0.0D, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.east, shape.optimize());

        shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, i, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.south, shape.optimize());

        shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D - i, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.west, shape.optimize());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SlopeBlockStateProperties.SLOPE_TYPE);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {

        var state = this.defaultBlockState();
        var rotationYaw = ((int) Mth.wrapDegrees((context.getPlayer().getYRot() + 180F) * 4F / 360F + 0.5F)) & 3;
        var tag = context.getItemInHand().getTag();

        if (tag == null) {
            return state;
        }

        var blockType = tag.getString("BlockType");
        var texture = tag.getString("Texture");
        var direction = "";

        if (blockType.startsWith("R")) {
            direction = "R";
        }

        switch (rotationYaw) {
            case 0 -> direction += "NORTH";
            case 1 -> direction += "EAST";
            case 2 -> direction += "SOUTH";
            case 3 -> direction += "WEST";
        }

        if (blockType.endsWith("2")) {
            direction += "2";
        }

        return state.setValue(SlopeBlockStateProperties.SLOPE_TYPE, SlopeType.valueOf(direction.toLowerCase())).setValue(SlopeBlockStateProperties.TRANSPARENT, texture.contains("glass"));

    }

    @Override
    public @Nonnull VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {

        SlopeType type = state.getValue(SlopeBlockStateProperties.SLOPE_TYPE);
        if (collisionShapeMap.containsKey(type)) {

            return collisionShapeMap.get(type);
        }

        if (getter.getBlockEntity(pos) instanceof SlopeBlockEntity entity) {

            String blockType = entity.getBlockType();

            if (collisionShapeMap.containsKey(blockType)) {

                return visualShapeMap.get(blockType);
            }
            var shape = Block.box(0.0D, 0.0D, 0.0D, 0, 0, 0);
            for (float i = 0; i <= 16; i += 1) {

                shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D, i, 16.0D - i));
            }
            return shape;
        }

        return Shapes.empty();
    }


    @Override
    public @Nonnull VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {

        SlopeType type = state.getValue(SlopeBlockStateProperties.SLOPE_TYPE);
        if (visualShapeMap.containsKey(type)) {

            return visualShapeMap.get(type);
        }

        return Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
                , Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 16.0D));

    }

    @Override
    public @Nonnull VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos) {

        if (state.getValue(SlopeBlockStateProperties.TRANSPARENT)) {
            return Shapes.empty();
        }

        SlopeType type = state.getValue(SlopeBlockStateProperties.SLOPE_TYPE);
        if (visualShapeMap.containsKey(type)) {
            return visualShapeMap.get(type);
        }
        return collisionShapeMap.get(SlopeType.north);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SlopeBlockEntity(pos, state);
    }
}
