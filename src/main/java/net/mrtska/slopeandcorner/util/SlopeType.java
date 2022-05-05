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

    @Override
    public @Nonnull String getSerializedName() {
        return this.name;
    }
}
