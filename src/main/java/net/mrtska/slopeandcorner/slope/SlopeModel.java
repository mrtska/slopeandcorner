package net.mrtska.slopeandcorner.slope;

import static net.minecraft.core.Direction.*;
import static net.mrtska.slopeandcorner.model.SlopeModelEntry.SlopeModelQuad.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrtska.slopeandcorner.model.SlopeModelBase;
import net.mrtska.slopeandcorner.model.SlopeModelEntry.SlopeModelBlock;

@OnlyIn(Dist.CLIENT)
public class SlopeModel extends SlopeModelBase {

    @Override
    public void init() {

        SlopeModelBlock north = new SlopeModelBlock();
        north.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        north.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, NORTH));
        north.add(EAST, make(1, 1, 0, 16, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, EAST));
        north.add(SOUTH, make(0, 1, 0, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 0, 16, 0, SOUTH));
        north.add(WEST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 0, 16, 0, WEST));
        vertex.put("NORTH", north);

        SlopeModelBlock east = new SlopeModelBlock();
        east.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        east.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 1, 1, 0, 16, 0, NORTH));
        east.add(EAST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, EAST));
        east.add(SOUTH, make(1, 1, 1, 16, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        east.add(WEST, make(1, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 1, 1, 1, 16, 0, WEST));
        vertex.put("EAST", east);

        SlopeModelBlock south = new SlopeModelBlock();
        south.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN))	;
        south.add(NORTH, make(1, 1, 1, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 1, 16, 0, NORTH));
        south.add(EAST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 1, 16, 0, EAST));
        south.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        south.add(WEST, make(0, 1, 1, 16, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, WEST));
        vertex.put("SOUTH", south);

        SlopeModelBlock west = new SlopeModelBlock();
        west.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        west.add(NORTH, make(0, 1, 0, 16, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, NORTH));
        west.add(EAST, make(0, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 0, 1, 0, 16, 0, EAST));
        west.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 0, 1, 1, 16, 0, SOUTH));
        west.add(WEST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, WEST));
        vertex.put("WEST", west);

        SlopeModelBlock rnorth = new SlopeModelBlock();
        rnorth.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        rnorth.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, NORTH));
        rnorth.add(EAST, make(1, 1, 1, 0, 0, 1, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, EAST));
        rnorth.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        rnorth.add(WEST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 0, 1, 1, 16, 0, WEST));
        vertex.put("RNORTH", rnorth);

        SlopeModelBlock reast = new SlopeModelBlock();
        reast.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        reast.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 1, 0, 0, 0, 16, 0, 1, 0, 16, 0, NORTH));
        reast.add(EAST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, EAST));
        reast.add(SOUTH, make(0, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        reast.add(EAST, make(0, 1, 0, 0, 0, 1, 0, 0, 0, 16, 1, 0, 1, 16, 16, 0, 1, 1, 16, 0, EAST));
        vertex.put("REAST", reast);

        SlopeModelBlock rsouth = new SlopeModelBlock();
        rsouth.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        rsouth.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 1, 0, 16, 0, 0, 1, 16, 16, 0, 1, 0, 16, 0, NORTH));
        rsouth.add(EAST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 1, 0, 16, 1, 1, 0, 16, 0, EAST));
        rsouth.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        rsouth.add(WEST, make(0, 1, 0, 0, 0, 0, 0, 1, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, WEST));
        vertex.put("RSOUTH", rsouth);

        SlopeModelBlock rwest = new SlopeModelBlock();
        rwest.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        rwest.add(NORTH, make(1, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, NORTH));
        rwest.add(EAST, make(1, 1, 1, 0, 0, 0, 0, 1, 0, 16, 0, 0, 0, 16, 16, 1, 1, 0, 16, 0, EAST));
        rwest.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 0, 0, 1, 0, 16, 1, 1, 1, 16, 0, SOUTH));
        rwest.add(WEST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, WEST));
        vertex.put("RWEST", rwest);

        SlopeModelBlock north2 = new SlopeModelBlock();
        north2.add(DOWN, make(1, 0, 1, 16, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        north2.add(UP, make(0, 1, 0, 0, 0, 1, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        north2.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, NORTH));
        north2.add(EAST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, EAST));
        north2.add(SOUTH, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        vertex.put("NORTH2", north2);

        SlopeModelBlock east2 = new SlopeModelBlock();
        east2.add(DOWN, make(0, 0, 1, 0, 0, 1, 0, 0, 0, 16, 1, 0, 0, 16, 16, 1, 0, 1, 16, 0, DOWN));
        east2.add(UP, make(1, 1, 0, 16, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 1, 1, 0, 16, 0, UP));
        east2.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, NORTH));
        east2.add(EAST, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, EAST));
        east2.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        vertex.put("EAST2", east2);

        SlopeModelBlock south2 = new SlopeModelBlock();
        south2.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 1, 0, 1, 16, 0, DOWN));
        south2.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 1, 1, 1, 16, 16, 0, 1, 0, 16, 0, UP));
        south2.add(NORTH, make(1, 1, 1, 0, 0, 1, 0, 1, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, NORTH));
        south2.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 1, 16, 16, 1, 1, 1, 16, 0, SOUTH));
        south2.add(WEST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, WEST));
        vertex.put("SOUTH2", south2);

        SlopeModelBlock west2 = new SlopeModelBlock();
        west2.add(DOWN, make(0, 0, 1, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 16, 16, 0, 0, 1, 16, 0, DOWN));
        west2.add(UP, make(0, 1, 0, 0, 0, 0, 1, 1, 0, 16, 0, 1, 1, 0, 16, 1, 1, 0, 16, 0, UP));
        west2.add(NORTH, make(1, 1, 0, 0, 0, 1, 0, 0, 0, 16, 0, 0, 0, 16, 16, 0, 1, 0, 16, 0, NORTH));
        west2.add(SOUTH, make(0, 1, 1, 0, 0, 0, 0, 1, 0, 16, 1, 0, 0, 16, 16, 1, 1, 0, 16, 0, SOUTH));
        west2.add(WEST, make(0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 1, 16, 16, 0, 1, 1, 16, 0, WEST));
        vertex.put("WEST2", west2);
    }
}
