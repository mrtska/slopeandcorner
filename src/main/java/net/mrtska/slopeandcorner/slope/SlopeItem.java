package net.mrtska.slopeandcorner.slope;

import net.minecraft.world.item.CreativeModeTab;
import net.mrtska.slopeandcorner.SlopeAndCorner;
import net.mrtska.slopeandcorner.item.SlopeItemBase;

public class SlopeItem extends SlopeItemBase {

    public SlopeItem(SlopeBlock block, CreativeModeTab tab) {
        super(block, new Properties().tab(tab));
        this.setRegistryName(SlopeAndCorner.MODID, "slopeitem");
    }
}
