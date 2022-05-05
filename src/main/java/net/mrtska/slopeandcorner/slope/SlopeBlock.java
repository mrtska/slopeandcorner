package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.block.SlopeBlockBase;
import net.mrtska.slopeandcorner.util.SlopeBlockStateProperties;
import net.mrtska.slopeandcorner.util.SlopeType;

import javax.annotation.Nonnull;
import java.util.HashMap;


public class SlopeBlock extends SlopeBlockBase {

    private static final HashMap<SlopeType, VoxelShape> collisionShapeMap = new HashMap<>();
    private static final HashMap<SlopeType, VoxelShape> visualShapeMap = new HashMap<>();

    public SlopeBlock() {
        this.setRegistryName(SlopeAndCorner.MODID, "slopeblock");
    }


    static {

        var bottom = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
        var top = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        var north = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
        var east = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        var south = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
        var west = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);

        visualShapeMap.put(SlopeType.north, Shapes.or(bottom, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D)));
        visualShapeMap.put(SlopeType.east, Shapes.or(bottom, Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D)));
        visualShapeMap.put(SlopeType.south, Shapes.or(bottom, Block.box(0.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D)));
        visualShapeMap.put(SlopeType.west, Shapes.or(bottom, Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 16.0D)));
        visualShapeMap.put(SlopeType.rnorth, Shapes.or(top, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D)));
        visualShapeMap.put(SlopeType.reast, Shapes.or(top, Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)));
        visualShapeMap.put(SlopeType.rsouth, Shapes.or(top, Block.box(0.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D)));
        visualShapeMap.put(SlopeType.rwest, Shapes.or(top, Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 16.0D)));
        visualShapeMap.put(SlopeType.north2, Shapes.or(north, east));
        visualShapeMap.put(SlopeType.east2, Shapes.or(east, south));
        visualShapeMap.put(SlopeType.south2, Shapes.or(south, west));
        visualShapeMap.put(SlopeType.west2, Shapes.or(west, north));

        var smoothStep = 0.25F;

        var shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D, i, 16.0D + smoothStep - i));
        }
        collisionShapeMap.put(SlopeType.north, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 0.0D, 0.0D, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.east, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, i - smoothStep, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.south, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D + smoothStep - i, i, 16.0D));
        }
        collisionShapeMap.put(SlopeType.west, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, 0.0D, 16.0D, 16.0D, 16.0D + smoothStep - i));
        }
        collisionShapeMap.put(SlopeType.rnorth, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 16.0D - i, 0.0D, 16.0D, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.reast, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, i - smoothStep, 16.0D, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.rsouth, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 16.0D - i, 0.0D, 16.0D + smoothStep - i, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.rwest, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 0.0D, 0.0D, 16.0D, 16.0D, i));
        }
        collisionShapeMap.put(SlopeType.north2, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(i - smoothStep, 0.0D, 16.0D - i, 16.0D, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.east2, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, i - smoothStep, i, 16.0D, 16.0D));
        }
        collisionShapeMap.put(SlopeType.south2, shape.optimize());

        shape = Shapes.empty();
        for (float i = smoothStep; i <= 16; i += smoothStep) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, i, 16.0D, 16.0D + smoothStep - i));
        }
        collisionShapeMap.put(SlopeType.west2, shape.optimize());
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

        var rotationYaw = ((int) Mth.wrapDegrees((context.getPlayer().getYRot() + 180F) * 4F / 360F + (type2 ? 0.0F : 0.5F))) & 3;

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
        if (visualShapeMap.containsKey(type)) {
            return visualShapeMap.get(type);
        }
        return collisionShapeMap.get(SlopeType.north);
    }
}
