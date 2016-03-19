package net.mrtska.util;

import java.util.Iterator;
import java.util.LinkedHashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.UniqueIdentifier;

/**[Module SlopeEntry.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/23
*/
public class SlopeEntry {

	private static final LinkedHashMap<String, SlopeEntry> list = new LinkedHashMap<String, SlopeEntry>();



	private final Block block;
	private final String blockName;
	private final String[] textureName;

	private final UniqueIdentifier id;


	private SlopeEntry(Block block, String blockname, String... textures) {

		this.block = block;
		this.blockName = blockname;


		this.textureName = new String[textures.length];
		for(int i = 0; i < textures.length; i++) {

			this.textureName[i] = count+++ ":" + textures[i];
		}

		this.id = GameRegistry.findUniqueIdentifierFor(this.block);

	}

	private int count = 0;

	public Block getBlock() {
		return block;
	}

	public String getBlockName() {
		return blockName;
	}

	public String[] getTexturesName() {
		return textureName;
	}


	//斜面MODに登録するブロックを指定する
	public static void register(Block block, String blockname, String... textures) {

		SlopeEntry entry = new SlopeEntry(block, blockname, textures);

		list.put(blockname, entry);
	}


	public static void init() {

		register(Blocks.stone, "stone", "stone", "stone_granite", "stone_granite_smooth", "stone_diorite", "stone_diorite_smooth", "stone_andesite", "stone_andesite_smooth");
		register(Blocks.grass, "grass", "up=grass_top,down=dirt,side=grass_side");
		register(Blocks.cobblestone, "cobblestone", "cobblestone");
		register(Blocks.planks, "planks", "planks_oak", "planks_spruce", "planks_birch", "planks_jungle", "planks_acacia", "planks_big_oak");
		register(Blocks.bedrock, "bedrock", "bedrock");
		register(Blocks.sand, "sand", "sand", "red_sand");
		register(Blocks.gravel, "gravel", "gravel");
		register(Blocks.gold_ore, "gold_ore", "gold_ore");
		register(Blocks.iron_ore, "iron_ore", "iron_ore");
		register(Blocks.coal_ore, "coal_ore", "coal_ore");
		register(Blocks.log, "log", "up=log_oak_top,down=log_oak_top,side=log_oak", "up=log_spruce_top,down=log_spruce_top,side=log_spruce",
								"up=log_birch_top,down=log_birch_top,side=log_birch", "up=log_jungle_top,down=log_jungle_top,side=log_jungle");
		register(Blocks.sponge, "sponge", "sponge", "sponge_wet");
		register(Blocks.glass, "glass", "glass");
		register(Blocks.lapis_ore, "lapis_ore", "lapis_ore");
		register(Blocks.lapis_block, "lapis_block", "lapis_block");
		register(Blocks.sandstone, "sandstone", "up=sandstone_top,down=sandstone_bottom,side=sandstone_normal",
												"up=sandstone_top,down=sandstone_bottom,side=sandstone_carved",
												"up=sandstone_top,down=sandstone_bottom,side=sandstone_smooth");
		register(Blocks.wool, "wool", "wool_colored_white", "wool_colored_orange", "wool_colored_magenta", "wool_colored_light_blue", "wool_colored_yellow",
									"wool_colored_lime", "wool_colored_pink", "wool_colored_gray", "wool_colored_silver", "wool_colored_cyan",
									"wool_colored_purple", "wool_colored_blue", "wool_colored_brown", "wool_colored_green", "wool_colored_red", "wool_colored_black");
		register(Blocks.gold_block, "gold_block", "gold_block");
		register(Blocks.iron_block, "iron_block", "iron_block");
		register(Blocks.brick_block, "brick_block", "brick");
		register(Blocks.bookshelf, "bookshelf", "up=planks_oak,down=planks_oak,side=bookshelf");
		register(Blocks.mossy_cobblestone, "mossy_cobblestone", "cobblestone_mossy");
		register(Blocks.obsidian, "obsidian", "obsidian");
		register(Blocks.diamond_ore, "diamond_ore", "diamond_ore");
		register(Blocks.diamond_block, "diamond_block", "diamond_block");
		register(Blocks.redstone_ore, "redstone_ore", "redstone_ore");
		register(Blocks.ice, "ice", "ice");
		register(Blocks.snow, "snow", "snow");
		register(Blocks.clay, "clay", "clay");
		register(Blocks.pumpkin, "pumpkin", "up=pumpkin_top,down=pumpkin_top,side=pumpkin_side");
		register(Blocks.stained_glass, "stained_glass", "glass_white", "glass_orange", "glass_magenta", "glass_light_blue", "glass_yellow", "glass_lime", "glass_pink",
				"glass_gray", "glass_silver", "glass_cyan", "glass_purple", "glass_blue", "glass_brown", "glass_green", "glass_red", "glass_black");
		register(Blocks.stonebrick, "stonebrick", "stonebrick", "stonebrick_mossy", "stonebrick_cracked", "stonebrick_carved");
		register(Blocks.melon_block, "melon_block", "up=melon_top,down=melon_top,side=melon_side");
		register(Blocks.mycelium, "mycelium", "up=mycelium_top,down=dirt,side=mycelium_side");
		register(Blocks.nether_brick, "nether_brick", "nether_brick");
		register(Blocks.end_stone, "end_stone", "end_stone");
		register(Blocks.emerald_ore, "emerald_ore", "emerald_ore");
		register(Blocks.emerald_block, "emerald_block", "emerald_block");
		register(Blocks.quartz_ore, "quartz_ore", "quartz_ore");
		register(Blocks.quartz_block, "quartz_block", "up=quartz_block_top,down=quartz_block_bottom,side=quartz_block_side",
														"up=quartz_block_chiseled_top,down=quartz_block_chiseled_top,side=quartz_block_chiseled",
														"up=quartz_block_lines_top,down=quartz_block_lines_top,side=quartz_block_lines");
		register(Blocks.stained_hardened_clay, "stained_hardened_clay", "hardened_clay", "hardened_clay_stained_orange", "hardened_clay_stained_magenta",
																		"hardened_clay_stained_light_blue", "hardened_clay_stained_yellow",
																		"hardened_clay_stained_lime", "hardened_clay_stained_pink",
																		"hardened_clay_stained_gray", "hardened_clay_stained_silver",
																		"hardened_clay_stained_cyan", "hardened_clay_stained_purple",
																		"hardened_clay_stained_blue", "hardened_clay_stained_brown",
																		"hardened_clay_stained_green", "hardened_clay_stained_red",
																		"hardened_clay_stained_black");
		register(Blocks.log2, "log2", "up=log_acacia_top,down=log_acacia_top,side=log_acacia", "up=log_big_oak_top,down=log_big_oak_top,side=log_big_oak");
		register(Blocks.prismarine, "prismarine", "prismarine_rough", "prismarine_bricks", "prismarine_dark");
		register(Blocks.sea_lantern, "sea_lantern", "sea_lantern");
		register(Blocks.hay_block, "hay_block", "up=hay_block_top,down=hay_block_top,side=hay_block_side");
		register(Blocks.hardened_clay, "hardened_clay", "hardened_clay");
		register(Blocks.coal_block, "coal_block", "coal_block");
		register(Blocks.packed_ice, "packed_ice", "ice_packed");
		register(Blocks.red_sandstone, "red_sandstone", "up=red_sandstone_top,down=red_sandstone_bottom,side=red_sandstone_normal",
														"up=red_sandstone_top,down=red_sandstone_top,side=red_sandstone_carved",
														"up=red_sandstone_top,down=red_sandstone_top,side=red_sandstone_smooth");


	}



	public static Iterator<SlopeEntry> getSlopeEntries() {

		return list.values().iterator();
	}

	public static SlopeEntry getSlopeEntry(String key) {

		return list.get(key);
	}

}




















