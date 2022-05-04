package net.mrtska.slopeandcorner.slope;

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

    west2("west2");

    private final String name;

    SlopeType(String name) {

        this.name = name;
    }

    @Override
    public @Nonnull String getSerializedName() {
        return this.name;
    }
}
