package net.mrtska.slopeandcorner.corner;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.block.SlopeBlockBase;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;
import net.mrtska.slopeandcorner.util.SlopeType;

import javax.annotation.Nonnull;
import java.util.HashMap;

/**
 * Corner block.
 */
public class CornerBlock extends SlopeBlockBase {

    private static final HashMap<SlopeType, VoxelShape> collisionShapeMap = new HashMap<>();
    private static final HashMap<SlopeType, VoxelShape> visualShapeMap = new HashMap<>();

    public CornerBlock() {
        this.setRegistryName(SlopeAndCorner.MODID, "cornerblock");
    }

    static {

        var bottom = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
        var top = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

        visualShapeMap.put(SlopeType.north, Shapes.or(bottom, Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D)));
        visualShapeMap.put(SlopeType.east, Shapes.or(bottom, Block.box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D)));
        visualShapeMap.put(SlopeType.south, Shapes.or(bottom, Block.box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D)));
        visualShapeMap.put(SlopeType.west, Shapes.or(bottom, Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D)));
        visualShapeMap.put(SlopeType.rnorth, Shapes.or(top, Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D)));
        visualShapeMap.put(SlopeType.reast, Shapes.or(top, Block.box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D)));
        visualShapeMap.put(SlopeType.rsouth, Shapes.or(top, Block.box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D)));
        visualShapeMap.put(SlopeType.rwest, Shapes.or(top, Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D)));

        visualShapeMap.put(SlopeType.north2, Shapes.join(visualShapeMap.get(SlopeType.north), Block.box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.east2, Shapes.join(visualShapeMap.get(SlopeType.east), Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.south2, Shapes.join(visualShapeMap.get(SlopeType.south), Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.west2, Shapes.join(visualShapeMap.get(SlopeType.west), Block.box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.rnorth2, Shapes.join(visualShapeMap.get(SlopeType.rnorth), Block.box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.reast2, Shapes.join(visualShapeMap.get(SlopeType.reast), Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.rsouth2, Shapes.join(visualShapeMap.get(SlopeType.rsouth), Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.rwest2, Shapes.join(visualShapeMap.get(SlopeType.rwest), Block.box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D), BooleanOp.ONLY_FIRST));

        var smoothStep = 0.25F;

        var shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 0.0D, 0.0D, 16.0D, i, 16.0D + smoothStep - i));
        }
        collisionShapeMap.put(SlopeType.north, shape);

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 0.0D, i - smoothStep, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.east, shape);

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, i - smoothStep, 16.0D - i + smoothStep, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.south, shape);

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D - i + smoothStep, i, 16.0D - i + smoothStep));
        }
        collisionShapeMap.put(SlopeType.west, shape);

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(16.0D - i , i - smoothStep, 0.0D, 16.0D, 16.0D, i));
        }
        collisionShapeMap.put(SlopeType.rnorth, shape);

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(16.0D - i, i - smoothStep, 16.0D - i, 16.0D, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.reast, shape);

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, i - smoothStep, 16.0D - i, i, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.rsouth, shape);

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16.0F; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, i - smoothStep, 0.0D, i, 16.0D, i));
        }
        collisionShapeMap.put(SlopeType.rwest, shape);

        smoothStep = 2F;
        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(x - smoothStep, 0.0D, 0.0D, x, x - z + smoothStep, z));
            }
        }
        collisionShapeMap.put(SlopeType.north2, shape);

        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(x - smoothStep, 0.0D, 16.0D - z, x, x - z + smoothStep, 16.0D));
            }
        }
        collisionShapeMap.put(SlopeType.east2, shape);

        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(16.0D - x, 0.0D, 16.0D - z, 16.0D - x + smoothStep, x - z + smoothStep, 16.0D));
            }
        }
        collisionShapeMap.put(SlopeType.south2, shape);

        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(16.0D - x, 0.0D, 0.0D, 16.0D - x + smoothStep, x - z + smoothStep, z));
            }
        }
        collisionShapeMap.put(SlopeType.west2, shape);

        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(x - smoothStep, 16.0D - (x - z) - smoothStep, 0.0D, x, 16.0D, z));
            }
        }
        collisionShapeMap.put(SlopeType.rnorth2, shape);

        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(x - smoothStep, 16.0D - (x - z) - smoothStep, 16.0D - z, x, 16.0D, 16.0D));
            }
        }
        collisionShapeMap.put(SlopeType.reast2, shape);

        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(16.0D - x, 16.0D - (x - z) - smoothStep, 16.0D - z, 16.0D - x + smoothStep, 16.0D, 16.0D));
            }
        }
        collisionShapeMap.put(SlopeType.rsouth2, shape);

        shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(16.0D - x, 16.0D - (x - z) - smoothStep, 0.0D, 16.0D - x + smoothStep, 16.0D, z));
            }
        }
        collisionShapeMap.put(SlopeType.rwest2, shape);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {

        var state = this.defaultBlockState();
        var tag = context.getItemInHand().getTag();

        if (tag == null) {
            return state;
        }

        var fluidState = context.getLevel().getFluidState(context.getClickedPos());
        var blockType = tag.getString("BlockType");
        var texture = tag.getString("Texture");
        var type2 = blockType.endsWith("2");
        var slopeType = "";

        if (blockType.startsWith("R")) {
            slopeType = "R";
        }

        var rotationYaw = ((int) Mth.wrapDegrees((context.getPlayer().getYRot() + 180F) * 4F / 360F)) & 3;

        switch (rotationYaw) {
            case 0 -> slopeType += "NORTH";
            case 1 -> slopeType += "EAST";
            case 2 -> slopeType += "SOUTH";
            case 3 -> slopeType += "WEST";
        }

        if (type2) {
            slopeType += "2";
        }

        return state.setValue(SlopeBlockStateProperties.SLOPE_TYPE, SlopeType.valueOf(slopeType.toLowerCase()))
                .setValue(SlopeBlockStateProperties.TRANSPARENT, texture.contains("glass"))
                .setValue(BlockStateProperties.WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public @Nonnull VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {

        var type = state.getValue(SlopeBlockStateProperties.SLOPE_TYPE);
        if (collisionShapeMap.containsKey(type)) {

            return collisionShapeMap.get(type);
        }

        var smoothStep = 2F;

        var shape = Shapes.empty();
        for (float x = smoothStep; x <= 16; x += smoothStep) {
            for (float z = smoothStep; z <= x; z += smoothStep) {
                shape = Shapes.or(shape, Block.box(x - smoothStep, 16.0D - (x - z) - smoothStep, 0.0D, x, 16.0D, z));
            }
        }
        return shape;
    }


    @Override
    public @Nonnull VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {

        var type = state.getValue(SlopeBlockStateProperties.SLOPE_TYPE);
        if (visualShapeMap.containsKey(type)) {

            return visualShapeMap.get(type);
        }

        return Shapes.empty();
    }

    @Override
    public @Nonnull VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos) {

        // No occlusion when block is transparent.
        if (state.getValue(SlopeBlockStateProperties.TRANSPARENT)) {
            return Shapes.empty();
        }

        var type = state.getValue(SlopeBlockStateProperties.SLOPE_TYPE);
        if (collisionShapeMap.containsKey(type)) {
            return collisionShapeMap.get(type);
        }
        return Shapes.empty(); //collisionShapeMap.get(SlopeType.north);
    }
}
