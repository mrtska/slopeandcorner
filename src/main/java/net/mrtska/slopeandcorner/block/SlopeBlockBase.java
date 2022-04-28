package net.mrtska.slopeandcorner.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.material.Material;

/**
 * Base class of slope blocks.
 */
public abstract class SlopeBlockBase extends Block implements EntityBlock {

    public SlopeBlockBase() {
        super(Properties.of(Material.GLASS).noCollission());
    }

}
