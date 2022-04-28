package net.mrtska.slopeandcorner.slope;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.block.SlopeBlockBase;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SlopeBlock extends SlopeBlockBase {

    public SlopeBlock() {

        this.setRegistryName(SlopeAndCorner.MODID, "slopeblock");
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SlopeBlockEntity(pos, state);
    }
}
