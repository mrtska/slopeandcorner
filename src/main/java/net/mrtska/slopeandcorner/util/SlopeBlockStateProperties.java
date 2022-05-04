package net.mrtska.slopeandcorner.util;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.mrtska.slopeandcorner.slope.SlopeType;

/**
 * Block state properties for slope blocks.
 */
public class SlopeBlockStateProperties {

    public static final BooleanProperty TRANSPARENT = BooleanProperty.create("transparent");
    public static final EnumProperty<SlopeType> SLOPE_TYPE = EnumProperty.create("type", SlopeType.class);
}
