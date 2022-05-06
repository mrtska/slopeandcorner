package net.mrtska.slopeandcorner.edgecorner;

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
 * Edge corner block.
 */
public class EdgeCornerBlock extends SlopeBlockBase {


    private static final HashMap<SlopeType, VoxelShape> collisionShapeMap = new HashMap<>();
    private static final HashMap<SlopeType, VoxelShape> visualShapeMap = new HashMap<>();

    public EdgeCornerBlock() {
        this.setRegistryName(SlopeAndCorner.MODID, "edgecornerblock");
    }

    static {

        var box = Shapes.block();

        visualShapeMap.put(SlopeType.north, Shapes.join(box, Block.box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.east, Shapes.join(box, Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.south, Shapes.join(box, Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.west, Shapes.join(box, Block.box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.rnorth, Shapes.join(box, Block.box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.reast, Shapes.join(box, Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.rsouth, Shapes.join(box, Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D), BooleanOp.ONLY_FIRST));
        visualShapeMap.put(SlopeType.rwest, Shapes.join(box, Block.box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D), BooleanOp.ONLY_FIRST));

        var smoothStep = 0.5F;

        var shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D, i, 16.0D + smoothStep - i));
            shape = Shapes.or(shape, Block.box(i - smoothStep, 0.0D, 0.0D, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.north, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 0.0D, 0.0D, 16.0D, i, 16.0D));
            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, i - smoothStep, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.east, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, i - smoothStep, 16.0D, i, 16.0D));
            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D + smoothStep - i, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.south, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D + smoothStep - i, i, 16.0D));
            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D, i, 16.0D + smoothStep - i));
        }
        collisionShapeMap.put(SlopeType.west, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, 0.0D, 16.0D, 16.0D, 16.0D + smoothStep - i));
            shape = Shapes.or(shape, Block.box(i - smoothStep, 16.0D - i, 0.0D, 16.0D, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.rnorth, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 16.0D - i, 0.0D, 16.0D, 16.0D, 16.0D));
            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, i - smoothStep, 16.0D, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.reast, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, i - smoothStep, 16.0D, 16.0D, 16.0D));
            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, 0.0D, 16.0D + smoothStep - i, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.rsouth, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, 0.0D, 16.0D + smoothStep - i, 16.0D, 16.0D));
            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, 0.0D, 16.0D, 16.0D, 16.0D + smoothStep - i));
        }
        collisionShapeMap.put(SlopeType.rwest, shape.optimize());
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

        return Shapes.empty();
    }


    @Override
    public @Nonnull VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {

        var type = state.getValue(SlopeBlockStateProperties.SLOPE_TYPE);
        if (visualShapeMap.containsKey(type)) {

            return visualShapeMap.get(type);
        }

        return Shapes.block();
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
