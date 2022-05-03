package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.block.SlopeBlockBase;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.IntStream;

public class SlopeBlock extends SlopeBlockBase {

    public SlopeBlock() {
        this.setRegistryName(SlopeAndCorner.MODID, "slopeblock");
    }

    private static final HashMap<String, VoxelShape> collisionShapeMap = new HashMap<>();
    private static final HashMap<String, VoxelShape> visualShapeMap = new HashMap<>();

    static {

        var bottom = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

        visualShapeMap.put("NORTH", Shapes.or(bottom, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D)));
        visualShapeMap.put("EAST", Shapes.or(bottom, Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D)));
        visualShapeMap.put("SOUTH", Shapes.or(bottom, Block.box(0.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D)));
        visualShapeMap.put("WEST", Shapes.or(bottom, Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 16.0D)));

        var shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D, i, 16.0D - i));
        }
        collisionShapeMap.put("NORTH", shape);

        shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(i, 0.0D, 0.0D, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put("EAST", shape);

        shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, i, 16.0D, i, 16.0D));
        }
        collisionShapeMap.put("SOUTH", shape);

        shape = Shapes.empty();
        for (float i = 0; i <= 16; i += 0.25) {

            shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D - i, i, 16.0D));
        }
        collisionShapeMap.put("WEST", shape);
    }

    @Override
    public @Nonnull VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {

        if (getter.getBlockEntity(pos) instanceof SlopeBlockEntity entity) {

            String direction = entity.getDirection();

            if (collisionShapeMap.containsKey(direction)) {

                return collisionShapeMap.get(direction);
            }
            var shape = Block.box(0.0D, 0.0D, 0.0D, 0, 0, 0);
            for (float i = 0; i <= 16; i += 1) {

                shape = Shapes.or(shape, Block.box(0.0D, 0.0D, 0.0D, 16.0D, i, 16.0D - i));
            }
            return shape;
        }

        return Shapes.block();
    }


    @Override
    public @Nonnull VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter getter, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {

        if (getter.getBlockEntity(pos) instanceof SlopeBlockEntity entity) {

            String direction = entity.getDirection();

            if (visualShapeMap.containsKey(direction)) {

                return visualShapeMap.get(direction);
            }

            if (direction.equals("WEST")) {

                return Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
                        , Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 16.0D));
            }

        }
        return Shapes.block();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SlopeBlockEntity(pos, state);
    }
}
