package net.mrtska.slopeandcorner;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * List of vanilla blocks that it can make slopes.
 */
public class SlopeEntry {

    private static final LinkedHashMap<String, SlopeEntry> entries;


    private final Block block;
    private final String blockName;
    private final String texture;
    private final String particle;

    private SlopeEntry(Block block, String blockName, String texture, String particle) {

        this.block = block;
        this.blockName = blockName;
        this.texture = texture;
        this.particle = particle;
    }

    public Block getBlock() {
        return this.block;
    }

    public String getBlockName() {
        return this.blockName;
    }

    public String getTexture() {
        return this.texture;
    }

    public String getParticle() {
        return this.particle;
    }

    private static void register(Block targetBlock, String texture) {

        var key = Objects.requireNonNull(targetBlock.getRegistryName()).getPath();
        entries.put(key, new SlopeEntry(targetBlock, key, texture, texture));
    }

    private static void register(Block targetBlock, String texture, String particle) {

        var key = Objects.requireNonNull(targetBlock.getRegistryName()).getPath();
        entries.put(key, new SlopeEntry(targetBlock, key, texture, particle));
    }


    /**
     * Returns all slope entries.
     */
    public static Collection<SlopeEntry> getSlopeBlocks() {

        return entries.values();
    }

    public static SlopeEntry getSlopeEntry(String key) {

        return entries.get(key);
    }

    static {

        entries = new LinkedHashMap<>();

        // Register vanilla blocks.
        register(Blocks.STONE, "stone");
        register(Blocks.GRANITE, "granite");
        register(Blocks.POLISHED_GRANITE, "polished_granite");
        register(Blocks.DIORITE, "diorite");
        register(Blocks.POLISHED_DIORITE, "polished_diorite");
        register(Blocks.ANDESITE, "andesite");
        register(Blocks.POLISHED_ANDESITE, "polished_andesite");
        register(Blocks.DEEPSLATE, "deepslate");
        register(Blocks.COBBLED_DEEPSLATE, "cobbled_deepslate");
        register(Blocks.POLISHED_DEEPSLATE, "polished_deepslate");
        register(Blocks.CALCITE, "calcite");
        register(Blocks.TUFF, "tuff");
        register(Blocks.DRIPSTONE_BLOCK, "dripstone_block");

        register(Blocks.GRASS_BLOCK, "up=grass_block_top,down=dirt,side=grass_block_side", "dirt");

        register(Blocks.DIRT, "dirt");
        register(Blocks.COARSE_DIRT, "coarse_dirt");
        register(Blocks.PODZOL, "podzol_side", "podzol_top");
        register(Blocks.ROOTED_DIRT, "rooted_dirt");
        register(Blocks.CRIMSON_NYLIUM, "crimson_nylium", "crimson_nylium_side");
        register(Blocks.WARPED_NYLIUM, "warped_nylium", "warped_nylium_side");
        register(Blocks.COBBLESTONE, "cobblestone");

        register(Blocks.OAK_PLANKS, "oak_planks");
        register(Blocks.SPRUCE_PLANKS, "spruce_planks");
        register(Blocks.BIRCH_PLANKS, "birch_planks");
        register(Blocks.JUNGLE_PLANKS, "jungle_planks");
        register(Blocks.ACACIA_PLANKS, "acacia_planks");
        register(Blocks.DARK_OAK_PLANKS, "dark_oak_planks");
        register(Blocks.CRIMSON_PLANKS, "crimson_planks");
        register(Blocks.WARPED_PLANKS, "warped_planks");

        register(Blocks.BEDROCK, "bedrock");
        register(Blocks.SAND, "sand");
        register(Blocks.RED_SAND, "red_sand");
        register(Blocks.GRAVEL, "gravel");
        register(Blocks.ANCIENT_DEBRIS, "ancient_debris_top", "ancient_debris_side");
        register(Blocks.COAL_BLOCK, "coal_block");
        register(Blocks.AMETHYST_BLOCK, "amethyst_block");
        register(Blocks.BUDDING_AMETHYST, "budding_amethyst");
        register(Blocks.IRON_BLOCK, "iron_block");
        register(Blocks.COPPER_BLOCK, "copper_block");
        register(Blocks.GOLD_BLOCK, "gold_block");
        register(Blocks.DIAMOND_BLOCK, "diamond_block");
        register(Blocks.NETHERITE_BLOCK, "netherite_block");
        register(Blocks.EXPOSED_COPPER, "exposed_copper");
        register(Blocks.WEATHERED_COPPER, "weathered_copper");
        register(Blocks.OXIDIZED_COPPER, "oxidized_copper");
        register(Blocks.CUT_COPPER, "cut_copper");
        register(Blocks.EXPOSED_CUT_COPPER, "exposed_cut_copper");
        register(Blocks.WEATHERED_CUT_COPPER, "weathered_cut_copper");
        register(Blocks.OXIDIZED_CUT_COPPER, "oxidized_cut_copper");

        register(Blocks.OAK_LOG, "oak_log");
        register(Blocks.SPRUCE_LOG, "spruce_log");
        register(Blocks.BIRCH_LOG, "birch_log");
        register(Blocks.JUNGLE_LOG, "jungle_log");
        register(Blocks.ACACIA_LOG, "acacia_log");
        register(Blocks.DARK_OAK_LOG, "dark_oak_log");
        register(Blocks.CRIMSON_STEM, "crimson_stem");
        register(Blocks.WARPED_STEM, "warped_stem");
        register(Blocks.STRIPPED_OAK_LOG, "stripped_oak_log");
        register(Blocks.STRIPPED_SPRUCE_LOG, "stripped_spruce_log");
        register(Blocks.STRIPPED_BIRCH_LOG, "stripped_birch_log");
        register(Blocks.STRIPPED_JUNGLE_LOG, "stripped_jungle_log");
        register(Blocks.STRIPPED_ACACIA_LOG, "stripped_acacia_log");
        register(Blocks.STRIPPED_DARK_OAK_LOG, "stripped_dark_oak_log");
        register(Blocks.STRIPPED_CRIMSON_STEM, "stripped_crimson_stem");
        register(Blocks.STRIPPED_WARPED_STEM, "stripped_warped_stem");
        register(Blocks.STRIPPED_OAK_WOOD, "stripped_oak_wood");
        register(Blocks.STRIPPED_SPRUCE_WOOD, "stripped_spruce_wood");
        register(Blocks.STRIPPED_BIRCH_WOOD, "stripped_birch_wood");
        register(Blocks.STRIPPED_JUNGLE_WOOD, "stripped_jungle_wood");
        register(Blocks.STRIPPED_ACACIA_WOOD, "stripped_acacia_wood");
        register(Blocks.STRIPPED_DARK_OAK_WOOD, "stripped_dark_oak_wood");
        register(Blocks.STRIPPED_CRIMSON_HYPHAE, "stripped_crimson_hyphae");
        register(Blocks.STRIPPED_WARPED_HYPHAE, "stripped_warped_hyphae");
        register(Blocks.OAK_WOOD, "oak_wood");
        register(Blocks.SPRUCE_WOOD, "spruce_wood");
        register(Blocks.BIRCH_WOOD, "birch_wood");
        register(Blocks.JUNGLE_WOOD, "jungle_wood");
        register(Blocks.ACACIA_WOOD, "acacia_wood");
        register(Blocks.DARK_OAK_WOOD, "dark_oak_wood");
        register(Blocks.CRIMSON_HYPHAE, "crimson_hyphae");
        register(Blocks.WARPED_HYPHAE, "warped_hyphae");

        register(Blocks.SPONGE, "sponge");
        register(Blocks.WET_SPONGE, "wet_sponge");

        register(Blocks.GLASS, "glass");
        register(Blocks.TINTED_GLASS, "tinted_glass");

    }
}
