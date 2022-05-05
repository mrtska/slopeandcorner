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

        register(Blocks.GRASS_BLOCK, "top=grass_block_top,bottom=dirt,side=grass_block_side", "dirt");

        register(Blocks.DIRT, "dirt");
        register(Blocks.COARSE_DIRT, "coarse_dirt");
        register(Blocks.PODZOL, "top=podzol_top,bottom=dirt,side=podzol_side", "dirt");
        register(Blocks.ROOTED_DIRT, "rooted_dirt");
        register(Blocks.CRIMSON_NYLIUM, "top=crimson_nylium,bottom=netherrack,side=crimson_nylium_side", "crimson_nylium_side");
        register(Blocks.WARPED_NYLIUM, "top=warped_nylium,bottom=netherrack,side=warped_nylium_side", "warped_nylium_side");
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
        register(Blocks.ANCIENT_DEBRIS, "top=ancient_debris_top,bottom=ancient_debris_top,side=ancient_debris_side", "ancient_debris_side");
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

        register(Blocks.OAK_LOG, "top=oak_log_top,bottom=oak_log_top,side=oak_log", "oak_log");
        register(Blocks.SPRUCE_LOG, "top=spruce_log_top,bottom=spruce_log_top,side=spruce_log", "spruce_log");
        register(Blocks.BIRCH_LOG, "top=birch_log_top,bottom=birch_log_top,side=birch_log", "birch_log");
        register(Blocks.JUNGLE_LOG, "top=jungle_log_top,bottom=jungle_log_top,side=jungle_log", "jungle_log");
        register(Blocks.ACACIA_LOG, "top=acacia_log_top,bottom=acacia_log_top,side=acacia_log", "acacia_log");
        register(Blocks.DARK_OAK_LOG, "top=dark_oak_log_top,bottom=dark_oak_log_top,side=dark_oak_log", "dark_oak_log");
        register(Blocks.CRIMSON_STEM, "top=crimson_stem_top,bottom=crimson_stem_top,side=crimson_stem", "crimson_stem");
        register(Blocks.WARPED_STEM, "top=warped_stem_top,bottom=warped_stem_top,side=warped_stem", "warped_stem");
        register(Blocks.STRIPPED_OAK_LOG, "top=stripped_oak_log_top,bottom=stripped_oak_log_top,side=stripped_oak_log", "stripped_oak_log");
        register(Blocks.STRIPPED_SPRUCE_LOG, "top=stripped_spruce_log_top,bottom=stripped_spruce_log_top,side=stripped_spruce_log", "stripped_spruce_log");
        register(Blocks.STRIPPED_BIRCH_LOG, "top=stripped_birch_log_top,bottom=stripped_birch_log_top,side=stripped_birch_log", "stripped_birch_log");
        register(Blocks.STRIPPED_JUNGLE_LOG, "top=stripped_jungle_log_top,bottom=stripped_jungle_log_top,side=stripped_jungle_log", "stripped_jungle_log");
        register(Blocks.STRIPPED_ACACIA_LOG, "top=stripped_acacia_log_top,bottom=stripped_acacia_log_top,side=stripped_acacia_log", "stripped_acacia_log");
        register(Blocks.STRIPPED_DARK_OAK_LOG, "top=stripped_dark_oak_log_top,bottom=stripped_dark_oak_log_top,side=stripped_dark_oak_log", "stripped_dark_oak_log");
        register(Blocks.STRIPPED_CRIMSON_STEM, "top=stripped_crimson_stem_top,bottom=stripped_crimson_stem_top,side=stripped_crimson_stem", "stripped_crimson_stem");
        register(Blocks.STRIPPED_WARPED_STEM, "top=stripped_warped_stem_top,bottom=stripped_warped_stem_top,side=stripped_warped_stem", "stripped_warped_stem");
        register(Blocks.STRIPPED_OAK_WOOD, "stripped_oak_log");
        register(Blocks.STRIPPED_SPRUCE_WOOD, "stripped_spruce_log");
        register(Blocks.STRIPPED_BIRCH_WOOD, "stripped_birch_log");
        register(Blocks.STRIPPED_JUNGLE_WOOD, "stripped_jungle_log");
        register(Blocks.STRIPPED_ACACIA_WOOD, "stripped_acacia_log");
        register(Blocks.STRIPPED_DARK_OAK_WOOD, "stripped_dark_oak_log");
        register(Blocks.STRIPPED_CRIMSON_HYPHAE, "stripped_crimson_stem");
        register(Blocks.STRIPPED_WARPED_HYPHAE, "stripped_warped_stem");
        register(Blocks.OAK_WOOD, "oak_log");
        register(Blocks.SPRUCE_WOOD, "spruce_log");
        register(Blocks.BIRCH_WOOD, "birch_log");
        register(Blocks.JUNGLE_WOOD, "jungle_log");
        register(Blocks.ACACIA_WOOD, "acacia_log");
        register(Blocks.DARK_OAK_WOOD, "dark_oak_log");
        register(Blocks.CRIMSON_HYPHAE, "crimson_stem");
        register(Blocks.WARPED_HYPHAE, "warped_stem");

        register(Blocks.SPONGE, "sponge");
        register(Blocks.WET_SPONGE, "wet_sponge");

        register(Blocks.GLASS, "glass");
        register(Blocks.TINTED_GLASS, "tinted_glass");

        register(Blocks.LAPIS_BLOCK, "lapis_block");
        register(Blocks.SANDSTONE, "top=sandstone_top,bottom=sandstone_bottom,side=sandstone", "sandstone");
        register(Blocks.CHISELED_SANDSTONE, "top=sandstone_top,bottom=sandstone_top,side=chiseled_sandstone", "chiseled_sandstone");
        register(Blocks.CUT_SANDSTONE, "top=sandstone_top,bottom=sandstone_top,side=cut_sandstone", "cut_sandstone");

        register(Blocks.WHITE_WOOL, "white_wool");
        register(Blocks.ORANGE_WOOL, "orange_wool");
        register(Blocks.MAGENTA_WOOL, "magenta_wool");
        register(Blocks.LIGHT_BLUE_WOOL, "light_blue_wool");
        register(Blocks.YELLOW_WOOL, "yellow_wool");
        register(Blocks.LIME_WOOL, "lime_wool");
        register(Blocks.PINK_WOOL, "pink_wool");
        register(Blocks.GRAY_WOOL, "gray_wool");
        register(Blocks.LIGHT_GRAY_WOOL, "light_gray_wool");
        register(Blocks.CYAN_WOOL, "cyan_wool");
        register(Blocks.PURPLE_WOOL, "purple_wool");
        register(Blocks.BLUE_WOOL, "blue_wool");
        register(Blocks.BROWN_WOOL, "brown_wool");
        register(Blocks.GREEN_WOOL, "green_wool");
        register(Blocks.RED_WOOL, "red_wool");
        register(Blocks.BLACK_WOOL, "black_wool");

        register(Blocks.SMOOTH_QUARTZ, "quartz_block_bottom");
        register(Blocks.SMOOTH_RED_SANDSTONE, "red_sandstone_top");
        register(Blocks.SMOOTH_SANDSTONE, "sandstone_top");
        register(Blocks.SMOOTH_STONE, "smooth_stone");
        register(Blocks.BRICKS, "bricks");
        register(Blocks.BOOKSHELF, "top=oak_planks,bottom=oak_planks,side=bookshelf", "bookshelf");
        register(Blocks.MOSSY_COBBLESTONE, "mossy_cobblestone");
        register(Blocks.OBSIDIAN, "obsidian");
        register(Blocks.PURPUR_BLOCK, "purpur_block");
        register(Blocks.PURPUR_PILLAR, "top=purpur_pillar_top,bottom=purpur_pillar_top,side=purpur_pillar", "purpur_pillar");

        register(Blocks.ICE, "ice");
        register(Blocks.SNOW_BLOCK, "snow");
        register(Blocks.CLAY, "clay");
        register(Blocks.PUMPKIN, "top=pumpkin_top,bottom=pumpkin_top,side=pumpkin_side", "pumpkin_side");

        register(Blocks.NETHERRACK, "netherrack");
        register(Blocks.SOUL_SAND, "soul_sand");
        register(Blocks.SOUL_SOIL, "soul_soil");
        register(Blocks.BASALT, "top=basalt_top,bottom=basalt_top,side=basalt_side", "basalt_side");
        register(Blocks.POLISHED_BASALT, "top=polished_basalt_top,bottom=polished_basalt_top,side=polished_basalt_side",  "polished_basalt");
        register(Blocks.SMOOTH_BASALT, "smooth_basalt");
        register(Blocks.GLOWSTONE, "glowstone");

        register(Blocks.STONE_BRICKS, "stone_bricks");
        register(Blocks.MOSSY_STONE_BRICKS, "mossy_stone_bricks");
        register(Blocks.CRACKED_STONE_BRICKS, "cracked_stone_bricks");
        register(Blocks.CHISELED_STONE_BRICKS, "chiseled_stone_bricks");
        register(Blocks.DEEPSLATE_BRICKS, "deepslate_bricks");
        register(Blocks.CRACKED_DEEPSLATE_BRICKS, "cracked_deepslate_bricks");
        register(Blocks.DEEPSLATE_TILES, "deepslate_tiles");
        register(Blocks.CRACKED_DEEPSLATE_TILES, "cracked_deepslate_tiles");
        register(Blocks.CHISELED_DEEPSLATE, "chiseled_deepslate");

        register(Blocks.MELON, "top=melon_top,bottom=melon_top,side=melon_side", "melon_side");
        register(Blocks.MYCELIUM, "top=mycelium_top,bottom=dirt,side=mycelium_side", "mycelium_side");
        register(Blocks.NETHER_BRICKS, "nether_bricks");
        register(Blocks.CRACKED_NETHER_BRICKS, "cracked_nether_bricks");
        register(Blocks.CHISELED_NETHER_BRICKS, "chiseled_nether_bricks");
        register(Blocks.END_STONE, "end_stone");
        register(Blocks.END_STONE_BRICKS, "end_stone_bricks");
        register(Blocks.EMERALD_BLOCK, "emerald_block");

        register(Blocks.CHISELED_QUARTZ_BLOCK, "top=chiseled_quartz_block_top,bottom=chiseled_quartz_block_top,side=chiseled_quartz_block", "chiseled_quartz_block");
        register(Blocks.QUARTZ_BLOCK, "top=quartz_block_top,bottom=quartz_block_top,side=quartz_block_side", "quartz_block_side");
        register(Blocks.QUARTZ_BRICKS, "quartz_bricks");
        register(Blocks.QUARTZ_PILLAR, "top=quartz_pillar_top,bottom=quartz_pillar_top,side=quartz_pillar", "quartz_pillar");

        register(Blocks.WHITE_TERRACOTTA, "white_terracotta");
        register(Blocks.ORANGE_TERRACOTTA, "orange_terracotta");
        register(Blocks.MAGENTA_TERRACOTTA, "magenta_terracotta");
        register(Blocks.LIGHT_BLUE_TERRACOTTA, "light_blue_terracotta");
        register(Blocks.YELLOW_TERRACOTTA, "yellow_terracotta");
        register(Blocks.LIME_TERRACOTTA, "lime_terracotta");
        register(Blocks.PINK_TERRACOTTA, "pink_terracotta");
        register(Blocks.GRAY_TERRACOTTA, "gray_terracotta");
        register(Blocks.LIGHT_GRAY_TERRACOTTA, "light_gray_terracotta");
        register(Blocks.CYAN_TERRACOTTA, "cyan_terracotta");
        register(Blocks.PURPLE_TERRACOTTA, "purple_terracotta");
        register(Blocks.BLUE_TERRACOTTA, "blue_terracotta");
        register(Blocks.BROWN_TERRACOTTA, "brown_terracotta");
        register(Blocks.GREEN_TERRACOTTA, "green_terracotta");
        register(Blocks.RED_TERRACOTTA, "red_terracotta");
        register(Blocks.BLACK_TERRACOTTA, "black_terracotta");
        register(Blocks.HAY_BLOCK, "top=hay_block_top,bottom=hay_block_top,side=hay_block_side", "hay_block_side");
        register(Blocks.TERRACOTTA, "terracotta");
        register(Blocks.PACKED_ICE, "packed_ice");

        register(Blocks.WHITE_STAINED_GLASS, "white_stained_glass");
        register(Blocks.ORANGE_STAINED_GLASS, "orange_stained_glass");
        register(Blocks.MAGENTA_STAINED_GLASS, "magenta_stained_glass");
        register(Blocks.LIGHT_BLUE_STAINED_GLASS, "light_blue_stained_glass");
        register(Blocks.YELLOW_STAINED_GLASS, "yellow_stained_glass");
        register(Blocks.LIME_STAINED_GLASS, "lime_stained_glass");
        register(Blocks.PINK_STAINED_GLASS, "pink_stained_glass");
        register(Blocks.GRAY_STAINED_GLASS, "gray_stained_glass");
        register(Blocks.LIGHT_GRAY_STAINED_GLASS, "light_gray_stained_glass");
        register(Blocks.CYAN_STAINED_GLASS, "cyan_stained_glass");
        register(Blocks.PURPLE_STAINED_GLASS, "purple_stained_glass");
        register(Blocks.BLUE_STAINED_GLASS, "blue_stained_glass");
        register(Blocks.BROWN_STAINED_GLASS, "brown_stained_glass");
        register(Blocks.GREEN_STAINED_GLASS, "green_stained_glass");
        register(Blocks.RED_STAINED_GLASS, "red_stained_glass");
        register(Blocks.BLACK_STAINED_GLASS, "black_stained_glass");

        register(Blocks.PRISMARINE, "prismarine");
        register(Blocks.PRISMARINE_BRICKS, "prismarine_bricks");
        register(Blocks.DARK_PRISMARINE, "dark_prismarine");
        register(Blocks.SEA_LANTERN, "sea_lantern");

        register(Blocks.RED_SANDSTONE, "top=red_sandstone_top,bottom=red_sandstone_bottom,side=red_sandstone", "red_sandstone");
        register(Blocks.CHISELED_RED_SANDSTONE, "top=red_sandstone_top,bottom=red_sandstone_top,side=chiseled_red_sandstone", "chiseled_red_sandstone");
        register(Blocks.CUT_RED_SANDSTONE, "top=red_sandstone_top,bottom=red_sandstone_top,side=cut_red_sandstone", "cut_red_sandstone");
        register(Blocks.MAGMA_BLOCK, "magma");
        register(Blocks.NETHER_WART_BLOCK, "nether_wart_block");
        register(Blocks.WARPED_WART_BLOCK, "warped_wart_block");
        register(Blocks.RED_NETHER_BRICKS, "red_nether_bricks");
        register(Blocks.BONE_BLOCK, "top=bone_block_top,bottom=bone_block_top,side=bone_block_side", "bone_block_side");

        register(Blocks.WHITE_CONCRETE, "white_concrete");
        register(Blocks.ORANGE_CONCRETE, "orange_concrete");
        register(Blocks.MAGENTA_CONCRETE, "magenta_concrete");
        register(Blocks.LIGHT_BLUE_CONCRETE, "light_blue_concrete");
        register(Blocks.YELLOW_CONCRETE, "yellow_concrete");
        register(Blocks.LIME_CONCRETE, "lime_concrete");
        register(Blocks.PINK_CONCRETE, "pink_concrete");
        register(Blocks.GRAY_CONCRETE, "gray_concrete");
        register(Blocks.LIGHT_GRAY_CONCRETE, "light_gray_concrete");
        register(Blocks.CYAN_CONCRETE, "cyan_concrete");
        register(Blocks.PURPLE_CONCRETE, "purple_concrete");
        register(Blocks.BLUE_CONCRETE, "blue_concrete");
        register(Blocks.BROWN_CONCRETE, "brown_concrete");
        register(Blocks.GREEN_CONCRETE, "green_concrete");
        register(Blocks.RED_CONCRETE, "red_concrete");
        register(Blocks.BLACK_CONCRETE, "black_concrete");
        register(Blocks.WHITE_CONCRETE_POWDER, "white_concrete_powder");
        register(Blocks.ORANGE_CONCRETE_POWDER, "orange_concrete_powder");
        register(Blocks.MAGENTA_CONCRETE_POWDER, "magenta_concrete_powder");
        register(Blocks.LIGHT_BLUE_CONCRETE_POWDER, "light_blue_concrete_powder");
        register(Blocks.YELLOW_CONCRETE_POWDER, "yellow_concrete_powder");
        register(Blocks.LIME_CONCRETE_POWDER, "lime_concrete_powder");
        register(Blocks.PINK_CONCRETE_POWDER, "pink_concrete_powder");
        register(Blocks.GRAY_CONCRETE_POWDER, "gray_concrete_powder");
        register(Blocks.LIGHT_GRAY_CONCRETE_POWDER, "light_gray_concrete_powder");
        register(Blocks.CYAN_CONCRETE_POWDER, "cyan_concrete_powder");
        register(Blocks.PURPLE_CONCRETE_POWDER, "purple_concrete_powder");
        register(Blocks.BLUE_CONCRETE_POWDER, "blue_concrete_powder");
        register(Blocks.BROWN_CONCRETE_POWDER, "brown_concrete_powder");
        register(Blocks.GREEN_CONCRETE_POWDER, "green_concrete_powder");
        register(Blocks.RED_CONCRETE_POWDER, "red_concrete_powder");
        register(Blocks.BLACK_CONCRETE_POWDER, "black_concrete_powder");

        register(Blocks.DEAD_TUBE_CORAL_BLOCK, "dead_tube_coral_block");
        register(Blocks.DEAD_BRAIN_CORAL_BLOCK, "dead_brain_coral_block");
        register(Blocks.DEAD_BUBBLE_CORAL_BLOCK, "dead_bubble_coral_block");
        register(Blocks.DEAD_FIRE_CORAL_BLOCK, "dead_fire_coral_block");
        register(Blocks.DEAD_HORN_CORAL_BLOCK, "dead_horn_coral_block");
        register(Blocks.TUBE_CORAL_BLOCK, "tube_coral_block");
        register(Blocks.BRAIN_CORAL_BLOCK, "brain_coral_block");
        register(Blocks.BUBBLE_CORAL_BLOCK, "bubble_coral_block");
        register(Blocks.FIRE_CORAL_BLOCK, "fire_coral_block");
        register(Blocks.HORN_CORAL_BLOCK, "horn_coral_block");
        register(Blocks.BLUE_ICE, "blue_ice");

        register(Blocks.DRIED_KELP_BLOCK, "top=dried_kelp_top,bottom=dried_kelp_bottom,side=dried_kelp_side", "dried_kelp_side");
        register(Blocks.CRYING_OBSIDIAN, "crying_obsidian");

        register(Blocks.BLACKSTONE, "top=blackstone_top,bottom=blackstone_top,side=blackstone", "blackstone");
        register(Blocks.GILDED_BLACKSTONE, "gilded_blackstone");
        register(Blocks.POLISHED_BLACKSTONE, "polished_blackstone");
        register(Blocks.CHISELED_POLISHED_BLACKSTONE, "chiseled_polished_blackstone");
        register(Blocks.POLISHED_BLACKSTONE_BRICKS, "polished_blackstone_bricks");
        register(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, "cracked_polished_blackstone_bricks");
    }
}
