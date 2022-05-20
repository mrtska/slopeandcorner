package net.mrtska.slopeandcorner.util;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

/**
 * Block state properties for slope blocks.
 */
public class SlopeBlockStateProperties {

    public static final BooleanProperty TRANSPARENT = BooleanProperty.create("transparent");
    public static final BooleanProperty TRANSPARENT2 = BooleanProperty.create("transparent2");
    public static final EnumProperty<SlopeType> SLOPE_TYPE = EnumProperty.create("type", SlopeType.class);
    public static final EnumProperty<SlopeType> SLOPE_TYPE2 = EnumProperty.create("type2", SlopeType.class);
    public static final EnumProperty<SlopeType> SLOPE_TYPE3 = EnumProperty.create("type3", SlopeType.class);
    public static final EnumProperty<SlopeType> SLOPE_TYPE4 = EnumProperty.create("type4", SlopeType.class);
}
