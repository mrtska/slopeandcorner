package net.mrtska.slopeandcorner.corner;

import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
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
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE)
                .setValue(SlopeBlockStateProperties.TRANSPARENT, false).setValue(SlopeBlockStateProperties.SLOPE_TYPE, SlopeType.north));
    }

    static {

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

}
