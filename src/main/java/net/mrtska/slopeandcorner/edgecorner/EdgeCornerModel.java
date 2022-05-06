package net.mrtska.slopeandcorner.edgecorner;

import static net.minecraft.core.Direction.*;
import static net.mrtska.slopeandcorner.model.SlopeModelEntry.SlopeModelQuad.*;

import net.mrtska.slopeandcorner.model.SlopeModelBase;
import net.mrtska.slopeandcorner.model.SlopeModelEntry.SlopeModelBlock;

/**
 * Edge corner block model.
 */
public class EdgeCornerModel extends SlopeModelBase {
    @Override
    public void init() {

        SlopeModelBlock north = new SlopeModelBlock();
        north.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        north.add(NORTH, make(0, 1, 0, 0, 0, 0, 0, 1, 0, 16, 0, 0, 1, 0, 16, 1, 1, 0, 16, 0, NORTH));
        north.add(NORTH, make(0, 0, 1, 0, 16, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, NORTH));
        north.add(EAST, make(1, 1, 0, 0, 0, 1, 1, 0, 0, 16, 0, 0, 1, 16, 16, 1, 1, 1, 16, 0, EAST));
        north.add(EAST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 0, 1, 16, 0, EAST));
        north.add(SOUTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, SOUTH));
        north.add(WEST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, WEST));
        vertex.put("NORTH", north);

        SlopeModelBlock east = new SlopeModelBlock();
        east.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        east.add(NORTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, NORTH));
        east.add(EAST, make(1, 1, 0, 0, 0, 0, 0, 0, 0, 16, 1, 1, 1, 16, 0, 1, 1, 1, 16, 0, EAST));
        east.add(EAST, make(0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        east.add(SOUTH, make(1, 1, 1, 0, 0, 1, 1, 1, 0, 16, 0, 0, 0, 16, 16, 0, 1, 1, 16, 0, SOUTH));
        east.add(SOUTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 0, 0, 16, 0, SOUTH));
        east.add(WEST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, WEST));
        vertex.put("EAST", east);

        SlopeModelBlock south = new SlopeModelBlock();
        south.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        south.add(NORTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, NORTH));
        south.add(EAST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        south.add(SOUTH, make(1, 1, 1, 0, 0, 1, 0, 0, 0, 16, 0, 1, 1, 16, 0, 0, 1, 1, 16, 0, SOUTH));
        south.add(SOUTH, make(1, 0, 0, 0, 16, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, SOUTH));
        south.add(WEST, make(0, 1, 1, 0, 0, 0, 1, 1, 0, 16, 1, 0, 0, 16, 16, 0, 1, 0, 16, 0, WEST));
        south.add(WEST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 0, 0, 16, 0, WEST));
        vertex.put("SOUTH", south);

        SlopeModelBlock west = new SlopeModelBlock();
        west.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        west.add(NORTH, make(0, 1, 0, 0, 0, 0, 1, 0, 0, 16, 1, 0, 1, 16, 16, 1, 1, 0, 16, 0, NORTH));
        west.add(NORTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 0, 1, 16, 0, NORTH));
        west.add(EAST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        west.add(SOUTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, SOUTH));
        west.add(WEST, make(0, 1, 1, 0, 0, 1, 0, 1, 0, 16, 0, 1, 0, 16, 0, 0, 1, 0, 16, 0, WEST));
        west.add(WEST, make(1, 0, 1, 0, 16, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, WEST));
        vertex.put("WEST", west);

        SlopeModelBlock rnorth = new SlopeModelBlock();
        rnorth.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        rnorth.add(NORTH, make(0, 1, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 0, 16, 0, NORTH));
        rnorth.add(NORTH, make(0, 1, 1, 0, 0, 0, 1, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, NORTH));
        rnorth.add(EAST, make(1, 0, 0, 0, 16, 1, 0, 0, 0, 16, 1, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        rnorth.add(EAST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 1, 1, 16, 0, 0, 1, 1, 16, 0, EAST));
        rnorth.add(SOUTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, SOUTH));
        rnorth.add(WEST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, WEST));
        vertex.put("RNORTH", rnorth);

        SlopeModelBlock reast = new SlopeModelBlock();
        reast.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        reast.add(NORTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, NORTH));
        reast.add(EAST, make(0, 1, 0, 0, 0, 1, 0, 0, 0, 16, 1, 0, 1, 16, 16, 1, 0, 1, 16, 0, EAST));
        reast.add(EAST, make(0, 1, 0, 0, 0, 0, 1, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        reast.add(SOUTH, make(1, 0, 1, 0, 16, 1, 0, 1, 0, 16, 0, 0, 1, 16, 16, 0, 1, 0, 16, 0, SOUTH));
        reast.add(SOUTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 1, 0, 16, 0, 0, 1, 0, 16, 0, SOUTH));
        reast.add(WEST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, WEST));
        vertex.put("REAST", reast);

        SlopeModelBlock rsouth = new SlopeModelBlock();
        rsouth.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        rsouth.add(NORTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, NORTH));
        rsouth.add(EAST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        rsouth.add(SOUTH, make(1, 1, 0, 0, 0, 1, 0, 1, 0, 16, 0, 0, 1, 16, 16, 0, 0, 1, 16, 0, SOUTH));
        rsouth.add(SOUTH, make(1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, SOUTH));
        rsouth.add(WEST, make(0, 0, 1, 0, 16, 0, 0, 1, 0, 16, 0, 0, 0, 16, 16, 1, 1, 0, 16, 0, WEST));
        rsouth.add(WEST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 1, 0, 16, 0, 1, 1, 0, 16, 0, WEST));
        vertex.put("RSOUTH", rsouth);

        SlopeModelBlock rwest = new SlopeModelBlock();
        rwest.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        rwest.add(NORTH, make(0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 1, 1, 16, 0, NORTH));
        rwest.add(NORTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 1, 1, 16, 0, 1, 1, 1, 16, 0, NORTH));
        rwest.add(EAST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        rwest.add(SOUTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, SOUTH));
        rwest.add(WEST, make(1, 1, 1, 0, 0, 0, 0, 1, 0, 16, 0, 0, 0, 16, 16, 0, 0, 0, 16, 0, WEST));
        rwest.add(WEST, make(1, 1, 1, 0, 0, 1, 1, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, WEST));
        vertex.put("RWEST", rwest);
    }
}
