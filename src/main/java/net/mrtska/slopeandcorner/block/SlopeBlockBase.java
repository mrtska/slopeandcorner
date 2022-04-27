package net.mrtska.slopeandcorner.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SlopeBlockBase extends Block {

    public SlopeBlockBase() {
        super(Properties.of(Material.GLASS).noCollission());
    }

    @Override
    public VoxelShape getVisualShape(BlockState p_60479_, BlockGetter p_60480_, BlockPos p_60481_, CollisionContext p_60482_) {
        return super.getVisualShape(p_60479_, p_60480_, p_60481_, p_60482_);
    }

}
