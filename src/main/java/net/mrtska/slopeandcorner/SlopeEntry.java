package net.mrtska.slopeandcorner;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * List of vanilla blocks that it can make slopes.
 */
public class SlopeEntry {

    private static final ArrayList<SlopeEntry> entries;


    private final Block block;
    private final String blockName;
    private final String[] textureName;

    private SlopeEntry(Block block, String blockName, String... textures) {

        this.block = block;
        this.blockName = blockName;
        this.textureName = textures;
    }

    public Block getBlock() {
        return block;
    }

    public String getBlockName() {
        return blockName;
    }

    public String[] getTexturesName() {
        return textureName;
    }



    private static void register(Block targetBlock, String... textures) {

        entries.add(new SlopeEntry(targetBlock, Objects.requireNonNull(targetBlock.getRegistryName()).getPath(), textures));
    }

    /**
     * Returns all slope entries.
     */
    public static List<SlopeEntry> getSlopeBlocks() {

        return entries;
    }


    static {

        entries = new ArrayList<>();

        // Register vanilla blocks.
        register(Blocks.STONE, "stone");
        register(Blocks.GRANITE, "granite");

    }
}
