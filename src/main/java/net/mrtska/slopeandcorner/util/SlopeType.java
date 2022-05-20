package net.mrtska.slopeandcorner.util;

import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;

/**
 * Slope block variants.
 */
public enum SlopeType implements StringRepresentable {

    north("north"),

    east("east"),

    south("south"),

    west("west"),

    rnorth("rnorth"),

    reast("reast"),

    rsouth("rsouth"),

    rwest("rwest"),

    north2("north2"),

    east2("east2"),

    south2("south2"),

    west2("west2"),

    rnorth2("rnorth2"),

    reast2("reast2"),

    rsouth2("rsouth2"),

    rwest2("rwest2");

    private final String name;

    SlopeType(String name) {

        this.name = name;
    }

    public static SlopeType parse(String name) {
        return SlopeType.valueOf(name.toLowerCase());
    }

    @Override
    public @Nonnull String getSerializedName() {
        return this.name;
    }

    public SlopeType opposite(boolean reversed) {

        if (reversed) {

            return switch (this) {
                case north -> rsouth;
                case east -> rwest;
                case south -> rnorth;
                case west -> reast;
                case rnorth -> south;
                case reast -> west;
                case rsouth -> north;
                case rwest -> east;
                case north2 -> rsouth2;
                case east2 -> rwest2;
                case south2 -> rnorth2;
                case west2 -> reast2;
                case rnorth2 -> south2;
                case reast2 -> west2;
                case rsouth2 -> north2;
                case rwest2 -> east2;
            };
        } else {

            return switch (this) {
                case north -> south;
                case east -> west;
                case south -> north;
                case west -> east;
                case rnorth -> rsouth;
                case reast -> rwest;
                case rsouth -> rnorth;
                case rwest -> reast;
                case north2 -> south2;
                case east2 -> west2;
                case south2 -> north2;
                case west2 -> east2;
                case rnorth2 -> rsouth2;
                case reast2 -> rwest2;
                case rsouth2 -> rnorth2;
                case rwest2 -> reast2;
            };
        }
    }
}
